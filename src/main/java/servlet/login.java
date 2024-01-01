/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import ejb.userBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    userBeanLocal ubl;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");
            out.println("</head>");
            out.println("<body>");
//            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Register</title>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">\n"
                    + "\n"
                    + "convert this into the servlet");
            out.println("</head>");
            out.println("<body>");
//            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
            out.println("<form method='POST' action=''>");
            out.println("<div class=\"form-group row\">");
            out.println("<label for=\"inputEmail3\" class=\"col-sm-2 col-form-label\">Email</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<input type=\"email\" class=\"form-control\" id=\"Email\" name=\"Email\" placeholder=\"Email\">");
            out.println("</div>");
            out.println("</div>");

            out.println("<div class=\"form-group row\">");
            out.println("<label for=\"inputPassword3\" class=\"col-sm-2 col-form-label\">Password</label>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<input type=\"text\" class=\"form-control\" id=\"Password\" name=\"Password\" placeholder=\"Password\">");
            out.println("</div>");
            out.println("</div>");

            out.println("<div class=\"form-group row\">");
            out.println("<div class=\"col-sm-2\">Checkbox</div>");
            out.println("<div class=\"col-sm-10\">");
            out.println("<div class=\"form-check\">");
            out.println("<input class=\"form-check-input\" type=\"checkbox\" id=\"gridCheck1\">");
            out.println("<label class=\"form-check-label\" for=\"gridCheck1\">Example checkbox</label>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");

            out.println("<div class=\"form-group row\">");
            out.println("<div class=\"col-sm-10\">");
            out.println("<button type=\"submit\" class=\"btn btn-primary\">Sign u</button>");
            out.println("</div>");
            out.println("</div>");
            out.println("</form>");

            out.println("</body>");
            out.println("</html>");
            out.println("</body>");
            out.println("</html>");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String Email = request.getParameter("Email");
        String Password = request.getParameter("Password");

//        out.println("<h1>"+ubl.Login(Email, Password)+"</h1>");
//        String role = ubl.Login(Email, Password);
//        if (role != null) {
//            HttpSession session = request.getSession(true); // Create a new session if one doesn't exist
//            session.setAttribute("Email", Email);
//            if ("Admin".equals(role)) {
//                // Redirect to admin page
//                response.sendRedirect("admin");
//            } else if ("Manager".equals(role)) {
//                // Redirect to manager page
//                response.sendRedirect("manager");
//            } else {
//                // Redirect to user page
//                response.sendRedirect("UHome.jsp");
//            }
//        } else {
//            // Authentication failed
//            response.sendRedirect("/login?error=1");
//        }

//        if (gid != null) {
//            if (gid.equals(1)) {
//                response.sendRedirect("admin");
//            } else if (gid.equals(2)) {
//                response.sendRedirect("Manager");
//            } else if (gid.equals(3)) {
//                response.sendRedirect("User");
//            }
//        } else {
//            response.sendRedirect("Error");
//        }
//        int Gid = Integer.parseInt(request.getParameter("Gid"));
//        ubl.Login(Email, Password);
//        out.println("<h1>" + ubl.Login(Email, Password) + "</h1>");
//        GroupMstr gid = ubl.Login(Email, Password);
//        if(gid.equals(1))
//        {
//            response.sendRedirect("Admin");
//        }else if(gid.equals(2))
//        {
//            response.sendRedirect("Manager");
//        }else if(gid.equals(3))
//        {
//            response.sendRedirect("User");
//        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
