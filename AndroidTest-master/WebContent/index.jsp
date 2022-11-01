<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>welcome</title>
</head>
<body>
<form action='mustLogin' method="post">
<br>
	name:<input type="text" name="logname">
<br>password:<input type="text" name="password">
<br><input type="submit" value="login">
</form>
<form  method="post" action='Register.jsp'>
	<br><input type="submit" value="register">
</form>
<form  method="post" action='Search.jsp'>
	<br><input type="submit" value="search">
</form>
<form  method="post" action='user_page.jsp'>
	<br><input type="submit" value="user_info">
</form>
<form  method="post" action='Practice_by_word.jsp'>
	<br><input type="submit" value="by_word">
</form>
<form  method="post" action='add_good_qusetion.jsp'>
	<br><input type="submit" value="add_good">
</form>
<form  method="post" action='show_good_questions.jsp'>
	<br><input type="submit" value="show_good">
</form>
<form  method="post" action='update_info.jsp'>
	<br><input type="submit" value="update_info">
</form>
<form  method="post" action='delete_good_question.jsp'>
	<br><input type="submit" value="delete_good">
</form>
<form  method="post" action='favourite_search.jsp'>
	<br><input type="submit" value="favourite_search">
</form>
<form  method="post" action='studio.jsp'>
	<br><input type="submit" value="studio">
</form>
<form  method="post" action='Practice_res.jsp'>
	<br><input type="submit" value="Practice_res">
</form>
<form  method="post" action='Practice.jsp'>
	<br><input type="submit" value="Practice">
</form>
</body>
</html>