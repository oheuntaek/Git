package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import member.dao.MemberDAO;

// @Controller : @Controller + Controller 인터페이스

@Controller
public class LoginController{
	@RequestMapping(value="/member/login.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그인 처리");
		// 1) 사용자 입력 정보 추출
    	String id = request.getParameter("id");
    	String pwd = request.getParameter("pwd");
    	// 2) DB 처리
    	MemberDAO dao = new MemberDAO();
    	String name = dao.login(id, pwd);
    	// 3) 화면 네비게이션
    	// ModelAndView 클래스 = 공유데이터 + 화면네비게이션 정보를 저장
    	ModelAndView modelAndView = new ModelAndView();
    	
    	if(name != null){	// log on 상태
    		HttpSession session = request.getSession();
    		session.setAttribute("memId", id);
    		session.setAttribute("memName", name);
    		//sendRedirect 방식
    		modelAndView.setViewName("redirect:../board/boardList.do?pg=1");
    		//response.sendRedirect("../board/boardList.jsp?pg=1");
    	} else {			// log off 상태
    		// forward방식: 뒤에 .jsp를 붙인다
    		modelAndView.setViewName("loginForm.jsp"); //loginForm.jsp
    		//response.sendRedirect("loginForm.jsp");
    	}
		//return viewPage;
    	return modelAndView;
	}

}
