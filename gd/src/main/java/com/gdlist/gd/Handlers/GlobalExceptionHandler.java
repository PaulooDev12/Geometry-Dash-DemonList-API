package com.gdlist.gd.Handlers;

import com.gdlist.gd.Dto.ErrorResponse;
import com.gdlist.gd.Exception.LevelAlredyExists;
import com.gdlist.gd.Exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LevelAlredyExists.class)
    public ResponseEntity<ErrorResponse> handleConflict(LevelAlredyExists ex) {
        ErrorResponse response = new ErrorResponse(409, ex.getMessage(), "Não é possivel salvar um level em uma posição já existente");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(404, ex.getMessage(), "Recurso nao encontrado / resource not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(
                        400,
                        message,
                        "Erro na requisicão confira as validações e tente novamente")
        );

        }
    }
