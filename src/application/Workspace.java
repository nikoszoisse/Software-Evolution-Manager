package application;
import java.util.ArrayList;
import java.util.Arrays;

public class Workspace {
	private ArrayList<Version> versions;
	private ArrayList<Law> laws;
	private int initOpNum;
	private int initDataStructuresNum;
	private float[] opRateOfGrowth;
	private float[] dataRateOfGrowth;
	private float[] opRateOfWork;
	private float[] dataRateOfWork;
	private float[] opComplexities,dataComplexities;
	private float[] opChanges,dataChanges;
	private float[] versionsPerYear;
	
	public Workspace(ArrayList<Version> version){
		this.versions=version;
	}
	
	public int getInitOpNum(){
		return initOpNum;
	}
	
	public int getInitDataStructuresNum(){
		return initDataStructuresNum;
	}
	
	public void computeRateOfGrowth(){
		opRateOfGrowth=new float[versions.size()];
		dataRateOfGrowth=new float[versions.size()];
		
		for(int i=1;i<versions.size();i++){
			versions.get(i).calculateOperationsRateOfGrowth(
					versions.get(i-1).getNumOfOperations());
			
			versions.get(i).calculateDataStructuresRateOfGrowth(
					versions.get(i-1).getNumOfDataStructures());
			
			opRateOfGrowth[i]=versions.get(i).getOpRateOfGrowth();
			dataRateOfGrowth[i]=versions.get(i).getDataStructuresRateOfGrowth();
		}
	}
	
	public void computeRateOfWork(){
		opRateOfWork=new float[versions.size()];
		dataRateOfWork=new float[versions.size()];
		
		for(int i=1;i<versions.size();i++){
			versions.get(i).computeRateOfWork(
					versions.get(i-1).getDate());
			opRateOfWork[i]=versions.get(i).getOpRateOfWork();
			dataRateOfWork[i]=versions.get(i).getDataStructuresRateOfWork();
		}
	}
	
	public void computeComplexities(){
		opComplexities=new float[versions.size()];
		dataComplexities=new float[versions.size()];
		
		for(int i=0;i<versions.size();i++){
			opComplexities[i]=versions.get(i).getOpComplexity();
			dataComplexities[i]=versions.get(i).getDataStructuresComplexity();
		}
	}
	
	public void computeChanges(){
		opChanges=new float[versions.size()];
		dataChanges=new float[versions.size()];
		
		for(int i=1;i<versions.size();i++){
			opChanges[i]=versions.get(i).getOpChanges();
			dataChanges[i]=versions.get(i).getDataChanges();
		}
	}
	
	public void calculateVersionsPerYear(){
		
	}
	
}

