<%--
  Created by IntelliJ IDEA.
  User: donggyun
  Date: 2024. 7. 17.
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>상품 수정 결과 페이지</h2>
    <hr>
    <c:if test="${re > 0}">
        상품 수정에 성공하였습니다.
    </c:if>
    <c:if test="${re <= 0}">
        상품 수정에 실패하였습니다.
    </c:if>
    <a href="listGoods.do">상품목록</a>
</body>
</html>
