public class AddSubtractPattern {

	public static String solve(String question) {
		
		try{

			question = question.toLowerCase();
			question = question.replaceAll("[^\\d|^\\w|^\\s|^+|^-]*", "");
			
			String toReturn = null;
			String firstPart = null, secondPart = null;
			
			int marker = 0, firstValue = 0, secondValue = 0;
			
			if((question.indexOf("add") > -1)) 
			{	
				marker = 1;
				
				firstPart = question.substring(0, question.indexOf("add")).trim();
				secondPart = question.substring(question.indexOf(" ", question.indexOf("add"))).trim();					
			}
			
			else if((question.indexOf("plus") > -1)) 
			{	
				marker = 1;
				
				firstPart = question.substring(0, question.indexOf("plus")).trim();
				secondPart = question.substring(question.indexOf(" ", question.indexOf("plus"))).trim();					
			}

			else if((question.indexOf("+") > -1)) 
			{	
				marker = 1;
				
				firstPart = question.substring(0, question.indexOf("+")).trim();
				secondPart = question.substring(question.indexOf(" ", question.indexOf("+"))).trim();					
			}
			
			else if((question.indexOf("subtract") > -1)) 
			{	
				marker = -1;
				
				firstPart = question.substring(0, question.indexOf("subtract")).trim();
				secondPart = question.substring(question.indexOf(" ", question.indexOf("subtract"))).trim();					
			}
			
			else if((question.indexOf("minus") > -1)) 
			{	
				marker = -1;
				
				firstPart = question.substring(0, question.indexOf("minus")).trim();
				secondPart = question.substring(question.indexOf(" ", question.indexOf("minus"))).trim();					
			}
					
			else if((question.indexOf("-") > -1)) 
			{	
				marker = -1;
				
				firstPart = question.substring(0, question.indexOf("-")).trim();
				secondPart = question.substring(question.indexOf(" ", question.indexOf("-"))).trim();					
			}
	
			
			if(firstPart.indexOf(" ") == -1)
				firstPart = " " + firstPart;
			
			if(secondPart.indexOf(" ") == -1)
				secondPart = secondPart + " ";
			
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
			
			if(NumericalOperations.isNumber(secondPart.substring(0, secondPart.indexOf(" ")).trim()))			
				secondValue = Integer.parseInt(secondPart.substring(0, secondPart.indexOf(" ")).trim());
			else
			{
				secondValue = NumericalOperations.convertTextIntoInteger(secondPart.trim());
				
				while(secondValue == 0 && (!secondPart.equals("zero")))
				{				 
					secondPart = secondPart.substring(0, secondPart.lastIndexOf(" ")).trim();
					secondValue = NumericalOperations.convertTextIntoInteger(secondPart.substring(secondPart.lastIndexOf(" ")).trim());				
				}
				secondValue = NumericalOperations.convertTextIntoInteger(secondPart);
			}								
			
			toReturn = "" + (firstValue + (marker * secondValue));
			
			return toReturn;
		}
		catch(Exception e) {e.printStackTrace(); return null;}
	}

}
