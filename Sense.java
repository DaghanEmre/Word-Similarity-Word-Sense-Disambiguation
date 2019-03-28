import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Sense {
	
	private Hashtable<String, Integer> trainTable;
	private int senseNumber;
	private int referenceNumber;
	
	
	public Sense(int senseNumber) {
		super();
		trainTable = new Hashtable<String, Integer>();
		this.referenceNumber = 0;
		this.senseNumber = senseNumber;
	}
	
	public Sense() {
		super();
		trainTable = new Hashtable<String, Integer>();
		this.senseNumber = 1;
	}
	
	public int[] calculate_Word_Count(){
		int count =0,unique_Count=0;
		
		Set set = this.getTrainTable().entrySet();
	    Iterator iterator = set.iterator();
	    while(iterator.hasNext()){
	    	Map.Entry mentry = (Map.Entry)iterator.next();
	    	int value = (int) mentry.getValue();
	    	String word = (String) mentry.getKey();
	    	
	    	count += value;
	    	++unique_Count;
	    }
	    int array[] = {count,unique_Count};
	    return array;
	}

	public int getSenseNumber() {
		return senseNumber;
	}
	public void setSenseNumber(int senseNumber) {
		this.senseNumber = senseNumber;
	}
	public int getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(int referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public Hashtable<String, Integer> getTrainTable() {
		return trainTable;
	}
	
}
