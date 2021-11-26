package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    //@RequestBody : json data를 Member로 맞춰줌
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    /*
        1. 엔티티와 API Spec의 분리
            * 엔티티는 변동이 잦을 수 밖에 없는데 엔티티 변화로 API Spec이 변하는게 문제
            * Api Spec 을 위한 DTO(data transfer object) 를 파라미터로 받는 것이 좋음
        2. 개발자 입장에 엔티티만 보고 Front에서 어떤 값이 넘어올지 알 수 없음
            * 서비스에서 채워주는 건지, API 스펙에서 채워주는건지 등 알 수 없음
        3. 엔티티를 외부에 노출하지 않는 것도 장점
     */
    @PostMapping("api/v2/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setName(request.getName());
        memberService.join(member);
        Long id = member.getId();
        return new CreateMemberResponse(id);
    }

    @Data
    static class CreateMemberRequest{
        private String name;
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
