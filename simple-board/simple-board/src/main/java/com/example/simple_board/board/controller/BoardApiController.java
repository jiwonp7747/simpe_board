package com.example.simple_board.board.controller;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.model.BoardRequest;
import com.example.simple_board.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Controller와 ResponseBody를 합친 역할
@RequestMapping("/api/board") // 컨트롤러의 모든 핸들러 메서드가 이 경로를 기본 경로로 가진다.
@RequiredArgsConstructor // final 또는 @NotNull 필드를 인자로 받는 생성자를 자동으로 생성한다. (생성자 주입)
public class BoardApiController {

    private final BoardService boardService; // 빈을 주입받는다.

    @PostMapping("")
    public BoardEntity create(
            @Valid // boardRequest 객체의 유효성 검사
            @RequestBody // 요청 본문을 boardRequest 객체로 변환 매핑
            BoardRequest boardRequest // 요청데이터를 가지고 있는 객체
    ){
            return boardService.create(boardRequest); // service의 비즈니스 로직 처리
    }
}
// controller의 역할
// 사용자로부터 요청을 받고 그 요청 데이터를 service에 보낸다.

// service의 역할
// 요청 데이터를 받고 비즈니스 로직을 수행, Repository 계층과 상호작용, 데이터를 저장

// Repository 역할
// 데이터베이스와 상호작용하여 데이터의 crud 작업을 담당한다.

// Model 역할
// 애플리케이션의 데이터 구조를 정의하는 역할, DB의 테이블과 매핑, 엔티티라고 부른다.
