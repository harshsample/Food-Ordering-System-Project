/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Client.SecureClient;

import javax.inject.Named;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author root
 */
@Named(value = "helloBean")
//@RequestScoped
public class HelloBean {
    
    SecureClient cl;
    String secureHello;
    String message="";
    String Email;
    
    public String getMessage() {
        return message;
       
    }

    /**
     * Creates a new instance of HelloBean
     */
    public void setMessage(String message) {    
        this.message = message;
    }

    public HelloBean() {
    }

    public String getSecureHello() {
        try{
         cl = new SecureClient();
         
         FacesContext facesContext = FacesContext.getCurrentInstance();

        // Get the HttpSession object
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        // Retrieve a session attribute
         Email = (String) session.getAttribute("Email");
         
            System.out.println("User name :- " + Email);
         message="";
         System.out.println("inside Try Block");
        return cl.sayHello();
        }
        catch(Exception e)
        {
            System.out.println("inside Catch Block");
             message="";
            setMessage("You are Forbidden to access"); 
            System.err.println(message);
        }
        return "hello";
     }

    public void setSecureHello(String secureHello) {
        this.secureHello = secureHello;
    }

    public SecureClient getCl() {
        return cl;
    }

    public void setCl(SecureClient cl) {
        this.cl = cl;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    
    
    
  
}
