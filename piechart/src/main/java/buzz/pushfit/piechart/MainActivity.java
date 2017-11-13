package buzz.pushfit.piechart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuequan on 2017/11/2.
 */

public class MainActivity extends AppCompatActivity
{
    private Pie pie;
    private List<PieEntity>entities=new ArrayList<>();
    private int[] colors={Color.RED, Color.BLACK, Color.BLUE, Color.GRAY, Color.GREEN};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pie=findViewById(R.id.pie);

        for (int i=0;i<colors.length;i++){
            entities.add(new PieEntity(i+1f,colors[i]));
        }

        pie.setPieData(entities);


    }
}
