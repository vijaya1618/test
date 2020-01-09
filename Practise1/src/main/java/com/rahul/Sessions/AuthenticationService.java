package com.rahul.Sessions;
import com.rahul.Login.*;
public interface AuthenticationService {
public LoginModel getLoginCredential();
public void setLoginCredential(LoginModel l);
public void setDetails(Session_Model sm);
public Session_Model getDetails();
}
