<%--
  Created by IntelliJ IDEA.
  User: donggyun
  Date: 2024. 7. 17.
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>상품수정</h2>
    <hr>
    <form action="updateGoodsOK.do" method="post" enctype="multipart/form-data">
        <input type="hidden" name="no" value="${g.no}">
        상품이름: <input type="text" name="item" value="${g.item}"><br>
        상품가격: <input type="text" name="price" value="${g.price}"><br>
        상품수량: <input type="text" name="qty" value="${g.qty}"><br>
        상품사진: <br>
<%--        <img src="images/${g.fname}"><br>--%>
        <c:if test="${isImg == 'yes'}">
            <img src="images/${g.fname}">
        </c:if>
        <c:if test="${isImg == 'yes'}">
            첨부파일: ${g.fname}<br>
        </c:if>
        <hr>
        <input type="hidden" name="fname" value="${g.fname}">
        첨부파일: <input type="file" name="uploadFile">
        <input type="submit" value="수정">
        <input type="reset" value="다시입력">
    </form>
</body>
</html>
