package demo.web.service.filter;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域拦截器
 * @author wq
 */
@Component
public class CorsConfig implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        StringBuilder allowHeaders = new StringBuilder();
        allowHeaders.append("accept,content-type,origin,referer,token,user-agent,x-ca-key,x-ca-signature,x-ca-nonce,x-ca-timestamp,content-md5,x-ca-stage,x-ca-signature-headers,");
        allowHeaders.append("Accept,Content-Type,Origin,Referer,User-Agent,X-Ca-Key,X-Ca-Signature,X-Ca-Nonce,X-Ca-Timestamp,Content-MD5,X-Ca-Stage,X-Ca-Signature-Headers");
        response.setHeader("Access-Control-Allow-Headers", allowHeaders.toString());
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Expose-Headers", "token");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
