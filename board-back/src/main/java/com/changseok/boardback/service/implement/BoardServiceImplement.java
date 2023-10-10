package com.changseok.boardback.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.changseok.boardback.dto.request.board.PatchBoardRequestDto;
import com.changseok.boardback.dto.request.board.PostBoardRequestDto;
import com.changseok.boardback.dto.request.board.PostCommentRequestDto;
import com.changseok.boardback.dto.response.ResponseDto;
import com.changseok.boardback.dto.response.board.DeleteBoardResponseDto;
import com.changseok.boardback.dto.response.board.GetBoardResponseDto;
import com.changseok.boardback.dto.response.board.GetCommentListResponseDto;
import com.changseok.boardback.dto.response.board.GetFavoriteListResponseDto;
import com.changseok.boardback.dto.response.board.GetLatestBoardListResponseDto;
import com.changseok.boardback.dto.response.board.GetUserBoardListResponseDto;
import com.changseok.boardback.dto.response.board.IncreaseViewCountResponseDto;
import com.changseok.boardback.dto.response.board.PatchBoardResponseDto;
import com.changseok.boardback.dto.response.board.PostBoardResponseDto;
import com.changseok.boardback.dto.response.board.PostCommentResponseDto;
import com.changseok.boardback.dto.response.board.PutFavoriteResponseDto;
import com.changseok.boardback.entity.BoardEntity;
import com.changseok.boardback.entity.BoardImageEntity;
import com.changseok.boardback.entity.BoardViewEntity;
import com.changseok.boardback.entity.CommentEntity;
import com.changseok.boardback.entity.FavoriteEntity;
import com.changseok.boardback.entity.UserEntity;
import com.changseok.boardback.repository.BoardImageRepository;
import com.changseok.boardback.repository.BoardRepository;
import com.changseok.boardback.repository.BoardViewRepository;
import com.changseok.boardback.repository.CommentRepository;
import com.changseok.boardback.repository.FavoriteRepository;
import com.changseok.boardback.repository.UserRepository;
import com.changseok.boardback.repository.resultSet.CommentListResultSet;
import com.changseok.boardback.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService{
    
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final FavoriteRepository favoriteRepository;
    private final BoardViewRepository boardViewRepository;
    private final BoardImageRepository boardImageRepository;

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {
        
        try {

            boolean existedUser = userRepository.existsByEmail(email);
            if (!existedUser) return PostBoardResponseDto.notExistUser();

            BoardEntity boardEntity = new BoardEntity(dto, email);
            boardRepository.save(boardEntity);

            List<String> boardImageList = dto.getBoardImageList();
            Integer boardNumber = boardEntity.getBoardNumber();

            List<BoardImageEntity> boardImageEntities = new ArrayList<>();
            for (String boardImage: boardImageList) {
                BoardImageEntity boardImageEntity = new BoardImageEntity(boardNumber, boardImage);
                boardImageEntities.add(boardImageEntity);
            }

            boardImageRepository.saveAll(boardImageEntities);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

    return PostBoardResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {
        
        BoardViewEntity boardViewEntity = null;
        List<BoardImageEntity> boardImageEntities = new ArrayList<>();

        try {

            boardViewEntity = boardViewRepository.findByBoardNumber(boardNumber);
            if (boardViewEntity == null) return GetBoardResponseDto.notExistBoard();

            boardImageEntities = boardImageRepository.findByBoardNumber(boardNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetBoardResponseDto.success(boardViewEntity, boardImageEntities);
    }

    @Override
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber) {

        List<UserEntity> userEntities = new ArrayList<>();
        
        try {

            boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
            if (!existedBoard) return GetFavoriteListResponseDto.notExistBoard();

            userEntities = userRepository.findByBoardFavorite(boardNumber);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return GetFavoriteListResponseDto.success(userEntities);
    }

    @Override
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {
        
        List<BoardViewEntity> boardViewEntities = new ArrayList<>();

        try {
            
            boardViewEntities = boardViewRepository.findByOrderByWriteDatetimeDesc();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetLatestBoardListResponseDto.success(boardViewEntities);
    }

    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email) {

        try {

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return PutFavoriteResponseDto.notExistBoard();

            boolean existedUser = userRepository.existsByEmail(email);
            if (!existedUser) return PutFavoriteResponseDto.notExistUser();

            boolean isFavorite = favoriteRepository.existsByUserEmailAndBoardNumber(email, boardNumber);
            
            FavoriteEntity favoriteEntity = new FavoriteEntity(email, boardNumber);

            if (isFavorite) {
                favoriteRepository.delete(favoriteEntity);
                boardEntity.decreaseFavoriteCount();
            }
            else {
                favoriteRepository.save(favoriteEntity);
                boardEntity.increaseFavoriteCount();
            } 

            boardRepository.save(boardEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PutFavoriteResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber) {

        List<CommentListResultSet> resultSets = new ArrayList<>();

        try {

            boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
            if (!existedBoard) return GetCommentListResponseDto.notExistBoard();

            resultSets = commentRepository.findByCommentList(boardNumber);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetCommentListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String email) {
            
        try {
            
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return PostCommentResponseDto.notExistBoard();

            boolean existedUser = userRepository.existsByEmail(email);
            if (!existedUser) return PostCommentResponseDto.notExistUser();

            CommentEntity commentEntity = new CommentEntity(dto, boardNumber, email);
            commentRepository.save(commentEntity);

            boardEntity.increaseCommentCount();
            boardRepository.save(boardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostCommentResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String email) {
        
            
        
        try {

            boolean existedUser = userRepository.existsByEmail(email);
            if (!existedUser) return PostCommentResponseDto.notExistUser();

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return PatchBoardResponseDto.notExistBoard();

            boolean equalWriter = boardEntity.getWriterEmail().equals(email);
            if (!equalWriter) return PatchBoardResponseDto.noPermission();

            boardEntity.patch(dto);
            boardRepository.save(boardEntity);

            List<String> boardImageList = dto.getBoardImageList();

            boardImageRepository.deleteByBoardNumber(boardNumber);

            List<BoardImageEntity> boardImageEntities = new ArrayList<>();
            for (String boardImage: boardImageList) {
                BoardImageEntity boardImageEntity = new BoardImageEntity(boardNumber, boardImage);
                boardImageEntities.add(boardImageEntity);
            }
            boardImageRepository.saveAll(boardImageEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchBoardResponseDto.success();
    }

    @Override
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email) {

        try {
            
            boolean existedUser = userRepository.existsByEmail(email);
            if (!existedUser) return DeleteBoardResponseDto.notExistUser();

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return DeleteBoardResponseDto.notExistBoard();
            
            boolean isWriter = boardEntity.getWriterEmail().equals(email);
            if (!isWriter) return DeleteBoardResponseDto.noPermission();

            commentRepository.deleteByBoardNumber(boardNumber);
            favoriteRepository.deleteByBoardNumber(boardNumber);
            boardImageRepository.deleteByBoardNumber(boardNumber);
            boardRepository.delete(boardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteBoardResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String email) {

        List<BoardViewEntity> boardViewEntities = new ArrayList<>();

        try {

            boolean existedUser = userRepository.existsByEmail(email);
            if (!existedUser) return GetUserBoardListResponseDto.notExistUser();

            boardViewEntities = boardViewRepository.findByWriterEmailOrderByWriteDatetimeDesc(email);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetUserBoardListResponseDto.success(boardViewEntities);
    }

    @Override
    public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber) {

        try {
            
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return IncreaseViewCountResponseDto.notExistBoard();

            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return IncreaseViewCountResponseDto.success();
    }

}
