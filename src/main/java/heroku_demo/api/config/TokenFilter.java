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

public class TokenFilter extends OncePerRequestFilter {

    private final RestRequestServices restRequestServices;

    @Value("${restApplicationHost}")
    private String restApplicationHost;

    public TokenFilter(RestRequestServices restRequestServices) {
        this.restRequestServices = restRequestServices;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie tokenCookie = restRequestServices.getCookieByName(cookies, "token");
        if(tokenCookie == null){
            response.sendRedirect(restApplicationHost+"/forbidden?message=Forbidden");
        }
    }
}
