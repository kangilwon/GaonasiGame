import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameFrame extends JFrame  {
	GameFrame(Object a) {
		// GamePanel 을 this(GameFrame)클레스에 add(추가한다)
		//this.add(new GamePanel3());
//		 gameDifficulty();/
		// 프레임창에 제목을 추가한다.
		
		//easy
		//normal
		//hard
		//hell
		if (a=="EASY") {
			this.dispose();
			this.add(new GamePanel());
		}else if (a=="NORMAL") {
			this.dispose();
			this.add(new GamePanel2());
		}else if (a=="HARD") {
			this.dispose();
			this.add(new GamePanel3());
		}else if (a=="HELL") {
			this.dispose();
			this.add(new GamePanel4());
		}
		
		this.setTitle("Snake");

		// setDefaultCloseOperation 메소드에 (JFrame.EXIT_ON_CLOSE)필드 값을 붙이면 윈도우창 종료시
		// 프로세스까지(jvm)깔끔하게 종료
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 창의 크기 조절 가능 유무
		this.setResizable(false);

		// pack()은 프레임내에 서브컴포넌트들의 레이아웃과 Prefered Size에 맞도록 윈도우의 사이즈를 맞추는 작업이다.
		this.pack();

		// 창을 화면에 나타낼것인지 설정
		this.setVisible(true);

		// setLocationRelativeTo(null) 값을 null을 넣으면 윈도우 창을 화면의 가운데에 띄운다.
		this.setLocationRelativeTo(null);
		
	}

	
}
