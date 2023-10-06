package com.changseok.boardback.dto.request.board;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostCommentRequestDto {
    @NotBlank   //문자열에만 쓰임
    private String content;
}
