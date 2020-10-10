import edu.duke.*;
import java.util.*;

public class GladLib {

	private HashMap<String, ArrayList<String>> myMap;
	private ArrayList<String> PW;
	private HashMap<String, Integer> TMap;
	
	private Random myRandom;

	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "datalong";
	
	public GladLib(){
		myMap = new HashMap<>();
		myRandom = new Random();
		PW = new ArrayList<>();
		TMap = new HashMap<>();
		initializeFromSource(dataSourceDirectory);
	}

	private void initializeFromSource(String source) {


		String[] lables = {"country","noun","animal","adjective","name","color","timeframe","verb","fruit"};
		for(String s : lables){
			ArrayList<String> list = readIt(source+"/" +s+".txt");
			myMap.put(s,list);
		}
	}
	
	private String randomFrom(ArrayList<String> source){

		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {

		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		if(!TMap.containsKey(label))
			TMap.put(label,1);
		else {
			int i = TMap.get(label);
			TMap.put(label,i+1);
		}
		return randomFrom(myMap.get(label));
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first + 1, last));

		while(PW.contains(sub)){
			sub = getSubstitute(w.substring(first + 1, last));
		}
		PW.add(sub);
		System.out.println(PW);

		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}


	public void makeStory(){
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
		System.out.print(totalWordsInMap());
		System.out.print(totalWordsConsidered());

	}

	public int totalWordsConsidered(){
		int sum = 0;
		System.out.println(TMap);
		for(String s : TMap.keySet()){
			sum = sum + TMap.get(s);
		}
		return sum;
	}

	public int totalWordsInMap(){
		return PW.size();

	}



	public static void main(String[] arg){
		GladLib Te = new GladLib();
		//for(int i = 0; i<5; i++)
		Te.makeStory();


	}

}
