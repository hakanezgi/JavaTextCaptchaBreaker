public class NumericalOperations {

	static String[] ordinals = {"first", "1st", "second", "2nd", "third", "3rd", "fourth", "4th", "fifth", "5th", "sixth", "6th", "seventh", "7th", "eighth", "8th", "ninth", "9th", "tenth", "10th"};
	
	public static boolean isNumber(String token)
	{
		boolean result = true;
		
		char[] chars = token.trim().toCharArray();
				
		for(char c : chars)
		{		
			if(!Character.isDigit(c))
			{					
				result = false;			
				break;
			} 
		}
						
		return result;
	}
	
	public static int convertTextIntoInteger(String text)
	{
		
		int toReturn = 0;
		
		String million = null, thousand = null;
		
		int millionb = 0, thousandb = 0, remainingb = 0; 
		
		if(text.indexOf("million") > -1)
		{
			million = text.substring(0, text.indexOf("million")).trim();
			text = text.substring(text.indexOf("million") + "million".length()).trim();
		}
		
		if(text.indexOf("thousand") > -1)
		{
			thousand = text.substring(0, text.indexOf("thousand")).trim();
			text = text.substring(text.indexOf("thousand") + "thousand".length()).trim();
		}	
		
		if(million != null)
			millionb = stringToThreeDigitNumber(million);
		
		if(thousand == null)
			thousandb = 0;
		else if (thousand.equals(""))
			thousandb = 1;
		else
			thousandb = stringToThreeDigitNumber(thousand);
		
		remainingb = stringToThreeDigitNumber(text);
		
		toReturn = (millionb * 1000000) + (thousandb * 1000) + remainingb;
		
		return toReturn;		
	}
	
	public static int stringToThreeDigitNumber(String text)
	{
		text = text.replaceAll("and", "");
		
		int toReturn = 0;			
		
		String hundreds = null, tens = null;
		int yb = 0, ob = 0, bb = 0;
		
		text = text.trim();
		
		if(text.indexOf("hundred") > -1)
		{
			hundreds = text.substring(0, text.indexOf("hundred")).trim();
			text = text.substring(text.indexOf("hundred") + "hundred".length()).trim();
		}	
		
		if(text.indexOf(" ") > -1)
		{
			tens = text.substring(0, text.indexOf(" ")).trim();
			text = text.substring(text.indexOf(" ") + " ".length()).trim();
		}
		
		if(hundreds == null)
			yb = 0;
		else if(hundreds.equals(""))
			yb = 1;
		else
			yb = stringToNumber(hundreds);
		
		if(tens == null || tens.equals(""))
			ob = 0;		
		else
			ob = stringToNumber(tens);
				
		if(text.equals(""))
			bb = 0;
		else
			bb = stringToNumber(text);					
		
		toReturn = (yb * 100) + ob + bb;		
				
		return toReturn;
	}
	
	public static int stringToNumber(String input)
	{
		if(input.equals("one"))
			return 1;
		else if(input.equals("two"))
			return 2;
		else if(input.equals("three"))
			return 3;
		else if(input.equals("four"))
			return 4;
		else if(input.equals("five"))
			return 5;
		else if(input.equals("six"))
			return 6;
		else if(input.equals("seven"))
			return 7;
		else if(input.equals("eight"))
			return 8;
		else if(input.equals("nine"))
			return 9;
		else if(input.equals("ten"))
			return 10;
		else if(input.equals("eleven"))
			return 11;
		else if(input.equals("twelve"))
			return 12;
		else if(input.equals("thirteen"))
			return 13;
		else if(input.equals("fourteen"))
			return 14;
		else if(input.equals("fifteen"))
			return 15;
		else if(input.equals("sixteen"))
			return 16;
		else if(input.equals("seventeen"))
			return 17;
		else if(input.equals("eighteen"))
			return 18;
		else if(input.equals("nineteen"))
			return 19;
		else if(input.equals("twenty"))
			return 20;
		else if(input.equals("thirty"))
			return 30;
		else if(input.equals("forty"))
			return 40;
		else if(input.equals("fifty"))
			return 50;
		else if(input.equals("sixty"))
			return 60;
		else if(input.equals("seventy"))
			return 70;
		else if(input.equals("eighty"))
			return 80;
		else if(input.equals("ninety"))
			return 90;
		
		return 0;
	}
	
	public static boolean isOrdinal(String text)
	{
		for(String s : ordinals)
			if(text.equals(s))
				return true;
		
		return false;
	}
}




















