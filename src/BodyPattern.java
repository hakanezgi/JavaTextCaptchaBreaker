public class BodyPattern {

	public static String solve(String question, MainProg mp) {

		try{
			
			question = question.replaceAll("[^\\d|^\\w|^\\s|^'|^.]*", "");

			String toReturn = null;
			int position = 0;
			
			String[] bodyParts = {"toe", "foot", "ankle", "knee", "leg", "waist", "thumb", "finger", "hand",  "elbow", "stomach", "arm", "heart", "chest", "head", "face", "tooth", "tongue", "chin", "nose", "ear", "eye", "brain", "hair"};				
			
			String[] tokens = question.toLowerCase().split(" ");
			
			for(String token : tokens)
			{
				for(String bodyPart : bodyParts)
				{
					if(token.equals(bodyPart))				
						mp.al.add(token);
				}
				
				for(int i = 0; i < NumericalOperations.ordinals.length; i++)
					if(token.equals(NumericalOperations.ordinals[i]))
						position = (int) Math.floor(i/2.0); 
			}
				
			
			if (question.indexOf("how many") != -1)		
				toReturn = "" + mp.al.size();		
			else
				toReturn = mp.al.get(position);
				
			return toReturn;
		}
		catch(Exception e) {return null;}
	}

}
