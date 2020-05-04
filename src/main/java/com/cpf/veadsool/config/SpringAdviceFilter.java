package com.cpf.veadsool.config;

import com.cpf.veadsool.base.BusinessException;
import com.cpf.veadsool.base.ErrorConstant;
import com.cpf.veadsool.base.Result;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Reason: 拦截通用错误信息.
 *
 * @author Chen Lingang
 * @version $Id: SpringAdviceFilter, vo 0.1 16/8/8 上午11:26
 */
@ControllerAdvice
@RestControllerAdvice
public class SpringAdviceFilter {

    private static Logger logger = LoggerFactory.getLogger(SpringAdviceFilter.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(Result.OnlyHintView.class)
    public @ResponseBody
    Result handlerIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) throws IOException {
        if (logger.isErrorEnabled()) {
            logger.error("『断言』业务断言错误: ", ex);
        }

        Result result = new Result();
        result.setStatus(ErrorConstant.FAIL);
        result.setText(ex.getMessage());
        result.setDescription(ex.getMessage());
        return result;
    }


    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(Result.OnlyHintView.class)
    public @ResponseBody
    Result handlerBusinessException(BusinessException ex) {
        if (logger.isErrorEnabled()) {
            logger.error("『断言』业务断言错误: ", ex);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintStream(baos));
        String message = ex.getMessage();
        return new Result(ex.getCode(), message, baos.toString());
    }


}
