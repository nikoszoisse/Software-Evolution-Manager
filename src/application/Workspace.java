package application;
import java.util.ArrayList;
import java.util.Arrays;

public class Workspace {
	private String title = "A Workspace";
	private ArrayList<Version> versions;
	private ArrayList<Law> laws=new ArrayList();
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
	private int[] versionsId,year;
	
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
		versionsId=new int[versions.size()];
	}
	
	public Law getLaw(int index){
		return this.laws.get(index);
	}
	
	public void setUpVersionsId(){
		for(int i=0;i<versions.size();i++){
			versionsId[i]=i+1;
		}
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
					versions.get(i-1));
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
		int yearCounter=1;
		int currPos=-1;/*me auth th metavlhth tha vroume poso xwro tha exei
		o pinakas versionsPerYear*/
		/*xrhshmopoioume temp Pinaka gia na mikrinoume to
		 * megethos tou pinaka versionsPerYear wste na mn exei
		 * size=versions.size()
		 */
		float[] tempArray=new float[versions.size()];
		ArrayList<Integer> tempList=new ArrayList();
		for(int i=0;i<versions.size();i++){
			if(!tempList.contains(versions.get(i).getYear())){
				yearCounter=1;
				tempList.add(versions.get(i).getYear());
				currPos++;
			}
			else{
				yearCounter++;
			}
			tempArray[currPos]=yearCounter;
		}
		versionsPerYear=new float[currPos+1];
		for(int i=0;i<=currPos;i++){
			versionsPerYear[i]=tempArray[i];
		}
	}
	
	public String checkIfLawsEvaluated(){
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
	
	public void setUpLaw1(){
		ArrayList<ChartType> chartType =new ArrayList();
		//TODO private oles oi alles methodoi ??? @NIKOS nai!!
		chartType.add(ChartType.CHART_BARS);
		chartType.add(ChartType.CHART_BARS);
		chartType.add(ChartType.CHART_BARS);
		String[] labelY=new String[2];
		labelY[0]="number of Changes";
		labelY[1]="number of Versions";
		String[] labelX=new String[2];
		labelX[0]="Versions Id";
		labelX[1]="Year";
		ArrayList<int[]> valuesX=new ArrayList();
		ArrayList<float[]> valuesY=new ArrayList();
		valuesY.add(opChanges);
		valuesY.add(dataChanges);
		valuesY.add(versionsPerYear);
		valuesX.add(versionsId);
		valuesX.add(year);
		laws.add(0,new Law("Law 1",labelX,labelY,chartType,valuesX,valuesY,2));
		
	}
	
	public void setUpLaw2(){
		ArrayList<ChartType> chartType =new ArrayList();
		chartType.add(ChartType.CHART_LINES);
		chartType.add(ChartType.CHART_BARS);
		String[] labelY=new String[2];
		labelY[0]="Complexity";
		labelY[1]="number of actions";
		String[] labelX=new String[1];
		labelX[0]="versionsId";
		ArrayList<int[]> valuesX=new ArrayList();
		ArrayList<float[]> valuesY=new ArrayList();
		valuesX.add(versionsId);
		valuesY.add(opComplexities);
		valuesY.add(dataComplexities);
		valuesY.add(maintainancePerVersion);
		laws.add(1,new Law("Law 2",labelX,labelY,chartType,valuesX,valuesY,2));
	}
	
	public void setUpLaw3(){
		ArrayList<ChartType> chartType =new ArrayList();
		chartType.add(ChartType.CHART_LINES);
		chartType.add(ChartType.CHART_LINES);
		String[] labelY=new String[1];
		labelY[0]="RateOfGrowth";
		String[] labelX=new String[1];
		labelX[0]="versionsId";
		ArrayList<int[]> valuesX=new ArrayList();
		ArrayList<float[]> valuesY=new ArrayList();
		valuesX.add(versionsId);
		valuesY.add(opRateOfGrowth);
		valuesY.add(dataRateOfGrowth);
		laws.add(2,new Law("Law 3",labelX,labelY,chartType,valuesX,valuesY,2));
	}
	
	public void setUpLaw4(){
		ArrayList<ChartType> chartType =new ArrayList();
		chartType.add(ChartType.CHART_LINES);
		chartType.add(ChartType.CHART_LINES);
		String[] labelY=new String[1];
		labelY[0]="RateOfGrowth";
		String[] labelX=new String[1];
		labelX[0]="versionsId";
		ArrayList<int[]> valuesX=new ArrayList();
		ArrayList<float[]> valuesY=new ArrayList();
		valuesX.add(versionsId);
		valuesY.add(opRateOfWork);
		valuesY.add(dataRateOfWork);
		laws.add(3,new Law("Law 4",labelX,labelY,chartType,valuesX,valuesY,2));
	}
	
	public void setUpLaw5(){//Epanalhpsh Kwdika
		ArrayList<ChartType> chartType =new ArrayList();
		chartType.add(ChartType.CHART_LINES);
		chartType.add(ChartType.CHART_LINES);
		String[] labelY=new String[1];
		labelY[0]="RateOfGrowth";
		String[] labelX=new String[1];
		labelX[0]="versionsId";
		ArrayList<int[]> valuesX=new ArrayList();
		ArrayList<float[]> valuesY=new ArrayList();
		valuesX.add(versionsId);
		valuesY.add(opRateOfGrowth);
		valuesY.add(dataRateOfGrowth);
		laws.add(4,new Law("Law 5",labelX,labelY,chartType,valuesX,valuesY,2));
	}
	
	public void setUpLaw6(){
		ArrayList<ChartType> chartType =new ArrayList();
		chartType.add(ChartType.CHART_LINES);
		chartType.add(ChartType.CHART_LINES);
		String[] labelY=new String[2];
		labelY[0]="number of Operations";
		labelY[1]="number of DataStructures";
		String[] labelX=new String[1];
		labelX[0]="Version Id";
		ArrayList<int[]> valuesX=new ArrayList();
		ArrayList<float[]> valuesY=new ArrayList();
		valuesX.add(versionsId);
		valuesY.add(opPerVersion);
		valuesY.add(dataPerVersion);
		laws.add(5,new Law("Law 6",labelX,labelY,chartType,valuesX,valuesY,2));
	}
	
	public void setUpLaw7(){
		laws.add(6,new Law("Law 7"));
		//TODO Is automated?
		if(laws.get(2).isAccepted()&&laws.get(6).isAccepted()){
			laws.get(7).setAccepted(true);
		}
	}
	
	public void setUpLaw8(){
		//TODO LAW 8
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

	public int getNumOfVersions() {
		return this.versions.size();
	}
	
	public int getNumOfEvaluatedLaws(){
		int ret_val = 0;
		for(int i=0;i<laws.size();i++){
			if(laws.get(i).checkLaw())
				ret_val++;
		}
		return ret_val;
		
	}
}

