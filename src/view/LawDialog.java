package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

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
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("Αποδοχή Νόμου:");
		label.setBounds(512, 442, 110, 14);
		contentPanel.add(label);
		
		JRadioButton radioButton = new JRadioButton("Ναι");
		radioButton.setBounds(511, 455, 50, 24);
		contentPanel.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Όχι");
		radioButton_1.setBounds(565, 455, 57, 24);
		contentPanel.add(radioButton_1);
	    
		ButtonGroup group = new ButtonGroup();
	    group.add(radioButton);
	    group.add(radioButton_1);
	    
	    JLabel label_1 = new JLabel("Σχόλιο:");
	    label_1.setBounds(149, 397, 55, 16);
	    contentPanel.add(label_1);
	    
	    JTextArea textArea = new JTextArea();
	    textArea.setToolTipText("κάντε κλικ για να προσθεστε ένα σχόλιο εδώ..");
	    textArea.setLineWrap(true);
	    textArea.setBorder(new LineBorder(Color.GRAY));
	    textArea.setBounds(149, 425, 286, 101);
	    contentPanel.add(textArea);
	    
	    chartPane = new JPanel();
	    chartPane.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
	    chartPane.setBounds(12, 10, 860, 378);
	    contentPanel.add(chartPane);
	    /*SET UP Charts if exists*/
	    if(law.getNum_of_charts() > 0)
	    	setUpCharts();
	    //TODO Remove them
	    DefaultXYDataset data_set = new DefaultXYDataset();
		 double data[][] = {{1,2,3,4},{3,4,5,6}};
		 data_set.addSeries("Sieries not null", data);
		 JFreeChart chart = 
				 ChartFactory.createXYLineChart("Test Chart",
				                 "Label x", "Label y", data_set,
				                 PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chart_panel = new ChartPanel(chart);
		ChartPanel chart_panel_1 = new ChartPanel(chart);
		ChartPanel chart_panel_2 = new ChartPanel(chart);
		chart_panel.setPreferredSize(new Dimension(chartPane.getWidth()/3,chartPane.getHeight()/2));
		chart_panel_1.setPreferredSize(new Dimension(chartPane.getWidth()/3,chartPane.getHeight()/2));
		chart_panel_2.setPreferredSize(new Dimension(chartPane.getWidth()/3,chartPane.getHeight()/2 - 15));
		this.chartPane.add(chart_panel);
		this.chartPane.add(chart_panel_1);
		this.chartPane.add(chart_panel_2);
		//TODO TILL HERE DELETE
	    
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
		int num_of_charts = law.getNum_of_charts();
		for(int i=0;i<num_of_charts;i++){
			JFreeChart chart = null;
			if(law.getChart(i) == ChartType.CHART_LINES){
				chart = newLineChart(i);
			}
			else if(law.getChart(i) == ChartType.CHART_BARS){
				chart = newBarChart(i);
			}
			
			ChartPanel chart_panel = new ChartPanel(chart);
			chart_panel.setPreferredSize(new Dimension(chartPane.getWidth()/num_of_charts,chartPane.getHeight()/2));
			this.chartPane.add(chart_panel);
			
		}
		
	}

	private JFreeChart newBarChart(int i) {
		DefaultXYDataset data_set = new DefaultXYDataset();
		 double data[][] = {law.getChartValuesX(i),law.getChartValuesY(i)};
		 data_set.addSeries("", data);
		 
		IntervalXYDataset dataset = new XYBarDataset(data_set, 1.0);
		JFreeChart chart = 
				 ChartFactory.createXYBarChart(null, law.getChartLabelX(i), true, law.getChartLabelY(i), dataset);
		 return chart;
	}

	private JFreeChart newLineChart(int i) {
		DefaultXYDataset data_set = new DefaultXYDataset();
		 double data[][] = {law.getChartValuesX(i),law.getChartValuesY(i)};
		 data_set.addSeries("", data);
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
