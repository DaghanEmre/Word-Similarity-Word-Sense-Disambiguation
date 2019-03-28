import java.util.ArrayList;
import java.util.Hashtable;

public class TrainingFileOperation {
	
	public static void parse_Train_File(String[] train_Lines,ArrayList<String> vocabulary_List,ArrayList<Sense> sense_List,
			Hashtable<Integer, Integer> training_File_Table){
		ArrayList<String> temp_List = new ArrayList<String>();
		int senseNumber = 0,counter_For_ReferenceNum=0, referenceNum=0; 
		
		for(String line :  train_Lines){
			if(line.length() == 0){			/*Then, there is blank line*/
				/*
				for(String train : temp_List){
					System.out.print(train + " ");
				}
				System.out.println();
				*/
				
				if(senseNumber != 0){		/*That condition controls if there is an instance that doesn't have a sense number*/
					add_Train_Instance_to_HashTable(senseNumber, referenceNum,temp_List, sense_List);
				}
				temp_List.clear();
				senseNumber=0;
				counter_For_ReferenceNum=0;
				
			}else{
				counter_For_ReferenceNum++;
				line = remove_Punctuation(line);
				String[] splittedLine = line.split("\\s+");
				for(int i=0; i<splittedLine.length; i++){
					
					if( splittedLine[i].contains("\">") && splittedLine[i].contains("</>") ){
						int sense_Location = splittedLine[i].indexOf(">");
						String temp_Sense = splittedLine[i].substring(1, sense_Location-1);
						senseNumber = Integer.parseInt(temp_Sense);
						
						training_File_Table.put(referenceNum, senseNumber);		/*That hashtable is for calculating p(c) in naive bayes algorithm*/
						
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
	
	public static Sense contains(ArrayList<Sense> sense_List, int senseNumber){
		for(Sense iter : sense_List){
			if(iter.getSenseNumber() == senseNumber){
				return iter;
			}
		}
		return null;
	}
	
	public static void add_Train_Instance_to_HashTable(int senseNumber, int referenceNum,ArrayList<String> temp_List,ArrayList<Sense> sense_List){
		Sense check_Exist = contains(sense_List, senseNumber);
		if(check_Exist != null){		/*This sense number has already been seen, so we just need to add new train words to hashmap*/
			for(String word : temp_List){
				Integer fr = check_Exist.getTrainTable().get(word);
				check_Exist.getTrainTable().put(word,  (fr == null) ? 1 : fr + 1);
			}
			sense_List.set(sense_List.indexOf(check_Exist), check_Exist);
		}else{					/*This sense Number is new so we need to make an instance of sense*/
			Sense sense = new Sense(senseNumber);
			for(String word : temp_List){
				Integer fr = sense.getTrainTable().get(word);
				sense.getTrainTable().put(word,  (fr == null) ? 1 : fr + 1);
			}
			sense.setReferenceNumber(referenceNum);
			sense_List.add(sense);
		}		
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
