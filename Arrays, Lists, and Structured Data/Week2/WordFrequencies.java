import java.io.File;
import java.util.ArrayList;
import edu.duke.*;
public class WordFrequencies {

    private ArrayList<String> Words;
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies(){
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
        Words = new ArrayList<>();
    }

    public boolean contains(ArrayList<String> list,String word, int number){
        for(int i = 0; i < number; i++){
            if(list.get(i).equals(word)){
                return true;
            }
        }
        return false;
    }

    public void readWord(){
        Words.clear();
        myWords.clear();
        myFreqs.clear();

        FileResource Fr = new FileResource();
        for(String word : Fr.words()){
            Words.add(word.toLowerCase());
        }

    }


    public void findUnique(){
        int number = 0;

        for(String s : Words){
            if(!contains(myWords,s,number)){
                myWords.add(s);
                number++;
                myFreqs.add(1);
            }
            else{
                for(int i=0; i<myWords.size(); i++){
                    if(myWords.get(i).equals(s)){
                        int z = myFreqs.get(i);
                        z+=1;
                        myFreqs.set(i, z);
                    }
                }

            }
        }

    }

    public void tester(){
        readWord();
        findUnique();
        System.out.println("How many unique words: "+myWords.size());
        int maxindex = 0;
        for(int i = 0; i<myFreqs.size(); i++){
            if(myFreqs.get(maxindex)<= myFreqs.get(i)){
                maxindex = i;
            }
        }
        System.out.println("Which word occurs the most often:  "+myWords.get(maxindex));
        System.out.println("How many times does it occur?  "+myFreqs.get(maxindex));
    }


    public static void main(String[] arg){
        WordFrequencies Tr = new WordFrequencies();
        Tr.tester();
    }

}
