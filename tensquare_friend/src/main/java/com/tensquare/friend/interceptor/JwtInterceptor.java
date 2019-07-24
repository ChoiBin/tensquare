package com.tensquare.friend.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String header = request.getHeader("Authorization");

        if (header != null && !"".equals(header)) {
            if (header.startsWith("Bearer ")) {
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if (roles == null && roles.equals("admin")) {
                        request.setAttribute("claims_admin",claims);
                    }
                    if (roles == null && roles.equals("user")) {
                        request.setAttribute("claims_user",claims);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("令牌不正确！");
                }
          }
    }
        return true;
    }
}
