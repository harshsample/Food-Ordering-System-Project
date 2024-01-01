/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ejb;

import entity.MenuMstr;
import entity.OrderDetailsMstr;
import entity.RegisterMstr;
import entity.RestaurantMstr;
import java.util.Collection;
import javax.ejb.Local;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author HP
 */
@Local
public interface ManagerBeanLocal {
    
    
    
    
    //====================== Restaurant At Manager ========================================
    
    public void AddRestaurant(String Email,String Name,String Address,String Description,String file,String Status);
    public Collection<RestaurantMstr> getRestaurantsByEmail(String managerEmail);
    public RestaurantMstr getRestaurantById(int Rid);
    public void updateRestaurantById(int Rid,String Email,String Name,String Address,String Description,String file,String Status);
    public void deleteRestaurantById(int Rid);
    public Long countRestaurantByEmail(String userEmail);
    
    //==================== Menu ==============================
    
    public void AddMenu(int Rid,String Name,String Description,String file,int Price,int Qty,String Status);
    public Collection<MenuMstr> getMenuByRestaurant(int Rid);
    public MenuMstr getMenuByMid(int Mid);
    public void updateMenuByMid(int Mid,int Rid,String Name,String Description,String file,int Price,int Qty,String Status);
    public void removeMenuById(int Mid);
    public Long countMenuItemsByEmail(String userEmail);
    public void updateMenuNotAvailable(int Mid, String status);
    public void updateMenuAvailable(int Mid, String status);
    
    //=================== Update user ===========================
    public RegisterMstr searchUserByEmail(String Email);
    public void updateUserByEmail(String Email,int Gid,String Name,int Phone);
    public String updatePasswordByEmail(String Email,String Password);
    
    //==================== Order ===============================
    
    public Collection<OrderDetailsMstr> getAllOrderDetails();
    public void updateStatusOnProccess(int oid,String status);
    public void updateStatusDelivered(int  oid,String status);
    public void updateStatusOnPending(int  oid,String status);
    public Long countAllOrders();
}
