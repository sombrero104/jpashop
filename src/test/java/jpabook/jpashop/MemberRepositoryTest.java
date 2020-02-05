package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import test.Member;
import test.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    /**
     * 엔티티매니저를 통한 모든 데이터 변경은 항상 트랜잭션 안에서 이루어져야 한다.
     * => @Transactional 걸어줌.
     *
     * @Transactional 이 테스트 코드에 있으면
     * 테스트가 끝난 후 자동으로 롤백함.
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testMember() throws Exception {
        // given
        Member member = new Member();
        member.setUsername("memberA");

        // when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        /**
         * 같은 영속성 컨텍스트 내에서는 아이디 값이 같으면(식별자가 같으면) 같은 엔티티로 인식함.
         * 1차 캐시에 관리되고 있는 똑같은 엔티티가 있기 때문에 관리하던 엔티티로 사용함.
         */
        Assertions.assertThat(findMember).isEqualTo(member);
        System.out.println("findMember == member: " + (findMember == member));
    }

}