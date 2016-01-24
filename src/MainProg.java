import java.util.ArrayList;
import java.security.MessageDigest;

public class MainProg {

	public ArrayList<String> al;
	public int possibleAnswerCounter;		
	
	public int numberOfTriedAnswers; // for testing purpose only
	
	public boolean checkQuality(String question, String[] allRealAnswers) {

		try {
			possibleAnswerCounter = 0;
			
			al = new ArrayList<String>();

			String answer = null;
	
			if ((answer == null) && ((question.indexOf("color") > -1) || (question.indexOf("colour") > -1)))
				answer = ColorPattern.solve(question, this);
			if ((answer == null) && ((question.indexOf("body") > -1) || (question.indexOf("part") > -1)))
				answer = BodyPattern.solve(question, this);
			if ((answer == null) && ((question.indexOf("largest") > -1) || (question.indexOf("highest") > -1) || (question.indexOf("smallest") > -1) || (question.indexOf("lowest") > -1) || (question.indexOf("biggest") > -1) || (question.indexOf("number") > -1)))
				answer = WhichNumberPattern.solve(question, this);
			if ((answer == null) && ((question.indexOf("digit") > -1) || (question.indexOf("number") > -1)))
				answer = Numbers.solveNumberOrder(question);
			if ((answer == null) && ((question.indexOf("digit") > -1) || (question.indexOf("number") > -1)))
				answer = Numbers.solveStringToNumber(question);
			if ((answer == null) && ((question.indexOf("plus") > -1) || (question.indexOf("add") > -1) || (question.indexOf("+") > -1) || (question.indexOf("minus") > -1) || (question.indexOf("subtract") > -1) || (question.indexOf("-") > -1)))
				answer = AddSubtractPattern.solve(question);
			if ((answer == null) && ((question.indexOf("day") > -1) || (question.indexOf("tomorrow") > -1) || (question.indexOf("weekend") > -1)))
				answer = DayPattern.solve(question, this); 
			if ((answer == null) && ((question.indexOf("letter") > -1) || (question.indexOf("length") > -1) || (question.indexOf("capital") > -1) || (question.indexOf("contain") > -1) || (question.indexOf("begin") > -1) || (question.indexOf("starting") > -1)))
				answer = AnalyzeWordsPattern.solve(question, this);
			if ((answer == null) && ((question.indexOf("person") > -1) || (question.indexOf("name") > -1)))
				answer = NamePattern.solve(question, this);			
			
			al.add("" + al.size());
	
			if ((question.indexOf(",") > 0))
				if ((question.indexOf(",", question.indexOf(",")) > 0) || (question.indexOf(",", question.indexOf(" and ")) > 0) || (question.indexOf(",", question.indexOf(" or ")) > 0))
					getList(question);
	
			CommonQuestionType.solve(question, this);
			
			possibleAnswerCounter += al.size();
			if (answer != null)
				possibleAnswerCounter++;			
			
			numberOfTriedAnswers = 0;
			
			for(String aRealAnswer : allRealAnswers)
			{
				if ((answer != null && md5Hash(answer.toLowerCase()).trim().equalsIgnoreCase(aRealAnswer.trim())))
					return false;
				
				for(String a : al)
				{
					numberOfTriedAnswers++;
					
					if (md5Hash(a.toLowerCase()).trim().equalsIgnoreCase(aRealAnswer.trim()))
						return false;
				}
			}

			return true;
		}
		
		catch(Exception ex){ex.printStackTrace(); return true;}
	}

	@SuppressWarnings("unused")
	private boolean checkIfAnswerIsInQuestion(String question, String realAnswer) {
		
		question = " " + question.toLowerCase().replaceAll("ý", "i").replaceAll("[^\\d|^\\w|^\\s]*", "").trim() + " ";
		realAnswer = realAnswer.toLowerCase().replaceAll("ý", "i").replaceAll("[^\\d|^\\w|^\\s]*", "").trim();
		
		String[] wordsInAnswer = realAnswer.trim().split(" ");
		
		boolean flag = false;
		
		for(String word : wordsInAnswer){
			if(word.trim().equals(""))
				continue;
			
			word = " " + word.trim() + " ";
			
			if(question.indexOf(word) > -1)
				flag = true;
			else{
				flag = false;
				break;
			}
		}
		
		possibleAnswerCounter += question.split(" ").length;
			
		return flag;
	}

	private void getList(String question) {

		try {
			question = question.replaceAll("[^\\d|^\\w|^\\s|^,]*", "");

			String[] splitByComma = question.split(",");

			insertFirstElement(splitByComma[0]);
			
			for(int i = 1; i < splitByComma.length-1; i++)
				splitAndAdd(splitByComma[i]);
			
			
			if((splitByComma[splitByComma.length - 1].indexOf(" or ") == -1) && (splitByComma[splitByComma.length - 1].indexOf(" and ") == -1))
				splitAndAdd(splitByComma[splitByComma.length - 1]);
			else
			{
				if(splitByComma[splitByComma.length - 1].indexOf(" or ") > -1)
				{
					insertFirstElement(splitByComma[splitByComma.length - 1].substring(0, splitByComma[splitByComma.length - 1].indexOf(" or ")));
					insertLastElement(splitByComma[splitByComma.length - 1].substring(splitByComma[splitByComma.length - 1].indexOf(" or ") + " or ".length()));
					splitAndAdd(splitByComma[splitByComma.length - 1]);
				}
					
				
				else if(splitByComma[splitByComma.length - 1].indexOf(" and ") > -1)
				{
					splitAndAdd(splitByComma[splitByComma.length - 1].substring(0, splitByComma[splitByComma.length - 1].indexOf(" and ")));
					splitAndAdd(splitByComma[splitByComma.length - 1].substring(splitByComma[splitByComma.length - 1].indexOf(" and ") + " and ".length()));
					al.add(splitByComma[splitByComma.length - 1].trim());
				}
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void splitAndAdd(String listElement)
	{
		listElement = listElement.trim();
		
		if(NumericalOperations.convertTextIntoInteger(listElement) > 0)
			al.add(NumericalOperations.convertTextIntoInteger(listElement) + "");
		
		else
		{
			if(listElement.indexOf(" or ") > -1)
			{
				splitAndAdd(listElement.substring(0, listElement.indexOf(" or ")));
				splitAndAdd(listElement.substring(listElement.indexOf(" or ") + " or ".length()));
			}
			
			else if(listElement.indexOf(" and ") > -1)
			{
				splitAndAdd(listElement.substring(0, listElement.indexOf(" and ")));
				splitAndAdd(listElement.substring(listElement.indexOf(" and ") + " and ".length()));				
			}	
		}		
		
		al.add(listElement);
	}
	
	private void insertFirstElement(String listElement)
	{	
		listElement = listElement.trim();
		
		String[] splitted = listElement.split(" ");
		
		int i = splitted.length - 1;
		String toAdd = splitted[i];
		
		boolean doesItEndWithAnd = false;
		boolean doesTheNumberContinue = NumericalOperations.convertTextIntoInteger(toAdd) > 0;
		
		while(doesTheNumberContinue || doesItEndWithAnd)
		{			
			if(i > 0)
				i--;
			else
				break;
			
			if(NumericalOperations.convertTextIntoInteger(splitted[i] + " " + toAdd) > 0)
				toAdd = splitted[i] + " " + toAdd;
			else
				doesTheNumberContinue = false;
			
			if(splitted[i].equals("and"))
				doesItEndWithAnd = true;
			else
				doesItEndWithAnd = false;
		}
		
		
		splitAndAdd(toAdd);
	}
	
	private void insertLastElement(String listElement)
	{	
		listElement = listElement.trim();
		
		String[] splitted = listElement.split(" ");
		
		int i = 0;
		String toAdd = splitted[i];
		
		boolean doesItEndWithAnd = false;
		boolean doesTheNumberContinue = NumericalOperations.convertTextIntoInteger(toAdd) > 0;
		
		while(doesTheNumberContinue || doesItEndWithAnd)
		{			
			if(i < splitted.length - 1)
				i++;
			else
				break;
			
			if(NumericalOperations.convertTextIntoInteger(toAdd + " " + splitted[i]) > 0)
				toAdd = toAdd + " " + splitted[i];
			else
				doesTheNumberContinue = false;
			
			
			if(splitted[i].equals("and"))
				doesItEndWithAnd = true;
			else
				doesItEndWithAnd = false;
		}

		splitAndAdd(toAdd);
	}
	
	public String md5Hash(String text) throws Exception
	{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(text.getBytes());
        
        byte byteData[] = md.digest();
 
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++)
			sb.append(String.format("%02x", byteData[i] & 0xff));
		
		return sb.toString();
	}
}
