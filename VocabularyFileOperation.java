import java.util.ArrayList;

public class VocabularyFileOperation {
	
	public static void parse_Vocabulary_File(String[] vocabulary_Lines, ArrayList<String> vocabulary_List){
		
		for(String line : vocabulary_Lines){
	
			String[] splittedLine = line.split("\\s+");
			for(int i=0; i<splittedLine.length; i++){
				if(i != splittedLine.length - 1){
					splittedLine[i] = splittedLine[i].substring(0,splittedLine[i].length()-1);	/*I splitted the word from comma here*/
				}
				String stemmed_Word = Stemmer.ReturnWord(splittedLine[i]);		/*Porter Stemmer is used in here*/
				vocabulary_List.add(stemmed_Word);
			}
			
		}
		
	}

}
