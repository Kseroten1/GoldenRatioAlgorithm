public class Main {

    private static double function(double[] x) {
        return Math.pow(x[0],3) * Math.sin(x[1] + 4 * x[2]);
    }

    private static double functionForGoldenRatio(double k) {
        return Math.pow((xn[0] + k * g[0]),3) * Math.sin((xn[1] + k * g[1]) + 4 * (xn[2] + k * g[2]));
    }

    private static double derivativeX0(double[] x) {
        return 3 * Math.pow(x[0],2) * Math.sin(x[1]);
    }

    private static double derivativeX1(double[] x) {
        return Math.pow(x[0],3) * Math.cos(x[1]);
    }

    private static double derivativeX2(double[] x) {
        return 4;
    }

    private static double functionEuk(double[] x, double[] xn) {
        return Math.sqrt(Math.pow(xn[0]-x[0], 2) + Math.pow(xn[1]-x[1], 2) + Math.pow(xn[2]-x[2], 2));
    }

    private static double[] gradient(double[] x) {
        return new double[]{-derivativeX0(x), -derivativeX1(x), -derivativeX2(x)};
    }

    private static double goldenRatio() {
        double a = -10;
        double b = 10;
        double k = (Math.sqrt(5) - 1) / 2;
        double xL = b - k * (b - a);
        double xP = a + k * (b - a);
        double eps = 0.02;
        while ((b - a) > eps) {
            if (functionForGoldenRatio(xL) < functionForGoldenRatio(xP)) {
                b = xP;
                xP = xL;
                xL = b - k * (b - a);
            } else {
                a = xL;
                xL = xP;
                xP = a + k * (b - a);
            }
        }
        return (a + b) / 2;
    }

    static double[] x = {1, 1, 1};
    static double[] xn = {1, 1, 1};

    static double[] g;

    public static void main(String[] args) {
        double e = 0.06;
        double k;
        for(int i = 0; i < 1000; i++){
            g = gradient(xn);
            k = goldenRatio();
            System.arraycopy(xn, 0, x, 0, x.length);
            for(int j = 0; j < 3; j++){
                xn[j] += k*g[j];
            }
            if(functionEuk(x, xn) < e){
                break;
            }
        }
        for(int i = 0; i < 3; i++){
            System.out.println(xn[i]);
        }
    }
}