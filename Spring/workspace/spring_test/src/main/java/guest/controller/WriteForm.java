package guest.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WriteForm {
	@RequestMapping(value="/guest/writeForm.do")
	public ModelAndView writeForm(HttpServletRequest request) {
		System.out.println("글 저장 폼");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("display", "../guest/writeForm.jsp");
		modelAndView.setViewName("/main/index.jsp");
		return modelAndView;
		
	}
}
