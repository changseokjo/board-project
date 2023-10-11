package com.changseok.boardback.service;

import org.springframework.http.ResponseEntity;

import com.changseok.boardback.dto.request.board.PatchBoardRequestDto;
import com.changseok.boardback.dto.request.board.PostBoardRequestDto;
import com.changseok.boardback.dto.request.board.PostCommentRequestDto;
import com.changseok.boardback.dto.response.board.DeleteBoardResponseDto;
import com.changseok.boardback.dto.response.board.GetBoardResponseDto;
import com.changseok.boardback.dto.response.board.GetCommentListResponseDto;
import com.changseok.boardback.dto.response.board.GetFavoriteListResponseDto;
import com.changseok.boardback.dto.response.board.GetLatestBoardListResponseDto;
import com.changseok.boardback.dto.response.board.GetSearchBoardListResponseDto;
import com.changseok.boardback.dto.response.board.GetTop3BoardListResponseDto;
import com.changseok.boardback.dto.response.board.GetUserBoardListResponseDto;
import com.changseok.boardback.dto.response.board.IncreaseViewCountResponseDto;
import com.changseok.boardback.dto.response.board.PatchBoardResponseDto;
import com.changseok.boardback.dto.response.board.PostBoardResponseDto;
import com.changseok.boardback.dto.response.board.PostCommentResponseDto;
import com.changseok.boardback.dto.response.board.PutFavoriteResponseDto;

public interface BoardService {
    
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);

    ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String email);

    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber);
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber);
    ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList();
    ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String email);
    ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList();
    ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord, String preSearchWord);

    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email);

    ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String email);
    ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber);

    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email);
}
