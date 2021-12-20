import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameFrame extends JFrame  {
	GameFrame(Object a) {
		// GamePanel �� this(GameFrame)Ŭ������ add(�߰��Ѵ�)
		//this.add(new GamePanel3());
//		 gameDifficulty();/
		// ������â�� ������ �߰��Ѵ�.
		
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

		// setDefaultCloseOperation �޼ҵ忡 (JFrame.EXIT_ON_CLOSE)�ʵ� ���� ���̸� ������â �����
		// ���μ�������(jvm)����ϰ� ����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// â�� ũ�� ���� ���� ����
		this.setResizable(false);

		// pack()�� �����ӳ��� ����������Ʈ���� ���̾ƿ��� Prefered Size�� �µ��� �������� ����� ���ߴ� �۾��̴�.
		this.pack();

		// â�� ȭ�鿡 ��Ÿ�������� ����
		this.setVisible(true);

		// setLocationRelativeTo(null) ���� null�� ������ ������ â�� ȭ���� ����� ����.
		this.setLocationRelativeTo(null);
		
	}

	
}
