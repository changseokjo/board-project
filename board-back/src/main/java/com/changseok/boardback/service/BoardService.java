package com.changseok.boardback.service;

import org.springframework.http.ResponseEntity;

import com.changseok.boardback.dto.request.board.PostBoardRequestDto;
import com.changseok.boardback.dto.response.board.PostBoardResponseDto;

public interface BoardService {
    
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);


}
