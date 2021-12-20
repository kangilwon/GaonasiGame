import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel2 extends JPanel implements ActionListener {
	Image IN=new ImageIcon(("C:\\Users\\eleck\\Desktop\\�ֿ�\\java1\\Snake\\src\\img\\img\\IN.jpg")).getImage();
	Image gao0 = new ImageIcon("C:\\Users\\eleck\\Desktop\\�ֿ�\\java1\\Snake\\src\\img\\img\\gao0.png").getImage();
	Image gao2 = new ImageIcon("C:\\Users\\eleck\\Desktop\\�ֿ�\\java1\\Snake\\src\\img\\img\\gao2.png").getImage();
	Image body = new ImageIcon("C:\\Users\\eleck\\Desktop\\�ֿ�\\java1\\Snake\\src\\img\\img\\body.png").getImage();
	Image mongi = new ImageIcon("C:\\Users\\eleck\\Desktop\\�ֿ�\\java1\\Snake\\src\\img\\img\\mongi.png").getImage();
	static final int SCREEN_WIDTH = 1100; // ������ â�� ���� ũ��
	static final int SCREEN_HEIGHT = 900; // ������ â�� ���� ũ��
	static final int UNIT_SIZE = 50; // ��� ����� ũ�� (������ â�� ���� �ϳ��� ũ��)
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	static final int DELAY = 85; // ���� �̵��ӵ�
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyparts = 6; // ���� �⺻ ���� ����
	int applesEaten; // ���� ����� �Ծ����� count���ִ� ����
	int appleX; // ����� x��ǥ
	int appleY; // ����� y��ǥ
	char direction = 'D';// ���� ���۽� ���� ù �̵�����
	boolean running = false;
	Timer timer; // ������ ������ TimerŸ���� ���� timer�� �����Ѵ�//Timer�� ������ ������ Ŭ�����̴�.
	Random random; // ������ ������ RandomŸ���� ���� random�� �����Ѵ�
	Image a = gao2;
	long start_time;
	long end_time;
	
	// GamePanel �޼ҵ�� �⺻���� ������â�� �ǳ��� �Ӽ��� ������ ����� startGame �޼ҵ带 �����Ų��.
	GamePanel2() {
		random = new Random();// java.util �� import�ؼ� random ������ new Random���� Ŭ���� ��ü�� �����Ѵ�.
		this.setPreferredSize(new Dimension(SCREEN_WIDTH+UNIT_SIZE, SCREEN_HEIGHT+UNIT_SIZE)); // setPreferredSize�� Dimension�� ������ �޾Ƽ� ������ â�� ũ�⸦ �����Ѵ�. //Dimension Ŭ������ ���� ���̸� ĸ��ȭ�Ѵ�.
//		this.setBackground(Color.white);// setBackground�� �ǳ��� ������ �����Ѵ�.
		this.setFocusable(true);// �ǳڿ� (Ű)�̺�Ʈ�� �޾ƿ´�.
		this.addKeyListener(new MyKeyAdapter());// GamePanel Ŭ������ addKeyListener�� �Է´´�. addKeyListener�� KeyAdapter�� ��ӹ���
												// ����Ŭ������ MyKeyAdapter�� �ֽ��ϰ��ִ� //MyKeyAdapter�� �Է°��� �Էµ� �� ����
												// addKeyListener�� �о�鿩�� �̺�Ʈ�� �߻���Ų��.
		startGame();// startGame�޼ҵ� ����
	}

	
	// ������ ���۽��Ѽ� ����� ���� �����ϰ� �����带 �߻����� ���� �����̰� ���ִ� �޼ҵ��̴�.
	public void startGame() {
		start_time = java.lang.System.currentTimeMillis();
		newApple(); // newApple �޼ҵ� ���� //����� ������ ��ġ�� �����ϴ� �޼ҵ��̴�.
		running = true; // ������ running������ false���� true�� �����Ų��.
		timer = new Timer(DELAY, this);// TimerŬ������ �����ϴ� timer������ ���� ����� �ִµ� ������ ������ DELAY���� this(GamePanel)�� �߻���Ų��.
		timer.start();// ������ Ŭ������ ������ Timer�� �������� timer������ �������� ������ ���� �� �־��ٸ� start()�޼ҵ带 ����Ͽ� ������Ѿ�
						// �Ѵ�.(thread�� start()�� ���� ��Ű����)
	}

	//�׷��� ��ü�� �ڽ��� ���θ� �׸��� �����̴�.
	public void paintComponent(Graphics g) {//�׸��� �׷��ִ� ������ �����ϴ� Ŭ���� ��ü�� �׸� �� ������ �׸��� Graphics�� ���� �������� �ȴ�.
		super.paintComponent(g);// JPanel�� paintComponent() ȣ��
		print(g);
	}

	// ���ӿ� �����ϴ� �� ��� ��Ʈ �� ��� ��Ҹ� �׷��ش�.
	public void print(Graphics g) {
		g.drawImage(IN, 0, 0, null);
		g.setColor(Color.black);

		if (running) {
			end_time = java.lang.System.currentTimeMillis();
				g.setColor(Color.magenta);// �� ����.
				g.setFont(new Font("Ink Free", Font.BOLD, 40));
				FontMetrics metrics3 = getFontMetrics(g.getFont());
				g.drawString("Time : " + ((end_time-start_time)/1000.0) + "sec",
						(SCREEN_WIDTH- metrics3.stringWidth("Time : " + ((end_time-start_time)/1000.0) + "sec")) / 2,SCREEN_HEIGHT / 8);
				repaint();
			// ȭ�鿡 ���� �׾��ش�.
/*				g.setColor(Color.BLACK );// �� ����.
			for (int i = 0; i < SCREEN_HEIGHT + UNIT_SIZE / UNIT_SIZE; i++) {
				g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT + UNIT_SIZE);
				g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH + UNIT_SIZE, i * UNIT_SIZE);
			}*/
			// ���� ���� ȭ�鿡 �׷��ش�.
//			g.setColor(null);//�� ����
			g.drawImage(mongi, appleX, appleY, UNIT_SIZE, UNIT_SIZE, null);
//			g.drawOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);//�������� ������ ����� ��ġ�� �����´�.

			for (int i = 0; i < bodyparts; i++) {
				if (i == 0) {
					/*
					 * g.setColor(Color.green);// �Ӹ��κ� �÷� ���� g.fillRect(x[i], y[i], UNIT_SIZE,
					 * UNIT_SIZE);//�Ӹ���ġ ����
					 */

					g.drawImage(a, x[i], y[i], UNIT_SIZE, UNIT_SIZE, null);
				} else {
					/*
					 * g.setColor(new Color(45, 180, 0));// �ٵ�κ� �÷� ���� // g.setColor(new
					 * Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
					 * g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);//�ٵ�κ� ��ġ ����//���� ����� �ִ� �κ�
					 */
//					g.drawImage(body, x[i], y[i], UNIT_SIZE, UNIT_SIZE, null);
					g.setColor(new Color(random.nextInt(35), random.nextInt(50), random.nextInt(20)));// �ٵ�κ� �÷� ����
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);// �ٵ�κ� ��ġ ����//���� ����� �ִ� �κ�
				}
			}
			g.setColor(Color.BLACK);// ��Ʈ �÷� ����
			g.setFont(new Font("Ink Free", Font.BOLD, 40));// ��Ʈ �Ӽ�����
			FontMetrics metrics = getFontMetrics(g.getFont());// ���� ������ ��Ʈ ������ �����´�
			// Graphics�� ���� ������ ��Ʈ�� ������ String�� ȭ�鿡 �׷� ǥ���Ѵ�.
			// g.drawString(ǥ���� ���� ,x��ǥ(stringWidth=������ String��, �� Font�� ǥ���ϱ� ���� ��ȿ���� �հ踦
			// �����մϴ�.),y��ǥ)
			g.drawString("Score : " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score : " + applesEaten)) / 2,
					g.getFont().getSize());

		} else {
			gameOver(g);
		new startGame();
		}
	}

	//����� ��ġ ������ �������� �־��ش� ��, ȭ���� ���� �ȿ���
	public void newApple() {
		appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
	}

	//���� �����̴� ������ �����Ѵ�.
	public void move() {
		for (int i = bodyparts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		switch (direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}

	// ���� �Ӹ��� ����� ���������� üũ�մϴ�.
	public void checkApple() {
		if ((x[0] == appleX) && (y[0] == appleY)) {
			bodyparts++;//���� ���� +1
			applesEaten++;//���� ����� �� +1
			newApple();//���ο� ��� ����
//			a=gao2 != null ?gao0:gao2;
			a=gao0 ;
 		}
	}

	//������ �����Ǵ� ������ üũ�մϴ�.
	public void checkCollisions() {
		// �Ӹ��� ��ü�� �浹�ϴ��� Ȯ���ϰ�
		for (int i = bodyparts; i > 0; i--) {
			if ((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		// �Ӹ��� ���� �׵θ��� �Ѿ���� Ȯ��
		if (x[0] < 0) {
			running = false;
		}
		// �Ӹ��� ������ �׵θ��� �Ѿ���� Ȯ��
		if (x[0] > SCREEN_WIDTH) {
			running = false;
		}
		// �Ӹ��� ���� �׵θ��� �Ѿ���� Ȯ��
		if (y[0] < 0) {
			running = false;
		}
		// �Ӹ��� �Ʒ��� �׵θ��� �Ѿ���� Ȯ��
		if (y[0] > SCREEN_HEIGHT) {
			running = false;
		}
		// üũ��� ���� �ؾ� �ϸ� Ÿ�̸� ����
		if (!running) {
			timer.stop();
			end_time = java.lang.System.currentTimeMillis();
		}
	}

	//������ ����ɶ� �����ϴ� �޼ҵ�
	public void gameOver(Graphics g) {
		// ������ ���� �Ǿ����� �ش� ���ھ �׷� ǥ���մϴ�.
		g.setColor(Color.BLACK);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score  : " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score : " + applesEaten)) / 2,
				g.getFont().getSize());
//	������ ���� �Ǿ����� Game Over���׷� ǥ���մϴ�.
		g.setColor(Color.BLACK);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
//	������ ���� �Ǿ����� end_time-start_time ���׷� ǥ���մϴ�.
		g.setColor(Color.BLACK);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics3 = getFontMetrics(g.getFont());
		g.drawString("Time : " + ((end_time - start_time) / 1000.0) + "sec",
				(SCREEN_WIDTH - metrics3.stringWidth("Time : " + ((end_time - start_time) / 1000.0) + "sec")) / 2,
				SCREEN_HEIGHT / 3);
	}

	@Override
	//action�� �߻��ϸ� �ش� �޼ҵ� ���� �����մϴ�.
	public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}

	//Ű������ �׼��� �����ϸ� �����մϴ�.
	public class MyKeyAdapter extends KeyAdapter {
		@Override
		//����ڰ� 90�� �������θ� ������ �� �ֵ��� �Ѵ�.
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (direction != 'R') {
					direction = 'L';
					a=gao2 ;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (direction != 'L') {
					direction = 'R';
					a=gao2 ;
				}
				break;
			case KeyEvent.VK_UP:
				if (direction != 'D') {
					direction = 'U';
					a=gao2 ;
				}
				break;
			case KeyEvent.VK_DOWN:
				if (direction != 'U') {
					direction = 'D';
					a=gao2 ;
				}
				break;
			}
		}
	}
}
