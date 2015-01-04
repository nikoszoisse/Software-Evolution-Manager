package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
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
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYBarDataset;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartMouseEvent;

public class LawDialog extends JDialog implements ActionListener,ChartMouseListener{
	static public final int OK_BUTTON = 1;
	static public final int CANCEL_BUTTON = 2;
	private int choosen_button = -1;
	private final JPanel contentPanel =  new JPanel();
	private JPanel chartPane;
	private Law law;
	private JTextArea textArea;
	private ButtonGroup group;

	/**
	 * Create the dialog.
	 */
	public LawDialog(Law law,Frame owner) {
		super(owner, "Evaluate the "+law.getLawName());
		setModal(true);
		this.law = law;
		setBounds(125, 125, 900, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAcceptLaw = new JLabel("Accept the law?");
		lblAcceptLaw.setBounds(512, 442, 110, 14);
		contentPanel.add(lblAcceptLaw);
		
		JRadioButton rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBounds(511, 455, 50, 24);
		rdbtnYes.setActionCommand("yes");
		
		contentPanel.add(rdbtnYes);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(565, 455, 57, 24);
		rdbtnNo.setActionCommand("no");
		
		contentPanel.add(rdbtnNo);
	    
		group = new ButtonGroup();
	    group.add(rdbtnYes);
	    group.add(rdbtnNo);
	    
	    JLabel lblComment = new JLabel("Comment:");
	    lblComment.setBounds(149, 397, 71, 16);
	    contentPanel.add(lblComment);
	    
	    textArea = new JTextArea();
	    textArea.setToolTipText("Type here to add a comment");
	    textArea.setLineWrap(true);
	    textArea.setBorder(new LineBorder(Color.GRAY));
	    textArea.setBounds(149, 425, 286, 101);
	    contentPanel.add(textArea);
	    
	    chartPane = new JPanel();
	    chartPane.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
	    chartPane.setBounds(12, 10, 860, 378);
	    contentPanel.add(chartPane);
	    
	    if(law.checkLaw()){
		    JLabel lblYouHaveEvaluated = new JLabel("");
		    lblYouHaveEvaluated.setToolTipText("You have evaluated this law before.");
		    lblYouHaveEvaluated.setIcon(UIManager.getIcon("OptionPane.warningIcon"));
		    lblYouHaveEvaluated.setForeground(Color.ORANGE);
		    lblYouHaveEvaluated.setBounds(774, 428, 32, 51);
		    contentPanel.add(lblYouHaveEvaluated);
		    
		    if(law.isAccepted())
		    	rdbtnYes.setSelected(true);
		    else
		    	rdbtnNo.setSelected(true);
		    
		    textArea.setText(law.getComment());
	    }
	    
	    /*SET UP Charts if exists*/
	    if(law.getNum_of_charts() > 0)
	    	setUpCharts();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
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
			chart_panel.addChartMouseListener(this);
			chart_panel.setToolTipText("Click for bigger size");
			this.chartPane.add(chart_panel);
		}
		
	}

	private JFreeChart newBarChart(int index) {
		
		DefaultCategoryDataset  data_set = createCategoryBarDataSet(index);
				 
		
		String chart_title = law.getChartLabelX(index)+","+law.getChartLabelY(index);
		
		JFreeChart chart = 
				 ChartFactory.createBarChart(chart_title, law.getChartLabelX(index),
						 law.getChartLabelY(index), data_set);
		 return chart;
	}

	private DefaultCategoryDataset createCategoryBarDataSet(int index) {
		DefaultCategoryDataset ret_set = new DefaultCategoryDataset();
		String seriesKey = law.getChartLabelX(index)+","+law.getChartLabelY(index);
		
		double data[][] = {law.getChartValuesX(index),law.getChartValuesY(index)};

        if (data[0].length != data[1].length) {
            throw new IllegalArgumentException(
                "The 'data' array must contain two arrays with equal length.");
        }
        for(int i=0;i<data[0].length;i++){
        	ret_set.addValue(data[1][i], seriesKey, Double.toString(data[0][i]));
        }
		return ret_set;
	}

	private JFreeChart newLineChart(int index) {
		String seriesKey;
		
		DefaultXYDataset data_set = new DefaultXYDataset();
		double data[][] = {law.getChartValuesX(index),law.getChartValuesY(index)};
		
		seriesKey = law.getChartLabelX(index)+","+law.getChartLabelY(index);
		data_set.addSeries(seriesKey, data);
		 //Special case for Law 8
		 //has 2 series
		 if(law.getLawName() == "Law 8"){
			 double data1[][] = {law.getChartValuesX(index),law.getChartValuesY(index+1)};
			 seriesKey = law.getChartLabelX(index)+","+law.getChartLabelY(index+1);
			 data_set.addSeries(seriesKey, data1);
		 }
		 
		 String chart_title = law.getChartLabelX(index)+","+law.getChartLabelY(index);
		 JFreeChart chart = 
				 ChartFactory.createXYLineChart(chart_title,
				                 law.getChartLabelX(index), law.getChartLabelY(index), data_set,
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

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "OK": saveLawState();this.choosen_button = LawDialog.OK_BUTTON;break;
		}
		
	}

	private void saveLawState() {
		/*Check if user checked Yes Or NO option*/
		if(group.getSelection() == null){
			showErrorDialog("You must select 'Yes' or 'No'!!");
			return;
		}
		
		if(this.textArea.getText().isEmpty()){
			//WARNING
			int dialogResult = JOptionPane.showConfirmDialog (this, "Comment is empty. Continue?",
					"Warning",JOptionPane.YES_NO_OPTION);
			if(dialogResult ==JOptionPane.NO_OPTION)
				return;	
			this.law.setComment(null);
		}else{
			this.law.setComment(this.textArea.getText());
		}
		
		/*updae law if accepted or not*/
		String selected_radio = group.getSelection().getActionCommand();
		switch(selected_radio){
		case "yes": this.law.setAccepted(true);break;
		case "no": this.law.setAccepted(false);break;
		}
		
		this.dispose();
	}
	
	public int showLawDialog(){
		this.setVisible(true);
		
		return choosen_button;	
	}

	@Override
	public void chartMouseClicked(ChartMouseEvent event) {
		final JDialog h = new JDialog(this);
		h.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		h.setBounds(150, 150, 900, 600);
		h.getContentPane().add(new ChartPanel(event.getChart()));
		h.setVisible(true);
		
		h.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {}
			@Override
			public void focusLost(FocusEvent e) {
				//If User click outside close the Chart Dialog
				h.dispose();
			}
		});
	}

	@Override
	public void chartMouseMoved(ChartMouseEvent event) {
		// TODO Auto-generated method stub
		
	}
}
