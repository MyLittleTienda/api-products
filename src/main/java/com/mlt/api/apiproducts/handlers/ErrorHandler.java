package com.mlt.api.apiproducts.handlers;

import com.mlt.api.common.domain.MltMessage;
import com.mlt.api.common.domain.response.MltResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MltResponse> handleArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<MltMessage> errors = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            errors.add(MltMessage.builder()
                                 .code(fieldError.getField())
                                 .message(fieldError.getDefaultMessage())
                                 .build());
        });
        return ResponseEntity.badRequest().body(MltResponse.builder().messages(errors).build());
    }

}
