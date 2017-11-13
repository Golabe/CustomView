package buzz.pushfit.drawtext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yuequan on 2017/11/2.
 */

public class DrawTextView extends View {
    private Paint mPaint;

    public DrawTextView(Context context) {
        this(context,null);
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint=new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(30);
        mPaint.setFakeBoldText(true);//文字加粗
        mPaint.setUnderlineText(true);//文字下划线
        mPaint.setStrikeThruText(true);//文字中间横线穿过
        mPaint.setTextAlign(Paint.Align.CENTER);//设置文本位置


    }
private String text="我是中国人";
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(text,240,400,mPaint);
    }
}
