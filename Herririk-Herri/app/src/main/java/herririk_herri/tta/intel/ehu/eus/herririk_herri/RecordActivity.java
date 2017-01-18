package herririk_herri.tta.intel.ehu.eus.herririk_herri;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.Exercise;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.Kultura;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.ListExercise;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.coms.ProgressTask;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.coms.RestClient;

public class RecordActivity extends AppCompatActivity {
    private int indice=0;
    private int size=2;
    final String URL=null;
    private List<Exercise> lExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        this.setTitle(R.string.Record_title);
    }

    public void load(View view)
    {
        final String url=getString(R.string.url_server);
        SharedPreferences myprefs=getSharedPreferences("herri",MODE_WORLD_READABLE);
        final String herri=myprefs.getString("herri",null);
        final String arikmota=myprefs.getString("ariketa",null);
        //llamada a logica para solicitar los datos, petición al servidor
        View element=findViewById(R.id.record_button_init);
        element.setVisibility(View.GONE);
        final int indice=0;
        new ProgressTask<ListExercise>(this)
        {

            @Override
            protected ListExercise work() throws Exception {
                RestClient rest= new RestClient(url);
                ListExercise ejercicios=new ListExercise();
                JSONObject jsonObject = rest.getJson(String.format("requestAriketa?herri=%s&ariketamota=%s",herri,arikmota ));
                JSONArray lista=jsonObject.getJSONArray("exercises");

                for(int i =0;i<lista.length();i++){
                    JSONObject temp=lista.getJSONObject(i);


                    Exercise objeto=new Exercise(temp.getString("ariketaCode"),temp.getString("audio"),temp.getString("enunciado"),temp.getString("img"),temp.getString("opcion_1"),temp.getString("opcion_2"),temp.getString("opcion_3"),temp.getInt("solucion"),temp.getString("tipo"));

                   ejercicios.addChoice(objeto);




                }
                ejercicios.setLessonCode(jsonObject.getString("lessonCode"));
                ejercicios.setSize(jsonObject.getInt("total"));






                return ejercicios;
            }

            @Override
            protected void onFinish(ListExercise result) {
                /*img.setImageBitmap(result);
                img.setVisibility(View.VISIBLE);
                Toast.makeText(context,"Aqui he llegado",Toast.LENGTH_SHORT).show();*/
                lExercise=result.lExercise;
                size=result.getSize();
                Toast.makeText(context,"Exito",Toast.LENGTH_SHORT).show();



                //ChangeView(text,title);

                //img.setVisibility(View.VISIBLE);




            }
        }.execute();

    }


}