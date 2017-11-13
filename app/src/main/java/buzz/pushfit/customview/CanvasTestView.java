package buzz.pushfit.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yuequan on 2017/11/2.
 */

public class CanvasTestView extends View {
    private Paint mPaint;

    //    主要用于创建实例
    public CanvasTestView(Context context) {
        this(context, null);
    }

    //    主要用于将当前自定义控件声明在不居中
    public CanvasTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //主要用将用户自定义控件声明在布局文件中，并且加入样式
    public CanvasTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //    构造方法的意义：用于初始化加载数据，即自定义控件流程的第一步
    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);//空心
        mPaint.setAntiAlias(true);//去锯齿
    }

    //用于封装X,Y坐标的坐标点
    private PointF facePoint = new PointF(240, 400);
    private int faceRadius = 200;

    private PointF line1Start = new PointF(140, 250);
    private PointF line1End = new PointF(340, 250);
    private PointF line2Start = new PointF(240, 250);
    private PointF line2End = new PointF(240, 500);
    private PointF line3End = new PointF(150, 450);

    private PointF eye1Point = new PointF(170, 330);
    private PointF eye2Point = new PointF(310, 330);
    private int eyeRadius = 60;
    private RectF mouth = new RectF(80, 300, 400, 550);
    private float startAngle = 380f;
    private float sweepAngle = 140f;
    private Rect leftEar=new Rect(30,300,80,500);


    private float[] pts={};

    //在绘制阶段调用
    @Override

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(200,0);
        canvas.rotate(-45);
        canvas.drawCircle(facePoint.x, facePoint.y, faceRadius, mPaint);
        canvas.drawLine(line1Start.x, line1Start.y, line1End.x, line1End.y, mPaint);
        canvas.drawLine(line2Start.x, line2Start.y, line2End.x, line2End.y, mPaint);
        canvas.drawLine(line2End.x, line2End.y, line3End.x, line3End.y, mPaint);
        canvas.drawCircle(eye1Point.x, eye1Point.y, eyeRadius, mPaint);
        canvas.drawCircle(eye2Point.x, eye2Point.y, eyeRadius, mPaint);


        canvas.drawArc(mouth, startAngle, sweepAngle, false, mPaint);
       // canvas.drawRect(mouth,mPaint);

        canvas.drawRect(leftEar,mPaint);
        canvas.restore();

    }
}
