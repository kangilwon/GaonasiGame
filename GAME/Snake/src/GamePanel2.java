import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel2 extends JPanel implements ActionListener {
	Image IN=new ImageIcon(("C:\\Users\\eleck\\Desktop\\쌍용\\java1\\Snake\\src\\img\\img\\IN.jpg")).getImage();
	Image gao0 = new ImageIcon("C:\\Users\\eleck\\Desktop\\쌍용\\java1\\Snake\\src\\img\\img\\gao0.png").getImage();
	Image gao2 = new ImageIcon("C:\\Users\\eleck\\Desktop\\쌍용\\java1\\Snake\\src\\img\\img\\gao2.png").getImage();
	Image body = new ImageIcon("C:\\Users\\eleck\\Desktop\\쌍용\\java1\\Snake\\src\\img\\img\\body.png").getImage();
	Image mongi = new ImageIcon("C:\\Users\\eleck\\Desktop\\쌍용\\java1\\Snake\\src\\img\\img\\mongi.png").getImage();
	static final int SCREEN_WIDTH = 1100; // 윈도우 창의 가로 크기
	static final int SCREEN_HEIGHT = 900; // 윈도우 창의 세로 크기
	static final int UNIT_SIZE = 50; // 뱀과 사과의 크기 (윈도우 창의 격자 하나의 크기)
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	static final int DELAY = 85; // 뱀의 이동속도
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyparts = 6; // 뱀의 기본 길이 설정
	int applesEaten; // 뱀이 사과를 먹었을때 count해주는 변수
	int appleX; // 사과의 x좌표
	int appleY; // 사과의 y좌표
	char direction = 'D';// 게임 시작시 뱀의 첫 이동방향
	boolean running = false;
	Timer timer; // 참조형 변수로 Timer타입의 변수 timer를 생성한다//Timer는 일종의 쓰레드 클래스이다.
	Random random; // 참조형 변수로 Random타입의 변수 random를 생성한다
	Image a = gao2;
	long start_time;
	long end_time;
	
	// GamePanel 메소드는 기본적인 윈도우창과 판넬의 속성과 설정을 만들고 startGame 메소드를 실행시킨다.
	GamePanel2() {
		random = new Random();// java.util 를 import해서 random 변수에 new Random으로 클래스 객체를 생성한다.
		this.setPreferredSize(new Dimension(SCREEN_WIDTH+UNIT_SIZE, SCREEN_HEIGHT+UNIT_SIZE)); // setPreferredSize는 Dimension의 정보를 받아서 윈도우 창의 크기를 지정한다. //Dimension 클래스는 폭과 높이를 캡슐화한다.
//		this.setBackground(Color.white);// setBackground는 판넬의 배경색을 지정한다.
		this.setFocusable(true);// 판넬에 (키)이벤트를 받아온다.
		this.addKeyListener(new MyKeyAdapter());// GamePanel 클래스에 addKeyListener를 입력는다. addKeyListener는 KeyAdapter를 상속받은
												// 내부클래스인 MyKeyAdapter를 주시하고있다 //MyKeyAdapter의 입력값이 입력될 때 마다
												// addKeyListener가 읽어들여서 이벤트를 발생시킨다.
		startGame();// startGame메소드 실행
	}

	
	// 게임을 시작시켜서 사과를 먼저 생성하고 쓰레드를 발생시켜 뱀을 움직이게 해주는 메소드이다.
	public void startGame() {
		start_time = java.lang.System.currentTimeMillis();
		newApple(); // newApple 메소드 실행 //사과를 랜덤의 위치로 생성하는 메소드이다.
		running = true; // 논리형인 running변수를 false에서 true로 변경시킨다.
		timer = new Timer(DELAY, this);// Timer클래스를 참조하는 timer변수를 새로 만들어 주는데 위에서 설정한 DELAY값을 this(GamePanel)에 발생시킨다.
		timer.start();// 쓰레드 클래스의 일종인 Timer를 참조받은 timer변수의 설정값을 위에서 설정 해 주었다면 start()메소드를 사용하여 실행시켜야
						// 한다.(thread를 start()로 실행 시키듯이)
	}

	//그래픽 객체로 자신의 내부를 그리는 역할이다.
	public void paintComponent(Graphics g) {//그림을 그려주는 도구를 제공하는 클래스 객체로 네모 선 원등을 그린후 Graphics을 통해 보여지게 된다.
		super.paintComponent(g);// JPanel의 paintComponent() 호출
		print(g);
	}

	// 게임에 등장하는 뱀 사과 폰트 등 모든 요소를 그려준다.
	public void print(Graphics g) {
		g.drawImage(IN, 0, 0, null);
		g.setColor(Color.black);

		if (running) {
			end_time = java.lang.System.currentTimeMillis();
				g.setColor(Color.magenta);// 색 지정.
				g.setFont(new Font("Ink Free", Font.BOLD, 40));
				FontMetrics metrics3 = getFontMetrics(g.getFont());
				g.drawString("Time : " + ((end_time-start_time)/1000.0) + "sec",
						(SCREEN_WIDTH- metrics3.stringWidth("Time : " + ((end_time-start_time)/1000.0) + "sec")) / 2,SCREEN_HEIGHT / 8);
				repaint();
			// 화면에 선을 그어준다.
/*				g.setColor(Color.BLACK );// 색 지정.
			for (int i = 0; i < SCREEN_HEIGHT + UNIT_SIZE / UNIT_SIZE; i++) {
				g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT + UNIT_SIZE);
				g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH + UNIT_SIZE, i * UNIT_SIZE);
			}*/
			// 빨간 공을 화면에 그려준다.
//			g.setColor(null);//색 지정
			g.drawImage(mongi, appleX, appleY, UNIT_SIZE, UNIT_SIZE, null);
//			g.drawOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);//랜덤으로 설정한 사과의 위치를 가져온다.

			for (int i = 0; i < bodyparts; i++) {
				if (i == 0) {
					/*
					 * g.setColor(Color.green);// 머리부분 컬러 설정 g.fillRect(x[i], y[i], UNIT_SIZE,
					 * UNIT_SIZE);//머리위치 지정
					 */

					g.drawImage(a, x[i], y[i], UNIT_SIZE, UNIT_SIZE, null);
				} else {
					/*
					 * g.setColor(new Color(45, 180, 0));// 바디부분 컬러 설정 // g.setColor(new
					 * Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
					 * g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);//바디부분 위치 지정//새로 만들어 주는 부분
					 */
//					g.drawImage(body, x[i], y[i], UNIT_SIZE, UNIT_SIZE, null);
					g.setColor(new Color(random.nextInt(35), random.nextInt(50), random.nextInt(20)));// 바디부분 컬러 설정
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);// 바디부분 위치 지정//새로 만들어 주는 부분
				}
			}
			g.setColor(Color.BLACK);// 폰트 컬러 설정
			g.setFont(new Font("Ink Free", Font.BOLD, 40));// 폰트 속성설정
			FontMetrics metrics = getFontMetrics(g.getFont());// 현재 설정된 폰트 설정을 가져온다
			// Graphics을 통해 설정한 폰트를 적용한 String을 화면에 그려 표시한다.
			// g.drawString(표시할 문자 ,x좌표(stringWidth=지정된 String를, 이 Font로 표시하기 위한 유효폭의 합계를
			// 리턴합니다.),y좌표)
			g.drawString("Score : " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score : " + applesEaten)) / 2,
					g.getFont().getSize());

		} else {
			gameOver(g);
		new startGame();
		}
	}

	//사과의 위치 설정을 랜덤으로 넣어준다 단, 화면의 범위 안에서
	public void newApple() {
		appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
	}

	//뱀이 운직이는 방향을 설정한다.
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

	// 뱀의 머리와 사과가 만났을때를 체크합니다.
	public void checkApple() {
		if ((x[0] == appleX) && (y[0] == appleY)) {
			bodyparts++;//몸의 길이 +1
			applesEaten++;//먹은 사과의 수 +1
			newApple();//새로운 사과 생성
//			a=gao2 != null ?gao0:gao2;
			a=gao0 ;
 		}
	}

	//게임이 오버되는 조건을 체크합니다.
	public void checkCollisions() {
		// 머리가 몸체에 충돌하는지 확인하고
		for (int i = bodyparts; i > 0; i--) {
			if ((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		// 머리가 왼쪽 테두리를 넘어가는지 확인
		if (x[0] < 0) {
			running = false;
		}
		// 머리가 오른쪽 테두리를 넘어가는지 확인
		if (x[0] > SCREEN_WIDTH) {
			running = false;
		}
		// 머리가 위쪽 테두리를 넘어가는지 확인
		if (y[0] < 0) {
			running = false;
		}
		// 머리가 아래쪽 테두리를 넘어가는지 확인
		if (y[0] > SCREEN_HEIGHT) {
			running = false;
		}
		// 체크결과 종료 해야 하면 타이머 중지
		if (!running) {
			timer.stop();
			end_time = java.lang.System.currentTimeMillis();
		}
	}

	//게임이 종료될때 실행하는 메소드
	public void gameOver(Graphics g) {
		// 게임이 종료 되었을때 해당 스코어를 그려 표시합니다.
		g.setColor(Color.BLACK);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score  : " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score : " + applesEaten)) / 2,
				g.getFont().getSize());
//	게임이 종료 되었을때 Game Over를그려 표시합니다.
		g.setColor(Color.BLACK);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
//	게임이 종료 되었을때 end_time-start_time 을그려 표시합니다.
		g.setColor(Color.BLACK);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics3 = getFontMetrics(g.getFont());
		g.drawString("Time : " + ((end_time - start_time) / 1000.0) + "sec",
				(SCREEN_WIDTH - metrics3.stringWidth("Time : " + ((end_time - start_time) / 1000.0) + "sec")) / 2,
				SCREEN_HEIGHT / 3);
	}

	@Override
	//action이 발생하면 해당 메소드 들을 실행합니다.
	public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}

	//키보드의 액션을 감지하면 실행합니다.
	public class MyKeyAdapter extends KeyAdapter {
		@Override
		//사용자가 90도 방향으로만 제어할 수 있도록 한다.
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
