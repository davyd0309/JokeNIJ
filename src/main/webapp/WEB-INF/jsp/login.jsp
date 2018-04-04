<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<head>
<meta charset="UTF-8">
</head>
<body>
<center>
<h1>Logowanie</h1>

<form id="loginForm" action="/login" method="POST">


            <table border="1" width="30%" cellpadding="3">
                <thead>
                    <tr>
                        <th colspan="2">Login Here</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>E-mail</td>
                        <td><input type="text" name="email" id="email" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" id="password" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Zaloguj" /></td>
                        <td><input type="reset" value="Reset" /></td>
                    </tr>

                </tbody>
            </table>


</form>
</center>

</body>
</html>