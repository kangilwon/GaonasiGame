import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

public class startGame extends JFrame  implements ActionListener {
	
	JButton btn0 = new JButton();
	JButton btn1 = new JButton();
	Image background=new ImageIcon(("C:\\Users\\eleck\\Desktop\\쌍용\\java1\\Snake\\src\\img\\img\\BIMG.jpg")).getImage();
	ImageIcon gameStart = new ImageIcon("C:\\Users\\eleck\\Desktop\\쌍용\\java1\\Snake\\src\\img\\img\\gameStart-cutout.png");
	ImageIcon gameExit = new ImageIcon("C:\\Users\\eleck\\Desktop\\쌍용\\java1\\Snake\\src\\img\\img\\gameExit-cutout.png");

	public void MakeButton() {

		btn0 = new JButton("시작",gameStart);
		btn1 = new JButton("종료",gameExit);
		btn0.setIcon(gameStart);
		btn1.setIcon(gameExit);
		btn0.addActionListener(this);
		btn1.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton)e.getSource();
		if(b.getText().equals("시작")) {
			this.dispose();
			new GameDifficulty();
		}else if(b.getText().equals("종료")) {
//			JOptionPane.showMessageDialog(null, "게임을 종료합니다.");
			System.exit(0);
		}

	}

	public void paint(Graphics g) {//그리는 함수
//		super.paintComponents(g);
		g.drawImage(background, 0, 0, null);//background를 그려줌
		
}

	
	public void gridLayoutEx() {
		
		this.getContentPane().setLayout(null);
//		this.getContentPane().setBackground(new Color(0,0,0));
		btn0.setBounds(150,100,200,100);
		btn1.setBounds(150,250,200,100);
		this.getContentPane().add(btn0);
		this.getContentPane().add(btn1);
		btn0.setLayout(null);
		btn1.setLayout(null);
		btn0.setBackground(Color.GREEN);
		btn1.setBackground(Color.RED);
	}
	
			public 	startGame(){
				MakeButton();
				gridLayoutEx();
				this.setBounds(100, 100, 500, 500);
				//프레임창에 제목을 추가한다.
				this.setTitle("Snake");
				//setDefaultCloseOperation 메소드에 (JFrame.EXIT_ON_CLOSE)필드 값을 붙이면 윈도우창 종료시 프로세스까지(jvm)깔끔하게 종료
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//창의 크기 조절 가능 유무
				this.setResizable(false);
				//창을 화면에 나타낼것인지 설정
				this.setVisible(true);
				//setLocationRelativeTo(null) 값을 null을 넣으면 윈도우 창을 화면의 가운데에 띄운다.
				this.setLocationRelativeTo(null);
			}
			
			public static void main(String[] args) {
				new startGame();
			}
	}
