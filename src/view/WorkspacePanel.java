package view;

import javax.swing.JPanel;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import application.Workspace;

import java.awt.Color;
import java.awt.Label;

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
		setLayout(null);
		
		JLabel label = new JLabel("Όνομα αρχείου: "+workspace.getTitle());
		label.setBounds(27, 47, 251, 14);
		add(label);
		
		JLabel label_1 = new JLabel("Αρ. Εκδόσεων: "+workspace.getNumOfVersions());
		label_1.setBounds(27, 91, 173, 16);
		add(label_1);
		
		this.progressBar = new JProgressBar();
		progressBar.setForeground(Color.RED);
		progressBar.setMaximum(8);
		progressBar.setBounds(265, 237, 148, 14);
		add(progressBar);
		
		this.lblEvaluatedLawsX = new JLabel("Evaluated Laws: 0/8");
		lblEvaluatedLawsX.setBounds(265, 209, 128, 16);
		add(lblEvaluatedLawsX);
		
		Label guide_step = new Label("Start by Evaluating the Laws on Menu->\"Tools->Evaluate Laws\"");
		guide_step.setBounds(27, 140, 354, 23);
		add(guide_step);

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
