import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Main {

	public static void main(String[] args) {		
		String[] train_Lines = IO_Operations .readCommandFile(args[0]);
		String[] test_Lines = IO_Operations .readCommandFile(args[1]);
		String[] vocabulary_Lines = IO_Operations .readCommandFile(args[2]);
		String[] similarity_Lines = IO_Operations .readCommandFile(args[3]);
		PrintWriter outWriter = IO_Operations.openFile(args[4]);

		ArrayList<String> vocabulary_List = new ArrayList<String>();
		ArrayList<Sense> sense_List = new ArrayList<Sense>();
		ArrayList<Sense> test_List = new ArrayList<Sense>();
		Hashtable<Integer, Integer> training_File_Table = new Hashtable<Integer, Integer>();
		
		VocabularyFileOperation.parse_Vocabulary_File(vocabulary_Lines, vocabulary_List);
		TrainingFileOperation.parse_Train_File(train_Lines,vocabulary_List,sense_List,training_File_Table);
		TestFileOperation.parse_Test_File(test_Lines,vocabulary_List,sense_List,test_List);
		TestFileOperation.calculating_Naive_Bayes_Algorithm(test_List, sense_List, training_File_Table, outWriter);
		SimilarityFileOperation.parsingSimilarityFile(outWriter, similarity_Lines);
		
		testing_Model(sense_List, test_List, training_File_Table);
		
		outWriter.close();
		
	}
	
	public static void testing_Model(ArrayList<Sense> sense_List,ArrayList<Sense> test_List, Hashtable<Integer, Integer> training_File_Table){
		System.out.println("TrainList:");
		print_senseList(sense_List);
		System.out.println();
		System.out.println("test list:");
		print_testList(test_List);
		System.out.println();
		System.out.println("training_File_Table for calculation of P(c) in naive Bayes algorithm:");
		print_training_File_Table(training_File_Table);
		//IO_Operations.writeToFile(vocabulary_Lines,args[3]);
	}
	
	public static void print_training_File_Table(Hashtable<Integer, Integer> training_File_Table){
		Set set = training_File_Table.entrySet();
	    Iterator iterator = set.iterator();
	    while(iterator.hasNext()){
	    	Map.Entry mentry = (Map.Entry)iterator.next();
	    	int value = (int) mentry.getValue();
	    	int word = (int) mentry.getKey();
	    	System.out.print(word + " : " +  value + "		");
	    }
	    System.out.println();
	}
	
	public static void print_senseList(ArrayList<Sense> sense_List){
		for(Sense sense : sense_List){
			System.out.println("sense number: " + sense.getSenseNumber());
			System.out.println("reference number: " + sense.getReferenceNumber());
			int count_Array[] = sense.calculate_Word_Count();
			System.out.println("sense count: " + count_Array[0]);
			System.out.println("sense unique count: " + count_Array[1]);
			
			Set set = sense.getTrainTable().entrySet();
		    Iterator iterator = set.iterator();
		    while(iterator.hasNext()){
		    	Map.Entry mentry = (Map.Entry)iterator.next();
		    	int value = (int) mentry.getValue();
		    	String word = (String) mentry.getKey();
		    	System.out.print(word + " " +  value + "	");
		    }
		    System.out.println();
		}
	}
	
	public static void print_testList(ArrayList<Sense> test_List){
		for(Sense sense : test_List){
			System.out.println(sense.getReferenceNumber());
			
			Set set = sense.getTrainTable().entrySet();
		    Iterator iterator = set.iterator();
		    while(iterator.hasNext()){
		    	Map.Entry mentry = (Map.Entry)iterator.next();
		    	int value = (int) mentry.getValue();
		    	String word = (String) mentry.getKey();
		    	System.out.print(word + " " +  value + "	");
		    }
		    System.out.println();
		}
	}

}
