import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Sub11 extends Frame {
	Label label1 = new Label("결과 출력");
	Label label2 = new Label("입력 : ", Label.RIGHT);
	TextArea textArea = new TextArea(); // 여러줄 입력, 출력(스크롤 기능O)
	TextField textField = new TextField(); // 한 줄 입력
	Panel panel = new Panel();

	public Sub11() {
		// frame 설정
		setTitle("test");
		setSize(300, 300);
		setLocation(1300, 300);
		init(); // 화면 구성
		start(); // 이벤트 설정
		setVisible(true);
	}

	private void init() {
		// frame layout 설정
		setLayout(new BorderLayout());
		// panel 구성
		panel.setLayout(new BorderLayout());
		panel.add("West", label2);
		panel.add("Center", textField);
		// frame 구성
		add("North", label1);
		add("Center", textArea);
		add("South", panel);
		// TextArea 쓰기 금지
		textArea.setEditable(false);
	}

	private void start() {
		// frame의 x버튼 종료 처리
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				super.windowClosing(e);
			}
		});
		// textfield 이벤트 설정

//		// 람다 표현식을 이욯한 이벤트 처리 1
//		textField.addActionListener((ActionEvent e) ->
//		{
//				String str = textField.getText();
//				if (!textArea.getText().equals("")) {
//					str = textArea.getText() + "\n" + str;
//				}
//		
//				textArea.setText(str);
//				// TextArea 마지막 줄 표시
//				textArea.setCaretPosition(textArea.getText().length());
//				textField.setText("");
//			}
//		);
		
		// 람다 표현식을 이욯한 이벤트 처리 2
		textField.addActionListener((e) -> {							// 명령어가 여러개이기 때문에 중괄호 생략안됨
			String str = textField.getText();
			if (!textArea.getText().equals("")) {
				str = textArea.getText() + "\n" + str;
			}

			textArea.setText(str);
			// TextArea 마지막 줄 표시
			textArea.setCaretPosition(textArea.getText().length());
			textField.setText("");
		});

		// 무명 클래스를 이용한 이벤트 처리
//		textField.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String str = textField.getText();
//				if (!textArea.getText().equals("")) {
//					str = textArea.getText() + "\n" + str;
//				}
//		
//				textArea.setText(str);
//				// TextArea 마지막 줄 표시
//				textArea.setCaretPosition(textArea.getText().length());
//				textField.setText("");
//				
//			}
//		});
//
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		String str = textField.getText();
//		if (!textArea.getText().equals("")) {
//			str = textArea.getText() + "\n" + str;
//		}
//
//		textArea.setText(str);
//		// TextArea 마지막 줄 표시
//		textArea.setCaretPosition(textArea.getText().length());
//		textField.setText("");
//	}

}

public class Exam11 {

	public static void main(String[] args) {
		Sub11 sub11 = new Sub11();

	}

}
