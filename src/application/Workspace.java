package application;
import java.util.ArrayList;
import java.util.Arrays;

public class Workspace {
	private String title = "A Workspace";
	private ArrayList<Version> versions;
	private ArrayList<Law> laws;
	private int initOpNum;
	private int initDataStructuresNum;
	private float[] opPerVersion,dataPerVersion;
	private float[] opRateOfGrowth;
	private float[] dataRateOfGrowth;
	private float[] opRateOfWork;
	private float[] dataRateOfWork;
	private float[] opComplexities,dataComplexities;
	private float[] opChanges,dataChanges;
	private float[] versionsPerYear;
	private float[] maintainancePerVersion;
	
	public Workspace(String title,ArrayList<Version> version){
		this.title = title;
		this.versions=version;
		opPerVersion=new float[versions.size()];
		dataPerVersion=new float[versions.size()];
		opRateOfGrowth=new float[versions.size()];
		dataRateOfGrowth=new float[versions.size()];
		opRateOfWork=new float[versions.size()];
		dataRateOfWork=new float[versions.size()];
		opComplexities=new float[versions.size()];
		dataComplexities=new float[versions.size()];
		opChanges=new float[versions.size()];
		dataChanges=new float[versions.size()];
		maintainancePerVersion=new float[versions.size()];
	}
	
	public Version getVersion(int index){
		return versions.get(index);
	}
	
	public void computeOpAndDataPerVersion(){
		versions.get(0).calculateOpAndData(initOpNum,initDataStructuresNum);
		opPerVersion[0]=versions.get(0).getNumOfOperations();
		dataPerVersion[0]=versions.get(0).getNumOfDataStructures();
		for(int i=1;i<versions.size();i++){
			versions.get(i).calculateOpAndData(versions.get(i-1).getNumOfOperations()
					,versions.get(i-1).getNumOfDataStructures());
			opPerVersion[i]=versions.get(i).getNumOfOperations();
			dataPerVersion[i]=versions.get(i).getNumOfDataStructures();
		}
	}
	
	public void computeRateOfGrowth(){
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
		for(int i=1;i<versions.size();i++){
			versions.get(i).computeRateOfWork(
					versions.get(i-1).getDate());
			opRateOfWork[i]=versions.get(i).getOpRateOfWork();
			dataRateOfWork[i]=versions.get(i).getDataStructuresRateOfWork();
		}
	}
	
	public void computeComplexities(){
		for(int i=0;i<versions.size();i++){
			opComplexities[i]=versions.get(i).getOpComplexity();
			dataComplexities[i]=versions.get(i).getDataStructuresComplexity();
		}
	}
	
	public void computeChanges(){
		for(int i=1;i<versions.size();i++){
			opChanges[i]=versions.get(i).getOpChanges();
			dataChanges[i]=versions.get(i).getDataChanges();
		}
	}
	
	public void calculateMaintainance(){
		for(int i=0;i<versions.size();i++){
			maintainancePerVersion[i]=versions.get(i).getMaintainance();
		}
	}
	
	public void calculateVersionsPerYear(){
		
	}
	
	public String checkLaws(){
		String message="";
		int lawNumber=0;
		boolean lawEvaluation=true;
		for(int i=0;i<laws.size();i++){
			if(!laws.get(i).checkLaw()){
				lawNumber=i+1;
				lawEvaluation=false;
				break;
			}
		}
		if(!lawEvaluation){
			message="Law "+lawNumber+" has not evaluated";
		}
		
		return message;
	}
	
	public void checkLaw1(){
		/*laws.add(new Law(....))*/
		/*Mesa vazoume tous pinakes
		 * float[] opChanges
		 * float[] dataChanges
		 * float[] versionsPerYear*/
	}
	
	public void checkLaw2(){
		/*law.add(new Law(...))
		 * Oi pinakes einai
		 * float[] opComplexities
		 * float[] dataComplexities
		 * float[] mantainancePerVersion*/
	}
	
	public void checkLaw3(){
		/*law.add(new Law(..))
		 * Pinakes
		 * float[] opRateOfGrowth
		 * float[] opDataRateOfGrowth*/
	}
	
	public void checkLaw4(){
		/*law.add(new Law(..))
		 * Pinakes
		 * float[] opRateOfWork
		 * float[] dataRateOfWork*/
	}
	
	public void checkLaw5(){
		/*law.add(new Law(..))
		 * Pinakes
		 * float[] opRateOfGrowth
		 * float[] opDataRateOfGrowth*/
	}
	
	public void checkLaw6(){
		/*law.add(new Law(..))
		 * Pinakes
		 * float[] opPerVersion
		 * float[] dataPerVersion*/
	}
	
	public void checkLaw7(){
		/*law.add(new Law(...))*/
		if(laws.get(2).isAccepted()&&laws.get(6).isAccepted()){
			laws.get(7).setAccepted(true);
		}
	}
	
	public void checkLaw8(){
		
	}
	
	public float[] getOpComplexities(){
		return this.opComplexities;
	}
	public float[] getDataComplexities(){
		return this.dataComplexities;
	}
	public float[] getOpRateOfGrowth(){
		return this.opRateOfGrowth;
	}
	public float[] getDataRateOfGrowth(){
		return this.dataRateOfGrowth;
	}
	public float[] getOpRateOfWork(){
		return this.opRateOfWork;
	}
	public float[] getDataRateOfWork(){
		return this.dataRateOfWork;
	}
	public float[] getOpChanges(){
		return this.opChanges;
	}
	public float[] getDataChanges(){
		return this.dataChanges;
	}

	public String getTitle() {
		return title;
	}
}

