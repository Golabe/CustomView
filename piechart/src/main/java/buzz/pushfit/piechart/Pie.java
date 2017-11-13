package buzz.pushfit.piechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yuequan on 2017/11/2.
 * 步骤分解
 * 1：最内侧的扇形组成圆形
 * 2：中间的线段
 * 3：最外层的文字
 */

public class Pie extends View {
    private static final String TAG = "Pie";
    private List<PieEntity> entities;
    private int height;
    private int width;
    private Paint paint;
    private Path path;
    private int radius;
    private Paint linePaint;

    public Pie(Context context) {
        this(context, null);
    }

    public Pie(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Pie(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private RectF rectF;//扇形的外接举行
    private RectF touchRectf;//被触摸的扇形的外接矩形

    private void init() {
        rectF = new RectF();
        touchRectf=new RectF();
        paint = new Paint();
        paint.setAntiAlias(true);
        path = new Path();

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(3);//设置线宽;
        linePaint.setTextSize(30);



    }

    //当自定义控件的尺寸决定好的时候回调
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
        //为了防止绘制后超出屏幕区域  获取屏幕狂傲的较小值
        int min = Math.min(w, h);
        radius = (int) (min * 0.7f / 2);
        rectF.left = -radius;
        rectF.top = -radius;
        rectF.right = radius;
        rectF.bottom = radius;

        touchRectf.left=-radius-30;
        touchRectf.top=-radius-30;
        touchRectf.right=radius+30;
        touchRectf.bottom=radius+30;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(width / 2, height / 2);
        drawPie(canvas);
        canvas.restore();

    }

    private void drawPie(Canvas canvas) {
        float startAngle = 0;
        for (int i = 0; i < entities.size(); i++) {
            paint.setColor(entities.get(i).color);

            path.moveTo(0, 0);

            PieEntity entity = entities.get(i);

            float sweepAngle = entity.value / totalValue * 360 - 1;

            if (i==position){

                //绘制被触摸的扇形的点击事件
                path.arcTo(touchRectf,startAngle,sweepAngle);
            }else {

                path.arcTo(rectF, startAngle, sweepAngle);

            }
            //每次每个扇形区域的起始点就是上一个扇形区域的终点
            canvas.drawPath(path, paint);

            double a = Math.toRadians(startAngle + sweepAngle / 2);
            float startX = (float) ((radius + 5) * Math.cos(a));
            float startY = (float) ((radius + 5) * Math.sin(a));
            float endX = (float) ((radius + 60) * Math.cos(a));
            float endY = (float) ((radius + 60) * Math.sin(a));
            canvas.drawLine(startX, startY, endX, endY, linePaint);
            startAngles[i] = startAngle;
            startAngle += sweepAngle + 1;

            path.reset();//每次绘制完后对path进行重新绘制

            //绘制文本
            //文本内容
            String percent = String.format("%.1f", entities.get(i).value / totalValue * 100);
            percent = percent + "%";

            if (startAngle % 360.0f >= 90.0f && startAngle % 360.0f <= 270.0f) {
                //计算文本宽度
                float textWidth = linePaint.measureText(percent);
                canvas.drawText(percent, endX - textWidth, endY, linePaint);
            } else {
                canvas.drawText(percent, endX, endY, linePaint);
            }


        }

    }


    private float totalValue;

    private float[] startAngles;

    public void setPieData(List<PieEntity> entities) {
        this.entities = entities;
        for (PieEntity entity : entities) {
            totalValue += entity.value;
        }
        startAngles = new float[entities.size()];
    }

    //当用户于屏幕个进行交互
    //按下
    //抬起
    //移动
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();

                x = x - width / 2;
                y = y - height / 2;
                float touchAngle = MathUtil.getTouchAngle(x, y);

                float touchRadius = (float) Math.sqrt(x * x + y * y);
                if (touchRadius < radius) {
                    //未找到则返回-(和搜索的附近的大于搜索的正确值嘴硬的索引值+1)
                    //binaraySearch  参数2在参数1对应的集合中的索引
                    int searchResult = Arrays.binarySearch(startAngles, touchAngle);
                    if (searchResult < 0) {
                        position = -searchResult - 1 - 1;
                    } else {
                        position = searchResult;
                    }
                    Log.d(TAG, "onTouchEvent: " + position);

                    invalidate();//重绘
                }

                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return super.onTouchEvent(event);
    }

    private int position;//被点击的扇形区域对应的位置
}
