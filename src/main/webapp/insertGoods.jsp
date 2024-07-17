<%--
  Created by IntelliJ IDEA.
  User: donggyun
  Date: 2024. 7. 17.
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>상품등록</h2>
    <hr>
    <form action="insertGoodsOK.do" method="post" enctype="multipart/form-data"> <!-- 파일 업로드도 가능하도록 함 -->
        상품이름: <input type="text" name="item"><br>
        상품가격: <input type="text" name="price"><br>
        상품수량: <input type="text" name="qty"><br>
        상품사진: <input type="file" name="uploadFile"><br>
        <input type="submit" value="등록">
        <input type="reset" value="다시입력">
    </form>

</body>
</html>
