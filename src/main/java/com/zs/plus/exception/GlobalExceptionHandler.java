package com.zs.plus.exception;

import com.zs.plus.domain.CodeMsg;
import com.zs.plus.domain.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
  
  @ExceptionHandler(value = Exception.class)
  public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
    e.printStackTrace();
    if (e instanceof GlobalException) {
      GlobalException globalException = (GlobalException) e;
      return Result.error(((GlobalException) e).getCodeMsg());
    } else if (e instanceof BindException) {
      BindException bindException = (BindException) e;
      List<ObjectError> errors = bindException.getAllErrors();
      ObjectError error = errors.get(0);
      String msg = error.getDefaultMessage();
      return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
    } else {
      return Result.error(CodeMsg.SERVER_ERROR);
    }
  }
  
}
