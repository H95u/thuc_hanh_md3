<%--
  Created by IntelliJ IDEA.
  User: Hieu's PC
  Date: 6/8/2023
  Time: 9:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
</head>
<body>
<h1 style="text-align: center">Employee Management</h1>
<hr>
<a href="/home?action=create" class="btn btn-primary">Add new employee</a>
<form action="/home?action=search" method="post">
    <input name="nameSearch" type="text" placeholder="Search..." style="float: right">
    <button type="submit" class="btn btn-info" style="float: right">Search</button>
</form>
<table class="table" style="margin-top: 100px">
    <thead>
    <tr>
        <th scope="col">id</th>
        <th scope="col">Name</th>
        <th scope="col">Email</th>
        <th scope="col">Address</th>
        <th scope="col">PhoneNumber</th>
        <th scope="col">Salary</th>
        <th scope="col">Department</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="e" items="${employeeList}">
        <tr>
            <th scope="row">${e.id}</th>
            <td>${e.name}</td>
            <td>${e.email}</td>
            <td>${e.address}</td>
            <td>${e.phone}</td>
            <td>${e.salary}</td>
            <td>${e.department.name}</td>
            <td><a href="/home?action=update&id=${e.id}" class="btn btn-success">Edit</a></td>
            <td><a onclick="deleteE(${e.id})" class="btn btn-danger">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
<script>
    function deleteE(id) {
        if (confirm("Sure?")) {
            window.location.href = "/home?action=delete&id=" + id
        }
    }
</script>
</html>
