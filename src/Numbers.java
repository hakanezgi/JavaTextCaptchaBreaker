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

}
