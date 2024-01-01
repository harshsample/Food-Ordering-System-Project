/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package JSFBeanRegister;

import Client.RestClientManager;
import Client.RestClientUser;
import ejb.userBeanLocal;
import entity.CartMstr;
import entity.MenuMstr;
import entity.OrderDetailsMstr;
import entity.OrderMstr;
import entity.RegisterMstr;
import entity.RestaurantMstr;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.glassfish.soteria.identitystores.hash.PasswordHashCompare;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author HP
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    @Inject
    private HttpServletRequest request;
    
    
    
    RestClientUser cl = new RestClientUser();
    Response rs;
    Collection<RestaurantMstr> restaurant;
    GenericType<Collection<RestaurantMstr>> grestaurant;
    
    
    Collection<CartMstr> carts;
    GenericType<Collection<CartMstr>> gcarts;
    
    
    RestClientManager clm = new RestClientManager();
    Collection<MenuMstr> menus;
    GenericType<Collection<MenuMstr>> gmenus;
    
    
    String updateMessage;
    
    Collection<OrderDetailsMstr> orderdetails;
    
    private Collection<OrderMstr> orders;
    
    Integer Rid,Mid,Qty,Price,Cid;
    int TotalAmt;
    String RestName,RestAddress,RestDesc,RestStatus,Email,RestFile,UserQty;
    private UIInput qtyInput;
    
    
    String userName,userPhone,Gid;
    
    private Map<Integer, String> itemQuantities = new HashMap<>();
    
    @EJB userBeanLocal ubl;
    
    private Long totalPrice;
    
    String DOP,PStatus,PType;
    
    String FeedDescription;
    String updatePassword;
    
    Pbkdf2PasswordHashImpl pb;
    PasswordHashCompare pbk;
    
    public UserBean() {
        cl = new RestClientUser();
        restaurant = new ArrayList<>();
        grestaurant = new GenericType<Collection<RestaurantMstr>>(){};
        
        menus = new ArrayList<>();
        gmenus = new GenericType<Collection<MenuMstr>>(){};
        
        carts = new ArrayList<>();
        gcarts = new GenericType<Collection<CartMstr>>(){};
        
        orderdetails = new ArrayList<>();
        
        pb = new Pbkdf2PasswordHashImpl();
        pbk = new PasswordHashCompare();
      
    }
    
    
    
    public String checkEmail()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

         Email = (String) session.getAttribute("User");
        if (Email == null) {
            // Redirect to the login page
            try {
                externalContext.redirect(externalContext.getRequestContextPath() + "/Login.jsf");
            } catch (Exception e) {
                e.printStackTrace(); // Handle the exception as needed
            }
            return "/Login.jsf?faces-redirect=true";
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
    
    
    public Collection<RestaurantMstr> getApproveRestaurant()
    {
        rs = cl.getApprovedRestaurant(Response.class);
        restaurant = rs.readEntity(grestaurant);
        return restaurant;
    }
    
    public Collection<RestaurantMstr> getFourRestaurant()
    {
        rs = cl.getFourRestaurant(Response.class);
        restaurant = rs.readEntity(grestaurant);
        return restaurant;
    }
    
    public Collection<RestaurantMstr> getThreeRestaurant()
    {
        rs = cl.getThreeRestaurant(Response.class);
        restaurant = rs.readEntity(grestaurant);
        return restaurant;
    }
    
    public Long fetchCartCount()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        Email = (String) session.getAttribute("User");
        Long cartCount = ubl.countCartByEmail(Email);

        return cartCount;
    }
    
    
    public Double GetTotalPrice()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        Email = (String) session.getAttribute("User");
        totalPrice = ubl.sumCartPriceByEmail(Email);

        return totalPrice.doubleValue();
    }
    
    public String searchRestaurantById(int RestId) {

     
           FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

//        if (session != null) {
            Email = (String) session.getAttribute("User");
            rs = clm.getRestaurantById(Response.class, RestId + "");
            GenericType<RestaurantMstr> gstate = new GenericType<RestaurantMstr>() {
            };
            RestaurantMstr cc = rs.readEntity(gstate);
            Rid = cc.getRid();
            RestName = cc.getName();
            RestAddress = cc.getAddress();
            RestFile = cc. getImg();
            RestDesc = cc.getDescription();
            RestStatus = cc.getStatus();
            System.out.println("id :- " + Rid);
            System.out.println("Name :- " + RestName);
            System.out.println("add :- " + RestAddress);
            System.out.println("Desc  :- " + RestDesc);
            System.out.println("Image name :- " + RestFile);
            System.out.println("upRestStatus :- " + RestStatus);
            
//        }
       
            
        return "ViewFood.jsf?faces-redirect=true";
    }

    public Collection<MenuMstr> getMenu() {
        rs = cl.getMenuByRestaurantAndStatus(Response.class, Rid+"");
        menus = rs.readEntity(gmenus);
        return menus;
    }
    
    
    public String AddToCart(int mid)
    {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpSession session = (HttpSession) externalContext.getSession(false);

            if (session != null) {
//            String custQty1 = request.getParameter("custQty");
                Email = (String) session.getAttribute("User");
                rs = clm.getMenuByMid(Response.class, mid + "");
                GenericType<MenuMstr> gstate = new GenericType<MenuMstr>() {
                };
                MenuMstr cc = rs.readEntity(gstate);
                Mid = cc.getMid();
                Price = cc.getPrice();
                String quantity = itemQuantities.get(Mid);

                // Handle the case where the quantity is null (not set yet)
                if (quantity == null) {
                    quantity = "0"; // or any default value
                }
                // Use the value directly from custQty property

                System.out.println("Email: " + Email);
                System.out.println("Mid: " + Mid);
                System.out.println("Quantity : " + quantity);
                System.out.println("Price: " + Price);

                itemQuantities.put(mid, quantity);

                TotalAmt = Integer.parseInt(quantity) * Price;
                System.out.println("Total :- " + TotalAmt);

                Date currentDateTime = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = dateFormat.format(currentDateTime);
                
                cl.AddCart(Email, Mid + "", quantity, TotalAmt+"", formattedDate + "");
                
                return "ViewFood.jsf?faces-redirect=true";

            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return "errorPage.jsf?faces-redirect=true";
    }
    
    
    public Collection<CartMstr> searchCartByEmail() {
//        String fname;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        if (session != null) {
            Email = (String) session.getAttribute("User");
            rs = cl.getCartByEmail(Response.class, Email);
//        GenericType<RestaurantMstr> gstate = new GenericType<RestaurantMstr>() {
//        };
            carts = rs.readEntity(gcarts);

        }
        return carts;
    }
    
    public String deleteCartById(int Cid)
    {
        System.out.println("Cart id :- " + Cid);
        cl.deleteCartById(Cid+"");
        return "ViewCart.jsf?faces-redirect=true";
    }
    
    
    public String searchUserByEmail() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        Email = (String) session.getAttribute("User");
        rs = cl.searchUserByEmail(Response.class, Email);
        GenericType<RegisterMstr> gstate = new GenericType<RegisterMstr>() {
        };

        RegisterMstr c = rs.readEntity(gstate);
        Email = c.getEmail();
        userName = c.getName();
        userPhone = c.getPhone().toString();
        Gid = c.getGid().toString();
        return "UpdateProfile.jsf?faces-redirect=true";
    }
    
    public String updateUser()
    {
        RegisterMstr r = new RegisterMstr();
        r.setEmail(Email);
        r.setName(userName);
//        r.setGid(gid);
        Gid = "3";
        
        r.setPhone(Integer.parseInt(userPhone));
        cl.updateUserByEmail(Email, Gid, userName, userPhone);
        System.out.println("Email :- " + Email);
        System.out.println("Gid :- " + Gid);
        System.out.println("userName :- " + userName);
        System.out.println("Phone :- " + userPhone);
        return "UpdateProfile.jsf?faces-redirect=true";
    }
    
   
    
    public String placeOrder() {
        // Retrieve user's email from the session
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        String userEmail = (String) session.getAttribute("User");

        // Call EJB method directly with the user's email
        ubl.placeOrder(userEmail);

        return "Payment.jsf?faces-redirect=true";
    }
    
    
    public Collection<OrderDetailsMstr> loadOrderDetails() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        Email = (String) session.getAttribute("User");
        orderdetails = ubl.getOrderDetailsForUser(Email);
        return orderdetails;
    }
    
    public Collection<OrderDetailsMstr> loadOrderDetailsOnProcess() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        Email = (String) session.getAttribute("User");
        orderdetails = ubl.getOrderDetailsOnProcess(Email);
        return orderdetails;
    }
    
    public Collection<OrderDetailsMstr> loadOrderDetailsPending() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        Email = (String) session.getAttribute("User");
        orderdetails = ubl.getOrderDetailsPending(Email);
        return orderdetails;
    }
    
    public String ProcessPayment()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        String userEmail = (String) session.getAttribute("User");
        
        Date currentDateTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDateTime);
        
        PStatus = "UnPaid";
//        PType = "Offline";
        System.out.println("Email = " + userEmail);
        System.out.println("Date :- " + formattedDate);
        System.out.println("Status = " + PStatus);
        System.out.println("Type = " + PType);
        
        cl.processPaymentForCurrentUser(userEmail, formattedDate+"", PStatus, PType);
        
        return "successful.jsf?faces-redirect=true";
    }
    
    public String AddFeedback()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        String userEmail = (String) session.getAttribute("User");
        
        Date currentDateTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDateTime);
        
        cl.addFeedback(userEmail, FeedDescription, formattedDate+"");
        
        return "FeedBack.jsf?faces-redirect=true";
    }
    
    
    
    
    public String ForgotPassword()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        String userEmail = (String) session.getAttribute("User");
        
//        String enc = pb.generate(updatePassword.toCharArray());
        
        RegisterMstr r = new RegisterMstr();
        r.setEmail(userEmail);
//        r.setPassword(enc);
        
        System.out.println("user Email :-" + userEmail );
//        System.out.println("Updated Password :- " + updatePassword);
        
//        ubl.updatePasswordByEmail(userEmail, enc);

        this.updateMessage = cl.updatePasswordByEmail(userEmail, updatePassword);
        
        return "ForgotPassword.jsf?faces-redirect=true";
    }
    
    public String ChangePassword()
    {
       FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        String userEmail = (String) session.getAttribute("User");
        
//        String enc = pb.generate(updatePassword.toCharArray());
        
        RegisterMstr r = new RegisterMstr();
        r.setEmail(userEmail);
//        r.setPassword(enc);
        
        System.out.println("user Email :-" + userEmail );
//        System.out.println("Updated Password :- " + updatePassword);
        
//        ubl.updatePasswordByEmail(userEmail, enc);

        this.updateMessage = cl.updatePasswordByEmail(userEmail, updatePassword);
        
        return "ChangePassword.jsf?faces-redirect=true";
    }
    
    public Collection<OrderDetailsMstr> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(Collection<OrderDetailsMstr> orderdetails) {
        this.orderdetails = orderdetails;
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

    

    public String getRestName() {
        return RestName;
    }

    public void setRestName(String RestName) {
        this.RestName = RestName;
    }

    public String getRestAddress() {
        return RestAddress;
    }

    public void setRestAddress(String RestAddress) {
        this.RestAddress = RestAddress;
    }

    public String getRestDesc() {
        return RestDesc;
    }

    public void setRestDesc(String RestDesc) {
        this.RestDesc = RestDesc;
    }

    public String getRestStatus() {
        return RestStatus;
    }

    public void setRestStatus(String RestStatus) {
        this.RestStatus = RestStatus;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getRestFile() {
        return RestFile;
    }

    public void setRestFile(String RestFile) {
        this.RestFile = RestFile;
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

    

   
    public UIInput getQtyInput() {
        return qtyInput;
    }

    public void setQtyInput(UIInput qtyInput) {
        this.qtyInput = qtyInput;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getUserQty() {
        return UserQty;
    }

    public void setUserQty(String UserQty) {
        this.UserQty = UserQty;
    }

    public int getTotalAmt() {
        return TotalAmt;
    }

    public void setTotalAmt(int TotalAmt) {
        this.TotalAmt = TotalAmt;
    }

    public Collection<CartMstr> getCarts() {
        return carts;
    }

    public void setCarts(Collection<CartMstr> carts) {
        this.carts = carts;
    }

    public GenericType<Collection<CartMstr>> getGcarts() {
        return gcarts;
    }

    public void setGcarts(GenericType<Collection<CartMstr>> gcarts) {
        this.gcarts = gcarts;
    }

    public Integer getCid() {
        return Cid;
    }

    public void setCid(Integer Cid) {
        this.Cid = Cid;
    }  

    public userBeanLocal getUbl() {
        return ubl;
    }

    public void setUbl(userBeanLocal ubl) {
        this.ubl = ubl;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getGid() {
        return Gid;
    }

    public void setGid(String Gid) {
        this.Gid = Gid;
    }

    public Collection<OrderMstr> getOrders() {
        return orders;
    }

    public void setOrders(Collection<OrderMstr> orders) {
        this.orders = orders;
    }

    public String getDOP() {
        return DOP;
    }

    public void setDOP(String DOP) {
        this.DOP = DOP;
    }

    public String getPStatus() {
        return PStatus;
    }

    public void setPStatus(String PStatus) {
        this.PStatus = PStatus;
    }

    public String getPType() {
        return PType;
    }

    public void setPType(String PType) {
        this.PType = PType;
    }

    public String getFeedDescription() {
        return FeedDescription;
    }

    public void setFeedDescription(String FeedDescription) {
        this.FeedDescription = FeedDescription;
    }

    public String getUpdatePassword() {
        return updatePassword;
    }

    public void setUpdatePassword(String updatePassword) {
        this.updatePassword = updatePassword;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }
    

}
