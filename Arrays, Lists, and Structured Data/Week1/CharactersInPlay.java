import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> names ;
    private ArrayList<Integer> myFreqs;

    public CharactersInPlay(){
        names  = new ArrayList<>();
        myFreqs = new ArrayList<>();

    }

    public String dotcheck(String line){
        for(int i =0; i<line.length(); i++){
            char dot = line.charAt(i);
            if(dot == '.'){
                return line.substring(0,i);
            }
        }
        return "";
    }

    public boolean contains(ArrayList<String> list,String word, int number){
        for(int i = 0; i < number; i++){
            if(list.get(i).equals(word)){
                return true;
            }
        }
        return false;
    }

    public void findAllCharacters(){
        FileResource Fr = new FileResource();
        int i =0;
        int maxindex = 0;
        for(String line : Fr.lines()){

            if(!dotcheck(line).equals("") && !contains(names,dotcheck(line),i)){
                names.add(dotcheck(line));
                i++;
                myFreqs.add(1);
            }
            else {
                for(int j=0; j<names.size(); j++){
                    if(names.get(j).equals(dotcheck(line))){
                        int z = myFreqs.get(j);
                        z+=1;
                        myFreqs.set(j, z);
                    }
                }
            }
        }
        for(int j= 0; j<myFreqs.size(); j++){
            if(myFreqs.get(j) != 1){
                System.out.print(names.get(j)+" : ");
                System.out.println(myFreqs.get(j));
                charactersWithNumParts(names.get(j), 10, 15);
            }

            if(myFreqs.get(maxindex)<= myFreqs.get(j)){
                    maxindex = j;
            }

        }
        System.out.println("What is the name of the character with the most speaking parts: "+names.get(maxindex)+myFreqs.get(maxindex));
        System.out.println(names.toString() + myFreqs.toString());
    }

    public void charactersWithNumParts(String s, int num1, int num2){
            if(s.length()>num1 & s.length()<num2){
                System.out.println(s);
            }

    }

    public void tester(){
        findAllCharacters();
    }

    public static void main(String[] arg){
        CharactersInPlay Tr = new CharactersInPlay();
        Tr.tester();
    }
}
