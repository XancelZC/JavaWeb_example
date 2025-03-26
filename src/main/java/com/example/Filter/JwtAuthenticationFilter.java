package com.example.Filter;

import com.alibaba.fastjson.JSON;
import com.example.Utils.JwtUtil;
import com.example.pojo.Result;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("token");

        if(StringUtils.isEmpty(token)){
            filterChain.doFilter(request,response);
            return;
        }

        try {
            Claims claims = JwtUtil.parseToken(token);
            Integer userId = claims.get("userId",Integer.class);
        } catch (Exception e) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(Result.error("NOT_LOGIN")));
            return;
        }
    }
}

/**
 * AI版本
 */
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    // 使用Spring的路径匹配器，支持Ant风格的路径模式
//    private final AntPathMatcher pathMatcher = new AntPathMatcher();
//
//    // 定义不需要认证的URL路径
//    private static final List<String> EXCLUDED_PATHS = Arrays.asList(
//            "/login",
//            "/register",
//            "/public/**"
//    );@Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        // 获取请求的URI路径
//        String path = request.getRequestURI();// 检查是否是排除在认证之外的路径
//        if (shouldExclude(path)) {
//            // 登录等路径直接放行
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        // 获取token
//        String token = request.getHeader("token");
//
//        // 检查token是否为空
//        if (!StringUtils.hasText(token)) {
//            // token为空，返回未登录错误
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(JSON.toJSONString(Result.error("NOT_LOGIN")));
//            return;
//        }
//
//        try {
//            // 解析token
//            Claims claims = JwtUtil.parseToken(token);
//            Integer userId = claims.get("userId", Integer.class);
//            String username = claims.getSubject();
//
//            // 将用户信息存储在请求属性中，以便后续业务逻辑使用
//            request.setAttribute("userId", userId);
//            request.setAttribute("username", username);
//
//            // 继续执行过滤链
//            filterChain.doFilter(request, response);} catch (Exception e) {
//            // token解析失败，返回未登录错误
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(JSON.toJSONString(Result.error("NOT_LOGIN")));
//        }
//    }
//
//    /**
//     * 检查请求路径是否应该被排除
//     */
//    private boolean shouldExclude(String path) {
//        for (String excludedPath : EXCLUDED_PATHS) {
//            if (pathMatcher.match(excludedPath, path)) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
