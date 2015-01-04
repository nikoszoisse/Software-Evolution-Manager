package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import application.Workspace;

import java.awt.Color;
import java.awt.Component;
import java.awt.Label;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class WorkspacePanel extends JPanel {
	private JProgressBar progressBar;
	private JLabel lblEvaluatedLawsX;
	private Workspace workspace;
	private Component success_message;
	/**
	 * Create the panel.
	 */
	public WorkspacePanel(Workspace workspace) {
		this.workspace = workspace;
		setLayout(new FlowLayout(FlowLayout.CENTER, 900, 30));
		
		JLabel label = new JLabel("File name: "+workspace.getTitle());
		add(label);
		
		JLabel label_1 = new JLabel("Num. of versions: "+workspace.getNumOfVersions());
		add(label_1);
		
		Label guide_step = new Label("Start by Evaluating the Laws on Menu->\"Tools->Evaluate Laws\"");
		add(guide_step);
		
		this.lblEvaluatedLawsX = new JLabel("Evaluated Laws: 0/8");
		add(lblEvaluatedLawsX);
		
		this.progressBar = new JProgressBar();
		progressBar.setToolTipText("Num. of evaluated laws.");
		progressBar.setStringPainted(true);
		add(progressBar);
		progressBar.setForeground(Color.RED);
		progressBar.setMaximum(8);
		
		success_message = new Label("You can create Report File!");
		success_message.setForeground(new Color(0, 128, 0));
		success_message.setBounds(265, 257, 173, 23);
		success_message.setVisible(false);
		add(success_message);
	}
	
	public void updatePanel(){
		int evaluated_laws = workspace.getNumOfEvaluatedLaws();
		this.lblEvaluatedLawsX.setText("Evaluated Laws: "+evaluated_laws+"/8");
		this.progressBar.setValue(evaluated_laws);
		
		if(evaluated_laws == 8){
			this.progressBar.setForeground(Color.GREEN);
			this.success_message.setVisible(true);
		}
	}
	
	public Workspace getWorkspace(){
		return workspace;
	}
}
