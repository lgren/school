<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<body>
<form action="${pageContext.request.contextPath}/insertStudent.do" method="post">
    <label>用户名:<input type="text" name="username" value="${student.getUsername()}"></label>
    <label>密码:<input type="password" name="password" value="${student.getPassword()}"></label>
    <label>姓名:<input type="text" name="realName" value="${student.getRealName()}"></label>
    <label>生日:<input type="date" name="birthday" value="${student.getBirthdayStr()}"></label>
    <input type="submit" value="确认" style="margin-right: 10px">
    <input type="reset" value="重置">
</form>

<script src="${pageContext.request.contextPath}/webjars/jquery/3.1.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
