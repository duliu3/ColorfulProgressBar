package eternnal.trianglecolorfulprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ColorfulProgressBar bar = (ColorfulProgressBar) findViewById(R.id.colorProgress);
        bar.setProgress(55);
    }
}
