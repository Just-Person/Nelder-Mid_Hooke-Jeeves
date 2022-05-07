import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        double alpha = 1.0;
        double beta = 2.0;
        double gamma = 0.5;
        ArrayList<Double> coordinates = new ArrayList<Double>();
        ArrayList<Double> delta = new ArrayList<Double>();
        ArrayList<Double> PointCoordinates = new ArrayList<>();
        PointCoordinates.add(0.99996);
        PointCoordinates.add(0.99993);
        Point NelderMidPoint = new Point(PointCoordinates);
        coordinates.add(-1.2);
        coordinates.add(1.0);
        delta.add(0.01);
        delta.add(0.01);
        Point HookeJeevesPoint = new Point(coordinates);

        AnswerHookeJeeves HookeJeevesAns = HookeJeevesAlg.doHookeJeevesAlg(HookeJeevesPoint, (point)->RosenbrockFunction(point), delta);
        AnswerNelderMid NelderMidAns = NelderMidAlg.doNelderMidAlg(NelderMidPoint,(point)->RosenbrockFunction(point),alpha,beta,gamma);
        System.out.println(HookeJeevesAns);
        System.out.println(NelderMidAns);
    }
    static double RosenbrockFunction(Point point)
    { double res= 100*Math.pow((Math.pow(point.getX().get(0), 2)- point.getX().get(1)),2) + Math.pow((1- point.getX().get(0)),2);
        System.out.println(res);
        return res;
    }
}
