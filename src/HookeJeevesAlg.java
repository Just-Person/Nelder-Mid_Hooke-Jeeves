import java.util.ArrayList;

public class HookeJeevesAlg {
    public static AnswerHookeJeeves doHookeJeevesAlg(Point T0, FuncDelegate Q, ArrayList<Double> delta){
        double eps = 0.0000001;
        double h = 0.03;
        Point T = T0;
        int steps = 0;
        double buffer = 0;
        while (deltamore(delta, eps)){
            T= directionSearch(T0, xiShift(T0, Q, delta, 0), Q, h);
            if(T.equals(T0)){
                for (int i = 0; i < delta.size(); i++)
                {
                    buffer = delta.get(i);
                    delta.remove(i);
                    delta.add(i,buffer - eps);
                }
            }
            steps ++;
            T0=T;
        }
        return new AnswerHookeJeeves(Q.function(T),T,eps,steps,delta);
    }

    private static Point xiShift(Point T0, FuncDelegate Q, ArrayList<Double> delta, int index){
        Point T2;
        ArrayList<Double> coordinates = new ArrayList<Double>();
        for (int i = 0; i < T0.getX().size(); i++)
        {
            if (i == index)
                coordinates.add(T0.getX().get(index) + delta.get(index));
            else
                coordinates.add(T0.getX().get(i));
        }
        Point T1_t = new Point(coordinates);
        if(Q.function(T1_t) < Q.function(T0)){
            T2=T1_t;
        }
        else{
            coordinates.remove(index);
            coordinates.add(index,T0.getX().get(index) - delta.get(index));
            Point T1_d = new Point(coordinates);
            if(Q.function(T1_d) < Q.function(T0)){
                T2=T1_d;
            }
            else{
                T2=T0;
            }
        }
        if (index == T2.getX().size()-1)
            return T2;
        else
            return xiShift(T2, Q, delta, index + 1);
    }
    public static Point x1Shift(Point T0, FuncDelegate Q, double delta1){
        Point T1;
        ArrayList<Double> coordinates = new ArrayList<Double>();
        coordinates.add(T0.getX().get(0)+ delta1);
        coordinates.add( T0.getX().get(1));
        Point T1_t = new Point(coordinates);
        if(Q.function(T1_t) < Q.function(T0)){
            T1=T1_t;
        }
        else{
            coordinates = new ArrayList<Double>();
            coordinates.add(T0.getX().get(0)- delta1);
            coordinates.add( T0.getX().get(1));
            Point T1_d = new Point(coordinates);
            if(Q.function(T1_d) < Q.function(T0)){
                T1=T1_d;
            }
            else{
                T1=T0;
            }
        }
        return T1;
    }

    public static Point x2Shift(Point T0, FuncDelegate Q, double delta2){
        Point T2;
        ArrayList<Double> coordinates = new ArrayList<Double>();
        coordinates.add(T0.getX().get(0));
        coordinates.add(T0.getX().get(1)+ delta2);
        Point T1_t = new Point(coordinates);
        if(Q.function(T1_t) < Q.function(T0)){
            T2=T1_t;
        }
        else{
            coordinates = new ArrayList<Double>();
            coordinates.add(T0.getX().get(0));
            coordinates.add(T0.getX().get(1)- delta2);
            Point T1_d = new Point(coordinates);
            if(Q.function(T1_d) < Q.function(T0)){
                T2=T1_d;
            }
            else{
                T2=T0;
            }
        }
        return T2;
    }
    private static Point directionSearch(Point T0,Point T2, FuncDelegate Q, double h){
        Point T;
        ArrayList<Double> coordinates = new ArrayList<Double>();
        for(int i = 0; i<T0.getX().size(); i++)
        {
            coordinates.add(T0.getX().get(i)+h*(T2.getX().get(i)-T0.getX().get(i)));
        }
        Point X_d = new Point(coordinates);
        if(Q.function(X_d) < Q.function(T0)){
            T=X_d;
        }
        else{
            T=T2;
        }
        return T;
    }
    static boolean deltamore(ArrayList<Double> delta, double eps)
    {
        boolean more = true;
        for (int i = 0; i < delta.size(); i++)
        {
            if (delta.get(i) < eps)
                more = false;
        }
        return more;
    }

}
