package dice_game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class game extends JFrame {

	int num[] = new int[4];
	private int Arr_Score[] = { 0, 0, 0, 0 };
	private String Arr_Player[] = { "player1", "player2", "player3", "player4" };
	private MyJPanel Arr_pan[] = new MyJPanel[4];
	private JTextField Score_Dice = new JTextField(5);
	private JTextField Win = new JTextField(20);
	private JPanel JP_R = new JPanel();
	private JPanel JP_L = new JPanel();
	private JButton Stop = new JButton("Stop");
	private JButton Roll = new JButton("Roll");

	JPanel pan[] = new JPanel[Arr_pan.length];
	JTextField Score[] = new JTextField[Arr_Score.length];
	JLabel Player[] = new JLabel[Arr_Player.length];

	public game() {

		Container c = getContentPane();
		c.setLayout(new BorderLayout(5, 0));
		c.setBackground(Color.pink);
		JP_L.setLayout(new GridLayout(4, 1));
		JP_R.setLayout(new GridLayout(8, 1));
		JP_L.setBackground(Color.LIGHT_GRAY);
		JP_L.setOpaque(true);
		new MyJPanel();
		Roll.addMouseListener(new MyMouseListener());
		Stop.addMouseListener(new MyMouseListener());
		JP_R.setBackground(Color.LIGHT_GRAY);

		this.setResizable(false); // 창 크기 변경 불가
		this.setTitle("Dice Game");
		this.setSize(500, 650);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setFocusable(true);

		JP_L.setOpaque(true);
		JP_R.setOpaque(true);
		JP_R.setSize(200, 550);
		JP_R.setLocation(250, 30);

		JP_R.add(Score_Dice);
		JP_R.add(Roll); // 주사위 굴리는 버튼
		JP_R.add(Stop); // 주사위 멈추는 버튼
		JP_R.add(Win);

		c.add(JP_R, BorderLayout.EAST);
		c.add(JP_L, BorderLayout.CENTER);

	}

	int i = 0;

	class MyMouseListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			JButton b = (JButton) e.getSource();
			int Dice = (int) (Math.random() * 6 + 1);
			if (b.getText().equals("Roll")) {
				Score_Dice.setText(Integer.toString(Dice));
				Arr_Score[i] = Arr_Score[i] + Dice;
				String str = Integer.toString(Arr_Score[i]);
				Score[i].setText(str);
				if (Dice == 1) { // if문 : 주사위가 1이면 얻은 점수 0으로
					Arr_Score[i] = 0;
					Score[i].setText("0");
					i++;
					if (i == 4) {
						i = 0;
					}
				}
				if (Arr_Score[i] >= 30) {
					JOptionPane.showMessageDialog(b, "플레이어 " + (i + 1) + "이(가) 승리하였습니다.");
					Win.setText("플레이어 " + (i + 1) + "이(가) 승리하였습니다.");
					System.exit(0);

				}
			} else if (b.getText().equals("Stop")) {
				i++;
				if (i == 4) {
					i = 0;
				}
			}
		}
	}

	class MyJPanel extends JPanel {

		public MyJPanel() {

			for (int i = 0; i < Arr_pan.length; i++) {
				int location = 10;
				pan[i] = new JPanel();
				pan[i].setSize(30, 100);
				pan[i].setLocation(20, (location + 50) * i);
				pan[i].setForeground(Color.LIGHT_GRAY);
				pan[i].setOpaque(true);
				Player[i] = new JLabel(Arr_Player[i]);
				Score[i] = new JTextField(7);
				pan[i].add(Player[i]);
				pan[i].add(Score[i]); // 점수 누적
				JP_L.add(pan[i]);

			}

		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int i = 0; i < Arr_pan.length; i++) {
				g.drawRect((0 + 30) * i, (0 + 100) * i, getWidth(), getHeight());
				g.setColor(Color.pink);
				g.fillRect((0 + 30) * i, (0 + 100) * i, getWidth(), getHeight());
			}
		}

	}

	public static void main(String[] args) {
		new game();
	}
}
