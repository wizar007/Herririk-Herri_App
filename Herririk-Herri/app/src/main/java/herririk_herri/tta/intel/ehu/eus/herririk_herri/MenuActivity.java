package herririk_herri.tta.intel.ehu.eus.herririk_herri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MenuActivity extends AppCompatActivity {
    public final static String EXTRA_LOGIN="es.tta.ejemplo31.login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void selherri(View view)
    {
        //Intent intent=new Intent(this,HerriActivity.class);

        switch (view.getId())
        {
            case (R.id.menu_Bermeo):
                SharedPreferences myprefs= this.getSharedPreferences("herri", MODE_WORLD_READABLE);
                myprefs.edit().putString("herri","Bermeo").commit();

                break;

        }
    }
}
