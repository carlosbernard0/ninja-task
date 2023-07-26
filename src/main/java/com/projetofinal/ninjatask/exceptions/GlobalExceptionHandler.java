package com.projetofinal.ninjatask.exceptions;

import com.projetofinal.ninjatask.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        HttpStatus badRequest =HttpStatus.BAD_REQUEST;
        List<String> listaDeErros= ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fildError -> fildError.getField() + ": "+ fildError.getDefaultMessage())
                .toList();

        return ResponseEntity.status(badRequest)
                .body(new ErrorDTO(new Date(), badRequest.value(),listaDeErros.toString()));

    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDTO> tratarBusinessException(BusinessException ex){
        HttpStatus status =HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status)
                .body(new ErrorDTO(new Date(), status.value(),ex.getMessage()));

    }
}
