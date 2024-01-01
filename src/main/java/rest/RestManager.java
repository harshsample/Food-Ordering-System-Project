/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import ejb.ManagerBeanLocal;
import entity.MenuMstr;
import entity.OrderDetailsMstr;
import entity.OrderMstr;
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
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("RestManager")
@RequestScoped
public class RestManager {

    @EJB
    ManagerBeanLocal mbl;

    @Context
    private UriInfo context;
    
    Pbkdf2PasswordHashImpl pb;
    PasswordHashCompare pbk;

    /**
     * Creates a new instance of RestManager
     */
    public RestManager() {
    }

    @POST
    @Path("/AddRestaurant/{Email}/{Name}/{Address}/{Description}/{file}/{status}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void AddRestaurant(
            @PathParam("Email") String email,
            @PathParam("Name") String name,
            @PathParam("Address") String address,
            @PathParam("Description") String description,
            @PathParam("file") String file,
            @PathParam("status") String status) {
        mbl.AddRestaurant(email, name, address, description, file, status);
    }
    
    @POST
    @Path("/AddMenu/{Rid}/{Name}/{Address}/{Description}/{file}/{Price}/{Qty}/{status}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void AddMenu(
            @PathParam("Rid") int Rid,
            @PathParam("Name") String name,
            @PathParam("Description") String description,
            @PathParam("file") String file,
            @PathParam("Price") int Price,
            @PathParam("Qty") int Qty,
            @PathParam("status") String status) {
        mbl.AddMenu(Rid, name, description, file, Price, Qty, status);
    }
    
    
    @POST
    @Path("/updateRestaurant/{Rid}/{Email}/{Name}/{Address}/{Description}/{file}/{status}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updaeRestaurant(
            @PathParam("Rid") int Rid,
            @PathParam("Email") String Email,
            @PathParam("Name") String name,
            @PathParam("Address") String address,
            @PathParam("Description") String description,
            @PathParam("file") String file,
            @PathParam("status") String status) {
        mbl.updateRestaurantById(Rid,Email, name, address, description, file, status);
    }
    
    @POST
    @Path("/updateMenu/{Mid}/{Rid}/{Name}/{Description}/{file}/{Price}/{Qty}/{status}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updaeMenu(
            @PathParam("Mid") int Mid,
            @PathParam("Rid") int Rid,
            @PathParam("Name") String name,
            @PathParam("Description") String description,
            @PathParam("file") String file,
            @PathParam("Price") int Price,
            @PathParam("Qty") int Qty,
            @PathParam("status") String status) {
        mbl.updateMenuByMid(Mid, Rid, name, description, file, Price, Qty, status);
    }

    @GET
    @Path("getRestaurantByEmail/{Email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<RestaurantMstr> getRestaurantsByEmail(@PathParam("Email") String managerEmail) {
        return mbl.getRestaurantsByEmail(managerEmail);
    }

    @GET
    @Path("getRestaurantById/{Rid}")
    @Produces(MediaType.APPLICATION_JSON)
    public RestaurantMstr getRestaurantById(@PathParam("Rid") int Rid) {
        return mbl.getRestaurantById(Rid);
    }

    @DELETE
    @Path("deleteRestaurant/{Rid}")
    public void deleteRestaurantById(@PathParam("Rid") int Rid) {
        mbl.deleteRestaurantById(Rid);
    }

    @GET
    @Path("getMenuByRestaurant/{Rid}")
    @Produces("application/json")
    public Collection<MenuMstr> getAddressesofCustomer(@PathParam("Rid") int Rid) {
        return mbl.getMenuByRestaurant(Rid);
    }

    @GET
    @Path("getMenuByMid/{Mid}")
    @Produces(MediaType.APPLICATION_JSON)
    public MenuMstr getMenuByMid(@PathParam("Mid") int Mid) {
        return mbl.getMenuByMid(Mid);
    }

    @DELETE
    @Path("removeMenu/{Mid}")
    public void removeMenuById(@PathParam("Mid") int Mid) {
        mbl.removeMenuById(Mid);
    }
    
    @GET
    @Path("getMenuByRid/{Rid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<MenuMstr> getMenuByRestaurant(@PathParam("Rid")int Rid) {
        return mbl.getMenuByRestaurant(Rid);
    }
    
    @GET
    @Path("getUserByEmail/{Email}")
    @Produces(MediaType.APPLICATION_JSON)
    public RegisterMstr searchUserByEmail(@PathParam("Email") String Email) {
        return mbl.searchUserByEmail(Email);
    }
    
    @POST
    @Path("updateUser/{Email}/{Gid}/{Name}/{phone}")
    public void updateUserByEmail(@PathParam("Email") String Email, @PathParam("Gid") int Gid, @PathParam("Name") String Name, @PathParam("phone") int Phone) {
       mbl.updateUserByEmail(Email, Gid, Name, Phone);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAllOrders")
    public Collection<OrderDetailsMstr> getAllOrderDetails() {
        return mbl.getAllOrderDetails();
    }
    
    @POST
    @Path("onProcessStatus/{oid}/{status}")
     public void updateStatusOnProccess(@PathParam("oid")int oid,@PathParam("status") String status) {
        mbl.updateStatusOnProccess(oid, status);
    }
     
    @POST
    @Path("updateStatusDelivered/{oid}/{status}")
    public void updateStatusDelivered(@PathParam("oid")int oid,@PathParam("status") String status) {
        mbl.updateStatusDelivered(oid, status);
    }
    
    @POST
    @Path("updateStatusPending/{oid}/{status}")
    public void updateStatusOnPending(@PathParam("oid")int oid,@PathParam("status") String status) {
        mbl.updateStatusOnPending(oid, status);
    }
    
    @POST
    @Path("updatePasswordByEmail/{Email}/{Password}")
    public String updatePasswordByEmail(@PathParam("Email") String Email,@PathParam("Password") String Password) {
        pb = new Pbkdf2PasswordHashImpl();
        pbk = new PasswordHashCompare();
        String enc = pb.generate(Password.toCharArray());
        return mbl.updatePasswordByEmail(Email, enc);
    }
    
     @POST
    @Path("NotAvalilavleStatus/{Mid}/{status}")
     public void updateMenuNotAvailable(@PathParam("Mid")int Mid,@PathParam("status") String status) {
        mbl.updateMenuNotAvailable(Mid, status);
    }
     
    @POST
    @Path("AvalilableStatus/{Mid}/{status}")
    public void updateMenuAvailable(@PathParam("Mid")int Mid,@PathParam("status") String status) {
        mbl.updateMenuAvailable(Mid, status);
    }
}
