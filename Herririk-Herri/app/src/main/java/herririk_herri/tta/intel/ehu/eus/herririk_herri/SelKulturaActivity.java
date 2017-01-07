package herririk_herri.tta.intel.ehu.eus.herririk_herri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class SelKulturaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_kultura);


        Button mButton=(Button)findViewById(R.id.herri_butt_kultur);
    }
}
