import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class BabyBirths {
    public void printname(){
        FileResource fr = new FileResource();
        for(CSVRecord rec: fr.getCSVParser(false)){
            System.out.println("Name" + rec.get(0) +
                               "Gender" + rec.get(1)+
                               "Num Born"+rec.get(2));
        }
    }

    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int boyBirths = 0;
        int girlBirths = 0;
        for(CSVRecord rec: fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths = totalBirths + numBorn;

            if(rec.get(1).equals("M")){
                boyBirths ++;

            }
            if(rec.get(1).equals("F")){
                girlBirths ++;
            }

        }
        System.out.println("total number births: "+totalBirths);
        System.out.println("boy number births: "+boyBirths);
        System.out.println("girl number births: "+girlBirths);

    }


    public int getRank(int year, String name, String gender){

        FileResource fr = new FileResource();
        int rank = 0;
        for(CSVRecord rec: fr.getCSVParser(false)){

            if (rec.get(1).equals(gender)){
                int MAXnumber = Integer.parseInt(rec.get(2));
                if (Integer.parseInt(rec.get(2))<=MAXnumber){
                    rank = rank +1;
                }
                if (rec.get(0).equals(name)){
                    return rank;
                }
            }
        }

        return -1;
    }

    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource();
        int temp = 0;
        for(CSVRecord rec: fr.getCSVParser(false)){

            if (rec.get(1).equals(gender)){
                int MAXnumber = Integer.parseInt(rec.get(2));
                if (Integer.parseInt(rec.get(2))<=MAXnumber){
                    temp = temp +1;
                }
                if (temp == rank){
                    return rec.get(0);
                }
            }
        }

        return "NO Name";
    }


    public void testtotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);


    }


    public void testgetRank(){

        int rank = getRank(2012, "Frank", "M");
        System.out.println("Rank is : "+rank );


    }



    public void whatIsNameInYear(String name, int year, int newyear, String gender){
            int temp1 = 0;
            String name2 = "";

            temp1 = getRank(year, name, gender);

            name2 = getName(newyear, temp1, gender);


        System.out.println(name+ " born in "+year +" would be " + name2+ " if she was born in "+newyear);

    }

    public int getRankF(int year, String name, String gender,File f){

        FileResource fr = new FileResource(f);
        int rank = 0;
        for(CSVRecord rec: fr.getCSVParser(false)){

            if (rec.get(1).equals(gender)){
                int MAXnumber = Integer.parseInt(rec.get(2));
                if (Integer.parseInt(rec.get(2))<=MAXnumber){
                    rank = rank +1;
                }
                if (rec.get(0).equals(name)){
                    return rank;
                }
            }
        }

        return -1;
    }


    public double yearOfHighestRank(String name,String gender ){
        DirectoryResource dr = new DirectoryResource();
        double POSITIVE_INFINITY = 1.0 / 0.0;
        double maxrank = POSITIVE_INFINITY;
        int temp = 0;
        File Fy=null;
        for(File f: dr.selectedFiles()){
            temp = getRankF(1, name, gender,f);
            if(temp < maxrank && temp != -1){

                maxrank = temp;
                Fy = f;
            }

        }
        System.out.println(Fy);
        return maxrank;
    }

    private void testgetName(){

        String name = getName(2012, 450, "M");
        System.out.println("NAME is : "+name );

    }

    public void testyearOfHighestRank(){
        double max = yearOfHighestRank("Mich","M" );
        System.out.println(max);
    }

    public double getAverageRank( String name,String gender){
        DirectoryResource dr = new DirectoryResource();
        double SumRank = 0;
        int i = 0;
        for(File f: dr.selectedFiles()){
            if (getRankF(2020, name, gender,f)==-1){
                continue;
            }
            SumRank+=getRankF(2020, name, gender,f);
            i++;
        }
        return SumRank/i;

    }


    public void testgetAverageRank(){
        double A = getAverageRank( "Robert","M");
        System.out.println(A);
    }

    public int  getTotalBirthsRankedHigher(int year, String name, String gender){

        FileResource fr = new FileResource();

        int Hnumber = 0;
        int Rnumber = 0;
        int a = 0;
        for(CSVRecord rec: fr.getCSVParser(false)){

            if (rec.get(1).equals(gender)){
                int Xnumber = Integer.parseInt(rec.get(2));
                a++;
                if (Integer.parseInt(rec.get(2))<=Xnumber){
                    Rnumber =Hnumber;
                    Hnumber = Hnumber+Xnumber;
                    if (rec.get(0).equals(name)){
                        if (a == 1){
                            return Xnumber;
                        }
                        else{
                            return Rnumber;
                        }

                    }
                }


            }
        }

        return -1;
    }

    public void testgetTotalBirthsRankedHigher(){
        int a = getTotalBirthsRankedHigher(2, "Drew", "M");
        System.out.println(a);
    }

    public static void main (String[] args){

        BabyBirths Tr = new BabyBirths();
        //Tr.printname();
        //Tr.testtotalBirths();
        //Tr.testgetRank();
        //Tr.testgetName();
        Tr.whatIsNameInYear("Owen", 2012, 2014, "M");
        //Tr.testyearOfHighestRank();
        //Tr.testgetAverageRank();
        //Tr.testgetTotalBirthsRankedHigher();

    }
}
