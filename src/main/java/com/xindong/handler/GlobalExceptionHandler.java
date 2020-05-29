package com.xindong.handler;

import com.xindong.common.Result;
import com.xindong.common.ResultCodeEnum;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error();
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public Result error(BadSqlGrammarException e) {
        e.printStackTrace();
        return Result.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result error(MyException e) {
        e.printStackTrace();
        return Result.error().msg(e.getMessage()).code(e.getCode());
    }
}
