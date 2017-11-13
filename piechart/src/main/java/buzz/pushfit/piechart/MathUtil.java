package buzz.pushfit.piechart;

/**
 * Created by yuequan on 2017/11/3.
 */

public class MathUtil {

    public static float getTouchAngle(float x, float y) {
        float touchAngle = 0;
        if (x < 0 && y < 0) {
            touchAngle += 180;
        } else if (y < 0 && x > 0) {
            touchAngle += 360;
        } else if (y > 0 && x < 0) {
            touchAngle += 180;
        }

        touchAngle += Math.toDegrees(Math.atan(y / x));
        if (touchAngle < 0) {
            touchAngle = touchAngle + 360;
        }
        return touchAngle;
    }
}
