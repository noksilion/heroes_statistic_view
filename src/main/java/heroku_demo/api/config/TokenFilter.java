package heroku_demo.api.config;

import heroku_demo.api.services.RestRequestServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)

public class TokenFilter implements Filter {

    private final RestRequestServices restRequestServices;

    @Value("${applicationHost}")
    private String applicationHost;

    public TokenFilter(RestRequestServices restRequestServices) {
        this.restRequestServices = restRequestServices;
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Cookie[] cookies = httpServletRequest.getCookies();
        Cookie tokenCookie = restRequestServices.getCookieByName(cookies, "token");
        if(tokenCookie == null){
            httpServletResponse.sendRedirect(applicationHost+"/forbidden?message=Forbidden");
        }
        else {
            chain.doFilter(request, response);
        }
    }
    @Override
    public void destroy() {
    }
}
