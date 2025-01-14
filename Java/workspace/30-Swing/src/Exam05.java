import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

class Swing05 extends JFrame {
	Container container = getContentPane();
	JToggleButton button1 = new JToggleButton("1",true);
	JToggleButton button2 = new JToggleButton("2",false);
	JToggleButton button3 = new JToggleButton("3",false);
	JToggleButton button4 = new JToggleButton("4",false);
	// JToggleButton 들을 묶어주는 클래스
	ButtonGroup buttonGroup = new ButtonGroup();
	
	public Swing05() {
		//JFrame 설정
		this.setTitle("Swing05");
		this.setSize(300, 200);
		this.setLocation(1300, 300);
		init();
		start();
		setVisible(true);
	}

	private void init() {
		container.setLayout(new GridLayout(2, 2));
		container.add(button1);
		container.add(button2);
		container.add(button3);
		container.add(button4);
		// buttonGroup으로 토글버튼 묶기
		buttonGroup.add(button1);
		buttonGroup.add(button2);
		buttonGroup.add(button3);
		buttonGroup.add(button4);
		
		
	}

	private void start() {
		// x버튼 종료 처리
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// button1 이벤트 처리
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(button1.isSelected()); 	// 버튼1이 눌렸을때 true
				System.out.println(buttonGroup.getSelection());
				
			}
		});
		
	}
}
public class Exam05 {

	public static void main(String[] args) {
		Swing05 swing05 = new Swing05();

	}

}
