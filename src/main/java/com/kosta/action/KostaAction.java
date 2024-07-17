package com.kosta.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface KostaAction {
    // 이동할 JSP 이름을 반환하도록 String 타입 사용
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
