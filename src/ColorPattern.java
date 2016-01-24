public class ColorPattern {

	public static String solve(String question, MainProg mp) {
		
		try{
			
			question = question.replaceAll("[^\\d|^\\w|^\\s|^'|^.]*", "");
			
			String toReturn = null;
			int position = 0;
			
			String[] colors = {"alizarin", "almond", "amaranth", "amber", "amethyst", "ao", "apricot", "aqua", "aquamarine", "arsenic", "asparagus", "auburn", "aureolin", "aurometalsaurus", "awesome", "azure", "bazaar", "beaver", "beige", "bistre", "bittersweet", "black", "blond", "blue", "blush", "bole", "brass", "bronze", "brown", "bubbles", "buff", "burgundy", "burlywood", "byzantine", "byzantium", "cadet", "camel", "capri", "cardinal", "carmine", "carnelian", "ceil", "celadon", "cerise", "cerulean", "chamoisee", "champagne", "charcoal", "chartreuse", "chestnut", "chocolate", "cinereous", "cinnabar", "citrine", "cobalt", "copper", "coquelicot", "coral", "cordovan", "corn", "cornsilk", "cream", "crimson", "cyan", "daffodil", "dandelion", "denim", "desert", "drab", "ecru", "eggplant", "eggshell", "emerald", "fallow", "fandango", "fawn", "feldgrau", "firebrick", "flame", "flavescent", "flax", "folly", "fuchsia", "fulvous", "gainsboro", "gamboge", "glaucous", "gold", "goldenrod", "gray", "green", "grullo", "harlequin", "heliotrope", "honeydew", "iceberg", "icterine", "inchworm", "indigo", "iris", "isabelline", "ivory", "jade", "jasper", "jonquil", "khaki", "lava", "lavender", "lemon", "lilac", "lime", "linen", "liver", "lust", "magenta", "magnolia", "mahogany", "maize", "malachite", "manatee", "maroon", "mauve", "mauvelous", "melon", "mint", "moccasin", "mulberry", "mustard", "myrtle", "ochre", "olive", "olivine", "onyx", "orange", "orchid", "peach", "pear", "pearl", "peridot", "periwinkle", "persimmon", "pink", "pistachio", "platinum", "plum", "prune", "puce", "pumpkin", "purple", "raspberry", "razzmatazz", "red", "redwood", "regalia", "rose", "rosewood", "ruby", "ruddy", "rufous", "russet", "rust", "saffron", "salmon", "sand", "sandstorm", "sangria", "sapphire", "scarlet", "seashell", "sepia", "shadow", "sienna", "silver", "sinopia", "skobeloff", "smalt", "snow", "straw", "sunglow", "sunset", "tan", "tangelo", "tangerine", "taupe", "teal", "thistle", "timberwolf", "tomato", "toolbox", "tumbleweed", "turquoise", "ube", "ultramarine", "umber", "urobilin", "vanilla", "verdigris", "vermilion", "veronica", "violet", "viridian", "wenge", "wheat", "white", "wisteria", "xanadu", "yellow", "zaffre"}; 			
			
			String[] tokens = question.toLowerCase().split(" ");
			
			for(String token : tokens)
			{
				for(String color : colors)
				{
					if(token.equals(color))				
						mp.al.add(token);
				}
				
				for(int i = 0; i < NumericalOperations.ordinals.length; i++)
					if(token.equals(NumericalOperations.ordinals[i]))
						position = (int) Math.floor(i/2.0);
			}
				
			
			if (question.indexOf("how many") != -1)		
				toReturn = "" + mp.al.size();		
			else if (mp.al.size() >= 1)
				toReturn = mp.al.get(position);
				
			return toReturn;
		}
		catch(Exception e) {return null;}
	}

}
