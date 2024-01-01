/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package Client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:Rest [RestApp]<br>
 * USAGE:
 * <pre>
 *        RestClient client = new RestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author HP
 */
public class RestClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/FoodOrderingSystem/resources";

    public RestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("RestApp");
    }

    public void AddAddress(String Email, String State, String City, String Country) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("AddAddress/{0}/{1}/{2}/{3}", new Object[]{Email, State, City, Country})).request().post(null);
    }

    public String AddRegister(String Email, String Gid, String Name, String Password, String phone, String State, String City, String Country) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addRegister/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{Email, Gid, Name, Password, phone, State, City, Country})).request().post(null, String.class);
    }

    public <T> T SerachEmail(Class<T> responseType, String Email) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("SearchEmail/{0}", new Object[]{Email}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
