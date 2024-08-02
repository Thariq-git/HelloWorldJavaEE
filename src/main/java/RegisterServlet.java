import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Initialize a PrintWriter object to return the html values via the response
		PrintWriter out = response.getWriter();
		
		//retrieve the four parameters from the request from the web form
		String u = request.getParameter("username");
		String p = request.getParameter("password");
		String e = request.getParameter("email");
		String c = request.getParameter("contact");
		String d = request.getParameter("dob");
		String l = request.getParameter("language");
		
		//attempt connection to database using JDBC, you can change the username and password
		//accordingly using the phpMyAdmin > User Account dashboard
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/userdetails", "root", "password");

		//implement the sql query using prepared statement
			PreparedStatement ps = con.prepareStatement("insert into UserDetails values(?,?,?,?,?,?)");
		
		//parse in the data retrieved from the web form request into the prepared statement
		//accordingly
			ps.setString(1, u);
			ps.setString(2, p);
			ps.setString(3, e);
			ps.setString(4, c);
			ps.setString(5, d);
			ps.setString(6, l);
		
		//perform the query on the database using the prepared statement
			int i = ps.executeUpdate();
			
		//check if the query had been successfully execute, return “You are successfully
		//registered” via the response,
			if (i > 0){
				PrintWriter writer = response.getWriter();
				writer.println("<h1>" + "You have successfully registered an account!" +
				"</h1>");
				writer.close();
				}
		}
		
		//catch and print out any exception
			catch (Exception exception) 
			{
			System.out.println(exception);
			out.close();
			}		
		doGet(request, response);
	}
	
	

}
