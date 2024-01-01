/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package JSFBeanRegister;

import Client.RestClientManager;
import ejb.ManagerBeanLocal;
import entity.MenuMstr;
import entity.OrderDetailsMstr;
import entity.RegisterMstr;
import entity.RestaurantMstr;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.http.Part;
import javax.ws.rs.core.Response;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import org.glassfish.soteria.identitystores.hash.PasswordHashCompare;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author HP
 */
@Named(value = "managerBean")
@SessionScoped
public class ManagerBean implements Serializable {

    RestClientManager cl = new RestClientManager();
    Response rs;
    Collection<RestaurantMstr> restaurants;
    GenericType<Collection<RestaurantMstr>> grestaurant;

    Collection<MenuMstr> menus;
    GenericType<Collection<MenuMstr>> gmenus;
    
    String updateMessage;
    
    Collection<OrderDetailsMstr> ordersDetails;
    GenericType<Collection<OrderDetailsMstr>> gOrderDetails;
    
    String userName,userPhone,Gid,processStatus,availableStatus;

    String updatePassword;
    
     Pbkdf2PasswordHashImpl pb;
    PasswordHashCompare pbk;
    
    @EJB ManagerBeanLocal mbl;
    
    public ManagerBean() {
        cl = new RestClientManager();
        restaurants = new ArrayList();
        grestaurant = new GenericType<Collection<RestaurantMstr>>() {
        };

        menus = new ArrayList<>();
        gmenus = new GenericType<Collection<MenuMstr>>() {
        };
        
        ordersDetails = new ArrayList<>();
        gOrderDetails = new GenericType<Collection<OrderDetailsMstr>>(){};
        
        pb = new Pbkdf2PasswordHashImpl();
        pbk = new PasswordHashCompare();
    }

    private Part file;
    String Email, Name, Address, Description, status, upRestName, upRestAddress, upRestDesc, upRestStatus;
    Integer Rid, Price, Qty, Mid;
    String menuName, MenuDescription, MenuStatus, upMenuName, upMenuDesc, upMenuStatus;
    
    
    
    public String checkEmail() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        String email = (String) session.getAttribute("Manager");
        if (email == null) {
            // Redirect to the login page only if the response is not committed
            if (!facesContext.getResponseComplete()) {
                try {
                    externalContext.redirect(externalContext.getRequestContextPath() + "/Login.jsf");
                } catch (IOException e) {
                    e.printStackTrace(); // Handle the exception as needed
                }
                return null; // No need to return a navigation outcome here
            }
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
    

    //To Add a Retaurant 
    public String AddRestaurant() {
        System.out.println("Yoyo");

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        if (session != null) {
            Email = (String) session.getAttribute("Manager");

            if (Email != null) {
                // Now you can use the email in your AddRestaurant method
                if (file != null) {
                    try {
                        String fileName = file.getSubmittedFileName();
                        InputStream in = file.getInputStream();
                        File uploadedFile = new File("C:\\Users\\HP\\OneDrive\\Desktop\\MSCICT\\SEM-1\\JAVA\\Food Ordering System\\FoodOrderingSystem\\src\\main\\webapp\\RestaurantImage\\" + fileName);
                        FileOutputStream out = new FileOutputStream(uploadedFile);
                        System.out.println("Path" + uploadedFile);
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = in.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                        }

                        status = "Block";
//                        System.out.println("Emalil =" + Email);
//                        System.out.println("Name =" + Name);
//                        System.out.println("Add =" + Address);
//                        System.out.println("Desc =" + Description);
//                        System.out.println("Image name = " + fileName);
//                        System.out.println("status =" + status);
                        // You can use standard Java I/O operations to handle the file.
                        cl.AddRestaurant(null, Email, Name, Address, Description, fileName, status);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } else {

            }
        } else {

        }

        return "ViewAllRestaurant.jsf?faces-redirect=true";

    }

    //Get Restaurant Email Wise
    public Collection<RestaurantMstr> searchRestaurantByEmail() {
//        String fname;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        if (session != null) {
            Email = (String) session.getAttribute("Manager");
            rs = cl.getRestaurantsByEmail(Response.class, Email);
//        GenericType<RestaurantMstr> gstate = new GenericType<RestaurantMstr>() {
//        };
            restaurants = rs.readEntity(grestaurant);

        }
        return restaurants;
    }

    //Ge't Restaurant ID Wise For update Retaurant we get id and all the data of restaurant
    public String searchRestaurantById(int RestId) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

//        if (session != null) {
            Email = (String) session.getAttribute("Manager");
            rs = cl.getRestaurantById(Response.class, RestId + "");
            GenericType<RestaurantMstr> gstate = new GenericType<RestaurantMstr>() {
            };
            RestaurantMstr cc = rs.readEntity(gstate);
            Rid = cc.getRid();
            upRestName = cc.getName();
            upRestAddress = cc.getAddress();
            upRestDesc = cc.getDescription();
            upRestStatus = cc.getStatus();
//            System.out.println("id :- " + Rid);
//            System.out.println("Name :- " + upRestName);
//            System.out.println("add :- " + upRestAddress);
//            System.out.println("Desc  :- " + upRestDesc);
//            System.out.println("upRestStatus :- " + upRestStatus);
            
//        }
        return "EditRestaurant.jsf?faces-redirect=true";
    }

    // update Restaurant
    public String updateRestaurant() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        if (session != null) {
            Email = (String) session.getAttribute("Manager");

            if (Email != null) {
                // Now you can use the email in your AddRestaurant method
                if (file != null) {

                    try {
                        String fileName = file.getSubmittedFileName();
                        InputStream in = file.getInputStream();
                        File uploadedFile = new File("C:\\Users\\HP\\OneDrive\\Desktop\\MSCICT\\SEM-1\\JAVA\\Food Ordering System\\FoodOrderingSystem\\src\\main\\webapp\\RestaurantImage\\" + fileName);
                        FileOutputStream out = new FileOutputStream(uploadedFile);
                        System.out.println("Path" + uploadedFile);
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = in.read(buffer)) != -1) {
                            out.write(buffer, 0, bytesRead);
                        }

                        RestaurantMstr rm = new RestaurantMstr();
//                        rm.setEmail(Email);
                        rm.setRid(Rid);
                        rm.setName(upRestName);
                        rm.setAddress(upRestAddress);
                        rm.setDescription(upRestDesc);
                        rm.setImg(fileName);
                        rm.setStatus(upRestStatus);

                        System.out.println("Emalil =" + Email);
                        System.out.println("Name =" + Name);
                        System.out.println("Add =" + Address);
                        System.out.println("Desc =" + Description);
                        System.out.println("Image name = " + fileName);
                        System.out.println("status =" + status);
                        // You can use standard Java I/O operations to handle the file.
                        cl.updaeRestaurant(null, Rid + "", Email, upRestName, upRestAddress, upRestDesc, fileName, upRestStatus);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } else {

            }
        } else {

        }

        return "ViewAllRestaurant.jsf?faces-redirect=true"; // Or any navigation outcome you desire
    }

    //Delete Restaurant
    public String deleteRestaurant(int Rid) {
        cl.deleteRestaurantById(Rid + "");
        return "ViewAllRestaurant.jsf?faces-redirect=true";
    }

    //Search Restaurant id for Adding Food in Perticular Restaurant
    public String searchRestaurantForAddFood(int RestId) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        if (session != null) {
            Email = (String) session.getAttribute("Manager");
            Response rs = cl.getRestaurantById(Response.class, RestId + "");
            GenericType<RestaurantMstr> gstate = new GenericType<RestaurantMstr>() {
            };
            RestaurantMstr cc = rs.readEntity(gstate);
            Rid = cc.getRid();
        }

        return "AddMenu.jsf?faces-redirect=true";

    }

    // Add Food in perticular Restaurant
    public String AddMenu() {
        System.out.println("Yoyo");

        if (file != null) {
            try {
                String fileName = file.getSubmittedFileName();
                InputStream in = file.getInputStream();
                File uploadedFile = new File("C:\\Users\\HP\\OneDrive\\Desktop\\MSCICT\\SEM-1\\JAVA\\Food Ordering System\\FoodOrderingSystem\\src\\main\\webapp\\FoodImage\\" + fileName);
                FileOutputStream out = new FileOutputStream(uploadedFile);
                System.out.println("Path" + uploadedFile);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }

                RestaurantMstr r = new RestaurantMstr();

                MenuStatus = "Available";
                r.setRid(Rid);

                // You can use standard Java I/O operations to handle the file.
                cl.AddMenu(null, Rid + "", menuName, Address, MenuDescription, fileName, Price + "", Qty + "", MenuStatus);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "ViewMenu.jsf?faces-redirect=true";

    }

    public String getMenuByRid(int restId) {
        Response rs = cl.getRestaurantById(Response.class, restId + "");
        GenericType<RestaurantMstr> gstate = new GenericType<RestaurantMstr>() {
        };
        RestaurantMstr cc = rs.readEntity(gstate);

        Rid = cc.getRid();
        return "ViewMenu.jsf?faces-redirect=true";
    }

    public Collection<MenuMstr> getMenu() {
        if (Rid != null) {

            rs = cl.getMenuByRestaurant(Response.class, Rid + "");
//        GenericType<RestaurantMstr> gstate = new GenericType<RestaurantMstr>() {
//        };
            menus = rs.readEntity(gmenus);

        }
        return menus;

    }

    public String searchMenuById(int RestId) {

        Response rs = cl.getMenuByMid(Response.class, RestId + "");
        GenericType<MenuMstr> gstate = new GenericType<MenuMstr>() {
        };
        MenuMstr cc = rs.readEntity(gstate);
        Mid = cc.getMid();
        upMenuName = cc.getName();
        upMenuDesc = cc.getDescription();
        Price = cc.getPrice();
        Qty = cc.getQty();
        upMenuStatus = cc.getStatus();

        return "EditMenu.jsf?faces-redirect=true";

    }

    public String updateMenu() {
        if (file != null) {

            try {
                String fileName = file.getSubmittedFileName();
                InputStream in = file.getInputStream();
                File uploadedFile = new File("C:\\Users\\HP\\OneDrive\\Desktop\\MSCICT\\SEM-1\\JAVA\\Food Ordering System\\FoodOrderingSystem\\src\\main\\webapp\\FoodImage\\" + fileName);
                FileOutputStream out = new FileOutputStream(uploadedFile);
                System.out.println("Path" + uploadedFile);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }

                MenuMstr rm = new MenuMstr();
//                        rm.setEmail(Email);
                rm.setMid(Mid);
                rm.setName(upMenuName);
                rm.setDescription(upMenuDesc);
                rm.setImg(fileName);
                rm.setPrice(Price);
                rm.setQty(Qty);
                rm.setStatus(upMenuStatus);

//                        System.out.println("Mid =" + Mid);
//                        System.out.println("Name =" + Name);
//                        System.out.println("Desc =" + Description);
//                        System.out.println("Image name = " + fileName);
//                        System.out.println("Price = " + Price);
//                        System.out.println("Qty = " + Qty);
//                        System.out.println("status =" + status);
                // You can use standard Java I/O operations to handle the file.
                cl.updaeMenu(null, Mid + "", Rid + "", upMenuName, upMenuDesc, fileName, Price + "", Qty + "", upMenuStatus);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "ViewMenu.jsf?faces-redirect=true"; // Or any navigation outcome you desire
    }

    public String deleteMenu(int Mid) {
        cl.removeMenuById(Mid + "");
        return "ViewMenu.jsf?faces-redirect=true";
    }
    
    
    public String searchUserByEmail() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        Email = (String) session.getAttribute("Manager");
        rs = cl.searchUserByEmail(Response.class, Email);
        GenericType<RegisterMstr> gstate = new GenericType<RegisterMstr>() {
        };

        RegisterMstr c = rs.readEntity(gstate);
        Email = c.getEmail();
        userName = c.getName();
        userPhone = c.getPhone().toString();
        Gid = c.getGid().toString();
        System.out.println("Email :- " + Email);
        System.out.println("Gid :- " + Gid);
        System.out.println("userName :- " + userName);
        System.out.println("Phone :- " + userPhone);
        return "UpdateProfile.jsf?faces-redirect=true";
    }
    
    
    public String updateUser()
    {
        RegisterMstr r = new RegisterMstr();
        r.setEmail(Email);
        r.setName(userName);
//        r.setGid(gid);
        Gid = "2";
        
        r.setPhone(Integer.parseInt(userPhone));
        cl.updateUserByEmail(Email, Gid, userName, userPhone);
        System.out.println("Email :- " + Email);
        System.out.println("Gid :- " + Gid);
        System.out.println("userName :- " + userName);
        System.out.println("Phone :- " + userPhone);
        return "UpdateProfile.jsf?faces-redirect=true";
    }
    
    public Collection<OrderDetailsMstr> getAllOrders()
    {
        rs = cl.getAllOrderDetails(Response.class);
        ordersDetails = rs.readEntity(gOrderDetails);
        return ordersDetails;
    }
    
   
    
    public String updateStatusOnProcess(String orderId)
    {
        cl.updateStatusOnProccess(orderId+"", processStatus);
        return "ViewAllOrders.jsf?faces-redirect=true";
    }
    
    public String updateStatusDelivered(String orderId)
    {
        cl.updateStatusDelivered(orderId+"", processStatus);
        return "ViewAllOrders.jsf?faces-redirect=true";
    }
    
    public String updateStatusPending(String orderId)
    {
        cl.updateStatusOnPending(orderId+"", processStatus);
        return "ViewAllOrders.jsf?faces-redirect=true";
    }
    
    public String updateAvailableStatus(String menuId)
    {
        cl.updateMenuAvailable(menuId+"", availableStatus);
        return "ViewMenu.jsf?faces-redirect=true";
    }
    
    public String updateNotAvailableStatus(String menuId)
    {
        cl.updateMenuNotAvailable(menuId+"", availableStatus);
        return "ViewMenu.jsf?faces-redirect=true";
    }
    
    public String ForgotPassword()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        String userEmail = (String) session.getAttribute("Manager");
        
        
        
        this.updateMessage = cl.updatePasswordByEmail(userEmail, updatePassword);
        
        return "ForgotPassword.jsf?faces-redirect=true";
    }
    
    public String ChangePassword()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        String userEmail = (String) session.getAttribute("Manager");
        
        
        
        this.updateMessage = cl.updatePasswordByEmail(userEmail, updatePassword);
        
        return "ChangePassword.jsf?faces-redirect=true";
    }
    
    
    public Long fetchCountRestaurant()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        Email = (String) session.getAttribute("Manager");
        Long cartCount = mbl.countRestaurantByEmail(Email);

        return cartCount;
    }
    
     public Long fetchAllOrder()
    {
        Long countOrder = mbl.countAllOrders();
        return countOrder;
        
    }
     
     public Long fetchMenuByEmail()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);

        Email = (String) session.getAttribute("Manager");
        Long countMenu = mbl.countMenuItemsByEmail(Email);
        return countMenu;
        
    }

    public Collection<OrderDetailsMstr> getOrdersDetails() {
        return ordersDetails;
    }

    public void setOrdersDetails(Collection<OrderDetailsMstr> ordersDetails) {
        this.ordersDetails = ordersDetails;
    }

    public GenericType<Collection<OrderDetailsMstr>> getgOrderDetails() {
        return gOrderDetails;
    }

    public void setgOrderDetails(GenericType<Collection<OrderDetailsMstr>> gOrderDetails) {
        this.gOrderDetails = gOrderDetails;
    }
    
    

    public RestClientManager getCl() {
        return cl;
    }

    public void setCl(RestClientManager cl) {
        this.cl = cl;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Response getRs() {
        return rs;
    }

    public void setRs(Response rs) {
        this.rs = rs;
    }

    public Collection<RestaurantMstr> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Collection<RestaurantMstr> restaurants) {
        this.restaurants = restaurants;
    }

    public GenericType<Collection<RestaurantMstr>> getGrestaurant() {
        return grestaurant;
    }

    public void setGrestaurant(GenericType<Collection<RestaurantMstr>> grestaurant) {
        this.grestaurant = grestaurant;
    }

    public Integer getRid() {
        return Rid;
    }

    public void setRid(Integer Rid) {
        this.Rid = Rid;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer Price) {
        this.Price = Price;
    }

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer Qty) {
        this.Qty = Qty;
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

    public Integer getMid() {
        return Mid;
    }

    public void setMid(Integer Mid) {
        this.Mid = Mid;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDescription() {
        return MenuDescription;
    }

    public void setMenuDescription(String MenuDescription) {
        this.MenuDescription = MenuDescription;
    }

    public String getMenuStatus() {
        return MenuStatus;
    }

    public void setMenuStatus(String MenuStatus) {
        this.MenuStatus = MenuStatus;
    }

    public String getUpMenuName() {
        return upMenuName;
    }

    public void setUpMenuName(String upMenuName) {
        this.upMenuName = upMenuName;
    }

    public String getUpMenuDesc() {
        return upMenuDesc;
    }

    public void setUpMenuDesc(String upMenuDesc) {
        this.upMenuDesc = upMenuDesc;
    }

    public String getUpMenuStatus() {
        return upMenuStatus;
    }

    public void setUpMenuStatus(String upMenuStatus) {
        this.upMenuStatus = upMenuStatus;
    }

    public String getUpRestName() {
        return upRestName;
    }

    public void setUpRestName(String upRestName) {
        this.upRestName = upRestName;
    }

    public String getUpRestAddress() {
        return upRestAddress;
    }

    public void setUpRestAddress(String upRestAddress) {
        this.upRestAddress = upRestAddress;
    }

    public String getUpRestDesc() {
        return upRestDesc;
    }

    public void setUpRestDesc(String upRestDesc) {
        this.upRestDesc = upRestDesc;
    }

    public String getUpRestStatus() {
        return upRestStatus;
    }

    public void setUpRestStatus(String upRestStatus) {
        this.upRestStatus = upRestStatus;
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

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getUpdatePassword() {
        return updatePassword;
    }

    public void setUpdatePassword(String updatePassword) {
        this.updatePassword = updatePassword;
    }

    public Pbkdf2PasswordHashImpl getPb() {
        return pb;
    }

    public void setPb(Pbkdf2PasswordHashImpl pb) {
        this.pb = pb;
    }

    public PasswordHashCompare getPbk() {
        return pbk;
    }

    public void setPbk(PasswordHashCompare pbk) {
        this.pbk = pbk;
    }

    public String getAvailableStatus() {
        return availableStatus;
    }

    public void setAvailableStatus(String availableStatus) {
        this.availableStatus = availableStatus;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }
    
    

}
