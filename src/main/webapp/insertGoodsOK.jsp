<%--
  Created by IntelliJ IDEA.
  User: donggyun
  Date: 2024. 7. 17.
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="${re > 0}">
        상품등록 성공
    </c:if>
    <c:if test="${re <= 0}">
        상품등록 실패
    </c:if>
    <hr>
    <a href="insertGoods.do">상품등록</a>
    <a href="listGoods.do">상품목록</a>
</body>
</html>
