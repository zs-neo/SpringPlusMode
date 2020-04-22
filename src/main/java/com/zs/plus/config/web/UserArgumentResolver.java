package com.zs.plus.config.web;

import com.zs.plus.config.prefix.MUserKey;
import com.zs.plus.domain.MUser;
import com.zs.plus.service.MUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
  
  @Autowired
  MUserService mUserService;
  
  @Override
  public boolean supportsParameter(MethodParameter methodParameter) {
    Class<?> clazz = methodParameter.getParameterType();
    return clazz == MUser.class;
  }
  
  @Override
  public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
    HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
    HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
  
    String paramToken = request.getParameter(MUserService.COOKIE_NAME_TOKEN);
    String cookieToken = getCookieValue(request, MUserService.COOKIE_NAME_TOKEN);
    if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
      return null;
    }
    String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
    return mUserService.getByToken(response,token);
  }
  
  private String getCookieValue(HttpServletRequest request, String cookieName) {
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals(cookieName)) {
        return cookie.getValue();
      }
    }
    return null;
  }
  
}
