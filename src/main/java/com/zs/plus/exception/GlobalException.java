package com.zs.plus.exception;

import com.zs.plus.domain.CodeMsg;

public class GlobalException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;
  
  private CodeMsg codeMsg;
  
  public GlobalException(CodeMsg codeMsg) {
    super(codeMsg.toString());
    this.codeMsg = codeMsg;
  }
  
  public CodeMsg getCodeMsg() {
    return codeMsg;
  }
}
