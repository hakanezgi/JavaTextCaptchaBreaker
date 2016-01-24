public class AnalyzeWordsPattern {

	public static String solve(String question, MainProg mp) {
		try
		{		
		
			String toReturn = null;
			int order = -1;											
			
			String[] tokens = question.split(" ");						
			
			for(String token : tokens)
			{				
				for(int i = 0; i < NumericalOperations.ordinals.length; i++)
					if(token.toLowerCase().equals(NumericalOperations.ordinals[i]))
						order = (int) Math.floor(i/2.0);
			}
			
			if(order > -1)
			{
				String word = question.substring(question.indexOf("\"")+1, question.indexOf("\"", question.indexOf("\"")+1));
				return "" + word.charAt(order);
			}
			
			if((question.toLowerCase().indexOf("how many letter") > -1) || (question.toLowerCase().indexOf("length") > -1))
				return "" + question.substring(question.indexOf("\"")+1, question.indexOf("\"", question.indexOf("\"")+1)).length();
			
			if(question.indexOf("capital") > -1)
			{
				for(String token : tokens)
					if(token.equals(token.toUpperCase()))
						return token;
			}
			
			if((question.indexOf("begin") > -1) || (question.indexOf("starting") > -1))
			{
				findWords(question, mp);
				String letter = findTheLetter(question);
				
				for(String word : mp.al)
					if(letter.charAt(0) == word.charAt(0))
						return word;
			}
			
			if(question.indexOf("contain") > -1)
			{
				findWords(question, mp);
				String letter = findTheLetter(question);
				
				for(String word : mp.al)
					if(word.indexOf(letter) > -1)
						return word;
			}															
			
			return toReturn;
		}
		catch(Exception e) {return null;}
	}

	private static String findTheLetter(String question) {
		
		String[] bol = question.toLowerCase().trim().split("\"");
		int min = question.length() + 1;
		String toReturn = null;
		
		for(int i = 0; i < bol.length; i++)
		{			
			if((bol[i].length() > 0) && (bol[i].length() < min))
			{
				min = bol[i].length();
				toReturn = bol[i];
			}				
		}

		return toReturn.trim();
	}

	private static void findWords(String question, MainProg mp) {
		
		question = question.replaceAll("[^\\d|^\\w|^\\s|^,]*", "");
		
		String[] splitByComma = question.toLowerCase().split(",");

		for(int i = 0; i < splitByComma.length; i++)
			splitByComma[i] = splitByComma[i].trim();
			
		if(splitByComma[0].indexOf(" ") == -1)
			splitByComma[0] = " " + splitByComma[0];
		if(splitByComma[splitByComma.length-1].indexOf(" ") == -1)
			splitByComma[splitByComma.length-1] = splitByComma[splitByComma.length-1].trim() + " ";
		
		splitByComma[0] = splitByComma[0].substring(splitByComma[0].lastIndexOf(" ") + 1);
		splitByComma[splitByComma.length-1] = splitByComma[splitByComma.length-1].substring(0, splitByComma[splitByComma.length-1].indexOf(" "));
		
		for(String word : splitByComma)
			mp.al.add(word);
		
	}

}
