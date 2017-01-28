package herririk_herri.tta.intel.ehu.eus.herririk_herri.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import herririk_herri.tta.intel.ehu.eus.herririk_herri.R;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.Exercise;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.ListExercise;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.ListTest;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.Test;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.coms.ProgressTask;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.coms.RestClient;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    private int indice=0;
    private int size=2;
    final String URL=null;
    private List<Test> lTest;
    int aciertos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


    }

    public void load(View view)
    {
        final String url=getString(R.string.url_server);
        //llamada a logica para solicitar los datos, petición al servidor
        View element=findViewById(R.id.test_button_init);
        element.setVisibility(View.GONE);
        aciertos=0;
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

                getData();

            }


        }.execute();

    }

    protected void zuzendu(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.test_choices);
        int choices = group.getChildCount();
        for (int i = 0; i < choices; i++)
            group.getChildAt(i).setEnabled(false);
        View selected = group.findViewById(group.getCheckedRadioButtonId());
        final int selectednum=group.indexOfChild(selected);
        Test pregunta=lTest.get(indice);

        group.getChildAt(pregunta.getSolucion()).setBackgroundColor(Color.GREEN);
        //Toast.makeText(getApplicationContext(),"Has dicho algo",Toast.LENGTH_SHORT);
        if(selectednum != pregunta.getSolucion())
        {
            findViewById(group.getCheckedRadioButtonId()).setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(),R.string.toast_fail, Toast.LENGTH_SHORT).show();

        }else
        {
            Toast.makeText(getApplicationContext(),R.string.toast_success,Toast.LENGTH_SHORT).show();
            aciertos++;
        }
        findViewById(R.id.test_button_correct).setVisibility(View.INVISIBLE);
        findViewById(R.id.test_button_next).setVisibility(View.VISIBLE);
    }

    protected void getData()
    {

        Test test=lTest.get(indice);
        TextView textWording=(TextView)findViewById(R.id.test_enunciado);
        textWording.setText(test.getEnunciado());
        textWording.setVisibility(View.VISIBLE);
        RadioGroup group= (RadioGroup)findViewById(R.id.test_choices);
        RadioButton radio= new RadioButton(this);
        radio.setText(test.getOpcion_1());
        radio.setOnClickListener(this);
        group.addView(radio);
        RadioButton radio2= new RadioButton(this);
        radio2.setText(test.getOpcion_2());
        radio2.setOnClickListener(this);
        group.addView(radio2);
        RadioButton radio3= new RadioButton(this);
        radio3.setText(test.getOpcion_3());
        radio3.setOnClickListener(this);
        group.addView(radio3);


    }
    public void next(View view)
    {

        if(indice==size-1)
        {
            Toast.makeText(this,"Ariketa bukatuta "+aciertos+" erantzun zuzena izan duzu",Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            indice++;
        }

        RadioGroup group= (RadioGroup)findViewById(R.id.test_choices);
        group.removeAllViews();
        getData();
        findViewById(R.id.test_button_next).setVisibility(View.INVISIBLE);
        if(indice==size-1)
        {
            Button bt=(Button)findViewById(R.id.test_button_next);
            bt.setText("Bukatu test");
        }

    }
    @Override
    public void onClick(View v)
    {

        findViewById(R.id.test_button_correct).setVisibility(View.VISIBLE);
    }
}
