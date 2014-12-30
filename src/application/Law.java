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
	
	public ChartType getChart(int index){
		return this.chartTypes.get(index);
	}
	
	public int getNum_of_charts() {
		return numOfCharts;
	}
	
	public void setNum_of_charts(int num_of_charts) {
		this.numOfCharts = num_of_charts;
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
