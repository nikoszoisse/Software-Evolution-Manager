package application;

public class Version {
	//TODO initialize the fields
	private String softwareName,date;
	private int operationsNumber,dataStructuresNumber,id;
	private int opAdded,opDeleted,opUpdated;
	private int dataStructuresAdded,dataStructuresDeleted,dataStructuresUpdated;
	private float opComplexity,dataStructuresComplexity;
	private int opRateOfGrowth,DataStructuresRateOfGrowth;
	private float dataStructuresRateOfWork,opRateOfWork;
	
	public Version(String softwareName, int initOperationsNum,
			int initDataStructuresNum, int id, String date, int opAdd,
			int opDel, int opUpd, int dataStructuresAdd, int dataStructuresDel,
			int dataStructuresUpd) {
		// TODO Auto-generated constructor stub
		this.softwareName=softwareName;
		this.id=id;
		this.date=date;
		this.operationsNumber=initOperationsNum;
		this.opAdded=opAdd;
		this.opDeleted=opDel;
		this.opUpdated=opUpd;
		this.dataStructuresNumber=initDataStructuresNum;
		this.dataStructuresAdded=dataStructuresAdd;
		this.dataStructuresUpdated=dataStructuresUpd;
		this.dataStructuresDeleted=dataStructuresDel;
		calculateComplexities();
	}
	
	private void calculateComplexities(){
		opComplexity=(opUpdated+opDeleted)/opAdded;
		dataStructuresComplexity=(dataStructuresUpdated+dataStructuresDeleted)/
				dataStructuresAdded;
	}
	
	public void calculateOperationsRateOfGrowth(int opNumOfPreviousVersion){
		opRateOfGrowth=this.operationsNumber-opNumOfPreviousVersion;
	}
	
	public void calculateDataStructuresRateOfGrowth(int PrevNumOfDataStructures)
	{
		DataStructuresRateOfGrowth=this.dataStructuresNumber-
				PrevNumOfDataStructures;
	}
	
	public void computeOperationsRateOfWork(String prevVersionDate){
		//int timePassed= Πως να υπολογισω το χρονικο διαστημα ?
		
		opRateOfWork=(opAdded+opDeleted+opUpdated)/timePassed;
		dataStructuresRateOfWork=(dataStructuresAdded+dataStructuresDeleted
				+dataStructuresUpdated)/timePassed;
	}
	
	public int getNumOfOperations(){
		return this.operationsNumber;
	}
	
	public int getNumOfDataStructures(){
		return this.dataStructuresNumber;
	}
	
	public int getOpRateOfGrowth(){
		return this.opRateOfGrowth;
	}
	
	public int getDataStructuresRateOfGrowth(){
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

}
