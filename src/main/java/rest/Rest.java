/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import ejb.userBeanLocal;
import entity.AddressMstr;
import entity.RegisterMstr;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.soteria.identitystores.hash.PasswordHashCompare;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 * REST Web Service
 *
 * @author HP
 */
@Path("RestApp")
@RequestScoped
public class Rest {

    @EJB
    userBeanLocal ubl;
    
    
    Pbkdf2PasswordHashImpl pb;
    PasswordHashCompare pbk;
    
    @Context
    private UriInfo context;

    
    
    public Rest() {
    }

    @POST
    @Path("addRegister/{Email}/{Gid}/{Name}/{Password}/{phone}/{State}/{City}/{Country}")
    public String AddRegister(@PathParam("Email") String Email, @PathParam("Gid") int Gid, @PathParam("Name") String Name, @PathParam("Password") String Password, @PathParam("phone") int phone,@PathParam("State")String State,@PathParam("City")String City,@PathParam("Country")String Country) {
        
        pb = new Pbkdf2PasswordHashImpl();
        pbk = new PasswordHashCompare();
        
        String enc = pb.generate(Password.toCharArray());
        return ubl.AddRegister(Email, Gid, Name, enc, phone, State, City, Country);
    }

    @POST
    @Path("AddAddress/{Email}/{State}/{City}/{Country}")
    public void AddAddress(@PathParam("Email") String Email, @PathParam("State") String State, @PathParam("City") String City, @PathParam("Country") String Country) {
        ubl.AddAddress(Email, State, City, Country);

    }

    @GET
    @Path("SearchEmail/{Email}")
    @Produces(MediaType.APPLICATION_JSON)
    public RegisterMstr SerachEmail(@PathParam("Email") String Email) {
        return ubl.searchUserByEmail(Email);
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.TEXT_XML)
//    public Response login(UserCredentials userCredentials) {
//        String role = ubl.Login(userCredentials.getEmail(), userCredentials.getPassword());
//        if (role != null) {
//            return Response.ok(role).build();
//        } else {
//            return Response.status(Response.Status.UNAUTHORIZED).build();
//        }
//    }
}
