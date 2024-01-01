/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.FeedbackMstr;
import entity.GroupMstr;
import entity.RegisterMstr;
import entity.RestaurantMstr;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author HP
 */
@Stateless
public class AdminBean implements AdminBeanLocal {

    @PersistenceContext(unitName = "jpapu")
    EntityManager em;

    @Override
    public Collection<RegisterMstr> getAllUsers() {
//        RegisterMstr user = new RegisterMstr();
//        user.getGid().getGName();
        Collection<RegisterMstr> users = em.createNamedQuery("RegisterMstr.findAll").getResultList();
        return users;
    }

    @Override
    public Collection<FeedbackMstr> viewAllFeedBack() {
        Collection<FeedbackMstr> feedbacks = em.createNamedQuery("FeedbackMstr.findAll").getResultList();
        return feedbacks;
    }

    @Override
    public Collection<RestaurantMstr> getAllRestaurant() {
        Collection<RestaurantMstr> restaurant = em.createNamedQuery("RestaurantMstr.findAll").getResultList();
        return restaurant;
    }

    @Override
    public RestaurantMstr getRestaurantById(int Rid) {
        RestaurantMstr rid = em.find(RestaurantMstr.class, Rid);
        return rid;
    }

    @Override
    public void approveRestaurant(int Rid,String status) {
        RestaurantMstr restaurant = em.find(RestaurantMstr.class, Rid);

        if (restaurant != null) {
            // Update the status to 'approve'
            restaurant.setStatus("Approve");
            em.merge(restaurant); // Merge the changes into the database
        }else{
            System.out.println("Data Not Approved..");
        }
    }

    @Override
    public void blockRestaurant(int Rid,String status) {
        RestaurantMstr restaurant = em.find(RestaurantMstr.class, Rid);

        if (restaurant != null) {
            // Update the status to 'approve'
            restaurant.setStatus("Block");
            em.merge(restaurant); // Merge the changes into the database
        }else{
            System.out.println("Data not Bolcked..");
        }
    }
    
     @Override
    public RegisterMstr searchUserByEmail(String Email) {
        RegisterMstr e = em.find(RegisterMstr.class, Email);
        return e;
    }
    
    @Override
    public void updateUserByEmail(String Email, int Gid, String Name,int Phone) {
        RegisterMstr r = em.find(RegisterMstr.class, Email);
        GroupMstr gid = em.find(GroupMstr.class, Gid);
        
        r.setGid(gid);
        r.setName(Name);
        r.setPhone(Phone);
        
        em.merge(r);
    }
    
    @Override
    public String updatePasswordByEmail(String Email, String Password) {
        RegisterMstr r = em.find(RegisterMstr.class, Email);
        r.setPassword(Password);
        em.merge(r);
        return "Password Updated Successfully...";
    }

    @Override
    public Long CountAllRestaurant() {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(r) FROM RestaurantMstr r",
                Long.class
        );
        Long restaurantCount = query.getSingleResult();
        System.out.println("Total Restaurants: " + restaurantCount);

        // Perform any other actions with the restaurantCount if needed
        return restaurantCount;
    }
    
    @Override
    public Long countAllUsers() {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(u) FROM RegisterMstr u",
                Long.class
        );
        Long userCount = query.getSingleResult();
        System.out.println("Total Users: " + userCount);

        // Perform any other actions with the userCount if needed
        return userCount; // Return the user count
    }

    @Override
    public Long countAllFededBack() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(f) FROM FeedbackMstr f",Long.class);
        Long countFeedback = query.getSingleResult();
        System.out.println("Total Feedback : " + countFeedback);
        return countFeedback;
    }
    
    
    @Override
    public Collection<RestaurantMstr> getApprovedRestaurant() {
        TypedQuery<RestaurantMstr> restaurant = em.createQuery(
                "SELECT r FROM RestaurantMstr r WHERE r.status = 'Approve'",
                RestaurantMstr.class
        );
        return restaurant.getResultList();
    }

    @Override
    public Collection<RestaurantMstr> getBlockRestaurant() {
        TypedQuery<RestaurantMstr> restaurant = em.createQuery(
                "SELECT r FROM RestaurantMstr r WHERE r.status = 'Block'",
                RestaurantMstr.class
        );
        return restaurant.getResultList();
    }
    
}
