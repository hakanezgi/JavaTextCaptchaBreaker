public class DayPattern {

	public static String solve(String question, MainProg mp) {

		try{
			
			String toReturn = null;
			int order = 0, day = 0;

			question = question.toLowerCase();
			question = question.replaceAll("[^\\d|^\\w|^\\s|^.]*", "");
			
			String[] days = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"}; 			
	
			String[] tokens = question.split(" ");
	
			/*
			 * Eger soru icerisinde "hafta sonu" ifadesi geciyorsa cevap cumartesi veya pazar olmalidir..
			 * Soru icerisinde cumartesi veya pazar kelimelerinden hangisi varsa cevap olarak dondur..
			 */
			if(question.indexOf("weekend") > -1)
			{
				if(question.indexOf("saturday") > -1)
					return "saturday";
				else if(question.indexOf("sunday") > -1)
					return "sunday";
			}
			
	
			/*
			 * Eger hafta sonu degil de "Dun Persembe ise yarin hangi gundur?" gibi bir mantik
			 * sorusu sorulmussa, soruda verilen gunu tespit et..
			 * Verilen gune kadar olan kisimda dun veya yarin kelimeleri varsa, gun degiskenini uygun olarak guncelle..
			 * Sonraki kisimda dun veya yarin soruluyorsa, gun degiskenini yine uygun olarak guncelle ve sonucu dondur..
			 */
			if((question.indexOf("yesterday") > -1) || (question.indexOf("today") > -1) || (question.indexOf("tomorrow") > -1))
			{
				for(String token : tokens)
					for(int i = 0; i < days.length; i++)
						if(token.indexOf(days[i]) > -1)
						{
							day = i;					
							break;
						}
				
				String firstPart = question.substring(0, question.indexOf(days[day]));
				String secondPart = question.substring(question.indexOf(days[day]) + days[day].length());
				
				/*
				 * burada aslinda -1 ve +1 ler vardi fakat -1 % 7 = -1 ciktigi icin hata veriyor..
				 * bu sebeple es deger olan +6 ve +8 degerleri eklenerek mod 7 alinmistir..
				 */
				
				if(firstPart.indexOf("yesterday") > -1)
					day = (day + 8) % 7;
				else if (firstPart.indexOf("tomorrow") > -1)
					day = (day + 6) % 7;
				
				if(secondPart.indexOf("yesterday") > -1)
					day = (day + 6) % 7;
				else if (secondPart.indexOf("tomorrow") > -1)
					day = (day + 8) % 7;
				
				return days[day];
			}
	
			
			/*
			 * Eger hafta sonu veya dun-bugun-yarin iceren mantik sorusu sorulmamissa 
			 * ya listedekilerin hangisi bir gundur
			 * ya listedeki x. gun hangisidir
			 * ya da listede kac tane gun vardir sorusu sorulmus olmali
			 * asagidaki kisim bu sorulari cozuyor.. 
			 */
			for(String token : tokens)
			{
				for(String sDay : days)
				{
					if(token.equals(sDay))				
						mp.al.add(token);
				}
				
				for(int i = 0; i < NumericalOperations.ordinals.length; i++)
					if(token.equals(NumericalOperations.ordinals[i]))
						order = (int) Math.floor(i/2.0);
			}
	
	
			if (question.indexOf("how many") != -1)
				toReturn = "" + mp.al.size();
			else if (mp.al.size() >= 1)
				toReturn = mp.al.get(order);
	
			return toReturn;
		}	
		
		catch(Exception e) {e.printStackTrace(); return null;}	
	}

}
