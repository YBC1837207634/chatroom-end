package com.example.Interceptor;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.example.common.BaseContent;
import com.example.entity.Result;
import com.example.utils.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token != null) {
            Map<String, Claim> claimMap = JWTUtils.verifyToken(token);
            if (claimMap != null) {
                BaseContent.set(claimMap.get("userId").asInt());  // 保存 id
                return true;
            }
        }
        Result<String> result = Result.error("NOT_LOGIN");
        response.getWriter().println(JSON.toJSONString(result));
        return false;
    }

}
