package application;

import report.HistoryReportEngine;
import Parser.ParserController;
import view.ViewEngine;

/*
 * Initialize the fields and the methods like report
 * for any differce between report and this file, report it with comments.
 *  
 */
public class AppManager {
	private ViewEngine view_engine;
	private ParserController parser_controller;
	private HistoryReportEngine history_report;
	
	public void initializeViewEngine(){
		view_engine = new ViewEngine(this);
		view_engine.initialize();
	}

	public void parseFile(String name) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Hey "+name);
		Thread th = new Thread(){
			public void run(){
				try {
					sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("After 5");
			}
		};
		
		th.start();
	}
	
	/**
	 * All Error need to be loged here! 
	 * @param message
	 */
	public void setError(String message){
		//We Handle this error just shoing it to User via ViewEngine
		this.view_engine.showErrorDialog(message);
	}
}
