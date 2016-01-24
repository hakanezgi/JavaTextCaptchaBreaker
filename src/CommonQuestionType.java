import java.util.ArrayList;

public class CommonQuestionType {

	public static void solve(String question, MainProg mainProg) {		
		
		try{
			question = question.replaceAll("[^\\d|^\\w|^\\s]*", "");
			
			ArrayList<Integer> numbers = new ArrayList<Integer>();
			
			String[] splitted = question.split(" ");
			
			int sum = 0, mult = 1;
			
			for(String q : splitted)
				if(NumericalOperations.isNumber(q.trim()))
					numbers.add(Integer.parseInt(q.trim()));
			
			for(int n : numbers)
				for(int i = -3; i < 4; i++)
					mainProg.al.add((n + i) + "");
			
			for(int i = 0; i < numbers.size(); i++){
				
				sum = sum + numbers.get(i);
				mult = mult * numbers.get(i);
				
				for(int j = -9; j < 10; j++)
					mainProg.al.add((numbers.get(i) + j) + "");
				
				for(int j = i; j < numbers.size(); j++){									
					mainProg.al.add((numbers.get(i) + numbers.get(j)) + "");
					mainProg.al.add((numbers.get(i) * numbers.get(j)) + "");
					mainProg.al.add((numbers.get(i) - numbers.get(j)) + "");
					mainProg.al.add((numbers.get(j) - numbers.get(i)) + "");
					mainProg.al.add((int)(numbers.get(i) / numbers.get(j)) + "");
					mainProg.al.add((int)(numbers.get(j) / numbers.get(i)) + "");
				}
			}

			mainProg.al.add(sum + "");
			mainProg.al.add(mult + "");
			
		}
		catch(Exception ex){}
	}
}
