import java.util.Arrays;


public class WhichNumberPattern {

	public static String solve(String question, MainProg mp) {
		
		try{
			
			findNumbersInList(question, mp);
			int[] numbers = listToNumbers(mp);
						
			String toReturn = null;
			int position = 0;											
			
			String[] tokens = question.toLowerCase().split(" ");
			
			if((question.indexOf("largest") > -1) || (question.indexOf("highest") > -1) || (question.indexOf("biggest") > -1))
			{
				Arrays.sort(numbers);
				toReturn = "" + numbers[numbers.length - 1];
			}
			else if((question.indexOf("lowest") > -1) || (question.indexOf("smallest") > -1))
			{
				Arrays.sort(numbers);
				toReturn = "" + numbers[0];			
			}
			else
			{
				for(String token : tokens)
				{									
					for(int i = 0; i < NumericalOperations.ordinals.length; i++)
						if(token.equals(NumericalOperations.ordinals[i]))
							position = (int) Math.floor(i/2.0);
				}
							
				toReturn = "" + numbers[position];
			}
			
			return toReturn;
		}
		catch(Exception e) {return null;}
	}

	private static int[] listToNumbers(MainProg mp) {

		int[] numbers = new int[mp.al.size()];
		
		for(int i = 0; i < numbers.length; i++)
			if(NumericalOperations.isNumber(mp.al.get(i)))
				numbers[i] = Integer.parseInt(mp.al.get(i).trim());
			else
				numbers[i] = NumericalOperations.stringToThreeDigitNumber(mp.al.get(i));
		
		return numbers;
	}

	private static void findNumbersInList(String question, MainProg mp) {

		question = question.replaceAll("[^\\d|^\\w|^\\s|^ç|^ð|^ý|^ö|^þ|^ü|^Ç|^Ð|^Ý|^Ö|^Þ|^Ü|^,]*", "");
		
		int firstValue = 0, secondValue = 0;
		
		String[] splitByComma = question.toLowerCase().split(",");
		String firstPart = splitByComma[0].trim();
		String secondPart = splitByComma[splitByComma.length-1].trim();
		
		if(firstPart.indexOf(" ") == -1)
			firstPart = " " + firstPart;
		
		if(NumericalOperations.isNumber(firstPart.substring(firstPart.lastIndexOf(" ")).trim()))			
			firstValue = Integer.parseInt(firstPart.substring(firstPart.lastIndexOf(" ")).trim());
		else
		{
			firstValue = NumericalOperations.convertTextIntoInteger(firstPart.trim());
			
			while(firstValue == 0 && (!firstPart.equals("zero")))
			{
				firstPart = firstPart.substring(firstPart.indexOf(" ")).trim();
				firstValue = NumericalOperations.stringToThreeDigitNumber(firstPart.trim());				
			}
		}		
			
		mp.al.add(firstValue + " ".trim());

		
		for(int i = 1; i < splitByComma.length - 1; i++)		
			mp.al.add(splitByComma[i].trim());
		
		
		if(secondPart.indexOf("and") > -1)
		{
			firstPart = " " + secondPart.substring(0, secondPart.indexOf("and")).trim();
			secondPart = secondPart.substring(secondPart.indexOf(" ", secondPart.indexOf("and"))).trim();
		}
		
		if(secondPart.indexOf("or") > -1)
		{
			firstPart = " " + secondPart.substring(0, secondPart.indexOf("or")).trim();
			secondPart = secondPart.substring(secondPart.indexOf(" ", secondPart.indexOf("or"))).trim();
		}
		
		if(NumericalOperations.isNumber(firstPart.substring(firstPart.lastIndexOf(" ")).trim()))			
			firstValue = Integer.parseInt(firstPart.substring(firstPart.lastIndexOf(" ")).trim());
		else
		{
			firstValue = NumericalOperations.convertTextIntoInteger(firstPart.trim());
			
			while(firstValue == 0 && (!firstPart.equals("zero")))
			{
				firstPart = firstPart.substring(firstPart.indexOf(" ")).trim();
				firstValue = NumericalOperations.stringToThreeDigitNumber(firstPart.trim());				
			}
		}		
			
		mp.al.add(firstValue + " ".trim()); 
		
		if(secondPart.indexOf(" ") == -1)
			secondPart = secondPart + " ";
		
		if(NumericalOperations.isNumber(secondPart.substring(0, secondPart.indexOf(" ")).trim()))			
			secondValue = Integer.parseInt(secondPart.substring(0, secondPart.indexOf(" ")).trim());
		else
		{
			while(secondValue == 0 && (!secondPart.equals("zero")))
			{				 
				secondPart = secondPart.substring(0, secondPart.lastIndexOf(" ")).trim();
				secondValue = NumericalOperations.convertTextIntoInteger(secondPart.substring(secondPart.lastIndexOf(" ")).trim());				
			}
			secondValue = NumericalOperations.convertTextIntoInteger(secondPart);
		}
		
		mp.al.add(secondValue + " ".trim());												
	}

}
