
public class WordPlay {

    public boolean isVowel(char ch){
        String Vowel = "AEIOUaeiou";
        for(int i =0; i<Vowel.length(); i++){
            char currchar = Vowel.charAt(i);
            if(currchar == ch){
                return true;
            }
        }

    return false;
    }

    public void testisVowel(){
        System.out.println(isVowel('O'));
        System.out.println(isVowel('F'));
        System.out.println(isVowel('o'));

    }

    public String replaceVowels(String phrase, char ch){

        StringBuilder phraseB = new StringBuilder(phrase);

        for(int i =0; i<phrase.length(); i++){
            char currchar = phrase.charAt(i);
            if(isVowel(currchar)){
                phraseB.setCharAt(i,ch);
            }
        }

        return phraseB.toString();

    }

    public void testreplaceVowels(){
        System.out.println(replaceVowels("Hello World", '*'));


    }

    public String emphasize(String phrase, char ch){
        StringBuilder phraseB = new StringBuilder(phrase);
        for(int i =0; i<phrase.length(); i++){
            char currchar = phraseB.charAt(i);
            if(currchar == ch) {
                if (i % 2 == 0) {
                    phraseB.setCharAt(i, '*');
                }
                else{
                    phraseB.setCharAt(i, '+');
                }
            }
        }
        return phraseB.toString();

    }

    public void testemphasize(){

        System.out.println(emphasize("dna ctgaaactga",'a'));
    }


    public static void main(String[] arg){
        WordPlay Te = new WordPlay();
        //Te.testisVowel();
        //Te.testreplaceVowels();
        Te.testemphasize();



    }
}
