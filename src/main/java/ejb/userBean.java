/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.AddressMstr;
import entity.CartMstr;
import entity.FeedbackMstr;
import entity.GroupMstr;
import entity.MenuMstr;
import entity.OrderDetailsMstr;
import entity.OrderMstr;
import entity.PaymentMstr;
import entity.RegisterMstr;
import entity.RestaurantMstr;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author HP
 */
@Stateless
public class userBean implements userBeanLocal {
    
    @EJB validate validation;
    
    

    @PersistenceContext(unitName = "jpapu")
    EntityManager em;

    
    
    @Override
    public String AddRegister(String Email, int Gid, String Name, String Password, int phone, String State, String City, String Country) {
        if (validation.isEmailExists(Email)) {
            return "**Email is already Exists...";
        }
        
        try {
            GroupMstr gid = em.find(GroupMstr.class, Gid);
            RegisterMstr register = new RegisterMstr();
            register.setEmail(Email);
            register.setGid(gid);
            register.setName(Name);
            register.setPassword(Password);
            register.setPhone(phone);

            em.persist(register);

            AddressMstr address = new AddressMstr();
            address.setEmail(register);
            address.setState(State);
            address.setCity(City);
            address.setCountry(Country);
            em.persist(address);
            return "Register Success...";
            
        } catch (Exception e) {
            System.out.println("Message :  " + e.getMessage());
            e.printStackTrace();
            return "";
        }
        
    }

    @Override
    public void AddAddress(String Email, String State, String City, String Country) {
        try {
            RegisterMstr addressEmail = em.find(RegisterMstr.class, Email);
            AddressMstr address = new AddressMstr();
            address.setEmail(addressEmail);
            address.setState(State);
            address.setCity(City);
            address.setCountry(Country);
        } catch (Exception e) {
            System.out.println("Addredd Not Added...");
        }

    }

//    @Override
//    public String Login(String Email, String Password) {
//        try {
//            TypedQuery<RegisterMstr> query = em.createNamedQuery("RegisterMstr.findByEmail", RegisterMstr.class)
//                    .setParameter("email", Email);
//
//            RegisterMstr user = query.getSingleResult();
//            user.getGid();
//
//            String userPassword = user.getPassword();
//
//            if (userPassword.equals(Password)) {
//                String role = user.getGid().getGName();
//                return role;
//
//            }
//        } catch (NoResultException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//
//    }

    @Override
    public Collection<RestaurantMstr> getApprovedRestaurant() {
        TypedQuery<RestaurantMstr> restaurant = em.createQuery(
                "SELECT r FROM RestaurantMstr r WHERE r.status = 'Approve'",
                RestaurantMstr.class
        );
        return restaurant.getResultList();
    }

    @Override
    public void AddCart(String Email, int Mid, int Qty, int Price, Date Added_at) {
        try {
            CartMstr cm = new CartMstr();
            MenuMstr mid = em.find(MenuMstr.class, Mid);
            RegisterMstr registerMstr = new RegisterMstr();
            registerMstr.setEmail(Email);
            cm.setEmail(registerMstr);
            cm.setMid(mid);
            cm.setQty(Qty);
            cm.setPrice(Price);
            cm.setAddedat(Added_at);
            em.persist(cm);
        } catch (Exception e) {
            System.out.println("Cart Not Added..");
        }

    }

    @Override
    public Collection<CartMstr> getCartByEmail(String userEmail) {
        if (userEmail != null && !userEmail.isEmpty()) {
            TypedQuery<CartMstr> query = em.createQuery(
                    "SELECT c FROM CartMstr c WHERE c.email.email = :userEmail",
                    CartMstr.class
            );
            query.setParameter("userEmail", userEmail);
            return query.getResultList();
        } else {
            // Handle the case where the manager's email is null or empty
            return null; // Or you can return an empty list or throw an exception as needed
        }
    }
    
    @Override
    public Long countCartByEmail(String userEmail) {
        if (userEmail != null && !userEmail.isEmpty()) {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(c) FROM CartMstr c WHERE c.email.email = :userEmail",
                    Long.class
            );
            query.setParameter("userEmail", userEmail);
            Long cartCount = query.getSingleResult();
            System.out.println("Cart is: " + cartCount);

            // Perform any other actions with the cartCount if needed
            return cartCount; // Return the cart count
        } else {
            // Handle the case where the user's email is null or empty
            System.out.println("User email is null or empty");
            // You can throw an exception or handle it as needed
            return 0L; // Return a default value, or you can return null
        }
    }
    
    @Override
    public Long sumCartPriceByEmail(String userEmail) {
        if (userEmail != null && !userEmail.isEmpty()) {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT SUM(c.price) FROM CartMstr c WHERE c.email.email = :userEmail",
                    Long.class
            );
            query.setParameter("userEmail", userEmail);
            Long cartSum = query.getSingleResult();

            if (cartSum == null) {
                cartSum = 0L; // If there are no cart entries, set the sum to 0L
            }

            System.out.println("Cart sum for user " + userEmail + " is: " + cartSum);

            // Perform any other actions with the cartSum if needed
            return cartSum; // Return the cart sum as Long
        } else {
            // Handle the case where the user's email is null or empty
            System.out.println("User email is null or empty");
            // You can throw an exception or handle it as needed
            return 0L; // Return a default value, or you can return null
        } // Return a default value, or you can return null
        
    }



    @Override
    public RegisterMstr searchUserByEmail(String Email) {
        RegisterMstr e = em.find(RegisterMstr.class, Email);
        return e;
    }

    @Override
    public void deleteCartById(int Cid) {
        CartMstr cid = em.find(CartMstr.class, Cid);
        em.remove(cid);
    }

    @Override
    public void addFeedBack(String Email, String Description, Date date) {
        try {
            FeedbackMstr fm = new FeedbackMstr();
            RegisterMstr email = em.find(RegisterMstr.class, Email);
            fm.setEmail(email);
            fm.setDescription(Description);
            fm.setDate(date);
            em.persist(fm);
        } catch (Exception e) {
            System.out.println("FeedBack Not added...");
        }

    }

    @Override
    public Collection<RestaurantMstr> getFourRestaurant() {
        try {
            TypedQuery<RestaurantMstr> restaurantQuery = em.createQuery(
                    "SELECT r FROM RestaurantMstr r WHERE r.status = 'Approve'",
                    RestaurantMstr.class
            );

            // Set the maximum number of results to 4
            restaurantQuery.setMaxResults(4);

            return restaurantQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Collection<RestaurantMstr> getThreeRestaurant() {
        try {
            TypedQuery<RestaurantMstr> restaurantQuery = em.createQuery(
                    "SELECT r FROM RestaurantMstr r WHERE r.status = 'Approve'",
                    RestaurantMstr.class
            );

            // Set the maximum number of results to 3
            restaurantQuery.setMaxResults(3);

            return restaurantQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
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
            System.out.println("User Not updated...");
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
            System.out.println("Password not updated...");
            return "Password Not updated...";
        }

    }
    
    
   
    
    @Override
     public void placeOrder(String userEmail) {
       Collection<CartMstr> cartItems = em.createQuery("SELECT c FROM CartMstr c WHERE c.email.email = :email", CartMstr.class)
            .setParameter("email", userEmail)
            .getResultList();

    // Check if there are items in the cart
    if (!cartItems.isEmpty()) {
        // Find the user by email
        RegisterMstr user = em.find(RegisterMstr.class, userEmail);

        // Create an order
        OrderMstr order = new OrderMstr();
        order.setEmail(user);
        order.setOrderdate(new Date());
        order.setTotalAmt(calculateTotalAmount(cartItems).intValue());
        order.setStatus("Pending");

        // Save the order to the database
        em.persist(order);

        // Create order details
        for (CartMstr cartItem : cartItems) {
            OrderDetailsMstr orderDetails = new OrderDetailsMstr();
            orderDetails.setOid(order);
            orderDetails.setMid(cartItem.getMid());
            orderDetails.setPrice(cartItem.getPrice());
            orderDetails.setQty(cartItem.getQty());
            orderDetails.setTotalAmt(cartItem.getPrice());

            // Save order details to the database
            em.persist(orderDetails);
        }

        // Remove cart items from CartMstr after successfully placing the order
        for (CartMstr cartItem : cartItems) {
            em.remove(cartItem);
        }

        // Update status in CartMstr or clear the cart
        // ...

    } else {
        // Handle the case where there are no items in the cart for the specified user
        // You may throw an exception, log a message, or take appropriate action based on your requirements.
    }
    }

    private BigDecimal calculateTotalAmount(Collection<CartMstr> cartItems) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (CartMstr cartItem : cartItems) {
            totalAmount = totalAmount.add(BigDecimal.valueOf(cartItem.getPrice()));

        }
        return totalAmount;
    }
    
    @Override
    public Collection<OrderDetailsMstr> getOrderDetailsForUser(String userEmail) {
        TypedQuery<OrderDetailsMstr> query = em.createQuery(
                "SELECT od FROM OrderDetailsMstr od JOIN od.oid o WHERE o.email.email = :email AND o.status = 'Delivered'",
                OrderDetailsMstr.class
        );
        query.setParameter("email", userEmail);
        return query.getResultList();
    }
    
    @Override
    public Collection<OrderDetailsMstr> getOrderDetailsOnProcess(String userEmail) {
        TypedQuery<OrderDetailsMstr> query = em.createQuery(
                "SELECT od FROM OrderDetailsMstr od JOIN od.oid o WHERE o.email.email = :email AND o.status = 'On Process'",
                OrderDetailsMstr.class
        );
        query.setParameter("email", userEmail);
        return query.getResultList();
    }
    
    @Override
    public Collection<OrderDetailsMstr> getOrderDetailsPending(String userEmail) {
        TypedQuery<OrderDetailsMstr> query = em.createQuery(
                "SELECT od FROM OrderDetailsMstr od JOIN od.oid o WHERE o.email.email = :email AND o.status = 'Pending'",
                OrderDetailsMstr.class
        );
        query.setParameter("email", userEmail);
        return query.getResultList();
    }

    @Override
    public void processPaymentForCurrentUser(String Email, Date dop, String status, String pType) {
//        RegisterMstr e =  em.find(RegisterMstr.class, Email);
        TypedQuery<OrderMstr> query = em.createQuery(
                "SELECT o FROM OrderMstr o WHERE o.email.email = :email",
                OrderMstr.class
        );
        query.setParameter("email", Email);

        Collection<OrderMstr> orders = query.getResultList();

        
        
        for (OrderMstr order : orders) {
            PaymentMstr payment = new PaymentMstr();
            RegisterMstr e =  em.find(RegisterMstr.class, Email);
            payment.setOid(order);
            payment.setEmail(e);
            payment.setDop(dop);
            payment.setStatus(status);
            payment.setPType(pType);
            em.persist(payment);
        }
    }
    
    @Override
    public Collection<MenuMstr> getMenuByRestaurantAndStatus(int Rid) {
        TypedQuery<MenuMstr> query = em.createQuery("SELECT m FROM MenuMstr m WHERE m.rid.rid = :Rid AND m.status = 'Available'", MenuMstr.class);
        query.setParameter("Rid", Rid);
        return query.getResultList();
    }



}
