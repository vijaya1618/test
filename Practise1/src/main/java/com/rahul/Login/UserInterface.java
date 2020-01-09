package com.rahul.Login;

public interface UserInterface {
public int validuser(String username,String password,String role);
public String click(String username);
public LoginModel get_Userphno(String username);
public int ChangePassword(String password,String username);
}
