package main;

import javax.swing.JFrame;

public class JImpossibleMission {

	public static void main(String[] args) {
		JFrame window = new JFrame("Impossible Mission");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(window);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);

	}

}
