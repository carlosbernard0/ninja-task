package com.projetofinal.ninjatask.exceptions;

import com.projetofinal.ninjatask.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDTO> tratarBusinessException(BusinessException ex){
        HttpStatus status =HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status)
                .body(new ErrorDTO(new Date(), status.value(),ex.getMessage()));

    }
}
