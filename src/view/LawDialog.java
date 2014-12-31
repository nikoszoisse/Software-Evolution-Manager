package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import application.AppManager;
import application.Law;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class LawDialog extends JDialog {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LawDialog dialog = new LawDialog(new Law("hahah"),null);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private final JPanel contentPanel =  new JPanel();

	/**
	 * Create the dialog.
	 */
	public LawDialog(Law law,Frame owner) {
		super(owner, "Αποτίμιση "+law.getLawName());
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("Αποδοχή Νόμου:");
		label.setBounds(249, 168, 110, 14);
		contentPanel.add(label);
		
		JRadioButton radioButton = new JRadioButton("Ναι");
		radioButton.setBounds(248, 181, 50, 24);
		contentPanel.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Όχι");
		radioButton_1.setBounds(302, 181, 57, 24);
		contentPanel.add(radioButton_1);
	    
		ButtonGroup group = new ButtonGroup();
	    group.add(radioButton);
	    group.add(radioButton_1);
	    
	    JLabel label_1 = new JLabel("Σχόλιο:");
	    label_1.setBounds(12, 140, 55, 16);
	    contentPanel.add(label_1);
	    
	    JTextArea textArea = new JTextArea();
	    textArea.setToolTipText("κάντε κλικ για να προσθεστε ένα σχόλιο εδώ..");
	    textArea.setLineWrap(true);
	    textArea.setBounds(12, 168, 201, 56);
	    contentPanel.add(textArea);
	    
	    JLabel lblNewLabel = new JLabel("New label");
	    lblNewLabel.setBounds(27, 25, 110, 90);
	    contentPanel.add(lblNewLabel);
	    
	    JLabel label_2 = new JLabel("New label");
	    label_2.setBounds(312, 25, 110, 90);
	    contentPanel.add(label_2);
	    
	    JLabel label_3 = new JLabel("New label");
	    label_3.setBounds(188, 25, 110, 90);
	    contentPanel.add(label_3);
	    
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//if(comment null)
						//Warning message
						//else
						//law.setcomment
						//law.setaccepted(true);
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * Creates an error dialog
	 * can called by this
	 * @param message
	 */
	private void showErrorDialog(String message){
		JOptionPane.showMessageDialog(this,
			    message,
			    "Opps error",
			    JOptionPane.ERROR_MESSAGE);
	}
}
