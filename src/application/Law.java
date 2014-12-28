package application;
import java.util.ArrayList;
import java.util.Arrays;

public class Law {
	private int lawId;
	private String labelX,labelY;
	private String comment;
	private boolean accepted;
	float[] valuesX,valuesY;
	ArrayList<Version> versions;
	
	public Law(String xLabel,String yLabel,float[] xValues,float[] yValues,
			int lawId,ArrayList versions){
		this.labelX=xLabel;
		this.labelY=yLabel;
		this.lawId=lawId;
		this.valuesX=xValues;
		this.valuesY=yValues;
		this.versions=versions;
		
	}
	
	public void checkLaw(){
		if(lawId==1){
			
		}
	}
	
	
	public void setComment(String userComment){
		this.comment=userComment;
	}
	public String getComment(){
		return comment;
	}
	
	public boolean checkConfirmation(){
		return accepted;
	}
}
