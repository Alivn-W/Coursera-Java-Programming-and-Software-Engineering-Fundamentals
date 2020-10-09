import edu.duke.*;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;

import static java.lang.Float.NaN;

//这里实际找的是最小温度
public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSoFar = null;
        for(CSVRecord currentRow : parser){

            if(largestSoFar == null){
                largestSoFar = currentRow;
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if(currentTemp != -9999){
                if(currentTemp < largestTemp){
                    largestSoFar = currentRow;

                }
            }


            }

        }
        return  largestSoFar;
    }


    public void informationday(CSVParser parser) {
        //CSVRecord informationday = null;

        for (CSVRecord informationday : parser) {
            System.out.println("Coldest day information: " + informationday.get("DateUTC") + informationday.get("TemperatureF"));
        }
    }


    public void test(){
        //FileResource Fr = new FileResource();
        //CSVRecord largest = hottestHourInFile(Fr.getCSVParser());
        //System.out.println(largest.get("TemperatureF"));
        CSVRecord Mlargest = hottestInManyDays();
        //System.out.println(Mlargest);
        //System.out.println("Coldest temperature File was: "+Mlargest.);
        System.out.println("Coldest temperature on that day was: "+Mlargest.get("TemperatureF"));


    }

    public CSVRecord hottestInManyDays(){
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        File fname = null;
        CSVRecord CR = null;
        FileResource f1 = null;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            if(largestSoFar == null){
                largestSoFar = currentRow;

            }

            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));

                if(currentTemp < largestTemp){
                    largestSoFar = currentRow;
                    fname = f;
                    f1 = fr;

                }
            }

        }
        System.out.println("Coldest temperature File was: "+fname.getName() );
        informationday(f1.getCSVParser());

        return  largestSoFar;
    }


    public CSVRecord lowestHumidityInFile (CSVParser parser){
        CSVRecord lowestHumiditySoFar = null;
        for(CSVRecord currentRow : parser){

            if(lowestHumiditySoFar == null){
                lowestHumiditySoFar = currentRow;
            }
            else{
                if(!currentRow.get("Humidity").equals("N/A")) {
                    double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                    double largestTemp = Double.parseDouble(lowestHumiditySoFar.get("Humidity"));


                    if (currentTemp < largestTemp) {
                        lowestHumiditySoFar = currentRow;

                    }
                }

            }
        }
        return  lowestHumiditySoFar;
    }

    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println(csv.get("DateUTC")+": "+csv.get("Humidity"));
    }


    public CSVRecord lowestHumidityInManyFiles(){

        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        File fname = null;
        CSVRecord CR = null;
        FileResource f1 = null;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if(largestSoFar == null){
                largestSoFar = currentRow;

            }

            else{
                double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                double largestTemp = Double.parseDouble(largestSoFar.get("Humidity"));

                if(currentTemp < largestTemp){
                    largestSoFar = currentRow;
                    fname = f;
                    f1 = fr;

                }
            }

        }
        System.out.println("Lowest Humidity was: "+fname.getName() );

        return  largestSoFar;

    }

    public void testlowestHumidityInManyFiles(){
        CSVRecord Mlargest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was: "+Mlargest.get("Humidity")+Mlargest.get("DateUTC"));
    }


    public double averageTemperatureInFile(CSVParser parser){

        double average = 0.0;
        double sumTemp = 0.0;
        int i = 0;
        for(CSVRecord currentRow : parser){
            i = i+1;


                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                sumTemp = sumTemp + currentTemp;




        }
        average = sumTemp/i;
        return average;
    }


    public void testaverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is "+ average);
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double Humidity = (double)value;
        double average = 0.0;
        double sumTemp = 0.0;
        int i = 0;
        for(CSVRecord currentRow : parser){

            if(Double.parseDouble(currentRow.get("Humidity"))>Humidity){
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                sumTemp = sumTemp + currentTemp;
                i = i+1;

            }


        }

        if(i == 0){
            average =0.0;
        }
        else{
            average = sumTemp/i;
        }

        return average;

    }


    public void testaverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureWithHighHumidityInFile(parser, 80);
        if(average == 0.0){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println("Average Temp when high Humidity is: "+average );
        }
    }

    public static void main (String[] args){

        CSVMax Tr = new CSVMax();
        Tr.test();
        //Tr.testLowestHumidityInFile();
        //Tr.testlowestHumidityInManyFiles();
        //Tr.testaverageTemperatureInFile();
        //Tr.testaverageTemperatureWithHighHumidityInFile();

    }



}
