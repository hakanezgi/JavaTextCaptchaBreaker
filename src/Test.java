import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;

public class Test
{
	public static void main(String[] args) throws Exception
	{
		MainProg mp = new MainProg();
		
		int successOnFirstTry = 0, successLater = 0, fail = 0, totalNumberOfTries = 0;
		
		ArrayList<String> failedTests = new ArrayList<String>();
		/*
		System.out.println(mp.checkQuality("Of the numbers 59, 9, eighty or ninety two, which is the largest?", new String[]{"92cc227532d17e56e07902b254dfad10"}));
		*/
		
		for(int i = 0; i < 1000; i++)
		{
			Thread.sleep(1000); 
			
			String[] test = getNewTest();
			String[] answers = new String[test.length - 1];
			
			for(int j = 1; j < test.length; j++)
				answers[j-1] = test[j];
			
			
			
			if(!mp.checkQuality(test[0], answers))
			{
				if(mp.numberOfTriedAnswers == 0)
					successOnFirstTry++;
				else
					successLater++;
				
				totalNumberOfTries += mp.numberOfTriedAnswers;
			}
			else{
				fail++;
				failedTests.add(test[0]);
			}
			if(i % 50 != 0)
				System.out.print(".");
			else
				System.out.println(i);
		}
		
		System.out.println();
		System.out.println("Success on first try = " + successOnFirstTry);
		System.out.println("Success using possible answers list = " + successLater);
		System.out.println("Total number of tries when possible answers list is used = " + totalNumberOfTries);
		System.out.println("Fail to solve = " + fail);
		
		if(failedTests.size() > 0)
		{
			System.out.println();
			System.out.println("Failed tests are:");
			
			for(String s : failedTests)
				System.out.println(s);
		}
		
	}
	
	public static String[] getNewTest() throws Exception
	{
		String url = "http://api.textcaptcha.com/javaTextCaptchaBreaker.xml";   
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder b = f.newDocumentBuilder();
		Document doc = b.parse(url);

		doc.getDocumentElement().normalize();
		
		String[] newTest = new String[1 + doc.getDocumentElement().getElementsByTagName("answer").getLength()];
	   
		newTest[0] = doc.getDocumentElement().getElementsByTagName("question").item(0).getTextContent().trim();
		
		for(int i = 1; i < newTest.length; i++)
			newTest[i] = doc.getDocumentElement().getElementsByTagName("answer").item(i-1).getTextContent().trim();
		
		return newTest;
	}
	
}