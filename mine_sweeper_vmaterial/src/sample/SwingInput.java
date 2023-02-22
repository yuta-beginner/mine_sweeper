package sample;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SwingInput extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		SwingInput frame = new SwingInput("インプットサンプル");
		frame.setVisible(true);
	}

	SwingInput(String title) {
		setTitle(title);
		setBounds(100, 100, 300, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new FlowLayout());

		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(200, 100));
		p.setBackground(Color.ORANGE);

		JLabel label = new JLabel("input");
		JTextField text = new JTextField(5);

		p.add(label);
		p.add(text);

		Container contentPane = getContentPane();
		contentPane.add(p);
	}
}