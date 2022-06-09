package com.company.exp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandlerController {
    @ExceptionHandler({BadRequestException.class, InformationNotFound.class})
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({NoPermissionException.class})
    public ResponseEntity<String> handleExp(javax.naming.NoPermissionException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
