import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GameDifficulty extends JFrame implements ActionListener {
//////////////////////////////////////////////////////////////////////////////////////
	JButton easybt = new JButton();
	JButton normalbt = new JButton();
	JButton hardbt = new JButton();
	JButton hellbt = new JButton();
	Image background=new ImageIcon(("C:\\Users\\eleck\\Desktop\\쌍용\\java1\\Snake\\src\\img\\img\\BIMG.jpg")).getImage();
	ImageIcon EASY = new ImageIcon("C:\\Users\\eleck\\Desktop\\쌍용\\java1\\Snake\\src\\img\\img\\easy.png");
	ImageIcon NORMAL = new ImageIcon("C:\\Users\\eleck\\Desktop\\쌍용\\java1\\Snake\\src\\img\\img\\normal.png");
	ImageIcon HARD = new ImageIcon("C:\\Users\\eleck\\Desktop\\쌍용\\java1\\Snake\\src\\img\\img\\hard.png");
	ImageIcon HELL = new ImageIcon("C:\\Users\\eleck\\Desktop\\쌍용\\java1\\Snake\\src\\img\\img\\hell.png");
/////////////////////////////////////////////////////////////
	public void Difficulty() {
		easybt = new JButton("EASY",EASY);
		normalbt = new JButton("NORMAL",NORMAL);
		hardbt = new JButton("HARD",HARD);
		hellbt = new JButton("HELL",HELL);
		easybt.addActionListener(this);
		normalbt.addActionListener(this);
		hardbt.addActionListener(this);
		hellbt.addActionListener(this);

	}
	
	public void paint(Graphics g) {//그리는 함수
		
//		super.paintComponents(g);
		g.drawImage(background, 0, 0, null);//background를 그려줌
}


	@Override
	public void actionPerformed(ActionEvent e) {
//TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		new GameFrame(b.getText());
		this.dispose();

	
	}

	public void gridLayoutEx() {
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(new Color(0, 0, 0));
		this.getContentPane().add(easybt);
		this.getContentPane().add(normalbt);
		this.getContentPane().add(hardbt);
		this.getContentPane().add(hellbt);

		easybt.setBounds(150, 150, 100, 100);
		normalbt.setBounds(250, 150, 100, 100);
		hardbt.setBounds(150, 250, 100, 100);
		hellbt.setBounds(250, 250, 100, 100);
		easybt.setBackground(Color.YELLOW);
		normalbt.setBackground(Color.GREEN);
		hardbt.setBackground(Color.RED);
		hellbt.setBackground(Color.GRAY);
	}

	public GameDifficulty() {
		Difficulty();
		gridLayoutEx();
		this.setBounds(90, 110, 500, 500);
// 프레임창에 제목을 추가한다.
		this.setTitle("Snake");
// setDefaultCloseOperation 메소드에 (JFrame.EXIT_ON_CLOSE)필드 값을 붙이면 윈도우창 종료시
// 프로세스까지(jvm)깔끔하게 종료
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// 창의 크기 조절 가능 유무
		this.setResizable(false);
// 창을 화면에 나타낼것인지 설정
		this.setVisible(true);
// setLocationRelativeTo(null) 값을 null을 넣으면 윈도우 창을 화면의 가운데에 띄운다.
		this.setLocationRelativeTo(null);

	}
}
