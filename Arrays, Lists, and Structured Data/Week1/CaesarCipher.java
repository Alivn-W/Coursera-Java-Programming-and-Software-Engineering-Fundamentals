import edu.duke.*;
public class CaesarCipher {

    public String encrypt(String input, int key){
        StringBuilder encrypt = new StringBuilder(input);
        String alphabetU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetL = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet = alphabetU.substring(key)+ alphabetU.substring(0,key);
        String shiftedAlphabetL = alphabetL.substring(key)+ alphabetL.substring(0,key);

        for(int i =0; i<encrypt.length(); i++){
            char currchar = encrypt.charAt(i);
            int idx = alphabetU.indexOf(currchar);
            int idxl = alphabetL.indexOf(currchar);
            if(idx != -1 & idxl == -1){
                char newChar = shiftedAlphabet.charAt(idx);
                encrypt.setCharAt(i,newChar);
            }
            if(idxl != -1 & idx == -1){
                char newChar = shiftedAlphabetL.charAt(idxl);
                encrypt.setCharAt(i,newChar);
            }

        }

        return encrypt.toString();
    }


    public void testencrypt(){
       System.out.println(encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15));
       System.out.println(encrypt("First Legion", 23));

    }

    public void testCaesar(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, 23);
        System.out.println("key is " + 23 + "\n" + encrypted);
    }

    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypt = new StringBuilder(input);
        String alphabetU = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetL = "abcdefghijklmnopqrstuvwxyz";

        for(int i =0; i<encrypt.length(); i++){
            if (i % 2 == 0) {
                String shiftedAlphabet = alphabetU.substring(key1)+ alphabetU.substring(0,key1);
                String shiftedAlphabetL = alphabetL.substring(key1)+ alphabetL.substring(0,key1);
                    char currchar = encrypt.charAt(i);
                    int idx = alphabetU.indexOf(currchar);
                    int idxl = alphabetL.indexOf(currchar);
                    if(idx != -1 & idxl == -1){
                        char newChar = shiftedAlphabet.charAt(idx);
                        encrypt.setCharAt(i,newChar);
                    }
                    if(idxl != -1 & idx == -1){
                        char newChar = shiftedAlphabetL.charAt(idxl);
                        encrypt.setCharAt(i,newChar);
                    }
            }
            else{
                String shiftedAlphabet = alphabetU.substring(key2)+ alphabetU.substring(0,key2);
                String shiftedAlphabetL = alphabetL.substring(key2)+ alphabetL.substring(0,key2);
                    char currchar = encrypt.charAt(i);
                    int idx = alphabetU.indexOf(currchar);
                    int idxl = alphabetL.indexOf(currchar);
                    if(idx != -1 & idxl == -1){
                        char newChar = shiftedAlphabet.charAt(idx);
                        encrypt.setCharAt(i,newChar);
                    }
                    if(idxl != -1 & idx == -1){
                        char newChar = shiftedAlphabetL.charAt(idxl);
                        encrypt.setCharAt(i,newChar);
                    }
            }
        }

        return encrypt.toString();
    }

    public void testencryptTwoKeys(){
        System.out.println(encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21,8));


    }

    public static void main(String[] arg){
        CaesarCipher Te = new CaesarCipher();
        Te.testencryptTwoKeys();
        //Te.testencrypt();
        //Te.testencrypt();
    }
}
