package herririk_herri.tta.intel.ehu.eus.herririk_herri.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import herririk_herri.tta.intel.ehu.eus.herririk_herri.R;

public class SelKulturaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_kultura);


        Button mButton=(Button)findViewById(R.id.herri_butt_kultur);
    }
    public void selkultura(View view)
    {
        Intent intent=new Intent(this,KulturActivity.class);
        SharedPreferences myprefs= this.getSharedPreferences("herri", MODE_WORLD_READABLE);
        //this.setTitle("Prueba111");


        switch (view.getId())
        {
            case (R.id.selKultur_Kultura):
                myprefs.edit().putString("kultur","Kultura").commit();
                startActivity(intent);

                break;
            case (R.id.selKultur_Gastronomia):
                myprefs.edit().putString("kultur","Gastronomia").commit();
                startActivity(intent);

                break;
            case (R.id.selKultur_Jaiak):
                myprefs.edit().putString("kultur","Jaiak").commit();
                startActivity(intent);

                break;
            case (R.id.selKultur_Bitxiker):
                myprefs.edit().putString("kultur","Bitxikeriak").commit();
                startActivity(intent);

                break;

        }
    }
}
