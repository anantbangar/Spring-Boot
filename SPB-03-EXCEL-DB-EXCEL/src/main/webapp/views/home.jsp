<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>this is home page</h2>
	
	<form action="downloadEmps">
		<input type="submit" value="Download Excel File" />
	</form>
	
	<br><br>
	
	<form action="/emps/showAllEmp" target="blank">
		<input type="submit" value="Show all Emps" />
	</form>
	
	<br><br>
	
	<form action="/emps/upload" target="blank" method="post" enctype="multipart/form-data">
		<input type="file" name="file"/>
		<input type="submit" value="Upload Excel File" />
	</form>
</body>
</html>