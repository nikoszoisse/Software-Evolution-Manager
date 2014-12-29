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
		parser_controller = new ParserController(this);
		view_engine.initialize();
	}
	
	//RENAME of parseProcedure
	/**
	 * Procedure to parse a file that ncomes from ViewEngine
	 * @param file_path
	 * @throws InterruptedException
	 */
	public void parseFileProcedure(final String file_path) throws InterruptedException {
		Thread th = new Thread(){
			public void run(){
				parser_controller.generateWorkspaceFromFile(file_path);
			}
		};
		
		th.start();
	}
	
	/**
	 * TODO 
	 */
	public void reportProcedure(Workspace which){
		if(checkLaws()){
			//e.x which.checkLaws()
		}
	}
	
	/*
	 * Maybe Need to go at Workspace Class
	 */
	private boolean checkLaws(){
		return false;
	}
	
	/**
	 * All Error need to be loged here! 
	 * @param message
	 */
	public void setError(String message){
		//We Handle this error just shoing it to User via ViewEngine
		this.view_engine.showErrorDialog(message);
	}
	
	/**
	 * Add the workspace to Workspace list of app
	 * Inform ViewEngine Too
	 * @param ret_workspace
	 */
	public void addWorkspace(Workspace ret_workspace) {
		//TODO ADD IT TO LLIST
		//TODO CALL viewEngine to add it in GUI
	}
}
