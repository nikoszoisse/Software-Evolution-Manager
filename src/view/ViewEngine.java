package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import application.AppManager;
import application.Law;
import application.Version;
import application.Workspace;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ViewEngine implements ActionListener{
	private final AppManager app;
	private JFrame frmProjectSem;
	private JTabbedPane tabbedPane;

	/**
	 * Create the application.
	 */
	public ViewEngine(AppManager running_app){
		this.app = running_app;
	}
	
	/**
	 * Creae a tab with the created Workspace(History File)
	 * @param workspace
	 */
	public void addTab(Workspace workspace){
		WorkspacePanel panel = new WorkspacePanel(workspace);
		panel.setPreferredSize(new Dimension(frmProjectSem.getWidth(),frmProjectSem.getHeight()));
		
		this.tabbedPane.addTab(workspace.getTitle(), null, panel, null);
		
		int tab_index = this.tabbedPane.getTabCount()-1;
		initTabComponent(tab_index,this.tabbedPane);
	}
	
	public void closeTab(){
		
	}
	
	public void createOpenFileDialog(){
		//Set up J File Chooser
		final JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "TxT History Files", "txt");
	    chooser.setFileFilter(filter);
	    
		int returnVal = chooser.showOpenDialog(null);
		
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	//Tell to AppManager to handle the file and parse it!
	    	try {
	    		String file_path = chooser.getSelectedFile().getAbsolutePath();
				app.parseFileProcedure(file_path);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	       System.out.println(" You chose to open this file: " +
	            chooser.getSelectedFile().getName());
	    }
	    
	}
	
	public void createReportFileDialog(){
		
	}
	
	public void viewLaw(int law_num){
		if(law_num == 0){
			law_num++;
		}
		String tab_type = this.tabbedPane.getSelectedComponent().getClass().getName();
		//TODO remove it when public
		System.out.println(tab_type);
		
		/*Check if History file selected*/
		if(tab_type != "view.WorkspacePanel"){
			this.showErrorDialog("Evaluating a law requires 'Open/Select' a valid 'History File' first!");
		}
		//TODO Check if returns the referce of the panel or shits 
		WorkspacePanel current_tab = (WorkspacePanel) this.tabbedPane.getSelectedComponent();
		Workspace current_tab_wrokspace = current_tab.getWorkspace();
		
		/*Check if Our Law's dependencies exits and if exists check if evaluated!!*/
		ArrayList<Integer> depended_laws = current_tab_wrokspace.getLaw(law_num).getDependedLaws();
		if(depended_laws.size()>0){
			for(int i=0;i<depended_laws.size();i++){
				Law dep_law = current_tab_wrokspace.getLaw(depended_laws.get(i)-1);
				if(!dep_law.checkLaw()){
					this.showErrorDialog(dep_law.getLawName()+" need's evaluation before continue");
					return;
				}
			}
		}
		
		LawDialog dialog = new LawDialog(current_tab_wrokspace.getLaw(law_num), this.frmProjectSem);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	
	/**
	 * Initialize Menu Bar and Items
	 * 
	 * */
	private void initMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		frmProjectSem.setJMenuBar(menuBar);
		
		JMenu mnFileMenu = new JMenu("File");
		menuBar.add(mnFileMenu);
		
		JMenuItem mntmOpenHistoryFile = new JMenuItem("Open History File");
		mntmOpenHistoryFile.setActionCommand("open_file");
		mntmOpenHistoryFile.addActionListener(this);
		mnFileMenu.add(mntmOpenHistoryFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFileMenu.add(mntmExit);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenu mnEvaluateLaws = new JMenu("Evaluate Laws");
		mnTools.add(mnEvaluateLaws);
		
		JMenuItem mntmstLaw_1 = new JMenuItem("1st Law");
		mntmstLaw_1.setActionCommand("law_1");
		mntmstLaw_1.addActionListener(this);
		
		mnEvaluateLaws.add(mntmstLaw_1);
		
		JMenuItem mntmndLaw_2 = new JMenuItem("2nd Law");
		mntmndLaw_2.setActionCommand("law_2");
		mntmndLaw_2.addActionListener(this);
		
		mnEvaluateLaws.add(mntmndLaw_2);
		
		JMenuItem mntmrdLaw_3 = new JMenuItem("3rd Law");
		mntmrdLaw_3.setActionCommand("law_3");
		mntmrdLaw_3.addActionListener(this);
		
		mnEvaluateLaws.add(mntmrdLaw_3);
		
		JMenuItem mntmthLaw_4 = new JMenuItem("4th Law");
		mntmthLaw_4.setActionCommand("law_4");
		mntmthLaw_4.addActionListener(this);
		
		mnEvaluateLaws.add(mntmthLaw_4);

		JMenuItem mntmthLaw_5 = new JMenuItem("5th Law");
		mntmthLaw_5.setActionCommand("law_5");
		mntmthLaw_5.addActionListener(this);
		
		mnEvaluateLaws.add(mntmthLaw_5);;

		JMenuItem mntmthLaw_6 = new JMenuItem("6th Law");
		mntmthLaw_6.setActionCommand("law_6");
		mntmthLaw_6.addActionListener(this);
		mnEvaluateLaws.add(mntmthLaw_6);

		JMenuItem mntmthLaw_7 = new JMenuItem("7th Law");
		mntmthLaw_7.setActionCommand("law_7");
		mntmthLaw_7.addActionListener(this);
		mnEvaluateLaws.add(mntmthLaw_7);

		JMenuItem mntmthLaw_8 = new JMenuItem("8th Law");
		mntmthLaw_8.setActionCommand("law_8");
		mntmthLaw_8.addActionListener(this);
		
		mnEvaluateLaws.add(mntmthLaw_8);

		JMenu mnReport = new JMenu("Report");		
		mnTools.add(mnReport);
		
		JMenuItem mntmCreateReport = new JMenuItem("Create Report");
		mntmCreateReport.setActionCommand("create_report");
		mntmCreateReport.addActionListener(this);
		mnReport.add(mntmCreateReport);

		
		JMenu mnHelp = new JMenu("Help");
		
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setActionCommand("help_about");
		mntmAbout.addActionListener(this);
		
		mnHelp.add(mntmAbout);
		
	}
	
	/**
	 *Initialize the Tab Panels 
	 */
	private void initializeTabPane() {
		/*TAB BAR*/
		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmProjectSem.getContentPane().add(tabbedPane, BorderLayout.NORTH);
		/*Add Welcome Home Panel*/
		JPanel panel = new JPanel();
		tabbedPane.addTab("Home", null, panel, null);
		
		JLabel lblWelcomeToSem = new JLabel("Welcome to SEM project. \r Select \"File\" -> \"Open History File\" to start.");
		panel.setPreferredSize(new Dimension(frmProjectSem.getWidth(),frmProjectSem.getHeight()));
		panel.add(lblWelcomeToSem);

		
		int tab_index = tabbedPane.getTabCount()-1;
		initTabComponent(tab_index,tabbedPane);
	}

	/**
	 * Chnages the Tab Componet layout in order to add Close Button too
	 * @param index
	 * @param tabbedPane
	 */
    private void initTabComponent(int index, JTabbedPane tabbedPane) {
    	tabbedPane.setTabComponentAt(index,
    			new ButtonTabComponent(tabbedPane));
    }
    
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	public void initialize() {
		frmProjectSem = new JFrame();
		frmProjectSem.setTitle("Project Sem");
		frmProjectSem.setResizable(false);
		frmProjectSem.setBounds(100, 100, 900, 600);
		frmProjectSem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initMenuBar();
		this.initializeTabPane();
		this.frmProjectSem.setVisible(true);
	}

	public void close(){
		
	}
	
	/**
	 * Creates an error dialog
	 * can called by AppManager
	 * @param message
	 */
	public void showErrorDialog(String message){
		JOptionPane.showMessageDialog(frmProjectSem,
			    message,
			    "Opps error",
			    JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case  "open_file": addTab(new Workspace("TestWorkspace", new ArrayList<Version>()));/*this.createOpenFileDialog();*/break;
		case "create_report": this.createReportFileDialog();break;
		case "law_1" :this.viewLaw(1);break;
		case "law_2" :this.viewLaw(2);break;
		case "law_3" :this.viewLaw(3);break;
		case "law_4" :this.viewLaw(4);break;
		case "law_5" :this.viewLaw(5);break;
		case "law_6" :this.viewLaw(6);break;
		case "law_7" :this.viewLaw(7);break;
		case "law_8" :this.viewLaw(8);break;
		default: System.out.println(e.getActionCommand()+" Need creation first");
		}
		
	}

}
