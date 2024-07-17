package com.kosta.action;

import com.kosta.dao.GoodsDAO;
import com.kosta.vo.GoodsVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateGoodsAction implements KostaAction {
    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GoodsDAO dao = new GoodsDAO();

        int no = Integer.parseInt(request.getParameter("no"));

        GoodsVO g = dao.findByNo(no);

        String ext = g.getFname().split("\\.")[1]; //=> 파일의 확장자만 가져옵니다.

        ext = ext.toLowerCase(); //=> 대소문자를 모두 처리하기 위해 확장자를 소문자로 만듭니다.
        String isImg = "no";
        if (ext.equals("jpg") || ext.equals("png") || ext.equals("gif") || ext.equals("bmp") || ext.equals("jpeg") || ext.equals("webp")) {
            isImg = "yes";
        }

        request.setAttribute("g", g);

        request.setAttribute("isImg", isImg);

        return "updateGoods.jsp"; // 수정 페이지로 이동
    }
}
