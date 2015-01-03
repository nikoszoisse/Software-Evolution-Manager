package application;

public class Version {
	private String softwareName,date;
	private int initOp,initData,operationsNumber,dataStructuresNumber,id;
	private int opAdded,opDeleted,opUpdated;
	private int dataStructuresAdded,dataStructuresDeleted,dataStructuresUpdated;
	private float opComplexity,dataStructuresComplexity;
	private float opRateOfGrowth,DataStructuresRateOfGrowth;
	private float dataStructuresRateOfWork,opRateOfWork;
	private int year,day,month;
	
	public Version(String softwareName,int initialOperations,int initialData,
			int id, String date, int opAdd,
			int opDel, int opUpd, int dataStructuresAdd, int dataStructuresDel,
			int dataStructuresUpd) {
		this.softwareName=softwareName;
		this.id=id;
		this.date=date;
		this.initOp=initialOperations;
		this.initData=initialData;
		this.opAdded=opAdd;
		this.opDeleted=opDel;
		this.opUpdated=opUpd;
		this.dataStructuresAdded=dataStructuresAdd;
		this.dataStructuresUpdated=dataStructuresUpd;
		this.dataStructuresDeleted=dataStructuresDel;
		calculateComplexities();
		setDayYearMonth();
	}
	
	private void calculateComplexities(){
		if(opAdded==0)
			opAdded=1;
		opComplexity=(opUpdated+opDeleted)/opAdded;
		if(dataStructuresAdded==0)
			dataStructuresAdded=1;
		dataStructuresComplexity=(dataStructuresUpdated+dataStructuresDeleted)/
				dataStructuresAdded;
	}
	
	public void calculateOpAndData(int previousOp,int previousData){
		operationsNumber=opAdded+opDeleted+previousOp;
		dataStructuresNumber=dataStructuresAdded+dataStructuresDeleted+
				previousData;
	}
	
	public void calculateOperationsRateOfGrowth(int opNumOfPreviousVersion){
		opRateOfGrowth=this.operationsNumber-opNumOfPreviousVersion;
	}
	
	public void calculateDataStructuresRateOfGrowth(int PrevNumOfDataStructures)
	{
		DataStructuresRateOfGrowth=this.dataStructuresNumber-
				PrevNumOfDataStructures;
	}
	
	public void computeRateOfWork(Version prevVersion){
		int timePassed=1/*Den exei vrethei akoma lush */;
		
		opRateOfWork=(opAdded+opDeleted+opUpdated)/timePassed;
		dataStructuresRateOfWork=(dataStructuresAdded+dataStructuresDeleted
				+dataStructuresUpdated)/timePassed;
	}
	
	public int getMaintainance(){
		int mantainance=opDeleted+opUpdated+
				dataStructuresDeleted+dataStructuresUpdated;
		return mantainance;
	}
	
	private void setDayYearMonth(){
		String[] versDate=date.split("/");
		this.day=Integer.parseInt(versDate[0]);
		this.month=Integer.parseInt(versDate[1]);
		this.year=Integer.parseInt(versDate[2]);
	}
	public int getNumOfOperations(){
		return this.operationsNumber;
	}
	
	public int getNumOfDataStructures(){
		return this.dataStructuresNumber;
	}
	
	public float getOpRateOfGrowth(){
		return this.opRateOfGrowth;
	}
	
	public float getDataStructuresRateOfGrowth(){
		return this.DataStructuresRateOfGrowth;
	}
	
	public float getOpComplexity(){
		return this.opComplexity;
	}
	
	public float getDataStructuresComplexity(){
		return this.dataStructuresComplexity;
	}
	
	public float getOpRateOfWork(){
		return this.opRateOfWork;
	}
	
	public float getDataStructuresRateOfWork(){
		return this.dataStructuresRateOfWork;
	}
	
	public String getDate(){
		return this.date;
	}

	public int getDay(){
		return this.day;
	}
	public int getMonth(){
		return this.month;
	}
	public int getYear(){
		return this.year;
	}
	
	public int getId(){
		return this.id;
	}
	
	public int getOpAdded(){
		return this.opAdded;
	}
	
	public float getOpChanges(){
		return (opAdded+opDeleted+opUpdated);
	}
	
	public float getDataChanges(){
		return (dataStructuresAdded+dataStructuresDeleted
				+dataStructuresUpdated);
	}
}

