package com.java.interceptor;

import com.java.service.IUserInfoService;
import com.java.utils.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/8/17.
 */
public class UserInfoInterceptor implements HandlerInterceptor{
    @Resource
    private IUserInfoService userInfoService;

    private static final Logger logger = LoggerFactory.getLogger(UserInfoInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
       logger.debug("拦截开始.............");

        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 读取本地ip地址文件
     * @param request
     * @return
     */
    private String getIpDBFilePath(HttpServletRequest request) {
        Properties properties = new Properties(null);
        try {
            properties.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = "";
        path = properties.getValue("ip_file_path").trim();
        return path;
    }


    /**
     * 获取ip
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
