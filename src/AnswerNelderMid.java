import java.util.ArrayList;

public class AnswerNelderMid {
    private double Q;
    private Point T;
    private double eps;
    private double alpha;
    private double beta;
    private double gamma;
    private int steps;
    public AnswerNelderMid(double Q, Point T, double eps, double alpha, double beta, double gamma, int steps) {
        this.Q = Q;
        this.T = T;
        this.eps = eps;
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "Ответ: minQ =" + Q +
                ", T = " + T.getX().toString() +
                " , eps=" + eps +
                " , alpha=" + alpha +
                " , beta=" + beta +
                " , gamma=" + gamma +
                ", steps =" + steps;
    }
}
