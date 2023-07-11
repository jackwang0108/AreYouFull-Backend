package com.ayf.areyoufull.filter;

import com.ayf.areyoufull.controller.LoginController;
import com.ayf.areyoufull.entity.Result;
import com.ayf.areyoufull.utils.Constant;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@WebFilter("/*")
@Slf4j
public class SecurityFilter implements Filter {
    @Autowired
    private LoginController loginController;

//    @Autowired
//    public SecurityFilter(LoginController loginController) {
//        this.loginController = loginController;
//    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();
        System.out.println(path);

        // 白名单
        if(path.equals("/")
                || path.equals("/captcha/captchaImage")
                || path.equals("/login")
                || path.equals("/logout")) {
            // 继续传递
            filterChain.doFilter(request, response);
            return;
        }

        // 静态资源放行
        if(path.endsWith(".html")
                || path.endsWith(".css")
                || path.endsWith(".js")
                || path.endsWith(".jpg")
                || path.endsWith(".jpeg")
                || path.endsWith(".png")
                || path.endsWith(".gif")
                || path.endsWith(".webp")
                || path.startsWith("/whimg")
                || path.contains("/download")
        ) {
            // 继续传递
            filterChain.doFilter(request, response);
            return;
        }

        // 检查Token
        String clientToken = request.getHeader(Constant.HEADER_TOKEN_NAME);
        Result result = loginController.verifyToken(clientToken);
        if(result.isSuccess()){
            filterChain.doFilter(request, response);
            return;
        }


        // 其它情况，将校验结果返回
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result); // 将result对象转成json串
        out.print(json);
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {}
}
