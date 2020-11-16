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
    <title>Home</title>
</head>
<body>
    Home
    <form id="demo-form" action="download" method="get">

        <label>NAME<input name="name" type="text"></label>
        <button class="g-recaptcha"
            data-sitekey="6LcZNuMZAAAAAEJJww5UJ23pbPHn1hmqUu3KLFxw"
            data-callback='onSubmit'
            data-action='submit'>Submit</button>
    </form>
    <a href="${pageContext.request.contextPath}/download">CLICK TO GO</a>

    <script src="https://www.google.com/recaptcha/api.js"></script>
    <script>
        function onSubmit(token) {
            document.getElementById("demo-form").submit();
        }
    </script>
</body>
</html>
