package sample;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class SwingPanel extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		SwingPanel frame = new SwingPanel("パネルサンプル");
		frame.setVisible(true);
	}

	SwingPanel(String title) {
		setTitle(title);
		setBounds(100, 100, 300, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(null);

		JPanel p1 = new JPanel();
		p1.setBounds(50, 25, 100, 50);
		p1.setBackground(Color.BLUE);

		JPanel p2 = new JPanel();
		p2.setBounds(160, 0, 50, 100);
		p2.setBackground(Color.RED);

		BevelBorder border = new BevelBorder(BevelBorder.RAISED);
		p2.setBorder(border);

		Container contentPane = getContentPane();
		contentPane.add(p1);
		contentPane.add(p2);
	}
}
