public class NamePattern {

	public static String solve(String question, MainProg mp) {

		try{

			question = question.replaceAll("[^\\d|^\\w|^\\s|^'|^.]*", "");
			
			String toReturn = null;
			
			String[] tokens = question.split(" ");
			
			for(String token : tokens)
			{			
				if(token.charAt(0) == token.toUpperCase().charAt(0))			
				{
					if(token.indexOf("'") > -1)			
						mp.al.add(token.substring(0, token.indexOf("'")));
					else
						mp.al.add(token);
				}
			}
			if(mp.al.size()>=1)
				toReturn = mp.al.get(mp.al.size()-1);
			
			return toReturn;
		}
		catch(Exception e) {return null;}
	}

}
