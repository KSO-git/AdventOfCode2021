package Day6;

public class FishBuckets {
    long numberOf0DayOld;
    long numberOf1DayOld;
    long numberOf2DayOld;
    long numberOf3DayOld;
    long numberOf4DayOld;
    long numberOf5DayOld;
    long numberOf6DayOld;
    long numberOf7DayOld;
    long numberOf8DayOld;

    public FishBuckets(long numberOf0DayOld, long numberOf1DayOld, long numberOf2DayOld, long numberOf3DayOld, long numberOf4DayOld, long numberOf5DayOld, long numberOf6DayOld, long numberOf7DayOld, long numberOf8DayOld) {
        this.numberOf0DayOld = numberOf0DayOld;
        this.numberOf1DayOld = numberOf1DayOld;
        this.numberOf2DayOld = numberOf2DayOld;
        this.numberOf3DayOld = numberOf3DayOld;
        this.numberOf4DayOld = numberOf4DayOld;
        this.numberOf5DayOld = numberOf5DayOld;
        this.numberOf6DayOld = numberOf6DayOld;
        this.numberOf7DayOld = numberOf7DayOld;
        this.numberOf8DayOld = numberOf8DayOld;
    }

    public void pass1Day(){
        long soonToGiveBirth = this.numberOf0DayOld;
        this.numberOf0DayOld = numberOf1DayOld;
        this.numberOf1DayOld = numberOf2DayOld;
        this.numberOf2DayOld = numberOf3DayOld;
        this.numberOf3DayOld = numberOf4DayOld;
        this.numberOf4DayOld = numberOf5DayOld;
        this.numberOf5DayOld = numberOf6DayOld;
        this.numberOf6DayOld = numberOf7DayOld + soonToGiveBirth;
        this.numberOf7DayOld = numberOf8DayOld;
        this.numberOf8DayOld = soonToGiveBirth;
    }

    public long sumAllUp(){
        return this.numberOf0DayOld + this.numberOf1DayOld + this.numberOf2DayOld + this.numberOf3DayOld
                + this.numberOf4DayOld + this.numberOf5DayOld + this.numberOf6DayOld + this.numberOf7DayOld 
                + this.numberOf8DayOld;
    }
}
