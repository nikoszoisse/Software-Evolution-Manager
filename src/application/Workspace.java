package application;
import java.util.ArrayList;

public class Workspace {
	private String title = "A Workspace";
	private ArrayList<Version> versions;
	private ArrayList<Law> laws=new ArrayList<Law>();
	private int initOpNum;
	private int initDataStructuresNum;
	private double[] opPerVersion;
	private double[] dataPerVersion;
	private double[] opRateOfGrowth;
	private double[] dataRateOfGrowth;
	private double[] opRateOfWork;
	private double[] dataRateOfWork;
	private double[] opComplexities,dataComplexities;
	private double[] opChanges;
	private double[] dataChanges;
	private double[] versionsPerYear;
	private double[] maintainancePerVersion;
	private double[] versionsId;
	private double[] SFuturePerVersion;
	private double[] versionsYear;
	
	public Workspace(String title,ArrayList<Version> version){
		this.title = title;
		this.versions=version;
		opPerVersion=new double[versions.size()];
		dataPerVersion=new double[versions.size()];
		opRateOfGrowth=new double[versions.size()];
		dataRateOfGrowth=new double[versions.size()];
		opRateOfWork=new double[versions.size()];
		dataRateOfWork=new double[versions.size()];
		opComplexities=new double[versions.size()];
		dataComplexities=new double[versions.size()];
		opChanges=new double[versions.size()];
		dataChanges=new double[versions.size()];
		maintainancePerVersion=new double[versions.size()];
		versionsId=new double[versions.size()];
		SFuturePerVersion=new double[getNumOfVersions()];
		
		this.initialize();
		this.setUpLaws();
	}
	
	public void initialize(){
		this.setUpVersionsId();
		this.computeOpAndDataPerVersion();
		this.computeChanges();
		this.computeComplexities();
		this.computeRateOfGrowth();
		this.computeRateOfWork();
		this.calculateMaintainance();
		this.calculateVersionsPerYear();
		this.computeFormulaPerVerion();
	}
	
	public void setUpLaws(){
		this.setUpLaw1();
		this.setUpLaw2();
		this.setUpLaw3();
		this.setUpLaw4();
		this.setUpLaw5();
		this.setUpLaw6();
		this.setUpLaw7();
		this.setUpLaw8();
	}
	
	public Law getLaw(int index){
		return this.laws.get(index);
	}
	
	private void setUpVersionsId(){
		for(int i=0;i<versions.size();i++){
			versionsId[i]=i+1;
		}
	}
	
	public Version getVersion(int index){
		return versions.get(index);
	}
	
	private void computeOpAndDataPerVersion(){
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
	
	private void computeRateOfGrowth(){
		for(int i=1;i<versions.size();i++){
			versions.get(i).calculateOperationsRateOfGrowth(
					versions.get(i-1).getNumOfOperations());
			
			versions.get(i).calculateDataStructuresRateOfGrowth(
					versions.get(i-1).getNumOfDataStructures());
			
			opRateOfGrowth[i]=versions.get(i).getOpRateOfGrowth();
			dataRateOfGrowth[i]=versions.get(i).getDataStructuresRateOfGrowth();
		}
	}
	
	private void computeRateOfWork(){
		for(int i=1;i<versions.size();i++){
			versions.get(i).computeRateOfWork(
					versions.get(i-1));
			opRateOfWork[i]=versions.get(i).getOpRateOfWork();
			dataRateOfWork[i]=versions.get(i).getDataStructuresRateOfWork();
		}
	}
	
	private void computeComplexities(){
		for(int i=0;i<versions.size();i++){
			opComplexities[i]=versions.get(i).getOpComplexity();
			dataComplexities[i]=versions.get(i).getDataStructuresComplexity();
		}
	}
	
	private void computeChanges(){
		for(int i=1;i<versions.size();i++){
			opChanges[i]=versions.get(i).getOpChanges();
			dataChanges[i]=versions.get(i).getDataChanges();
		}
	}
	
	private void calculateMaintainance(){
		for(int i=0;i<versions.size();i++){
			maintainancePerVersion[i]=versions.get(i).getMaintainance();
		}
	}
	
	private void calculateVersionsPerYear(){
		int yearCounter=1;
		int currPos=-1;/*me auth th metavlhth tha vroume poso xwro tha exei
		o pinakas versionsPerYear*/
		/*xrhshmopoioume temp Pinaka gia na mikrinoume to
		 * megethos tou pinaka versionsPerYear wste na mn exei
		 * size=versions.size()
		 */
		float[] tempArray=new float[versions.size()];

		ArrayList<Integer> tempList=new ArrayList<Integer>();
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
		
		versionsPerYear=new double[currPos+1];
		versionsYear = new double[currPos+1];
		for(int i=0;i<=currPos;i++){
			versionsPerYear[i]=tempArray[i];
			versionsYear[i] = tempList.get(i);
		}
		
	}
	
	private double computeE(ArrayList<Integer> SCurrents,
			int SVersion,int pos){
		double E=1,sum=0;
		if(pos!=0){
			for(int i=1;i<pos;i++){
				sum+=1/(SCurrents.get(i)^2);
			}
			if(sum==0)
				sum=1;
			E=((SVersion-SCurrents.get(0))/sum);
		}
		return E;
	}
	
	private double computeFormula(int size){
		double avgE=0,sumE=0;
		double SFuture;
		ArrayList<Integer>SCurrents=new ArrayList<Integer>();
		for(int i=0;i<size;i++){
			SCurrents.add(getVersion(i).getNumOfOperations());
		}
		for(int i=1;i<size;i++){
			sumE+=computeE(SCurrents,SCurrents.get(i),i);
		}
		if(size!=0){
			avgE=sumE/size;
		}
		SFuture=SCurrents.get(size-1)+
				(avgE/(SCurrents.get(size-1)^2));
		return SFuture;
		
	}
	
	private void computeFormulaPerVerion(){
		for(int i=0;i<SFuturePerVersion.length;i++){
			SFuturePerVersion[i]=computeFormula(i+1);
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
	
	private void setUpLaw1(){
		ArrayList<ChartType> chartType =new ArrayList<ChartType>();
		chartType.add(ChartType.CHART_BARS);
		chartType.add(ChartType.CHART_BARS);
		chartType.add(ChartType.CHART_BARS);
		String[] labelY=new String[3];
		labelY[0]="number of Changes";
		labelY[1]="number of Versions";
		labelY[2]="Chart label 3 Y";
		String[] labelX=new String[3];
		labelX[0]="Versions Id";
		labelX[1]="Version Id";
		labelX[2]="Year";
		ArrayList<double[]> valuesX=new ArrayList<double[]>();
		ArrayList<double[]> valuesY=new ArrayList<double[]>();
		valuesX.add(versionsId);
		valuesX.add(versionsId);
		valuesX.add(versionsYear);
		valuesY.add(opChanges);
		valuesY.add(dataChanges);
		valuesY.add(versionsPerYear);
		laws.add(0,new Law("Law 1",labelX,labelY,chartType,valuesX,valuesY,3));
		
	}
	
	private void setUpLaw2(){
		ArrayList<ChartType> chartType =new ArrayList<ChartType>();
		chartType.add(ChartType.CHART_LINES);
		chartType.add(ChartType.CHART_LINES);
		String[] labelY=new String[2];
		labelY[0]="Complexity";
		labelY[1]="number of actions";
		String[] labelX=new String[2];
		labelX[0]="versionsId";
		labelX[1]="versionsId";
		ArrayList<double[]> valuesX=new ArrayList<double[]>();
		ArrayList<double[]> valuesY=new ArrayList<double[]>();
		valuesX.add(versionsId);
		valuesX.add(versionsId);
		//TODO WHY 3 Y damm? valuesY.add(opComplexities);
		valuesY.add(dataComplexities);
		valuesY.add(maintainancePerVersion);
		laws.add(1,new Law("Law 2",labelX,labelY,chartType,valuesX,valuesY,2));
	}
	
	private void setUpLaw3(){
		ArrayList<ChartType> chartType =new ArrayList<ChartType>();
		chartType.add(ChartType.CHART_LINES);
		chartType.add(ChartType.CHART_LINES);
		String[] labelY=new String[2];
		labelY[0]="Op RateOfGrowth";
		labelY[1]="Data RateOfGrowth";
		String[] labelX=new String[2];
		labelX[0]="versionsId";
		labelX[1]="versionsId";
		ArrayList<double[]> valuesX=new ArrayList<double[]>();
		ArrayList<double[]> valuesY=new ArrayList<double[]>();
		valuesX.add(versionsId);
		valuesX.add(versionsId);
		valuesY.add(opRateOfGrowth);
		valuesY.add(dataRateOfGrowth);
		laws.add(2,new Law("Law 3",labelX,labelY,chartType,valuesX,valuesY,2));
	}
	
	private void setUpLaw4(){
		ArrayList<ChartType> chartType =new ArrayList<ChartType>();
		chartType.add(ChartType.CHART_LINES);
		chartType.add(ChartType.CHART_LINES);
		String[] labelY=new String[2];
		labelY[0]="Op RateOfWork";
		labelY[1]="Data RateOfWork";
		String[] labelX=new String[2];
		labelX[0]="versionsId";
		labelX[1]="versionsId";
		ArrayList<double[]> valuesX=new ArrayList<double[]>();
		ArrayList<double[]> valuesY=new ArrayList<double[]>();
		valuesX.add(versionsId);
		valuesX.add(versionsId);
		valuesY.add(opRateOfWork);
		valuesY.add(dataRateOfWork);
		laws.add(3,new Law("Law 4",labelX,labelY,chartType,valuesX,valuesY,2));
	}
	
	private void setUpLaw5(){//Epanalhpsh Kwdika
		ArrayList<ChartType> chartType =new ArrayList<ChartType>();
		chartType.add(ChartType.CHART_LINES);
		chartType.add(ChartType.CHART_LINES);
		String[] labelY=new String[2];
		labelY[0]="Op RateOfWork";
		labelY[1]="Data RateOfWork";
		String[] labelX=new String[2];
		labelX[0]="versionsId";
		labelX[1]="versionsId";
		ArrayList<double[]> valuesX=new ArrayList<double[]>();
		ArrayList<double[]> valuesY=new ArrayList<double[]>();
		valuesX.add(versionsId);
		valuesX.add(versionsId);
		valuesY.add(opRateOfWork);
		valuesY.add(dataRateOfWork);
		laws.add(4,new Law("Law 5",labelX,labelY,chartType,valuesX,valuesY,2));
	}
	
	private void setUpLaw6(){
		ArrayList<ChartType> chartType =new ArrayList<ChartType>();
		chartType.add(ChartType.CHART_LINES);
		chartType.add(ChartType.CHART_LINES);
		String[] labelY=new String[2];
		labelY[0]="number of Operations";
		labelY[1]="number of DataStructures";
		String[] labelX=new String[2];
		labelX[0]="Version Id";
		labelX[1]="Version Id";
		ArrayList<double[]> valuesX=new ArrayList<double[]>();
		ArrayList<double[]> valuesY=new ArrayList<double[]>();
		valuesX.add(versionsId);
		valuesX.add(versionsId);
		valuesY.add(opPerVersion);
		valuesY.add(dataPerVersion);
		laws.add(5,new Law("Law 6",labelX,labelY,chartType,valuesX,valuesY,2));
	}
	
	private void setUpLaw7(){
		laws.add(6,new Law("Law 7"));
		laws.get(6).addDependedLaw(2);
		laws.get(6).addDependedLaw(6);
		
		if(laws.get(1).isAccepted()&&laws.get(5).isAccepted()){
			laws.get(6).setAccepted(true);
		}
	}
	
	private void setUpLaw8(){
		ArrayList<ChartType> chartType=new ArrayList<ChartType>();
		chartType.add(ChartType.CHART_LINES);
		String[] labelY=new String[2];
		labelY[0]="Estimated Operations";
		labelY[1]="Number of Operations";
		String[] labelX= new String[1];
		labelX[0]="Version Id";
		ArrayList<double[]> valuesX=new ArrayList<double[]>();
		ArrayList<double[]> valuesY=new ArrayList<double[]>();
		valuesX.add(versionsId);
		valuesY.add(SFuturePerVersion);
		valuesY.add(opPerVersion);
		laws.add(7,new Law("Law 8",labelX,labelY,chartType,valuesX,valuesY,1));
	}
	
	public double[] getOpComplexities(){
		return this.opComplexities;
	}
	public double[] getDataComplexities(){
		return this.dataComplexities;
	}
	public double[] getOpRateOfGrowth(){
		return this.opRateOfGrowth;
	}
	public double[] getDataRateOfGrowth(){
		return this.dataRateOfGrowth;
	}
	public double[] getOpRateOfWork(){
		return this.opRateOfWork;
	}
	public double[] getDataRateOfWork(){
		return this.dataRateOfWork;
	}
	public double[] getOpChanges(){
		return this.opChanges;
	}
	public double[] getDataChanges(){
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

