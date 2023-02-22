package sample;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SwingLabel extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		SwingLabel frame = new SwingLabel("ラベルサンプル");
		frame.setVisible(true);
	}

	SwingLabel(String title) {
		setTitle(title);
		setBounds(100, 100, 300, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p = new JPanel();

		JLabel label1 = new JLabel("左側ラベル");
		label1.setForeground(Color.BLUE);
		label1.setBackground(Color.BLACK);

		JLabel label2 = new JLabel("右側ラベル");
		label2.setForeground(Color.RED);
		label2.setBackground(Color.WHITE);
		label2.setOpaque(true);

		p.add(label1);
		p.add(label2);

		Container contentPane = getContentPane();
		contentPane.add(p, BorderLayout.CENTER);
	}
}