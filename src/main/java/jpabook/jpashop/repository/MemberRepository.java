package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    /**
     * @PersistenceContext 를 붙이면
     * 스프링부트가 자동으로 EntityManager를 주입해줌.
     */
    @PersistenceContext
    private EntityManager em;

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
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

}
