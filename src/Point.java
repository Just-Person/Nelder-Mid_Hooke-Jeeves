import java.util.ArrayList;

public class Point {
    private ArrayList<Double> X;
    public Point(ArrayList<Double> X) {
        this.X = new ArrayList<Double>();
        for (int i = 0; i < X.size(); i++)
        {
            this.X.add(X.get(i));
        }
    }
    public ArrayList<Double> getX() {
        return X;
    }
}
