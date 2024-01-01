/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import ejb.userBeanLocal;
import entity.CartMstr;
import entity.MenuMstr;
import entity.OrderMstr;
import entity.PaymentMstr;
import entity.RegisterMstr;
import entity.RestaurantMstr;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.glassfish.soteria.identitystores.hash.PasswordHashCompare;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 * REST Web Service
 *
 * @author HP
 */
@Path("RestUser")
@RequestScoped
public class RestUser {

    @EJB
    userBeanLocal ubl;
    
    Pbkdf2PasswordHashImpl pb;
    PasswordHashCompare pbk;

    @Context
    private UriInfo context;

    public RestUser() {
    }

    @GET
    @Path("getApproveRestaurant")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<RestaurantMstr> getApprovedRestaurant() {
        return ubl.getApprovedRestaurant();
    }
    
    @GET
    @Path("getFourApprove")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<RestaurantMstr> getFourRestaurant() {
        return ubl.getFourRestaurant();
    }
    
    @GET
    @Path("getThreeApprove")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<RestaurantMstr> getThreeRestaurant() {
        return ubl.getThreeRestaurant();
    }

    @GET
    @Path("getUserByEmail/{Email}")
    @Produces(MediaType.APPLICATION_JSON)
    public RegisterMstr searchUserByEmail(@PathParam("Email") String Email) {
        return ubl.searchUserByEmail(Email);
    }

//    @GET
//    @Path("getCartByEmail/{Email}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Collection<CartMstr> getCartByEmail(String userEmail) {
//        return ubl.getCartByEmail(userEmail);
//    }

    @DELETE
    @Path("deleteCart/{Cid}")
    public void deleteCartById(@PathParam("Cid")int Cid) {
        ubl.deleteCartById(Cid);
    }
    
    @GET
    @Path("/count/{Email}")
    @Produces(MediaType.APPLICATION_JSON)
    public void countCartByEmail(@PathParam("Email") String userEmail) {
        ubl.countCartByEmail(userEmail);
    }
    
    
    @POST 
   @Path("addFeedback/{Email}/{Description}/{Date}")
    public void addFeedback(@PathParam("Email") String Email, @PathParam("Description") String Description,@PathParam("Date") String date) {
        try {
            // Convert the string representation of the date to a Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date addedAt = dateFormat.parse(date);

            // Call your EJB method with the Date object
            ubl.addFeedBack(Email, Description, addedAt);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parsing exception
        }
    }
    
    @POST
    @Path("addToCart/{Email}/{Mid}/{Qty}/{Price}/{Added_at}")
    public void AddCart(@PathParam("Email")String Email,@PathParam("Mid") int Mid,@PathParam("Qty") int Qty,@PathParam("Price") int Price,@PathParam("Added_at") String Added_at) {
        try {
            // Convert the string representation of the date to a Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date addedAt = dateFormat.parse(Added_at);

            // Call your EJB method with the Date object
            ubl.AddCart(Email, Mid, Qty, Price, addedAt);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parsing exception
        }
    }
    
    @GET
    @Path("getCartByEmail/{Email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CartMstr> getCartByEmail(@PathParam("Email") String userEmail) {
        return ubl.getCartByEmail(userEmail);
    }
    
    @POST
    @Path("updateUser/{Email}/{Gid}/{Name}/{phone}")
    public void updateUserByEmail(@PathParam("Email") String Email, @PathParam("Gid") int Gid, @PathParam("Name") String Name, @PathParam("phone") int Phone) {
       ubl.updateUserByEmail(Email, Gid, Name, Phone);
    }
    
    @POST
    @Path("ProcessPayment/{Email}/{dop}/{status}/{pType}")
    public void processPaymentForCurrentUser(@PathParam("Email") String Email,@PathParam("dop") String dop,@PathParam("status") String status,@PathParam("pType") String pType) {
        try {
            // Convert the string representation of the date to a Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date addedAt = dateFormat.parse(dop);

            // Call your EJB method with the Date object
            ubl.processPaymentForCurrentUser(Email, addedAt, status, pType);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle the parsing exception
        }
    }
    
   
    @POST
    @Path("updatePasswordByEmail/{Email}/{Password}")
    public String updatePasswordByEmail(@PathParam("Email") String Email,@PathParam("Password") String Password) {
        pb = new Pbkdf2PasswordHashImpl();
        pbk = new PasswordHashCompare();
        String enc = pb.generate(Password.toCharArray());
        return ubl.updatePasswordByEmail(Email, enc);
    }
    
    
    @GET
    @Path("getMenuByRidAvailable/{Rid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<MenuMstr> getMenuByRestaurantAndStatus(@PathParam("Rid")int Rid) {
        return ubl.getMenuByRestaurantAndStatus(Rid);
    }
}
