package com.red.advertising.exception.handler;


import com.red.advertising.exception.EntityNotFoundException;
import com.red.advertising.exception.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class DiscountExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDTO> EntityNotFoundExceptionHandler(EntityNotFoundException entityNotFoundException) {
        return new ResponseEntity<>(ExceptionResponseDTO.builder()
                .time(LocalDateTime.now())
                .message(entityNotFoundException.getMessage())
                .status(HttpStatus.BAD_REQUEST).build(), HttpStatus.BAD_REQUEST);
    }

}
