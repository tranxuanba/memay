<%--
  Created by IntelliJ IDEA.
  User: ishopjapan
  Date: 25/08/2022
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MotorCycle List</title>
    <style>
        *{
            background-color: azure;
        }
        img{
            width: 120px;
            height: 80px;
            padding: 0px;
        }
    </style>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">

</head>
<body>
<center>
    <h1>MotorCycle List</h1>
    <h2>
        <a href="/motors?action=create">Add New MotorCycle</a>
    </h2>
</center>
<div align="center">
    <table border="2" cellpadding="5" style="background-color: aqua" class="table">
        <caption><h2>List of MotorCycle</h2></caption>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Price</th>
            <th scope="col">Manufacturer</th>
            <th scope="col">Image</th>
            <th scope="col">EditMotor</th>
            <th scope="col">DeleteMotor</th>
        </tr>
        <c:forEach items="${listMotorCycle}" var="motor">
            <tr>
                <td>${motor.id}</td>
                <td>${motor.name}</td>
                <td>${motor.price}</td>
                <td>${motor.manufacturer}</td>
                <td><img src="${motor.image}" alt="khong co anh"></td>
                <td><a href="/motors?action=edit&id=${motor.id}">Edit</a></td>
                <td><a href="/motors?action=delete&id=${motor.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
