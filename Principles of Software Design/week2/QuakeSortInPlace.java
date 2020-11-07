
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        int count = 0;
        for(int i = 0; i < in.size(); i++){
            if(!checkInSortedOrder(in)){
                int minIdx = getSmallestMagnitude(in,i);
                QuakeEntry qi = in.get(i);
                QuakeEntry qmin = in.get(minIdx);
                in.set(i,qmin);
                in.set(minIdx,qi);
                count++;
                System.out.println(count+" passes were needed to sort the elements");
            }

        }
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData,int from){
        int maxIdx = from;
        for (int i=from+1; i< quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;

    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        for (int i=0; i< 50; i++) {
            int maxIdx = getLargestDepth(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx,qi);
        }

    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> in, int numSorted){
        for (int i=0; i< numSorted; i++) {
            if(in.get(i).getMagnitude() > in.get(i+1).getMagnitude()){
                QuakeEntry temp = in.get(i); in.set(i, in.get(i + 1));in.set(i+1, temp);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        onePassBubbleSort(in,in.size()-1);
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakeData){
        for (int i=1; i< quakeData.size(); i++) {
            if(quakeData.get(i-1).getMagnitude() > quakeData.get(i).getMagnitude()){
                return false;
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        int count = 0;
        for(int i = 0; i < in.size(); i++){
            if(!checkInSortedOrder(in)){
                sortByMagnitudeWithBubbleSort(in);
                count++;
                System.out.println(count+" passes were needed to sort the elements");
            }

        }

    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        sortByMagnitudeWithCheck(list);
        int count = 0;
        for (QuakeEntry qe: list) {
            count++;
            System.out.print(count);
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}

	public static void main(String[] arg){
        QuakeSortInPlace tr = new QuakeSortInPlace();
        tr.testSort();
    }
}
