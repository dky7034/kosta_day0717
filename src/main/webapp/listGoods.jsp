<%--
  Created by IntelliJ IDEA.
  User: donggyun
  Date: 2024. 7. 17.
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>상품목록</h2>
    <a href="insertGoods.do">상품등록</a>
    <hr>
    <table border="1" width="80%">
        <tr>
            <th>상품번호</th>
            <th>상품이름</th>
            <th>상품가격</th>
            <th>상품수량</th>
            <th>첨부파일</th>
        </tr>
        <c:forEach var="g" items="${list}">
            <tr>
                <td>${g.no}</td>
                <td><a href="detailGoods.do?no=${g.no}">${g.item}</a></td>
                <td>${g.price}</td>
                <td>${g.qty}</td>
                <td>${g.fname}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
