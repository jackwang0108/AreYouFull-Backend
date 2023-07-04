package com.ayf.areyoufull.utils;

import com.ayf.areyoufull.entity.Result;
import com.ayf.areyoufull.exception.BusinessException;
import com.ayf.areyoufull.exception.SysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException e){
        return Result.err(Result.CODE_ERR_BUSINESS, e.getMessage());
    }

    @ExceptionHandler(SysException.class)
    public Result handleSysException(SysException e){
        log.error("系统错误！", e);
        return Result.err(Result.CODE_ERR_SYS, "系统错误!");
    }

    @ExceptionHandler
    public Result handleException(Throwable t){
        log.error("系统错误！", t);
        return Result.err(Result.CODE_ERR_SYS, "系统错误!");
    }
}
