import edu.duke.*;

public class AllCodons {

    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){

        int currIndex = dnaStr.indexOf(stopCodon,startIndex+3);
        while(currIndex != -1){
            int diff = currIndex -startIndex;
            if(diff % 3 ==0){
                return currIndex;
            }
            else{
                currIndex = dnaStr.indexOf(stopCodon, currIndex+1);


            }

        }
    return -1;
    }

    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG",where);
        if(startIndex == -1){
            return "";
        }

        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int minIndex = 0;
        if(tagIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;

        }
        else{
            minIndex =taaIndex;

        }

        if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){ //TAA 和 TGA 都没有，tag 有
            minIndex = tagIndex;

        }

        if(minIndex == -1){
            return "";
        }




        return dna.substring(startIndex,minIndex+3);

    }

    public int CGnumber(String gene, String TG){
            int number = 0;


            int startIndex = 0;
            int currIndex = gene.indexOf(TG,startIndex);
            while(currIndex != -1){
                currIndex = gene.indexOf(TG, currIndex+1);
                number = number +1;
            }

    return number;

    }

    public float CGRatio(String gene){

        int total = gene.length();
        int Cnumber = CGnumber(gene, "C");
        int Gnumber = CGnumber(gene, "G");
        float result =(float)(Cnumber+Gnumber)/total;
        return result;

    }


    public void printAllGenes(String dna){
        int startIndex = 0;
        int i = 0;
        int j = 0;
        int z = 0;
        String max ="";
        while(true){
            String currentGene = findGene(dna, startIndex);

            if(currentGene.isEmpty()){
                break;
            }
            else{
                i = i+1;
            }

            if(CGRatio(currentGene)>0.35){
                z = z+1;
            }

            if(currentGene.length()>60){
                j= j+1;
            }

            if(currentGene.length()>max.length()){
                max = currentGene;
            }

            System.out.println(currentGene);


            startIndex = dna.indexOf(currentGene,startIndex)+currentGene.length();
        }
        System.out.println(i);
        System.out.println(j);
        System.out.println(z);
        System.out.println(max.length());

    }



    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        int i = 0;
        while(true){
            String currentGene = findGene(dna, startIndex);

            if(currentGene.isEmpty()){
                break;
            }
            else{
                i = i+1;
            }
            geneList.add(currentGene);
            //System.out.println(currentGene);


            startIndex = dna.indexOf(currentGene,startIndex)+currentGene.length();
        }
        //System.out.println(i);
    return geneList;
    }


    public void test(){
        FileResource fr = new FileResource("GRch38dnapart.fa.txt");
        String dna = fr.asString();
        float GRG = CGnumber(dna, "CTG");
        System.out.println(GRG);
        printAllGenes(dna);
       /* StorageResource gene = getAllGenes(dna);
        for(String s : gene.data()){
            System.out.println(s);
        }*/

    }

    public void test1(){
        String dna = "ATGCCATAG";
        float CGR = CGRatio(dna);
        System.out.println(CGR);

        //printAllGenes(dna);
       /* StorageResource gene = getAllGenes(dna);
        for(String s : gene.data()){
            System.out.println(s);
        }*/

    }

    public void processGenes(){
        FileResource fr = new FileResource("GRch38dnapart.fa.txt");
        String dna = fr.asString();


        System.out.println(dna);

    }

    public static void main(String[] args){

        AllCodons Tr = new AllCodons();
        Tr.test();
    }
}
