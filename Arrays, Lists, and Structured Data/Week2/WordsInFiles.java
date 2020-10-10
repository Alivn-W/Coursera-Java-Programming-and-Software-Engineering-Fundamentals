import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import edu.duke.*;

public class WordsInFiles {
    private ArrayList<String> fileName;
    private HashMap<String, ArrayList<String>> remainlist;

    public WordsInFiles(){
        remainlist = new HashMap<String, ArrayList<String>>();


    }

    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for(String s : fr.words()){
            fileName = new ArrayList<String>();
            if(remainlist.isEmpty()|| !remainlist.containsKey(s)){
                fileName.add(f.getName());
                remainlist.put(s,fileName);
            }
            if(remainlist.containsKey(s)){
                fileName = remainlist.get(s);
                if(!fileName.contains(f.getName())) {
                    fileName.add(f.getName());
                    remainlist.put(s, fileName);
                }
            }
        }

    }

    public void buildWordFileMap(){

        remainlist.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }

        //System.out.println(remainlist);

    }

    public int maxNumber(){
        int max = 0;
        ArrayList<String> temp;
        for(String s : remainlist.keySet()) {
            temp = remainlist.get(s);
            int a = temp.size();
            if(a>max)
                max = a;

        }

        return max;
    }

    public ArrayList wordsInNumFiles(int number){
        ArrayList<String> temp = new ArrayList<>();
        for(String s : remainlist.keySet()) {
            int a = remainlist.get(s).size();
            if(a==number){
                temp.add(s);

            }
        }
        return temp;
    }


    public void printFilesIn(String word){

        for(String s : remainlist.keySet()) {
            if(word.equals(s)){
                System.out.println(remainlist.get(s));
            }
        }
    }

    public void  tester(){
        buildWordFileMap();
        //System.out.println(maxNumber());
        //System.out.println(wordsInNumFiles(4).size());
        printFilesIn("tree");

    }




    public static void main(String[] arg){
        WordsInFiles Te = new WordsInFiles();
        Te.tester();
    }
}
