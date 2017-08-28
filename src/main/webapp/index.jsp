<%@ page import="java.util.Properties" %>
<%@ page import="com.grain.utils.PropertiesUtil" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/24 0024
  Time: 下午 6:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
     Properties properties = PropertiesUtil
            .loadProperties("/config.properties");

     String REDIRECT_URL = properties
            .getProperty("redirect_url");
%>
<html>
<head>
    <title>禾捆</title>
</head>
<body>
<script>
    window.location.href="<%=REDIRECT_URL%>"+"/index.do";
</script>
</body>
</html>
