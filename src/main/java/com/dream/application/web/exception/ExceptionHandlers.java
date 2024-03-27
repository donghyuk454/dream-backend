package com.dream.application.web.exception;

import com.dream.application.common.exception.DreamException;
import com.dream.application.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlers {

    @ResponseBody
    @ExceptionHandler(DreamException.class)
    public BaseResponse<String> handleDreamException(Exception e){
        log.info("에러 발생 message = {}", e.getMessage());
        return BaseResponse.error(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public BaseResponse<String> handleException(Exception e){
        log.info("에러 발생 message = {}", e.getMessage());
        return BaseResponse.error(e.getMessage());
    }
}
