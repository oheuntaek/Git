package test01;

import org.springframework.context.support.GenericXmlApplicationContext;



public class JdbcExample2 {
	public static void main(String[] args) {
		GenericXmlApplicationContext context = 
			new GenericXmlApplicationContext("applicationContext.xml");
		
		GoodsService service = (GoodsService) context.getBean("goodsService");
		
		GoodsVO vo = new GoodsVO();
		vo.setCode("p0001");
		
		int result = service.deleteGoods(vo);
		if(result > 0) {
			System.out.println(vo.getCode() + "교재가 삭제되었습니다.");
		} else {
			System.out.println(vo.getCode() + "교재가 삭제 실패입니다.");
		}
		
		context.close();
	}
}









