package test.main;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import test.bean.ScoreVO;
import test.service.ScoreService;
@Component
public class HelloSpring2 {
	Scanner sc = new Scanner(System.in);
	@Autowired
	private ScoreService scoreService;
	
	public void menu() {
		int num = 0;
		while (true) {
			do {
				System.out.println();
				System.out.println("1. 입력");
				System.out.println("2. 출력");
				System.out.println("3. 수정");
				System.out.println("4. 삭제");
				System.out.println("5. 종료");
				System.out.println("-------------");
				System.out.print("번호입력 : ");
				num = sc.nextInt();
			} while(num>5 || num < 1); // 1~5까지 입력받음
			switch (num) {
			case 1:
				score_insert();
				break;
			case 2:
				score_list();
				break;
			case 3:
				score_modify();
				break;
			case 4:
				score_delete();
				break;
			case 5:
				System.out.println();
				System.out.println("프로그램 종료!!");
				System.out.println();
				return;
			}
		}
	}
	public void score_insert() {
		ScoreVO scoreVO = new ScoreVO();
		System.out.println();
		System.out.print("학번 : ");
		scoreVO.setStudNo(sc.next());
		System.out.print("이름 : ");
		scoreVO.setName(sc.next());
		System.out.print("국어 : ");
		scoreVO.setKor(sc.nextInt());
		System.out.print("영어 : ");
		scoreVO.setEng(sc.nextInt());
		System.out.print("수학 : ");
		scoreVO.setMat(sc.nextInt());
		int tot = scoreVO.getKor() +scoreVO.getEng() + scoreVO.getMat();
		scoreVO.setTot(tot);
		scoreVO.setAvg((double)tot/3);
		
		int result = scoreService.insertScore(scoreVO);
		if(result > 0) {
			System.out.println("저장 성공");
		} else {
			System.out.println("저장 실패");
		}
	}
	public void score_modify() {
		ScoreVO scoreVO = new ScoreVO();
		System.out.println();
		System.out.print("수정할 학번 : ");
		scoreVO.setStudNo(sc.next());
		System.out.print("이름 : ");
		scoreVO.setName(sc.next());
		System.out.print("국어 : ");
		scoreVO.setKor(sc.nextInt());
		System.out.print("영어 : ");
		scoreVO.setEng(sc.nextInt());
		System.out.print("수학 : ");
		scoreVO.setMat(sc.nextInt());
		int tot = scoreVO.getKor() +scoreVO.getEng() + scoreVO.getMat();
		scoreVO.setTot(tot);
		scoreVO.setAvg((double)tot/3);
		
		int result = scoreService.updateScore(scoreVO);
		if(result > 0) {
			System.out.println("수정 성공");
		} else {
			System.out.println("수정 실패");
		}
	}
	public void score_delete() {
		ScoreVO scoreVO = new ScoreVO();
		System.out.println();
		System.out.print("삭제할 학번 : ");
		scoreVO.setStudNo(sc.next());
		int result = scoreService.deleteScore(scoreVO);
		if(result > 0) {
			System.out.println(scoreVO.getStudNo() + "삭제 성공");
		} else {
			System.out.println(scoreVO.getStudNo() + "삭제 실패");
		}
	}	
	
	public void score_list() {
		List<ScoreVO> list = scoreService.listScore();
		String resultTitle = "학번\t이름\t국어\t영어\t수학\t총점\t평균\t입력일";
		System.out.println(resultTitle);
		for(ScoreVO vo : list) {
			System.out.println(vo.toString());
		}
	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext context =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		HelloSpring2 helloSpring2 = (HelloSpring2) context.getBean("helloSpring2");
		helloSpring2.menu();
		context.close();
	}

}
