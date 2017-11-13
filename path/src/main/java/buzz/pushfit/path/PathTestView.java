package buzz.pushfit.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yuequan on 2017/11/2.
 */

public class PathTestView extends View {
    private Paint mPaint;
    private Path mPath;

    public PathTestView(Context context) {
        this(context, null);
    }

    public PathTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPath.moveTo(200, 200);
//        mPath.lineTo(100, 200);
//        mPath.lineTo(100, 300);
//        mPath.lineTo(200, 300);
//        mPath.close();


//        贝塞尔曲线
        mPath.moveTo(100,100);
        mPath.quadTo(200,400,100,300);
        mPath.quadTo(200,400,60,500);
        mPath.lineTo(100,300);
        mPath.lineTo(100,100);

        canvas.drawPath(mPath, mPaint);
    }
}
