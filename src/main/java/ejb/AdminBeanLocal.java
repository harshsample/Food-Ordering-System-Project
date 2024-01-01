/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entity.FeedbackMstr;
import entity.RegisterMstr;
import entity.RestaurantMstr;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author HP
 */
@Local
public interface AdminBeanLocal {
    //==================== For Restaurant ======================
    public Collection<RestaurantMstr> getAllRestaurant();
    public void approveRestaurant(int Rid,String status);
    public void blockRestaurant(int Rid,String status);
    public RestaurantMstr getRestaurantById(int Rid);
    public Long CountAllRestaurant();
    public Collection<RestaurantMstr> getApprovedRestaurant();
    public Collection<RestaurantMstr> getBlockRestaurant();
    
    
    //==================== For users =========================
    public Collection<RegisterMstr> getAllUsers();
    public RegisterMstr searchUserByEmail(String Email);
    public void updateUserByEmail(String Email,int Gid,String Name,int Phone);
    public String updatePasswordByEmail(String Email,String Password);
    public Long countAllUsers();
    
    //==================== For feedBack ======================
    public Collection<FeedbackMstr> viewAllFeedBack();
    public Long countAllFededBack();
}
