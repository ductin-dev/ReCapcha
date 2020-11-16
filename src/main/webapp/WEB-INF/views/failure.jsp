<%--
  Created by IntelliJ IDEA.
  User: doand
  Date: 15/11/2020
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FAIL</title>
</head>
<body>
    Error, detail=${requestScope.log}
    <a href="${pageContext.request.contextPath}/index">CLICK TO BACK</a>
</body>
</html>
