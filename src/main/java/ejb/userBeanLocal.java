/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entity.AddressMstr;
import entity.CartMstr;
import entity.FeedbackMstr;
import entity.MenuMstr;
import entity.OrderDetailsMstr;
import entity.OrderMstr;
import entity.RegisterMstr;
import entity.RestaurantMstr;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author HP
 */
@Local
public interface userBeanLocal {
    
    //======================== Register & Login ===============================
    public String AddRegister(String Email,int Gid,String Name,String Password,int phone,String State,String City,String Country);
    public void AddAddress(String Email,String State,String City,String Country);
//    public String Login(String Email,String Password);
    public RegisterMstr searchUserByEmail(String Email);
    public void updateUserByEmail(String Email,int Gid,String Name,int Phone);
    public String updatePasswordByEmail(String Email,String Password);
    
    //============================ Home page Restaurant Display============================
    public Collection<RestaurantMstr> getApprovedRestaurant();
    public Collection<RestaurantMstr> getFourRestaurant();
    public Collection<RestaurantMstr> getThreeRestaurant();
    
    //=============================== Get Approve Foods ===========================
    
    public Collection<MenuMstr> getMenuByRestaurantAndStatus(int Rid);
    
    //=========================== Add To Cart ================================
    
    public void AddCart(String Email,int Mid,int Qty,int Price,Date Added_at);
    public Collection<CartMstr> getCartByEmail(String userEmail);
    public void deleteCartById(int Cid);
    public Long countCartByEmail(String userEmail);
    public Long sumCartPriceByEmail(String userEmail);
    
    
    //========================== Order ======================================
    
    
     public void placeOrder(String email);
     public Collection<OrderDetailsMstr> getOrderDetailsForUser(String userEmail);
     public Collection<OrderDetailsMstr> getOrderDetailsOnProcess(String userEmail);
     public Collection<OrderDetailsMstr> getOrderDetailsPending(String userEmail);
     //============================= Payment =================================
     
     public void processPaymentForCurrentUser(String Email,Date dop, String status,String pType);
     
    //======================= FeedBack ================================================
    public void addFeedBack(String Email,String Description,Date date);
    
}
