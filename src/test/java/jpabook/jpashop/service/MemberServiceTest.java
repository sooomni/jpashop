package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    //@Rollback(false)
    public void 회원가입() throws Exception{

            //given
            Member member = new Member();
            member.setName("kim1");

            //when
            Long saveId = memberService.join(member);
            em.flush();
            //then
            assertEquals(member,memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    @Transactional
//    @Rollback(false)
    public void 중복_회원_예외() throws Exception{


             //given
             Member member1 = new Member();
             member1.setName("kim");
             Member member2 = new Member();
             member2.setName("kim");

             //when
             memberService.join(member1);
             memberService.join(member2);

             //then
             fail("예외 발생");
         }

    }