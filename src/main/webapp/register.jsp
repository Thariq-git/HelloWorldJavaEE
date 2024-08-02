<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<form action="RegisterServlet" method="Post">
		Name 		: 	<input type="text" 		name="username"><br>
		Password 	: 	<input type="password" 	name="password"><br>
		Email 		: 	<input type="text" 		name="email"><br>
		Contact 	: 	<input type="text" 		name="contact"><br>
		DOB 		: 	<input type="text" 		name="dob"><br>
		Language 	: 	<select 				name="language">
									<option>English</option>
									<option>Tamil</option>
		</select>
		<br><br>
		<input type="submit" value = "Register"/>
		</form>

</body>
</html>