package com.project.isc.iscdbserver.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.isc.iscdbserver.annotation.Auth;
import com.project.isc.iscdbserver.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
@CrossOrigin
public class LoginInterceptor implements HandlerInterceptor {

    private String httpHeaderName = "Authorization";

    private String unauthorizedErrorMessage = "401 unauthorized";

    private static final String CURRENT_USER = "REQUEST_CURRENT_KEY";

    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
        response.setHeader( "Access-Control-Expose-Headers", "Authorization");
        if (!(handle instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handle;
        Method method = handlerMethod.getMethod();
        // 如果打上了@Auth注解,则进行登录验证
        if (method.getAnnotation(Auth.class) != null || handlerMethod.getBeanType().getAnnotation(Auth.class) != null) {
            String token = request.getHeader(httpHeaderName);
            Long aliveTime = redisService.getFreeTime(token);
            System.out.println("剩余存活时间:" + aliveTime);
            String account = "";
            if (token != null && token.length() != 0) {
                account = (String)redisService.getObj(token);
                System.out.println(account);
            }
            //  如何token剩余时间小于10分钟,自动续期
            if (account != null && !account.trim().equals("")) {
                if (aliveTime < 900) {
                    redisService.setKeyExpireSecond(token, 1800);
                }
                request.setAttribute(CURRENT_USER, account);
                return true;
            } else {
                PrintWriter out = null;
                JSONObject msg = new JSONObject();
                try {
                    response.setStatus(unauthorizedErrorCode);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setHeader( "Access-Control-Allow-Origin","*");

                    msg.put("code", 401);
                    msg.put("message", unauthorizedErrorMessage);
                    out = response.getWriter();
                    out.println(msg + "");
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != out) {
                        out.flush();
                        out.close();
                    }
                }

            }
        }
        request.setAttribute(CURRENT_USER, null);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
