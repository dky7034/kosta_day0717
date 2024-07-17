package com.kosta.action;

import com.kosta.dao.GoodsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListGoodsAction implements KostaAction {
    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GoodsDAO dao = new GoodsDAO();
        request.setAttribute("list", dao.findAll());
        return "listGoods.jsp";
    }
}
