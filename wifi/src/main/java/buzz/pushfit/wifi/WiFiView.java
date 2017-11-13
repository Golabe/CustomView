package buzz.pushfit.wifi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by yuequan on 2017/11/13.
 */

public class WiFiView extends View {
    private Paint paint;
    private int baseLength;
    private int delayedMillis=1000;

    public WiFiView(Context context) {
        this(context, null);
    }

    public WiFiView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WiFiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                invalidate();
                handler.postDelayed(this, delayedMillis);
            }
        }, delayedMillis);


    }

    private static Handler handler = new Handler();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        baseLength = Math.min(w, h);
    }

    private float signalSize = 4f;
    private float showSignalSize = 1f;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        showSignalSize++;
        if (showSignalSize > 4) {
            showSignalSize = 1;
        }
        canvas.save();
        canvas.translate(0, baseLength / signalSize);
        RectF rectF;

        float baseRadius = baseLength / 2 / signalSize;
        for (int i = 0; i < signalSize; i++) {

            if (i >=signalSize - showSignalSize) {
                float radius = baseRadius * i;
                rectF = new RectF(radius, radius, baseLength - radius, baseLength - radius);
                if (i < signalSize - 1) {
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawArc(rectF, -135, 90, false, paint);
                } else {
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawArc(rectF, -135, 90, true, paint);
                }
            }


        }
        canvas.restore();


    }
}
