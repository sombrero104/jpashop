package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    /**
     * [injection 방식]
     *
     * (1) 필드 injection 방식
     */
    /*@Autowired
    private MemberRepository memberRepository;*/

    /**
     * (2) setter injection 방식
     * 장점: 테스트 코드 작성 시 Mock을 직접 주입해서 사용할 수 있다.
     * 단점: 실제 서비스 시에는 어플리케이션이 뜰 때 모든게 조립이 완성되므로 사용할 일이 없다. 잘못 사용하면 문제 발생.
     */
    /*private MemberRepository memberRepository;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /**
     * (3) 생성자 injection 방식
     * 장점: 어플리케이션이 뜨고 서비스가 생성될 때에만 조립이 되기 때문에 중간에 set해서 바꿀 일이 없다.
     *  테스트 코드에서 MemberService 생성 시 MemberService가 MemberRepository를 의존하고 있다는 것을 명확하게 알 수 있다.
     *  스프링 최신 버전에서는 생성자가 하나만 있는 경우, @Autowired가 명시되어 있지 않아도 자동으로 injection 해준다.
     *
     * final을 명시해주면 생성자에서 값을 넣지 않는 경우 컴파일 에러가 나기 때문에 실수로 값을 넣지 않는 경우가 발생하지 않게 된다.
     */
    /*private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /**
     * (4) 생성자 injection 방식에서 롬복 사용
     * 롬복의 @AllArgsConstructor를 클래스에 명시해주면 필드를 가지고 위와 같이 값을 주입받는 생성자를 자동으로 만들어준다.
     * 더 좋은 방법은 @RequiredArgsConstructor인데, final이 있는 필드만 가지고 위와 같이 값을 주입받는 생성자를 자동으로 만들어준다.
     */
    private final MemberRepository memberRepository;





    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) { // 중복 회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * WAS가 여러대인 경우 동시에 저장하게 되면 아래 validate를 동시에 통과하는 문제가 발생할 수도 있다.
     * 때문에 DB에서 제약조건을 유니크로 걸어서 한번 더 방어하는 것이 좋다.
     */
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     * 조회용 메소드는 'readOnly = true' 옵션으로 성능 최적화.
     * 클래스에 선언하고 읽기용이 아닌 곳에만 디폴트인(디폴트가 false) @Transactional을 선언해줘도 된다.
     * (이 예제에서는 읽기가 더 많아서 클래스에 선언.)
     * 클래스에 선언하면 기본적으로 public인 메소드에는 다 적용된다.
     */
    // @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
