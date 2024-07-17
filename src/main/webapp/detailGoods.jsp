<%--
  Created by IntelliJ IDEA.
  User: donggyun
  Date: 2024. 7. 17.
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script>
        window.onload = function () {
            document.querySelector('#btn_delete').onclick = function () {
                let re = confirm('정말로 삭제하시겠습니까?');
                if (re == false) {
                    event.preventDefault();
                    return false;
                }
            };
        };
    </script>
</head>
<body>
    <h2>상품상세</h2>
    <hr>
    상품번호: ${g.no}<br>
    상품이름: ${g.item}<br>
    상품가격: ${g.price}<br>
    상품수량: ${g.qty}<br>
    상품이미지: <br>
    <img src="images/${g.fname}">${g.fname}<br>
    <hr>
    <a href="listGoods.do">상품목록</a>
    <a href="updateGoods.do?no=${g.no}">상품수정</a>
    <a id="btn_delete" href="deleteGoods.do?no=${g.no}">상품삭제</a>
</body>
</html>
