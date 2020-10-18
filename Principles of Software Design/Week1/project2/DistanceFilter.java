public class DistanceFilter implements Filter {

    private double disMax;
    private Location lo;

    public DistanceFilter(double max, Location l) {
        disMax = max;
        lo = l;
    }
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(lo) <= disMax;
    }

    @Override
    public String getName() {
        return "DistanceFilter";
    }
}
