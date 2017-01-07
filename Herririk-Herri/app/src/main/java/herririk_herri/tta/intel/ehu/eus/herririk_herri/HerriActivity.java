package herririk_herri.tta.intel.ehu.eus.herririk_herri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HerriActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herri);
        SharedPreferences prefs = getSharedPreferences("herri", MODE_PRIVATE);
        String restoredText = prefs.getString("herri", null);
        Button mButton=(Button)findViewById(R.id.herri_butt_kultur);
        mButton.setText("Ezagutu \""+restoredText+"\"!");
        this.setTitle(restoredText);

    }

    public void selariketa(View view)
    {
        Intent intent=new Intent(this,SelAriketaActivity.class);
        startActivity(intent);

    }
    public void selkultura(View view)
    {
        Intent intent=new Intent(this,SelKulturaActivity.class);
        startActivity(intent);

    }
}
