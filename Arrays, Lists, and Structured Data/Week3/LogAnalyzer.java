
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;


public class LogAnalyzer
{
     private ArrayList<LogEntry> records;

     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<>();

     }
        
     public void readFile(String filename) {

         FileResource fr = new FileResource(filename);
         for(String s : fr.lines()){
             LogEntry i = WebLogParser.parseEntry(s);
             records.add(i);
         }
         // complete method
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }

     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<>();
         for(LogEntry le: records){
             String ipAddr = le.getIpAddress();
             if(!uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
             }

         }
         return uniqueIPs.size();
     }

     public void printAllHigherThanNum(int num){

         for(LogEntry le: records){
             int statecode = le.getStatusCode();
             if(statecode>num){
                 System.out.println(le);
             }

         }

     }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for(LogEntry le: records){
            String ipAddr = le.getIpAddress();
            Date day = le.getAccessTime();
            String str = day.toString().substring(4,10);
            if(str.equals(someday)){
                if(!uniqueIPs.contains(ipAddr)){
                    uniqueIPs.add(ipAddr);
                }
            }

        }
        return uniqueIPs;
    }


    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<>();

        for(LogEntry le: records){
            int num = le.getStatusCode();
            if(num >= low){
                if(num <= high){
                    String ipAddr = le.getIpAddress();
                    if(!uniqueIPs.contains(ipAddr)){
                        uniqueIPs.add(ipAddr);
                    }
                }
            }
        }
    return uniqueIPs.size();
    }

    public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> counts = new HashMap<String, Integer>();

         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(!counts.containsKey(ip)){
                 counts.put(ip,1);
             }

             else{
                 int i = counts.get(ip);
                 counts.put(ip,i+1);
             }
         }
         return counts;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
         int mostnumber = 0;
         for(String s : counts.keySet()){
             if(counts.get(s)>mostnumber){
                 mostnumber = counts.get(s);
             }
         }
    return mostnumber;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
        int mostnumber = 0;
        ArrayList<String> MostIP = new ArrayList<>();
        for(String s : counts.keySet()){
            if(counts.get(s)>=mostnumber){
                mostnumber = counts.get(s);
                MostIP.add(s);
            }
        }
        return MostIP;
    }

    public HashMap<String, ArrayList<String>> iPsForDays(){
        HashMap<String,ArrayList<String>> IPDay = new HashMap<String, ArrayList<String>>();

        for(LogEntry le: records){
            ArrayList<String> IPs = new ArrayList<>();
            String ipAddr = le.getIpAddress();
            Date day = le.getAccessTime();
            String str = day.toString().substring(4,10);
            if(!IPDay.containsKey(str)){
                IPs.add(ipAddr);
                IPDay.put(str,IPs);
            }

            else{
                IPs = IPDay.get(str);
                IPs.add(ipAddr);
                IPDay.put(str, IPs);
            }

        }
        return IPDay;

    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> IPDay){
         String z = null;
         int max = 0;
        for(String s : IPDay.keySet()){

            if(IPDay.get(s).size()>max){
                max = IPDay.get(s).size();
                z =s;

            }
        }
        return z;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> IPDay,String day){
        HashMap<String, Integer> OneIPDay = new HashMap<>();
        ArrayList<String> res = new ArrayList<>();
        for(String s : IPDay.keySet()){
            if(s.equals(day)){
                ArrayList<String> OneDayIPs;
                OneDayIPs = IPDay.get(s);

                for(String x : OneDayIPs){
                    if(!OneIPDay.containsKey(x)){
                        OneIPDay.put(x,1);
                    }
                    else{
                        int z = OneIPDay.get(x);
                        z++;
                        OneIPDay.put(x,z);
                    }
                }
            }
        }
        int max = 0;
        for(String s: OneIPDay.keySet()){
            if(OneIPDay.get(s)>=max){
                max = OneIPDay.get(s);
            }

        }
        for(String s: OneIPDay.keySet()){
            if(OneIPDay.get(s) == max){
                res.add(s);
            }

        }

    return res;
    }


}
