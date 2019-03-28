import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TestFileOperation {

	public static void parse_Test_File(String[] test_Lines,ArrayList<String> vocabulary_List,ArrayList<Sense> sense_List, ArrayList<Sense> test_List){
		ArrayList<String> temp_List = new ArrayList<String>();
		int senseNumber = 0,counter_For_ReferenceNum=0, referenceNum=0; 
		
		for(String line :  test_Lines){
			if(line.length() == 0){			/*Then, there is blank line*/
				/*
				for(String train : temp_List){
					System.out.print(train + " ");
				}
				System.out.println();
				*/
				
				if(senseNumber != 0){		/*That condition controls if there is an instance that doesn't have a sense number*/
					add_Train_Instance_to_HashTable(referenceNum, temp_List, test_List);
				}
				temp_List.clear();
				senseNumber=0;
				counter_For_ReferenceNum=0;
				
			}else{
				counter_For_ReferenceNum++;
				line = remove_Punctuation(line);
				String[] splittedLine = line.split("\\s+");
				for(int i=0; i<splittedLine.length; i++){
					
					if( splittedLine[i].contains("<tag>") && splittedLine[i].contains("</>") ){
						senseNumber = 2;		/*That sense number is a signal for control the instance has the word to be disambiguated*/

					}else{
						if(!splittedLine[i].contains("<")){
							String stemmed_Word = Stemmer.ReturnWord(splittedLine[i]);
							if(vocabulary_List.contains(stemmed_Word)){
								temp_List.add(stemmed_Word);
							}
							if(counter_For_ReferenceNum==1){
								referenceNum = Integer.parseInt(splittedLine[i]);
							}
						}
					}
					/*System.out.print(splittedLine[i] + " ");*/
				}
				/*System.out.println();*/
			}
		}
	}
	
	public static double calculating_P_c(Hashtable<Integer, Integer> training_File_Table,int sense_Number){
		int count=0,total_count=0;
		Set set = training_File_Table.entrySet();
	    Iterator iterator = set.iterator();
	    while(iterator.hasNext()){
	    	Map.Entry mentry = (Map.Entry)iterator.next();
	    	int value = (int) mentry.getValue();
	    	if(value == sense_Number){
	    		count++;
	    	}
	    	total_count++;
	    }
	    return (double)count/total_count;
	}
	
	public static void calculating_Naive_Bayes_Algorithm(ArrayList<Sense> test_List,ArrayList<Sense> sense_List,
			Hashtable<Integer, Integer> training_File_Table,PrintWriter outWriter){		
		
		
		for(Sense test_Sense : test_List){

			Sense big_Probability_Sense = null;
			double probability = 0.0;
			
			for(Sense train_Sense : sense_List){
				double temp_probability=0.0;
				double P_c = calculating_P_c(training_File_Table, train_Sense.getSenseNumber());
				
				Set set = test_Sense.getTrainTable().entrySet();
			    Iterator iterator = set.iterator();	    
			    while(iterator.hasNext()) {
			        Map.Entry test_mentry = (Map.Entry)iterator.next();
			        int test_value = (int) test_mentry.getValue();
			        String test_key = (String) test_mentry.getKey();
			        
			        if(train_Sense.getTrainTable().get(test_key) != null){
			        	int temp_value = train_Sense.getTrainTable().get(test_key);
			        	int count_Array[] = train_Sense.calculate_Word_Count();
			        	int count_Array2[] = test_Sense.calculate_Word_Count();

			        	/*Here i implement the naive bayes algorithm*/
			        	if(temp_probability == 0.0){
			        		temp_probability = (double)(temp_value + 1) / ( count_Array[0] + count_Array[1] + count_Array2[1]); /*first element of array keep the count of words, second element of array keep the count of unique words*/
			        	}else{
			        		temp_probability = temp_probability * (double)((temp_value + 1) / ( count_Array[0] + count_Array[1] + count_Array2[1])); /*first element of array keep the count of words, second element of array keep the count of unique words*/
			        	}
			        	
			        	if(test_value > 1){
			        		for(int i=1; i<test_value; i++){
				        		temp_probability *= temp_probability;
				        	}
			        	}
				        	
			        }
			        
				    temp_probability = (double)temp_probability * P_c;
			        
			        if(probability == 0.0){
		        		probability = temp_probability;
		        		big_Probability_Sense = train_Sense;
		        	}
		        	else{
		        		if(temp_probability > probability){
			        		probability = temp_probability;
			        		big_Probability_Sense = train_Sense;
		        		}	
		        	}
			        temp_probability=0.0;
			    }
			    


			}


			outWriter.println(test_Sense.getReferenceNumber() + " " + big_Probability_Sense.getSenseNumber());
		}
		
	}
	
	public static void add_Train_Instance_to_HashTable(int referenceNum, ArrayList<String> temp_List,ArrayList<Sense> test_List){
			Sense sense = new Sense();
			for(String word : temp_List){
				Integer fr = sense.getTrainTable().get(word);
				sense.getTrainTable().put(word,  (fr == null) ? 1 : fr + 1);
			}
			sense.setReferenceNumber(referenceNum);
			test_List.add(sense);	
	}
	
	public static String remove_Punctuation(String line){
		
		line = line.replaceAll("\\?", "");			
		line = line.replaceAll("\\(", "");
		line = line.replaceAll("\\)", "");
		line = line.replaceAll("\\,", "");
		line = line.replaceAll("\\:", "");
		line = line.replaceAll("\\;", "");
		line = line.replaceAll("\\!", "");
		line = line.replaceAll("\\#", "");
		line = line.replaceAll("\\-", "");
		line = line.replaceAll("\\-", "");
		line = line.replaceAll("\\.", "");
		line = line.replaceAll("\\&", "");
		line = line.replaceAll("\\'", "");
		line = line.replaceAll("\\`", "");
		
		return line;
	}
	
}
