package herririk_herri.tta.intel.ehu.eus.herririk_herri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.Exercise;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.ListExercise;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.Test;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.User;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.coms.ProgressTask;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.coms.RestClient;

public class ImgActivity extends AppCompatActivity implements View.OnClickListener{
    private int indice=0;
    private int size=2;
    final String URL=null;
    private List<Exercise> lExercise;
    int aciertos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);
        this.setTitle(R.string.Img_title);
    }

    public void load(View view)
    {
        final String url=getString(R.string.url_server);
        SharedPreferences myprefs=getSharedPreferences("herri",MODE_WORLD_READABLE);
        final String herri=myprefs.getString("herri",null);
        final String arikmota=myprefs.getString("ariketa",null);
        //llamada a logica para solicitar los datos, petición al servidor
        View element=findViewById(R.id.img_button_init);
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
                //Toast.makeText(context,"Exito",Toast.LENGTH_SHORT).show();
                TextView textWording=(TextView)findViewById(R.id.img_code);
                textWording.setText(R.string.Img_ark_code+result.lExercise.get(0).getAriketaKode());
                textWording.setVisibility(View.VISIBLE);
                getData();
                DownloadImage(result.lExercise.get(0).getImg());




                //ChangeView(text,title);

                //img.setVisibility(View.VISIBLE);




            }
        }.execute();

    }
    protected void loadImage(Bitmap bm)
    {
        final ImageView img=(ImageView)findViewById(R.id.img_img);
        img.setImageBitmap(bm);
        img.setVisibility(View.VISIBLE);
        //Toast.makeText(context,"Aqui he llegado",Toast.LENGTH_SHORT).show();
    }
    protected void DownloadImage(String path)
    {
        final String imageUrl=path;
        new ProgressTask<Bitmap>(this)
        {

            @Override
            protected Bitmap work() throws Exception {
                Bitmap bm=null;
                bm= BitmapFactory.decodeStream((InputStream)new URL(imageUrl).getContent());


                return bm;
            }

            @Override
            protected void onFinish(Bitmap result) {
                loadImage(result);
                /*img.setImageBitmap(result);
                img.setVisibility(View.VISIBLE);
                Toast.makeText(context,"Aqui he llegado",Toast.LENGTH_SHORT).show();*/

            }
        }.execute();
    }


    protected void getData()
    {

        Exercise test=lExercise.get(indice);
        TextView textWording=(TextView)findViewById(R.id.img_enunciado);
        textWording.setText(test.getEnunciado());
        textWording.setVisibility(View.VISIBLE);
        DownloadImage(test.getImg());
        RadioGroup group= (RadioGroup)findViewById(R.id.img_choices);
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
        RadioGroup group = (RadioGroup) findViewById(R.id.img_choices);
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
        findViewById(R.id.img_button_correct).setVisibility(View.VISIBLE);
        findViewById(R.id.img_button_next).setVisibility(View.VISIBLE);
    }
    protected void SuperaEjercicio()
    {
        final String path=getString(R.string.url_server);
        if(aciertos>=size/2)
        {
            SharedPreferences myprefs=getSharedPreferences("user",MODE_WORLD_READABLE);
            final String user=myprefs.getString("user",null);
            final int testVis=myprefs.getInt("unlockTest",0);
            final int ark2Unblocked=myprefs.getInt("unlockariketa2",0);
            int ark3=myprefs.getInt("unlockariketa3",0);
            if(ark3==0)
            {
                final int ark3Unblocked=1;
                myprefs.edit().putInt("unlockariketa3",ark3Unblocked).commit();
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
                            load_prefs();
                        }
                        //Toast.makeText(context,"Bienvenido "+result.getUser(),Toast.LENGTH_SHORT);


                    }
                }.execute();
            }

        }

    }
    protected void load_prefs()
    {

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
        /*//llamada a logica para solicitar los datos, petición al servidor
        String imageUrl=lkultura.get(indice).getImg();//"https://dl.dropboxusercontent.com/s/csw2mjy6kau1twu/12.%20Turismo%20bulegoa.jpg?dl=0";//cargar imagen de lista
        String text=lkultura.get(indice).getInformacion();//cargar texto de la lista
        DownloadImage(imageUrl);
        //View element=null;
        View element=findViewById(R.id.kultur_button_init);
        element.setVisibility(View.GONE);
        TextView texto=(TextView)findViewById(R.id.kultur_text);
        texto.setText(text);
        texto.setVisibility(View.VISIBLE);
        element=findViewById(R.id.kultur_button_back);
        if(indice==0){
            element.setVisibility(View.GONE);
        }
        else
        {
            element.setVisibility(View.VISIBLE);
        }
        element=findViewById(R.id.kultur_button_next);
        element.setVisibility(View.VISIBLE);

    */
        RadioGroup group= (RadioGroup)findViewById(R.id.img_choices);
        group.removeAllViews();
        getData();
        findViewById(R.id.img_button_next).setVisibility(View.INVISIBLE);
        if(indice==size-1)
        {
            Button bt=(Button)findViewById(R.id.img_button_next);
            bt.setText("Bukatu test");
        }

    }
    @Override
    public void onClick(View v)
    {

        findViewById(R.id.img_button_correct).setVisibility(View.VISIBLE);
    }
}
