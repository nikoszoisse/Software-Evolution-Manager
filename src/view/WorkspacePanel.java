package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import application.Workspace;

import java.awt.Color;
import java.awt.Label;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class WorkspacePanel extends JPanel {
	private JProgressBar progressBar;
	private JLabel lblEvaluatedLawsX;
	private Workspace workspace;
	/**
	 * Create the panel.
	 */
	public WorkspacePanel(Workspace workspace) {
		this.workspace = workspace;
		setLayout(new FlowLayout(FlowLayout.CENTER, 900, 30));
		
		JLabel label = new JLabel("Όνομα αρχείου: "+workspace.getTitle());
		add(label);
		
		JLabel label_1 = new JLabel("Αρ. Εκδόσεων: "+workspace.getNumOfVersions());
		add(label_1);
		
		Label guide_step = new Label("Start by Evaluating the Laws on Menu->\"Tools->Evaluate Laws\"");
		add(guide_step);
		
		this.lblEvaluatedLawsX = new JLabel("Evaluated Laws: 0/8");
		add(lblEvaluatedLawsX);
		
		this.progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		add(progressBar);
		progressBar.setForeground(Color.RED);
		progressBar.setMaximum(8);
		//TODO REMOVE THREAD because is just game
		Thread thread = new Thread(){
			public void run(){
				int value = 0;
				while(true){
					progressBar.setForeground(Color.RED);
					value++;
					progressBar.setValue(value);
					if(value == 8){
						progressBar.setForeground(Color.GREEN);
						value=0;
					}
					try {
						sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();

	}
	
	public void updatePanel(){
		int evaluated_laws = workspace.getNumOfEvaluatedLaws();
		this.lblEvaluatedLawsX.setText("Evaluated Laws: "+evaluated_laws+"/8");
		this.progressBar.setValue(evaluated_laws);
		
		if(evaluated_laws == 8){
			this.progressBar.setForeground(Color.GREEN);
			Label success_message = new Label("You can create Report File!");
			success_message.setForeground(new Color(0, 128, 0));
			success_message.setBounds(265, 257, 173, 23);
			add(success_message);
		}
	}
	
	public Workspace getWorkspace(){
		return workspace;
	}
}
