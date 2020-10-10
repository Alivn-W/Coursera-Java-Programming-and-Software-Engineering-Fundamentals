import edu.duke.FileResource;

import java.util.Arrays;

public class CaesarBreaker {


    public int [] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k<message.length(); k++ ){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if(dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }

    public int indexofMax(int [] values) {
        int maxvalue = 0;
        int position = 0;
        for (int i = 0; i < values.length; i++) {

                if (maxvalue < values[i]) {
                    maxvalue = values[i];
                    position = i;
                }
        }
        return position+1;
    }

    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = indexofMax(freqs);
        int dkey = maxDex -5;
        if(maxDex<5){
            dkey = 26-(5-maxDex);
        }
        String message = cc.encrypt(encrypted, 26 - dkey);

        return message;
    }

    public void testDecrypt(){
        System.out.println(decrypt("aaaa"));
    }

    public String halfOfString(String message,int start){
        int i = 0;
        int j = 0;
        StringBuilder H1 = new StringBuilder();
        StringBuilder H2 = new StringBuilder();
        for(int k=0; k<message.length(); k++ ){
            char ch = message.charAt(k);

            if(start == 0 && k%2 == 0){
                H1.append(ch);
                i++;
            }
            if(start == 1 && k%2 != 0){
                H2.append(ch);
                j++;
            }
        }

        if(start == 0)
            return H1.toString();

        return H2.toString();
    }

    public void testhalfOfString(){
        System.out.println(halfOfString("Qbkm Zgis",0));
    }

    public int getKey(String s){
        int[] freq = countLetters(s);
        System.out.println(Arrays.toString(freq));
        return indexofMax(freq);
    }

    public String decryptTwo(String encrypted,int k){
        CaesarCipher cc = new CaesarCipher();

        int dkey = k -5;
        if(k<5){
            dkey = 26-(5-k);
        }
        String message = cc.encrypt(encrypted, 26 - dkey);

        return message;
    }

    public String decryptTwoKeys(String s){
        String a = halfOfString(s,0);
        String b = halfOfString(s,1);
        int k1 = getKey(a);
        int k2 = getKey(b);
        int i = 0;
        int j = 0;
        System.out.println(k1+" he "+k2);
        String da = decryptTwo(a,k1);
        String db = decryptTwo(b,k2);
        StringBuilder result = new StringBuilder(s);
        for(int k=0; k<s.length(); k++ ){
            if (k%2 == 0){
                char ch = da.charAt(i);
                result.setCharAt(k,ch);
                i++;
            }
            else{
                char ch = db.charAt(j);
                result.setCharAt(k,ch);
                j++;
            }
        }

        System.out.println(result.toString());
        return result.toString();
    }

    public void testdecryptTwoKeys(){
        FileResource resource = new FileResource();
        String s = resource.asString();
        decryptTwoKeys(s);
    }
    public static void main(String[] arg){
        CaesarBreaker Te = new CaesarBreaker();
        //Te.testencryptTwoKeys();
        //Te.testDecrypt();
        //Te.testhalfOfString();
        //System.out.println(Te.getKey("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));
        Te.testdecryptTwoKeys();
    }
}
