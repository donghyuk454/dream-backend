package com.dream.application.web.auth.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!isLoggedIn(request)) {
            response.sendRedirect("/api/v1/auth/login");
            return false;
        }
        return true;
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("memberLongin") != null;
    }
}
