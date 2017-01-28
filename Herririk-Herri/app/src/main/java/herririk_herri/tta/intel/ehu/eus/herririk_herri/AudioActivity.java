package herririk_herri.tta.intel.ehu.eus.herririk_herri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import herririk_herri.tta.intel.ehu.eus.herririk_herri.View.AudioPlayer;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.Exercise;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.ListExercise;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.User;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.coms.ProgressTask;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.coms.RestClient;

public class AudioActivity extends AppCompatActivity implements View.OnClickListener {
    private int indice=0;
    private int size=2;
    final String URL=null;
    private List<Exercise> lExercise;
    int aciertos;
    private String audioURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        this.setTitle(R.string.Audio_title);
    }

    public void load(View view)
    {
        final String url=getString(R.string.url_server);
        SharedPreferences myprefs=getSharedPreferences("herri",MODE_WORLD_READABLE);
        final String herri=myprefs.getString("herri",null);
        final String arikmota=myprefs.getString("ariketa",null);
        //llamada a logica para solicitar los datos, petición al servidor
        View element=findViewById(R.id.audio_button_init);
        element.setVisibility(View.GONE);
        View elementplay=findViewById(R.id.audio_button_play);
        elementplay.setVisibility(View.VISIBLE);
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
                //Toast.makeText(context,"Exito",Toast.LENGTH_SHORT).show();
                TextView textWording=(TextView)findViewById(R.id.audio_code);
                textWording.setText(R.string.audio_ark_code+result.lExercise.get(0).getAriketaKode());
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
        TextView textWording=(TextView)findViewById(R.id.audio_enunciado);
        textWording.setText(test.getEnunciado());
        textWording.setVisibility(View.VISIBLE);
        audioURL=test.getAudio();
        RadioGroup group= (RadioGroup)findViewById(R.id.audio_choices);
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

    protected void zuzendu(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.audio_choices);
        int choices = group.getChildCount();
        for (int i = 0; i < choices; i++)
            group.getChildAt(i).setEnabled(false);
        View selected = group.findViewById(group.getCheckedRadioButtonId());
        final int selectednum=group.indexOfChild(selected);
        Exercise pregunta=lExercise.get(indice);

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
        findViewById(R.id.audio_button_correct).setVisibility(View.INVISIBLE);
        findViewById(R.id.audio_button_next).setVisibility(View.VISIBLE);
    }

    public void play(View view)
    {
        final String audio=audioURL;
        final Thread thread = new Thread(new Runnable() {
            public void run() {
            }});
        AudioPlayer audioPlay = new AudioPlayer(findViewById(R.id.audio_layout),thread);
        try {
            audioPlay.setAudioUri(Uri.parse(audioURL));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void next(View view)
    {

        if(indice==size-1)
        {
            SuperaEjercicio();
            //Toast.makeText(this,"Ariketa bukatuta "+aciertos+" erantzun zuzena izan duzu",Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(this,SelAriketaActivity.class);
            startActivity(intent);
        }
        else
        {
            indice++;
        }

        RadioGroup group= (RadioGroup)findViewById(R.id.audio_choices);
        group.removeAllViews();
        getData();
        findViewById(R.id.audio_button_next).setVisibility(View.INVISIBLE);
        if(indice==size-1)
        {
            Button bt=(Button)findViewById(R.id.audio_button_next);
            bt.setText("Bukatu test");
        }

    }
    protected void SuperaEjercicio()
    {
        final String path=getString(R.string.url_server);
        if(aciertos>=size/2)
        {
            SharedPreferences myprefs=getSharedPreferences("user",MODE_WORLD_READABLE);
            final String user=myprefs.getString("user",null);
            final int testVis=myprefs.getInt("unlockTest",0);
            int ark2=myprefs.getInt("unlockariketa2",0);
            final int ark3Unblocked=myprefs.getInt("unlockariketa3",0);
            if(ark2==0)
            {
                final int ark2Unblocked=1;
                myprefs.edit().putInt("unlockariketa2",ark2Unblocked).commit();
                myprefs.edit().commit();
                new ProgressTask<User>(this)
                {

                    @Override
                    protected User work() throws Exception {
                        User result=new User();
                        RestClient rest = new RestClient(path);//se genera
                        //rest.setProperty();
                        JSONObject jsonObject=new JSONObject();
                        jsonObject.put("lastName"," ");
                        jsonObject.put("login",user);
                        jsonObject.put("name"," ");
                        jsonObject.put("password"," ");
                        jsonObject.put("testVis",testVis);
                        jsonObject.put("ark3Unblocked",ark2Unblocked);
                        jsonObject.put("ark2Unblocked",ark3Unblocked);
                        Log.e("JSON enviado:",jsonObject.toString());
                        result.setTestVis(rest.postJson(jsonObject,"updateUser"));//Chapuza para poder pasar el response code sin tener que modificar progressTask
                        return result;
                    }

                    @Override
                    protected void onFinish(User result) {
                        if(result.getTestVis()==200)
                        {
                            Toast.makeText(context,"Has desbloqueado el ejercicio siguiente",Toast.LENGTH_SHORT).show();
                        }
                        //Toast.makeText(context,"Bienvenido "+result.getUser(),Toast.LENGTH_SHORT);


                    }
                }.execute();
            }

        }

    }
    @Override
    public void onClick(View v)
    {

        findViewById(R.id.audio_button_correct).setVisibility(View.VISIBLE);
    }
}
