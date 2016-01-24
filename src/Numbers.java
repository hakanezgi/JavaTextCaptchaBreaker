import java.util.ArrayList;

public class Numbers {

	public static String solveNumberOrder(String question) {
			
		try{
			
			question = question.replaceAll("[^\\d|^\\w|^\\s]*", "");
			
			String toReturn = null;
			int position = 0;						
			
			String[] tokens = question.toLowerCase().split(" ");
			
			for(String token : tokens)
			{
				if(NumericalOperations.isNumber(token))				
					toReturn = token;
					
				for(int i = 0; i < NumericalOperations.ordinals.length; i++)
					if(token.equals(NumericalOperations.ordinals[i]))
						position = (int) Math.floor(i/2.0);
			}		
			
			toReturn = "" + toReturn.charAt(position);				

			return toReturn;
		}
		catch(Exception e) {return null;}
	}

	public static String solveStringToNumber(String question) {

		try{
						
			String toReturn = "";
			
			int begin = -1, end = -1;
			
			String[] tokens = question.toLowerCase().split(" ");
			
			for(int i = 0; i < tokens.length; i++)
			{									
				if(NumericalOperations.convertTextIntoInteger(tokens[i]) > 0)
				{
					begin = i;
					break;
				}
			}
			
			for(int i = begin + 1; i < tokens.length; i++)
			{	
				if(tokens[i].equals("and"))
					continue;
				if(NumericalOperations.convertTextIntoInteger(tokens[i]) == 0)
				{
					end = i;
					break;
				}
			}
			
			for(int i = begin; i < end; i++)
				toReturn = toReturn + " " + tokens[i];
			
			return NumericalOperations.convertTextIntoInteger(toReturn) + "";
		}
		catch(Exception e) {return null;}
	}

	public static ArrayList<Integer> findAllNumbersInText(String question)
	{
		String[] tokens = question.split(" ");
		
		String add = "";
		
		ArrayList<Integer> toReturn = new ArrayList<Integer>();
		boolean hasChanged = false;
		
		for(int i = 0; i < tokens.length; i++)
		{
			String token = tokens[i].replaceAll("[^\\d|^\\w|^\\s]*", "").trim();

			String test;
			test = add + " " + token;
			
			if(NumericalOperations.convertTextIntoInteger(test) == 0 || NumericalOperations.convertTextIntoInteger(token) == 0 || (tokens[i-1].charAt(tokens[i-1].length()-1) == ',' && hasChanged))
			{
				if(hasChanged)
				{
					toReturn.add(NumericalOperations.convertTextIntoInteger(add));
					hasChanged = false;
				}
				
				if(NumericalOperations.isNumber(token))
					toReturn.add(Integer.parseInt(token));
				
				if(NumericalOperations.convertTextIntoInteger(token) == 0)
					add = "";
				else
					add = token;
			}
			else{
				add = test;
				hasChanged = true;
			}
		}
		
		if(!add.trim().equals(""))
			toReturn.add(NumericalOperations.convertTextIntoInteger(add));
		
		return toReturn;
	}
	
}
