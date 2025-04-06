package com.ezcode.utils.exception;


import com.ezcode.utils.ResponseResource;
import com.ezcode.utils.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ResponseResource<String>> handleCommonException(CommonException ex) {
        ResponseResource<String> response = new ResponseResource<>(
                ex.getStatusCode(),
                ex.getMessage(),
                ResponseResource.RES_FAILURE,
                Status.FAILURE
        );
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseResource<String>> handleGenericException(Exception ex) {
        ResponseResource<String> response = new ResponseResource<>(
                ResponseResource.R_CODE_INTERNAL_SERVER_ERROR,
                "Internal Server Error",
                ResponseResource.RES_FAILURE,
                Status.FAILURE
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
