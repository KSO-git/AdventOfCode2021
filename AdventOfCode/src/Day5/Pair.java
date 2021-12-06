package Day5;

public class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean arePoinOnTheSameStraightsX(Pair endPoint){
        return this.getX() == endPoint.getX();
    }

    public boolean arePoinOnTheSameStraightsY(Pair endPoint){
        return  this.getY() == endPoint.getY();
    }

    public boolean areDiagonal(Pair endPoint){
        return (this.getY() == endPoint.getX() && this.getX() == endPoint.getY()) ||
                (Math.abs(this.getY()-endPoint.getY()) == Math.abs(this.getX()-endPoint.getX()));
    }
}
