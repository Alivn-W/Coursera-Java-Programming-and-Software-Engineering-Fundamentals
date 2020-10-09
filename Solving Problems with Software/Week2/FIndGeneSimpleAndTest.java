import edu.duke.*;

public class FIndGeneSimpleAndTest {


    public String findGeneSimple(String dna){

        String result = "";
        dna = dna.toUpperCase();
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){
            return "";
        }
        int stopIndex = dna.indexOf("TAA",startIndex+3);
        //public int indexOf(int ch, int fromIndex): 返回从 fromIndex 位置开始查找指定字符在字符串中第一次出现处的索引，如果此字符串中没有这样的字符，则返回 -1。
        result = dna.substring(startIndex,stopIndex+3);
        return result;
    }


    public void testSimpleGene(){

        String dna ="AATGHVIBKOLNBHUTAAVUGCHFJ";
        System.out.println("DNA strand is: " +dna);
        String gene = findGeneSimple(dna);
        System.out.println("Gene is: " +gene);

         dna ="GHVIBKOLNBHUTAAVTAA";
        System.out.println("DNA strand is: " +dna);
         gene = findGeneSimple(dna);
        System.out.println("Gene is: " +gene);

        dna ="gatgubhinomiubhvytaa";
        System.out.println("DNA strand is: " +dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is: " +gene.toLowerCase());

    }

   public static void main (String[] args){
       FIndGeneSimpleAndTest Fg = new FIndGeneSimpleAndTest();
       Fg.testSimpleGene();
   }


}
