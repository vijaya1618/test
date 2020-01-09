package com.rahul.Login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkmax.zul.Tablelayout;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class Forgot_Password_Cntrl extends Window{
	int otp;
	UserInterface db;
	LoginModel login=new LoginModel();

	String user_Name;
	public void sendotp() throws Exception{
				Textbox num=(Textbox)this.getFellow("user_name");
			try{
				 user_Name=num.getValue();
			}catch(NullPointerException e){user_Name=null;}
			if(user_Name==null){
				Clients.showNotification("Please Enter The UserName", "error", num, "end_center", 2000);
			}else{
				ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
				db = (UserInterface)ctx.getBean("db");
				login=db.get_Userphno(user_Name);
				if(login.getUser_phno()>0){
					Session se=Sessions.getCurrent(true);
					se.setAttribute("user_Name",user_Name);
				System.out.println(login.getUser_phno());
				String string_number=String.valueOf(login.getUser_phno());
				String digits=string_number.substring(6,10);
				Label notify=(Label)this.getFellow("inform");
				notify.setValue("OTP was send to Number end with  ****"+digits);
				System.out.println(login.getPassword());
				       try {
				          
				      
				            Random rd1=new Random();
				            otp=rd1.nextInt(999999);
				          
				// Construct data+
				           
				String apiKey = "apikey=" + "aLF1nz6SWPE-4CijNZ6uYID67dRfo1h7QnfrefPHGU";
				String message = "&message=" +"Hey Your OTP for Gold Loan Mangement System Reset Password is:"+" "+ otp;
				String sender = "&sender=" + "TXTLCL";
				String numbers = "&numbers=" +"91"+login.getUser_phno();
			
				// Send data
				HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
				String data = apiKey + numbers + message + sender;
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
				conn.getOutputStream().write(data.getBytes("UTF-8"));
				final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;
				while ((line = rd.readLine()) != null) {
				
				Messagebox.show("OTP send successfully");
				}
				rd.close();
				
				} catch (Exception e) {
				
				       System.out.println(e);
				}
				}else{
					 Clients.showNotification("Username Not Found", "error", num, "end_center", 2000);
				}
			
			}
 }
	
	
public void verify()
				{
				Intbox num=(Intbox)this.getFellow("otp");
				try{
				int number=num.getValue();
				if(number==otp) {
					Executions.sendRedirect("change_pwd.zul");
				
					}else
					{
					Messagebox.show("wrong otp");
					}
				}catch(NullPointerException e){
					Clients.showNotification("Please Provide The OTP", "error", num, "end_center", 2000);
				}
				
				
	}
	
				
				
	public void Changepwd() 
	{					
					Session se=Sessions.getCurrent(true);
					String username=(String) se.getAttribute("user_Name");
					System.out.print("username"+username);
					
					Textbox n=(Textbox)this.getFellow("new_pwd");
					String nw=n.getValue();
					if(nw.equals("")){
						Clients.showNotification("Please Enter","error",n,"end_center",2000);
						return;
					}
					Textbox c=(Textbox)this.getFellow("cnfrm_pwd");
					String cp=c.getValue();
					if(cp.equals("")){
						Clients.showNotification("Please Enter","error",c,"end_center",2000);
						return;
					}
					if(!nw.equals(cp))
					{
						Tablelayout d=(Tablelayout)this.getFellow("forgotPass");
						Clients.showNotification("Both Must Be Same","error",d,"end_center",2000);
					}
					else
					{
						ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext((ServletContext)getDesktop().getWebApp().getNativeContext());
						db = (UserInterface)ctx.getBean("db");
						int n1=db.ChangePassword(cp,username);
						if(n1>0)
						{
							Tablelayout d=(Tablelayout)this.getFellow("forgotPass");
							Clients.showNotification("Successfully Changed","info",d,"end_center",2000);
							Executions.sendRedirect("../index.zul");
						}
						else
						{
							Tablelayout d=(Tablelayout)this.getFellow("forgotPass");
							Clients.showNotification("please try again","error",d,"end_center",2000);
						}
					}
	}
	
	
}
