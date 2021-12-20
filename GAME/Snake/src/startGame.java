import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

public class startGame extends JFrame  implements ActionListener {
	
	JButton btn0 = new JButton();
	JButton btn1 = new JButton();
	Image background=new ImageIcon(("C:\\Users\\eleck\\Desktop\\�ֿ�\\java1\\Snake\\src\\img\\img\\BIMG.jpg")).getImage();
	ImageIcon gameStart = new ImageIcon("C:\\Users\\eleck\\Desktop\\�ֿ�\\java1\\Snake\\src\\img\\img\\gameStart-cutout.png");
	ImageIcon gameExit = new ImageIcon("C:\\Users\\eleck\\Desktop\\�ֿ�\\java1\\Snake\\src\\img\\img\\gameExit-cutout.png");

	public void MakeButton() {

		btn0 = new JButton("����",gameStart);
		btn1 = new JButton("����",gameExit);
		btn0.setIcon(gameStart);
		btn1.setIcon(gameExit);
		btn0.addActionListener(this);
		btn1.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton)e.getSource();
		if(b.getText().equals("����")) {
			this.dispose();
			new GameDifficulty();
		}else if(b.getText().equals("����")) {
//			JOptionPane.showMessageDialog(null, "������ �����մϴ�.");
			System.exit(0);
		}

	}

	public void paint(Graphics g) {//�׸��� �Լ�
//		super.paintComponents(g);
		g.drawImage(background, 0, 0, null);//background�� �׷���
		
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
				//������â�� ������ �߰��Ѵ�.
				this.setTitle("Snake");
				//setDefaultCloseOperation �޼ҵ忡 (JFrame.EXIT_ON_CLOSE)�ʵ� ���� ���̸� ������â ����� ���μ�������(jvm)����ϰ� ����
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//â�� ũ�� ���� ���� ����
				this.setResizable(false);
				//â�� ȭ�鿡 ��Ÿ�������� ����
				this.setVisible(true);
				//setLocationRelativeTo(null) ���� null�� ������ ������ â�� ȭ���� ����� ����.
				this.setLocationRelativeTo(null);
			}
			
			public static void main(String[] args) {
				new startGame();
			}
	}
