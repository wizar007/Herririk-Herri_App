package herririk_herri.tta.intel.ehu.eus.herririk_herri;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class SelAriketaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_ariketa);
        SharedPreferences prefs = getSharedPreferences("user", MODE_WORLD_READABLE);
        int ariketa2=prefs.getInt("unlockariketa2",0);
        int ariketa3=prefs.getInt("unlockariketa3",0);
        Button mButton=(Button)findViewById(R.id.selArik_button_img);
        if(ariketa2==1)
        {
            mButton.setCompoundDrawables(null,null,null,null);
        }
        else
        {
            mButton.setOnClickListener(null);
        }
        mButton=(Button)findViewById(R.id.selArik_button_record);
        if(ariketa3==1)
        {
            mButton.setCompoundDrawables(null,null,null,null);
        }
        else
        {
            mButton.setOnClickListener(null);
        }

    }
}
