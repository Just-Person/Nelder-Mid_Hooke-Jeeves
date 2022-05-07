import java.util.ArrayList;

public class AnswerHookeJeeves {
    private double Q;
    private Point T;
    private double eps;
    private int steps;
    private ArrayList<Double> delta;
    public AnswerHookeJeeves(double Q, Point T, double eps, int steps, ArrayList<Double> delta) {
        this.Q = Q;
        this.T = T;
        this.eps = eps;
        this.steps = steps;
        this.delta = delta;
    }

    @Override
    public String toString() {
        return "Ответ: minQ =" + Q +
                ", T = " + T.getX().toString() +
                " , eps=" + eps +
                ", steps =" + steps;
    }
}
