import java.util.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData){
            if(qe.getLocation().distanceTo(from) < distMax){

                answer.add(qe);
            }
        }

        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> listBig = filterByMagnitude(list,5.0);
        int count = 0;
        for(QuakeEntry qe : listBig){
            System.out.println(qe);
            count++;
        }
        System.out.println("Found " +count+" quakes that match that criteria");


    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> re;
        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        // TODO
        re = filterByDistanceFrom(list,1000000,city);
        int count = 0;
        for(QuakeEntry qe : re){
            System.out.println(qe.getInfo());
            count++;
        }
        System.out.println("Found " +count+" quakes that match that criteria");



    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
                                               double minDepth,
                                               double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData){
            if(qe.getDepth() > minDepth){
                if(qe.getDepth() < maxDepth){
                    answer.add(qe);
                }
            }
        }
        return answer;
    }

    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> re = filterByDepth(list,-10000,-8000);
        int count = 0;
        for(QuakeEntry qe : re){
            System.out.println(qe);
            count++;
        }
        System.out.println("Found " +count+" quakes that match that criteria");
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
                                               String where,
                                               String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData){
            String info = qe.getInfo();
            if(where.equals("any")){
                String[] newArr = info.split("\\s+");
                for(String word : newArr)
                if(word.contains(phrase))
                    answer.add(qe);
            }
            if(where.equals("end")){
                int l = phrase.length();
                int i = info.length();
                String c = info.substring(i-l);
                if(c.equals(phrase))
                    answer.add(qe);
            }

            if(where.equals("start")){
                int l = phrase.length();
                String c = info.substring(0,l);
                if(c.equals(phrase))
                    answer.add(qe);
            }
        }
        return answer;
    }

    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> re = filterByPhrase(list,"any","Creek");
        int count = 0;
        for(QuakeEntry qe : re){
            System.out.println(qe);
            count++;
        }
        System.out.println("Found " +count+" quakes that match that criteria");

    }

    public static void main(String[] arg){
        EarthQuakeClient Te = new EarthQuakeClient();
        Te.quakesByPhrase();

    }

    
}
