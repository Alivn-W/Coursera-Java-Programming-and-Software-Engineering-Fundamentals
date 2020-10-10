import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CodonCount {
    private HashMap<String, Integer> dnaMap;

    public CodonCount(){
        dnaMap = new HashMap<String, Integer>();
    }

    public void buildCodonMap(String dna, int start){


        String dnaNew = dna.substring(start);
        int finial = dnaNew.length();
        for(int i = 0; i<dnaNew.length() ; i=i+3){
            if(dna.substring(i,finial).length()<3) break;
            int j = i+3;
            String Codon = dnaNew.substring(i,j);
            if(!dnaMap.containsKey(Codon))
            dnaMap.put(Codon,1);
            else{
                dnaMap.put(Codon, dnaMap.get(Codon)+1);
            }

        }
    }

    public String getMostCommonCodon(){
        int mostcodon = 0;
        String sMax = "";
        for(String s : dnaMap.keySet()){

            if(dnaMap.get(s)>mostcodon){
                mostcodon = dnaMap.get(s);
                sMax = s;
            }

        }
        System.out.println(mostcodon);
    return sMax;
    }

    public void printCodonCounts(int start, int end){
        for(String s : dnaMap.keySet()){
            if(dnaMap.get(s)>=start){
                if (dnaMap.get(s)<=end){
                    System.out.println(s + dnaMap.get(s));
                }
            }
        }
    }

    public void  tester(){
        dnaMap.clear();
        String dna = "CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC";
        buildCodonMap(dna, 0);
        System.out.println("and most common codon is " +getMostCommonCodon());
        System.out.println(dnaMap.size());
        System.out.println("Counts of codons between 1 and 5 inclusive are: ");
        printCodonCounts(1, 7);

    }

    public static void main(String[] arg){
        CodonCount Te = new CodonCount();
        Te.tester();
    }
}
