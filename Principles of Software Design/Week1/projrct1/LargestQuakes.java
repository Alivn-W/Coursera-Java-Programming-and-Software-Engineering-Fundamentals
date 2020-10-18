import java.util.*;

public class LargestQuakes {

    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        int count = 0;
        for(QuakeEntry qe : list){
            System.out.println(qe);
            count++;
        }
        System.out.println("Found " +count+" quakes that match that criteria");
        System.out.println(getLargest(list,50));

    }

    public int indexOfLargest(ArrayList<QuakeEntry> list){

        double max = 0.0;
        int index = 0;
        for(QuakeEntry qe : list){
            if(qe.getMagnitude() > max){

                max = qe.getMagnitude();
                index = list.indexOf(qe);
            }
        }

        System.out.println(index);
        System.out.println(max);


        return index;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<>(howMany);

        for(int count = 0; count < howMany;count++){
            answer.add(copy.get(indexOfLargest(copy)));
            copy.remove(copy.get(indexOfLargest(copy)));

        }
        return answer;
    }


    public static void main(String[] arg){
        LargestQuakes Te = new LargestQuakes();
        Te.findLargestQuakes();


    }
}
