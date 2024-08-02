

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;


/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Add the following database setting within the UserServlet class:

		//Prepare list of variables used for database connections
		private String jdbcURL = "jdbc:mysql://localhost:3306/userdetails";
		private String jdbcUsername = "root";
		private String jdbcPassword = "password";
   
		//Prepare list of SQL prepared statements to perform CRUD to our database
		private static final String INSERT_USERS_SQL = "INSERT INTO UserDetails" + " (username, password, email, contact, dob, language) VALUES " +
		 " (?, ?, ?, ?, ?, ?);";
		private static final String SELECT_USER_BY_ID = "select username,password,email,contact,dob,language from UserDetails where username =?";
		private static final String SELECT_ALL_USERS = "select * from UserDetails ";
		private static final String DELETE_USERS_SQL = "delete from UserDetails where username = ?;";
		private static final String UPDATE_USERS_SQL = "update UserDetails set username = ?,password= ?, email =?,contact =?,dob =?,language =? where username = ?;";

		
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Add the following route controller codes in the doGet method of UserServlet:

				//Depending on the request servlet path, determine the function to invoke using the follow switch statement.
		String action = request.getServletPath();
		try {
		switch (action) {
		case "/UserServlet/delete":
		deleteUser(request, response);
		break;
		case "/UserServlet/edit":
		showEditForm(request, response);
		break;
		case "/UserServlet/update":
		updateUser(request, response);
		break;
		case "/UserServlet/dashboard":
		listUsers(request, response);
		break;
		case "/UserServlet/insert":
		addNewUser(request, response);
		break;
		}
		} catch (SQLException ex) {
		throw new ServletException(ex);
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	//Implement the getConnection method which facilitates connection to the database via JDBC
	protected Connection getConnection() {
	    Connection connection = null;
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return connection;
	}

	//Implement the listUsers method inside the UserServlet to retrieve all users from database

	//listUsers function to connect to the database and retrieve all users records
	private void listUsers(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException, ServletException {
	    List <User> users = new ArrayList <>();
	    try (Connection connection = getConnection();

	         // Step 5.1: Create a statement using connection object
	         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {

	        // Step 5.2: Execute the query or update query
	        ResultSet rs = preparedStatement.executeQuery();

	        // Step 5.3: Process the ResultSet object.
	        while (rs.next()) {
	            String username = rs.getString("username");
	            String password = rs.getString("password");
	            String email = rs.getString("email");
	            int contact = rs.getInt("contact");
	            Date dob = rs.getDate("dob");
	            String language = rs.getString("language");
	            users.add(new User(username, password, email, contact, dob, language));
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }

	    // Step 5.4: Set the users list into the listUsers attribute to be pass to the userManagement.jsp
	    request.setAttribute("listUsers", users);
	    request.getRequestDispatcher("/userManagement.jsp").forward(request, response);
	}
	//method to get parameter, query database for existing user data and redirect to user edit page
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	//get parameter passed in the URL
	String username = request.getParameter("name");
	User existingUser = new User("", "", "", 0, Date.valueOf("2015-03-02"), "");
	// Step 1: Establishing a Connection
	try (Connection connection = getConnection();
	// Step 2:Create a statement using connection object
	PreparedStatement preparedStatement =
	connection.prepareStatement(SELECT_USER_BY_ID);) {
	preparedStatement.setString(1, username);
	// Step 3: Execute the query or update query
	ResultSet rs = preparedStatement.executeQuery();
	// Step 4: Process the ResultSet object
	while (rs.next()) {
	username = rs.getString("username");
	String password = rs.getString("password");
	String email = rs.getString("email");
	int contact = rs.getInt("contact");
	Date dob = rs.getDate("dob");
	String language = rs.getString("language");
	existingUser = new User(username, password, email, contact, dob, language);
	}
	} catch (SQLException e) {
	System.out.println(e.getMessage());
	}
	//Step 5: Set existingUser to request and serve up the userEdit form
	request.setAttribute("user", existingUser);
	request.getRequestDispatcher("/userEdit.jsp").forward(request, response);
	}

	//method to update the user table base on the form data
	private void updateUser(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException {
	//Step 1: Retrieve value from the request
	String oriName = request.getParameter("oriName");
	String username = request.getParameter("userName");
	String password = request.getParameter("password");
	String email = request.getParameter("email");
	int contact = Integer.parseInt(request.getParameter("contact"));
	Date dob = Date.valueOf(request.getParameter("dob"));
	String language = request.getParameter("language");
	//Step 2: Attempt connection with database and execute update user SQL query
	try {
			Connection connection = getConnection(); 
			PreparedStatement statement =
	connection.prepareStatement(UPDATE_USERS_SQL); 
	statement.setString(1, username);
	statement.setString(2, password);
	statement.setString(3, email);
	statement.setInt(4, contact);
	statement.setDate(5, dob);
	statement.setString(6, language);
	statement.setString(7, oriName);
	int i = statement.executeUpdate();
	response.sendRedirect("http://localhost:8090/HelloWorldJavaEE/UserServlet/dashboard");
	}catch (SQLException e) {
		System.out.println(e.getMessage());
	//Step 3: redirect back to UserServlet (note: remember to change the url to your project name)
	
	}}

	//method to delete user
	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException {
	//Step 1: Retrieve value from the request
	String username = request.getParameter("name");
	//Step 2: Attempt connection with database and execute delete user SQL query
	try (Connection connection = getConnection(); PreparedStatement statement =
	connection.prepareStatement(DELETE_USERS_SQL);) {
	statement.setString(1, username);
	int i = statement.executeUpdate();
	}
	//Step 3: redirect back to UserServlet dashboard (note: remember to change the url to your project name)
	response.sendRedirect("http://localhost:8090/HelloWorldJavaEE/UserServlet/dashboard");
	}
	//Method 4: addNewUser (trigger RegisterServlet)
	//method to trigger RegisterServlet
	private void addNewUser(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException, ServletException {
	RequestDispatcher rd = null;
	rd = getServletContext().getRequestDispatcher("/RegisterServlet");
	rd.include(request, response);
	}

}