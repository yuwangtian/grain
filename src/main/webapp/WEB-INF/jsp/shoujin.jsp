<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/include/taglib.jsp" %>

<!DOCTYPE>
<html>
<head>
    <title>人员列表</title>
    <%@include file="/WEB-INF/include/header.jsp"%>
</head>

<body>
<%--查询框--%>

<div class="container-fluid main-content">



    <table class="lord_table" border="1" >
        <tr>
            <td colspan="3" style="background-color: darkorange">【${loginName}】${typeName}</td>
        </tr>
        <tr>
            <td>序号</td>
            <td>姓名</td>
            <td>受浸</td>
        </tr>
        <c:forEach items="${userBoList}" var="user" varStatus="stat">

            <tr>
                <td>${stat.index + 1}</td>
                <td>${user.name}</td>
                <td>
                    <c:if test="${user.shoujin_flag==1}">
                        <input name="shoujin_flag"  value="${user.user_id}" onclick="shoujin_flag_checkbox(this)" type="checkbox" checked />
                    </c:if>

                    <c:if test="${user.shoujin_flag!=1}">
                        <input name="shoujin_flag"  value="${user.user_id}"  onclick="shoujin_flag_checkbox(this)"  type="checkbox"  />
                    </c:if>
                </td>

            </tr>
        </c:forEach>
    </table>
    <br/>
</div>
<script>
    function shoujin_flag_checkbox(lord_flag_checkbox){
        var user_id=lord_flag_checkbox.value;
        var isShoujin=lord_flag_checkbox.checked;
        var type="new_saits_total_num";
        var url="/shoujin.do?user_id="+user_id+"&isShoujin="+isShoujin+"&type="+type;
        $.ajax({
            url:url,
            type:'POST',
            async: true,
            success: function( status) {
                alert(status);
            }
        });
    }
</script>
</body>
</html>
