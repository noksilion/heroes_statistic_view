package heroku_demo.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import heroku_demo.api.services.RestRequestServices;
import heroku_demo.api.services.RestTemplateResponseErrorHandler;
import heroku_demo.api.filters.TokenFilter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.Filter;

@Configuration
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".ftl");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**","/js/**","/templates/**","/img/**")
                .addResourceLocations("classpath:/static/css/","classpath:/static/js/","classpath:/templates/","classpath:/static/img/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplateBuilder()
                .errorHandler(new RestTemplateResponseErrorHandler(objectMapper()))
                .build();
    }

    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(tokenFilter());
        registration.addUrlPatterns("/view/add_battle","/add_battle","/approve_one_game","/view_unapproved_games","/","/view/statistic","/statistic","/get_stats_html");
        registration.setName("tokenFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean(name = "tokenFilter")
    public Filter tokenFilter() {
        return new TokenFilter(restRequestServices());
    }

    @Bean
    public RestRequestServices restRequestServices(){
        return new RestRequestServices(restTemplate());
    }


}
