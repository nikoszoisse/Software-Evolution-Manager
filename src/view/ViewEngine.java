package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import application.AppManager;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ViewEngine {
	private final AppManager app;
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public ViewEngine(AppManager running_app){
		this.app = running_app;
	}
	
	/*Interact with GUI routines*/
	public void addTab(){
		
	}
	
	public void closeTab(){
		
	}
	
	public void createSaveFileDialog(){
		
	}
	
	public void createReportFileDialog(){
		
	}
	
	public void viewLaw(){
		
	}
	
	public void exportReportEvent(String file_path){
		
	}
	
	public void parseFileEvent(String file_path){
		
	}
	
	/*Initialize Menu Bar and Items*/
	private void initMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFileMenu = new JMenu("File");
		menuBar.add(mnFileMenu);
		
		JMenuItem mntmOpenHistoryFile = new JMenuItem("Open History File");
		mnFileMenu.add(mntmOpenHistoryFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFileMenu.add(mntmExit);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenu mnEvaluateLaws = new JMenu("Evaluate Laws");
		mnTools.add(mnEvaluateLaws);
		
		JMenuItem mntmstLaw_1 = new JMenuItem("1st Law");
		mnEvaluateLaws.add(mntmstLaw_1);
		
		JMenuItem mntmndLaw_2 = new JMenuItem("2nd Law");
		mnEvaluateLaws.add(mntmndLaw_2);
		
		JMenuItem mntmrdLaw_3 = new JMenuItem("3rd Law");
		mnEvaluateLaws.add(mntmrdLaw_3);
		
		JMenuItem mntmthLaw_4 = new JMenuItem("4th Law");
		mnEvaluateLaws.add(mntmthLaw_4);
		
		JMenuItem mntmthLaw_5 = new JMenuItem("5th Law");
		mnEvaluateLaws.add(mntmthLaw_5);
		
		JMenuItem mntmthLaw_6 = new JMenuItem("6th Law");
		mnEvaluateLaws.add(mntmthLaw_6);
		
		JMenuItem mntmthLaw_7 = new JMenuItem("7th Law");
		mnEvaluateLaws.add(mntmthLaw_7);
		
		JMenuItem mntmthLaw_8 = new JMenuItem("8th Law");
		mnEvaluateLaws.add(mntmthLaw_8);
		
		JMenu mnReport = new JMenu("Report");
		mnTools.add(mnReport);
		
		JMenuItem mntmCreateReport = new JMenuItem("Create Report");
		mnReport.add(mntmCreateReport);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
	}
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initMenuBar();
		
		this.frame.setVisible(true);
	}

	public void close(){
		
	}
	
	/**
	 * Launch the application.
	 * @wbp.parser.entryPoint
	 */
	//TODO Remove it when public 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppManager app_test = new AppManager();
					app_test.initializeViewEngine();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
