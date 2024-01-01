/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package JSFBeanRegister;

import Client.RestClient;
import Client.RestClientAdmin;
import ejb.userBeanLocal;
import entity.RegisterMstr;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.glassfish.soteria.identitystores.hash.PasswordHashCompare;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author HP
 */
@Named(value = "jsfBeanRegister")
@RequestScoped
public class jsfBeanRegister implements Serializable {

    RestClient cl = new RestClient();
    Response rs;
    
    String message;
    
    Pbkdf2PasswordHashImpl pb;
    PasswordHashCompare pbk;
    
    @EJB
    userBeanLocal ubl;

    public jsfBeanRegister() {
        pb = new Pbkdf2PasswordHashImpl();
        pbk = new PasswordHashCompare();
    }

    String Email, Name, Password;
    String Gid, phone;
    String Country, State, City;

    
    
    
    public String addRegister() {
        
        
        
        this.message = cl.AddRegister(Email, Gid, Name, Password, phone, State, City, Country);
        System.out.println("Message :- " + this.message);
       
//        cl.AddAddress(Email, State, City, Country);
        System.out.println(Email);
        System.out.println(Gid);
        System.out.println(Name);
//        System.out.println(enc);
        System.out.println(phone);
//        System.out.println(Email);
        System.out.println(Country);
        System.out.println(State);
        System.out.println(City);
         // Returning "Inserted" upon successful operation
        
        if(this.message.equals("Register Success...") )
        {
            return "Login.jsf?faces-redirect=true";
        }
        
        return null;

    }
    
//    public String addAddress()
//    {
//        
//        cl.AddAddress(Email, State, City, Country);
//        return "Login.jsf?faces-redirect=true";
//    }
    
     
//    public String authenticateUser() {
//        String role = ubl.Login(Email, Password);
//        if (role != null) {
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            facesContext.getExternalContext().getSessionMap().put("Email", Email);
//            
//            
//            // Redirect based on the role, you can customize the logic here
//            if (role.equals("Admin")) {
//                return "/Admin/adminPage.jsf?faces-redirect=true";
//            } else if (role.equals("Manager")) {
//                return "/Manager/managerPage?faces-redirect=true";
//            } else if (role.equals("User")) {
//                return "/user/userPage?faces-redirect=true";
//            }
//        }
//        // Handle authentication failure
//        return "loginPage?faces-redirect=true";
//    }
    
    

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public RestClient getCl() {
        return cl;
    }

    public void setCl(RestClient cl) {
        this.cl = cl;
    }

    public Response getRs() {
        return rs;
    }

    public void setRs(Response rs) {
        this.rs = rs;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getGid() {
        return Gid;
    }

    public void setGid(String Gid) {
        this.Gid = Gid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Pbkdf2PasswordHashImpl getPb() {
        return pb;
    }

    public void setPb(Pbkdf2PasswordHashImpl pb) {
        this.pb = pb;
    }

    public PasswordHashCompare getPbk() {
        return pbk;
    }

    public void setPbk(PasswordHashCompare pbk) {
        this.pbk = pbk;
    }

    public userBeanLocal getUbl() {
        return ubl;
    }

    public void setUbl(userBeanLocal ubl) {
        this.ubl = ubl;
    }

    
}
