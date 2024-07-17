package com.kosta.action;

import com.kosta.dao.GoodsDAO;
import com.kosta.vo.GoodsVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InsertGoodsOKAction implements KostaAction {
    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // images 폴더의 실제 경로를 알아옴
        String path = request.getServletContext().getRealPath("images");
        System.out.println("path = " + path);
        
        MultipartRequest multi = new MultipartRequest(
                request, path, 1024*1024*1024*5, "UTF-8", new DefaultFileRenamePolicy());

        GoodsVO g = new GoodsVO();
        g.setItem(multi.getParameter("item"));
        g.setPrice(Integer.parseInt(multi.getParameter("price")));
        g.setQty(Integer.parseInt(multi.getParameter("qty")));
        g.setFname(multi.getOriginalFileName("uploadFile"));

        GoodsDAO dao = new GoodsDAO();
        int re = dao.insert(g);

        String viewPage = "";
        if (re > 0) {
            viewPage = "listGoods.do";
        } else {
            request.setAttribute("msg", "상품 등록에 실패하였습니다");
            viewPage = "error.jsp";
        }
//        request.setAttribute("re", re);

        // 이동할 view page 반환
//        return "insertGoodsOK.jsp";
        return viewPage;
    }
}
