package sample;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SwingMessage extends JFrame {

	private static final long serialVersionUID = 1L;

	static SwingMessage frame;
	JButton button;

	public static void main(String args[]) {
		SwingMessage frame = new SwingMessage("ボタンサンプル");
		frame.setVisible(true);
	}

	SwingMessage(String title) {
		setTitle(title);
		setBounds(100, 100, 300, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p = new JPanel();

		button = new JButton("button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JLabel msg = new JLabel("クリックされました。");
				JOptionPane.showMessageDialog(frame, msg);
			}
		});

		p.add(button);

		Container contentPane = getContentPane();
		contentPane.add(p, BorderLayout.CENTER);
	}
}