package platform.framework;

import javax.swing.JFrame;

public class DisplayManager {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 500;
	
	/**Creates and shows a JFrame window<br>
	 * Called in Main
	 */
	public static void createDisplay() {
		System.out.println("[Render][DisplayManager]: Creating window...");
		
		JFrame window = new JFrame("Platform Game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(100, 50, WIDTH, HEIGHT);
		window.add(new GameScreen());
		window.setResizable(false);

		window.setVisible(true);
	}
}
