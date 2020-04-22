package com.zs.plus.validator;

import com.zs.plus.util.ValidatorUtil;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author zs
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {
  
  private boolean required = false;
  
  @Override
  public void initialize(IsMobile isMobile) {
    required = isMobile.require();
  }
  
  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (required) {
      return ValidatorUtil.isMobile(s);
    } else {
      if (StringUtils.isEmpty(s)) {
        return false;
      } else {
        return ValidatorUtil.isMobile(s);
      }
    }
  }
}
