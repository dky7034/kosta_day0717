package com.kosta.action;

import com.kosta.dao.GoodsDAO;
import com.kosta.vo.GoodsVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DetailGoodsAction implements KostaAction {
    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GoodsDAO dao = new GoodsDAO();
        int no = Integer.parseInt(request.getParameter("no"));
        GoodsVO g = dao.findByNo(no);
        request.setAttribute("g", g);
        return "detailGoods.jsp";
    }
}
