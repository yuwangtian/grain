<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link href="/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body style="position: relative; margin: 0 auto;top: 50px;text-align: center;" id="center">
<div class="navbar navbar-inverse navbar-fixed-top top_nav">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="menu_style hidden-sm" href="/index.do"> 首页</a>
            <c:if test="${loginName != null }">
                <span style="float: right">
                <a class="menu_style hidden-sm" href="/index.do?groupId=${sessionGroupId}"/>
                ${sessionName}
                <a class="menu_style hidden-sm" href="/loginOut.do">退出</a>
               </span>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
