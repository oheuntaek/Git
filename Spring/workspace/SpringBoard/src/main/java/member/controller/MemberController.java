package member.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import member.bean.MemberDTO;
import member.dao.MemberDAO;

public class MemberController {
 
	@RequestMapping(value="../member/writeForm.do")
	public ModelAndView writeForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("../member/writeForm.jsp");
		return modelAndView;
	}
	@RequestMapping(value="../member/write.do")
	public ModelAndView write(HttpServletRequest request) {
		// 데이터 처리
		// post 방식에서의 한글 처리
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String gender = request.getParameter("gender");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String addr = request.getParameter("addr");
		
		// DB 처리
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setName(name);
		memberDTO.setId(id);
		memberDTO.setPwd(pwd);
		memberDTO.setGender(gender);
		memberDTO.setEmail1(email1);
		memberDTO.setEmail2(email2);
		memberDTO.setTel1(tel1);
		memberDTO.setTel2(tel2);
		memberDTO.setTel3(tel3);
		memberDTO.setAddr(addr);
		
		MemberDAO memberDAO = new MemberDAO();
		int su = memberDAO.write(memberDTO);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("../member/write.jsp");
		modelAndView.addObject("su", su);
		return modelAndView;
	}
	@RequestMapping(value="../member/login.do")
	public ModelAndView writeForm(HttpServletRequest request, HttpServletResponse response) {
		// 데이터 처리
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		MemberDAO memberDAO = new MemberDAO();
		String name = memberDAO.login(id, pwd);	
		
		if(name != null) { 	
			/* 페이지 이동
				get 방식으로 이동하기 때문에 이름과 id가 주소창에 오픈된다. 보안에 취약
				그래서 쿠키나 세션으로 보내야 함(다음 장에서 할 것임).
			*/
			//response.sendRedirect("loginOk.jsp?name="
			//		+ URLEncoder.encode(name, "utf-8") + "&id=" + id);
			
			/* 데이터 공유 1 : 쿠키 이용 */
	/*		Cookie cookie1 = new Cookie("memName", 
					URLEncoder.encode(name, "UTF-8"));
			cookie1.setMaxAge(120);		// 120초 설정
			Cookie cookie2 = new Cookie("memId", id);
			cookie2.setMaxAge(120);		// 120초 설정
			
			response.addCookie(cookie1);
			response.addCookie(cookie2);
	*/		
			/* 데이터 공유 2 : 세션 이용 */
			HttpSession session = request.getSession();
			session.setAttribute("memName", name);
			session.setAttribute("memId", id);
			
			try {
				response.sendRedirect("loginOk.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else { 
			try {
				response.sendRedirect("loginFail.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("../member/writeForm.jsp");
		return modelAndView;
	}
}
