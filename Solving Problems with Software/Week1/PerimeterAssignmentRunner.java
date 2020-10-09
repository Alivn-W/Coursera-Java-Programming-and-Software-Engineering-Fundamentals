import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int i=0;
        for (Point Pt : s.getPoints()) {
            i = i+1;

        }
        return i;
    }

    public double getAverageLength(Shape s) {
        int i=0;
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
            i = i+1;
        }
        return totalPerim/i;
    }

    public double getLargestSide(Shape s) {

        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        double LargestSide = 0.0;
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            if(currDist > LargestSide){
                LargestSide = currDist;

            }
        }
        return LargestSide;
    }

    public double getLargestX(Shape s) {
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        double LargestX = 0;
        int currLX = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            currLX=currPt.getX();
            if (currLX > LargestX){
                LargestX = currLX;
            }
        }
        return LargestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double LPM =0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            File Lf;
            double length = getPerimeter(s);
            if(length >LPM ){
                LPM = length;
                Lf = f;
            }
        }
        return LPM;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double LPM =0.0;
        File Lf = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if(length >LPM ){
                LPM = length;
                Lf = f;
            }
        }

        return Lf.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int number = getNumPoints(s);
        double Alength = getAverageLength(s);
        double LS = getLargestSide(s);
        double LX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("numbers = " + number);
        System.out.println("Alength = " + Alength);
        System.out.println("LS = " + LS);
        System.out.println("LX = " + LX);

    }

    public void testPerimeterMultipleFiles() {
        double GLPM =  getLargestPerimeterMultipleFiles();
        System.out.println("GLPM = " + GLPM);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String FLPM =  getFileWithLargestPerimeter();
        System.out.println("FLPM = " + FLPM);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        //pr.testFileWithLargestPerimeter();
        //pr.testPerimeterMultipleFiles();
    }
}

