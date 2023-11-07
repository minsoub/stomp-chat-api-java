package kr.co.fns.chat.core.token;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Slf4j
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        RequestWrapper rereadableRequestWrapper = new RequestWrapper((HttpServletRequest)request);
        chain.doFilter(rereadableRequestWrapper, response);
    }
}
