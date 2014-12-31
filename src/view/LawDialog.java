package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import application.ChartType;
import application.Law;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYBarDataset;

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
	private JPanel chartPane;
	private Law law;

	/**
	 * Create the dialog.
	 */
	public LawDialog(Law law,Frame owner) {
		super(owner, "Αποτίμιση "+law.getLawName());
		this.law = law;
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
	    
	    chartPane = new JPanel();
	    chartPane.setBounds(12, 10, 412, 135);
	    contentPanel.add(chartPane);
	    /*SET UP Charts if exists*/
	    if(law.getNum_of_charts() > 0)
	    	setUpCharts();
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
	
	private void setUpCharts() {
		for(int i=0;i<law.getNum_of_charts();i++){
			JFreeChart chart = null;
			if(law.getChart(i) == ChartType.CHART_LINES){
				chart = newLineChart(i);
			}
			else if(law.getChart(i) == ChartType.CHART_BARS){
				chart = newBarChart(i);
			}
			
			ChartPanel chart_panel = new ChartPanel(chart);
			this.chartPane.add(chart_panel);
		}
		
	}

	private JFreeChart newBarChart(int i) {
		DefaultXYDataset data_set = new DefaultXYDataset();
		 double data[][] = {law.getChartValuesX(i),law.getChartValuesY(i)};
		 data_set.addSeries(null, data);
		 
		IntervalXYDataset dataset = new XYBarDataset(data_set, 1.0);
		JFreeChart chart = 
				 ChartFactory.createXYBarChart(null, law.getChartLabelX(i), true, law.getChartLabelY(i), dataset);
		 return chart;
	}

	private JFreeChart newLineChart(int i) {
		DefaultXYDataset data_set = new DefaultXYDataset();
		 double data[][] = {law.getChartValuesX(i),law.getChartValuesY(i)};
		 data_set.addSeries(null, data);
		 JFreeChart chart = 
				 ChartFactory.createXYLineChart("Test Chart",
				                 law.getChartLabelX(i), law.getChartLabelY(i), data_set,
				                 PlotOrientation.VERTICAL, true, true, false);
		 return chart;
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
