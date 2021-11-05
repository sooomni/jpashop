package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    @Rollback(false)
    public void 회원가입() throws Exception{

            //given
            Member member = new Member();
            member.setName("kim");

            //when

            //then
    }

    @Test
    @Transactional
    @Rollback(false)
    public void 중복_회원_예외() throws Exception{

            //given

            //when

            //then
    }

}