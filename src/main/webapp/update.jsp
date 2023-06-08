<%--
  Created by IntelliJ IDEA.
  User: Hieu's PC
  Date: 6/8/2023
  Time: 9:41 AM
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
<h1>Update employee</h1>
<form method="post">
    <p>Name</p>
    <input type="text" name="name" value="${employee.name}">
    <p>Email</p>
    <input type="text" name="email" value="${employee.email}">
    <p>Address</p>
    <input type="text" name="address" value="${employee.address}">
    <p>Phone number</p>
    <input type="text" name="phone" value="${employee.phone}">
    <p>Salary</p>
    <input type="text" name="salary" value="${employee.salary}">
    <p>Department</p>
    <select name="departmentId">
        <c:forEach items="${departmentList}" var="d">
            <option value="${d.id}">${d.name}</option>
        </c:forEach>
    </select>
    <br>
    <button class="btn btn-info" type="submit">Submit</button>
</form>
</body>
</html>
