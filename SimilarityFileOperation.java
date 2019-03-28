import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SimilarityFileOperation {
	
	public static void parsingSimilarityFile(PrintWriter outWriter,String[] similarity_Lines) {
		Hashtable<String, Integer> first_Paragraph_Table = new Hashtable<String, Integer>();
		Hashtable<String, Integer> second_Paragraph_Table = new Hashtable<String, Integer>();
		Hashtable<String, Integer> word_Table = new Hashtable<String, Integer>();

		ArrayList<Integer> first_Vec = new ArrayList<Integer>();
		ArrayList<Integer> second_Vec = new ArrayList<Integer>();
		
		String first_Word = null, second_Word = null;
		int first_Sum = 0, second_Sum = 0, word_Sum = 0,counter=0;
		double first_Size = 0.0, second_Size = 0.0, res = 0.0;
		
		for(int iter=0; iter<similarity_Lines.length; iter++){
			String line = similarity_Lines[iter];
			if(line.length() == 0){
				String next_Line = similarity_Lines[iter+1];
				String cleaned_Word = next_Line.replaceAll("[^a-zA-Z ]", "").toLowerCase();
				cleaned_Word = cleaned_Word.replaceAll("\\s+", "");
				second_Word = Stemmer.ReturnWord(cleaned_Word);
				counter++;
			}else{
				String[] words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");  /* I used the regular expression from that link: http://stackoverflow.com/questions/18830813/how-can-i-remove-punctuation-from-input-text-in-java */
				for(int i=0; i<words.length; i++){
		
					String stemmed_Word = Stemmer.ReturnWord(words[i]);
					if(iter == 0){
						first_Word = stemmed_Word;
					}
					if(stemmed_Word.equalsIgnoreCase(first_Word) && counter == 0){
						if(0 <= i-3){
							String temp = Stemmer.ReturnWord(words[i-3]);
							Integer frequency = word_Table.get(temp);
							word_Table.put(temp, (frequency == null) ? 1 : frequency + 1);
							Integer frequency1 = first_Paragraph_Table.get(temp);
							first_Paragraph_Table.put(temp, (frequency1 == null) ? 1 : frequency1 + 1);
						}if(0 <= i-2){
							String temp = Stemmer.ReturnWord(words[i-2]);
							Integer frequency = word_Table.get(temp);
							word_Table.put(temp, (frequency == null) ? 1 : frequency + 1);
							Integer frequency1 = first_Paragraph_Table.get(temp);
							first_Paragraph_Table.put(temp, (frequency1 == null) ? 1 : frequency1 + 1);							
						}if(0 <= i-1){
							String temp = Stemmer.ReturnWord(words[i-1]);
							Integer frequency = word_Table.get(temp);
							word_Table.put(temp, (frequency == null) ? 1 : frequency + 1);
							Integer frequency1 = first_Paragraph_Table.get(temp);
							first_Paragraph_Table.put(temp, (frequency1 == null) ? 1 : frequency1 + 1);
						}if(words.length-1 >= i+1){
							String temp = Stemmer.ReturnWord(words[i+1]);
							Integer frequency = word_Table.get(temp);
							word_Table.put(temp, (frequency == null) ? 1 : frequency + 1);
							Integer frequency1 = first_Paragraph_Table.get(temp);
							first_Paragraph_Table.put(temp, (frequency1 == null) ? 1 : frequency1 + 1);
						}if(words.length-1 >= i+2){
							String temp = Stemmer.ReturnWord(words[i+2]);
							Integer frequency = word_Table.get(temp);
							word_Table.put(temp, (frequency == null) ? 1 : frequency + 1);
							Integer frequency1 = first_Paragraph_Table.get(temp);
							first_Paragraph_Table.put(temp, (frequency1 == null) ? 1 : frequency1 + 1);
						}if(words.length-1 >= i+3 && !words[i+3].contains(" ")){
							String temp = Stemmer.ReturnWord(words[i+3]);
							Integer frequency = word_Table.get(temp);
							word_Table.put(temp, (frequency == null) ? 1 : frequency + 1);
							Integer frequency1 = first_Paragraph_Table.get(temp);
							first_Paragraph_Table.put(temp, (frequency1 == null) ? 1 : frequency1 + 1);
						}
					}if(stemmed_Word.equalsIgnoreCase(second_Word) && counter != 0){
						if(0 <= i-3){
							String temp = Stemmer.ReturnWord(words[i-3]);
							Integer frequency = word_Table.get(temp);
							word_Table.put(temp, (frequency == null) ? 1 : frequency + 1);
							Integer frequency1 = second_Paragraph_Table.get(temp);
							second_Paragraph_Table.put(temp, (frequency1 == null) ? 1 : frequency1 + 1);
						}if(0 <= i-2){
							String temp = Stemmer.ReturnWord(words[i-2]);
							Integer frequency = word_Table.get(temp);
							word_Table.put(temp, (frequency == null) ? 1 : frequency + 1);
							Integer frequency1 = second_Paragraph_Table.get(temp);
							second_Paragraph_Table.put(temp, (frequency1 == null) ? 1 : frequency1 + 1);							
						}if(0 <= i-1){
							String temp = Stemmer.ReturnWord(words[i-1]);
							Integer frequency = word_Table.get(temp);
							word_Table.put(temp, (frequency == null) ? 1 : frequency + 1);
							Integer frequency1 = second_Paragraph_Table.get(temp);
							second_Paragraph_Table.put(temp, (frequency1 == null) ? 1 : frequency1 + 1);
						}if(words.length-1 >= i+1){
							String temp = Stemmer.ReturnWord(words[i+1]);
							Integer frequency = word_Table.get(temp);
							word_Table.put(temp, (frequency == null) ? 1 : frequency + 1);
							Integer frequency1 = second_Paragraph_Table.get(temp);
							second_Paragraph_Table.put(temp, (frequency1 == null) ? 1 : frequency1 + 1);
						}if(words.length-1 >= i+2){
							String temp = Stemmer.ReturnWord(words[i+2]);
							Integer frequency1 = second_Paragraph_Table.get(temp);
							second_Paragraph_Table.put(temp, (frequency1 == null) ? 1 : frequency1 + 1);
							Integer frequency = word_Table.get(temp);
							word_Table.put(temp, (frequency == null) ? 1 : frequency + 1);
						}if(words.length-1 >= i+3){
							String temp = Stemmer.ReturnWord(words[i+3]);
							Integer frequency1 = second_Paragraph_Table.get(temp);
							second_Paragraph_Table.put(temp, (frequency1 == null) ? 1 : frequency1 + 1);
							Integer frequency = word_Table.get(temp);
							word_Table.put(temp, (frequency == null) ? 1 : frequency + 1);
						}
					}
				}				
			}
		}
		
		Set wordSet = word_Table.entrySet();
		Iterator word_Iterator  = wordSet.iterator();
		while (word_Iterator.hasNext()) {
			int first_Det  = 0, second_Det  = 0;
			Map.Entry word_Mentry  = (Map.Entry) word_Iterator.next();
			int word_Value  = (int) word_Mentry.getValue();
			String word_String  = (String) word_Mentry.getKey();

			Set first_Set  = first_Paragraph_Table.entrySet();
			Iterator first_Iterator  = first_Set.iterator();
			while (first_Iterator.hasNext()) {
				Map.Entry first_Mentry  = (Map.Entry) first_Iterator.next();
				int first_Value  = (int) first_Mentry .getValue();
				String first_String  = (String) first_Mentry .getKey();

				if (word_String.equals(first_String)) {
					first_Det  = 1;
					first_Vec.add(first_Value);
				}
			}
			if (first_Det == 0)
				first_Vec.add(0);

			Set second_Set  = second_Paragraph_Table.entrySet();
			Iterator second_Iterator  = second_Set .iterator();
			while (second_Iterator .hasNext()) {
				Map.Entry second_Mentry  = (Map.Entry) second_Iterator .next();
				int second_Value  = (int) second_Mentry .getValue();
				String second_String  = (String) second_Mentry .getKey();

				if (word_String.equals(second_String)) {
					second_Det  = 1;
					second_Vec.add(second_Value);
				}
			}
			if (second_Det == 0)
				second_Vec.add(0);
		}

		for (Integer first : first_Vec) {
			int square = first * first;
			first_Sum += square;
		}
		first_Size = Math.sqrt(first_Sum);

		for (Integer second : second_Vec) {
			int square = second * second;
			second_Sum += square;
		}
		second_Size = Math.sqrt(second_Sum);

		for (int iter = 0; iter < first_Vec.size(); iter++) {
			int multiplication = first_Vec.get(iter) * second_Vec.get(iter);
			word_Sum += multiplication;
		}
		
		res = (double) word_Sum / (first_Size * second_Size);
		System.out.println(res);
		outWriter.println(res);
		
	}


}
