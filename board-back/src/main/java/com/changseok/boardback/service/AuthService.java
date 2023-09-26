package com.changseok.boardback.service;

import org.springframework.http.ResponseEntity;

import com.changseok.boardback.dto.request.auth.SignInRequestDto;
import com.changseok.boardback.dto.request.auth.SignUpRequestDto;
import com.changseok.boardback.dto.response.auth.SignInResponseDto;
import com.changseok.boardback.dto.response.auth.SignUpResponseDto;

public interface AuthService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

}