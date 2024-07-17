package com.kosta.action;

import com.kosta.dao.GoodsDAO;
import com.kosta.vo.GoodsVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class UpdateGoodsOKAction implements KostaAction {
    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // images 폴더의 실제 경로를 알아옴
        String path = request.getServletContext().getRealPath("images");
        System.out.println("path = " + path);

        // MultipartRequest 객체 생성하여 파일 업로드 처리
        MultipartRequest multi = new MultipartRequest(
                request,
                path,
                1024 * 1024 * 1024 * 5,
                "UTF-8",
                new DefaultFileRenamePolicy()
        );

        GoodsDAO dao = new GoodsDAO();

        // 상품 번호를 받아옴
        int no;
        try {
            no = Integer.parseInt(request.getParameter("no"));
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid product number format", e);
        }

        // 상품 이름을 받아옴
        String item = multi.getParameter("item");

        // 상품 가격과 수량을 받아옴 (null 또는 빈 값 체크)
        int price;
        int qty;
        try {
            String priceStr = multi.getParameter("price");
            String qtyStr = multi.getParameter("qty");

            if (priceStr == null || priceStr.isEmpty()) {
                throw new ServletException("Price cannot be null or empty");
            }
            if (qtyStr == null || qtyStr.isEmpty()) {
                throw new ServletException("Quantity cannot be null or empty");
            }

            price = Integer.parseInt(priceStr);
            qty = Integer.parseInt(qtyStr);
        } catch (NumberFormatException e) {
            throw new ServletException("Invalid number format for price or quantity", e);
        }

        // 기존 파일 이름을 받아옴
        String oldFname = multi.getParameter("fname");

        // GoodsVO 객체 생성 및 값 설정
        GoodsVO g = new GoodsVO();
        g.setNo(no);
        g.setItem(item);
        g.setPrice(price);
        g.setQty(qty);
        g.setFname(oldFname);

        // 새 파일 이름을 받아옴
        String fname = multi.getOriginalFileName("uploadFile");

        // 업로드한 파일이 있으면 GoodsVO 객체의 파일 이름을 변경
        if (fname != null) {
            File file = new File(path + "/" + fname);
            g.setFname(fname);
        }

        // 데이터베이스 업데이트 요청
        int re = dao.update(g);

        // 업데이트가 성공하고 파일이 수정되었으면 원래 파일을 삭제
        if (re > 0 && fname != null) {
            File file = new File(path + "/" + oldFname);
            if (file.exists()) {
                file.delete();
            }
        }

        // 결과를 request에 설정
        request.setAttribute("re", re);

        // 결과 페이지로 이동
        return "updateGoodsOK.jsp";
    }
}
