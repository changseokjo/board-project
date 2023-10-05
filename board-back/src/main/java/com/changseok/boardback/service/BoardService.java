package com.changseok.boardback.service;

import org.springframework.http.ResponseEntity;

import com.changseok.boardback.dto.request.board.PostBoardRequestDto;
import com.changseok.boardback.dto.response.board.GetBoardResponseDto;
import com.changseok.boardback.dto.response.board.GetFavoriteListResponseDto;
import com.changseok.boardback.dto.response.board.GetLatestBoardListResponseDto;
import com.changseok.boardback.dto.response.board.PostBoardResponseDto;

public interface BoardService {
    
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);

    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber);
    ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList();

}
