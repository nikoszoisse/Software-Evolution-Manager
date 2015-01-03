import java.awt.EventQueue;

import application.AppManager;


public class Main {

	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */ 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppManager app_test = new AppManager();
					app_test.initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
