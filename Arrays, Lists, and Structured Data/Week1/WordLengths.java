import edu.duke.*;

import javax.naming.NameNotFoundException;
import java.util.Arrays;

public class WordLengths {

    public int indexOf(String[] list, String word){
        for(int k = 0; k< list.length; k++){
            if(list[k].equals(word)){
                return k;
            }
        }
        return -1;
    }
//这个是借鉴网上的写法，妙计！

    // 首先遍历每一个单词，针对每一个单词遍历它的字符，去求它的长度。
    // 之后把特定长度的字符数量存在特定位置的counts数组中
    public int [] countWordLengths(FileResource resource, int []  counts) {
        for (String word: resource.words()) {
            int n = word.length();
            int l = 0;
            for (int i = 0; i < n; i++) {
                if (Character.isLetter(word.charAt(i))) l += 1;
            }

            if (l <= counts.length & l != 0) counts[l-1] += 1;

        }
        return counts;
    }

    public void testcountWordLengths() {
        FileResource resource = new FileResource();
        int[] counts = new int[60];
        int[] result = countWordLengths(resource, counts);
        for (int i = 0; i < 60; i++) {
            if(result[i]!=0)
            System.out.println(result[i] + " words of length " + (i + 1));
        }

        int maxvalue = indexOfMax(result);
        System.out.println("The most common word length in the file is " +maxvalue);
    }


    public int indexOfMax(int[] value){
        int maxvalue = 0;
        for(int k =0; k< value.length; k++){
            if(value[k]>=maxvalue){
                maxvalue = value[k];
            }
        }
        return maxvalue;
    }


    public static void main(String[] arg) {
        WordLengths Te = new WordLengths();

        Te.testcountWordLengths();
    }
}
