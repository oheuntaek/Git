package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import board.bean.BoardDTO;
import board.dao.BoardDAO;
// @Controller: bean 객체 설정 
@Controller
public class BoardListController{
	// @RequestMapping(value="/board/boardList.do"): HandlerMapping 등록
	@RequestMapping(value="/board/boardList.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("글 목록보기");
		// 1) 데이터 처리
    	int pg = 1;
    	if(request.getParameter("pg") != null){
    		pg = Integer.parseInt(request.getParameter("pg"));
    	}
    	// 2) 목록처리 (5줄씩)
    	int endNum = pg*5;
    	int startNum = endNum-4;
    	
    	BoardDAO dao = new BoardDAO();
    	List<BoardDTO> list = dao.boardList(startNum, endNum);
    
    	// 3) 페이징 처리
    	int count = dao.count();	// 총목록수
    	int totalP = (count+4)/5;	// 총 페이지수
    	
    	int startPage = (pg-1)/3*3+1;
    	int endPage = startPage+2;
    	if(endPage > totalP){
    		endPage = totalP;
    	}
    	// 4) 데이터 공유 처리
    	ModelAndView modelAndView = new ModelAndView();
    	// ModelAndView 클래스에 공유데이터 저장
    	modelAndView.addObject("pg", pg);
    	modelAndView.addObject("list", list);
    	modelAndView.addObject("startPage", startPage);
    	modelAndView.addObject("endPage",endPage);
    	modelAndView.addObject("totalP", totalP);
    	// ModelAndView 클래스에 화면네비게이션 정보를 저장
    	modelAndView.setViewName("boardList.jsp");
		/*
		 * request.setAttribute("pg", pg); request.setAttribute("endNum", endNum);
		 * request.setAttribute("startNum", startNum); request.setAttribute("count",
		 * count); request.setAttribute("totalP", totalP);
		 * request.setAttribute("startPage", startPage); request.setAttribute("endPage",
		 * endPage); request.setAttribute("list", list);
		 */
    	// 5) 화면 네비게이션
    	
    	//return "boardList"; // ./boardList.jsp
    	return modelAndView;
	}

}
