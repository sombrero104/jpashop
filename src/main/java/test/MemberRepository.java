package test;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class MemberRepository {

    /**
     * @PersistenceContext 를 붙이면
     * 스프링부트가 자동으로 EntityManager를 주입해줌.
     * 엔티티매니저를 통한 모든 데이터 변경은 항상 트랜잭션 안에서 이루어져야 한다.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * 회원 저장.
     * Member 객체를 반환하지 않고 id만 반환하는 이유.
     * => 커맨드와 쿼리를 분리하라.
     * 저장하는 목적의 사이드이펙트를 일으키는 커맨드성 메소드이기 때문에 리턴값이 거의 필요없음.
     * 대신 아이디 정도는 조회할 수 있도록 아이디만 반환함.
     */
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    /**
     * 회원 id로 조회.
     */
    public Member find(Long id) {
        return em.find(Member.class, id);
    }

}
