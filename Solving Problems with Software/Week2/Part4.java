
import edu.duke.*;

public class Part4 {


    public void findURL() {

        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String s : ur.words()) {
            String result ="";
            if (s.toLowerCase().contains("youtube")){
                int startIndex = s.indexOf("\"");
                int stopIndex = s.lastIndexOf("\"");
                result = s.substring(startIndex+1,stopIndex);
                System.out.println(result);

            }

        }

    }

    public void TestfindURL() {



    }

    public static void main (String[] args){
        Part4 Ts = new Part4();
        //Ts.testtwoOccurrences();
        Ts.findURL();
    }


}
