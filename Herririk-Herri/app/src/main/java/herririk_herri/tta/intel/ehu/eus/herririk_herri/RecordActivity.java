package herririk_herri.tta.intel.ehu.eus.herririk_herri;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

import herririk_herri.tta.intel.ehu.eus.herririk_herri.View.AudioPlayer;
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
    int aciertos;
    private String solaudioURL;
    private String record;
    public final static int AUDIO_REQUEST_CODE=3;

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
                TextView textWording=(TextView)findViewById(R.id.record_code);
                textWording.setText(getString(R.string.Record_ark_code)+": "+result.lExercise.get(0).getAriketaKode());
                textWording.setVisibility(View.VISIBLE);
                getData();



                //ChangeView(text,title);

                //img.setVisibility(View.VISIBLE);




            }
        }.execute();

    }
    protected void getData()
    {

        Exercise test=lExercise.get(indice);
        TextView textWording=(TextView)findViewById(R.id.record_enunciado);
        textWording.setText(test.getEnunciado());
        textWording.setVisibility(View.VISIBLE);
        solaudioURL=test.getAudio();
        findViewById(R.id.record_button_record).setVisibility(View.VISIBLE);




    }

    public void recordAudio(View view)
    {
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE))
        {
            Toast.makeText(this, R.string.no_micro, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent=new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if(intent.resolveActivity(getPackageManager())!=null) {
                startActivityForResult(intent, AUDIO_REQUEST_CODE);
                findViewById(R.id.record_button_correct).setVisibility(View.VISIBLE);
            }
            else
                Toast.makeText(this,R.string.no_app,Toast.LENGTH_SHORT).show();
        }

    }
    protected void zuzendu(View view) {

        findViewById(R.id.record_button_correct).setVisibility(View.INVISIBLE);
        findViewById(R.id.record_button_solucion).setVisibility(View.VISIBLE);
        findViewById(R.id.record_button_next).setVisibility(View.VISIBLE);
    }

    public void play(View view)
    {
        String temp;
        if(view.getId()==R.id.record_button_solucion) {
            temp = solaudioURL;
        }
        else
        {
            temp = record;
        }
        final String audio = temp;
        final Thread thread = new Thread(new Runnable() {
            public void run() {
            }});
        AudioPlayer audioPlay = new AudioPlayer(findViewById(R.id.record_layout),thread);
        try {
            audioPlay.setAudioUri(Uri.parse(audio));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        if(resultCode != Activity.RESULT_OK)
            return;
        if(requestCode==AUDIO_REQUEST_CODE)
        {
            Toast.makeText(this,"Grabado con exito",Toast.LENGTH_SHORT).show();
            Uri urirecord=data.getData();
            record=urirecord.toString();
            findViewById(R.id.record_button_play).setVisibility(View.VISIBLE);

        }
    }


}