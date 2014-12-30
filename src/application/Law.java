package application;

public class Law {
	
	private String chart_one_label_x,chart_one_label_y;
	private String chart_two_label_x,chart_two_label_y;
	private ChartType chart_one,chart_two;
	private float[] chart_one_values_x,chart_one_values_y;
	private float[] chart_two_values_x,chart_two_values_y;
	private int num_of_charts;
	private boolean accepted;
	private boolean lawChecked;
	private String comment;
	
	public Law(String chart_one_label_x, String chart_one_label_y,
			String chart_two_label_x, String chart_two_label_y,
			ChartType chart_one, ChartType chart_two,
			float[] chart_one_values_x, float[] chart_one_values_y,
			float[] chart_two_values_x, float[] chart_two_values_y,
			int num_of_charts) {
		super();
		this.chart_one_label_x = chart_one_label_x;
		this.chart_one_label_y = chart_one_label_y;
		this.chart_two_label_x = chart_two_label_x;
		this.chart_two_label_y = chart_two_label_y;
		this.chart_one = chart_one;
		this.chart_two = chart_two;
		this.chart_one_values_x = chart_one_values_x;
		this.chart_one_values_y = chart_one_values_y;
		this.chart_two_values_x = chart_two_values_x;
		this.chart_two_values_y = chart_two_values_y;
		this.num_of_charts = num_of_charts;
	}
	
	public String getChart_one_label_x() {
		return chart_one_label_x;
	}
	
	public void setChart_one_label_x(String chart_one_label_x) {
		this.chart_one_label_x = chart_one_label_x;
	}
	
	public String getChart_one_label_y() {
		return chart_one_label_y;
	}
	
	public void setChart_one_label_y(String chart_one_label_y) {
		this.chart_one_label_y = chart_one_label_y;
	}
	
	public String getChart_two_label_x() {
		return chart_two_label_x;
	}
	
	public void setChart_two_label_x(String chart_two_label_x) {
		this.chart_two_label_x = chart_two_label_x;
	}
	
	public String getChart_two_label_y() {
		return chart_two_label_y;
	}
	
	public void setChart_two_label_y(String chart_two_label_y) {
		this.chart_two_label_y = chart_two_label_y;
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
