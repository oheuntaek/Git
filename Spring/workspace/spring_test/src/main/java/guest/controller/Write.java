package guest.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import guest.bean.GuestDTO;
import guest.dao.GuestDAO;
@Controller
public class Write {
	@Autowired
	private GuestService Service;
	@RequestMapping(value="/guest/write.do")
	public ModelAndView write(HttpServletRequest request) {
		System.out.println("글 저장");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		GuestDTO dto = new GuestDTO();
		dto.setContent(content);
		dto.setEmail(email);
		dto.setName(name);
		
		dto.setSubject(subject);
		
		int su = Service.input(dto);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("su", su);
		modelAndView.addObject("display", "../guest/write.jsp");
		modelAndView.setViewName("/main/index.jsp");
		return modelAndView;
		
	}
}
