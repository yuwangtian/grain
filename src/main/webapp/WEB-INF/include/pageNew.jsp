<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include/taglib.jsp"%>
<nav aria-label="...">
    <ul class="pagination">
        <li class="disabled"><a>${page.pageNum }/${page.pages }(${page.total })</a></li>
        <c:if test="${page.isFirstPage }"><li class="disabled"><a class="page">&laquo;</a></li></c:if>
        <c:if test="${!page.isFirstPage }"><li><a  onclick="clickMothod('${param.url}/1')" class="page">&laquo;</a></li></c:if>
        
        <c:forEach begin="${page.firstPage }" end="${page.lastPage }" var="item">
        <c:if test="${page.pageNum == item }"><li class="active"><a class="page">${item }</a></li> </c:if>
        <c:if test="${page.pageNum != item }"><li><a   onclick="clickMothod('${param.url }/${item}')" class="page">${item }</a></li> </c:if>
        </c:forEach>
        <c:if test="${page.isLastPage }"><li class="disabled"><a class="page">&raquo;</a></li></c:if>
        <c:if test="${!page.isLastPage }"><li><a  onclick="clickMothod('${param.url }/${page.pages}')" class="page">&raquo;</a></li></c:if>
    </ul>
    <script type="text/javascript">
        function  clickMothod(aa){
            var form = $("#from1");
            form.attr("action", aa);
            form.submit();
        }
    </script>
</nav>