package com.lening.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class MyInterceptor implements HandlerInterceptor {


    private List<String> exceptUrls;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
        System.out.println(uri);
        //uri和url有什么区别
        if(exceptUrls.contains(uri)){
            //直接放行
            return true;
        }else{
            //需要过滤的，首先要判断他有没有登录，要是没有登录回去登录
            Object ub = request.getSession().getAttribute("ub");
            if(ub==null){
                //他不是特殊的，也没有登录，直接让去登录就OK啦
                request.getRequestDispatcher("/index.html").forward(request, response);
            }else{
                return true;
            }
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public List<String> getExceptUrls() {
        return exceptUrls;
    }

    public void setExceptUrls(List<String> exceptUrls) {
        this.exceptUrls = exceptUrls;
    }
}
