package octils.base.util;

import java.util.Random;

public class NumUtils {

    private NumUtils() {
        throw new RuntimeException("Cannot instantiate a utility class.");
    }

    public static int getRandom(int max, int min) {
		return new Random().nextInt(max - min + 1) + min;
    }
    
    public static int getElo(int winner, int loser) {
        double calc = 1.0 / (1.0 + Math.pow(10.0, (winner - loser) / 400.0));
        return (int)(calc * 32.0);
    }
    
    public static double getKd(int k, int d) {
        double n;

        if (k > 0 && d == 0) {
            n = k;
        }
        else if (k == 0 && d == 0) {
            n = 0;
        }
        else {
            n = (double)k / (double)d;
        }

        return Double.parseDouble(FUtils.getFormat().format(n));
    }

    public static double getPercentage(double hundred_percent, double percent_to_calc){
        return hundred_percent == 0 ? 0 : (percent_to_calc*100.0)/hundred_percent;
    }

    public static boolean isInRange(int nm, int min, int max)
    {
        return nm>min && nm<max;    
    }
}
