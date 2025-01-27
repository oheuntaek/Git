package test4;

import java.util.Scanner;

import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloSpring {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext("test4/applicationContext.xml");
		SungjukDTO dto = new SungjukDTO();
		SungjukImpl impl = new SungjukImpl(dto);
		impl= (SungjukImpl) context.getBean("impl");
		impl.display();
		System.out.println();
		impl.modify();
		System.out.println();
		impl.display();
		context.close();
	}

}
