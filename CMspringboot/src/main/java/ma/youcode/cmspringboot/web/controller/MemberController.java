package ma.youcode.cmspringboot.web.controller;

import ma.youcode.cmspringboot.entity.Member;
import ma.youcode.cmspringboot.web.dto.memberDto.MemberResponseDto;
import ma.youcode.cmspringboot.web.dto.memberDto.memberRequestDto.MemberSaveDto;
import ma.youcode.cmspringboot.service.aftas.MemberService;
import ma.youcode.cmspringboot.web.dto.memberDto.memberRequestDto.MemberUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping()
    public ResponseEntity<List<MemberResponseDto>> getAllMembers(@RequestParam(defaultValue = "0")
                                                                 @Min(0) Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 4);
        Page<Member> members = memberService.getAllMember(pageRequest);
        List<MemberResponseDto> memberResponseDto = new ArrayList<>();
        members.stream().forEach(m -> {
            memberResponseDto.add(MemberResponseDto.toMemberResponseDto(m));
        });
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("X-Total-Page", String.valueOf(members.getTotalPages()))
                .body(memberResponseDto);
    }

    @PostMapping
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberSaveDto memberSaveDto) {
        Member member = memberService.createMember(MemberSaveDto.toMember(memberSaveDto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MemberResponseDto.toMemberResponseDto(member));
    }
    @GetMapping("/search")
    public ResponseEntity<List<MemberResponseDto>> searchMember(@RequestParam String keywordMember,
                                                    @RequestParam(defaultValue = "0") Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 3);
        Page<Member> memberPage = memberService.searchMember(keywordMember, pageRequest);
        List<MemberResponseDto> members = new ArrayList<>();
        memberPage.forEach(m -> {
            members.add(MemberResponseDto.toMemberResponseDto(m));
        });
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("X-Total-Page",String.valueOf(memberPage.getTotalPages()))
                .header("X-Total-Element",String.valueOf(memberPage.getTotalElements()))
                .body(members);
    }

    @PutMapping
    public ResponseEntity<MemberResponseDto> updateMember(@RequestBody  MemberUpdateDto member)
            throws NameNotFoundException {
        Member memberSaved = memberService.updateMember(MemberUpdateDto.toMember(member));

        return ResponseEntity.ok(MemberResponseDto.toMemberResponseDto(memberSaved));
    }
    @GetMapping("/update")
    public ResponseEntity<MemberResponseDto> getMemberByNum(@RequestParam("num")  Integer num){
        Member memberSaved = memberService.getMemberByNum(num);
        return ResponseEntity.ok(MemberResponseDto.toMemberResponseDto(memberSaved));
    }

}
