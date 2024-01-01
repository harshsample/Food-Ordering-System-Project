/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package Client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:RestUser [RestUser]<br>
 * USAGE:
 * <pre>
 *        RestClientUser client = new RestClientUser();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author HP
 */
public class RestClientUser {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/FoodOrderingSystem/resources";

    public RestClientUser() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("RestUser");
    }

    public void deleteCartById(String Cid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteCart/{0}", new Object[]{Cid})).request().delete();
    }

    public <T> T getFourRestaurant(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getFourApprove");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addFeedback(String Email, String Description, String Date) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addFeedback/{0}/{1}/{2}", new Object[]{Email, Description, Date})).request().post(null);
    }

    public <T> T getApprovedRestaurant(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getApproveRestaurant");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T countCartByEmail(Class<T> responseType, String Email) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("count/{0}", new Object[]{Email}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getMenuByRestaurantAndStatus(Class<T> responseType, String Rid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getMenuByRidAvailable/{0}", new Object[]{Rid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T searchUserByEmail(Class<T> responseType, String Email) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserByEmail/{0}", new Object[]{Email}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void processPaymentForCurrentUser(String Email, String dop, String status, String pType) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("ProcessPayment/{0}/{1}/{2}/{3}", new Object[]{Email, dop, status, pType})).request().post(null);
    }

    public <T> T getThreeRestaurant(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getThreeApprove");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void AddCart(String Email, String Mid, String Qty, String Price, String Added_at) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addToCart/{0}/{1}/{2}/{3}/{4}", new Object[]{Email, Mid, Qty, Price, Added_at})).request().post(null);
    }

    public <T> T getCartByEmail(Class<T> responseType, String Email) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getCartByEmail/{0}", new Object[]{Email}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateUserByEmail(String Email, String Gid, String Name, String phone) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateUser/{0}/{1}/{2}/{3}", new Object[]{Email, Gid, Name, phone})).request().post(null);
    }

    public String updatePasswordByEmail(String Email, String Password) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("updatePasswordByEmail/{0}/{1}", new Object[]{Email, Password})).request().post(null, String.class);
    }

    public void close() {
        client.close();
    }
    
}
