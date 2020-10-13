import java.awt.desktop.SystemSleepEvent;
import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder str = new StringBuilder(message);
        StringBuilder res = new StringBuilder();
        for(int i = whichSlice;i< message.length();i=i+totalSlices){
            char ch = str.charAt(i);
            res.append(ch);
        }
        return res.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];

        CaesarCracker thisCracker = new CaesarCracker(mostCommon);

        String[] encrypting = new String[klength];

        for(int i = 0; i<klength; i++){
            encrypting[i] = sliceString(encrypted,i,klength);
        }
        for(int i = 0; i<klength; i++){
            key[i] = thisCracker.getKey(encrypting[i]);
        }

        //WRITE YOUR CODE HERE
        return key;
    }



    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hs = new HashSet<>();
        ArrayList<String> as = new ArrayList<>();
        for(String s : fr.lines()){
            String str = s.toLowerCase();
            if(!hs.contains(str)) hs.add(str);
        }
        return hs;
    }
    public ArrayList<String> readDictionary2(FileResource fr){
        ArrayList<String> as = new ArrayList<>();
        for(String s : fr.lines()){
            String str = s.toLowerCase();
            if(!as.contains(str)) as.add(str);
        }
        return as;
    }

    public int countWords(String message,HashSet<String> Dictionary){
        int count = 0;
        for(String word : message.split("\\W+")){
            word = word.toLowerCase();
            if(Dictionary.contains(word)){
                count++;
            }
        }
    return count;
    }

    public int[] breakForLanguage(String encrypted,HashSet<String> Dictionary){
        int length;
        int max = 0;
        int res = 0;
        String r = "";
        int check=0;
        for(length = 1; length <= 100; length++){
            int[] k = tryKeyLength(encrypted,length,'e');
            VigenereCipher Vi= new VigenereCipher(k);
            String p = Vi.decrypt(encrypted);
            int count = countWords(p,Dictionary);

            if(count > max) {
                max = count;
                res = length;
                r=p;

            }

        }
        System.out.println(max);
        System.out.println(res);

        for(String word : r.split("\\W+")){

            check++;
        }
        System.out.println(check);

        int[] k = tryKeyLength(encrypted, res, 'e');

        return k;

    }

    public int[] breakForMLanguage(String encrypted,HashSet<String> Dictionary){
        int length;
        int max = 0;
        int res = 0;
        String r = "";
        int check=0;
        char mostCommon = mostCommonCharIn(Dictionary);
        for(length = 1; length <= 100; length++){
            int[] k = tryKeyLength(encrypted,length,mostCommon);
            VigenereCipher Vi= new VigenereCipher(k);
            String p = Vi.decrypt(encrypted);
            int count = countWords(p,Dictionary);

            if(count > max) {
                max = count;
                res = length;
                r=p;

            }

        }
        System.out.println(max);
        System.out.println(res);

        for(String word : r.split("\\W+")){

            check++;
        }
        System.out.println(check);

        int[] k = tryKeyLength(encrypted, res, mostCommon);

        return k;

    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        FileResource fd = new FileResource();
        HashSet<String> Directory = readDictionary(fd);
        int[] keys = breakForLanguage(encrypted,Directory);
        //int[] keys = tryKeyLength(encrypted, 4, 'e');
        VigenereCipher Vi= new VigenereCipher(keys);
        System.out.println(Vi.decrypt(encrypted));
        //WRITE YOUR CODE HERE
    }


    public char mostCommonCharIn(HashSet<String> words){
        HashMap<Character,Integer> letterFreq = new HashMap<>();
        int mostfre = 0;
        char mostword = ' ';
        for(String s :words){
            int slength = s.length();
            for(int i = 0; i<slength; i++){
                char c = s.charAt(i);
                if(!letterFreq.containsKey(c)){
                    letterFreq.put(c,1);
                }
                else{
                    int c1 = letterFreq.get(c);
                    letterFreq.put(c,c1+1);
                }

            }
        }

        for(char c : letterFreq.keySet()){
            if(letterFreq.get(c)>mostfre) {
                mostfre = letterFreq.get(c);
                mostword = c;
            }
        }

        return mostword;

    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int max = 0;
        String correct = null;
        for(String s: languages.keySet()){
            int num = countWords(encrypted,languages.get(s));
            if(max<num){
                max= num;
                correct = s;
            }
        }

        int[] keys= breakForMLanguage(encrypted,languages.get(correct));
        System.out.println("keys is " + Arrays.toString(keys) + "is detected for "+ correct);
        VigenereCipher Vi= new VigenereCipher(keys);
        System.out.println(Vi.decrypt(encrypted));
    }

    public void breakVigenereM () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> MD = new HashMap<>();
        for (File f : dr.selectedFiles()) {
            FileResource fd = new FileResource(f);
            HashSet<String> Directory = readDictionary(fd);
            MD.put(f.getName(),Directory);
        }
        breakForAllLangs(encrypted,MD);
        //WRITE YOUR CODE HERE
    }

    public void test(){

        //System.out.println(sliceString("abcdefghijklm", 3, 4));

        //System.out.println(fr.toString());
        //System.out.println(Arrays.toString());
        //FileResource fr = new FileResource();
        //VigenereCipher Vi= new VigenereCipher(tryKeyLength(fr.asString(), 38, 'e'));
        FileResource fe = new FileResource();
        HashSet<String> Directory = readDictionary(fe);
        //System.out.println(Vi.decrypt(fr.asString()));
        //System.out.println(countWords(Vi.decrypt(fr.asString()),Directory));
        //breakVigenere ();
        //System.out.println(readDictionary(fr));
        System.out.println(mostCommonCharIn(Directory));
        //breakVigenere ();
    }

    public void test1(){
        breakVigenere ();
    }

    public void test2(){
        breakVigenereM ();
    }
    public static void main(String[] arg){
        VigenereBreaker Vi= new VigenereBreaker();
        Vi.test2();
    }
    
}
