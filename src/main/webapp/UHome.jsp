<%-- 
    Document   : UHome
    Created on : 11-Oct-2023, 4:54:28 PM
    Author     : HP
--%>

<%@page import="entity.RestaurantMstr"%>
<%@page import="java.util.Collection"%>
<%@page import="ejb.userBeanLocal"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
     <%
            Context context = new InitialContext();
            userBeanLocal ubl = (userBeanLocal) context.lookup("java:global/FoodOrderingSystem/userBean");
            
              session = request.getSession(); // Retrieve the existing session without creating a new one
            if (session != null) {
                String Email = (String) session.getAttribute("Email");
                out.println("<h1>"+Email+"</h1>");
                // Use the 'email' variable as needed
            } else {
                // Session doesn't exist; handle the case where the user is not authenticated
                out.println("session not getting ");
            }
            
            Collection<RestaurantMstr> restaurant = ubl.getApprovedRestaurant();
      %>
    <body class="container">
        <a href="./user/Viewcart.jsp">Cart</a>
        <table class="table" border="1">
            <thead>
                <tr>
                    <!--<th scope="col">id</th>-->
                    <!--<th scope="col">Email</th>-->
                    <th scope="col">Name</th>
                    <th scope="col">Address</th>
                    <th scope="col">Description</th>
                    <th scope="col">Image</th>
                    <!--<th scope="col">Status</th>-->
                    <!--<th scope="col">Approve</th>-->
                    <!-- <th scope="col">Add Address</th> -->
                </tr>
            </thead>
            <tbody>
                <%


                    for(RestaurantMstr r : restaurant)
                    {
//                        RegisterMstr registerMstr = r.getEmail();
                %>
            <tr>
                    
                    
                    <td><%= r.getName()%></td>
                    <td><%= r.getAddress()%></td>
                    <td><%= r.getDescription()%></td>
                    
                    <td><a href="user/ViewFood.jsp?Rid=<%= r.getRid()%>"><img width='200px' height='200px' src='RestaurantImage/<%= r.getImg()%>' alt='not display'/></a></td>
                    <!-- Add other columns if needed -->
                    
                    
                </tr>
                <%
                    }

                %>
            </tbody>
        </table>
    </body>
</html>
