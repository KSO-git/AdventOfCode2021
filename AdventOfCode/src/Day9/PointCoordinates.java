package Day9;

public class PointCoordinates {
    private int row;
    private int col;
    private int basins = 0;

    public PointCoordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getBasins() {
        return basins;
    }

    public void setBasins(int basins) {
        this.basins = basins;
    }
}
