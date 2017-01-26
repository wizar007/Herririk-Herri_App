package herririk_herri.tta.intel.ehu.eus.herririk_herri;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.Exercise;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.ListExercise;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.ListTest;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.Test;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.coms.ProgressTask;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.coms.RestClient;

public class TestActivity extends AppCompatActivity {
    private int indice=0;
    private int size=2;
    final String URL=null;
    private List<Test> lTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        final String url=getString(R.string.url_server);
        //llamada a logica para solicitar los datos, petición al servidor
        View element=findViewById(R.id.record_button_init);
        element.setVisibility(View.GONE);
        final int indice=0;
        new ProgressTask<ListTest>(this)
        {

            @Override
            protected ListTest work() throws Exception {
                RestClient rest = new RestClient(url);
                ListTest ejercicios = new ListTest();
                JSONObject jsonObject = rest.getJson(String.format("requestTest"));
                JSONArray lista = jsonObject.getJSONArray("test");

                for (int i = 0; i < lista.length(); i++) {
                    JSONObject temp = lista.getJSONObject(i);


                    Test objeto = new Test(temp.getString("enunciado"), temp.getString("opcion_1"), temp.getString("opcion_2"), temp.getString("opcion_3"), temp.getInt("solucion"));

                    ejercicios.addChoice(objeto);


                }
                ejercicios.setTestCode(jsonObject.getString("testCode"));
                ejercicios.setSize(jsonObject.getInt("total"));

            return ejercicios;
            }

            @Override
            protected void onFinish(ListTest result) {
                lTest=result.lTest;
                size=result.getSize();
                Toast.makeText(context,"Exito",Toast.LENGTH_SHORT).show();
                getData();

            }


        }.execute();
    }

    protected void getData()
    {

        Test test=lTest.get(indice);
        TextView textWording=(TextView)findViewById(R.id.test_enunciado);
        textWording.setText(test.getEnunciado());
        RadioGroup group= (RadioGroup)findViewById(R.id.test_choices);
        RadioButton radio= new RadioButton(this);
        radio.setText(test.getOpcion_1());
        radio.setOnClickListener(this);
        group.addView(radio);
        RadioButton radio2= new RadioButton(this);
        radio2.setText(test.getOpcion_1());
        radio2.setOnClickListener(this);
        group.addView(radio2);
        RadioButton radio3= new RadioButton(this);
        radio3.setText(test.getOpcion_1());
        radio3.setOnClickListener(this);
        group.addView(radio3);


    }
}
