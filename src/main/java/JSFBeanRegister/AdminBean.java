/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package JSFBeanRegister;

import Client.RestClientAdmin;
import ejb.AdminBeanLocal;
import entity.FeedbackMstr;
import entity.RegisterMstr;
import entity.RestaurantMstr;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.glassfish.soteria.identitystores.hash.PasswordHashCompare;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author HP
 */
@Named(value = "adminBean")
@SessionScoped
public class AdminBean implements Serializable {

    RestClientAdmin cl = new RestClientAdmin();
    Response rs;
    @EJB AdminBeanLocal abl;
    Collection<RegisterMstr> users;
    GenericType<Collection<RegisterMstr>> gusers;

    String updateMessage;
    
    Collection<RestaurantMstr> restaurants;
    GenericType<Collection<RestaurantMstr>> grestaurants;
    
    Collection<FeedbackMstr> feedbacks;
    GenericType<Collection<FeedbackMstr>> gfeedbacks;
    
    String userName,userPhone,UserGid;
    
    String updatePassword;
    
     Pbkdf2PasswordHashImpl pb;
    PasswordHashCompare pbk;
    
    String status,Email;
    Integer Rid;
    public AdminBean() {
        cl = new RestClientAdmin();
        users = new ArrayList<>();
        gusers = new GenericType<Collection<RegisterMstr>>() {
        };
        
        restaurants = new ArrayList<>();
        grestaurants = new GenericType<Collection<RestaurantMstr>>(){};
        
        feedbacks = new ArrayList<>();
        gfeedbacks = new GenericType<Collection<FeedbackMstr>>(){};
        
        pb = new Pbkdf2PasswordHashImpl();
        pbk = new PasswordHashCompare();
    }

    String gid;

    public Collection<RegisterMstr> getAllUsers() {

        rs = cl.getAllUsers(Response.class);
        users = rs.readEntity(gusers);
        //books = bb.getAllBooks();
        return users;
    }
    
    public Collection<RestaurantMstr> getAllRestaurant()
    {
        rs = cl.getAllRestaurant(Response.class);
        restaurants = rs.readEntity(grestaurants);
        return restaurants;
        
    }
    
    public void searchRestaurant(int catid) {
        rs = cl.getRestaurantById(Response.class,catid+"");
        GenericType<RestaurantMstr> gstate = new GenericType<RestaurantMstr>() {
        };
        RestaurantMstr cc = rs.readEntity(gstate);
        Rid = cc.getRid();
        status = cc.getStatus();       
        approveRestaurant();
    }
    
    public String approveRestaurant()
    {
        RestaurantMstr r = new RestaurantMstr();
        r.setRid(Rid);
        r.setStatus("Approve");
        cl.approveRestaurant(Rid + "",status);
        return "ViewAllRetaurant.jsf?faces-redirect=true";
    }
    
    
    public void searchRestaurantBlock(int catid)
    {
         rs = cl.getRestaurantById(Response.class,catid+"");
        GenericType<RestaurantMstr> gstate = new GenericType<RestaurantMstr>() {
        };
        RestaurantMstr cc = rs.readEntity(gstate);
        Rid = cc.getRid();
        status = cc.getStatus();       
        blockRestaurant();
    }
    
    public String blockRestaurant()
    {
        RestaurantMstr r = new RestaurantMstr();
        r.setRid(Rid);
        r.setStatus("Block");
        cl.blockRestaurant(Rid + "",status);
        return "ViewAllRetaurant.jsf?faces-redirect=true";
    }
    
    
    public String checkEmail()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

         Email = (String) session.getAttribute("Admin");
        if (Email == null) {
            if (!facesContext.getResponseComplete()) {
                try {
                    externalContext.redirect(externalContext.getRequestContextPath() + "/Login.jsf");
                } catch (IOException e) {
                    e.printStackTrace(); // Handle the exception as needed
                }
                return null; // No need to return a navigation outcome here
            }
        }
         return "successOutcome";
    }
    
    public String logout() {
        System.out.println("Yo Yo");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        // Invalidate the session
        HttpSession session = (HttpSession) externalContext.getSession(false);
        if (session != null) {
            session.invalidate();
            return "/Login.jsf?faces-redirect=true";
        }

        return "/Login.jsf?faces-redirect=true";
    }
    
    
    public String searchUserByEmail() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        Email = (String) session.getAttribute("Admin");
        rs = cl.searchUserByEmail(Response.class, Email);
        GenericType<RegisterMstr> gstate = new GenericType<RegisterMstr>() {
        };

        RegisterMstr c = rs.readEntity(gstate);
        Email = c.getEmail();
        userName = c.getName();
        userPhone = c.getPhone().toString();
        UserGid = c.getGid().toString();
         System.out.println("Email :- " + Email);
        System.out.println("Gid :- " + UserGid);
        System.out.println("userName :- " + userName);
        System.out.println("Phone :- " + userPhone);
        return "UpdateProfile.jsf?faces-redirect=true";
    }
    
    
    public String updateUser()
    {
        RegisterMstr r = new RegisterMstr();
        r.setEmail(Email);
        r.setName(userName);
//        r.setGid(gid);
        UserGid = "1";
        
        r.setPhone(Integer.parseInt(userPhone));
        cl.updateUserByEmail(Email, UserGid, userName, userPhone);
        System.out.println("Email :- " + Email);
        System.out.println("Gid :- " + UserGid);
        System.out.println("userName :- " + userName);
        System.out.println("Phone :- " + userPhone);
        return "UpdateProfile.jsf?faces-redirect=true";
    }
    
    
    public Collection<FeedbackMstr> getAllFeedBacks()
    {
        rs = cl.getAllFeedBack(Response.class);
        feedbacks = rs.readEntity(gfeedbacks);
        return feedbacks;
        
    } 

    public Collection<FeedbackMstr> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Collection<FeedbackMstr> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public GenericType<Collection<FeedbackMstr>> getGfeedbacks() {
        return gfeedbacks;
    }

    public void setGfeedbacks(GenericType<Collection<FeedbackMstr>> gfeedbacks) {
        this.gfeedbacks = gfeedbacks;
    }
    
    
    
    public String ForgotPassword()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        String userEmail = (String) session.getAttribute("Admin");
        
        
        this.updateMessage = cl.updatePasswordByEmail(userEmail, updatePassword);
        
        return "ForgotPassword.jsf?faces-redirect=true";
    }
    
    public String ChangePassword()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        String userEmail = (String) session.getAttribute("Admin");
        
        String enc = pb.generate(updatePassword.toCharArray());
        
        this.updateMessage = abl.updatePasswordByEmail(userEmail, enc);
        
        return "ChangePassword.jsf?faces-redirect=true";
    }

    
    public Long fetchCountRestaurant()
    {
        
        Long restaurantCount = abl.CountAllRestaurant();
        return restaurantCount;
    }
    
    public Long fetchCountUsers()
    {
        
        Long usersCount = abl.countAllUsers();
        return usersCount;
    }
    
    public Long fetchFeedback()
    {
        Long countFeedback = abl.countAllFededBack();
        return countFeedback;
        
    }
    
    public Collection<RestaurantMstr> getApproveRestaurant()
    {
        rs = cl.getApprovedRestaurant(Response.class);
        restaurants = rs.readEntity(grestaurants);
        return restaurants;
    }
    
    public Collection<RestaurantMstr> getBlockRestaurant()
    {
        rs = cl.getBlockRestaurant(Response.class);
        restaurants = rs.readEntity(grestaurants);
        return restaurants;
    }
    
    public String getUpdatePassword() {
        return updatePassword;
    }

    public void setUpdatePassword(String updatePassword) {
        this.updatePassword = updatePassword;
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
    
    
    
    

    public Response getRs() {
        return rs;
    }

    public void setRs(Response rs) {
        this.rs = rs;
    }

    public Collection<RegisterMstr> getUsers() {
        return users;
    }

    public void setUsers(Collection<RegisterMstr> users) {
        this.users = users;
    }

    public GenericType<Collection<RegisterMstr>> getGusers() {
        return gusers;
    }

    public void setGusers(GenericType<Collection<RegisterMstr>> gusers) {
        this.gusers = gusers;
    }

    public Collection<RestaurantMstr> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Collection<RestaurantMstr> restaurants) {
        this.restaurants = restaurants;
    }

    public GenericType<Collection<RestaurantMstr>> getGrestaurants() {
        return grestaurants;
    }

    public void setGrestaurants(GenericType<Collection<RestaurantMstr>> grestaurants) {
        this.grestaurants = grestaurants;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public RestClientAdmin getCl() {
        return cl;
    }

    public void setCl(RestClientAdmin cl) {
        this.cl = cl;
    }

    public Integer getRid() {
        return Rid;
    }

    public void setRid(Integer Rid) {
        this.Rid = Rid;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserGid() {
        return UserGid;
    }

    public void setUserGid(String UserGid) {
        this.UserGid = UserGid;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }
    

}
