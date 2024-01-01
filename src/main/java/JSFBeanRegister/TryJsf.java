/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package JSFBeanRegister;

import Client.RestClientManager;
import Client.RestClientUser;
import entity.MenuMstr;
import entity.RestaurantMstr;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author HP
 */
@Named(value = "tryJsf")
@RequestScoped
public class TryJsf {

    RestClientUser cl = new RestClientUser();
    Response rs;
    Collection<RestaurantMstr> restaurant;
    GenericType<Collection<RestaurantMstr>> grestaurant;
    RestClientManager clm = new RestClientManager();
    Collection<MenuMstr> menus;
    GenericType<Collection<MenuMstr>> gmenus;
    Integer Rid,Mid,Qty,Price;
    String UserQty,Email;
    
    
    private Map<Integer, String> itemQuantities = new HashMap<>();
    
    public TryJsf() {
        cl = new RestClientUser();
        restaurant = new ArrayList<>();
        grestaurant = new GenericType<Collection<RestaurantMstr>>(){};
        
        menus = new ArrayList<>();
        gmenus = new GenericType<Collection<MenuMstr>>(){};
    }
    
    
    public String AddToCart(int mid)
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        if (session != null) {
            Email = (String) session.getAttribute("Email");

            // Retrieve the quantity for the current menu item
            String quantity = itemQuantities.get(mid);

            // Handle the case where the quantity is null (not set yet)
            if (quantity == null) {
                quantity = "0"; // or any default value
            }

            // Use the value directly from quantity property
            System.out.println("Email: " + Email);
            System.out.println("Mid: " + mid);
            System.out.println("Quantity: " + quantity);
            // ... (other code)

            // Update the quantity in the map
            itemQuantities.put(mid, quantity);
        }

        return "ViewCart.jsf?faces-redirect=true";
    }
    
    public Map<Integer, String> getItemQuantities() {
        return itemQuantities;
    }

    public void setItemQuantities(Map<Integer, String> itemQuantities) {
        this.itemQuantities = itemQuantities;
    }

    public RestClientUser getCl() {
        return cl;
    }

    public void setCl(RestClientUser cl) {
        this.cl = cl;
    }

    public Response getRs() {
        return rs;
    }

    public void setRs(Response rs) {
        this.rs = rs;
    }

    public Collection<RestaurantMstr> getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Collection<RestaurantMstr> restaurant) {
        this.restaurant = restaurant;
    }

    public GenericType<Collection<RestaurantMstr>> getGrestaurant() {
        return grestaurant;
    }

    public void setGrestaurant(GenericType<Collection<RestaurantMstr>> grestaurant) {
        this.grestaurant = grestaurant;
    }

    public RestClientManager getClm() {
        return clm;
    }

    public void setClm(RestClientManager clm) {
        this.clm = clm;
    }

    public Collection<MenuMstr> getMenus() {
        return menus;
    }

    public void setMenus(Collection<MenuMstr> menus) {
        this.menus = menus;
    }

    public GenericType<Collection<MenuMstr>> getGmenus() {
        return gmenus;
    }

    public void setGmenus(GenericType<Collection<MenuMstr>> gmenus) {
        this.gmenus = gmenus;
    }

    public Integer getRid() {
        return Rid;
    }

    public void setRid(Integer Rid) {
        this.Rid = Rid;
    }

    public Integer getMid() {
        return Mid;
    }

    public void setMid(Integer Mid) {
        this.Mid = Mid;
    }

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer Qty) {
        this.Qty = Qty;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer Price) {
        this.Price = Price;
    }

    public String getUserQty() {
        return UserQty;
    }

    public void setUserQty(String UserQty) {
        this.UserQty = UserQty;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    
    
    
    
    
    
}
