
package application;

public class Version {
	//TODO initialize the fields
	private String softwareName,date;
	private int operationsNumber,dataStructuresNumber,id;
	private int opAdded,opDeleted,opUpdated;
	private int dataStructuresAdded,dataStructuresDeleted,dataStructuresUpdated;
	private float opComplexity,dataStructuresComplexity;
	private int opRateOfGrowth,DataStructuresRateOfGrowth;
	private double dataStructuresRateOfWork,opRateOfWork;
	private int year;
	
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
	
	public void computeRateOfWork(String prevVersionDate){
		int timePassed;
		String currVersDate[]=this.date.split("/");
		String prevVersDate[]=prevVersionDate.split("/");
		/*Κραταμε σε μια μεταβλητη τη χρονια σε περιπτωση 
		 * που το χρειαστούμε
		 */
		year=Integer.parseInt(currVersDate[2]);
		/*Βρίσκουμε το διαστημα των μερων,μηνων και χρόνων*/
		int dayDifference=Integer.parseInt(currVersDate[0])-
				Integer.parseInt(prevVersDate[0]);
		int monthDifference=Integer.parseInt(currVersDate[1])-
				Integer.parseInt(prevVersDate[1]);
		int yearDifference=Integer.parseInt(currVersDate[2])-
				Integer.parseInt(prevVersDate[2]);
		/*Κάνουμα absolute την διαφορα μερών και μηνών σε περίπτωση αρνητικών*/
		timePassed=Math.abs(dayDifference)+Math.abs(monthDifference)*30
				+365*yearDifference;
		
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
	
	public double getOpRateOfWork(){
		return this.opRateOfWork;
	}
	
	public double getDataStructuresRateOfWork(){
		return this.dataStructuresRateOfWork;
	}
	
	public String getDate(){
		return this.date;
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
	
	public int getOpChanges(){
		return (opAdded+opDeleted+opUpdated);
	}
	
	public int getDataChanges(){
		return (dataStructuresAdded+dataStructuresDeleted
				+dataStructuresUpdated);
	}
}
