/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.GroupMstr;
import entity.MenuMstr;
import entity.OrderDetailsMstr;
import entity.OrderMstr;
import entity.RegisterMstr;
import entity.RestaurantMstr;
//import static entity.RestaurantMstr_.email;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author HP
 */
@Stateless
public class ManagerBean implements ManagerBeanLocal {

    @PersistenceContext(unitName = "jpapu")
    EntityManager em;

    @Resource
    private SessionContext sessionContext;

    @Override
    public void AddRestaurant(String Email, String Name, String Address, String Description, String file, String Status) {

// Assuming you have already retrieved the RegisterMstr entity using the provided Email
        RegisterMstr registerMstr = em.find(RegisterMstr.class, Email);

        if (registerMstr == null) {
            // Throw a custom exception indicating that RegisterMstr is not found
            throw new EntityNotFoundException("RegisterMstr not found for email: " + Email);
        }

        RestaurantMstr restaurant = new RestaurantMstr();

        restaurant.setEmail(registerMstr); // Set the email as a 'RegisterMstr' object
        restaurant.setName(Name);
        restaurant.setAddress(Address);
        restaurant.setDescription(Description);
        restaurant.setImg(file);
        restaurant.setStatus(Status);

        // Persist the restaurant entity to the database
        em.persist(restaurant);

    }

    @Override
    public Collection<RestaurantMstr> getRestaurantsByEmail(String managerEmail) {
        if (managerEmail != null && !managerEmail.isEmpty()) {
            TypedQuery<RestaurantMstr> query = em.createQuery(
                    "SELECT r FROM RestaurantMstr r WHERE r.email.email = :managerEmail",
                    RestaurantMstr.class
            );
            query.setParameter("managerEmail", managerEmail);
            return query.getResultList();
        } else {
            
            return null; // Or you can return an empty list or throw an exception as needed
        }
    }

    @Override
    public void updateRestaurantById(int Rid,String Email, String Name, String Address, String Description, String file,String Status) {
        try {
            RestaurantMstr restaurant = em.find(RestaurantMstr.class,
                    Rid);
            restaurant.setName(Name);
            restaurant.setAddress(Address);
            restaurant.setDescription(Description);
            restaurant.setImg(file);
            em.merge(restaurant);
        } catch (Exception e) {
            System.out.println("Restaurant not updated...");
        }
        
    }

    @Override
    public RestaurantMstr
            getRestaurantById(int Rid) {
        RestaurantMstr rid = em.find(RestaurantMstr.class,
                 Rid);
        return rid;
    }

    @Override
    public void deleteRestaurantById(int Rid) {
        try {
            RestaurantMstr rid = em.find(RestaurantMstr.class,
                    Rid);
            em.remove(rid);
        } catch (Exception e) {
            System.out.println("Restaurant not Deleted...");
        }
        
    }

    @Override
    public void AddMenu(int Rid, String Name, String Description, String file, int Price, int Qty, String Status) {
        try {
            RestaurantMstr rid = em.find(RestaurantMstr.class,
                    Rid);
            MenuMstr menu = new MenuMstr();
            menu.setRid(rid);
            menu.setName(Name);
            menu.setDescription(Description);
            menu.setImg(file);
            menu.setPrice(Price);
            menu.setQty(Qty);
            menu.setStatus(Status);
            em.persist(menu);
        } catch (Exception e) {
            System.out.println("Menu not Added...");
        }

    }

    @Override
    public Collection<MenuMstr> getMenuByRestaurant(int Rid) {
        TypedQuery<MenuMstr> query = em.createQuery("SELECT m FROM MenuMstr m WHERE m.rid.rid = :Rid", MenuMstr.class
        );
        query.setParameter("Rid", Rid);
        return query.getResultList();
    }

    @Override
    public void updateMenuByMid(int Mid, int Rid, String Name, String Description, String file, int Price, int Qty, String Status) {
        try {
            MenuMstr menu = em.find(MenuMstr.class,
                    Mid);
            menu.setName(Name);
            menu.setDescription(Description);
            menu.setImg(file);
            menu.setPrice(Price);
            menu.setQty(Qty);
            em.merge(menu);
        } catch (Exception e) {
            System.out.println("Menu is not Updated....");
        }

    }

    @Override
    public MenuMstr
            getMenuByMid(int Mid) {
        MenuMstr menu = em.find(MenuMstr.class,
                 Mid);
        return menu;
    }

    @Override
    public void removeMenuById(int Mid) {
        MenuMstr menu = em.find(MenuMstr.class,
                 Mid);
        em.remove(menu);
    }
    
    @Override
    public RegisterMstr searchUserByEmail(String Email) {
        RegisterMstr e = em.find(RegisterMstr.class, Email);
        return e;
    }
    
    @Override
    public void updateUserByEmail(String Email, int Gid, String Name, int Phone) {
        try {
            RegisterMstr r = em.find(RegisterMstr.class, Email);
            GroupMstr gid = em.find(GroupMstr.class, Gid);

            r.setGid(gid);
            r.setName(Name);
            r.setPhone(Phone);

            em.merge(r);
        } catch (Exception e) {
            System.out.println("User Not Updated..");
        }

    }
    
    
    @Override
    public Collection<OrderDetailsMstr> getAllOrderDetails() {
        TypedQuery<OrderDetailsMstr> query = em.createQuery(
                "SELECT od FROM OrderDetailsMstr od",
                OrderDetailsMstr.class
        );
        return query.getResultList();
    }

    @Override
    public void updateStatusOnProccess(int oid, String status) {
//        RegisterMstr register = em.find(RegisterMstr.class, Email);
            OrderMstr o = em.find(OrderMstr.class, oid);
        if (o != null) {
            
            o.setStatus("On Process");
            em.merge(o); // Merge the changes into the database
        } else {
            System.out.println("No user found for the given Id");
            // Handle the case where no user is found for the given email
        }
    }

    @Override
    public void updateStatusDelivered(int oid, String status) {
        OrderMstr o = em.find(OrderMstr.class, oid);
        if (o != null) {
            
            o.setStatus("Delivered");
            em.merge(o); // Merge the changes into the database
        } else {
            System.out.println("No user found for the given Id");
            // Handle the case where no user is found for the given email
        }
    }

    @Override
    public void updateStatusOnPending(int oid, String status) {
        OrderMstr o = em.find(OrderMstr.class, oid);
        if (o != null) {
            
            o.setStatus("Pending");
            em.merge(o); // Merge the changes into the database
        } else {
            System.out.println("No user found for the given Id");
            // Handle the case where no user is found for the given email
        }
    }
    
    @Override
    public String updatePasswordByEmail(String Email, String Password) {
        try {
            RegisterMstr r = em.find(RegisterMstr.class, Email);
            r.setPassword(Password);
            em.merge(r);
            return "Password Updated Successfully...";
        } catch (Exception e) {
            System.out.println("Password not updated....");
            return "Password not updated...";
        }

    }
    
    @Override
    public Long countRestaurantByEmail(String userEmail) {
        if (userEmail != null && !userEmail.isEmpty()) {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(r) FROM RestaurantMstr r WHERE r.email.email = :userEmail",
                    Long.class
            );
            query.setParameter("userEmail", userEmail);
            Long countRestaurant = query.getSingleResult();
            System.out.println("Restaurant is: " + countRestaurant);

            // Perform any other actions with the cartCount if needed
            return countRestaurant; // Return the cart count
        } else {
            // Handle the case where the user's email is null or empty
            System.out.println("User email is null or empty");
            // You can throw an exception or handle it as needed
            return 0L; // Return a default value, or you can return null
        }
    }

    @Override
    public Long countAllOrders() {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(o) FROM OrderMstr o",
                Long.class
        );
        Long orderCount = query.getSingleResult();
        System.out.println("Total Users: " + orderCount);

        // Perform any other actions with the userCount if needed
        return orderCount; // Return the user count
    }

    @Override
     public Long countMenuItemsByEmail(String userEmail) {
        
            Query query = em.createQuery("SELECT COUNT(m) FROM MenuMstr m WHERE m.rid.email.email = :userEmail");
            query.setParameter("userEmail", userEmail);
            Long count = (Long) query.getSingleResult();
            return count;
       
    }
     
     @Override
     public void updateMenuNotAvailable(int Mid, String status) {
//        RegisterMstr register = em.find(RegisterMstr.class, Email);
            MenuMstr o = em.find(MenuMstr.class, Mid);
        if (o != null) {
            
            o.setStatus("Not Available");
            em.merge(o); // Merge the changes into the database
        } else {
            System.out.println("No user found for the given Id");
            // Handle the case where no user is found for the given email
        }
    }
     
     @Override
     public void updateMenuAvailable(int Mid, String status) {
//        RegisterMstr register = em.find(RegisterMstr.class, Email);
            MenuMstr o = em.find(MenuMstr.class, Mid);
        if (o != null) {
            
            o.setStatus("Available");
            em.merge(o); // Merge the changes into the database
        } else {
            System.out.println("No user found for the given Id");
            // Handle the case where no user is found for the given email
        }
    }
    
}
