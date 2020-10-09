import edu.duke.*;
import org.apache.commons.csv.*;


public class WhichCountriesExport {
    public void listExports(CSVParser parser, String exportOfInterest){
        int a = 0;
        for(CSVRecord record :parser){
            String export = record.get("Exports");
            if(export.contains(exportOfInterest)){

                String country = record.get("Country");
                a ++;
                System.out.println(country);


            }
        }
        System.out.println(a);
    }


    public void test(){
        FileResource Fr = new FileResource();
        CSVParser parser = Fr.getCSVParser();
        //listExports(parser,"cocoa");
        //countryInfo(parser, "Nauru");
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //numberOfExporters(parser, "sugar");
        bigExporters(parser, "$999,999,999,999");

    }

    public void countryInfo(CSVParser parser, String countryname){
        int a = 0;
        for(CSVRecord record :parser){
            String Country = record.get("Country");
            String Value= record.get("Value (dollars)");
            String Exports = record.get("Exports");

            if(Country.contains(countryname)){
                a = a+1;
                System.out.println(Country+":"+Value+":"+Exports);

            }

        }
        if (a == 0){
            System.out.println("NOT FOUND");
        }

    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){

        for(CSVRecord record :parser){

            String Exports = record.get("Exports");

            if(Exports.contains(exportItem1)){
                if(Exports.contains(exportItem2)){
                    String Country = record.get("Country");
                    System.out.println(Country);
                }
            }
        }
    }

    public void numberOfExporters(CSVParser parser, String exportItem) {
        int a = 0;
        for (CSVRecord record : parser) {

            String Exports = record.get("Exports");

            if (Exports.contains(exportItem)) {
                a++;

            }
        }
        System.out.println(a);
    }

    public void bigExporters(CSVParser parser, String amount){

        for(CSVRecord record :parser){
            String Value= record.get("Value (dollars)");


            if(amount.length()<Value.length()){

                String Country = record.get("Country");
                System.out.println(Country+":"+Value);

            }
        }

    }




    public static void main (String[] args){

        WhichCountriesExport Tr = new WhichCountriesExport();
        Tr.test();

    }

}
