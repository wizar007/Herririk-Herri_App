package herririk_herri.tta.intel.ehu.eus.herririk_herri.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import herririk_herri.tta.intel.ehu.eus.herririk_herri.R;

public class MenuActivity extends AppCompatActivity {
    public final static String EXTRA_LOGIN="es.tta.ejemplo31.login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        SharedPreferences prefs = getSharedPreferences("user", MODE_WORLD_READABLE);
        int unlock=prefs.getInt("unlockTest",0);
        String valor="Valor de unlocktest es "+unlock;
        Log.d("Prueba_unlockTest",valor);
        if(unlock==1)
        {
            findViewById(R.id.menu_Test).setVisibility(View.VISIBLE);
        }

    }
    public void selherri(View view)
    {
        Intent intent=new Intent(this,HerriActivity.class);
        SharedPreferences myprefs= this.getSharedPreferences("herri", MODE_WORLD_READABLE);
        //this.setTitle("Prueba111");


        switch (view.getId())
        {
            case (R.id.menu_Bermeo):
                myprefs.edit().putString("herri","Bermeo").commit();
                startActivity(intent);

                break;
            case (R.id.menu_Gernika):
                myprefs.edit().putString("herri","Gernika").commit();
                startActivity(intent);

                break;
            case (R.id.menu_Lekeitio):
                myprefs.edit().putString("herri","Lekeitio").commit();
                startActivity(intent);

                break;
            case (R.id.menu_Zeberio):
                myprefs.edit().putString("herri","Zeberio").commit();
                startActivity(intent);

                break;
            case (R.id.menu_Ondarrua):
                myprefs.edit().putString("herri","Ondarrua").commit();
                startActivity(intent);

                break;
            case (R.id.menu_Zamudio):
                myprefs.edit().putString("herri","Zamudio").commit();
                startActivity(intent);

                break;

        }
    }


    public void test(View view)
    {
        Intent intent=new Intent(this,TestActivity.class);
        startActivity(intent);
    }
}
