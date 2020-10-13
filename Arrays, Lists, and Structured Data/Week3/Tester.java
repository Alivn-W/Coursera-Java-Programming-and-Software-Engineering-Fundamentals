
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer L = new LogAnalyzer();
        L.readFile("weblog2_log");
        //L.printAll();
        //testUniqueIP(L);
        //L.printAllHigherThanNum(400);
        //System.out.println(L.uniqueIPVisitsOnDay("Sep 27").size());
        //System.out.println(L.countUniqueIPsInRange(400,499));
        //System.out.println(L.countVisitsPerIP());
        //System.out.println(L.mostNumberVisitsByIP(L.countVisitsPerIP()));
        //System.out.println(L.iPsMostVisits(L.countVisitsPerIP()));
        //System.out.println(L.iPsForDays());
        //System.out.println(L.dayWithMostIPVisits(L.iPsForDays()));
        System.out.println(L.iPsWithMostVisitsOnDay(L.iPsForDays(),"Sep 29"));
    }

    public void testUniqueIP(LogAnalyzer L){
        System.out.println(L.countUniqueIPs());
    }

    public static void main(String[] arg){

        Tester Tr = new Tester();
        Tr.testLogAnalyzer();
    }
}
