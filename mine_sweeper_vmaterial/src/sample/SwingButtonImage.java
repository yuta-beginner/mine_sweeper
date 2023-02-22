package sample;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingButtonImage extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		SwingButtonImage frame = new SwingButtonImage("ボタンサンプル");
		frame.setVisible(true);
	}

	SwingButtonImage(String title) {
		setTitle(title);
		setBounds(100, 100, 480, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p = new JPanel();

		ImageIcon icon1 = new ImageIcon("./img/smiling.png");
		ImageIcon icon2 = new ImageIcon("./img/mine.png");
		ImageIcon icon3 = new ImageIcon("./img/flag.png");

		JButton button1 = new JButton("text1", icon1);
		button1.setPreferredSize(new Dimension(140, 80));
		button1.setHorizontalTextPosition(JButton.LEFT);
		button1.setVerticalTextPosition(JButton.TOP);

		JButton button2 = new JButton("text2", icon1);
		button2.setPreferredSize(new Dimension(140, 80));
		button2.setHorizontalTextPosition(JButton.LEFT);
		button2.setVerticalTextPosition(JButton.CENTER);

		JButton button3 = new JButton("text3", icon1);
		button3.setPreferredSize(new Dimension(140, 80));
		button3.setHorizontalTextPosition(JButton.LEFT);
		button3.setVerticalTextPosition(JButton.BOTTOM);

		JButton button4 = new JButton("text4", icon2);
		button4.setPreferredSize(new Dimension(140, 80));
		button4.setHorizontalTextPosition(JButton.LEFT);
		button4.setVerticalTextPosition(JButton.CENTER);

		JButton button5 = new JButton("text5", icon2);
		button5.setPreferredSize(new Dimension(140, 80));
		button5.setHorizontalTextPosition(JButton.CENTER);
		button5.setVerticalTextPosition(JButton.CENTER);

		JButton button6 = new JButton("text6", icon2);
		button6.setPreferredSize(new Dimension(140, 80));
		button6.setHorizontalTextPosition(JButton.RIGHT);
		button6.setVerticalTextPosition(JButton.CENTER);

		JButton button7 = new JButton("text", icon3);
		button7.setPreferredSize(new Dimension(140, 80));

		JButton button8 = new JButton("text7", icon3);
		button8.setPreferredSize(new Dimension(140, 80));
		button8.setIconTextGap(15);

		JButton button9 = new JButton("text8", icon3);
		button9.setPreferredSize(new Dimension(140, 80));
		button9.setIconTextGap(30);

		p.add(button1);
		p.add(button2);
		p.add(button3);
		p.add(button4);
		p.add(button5);
		p.add(button6);
		p.add(button7);
		p.add(button8);
		p.add(button9);

		Container contentPane = getContentPane();
		contentPane.add(p, BorderLayout.CENTER);
	}
}