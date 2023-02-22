package sample;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingButtonText extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		SwingButtonText frame = new SwingButtonText("ボタンサンプル");
		frame.setVisible(true);
	}

	SwingButtonText(String title) {
		setTitle(title);
		setBounds(100, 100, 300, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p = new JPanel();

		JButton button1 = new JButton("Button");
		JButton button2 = new JButton("Button");
		button2.setForeground(Color.GREEN);
		JButton button3 = new JButton("Button");
		button3.setForeground(Color.RED);
		button3.setBackground(Color.WHITE);

		p.add(button1);
		p.add(button2);
		p.add(button3);

		Container contentPane = getContentPane();
		contentPane.add(p, BorderLayout.CENTER);
	}
}