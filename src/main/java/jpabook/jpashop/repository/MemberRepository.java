package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext
    //Autowired 지원이 되어야 @RequiredArgsConstructor 사용 가능
    private final EntityManager em;

    /*@PersistenceInit
    private EntityManagerFactory emf;*/

    public long save (Member member){
        em.persist(member);
        return member.getId();
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m",Member.class)
                 .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name=:name", Member.class)
                 .setParameter("name",name);
                 ,getResultList();
    }

}