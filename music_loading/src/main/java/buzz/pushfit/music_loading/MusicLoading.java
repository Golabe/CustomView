package buzz.pushfit.music_loading;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yuequan on 2017/11/13.
 */

public class MusicLoading extends View {
    private Paint paint;
    private int length;
    private int smallRadius=10;

    public MusicLoading(Context context) {
        this(context, null);
    }

    public MusicLoading(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MusicLoading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        length = Math.min(w, h);
    }

    private float startAngle1=0f;
    private float startAngle2=180f;
    private float sweepAngle=80f;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStrokeWidth(10);
        canvas.drawCircle(length / 2, length / 2, smallRadius, paint);

        paint.setStrokeWidth(5);
        canvas.drawCircle(length / 2, length / 2, length/2-smallRadius, paint);

        RectF rectF=new RectF(length/2-length/3,length/2-length/3,length/2+length/3,length/2+length/3);
        canvas.drawArc(rectF,startAngle1,sweepAngle,false,paint);
        canvas.drawArc(rectF,startAngle2,sweepAngle,false,paint);

        rectF=new RectF(length/2-length/4,length/2-length/4,length/2+length/4,length/2+length/4);

        canvas.drawArc(rectF,startAngle1,sweepAngle,false,paint);
        canvas.drawArc(rectF,startAngle2,sweepAngle,false,paint);


    }
}
