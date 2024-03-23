package com.dream.application.web.auth.interceptor;

import com.dream.application.common.session.SessionConst;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute(SessionConst.LOGIN_SESSION_KEY) != null;
    }
}
