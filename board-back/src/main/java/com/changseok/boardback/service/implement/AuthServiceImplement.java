package com.changseok.boardback.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.changseok.boardback.dto.request.auth.SignUpRequestDto;
import com.changseok.boardback.dto.response.ResponseDto;
import com.changseok.boardback.dto.response.auth.SignUpResponseDto;
import com.changseok.boardback.service.AuthService;

@Service
public class AuthServiceImplement implements AuthService {

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        
        try {

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("DBE", "Database Error."));
        }

        return null;

    }
    
}