/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package Client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:RestManager [RestManager]<br>
 * USAGE:
 * <pre>
 *        RestClientManager client = new RestClientManager();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author HP
 */
public class RestClientManager {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/FoodOrderingSystem/resources";

    public RestClientManager() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("RestManager");
    }

    public <T> T getAddressesofCustomer(Class<T> responseType, String Rid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getMenuByRestaurant/{0}", new Object[]{Rid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getRestaurantsByEmail(Class<T> responseType, String Email) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getRestaurantByEmail/{0}", new Object[]{Email}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void removeMenuById(String Mid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removeMenu/{0}", new Object[]{Mid})).request().delete();
    }

    public void updateMenuAvailable(String Mid, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("AvalilableStatus/{0}/{1}", new Object[]{Mid, status})).request().post(null);
    }

    public <T> T getMenuByMid(Class<T> responseType, String Mid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getMenuByMid/{0}", new Object[]{Mid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getMenuByRestaurant(Class<T> responseType, String Rid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getMenuByRid/{0}", new Object[]{Rid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateMenuNotAvailable(String Mid, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("NotAvalilavleStatus/{0}/{1}", new Object[]{Mid, status})).request().post(null);
    }

    public void updateStatusOnProccess(String oid, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("onProcessStatus/{0}/{1}", new Object[]{oid, status})).request().post(null);
    }

    public <T> T getAllOrderDetails(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllOrders");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteRestaurantById(String Rid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteRestaurant/{0}", new Object[]{Rid})).request().delete();
    }

    public <T> T searchUserByEmail(Class<T> responseType, String Email) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserByEmail/{0}", new Object[]{Email}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateStatusDelivered(String oid, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateStatusDelivered/{0}/{1}", new Object[]{oid, status})).request().post(null);
    }

    public void AddRestaurant(Object requestEntity, String Email, String Name, String Address, String Description, String file, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("AddRestaurant/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{Email, Name, Address, Description, file, status})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getRestaurantById(Class<T> responseType, String Rid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getRestaurantById/{0}", new Object[]{Rid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updaeRestaurant(Object requestEntity, String Rid, String Email, String Name, String Address, String Description, String file, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateRestaurant/{0}/{1}/{2}/{3}/{4}/{5}/{6}", new Object[]{Rid, Email, Name, Address, Description, file, status})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void updateUserByEmail(String Email, String Gid, String Name, String phone) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateUser/{0}/{1}/{2}/{3}", new Object[]{Email, Gid, Name, phone})).request().post(null);
    }

    public void updateStatusOnPending(String oid, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateStatusPending/{0}/{1}", new Object[]{oid, status})).request().post(null);
    }

    public void AddMenu(Object requestEntity, String Rid, String Name, String Address, String Description, String file, String Price, String Qty, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("AddMenu/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{Rid, Name, Address, Description, file, Price, Qty, status})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void updaeMenu(Object requestEntity, String Mid, String Rid, String Name, String Description, String file, String Price, String Qty, String status) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateMenu/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{Mid, Rid, Name, Description, file, Price, Qty, status})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public String updatePasswordByEmail(String Email, String Password) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("updatePasswordByEmail/{0}/{1}", new Object[]{Email, Password})).request().post(null, String.class);
    }

    public void close() {
        client.close();
    }
    
}
