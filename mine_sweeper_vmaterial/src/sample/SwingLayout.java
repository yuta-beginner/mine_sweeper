package sample;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class SwingLayout extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		SwingLayout frame = new SwingLayout("レイアウトサンプル");
		frame.setVisible(true);
	}

	SwingLayout(String title) {
		setTitle(title);
		setBounds(800, 200, 290, 440);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		JButton button1 = new JButton("1-1");
		button1.setBounds(30, 20, 80, 100);
		JButton button2 = new JButton("2-1");
		button2.setBounds(30, 20, 80, 100);
		JButton button3 = new JButton("3-1");
		button3.setBounds(30, 20, 80, 100);
		JButton button4 = new JButton("1-2");
		button4.setBounds(50, 40, 80, 100);
		JButton button5 = new JButton("2-2");
		button5.setBounds(50, 40, 80, 100);
		JButton button6 = new JButton("3-2");
		button6.setBounds(50, 40, 80, 100);

		JPanel p1 = new JPanel();
		p1.setBounds(20, 20, 240, 100);
		p1.setBackground(Color.BLUE);
		//フローレイアウト（一定の大きさと間隔で置かれる）
		p1.setLayout(new FlowLayout());
		p1.add(button1);
		p1.add(button4);

		JPanel p2 = new JPanel();
		p2.setBounds(20, 150, 240, 100);
		p2.setBackground(Color.RED);
		//グリッドレイアウト（敷き詰めて置かれる）
		p2.setLayout(new GridLayout());
		p2.add(button2);
		p2.add(button5);

		JPanel p3 = new JPanel();
		p3.setBounds(20, 280, 240, 100);
		p3.setBackground(Color.ORANGE);
		// レイアウト指定なし(指定したxyで置かれる)
		p3.setLayout(null);
		p3.add(button3);
		p3.add(button6);

		BevelBorder border = new BevelBorder(BevelBorder.RAISED);
		p2.setBorder(border);

		Container contentPane = getContentPane();
		JLabel label1 = new JLabel("フローレイアウト");
		label1.setBounds(90, -40, 120, 100);
		contentPane.add(label1);
		contentPane.add(p1);
		JLabel label2 = new JLabel("グリッドレイアウト");
		label2.setBounds(90, 90, 120, 100);
		contentPane.add(label2);
		contentPane.add(p2);
		JLabel label3 = new JLabel("レイアウト指定なし");
		label3.setBounds(90, 220, 120, 100);
		contentPane.add(label3);
		contentPane.add(p3);
	}
}
