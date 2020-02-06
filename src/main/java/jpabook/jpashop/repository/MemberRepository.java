package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    /**
     * @PersistenceContext 를 붙이면
     * 스프링부트가 자동으로 EntityManager를 주입해줌.
     * 스프링부트에서는 @Autowired로도 사용 가능하다.
     */
    /*@PersistenceContext
    private EntityManager em;*/

    /**
     * @RequiredArgsConstructor 어노테이션 사용.
     * (설명은 MemberService 클래스 참조.)
     */
    private final EntityManager em;

    /**
     * 만약, EntityManagerFactory를 주입받고 싶을 경우.
     */
    /*@PersistenceUnit
    private EntityManagerFactory emf;*/

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    /**
     * JPQL과 SQL은 다르다.
     * SQL는 테이블을 대상으로 쿼리를 하고, JPQL은 엔티티를 대상으로 쿼리를 한다.
     */
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // 조회 타입은 Member.class
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
