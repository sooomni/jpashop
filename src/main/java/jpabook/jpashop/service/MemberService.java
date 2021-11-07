package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
//final 있는 필드만 가지고 생성자 생성
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	//회원 가입
	@Transactional
	public Long join (Member member){
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	// Member 검증	
	public void validateDuplicateMember(Member member){
		//EXCEPTION
		List<Member> findMembers = memberRepository.findByName(member.getName());
		if(!findMembers.isEmpty()){
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}

	//회원 전체 조회
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	//회원 단권 조회
	public Member findOne(Long id){
		return memberRepository.findOne(id);
	}

}
