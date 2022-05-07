import java.util.ArrayList;
import java.util.Collections;

public class NelderMidAlg {
    public static AnswerNelderMid doNelderMidAlg(Point T0, FuncDelegate Q,
                                                 double alpha, double beta, double gamma){
        double eps = 0.000000000001;
        int steps = 0;
        double buffer = 0;
        ArrayList<Point> points = BeginPoints(T0);
        points = SortPoint(points, Q);
        while (SumPoints(points, Q) >= eps) {
            Point C = CalculateC(points);
            Point U = CalculateU(points, C, alpha);
            if (Q.function(points.get(0)) <= Q.function(U) && Q.function(points.get(points.size() - 2)) >= Q.function(U)) {
                points = ResultBetween(points, U);
            }
            else if (Q.function(U) < Q.function(points.get(0))) {
                Point V = CalculateV(C, U, beta);
                if (Q.function(V) < Q.function(U)) points = ResultBetween(points, V);
                else points = ResultBetween(points, U);
            }
            else {
                Point W = CalculateW(points, Q, C, U, gamma);
                if (Q.function(W) < Math.min(Q.function(U), Q.function(points.get(points.size() - 1)))) {
                    points = ResultBetween(points, W);
                } else {
                    points = UpdatePoints(points);
                }
            }
            points = SortPoint(points, Q);
            steps++;
        }
        return new AnswerNelderMid(Q.function(points.get(0)),points.get(0),eps,alpha,beta,gamma,steps);
    }
    private static ArrayList<Point> BeginPoints(Point T0)
    {
        ArrayList<Point> Points = new ArrayList<>();
        Points.add(T0);
        for (int i = 0; i < T0.getX().size(); i++)
        {
            Point point = new Point(T0.getX());
            point.getX().remove(i);
            point.getX().add(i,T0.getX().get(i) + 0.00003);
            Points.add(point);
        }
        return Points;
    }
    private static ArrayList<Point> SortPoint(ArrayList<Point> points, FuncDelegate Q)
    {
        for (int i = points.size()-1; i>0; i--) {
            for (int j = 0; j < i; j++) {
                if (Q.function(points.get(j)) > Q.function(points.get(j + 1))) {
                    Collections.swap(points, j, j + 1);
                }
            }
        }
        return points;
    }
    private static double SumPoints(ArrayList<Point> points, FuncDelegate Q)
    {
        double sum = 0.0;
        for (int i = 1; i < points.size(); i++) {
            sum += Math.pow(Q.function(points.get(i)) - Q.function(points.get(0)), 2);
        }
        sum = Math.pow(sum / points.size(), 0.5);
        return sum;
    }
    private static Point CalculateC(ArrayList<Point> points)
    {
        double buffer = 0.0;
        Point C = new Point(new ArrayList<Double>());
        for (int i = 0; i < points.get(0).getX().size(); i++) {
            buffer = 0.0;
            for (int j = 0; j < points.size() - 1; j++) {
                buffer += points.get(j).getX().get(i);
            }
            C.getX().add(buffer / points.size());
        }
        return C;
    }
    private static Point CalculateU(ArrayList<Point> points, Point C, double alpha)
    {
        Point U = new Point(new ArrayList<Double>());
        for (int i = 0; i < C.getX().size(); i++) {
            U.getX().add(C.getX().get(i) + alpha * (C.getX().get(i) - points.get(points.get(0).getX().size() - 1).getX().get(i)));
        }
        return U;
    }
    private static Point CalculateV (Point C, Point U, double beta)
    {
        Point V = new Point(new ArrayList<Double>());
        for (int i = 0; i < U.getX().size(); i++) {
            V.getX().add(C.getX().get(i) + beta * (U.getX().get(i) - C.getX().get(i)));
        }
        return V;
    }
    private static Point CalculateW (ArrayList<Point> points, FuncDelegate Q, Point C, Point U, double gamma)
    {
        Point W = new Point(new ArrayList<Double>());
        if (Q.function(U) < Q.function(points.get(points.size() - 1))) {
            for (int i = 0; i < C.getX().size(); i++) {
                W.getX().add(C.getX().get(i) + gamma * (U.getX().get(i) - C.getX().get(i)));
            }
        } else {
            for (int i = 0; i < C.getX().size(); i++) {
                W.getX().add(C.getX().get(i) + gamma * (points.get(points.size() - 1).getX().get(i) - C.getX().get(i)));
            }
        }
        return W;
    }
    private static ArrayList<Point> ResultBetween(ArrayList<Point> points, Point U)
    {
        points.remove(points.size() - 1);
        points.add(U);
        return points;
    }
    private static ArrayList<Point> UpdatePoints(ArrayList<Point> points)
    {
        ArrayList<Point> pointsnew = new ArrayList<Point>();
        pointsnew.add(points.get(0));
        for (int i = 1; i < points.size(); i++) {
            Point buffernewpoint = new Point(new ArrayList<Double>());
            for (int j = 0; j < points.get(0).getX().size(); j++) {
                buffernewpoint.getX().add((points.get(0).getX().get(j) + points.get(i).getX().get(j)) / 2);
            }
            pointsnew.add(buffernewpoint);
        }
        points.clear();
        for (int i = 0; i < pointsnew.size(); i++) {
            points.add(pointsnew.get(i));
        }
        return points;
    }

}