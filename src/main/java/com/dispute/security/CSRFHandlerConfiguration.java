package com.dispute.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.dispute.user.UserComponent;

@Configuration
public class CSRFHandlerConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean
	public CSRFHandlerInterceptor cSRFHandlerInterceptor(){
		return new CSRFHandlerInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(cSRFHandlerInterceptor());
		
	}
}

class CSRFHandlerInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private UserComponent userComponent;
	
	@Override
    public void postHandle(final HttpServletRequest request,
            final HttpServletResponse response, final Object handler,
            final ModelAndView modelAndView) throws Exception {
		
		try{
			//CrsfToken
			
			CsrfToken token = (CsrfToken) request.getAttribute("_csrf"); 
			
	    	modelAndView.addObject("token", token.getToken());    	
	    	
	    	//User Login Info
	    	if(userComponent !=null){
		    	if(userComponent.isLoggedUser()){
		    		modelAndView.addObject("isLogged", true);
		    		modelAndView.addObject("adminPage", request.isUserInRole("ADMIN"));
		    		modelAndView.addObject("userLogged", userComponent.getLoggedUser());
		    	} else {
		    		modelAndView.addObject("isLogged", false);
		    	}
	    	}
		} catch(Exception e){
		}
    }
	
}
