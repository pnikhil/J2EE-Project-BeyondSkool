/*package com.beyondskool.filter;

import com.beyondskool.vo.UserVO;
import javax.ws.rs.core.SecurityContext;

import java.io.IOException;
import java.security.Principal;

public class AppSecurityContext implements SecurityContext, Authentication {
    private UserVO user;
    private String scheme;
    
    AppSecurityContext(UserVO user, String scheme){    	
    	this.user = user;
    	this.scheme = scheme;    	
    }
    
    @Override
    public Principal getUserPrincipal() {
        return new Principal() {
            @Override
            public String getName() {
                return user.getUserName();
            }
        };
    }

    @Override
    public boolean isUserInRole(String role) {
        return role.equals(user.getCurrentUserRole());
    }
    
    public String getUserRole(){
    	return user.getCurrentUserRole();
    }
    
	@Override
	public void setCredentials(HttpExchange arg0) throws IOException {		
		
	}
	
	@Override
    public boolean isSecure() {return "https".equals(this.scheme);}
	
	@Override
	public String getAuthenticationScheme() {		
		return null;
	}
}*/