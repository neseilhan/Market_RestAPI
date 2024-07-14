package dev.example.emarket.core.modelmapper;

import dev.example.emarket.core.config.Msg;
import dev.example.emarket.core.config.ResultData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import java.util.stream.Collectors;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e){
        List<String> validationErrorList = e.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        ResultData<List<String>> resultData = new ResultData<>("400", Msg.VALIDATE_ERROR, false, validationErrorList );
        return new ResponseEntity<>(resultData, HttpStatus.BAD_REQUEST);
    }
}
