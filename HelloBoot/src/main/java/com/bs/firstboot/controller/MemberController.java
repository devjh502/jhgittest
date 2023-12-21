package com.bs.firstboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.firstboot.model.dto.Member;
import com.bs.firstboot.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
// 롬복을 사용하여 각각 로깅과 생성자 주입 간편하게 처리하도록 함.
@Slf4j
@RequiredArgsConstructor

// 클래스가 RESTful웹 서비스의 컨트롤러임을 나타냄.
@RestController //@Controller + @ResponseBody

// 컨트롤러의 모든 매핑이 "/members"경로에서 시작됨을 지정
@RequestMapping("/members")
//@CrossOrigin(origins = "http://localhost:3000") //이 컨트롤러에 있는 건 다 허용하겠다. 그럼 다 해줘야함 모든곳에
public class MemberController {
   
   // MemberService라는 서비스를 주입받아서 이를 통해 비즈니스 로직 수행
   private final MemberService service;
   
   // /members경로로 요청이 오면 회원목록을 조회하는 메서드를 호출하여 반환
   @GetMapping
   public ResponseEntity<List<Member>> selectMemberAll(){
      List<Member> list=service.selectMemberAll();
      
      return ResponseEntity.status(HttpStatus.OK).body(list);
   }
   
   @GetMapping("/{id}")
   public ResponseEntity<Member> selectMemberById(@PathVariable String id){
      Member m=service.selectById(id);
      //return ResponseEntity.ok(m);
      return ResponseEntity.status(HttpStatus.OK).body(m);
   }
   
   /* HttpStatus 
    * CREATED: 정상처리 및 자원을 생성
    * OK: 요청이 성공적으로 처리됐을 때
    *BAD_REQUEST: 요청값이 잘못되어 요청에 실패했을 때
    * FORBIDDEN: 권한이 없는 요청을 했을 때
    * NOT_FOUND: 서비스를 찾을 수 없을 때
    * 
    * 
    * */
   
   
   // /members경로로 요청이 오면 요청 바디에 담긴 회원정보 받아와서 메서드 호출하여 회원 등록
   @PostMapping
   public ResponseEntity insertMember(@RequestBody Member member) {
      int result=service.insertMember(member);
      return ResponseEntity.status(HttpStatus.CREATED).body(result);
   }
   

   @PutMapping("/{id}")
	public ResponseEntity<Member> updateMember(@PathVariable String id,
			@RequestBody Member member) {
		try {
			int result=service.updateMember(member);
			return ResponseEntity.ok(member);
			
		}catch(Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
      
   
   @DeleteMapping("/{id}")
   public ResponseEntity<Object> deleteMember(@PathVariable String id) {
      int result=service.deleteMember(id);
      
      return ResponseEntity.ok().build();
   }
   
   // 회원정보에 대한 HTTP요청을 처리하며 실제 비즈니스 로직은 MemberService클래스에 위임되어 처리됨.
   
}