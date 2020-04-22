package com.zs.plus.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidator.class})
/**
 * @author zs
 */
public @interface IsMobile {
  
  boolean require() default false;
  
  String message() default "format error";
  
  Class<?>[] groups() default {};
  
  Class<? extends Payload>[] payload() default {};
  
  
}
