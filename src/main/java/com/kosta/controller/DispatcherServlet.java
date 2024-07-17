package com.kosta.controller;

// 필요한 클래스들을 import합니다.
import com.kosta.action.KostaAction;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

// @WebServlet 어노테이션을 사용하여 이 서블릿이 *.do 패턴의 모든 요청을 처리하도록 설정합니다.
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    // HashMap을 선언합니다. 이는 요청 URL과 해당하는 액션 클래스를 매핑하는데 사용됩니다.
    HashMap<String, KostaAction> map;

    // init 메서드는 서블릿이 초기화될 때 한 번만 호출됩니다.
    @Override
    public void init(ServletConfig config) throws ServletException {
        // HashMap을 초기화합니다.
        map = new HashMap<String, KostaAction>();
        // WEB-INF 폴더의 실제 경로를 가져옵니다.
        String path = config.getServletContext().getRealPath("WEB-INF");

        try {
            // kosta.properties 파일을 읽기 위한 FileReader 객체를 생성합니다.
            Reader reader = new FileReader(path + "/kosta.properties");
            // Properties 객체를 생성합니다. 이는 키-값 쌍의 설정 정보를 저장하는데 사용됩니다.
            Properties prop = new Properties();
            // reader를 통해 properties 파일의 내용을 로드합니다.
            prop.load(reader);
            // Properties 객체의 모든 키를 가져옵니다.
            Iterator iter = prop.keySet().iterator();
            // 모든 키에 대해 반복합니다.
            while (iter.hasNext()) {
                // 현재 키를 가져옵니다.
                String key = (String) iter.next();
                // 현재 키에 해당하는 값(클래스 이름)을 가져옵니다.
                String clsName = prop.getProperty(key);
                // 클래스 이름을 이용해 해당 클래스의 인스턴스를 생성합니다.
                Object obj = Class.forName(clsName).newInstance();
                // 생성된 인스턴스를 KostaAction 타입으로 캐스팅하여 HashMap에 추가합니다.
                map.put(key, (KostaAction) obj);
            }
            // 사용이 끝난 reader를 닫습니다.
            reader.close();
        } catch (Exception e) {
            // 예외가 발생하면 에러 메시지를 출력합니다.
            System.out.println("예외 발생: " + e.getMessage());
        }
    }

    // HTTP GET과 POST 요청을 모두 처리하는 메서드입니다.
    public void pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 URI를 가져옵니다.
        String uri = request.getRequestURI();
        // URI에서 마지막 '/' 이후의 문자열을 추출하여 명령어로 사용합니다.
        String cmd = uri.substring(uri.lastIndexOf("/") + 1);
        // 해당 명령어에 대응하는 KostaAction 객체를 가져옵니다.
        KostaAction action = null;
        action = map.get(cmd);

        // KostaAction 객체의 pro 메서드를 호출하여 비즈니스 로직을 실행하고, 결과로 뷰 페이지를 받아옵니다.
        String viewPage = action.pro(request, response);

        // 뷰 페이지가 .do로 끝나면 리다이렉트, 그렇지 않으면 포워드합니다.
        if (viewPage.endsWith(".do")) {
            response.sendRedirect(viewPage);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
            dispatcher.forward(request, response);
        }
    }

    // HTTP GET 요청을 처리하는 메서드입니다.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pro(request, response);
    }

    // HTTP POST 요청을 처리하는 메서드입니다.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pro(request, response);
    }
}