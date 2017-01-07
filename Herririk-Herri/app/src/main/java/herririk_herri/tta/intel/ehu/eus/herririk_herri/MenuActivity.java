package herririk_herri.tta.intel.ehu.eus.herririk_herri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
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
        //Intent intent=new Intent(this,TestActivity.class);
        //starActivity(intent);
    }
}
