package com.kosta.action;

import com.kosta.dao.GoodsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DeleteGoodsAction implements KostaAction {
    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int no = Integer.parseInt(request.getParameter("no"));
        GoodsDAO dao = new GoodsDAO();
        String fname = dao.findByNo(no).getFname();

        String viewPage = "";

        int re = dao.delete(no);
        if (re > 0) {
            viewPage = "listGoods.do";
            String path = request.getServletContext().getRealPath("images");
            File file = new File(path + "/" + fname);
            file.delete();
        } else {
            request.setAttribute("msg", "상품 삭제에 실패하였습니다.");
            viewPage = "error.jsp";
        }

        return viewPage;
    }
}
