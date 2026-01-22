package com.example.jpa.controller;

import com.example.jpa.domain.Member;
import com.example.jpa.dto.MemberDTO;
import com.example.jpa.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // JSON 반환을 위해 변경
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/members") // API 경로 구분을 위해 /api 추가 권장
@CrossOrigin(origins = "http://localhost:5173") // 리액트 서버 허용
public class MemberController {

    private final MemberService memberService;

    // 페이징 처리된 리스트 반환
    @GetMapping("/list")
    public ResponseEntity<Page<Member>> getList(
            @PageableDefault(size=5, sort = "memberId", direction = Sort.Direction.DESC) Pageable pageable){

        log.info("REST controller pageable: " + pageable);
        return ResponseEntity.ok(memberService.findByAll(pageable));
    }

    @GetMapping("/new")
    public void getNew(){

    }

    // 등록
    @PostMapping("/new")
    public ResponseEntity<String> register(@RequestBody MemberDTO memberDTO){
        log.info("-----------------------------");
        log.info(memberDTO);
        memberService.insert(memberDTO);
        return ResponseEntity.ok("Success");
    }

    // 삭제
    @DeleteMapping("/{id}") // PathVariable과 DeleteMapping 조합
    public ResponseEntity<String> delete(@PathVariable("id") int memberId){
        memberService.delete(memberId);
        return ResponseEntity.ok("Deleted");
    }

    // 상세 조회 (수정 폼용)
    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getOne(@PathVariable("id") int memberId){
        return ResponseEntity.ok(memberService.findById(memberId));
    }

    // 수정 처리
    @PutMapping("/{id}")
    public ResponseEntity<String> editPost(@PathVariable("id") int memberId,
                                           @RequestBody MemberDTO memberDTO){
        memberService.update(memberId, memberDTO);
        return ResponseEntity.ok("Updated");
    }

}
