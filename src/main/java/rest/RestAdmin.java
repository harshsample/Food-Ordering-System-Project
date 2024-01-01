/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import ejb.AdminBeanLocal;
import entity.FeedbackMstr;
import entity.RegisterMstr;
import entity.RestaurantMstr;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.glassfish.soteria.identitystores.hash.PasswordHashCompare;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 * REST Web Service
 *
 * @author HP
 */
@Path("RestAdmin")
@RequestScoped
public class RestAdmin {
    
    @EJB AdminBeanLocal abl;

    @Context
    private UriInfo context;

    
    Pbkdf2PasswordHashImpl pb;
    PasswordHashCompare pbk;
    
    /**
     * Creates a new instance of RestAdmin
     */
    public RestAdmin() {
    }
    
    @GET
    @Path("getApproveRestaurantAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<RestaurantMstr> getApprovedRestaurant() {
        return abl.getApprovedRestaurant();
    }
    
    @GET
    @Path("getBlockRestaurantAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<RestaurantMstr> getBlockRestaurant() {
        return abl.getBlockRestaurant();
    }

    @GET
    @Path("getAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<RegisterMstr> getAllUsers()
    {
        return abl.getAllUsers();
    }
    
    @GET
    @Path("getAllRestaurant")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<RestaurantMstr> getAllRestaurant()
    {
        return abl.getAllRestaurant();
    }
    
    @POST
    @Path("approveRestaurant/{Rid}/{status}")
     public void approveRestaurant(@PathParam("Rid")int Rid,@PathParam("status") String status) {
        abl.approveRestaurant(Rid,status);
    }
     
     @POST
    @Path("blockRestaurant/{Rid}/{status}")
     public void blockRestaurant(@PathParam("Rid")int Rid,@PathParam("status")String status) {
        abl.blockRestaurant(Rid,status);
    }
     
     @GET
    @Path("getRestaurantById/{Rid}")
    @Produces(MediaType.APPLICATION_JSON)
     public RestaurantMstr getRestaurantById(@PathParam("Rid")int Rid) {
        return abl.getRestaurantById(Rid);
    }
     
     @GET
    @Path("getUserByEmail/{Email}")
    @Produces(MediaType.APPLICATION_JSON)
    public RegisterMstr searchUserByEmail(@PathParam("Email") String Email) {
        return abl.searchUserByEmail(Email);
    }
    
    @POST
    @Path("updateUser/{Email}/{Gid}/{Name}/{phone}")
    public void updateUserByEmail(@PathParam("Email") String Email, @PathParam("Gid") int Gid, @PathParam("Name") String Name, @PathParam("phone") int Phone) {
       abl.updateUserByEmail(Email, Gid, Name, Phone);
    }
    
    @GET
    @Path("getAllFeedBack")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<FeedbackMstr> getAllFeedBack()
    {
        return abl.viewAllFeedBack();
    }
    
    @POST
    @Path("updatePasswordByEmail/{Email}/{Password}")
    public String updatePasswordByEmail(@PathParam("Email") String Email,@PathParam("Password") String Password) {
        pb = new Pbkdf2PasswordHashImpl();
        pbk = new PasswordHashCompare();
        String enc = pb.generate(Password.toCharArray());
        return abl.updatePasswordByEmail(Email, enc);
    }
    
    @GET
    @Path("/CountRestaurant")
    @Produces(MediaType.APPLICATION_JSON)
    public void countAllRestaurant() {
        abl.CountAllRestaurant();
    }
    
    @GET
    @Path("/countUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public void countAllUsers() {
        abl.countAllUsers();
    }
    
    @GET
    @Path("/countFeedback")
    @Produces(MediaType.APPLICATION_JSON)
    public void countAllFededBack()
    {
        abl.countAllFededBack();
    }
    
}
