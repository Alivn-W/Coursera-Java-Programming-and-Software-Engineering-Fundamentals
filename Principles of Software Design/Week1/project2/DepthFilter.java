public class DepthFilter implements Filter{

    private double depMin;
    private double depMax;

    public DepthFilter(double min, double max) {
        depMin = min;
        depMax = max;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth() >= depMin && qe.getDepth() <=depMax;
    }

    @Override
    public String getName() {
        return "DepthFilter";
    }
}
