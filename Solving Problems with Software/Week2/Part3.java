public class Part3 {

    public void twoOccurrences(String stringa, String stringb){

    if (stringb.contains(stringa)){

        System.out.println(stringa);

        }

    }

    public void testtwoOccurrences(){

        String stringa ="atg";
        String stringb ="ctgtatgta";

        twoOccurrences(stringa, stringb);
    }

    public void testLastPart(){

        String stringa ="an";
        String stringb ="banana";

        LastPart(stringa, stringb);


         stringa ="zoo";
         stringb ="forest";

        LastPart(stringa, stringb);
    }


    public void LastPart(String stringa, String stringb){

        String result = "";
        if (stringb.contains(stringa)){
            int startIndex = stringb.indexOf(stringa)+stringa.length();
            int stopIndex = stringb.length();
            result = stringb.substring(startIndex,stopIndex);
            System.out.println(result);
        }
        else{

            result = stringb;
            System.out.println(result);
        }
    }


    public static void main (String[] args){
        Part3 Ts = new Part3();
        //Ts.testtwoOccurrences();
        Ts.testLastPart();
    }

}
