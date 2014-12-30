package application;
import java.util.ArrayList;

public class Law {
	
	private String[] labelX,labelY;
	private ArrayList<ChartType> chartTypes;
	private ArrayList<int[]> valuesX;
	private ArrayList<float[]> valuesY;
	private int numOfCharts;
	private boolean accepted;
	private boolean lawChecked;
	private String comment;
	
	public Law(String[] labelX, String[] labelY,ArrayList<ChartType> chartTypes,
			ArrayList<int[]> valuesX,ArrayList<float[]> valuesY,int num_of_charts) {
		super();
		this.labelX = labelX;
		this.labelY = labelY;
		this.chartTypes=chartTypes;
		this.valuesX=valuesX;
		this.valuesY=valuesY;
		this.numOfCharts = num_of_charts;
	}
	
	public Law(){//constuctor gia laws xwris grafikes parastaseis 
		super();
	}
	
	public String getChartLabelX(int index) {
		return labelX[index];
	}
	
	public String getChartLabelY(int index) {
		return labelY[index];
	}
	
	
	public ChartType getChart_one() {
		return chart_one;
	}
	
	public void setChart_one(ChartType chart_one) {
		this.chart_one = chart_one;
	}
	
	public ChartType getChart_two() {
		return chart_two;
	}
	
	public void setChart_two(ChartType chart_two) {
		this.chart_two = chart_two;
	}
	
	public float[] getChart_one_values_x() {
		return chart_one_values_x;
	}
	
	public void setChart_one_values_x(float[] chart_one_values_x) {
		this.chart_one_values_x = chart_one_values_x;
	}
	
	public float[] getChart_one_values_y() {
		return chart_one_values_y;
	}
	
	public void setChart_one_values_y(float[] chart_one_values_y) {
		this.chart_one_values_y = chart_one_values_y;
	}
	
	public float[] getChart_two_values_x() {
		return chart_two_values_x;
	}
	
	public void setChart_two_values_x(float[] chart_two_values_x) {
		this.chart_two_values_x = chart_two_values_x;
	}
	
	public float[] getChart_two_values_y() {
		return chart_two_values_y;
	}
	
	public void setChart_two_values_y(float[] chart_two_values_y) {
		this.chart_two_values_y = chart_two_values_y;
	}
	
	public int getNum_of_charts() {
		return num_of_charts;
	}
	
	public void setNum_of_charts(int num_of_charts) {
		this.num_of_charts = num_of_charts;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.lawChecked=true;
		this.accepted = accepted;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public boolean checkLaw(){
		return this.lawChecked;
	}
	

}
