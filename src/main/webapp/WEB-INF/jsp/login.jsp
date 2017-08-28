<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/include/taglib.jsp" %>

<html>
<head>
    <title>登录 - 禾捆</title>
    <%@include file="/WEB-INF/include/header.jsp"%>
</head>

<body style="position: relative; margin: 0 auto;top: 50px;text-align: center;" id="center">
<div class="col-lg-4 col-lg-offset-4" >
    <div class="wrapper-page">
        <div class="m-t-30 card-box">
            <form class="form-signin" role="form" method="post" action="/login.do">
                <h2 class="form-signin-heading">登录禾捆</h2>
                <div class="error-box">${msg}</div>
                <input class="form-control" placeholder="城市0531大区02小区01小排03" name="userName" required autofocus>
                <div>&nbsp;</div>
                <input type="password" class="form-control" placeholder="密码" name="userPwd" required>
                <div class="checkbox">
                    <label>
                        <%--<input type="checkbox" value="1" name="rememberMe"> 记住登录信息--%>
                    </label>
                </div>
                <div class="row">
                    <div class="col-sm-4 col-xs-8">
                        <button class="btn btn-warning btn-block" type="submit">登录</button>
                    </div>
                    <%--<div class="col-sm-8 col-xs-4">--%>
                        <%--<a href="/resetpwd" class="reset_pwd pull-right">忘记密码？</a>--%>
                    <%--</div>--%>

                </div>
            </form>

        </div>

    </div>
    <!-- end wrapper page -->
</div>

</body>
</html>