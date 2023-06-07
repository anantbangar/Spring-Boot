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
	<form action="uploadCSV" target="blank" enctype="multipart/form-data" method="post">
		<input type="file" name="file" />
		<input type="submit" value="Upload CSV File" />
	</form>
</body>
</html>