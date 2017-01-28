package herririk_herri.tta.intel.ehu.eus.herririk_herri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    public void selAriketa(View view)
    {

        SharedPreferences myprefs= this.getSharedPreferences("herri", MODE_WORLD_READABLE);
        //this.setTitle("Prueba111");


        switch (view.getId())
        {
            case (R.id.selArik_button_audio):
                Intent intent=new Intent(this,AudioActivity.class);
                myprefs.edit().putString("ariketa","Audio").commit();
                myprefs.edit().commit();
                startActivity(intent);

                break;
            case (R.id.selArik_button_img):
                Intent intent2=new Intent(this,ImgActivity.class);
                myprefs.edit().putString("ariketa","Img").commit();
                myprefs.edit().commit();
                startActivity(intent2);

                break;
            case (R.id.selArik_button_record):
                Intent intent3=new Intent(this,RecordActivity.class);
                myprefs.edit().putString("ariketa","Record").commit();
                myprefs.edit().commit();
                startActivity(intent3);

                break;

        }
    }
}
