package com.changseok.boardback.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.changseok.boardback.common.object.BoardListItem;
import com.changseok.boardback.dto.response.ResponseCode;
import com.changseok.boardback.dto.response.ResponseDto;
import com.changseok.boardback.dto.response.ResponseMessage;
import com.changseok.boardback.entity.BoardViewEntity;

import lombok.Getter;

@Getter
public class GetTop3BoardListResponseDto extends ResponseDto {
    
    private List<BoardListItem> top3List;

    private GetTop3BoardListResponseDto(String code, String message, List<BoardViewEntity> boardViewEntities) {
        super(code, message);
        this.top3List = BoardListItem.getList(boardViewEntities);
    }

    public static ResponseEntity<GetTop3BoardListResponseDto> success(List<BoardViewEntity> boardViewEntities) {
        GetTop3BoardListResponseDto result = new GetTop3BoardListResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS, boardViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
