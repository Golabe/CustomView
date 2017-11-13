package buzz.pushfit.water_loading;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by yuequan on 2017/11/13.
 */

@SuppressLint("AppCompatCustomView")
public class WaterText extends TextView {

    private BitmapShader shader;
    private Matrix matrix;
    private int height;
    private int h;

    public WaterText(Context context) {
        this(context, null);
    }

    public WaterText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setTextColor(Color.RED);
        Typeface typeface = Typeface.createFromAsset(getResources().getAssets(), "rai.ttf");
        setTypeface(typeface);
        //Matrix  矩阵  可以实现平移旋转，3d效果
        matrix = new Matrix();


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //创建着色器
        createShader();
        this.h=h;
    }

    private void createShader() {
        //通过Resources 获取到的Drawable 没有边界
        Drawable wave = getResources().getDrawable(R.drawable.wave);
        int width = wave.getIntrinsicWidth();
        height = wave.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        //创建一个画布  为了将water图片的颜色数据写入Bitmap
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(getCurrentTextColor());

        wave.setBounds(0, 0, width, height);//设置边界
        wave.draw(canvas);

        //CLAMP 使用原来的图片
        //REPEAT  将原来的图片复制无数
        //MIRROR  将原来的图片镜像后，写入，再镜像
        shader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        getPaint().setShader(shader);//利用着色器进行着色
        shaderX = 0;
        shaderY = height / 2;
    }

    private float shaderX;
    private float shaderY;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        shaderX += 10;
        shaderY += 0.5;

        if (shaderY>-height/2+h){
            shaderY=-height/2;
        }
        //让BitmapShader 不断向下，向右移动

        matrix.setTranslate(shaderX, shaderY);
        //为着色器设置Matrix  ，实现着色器的移动
        shader.setLocalMatrix(matrix);
        invalidate();

    }
}
