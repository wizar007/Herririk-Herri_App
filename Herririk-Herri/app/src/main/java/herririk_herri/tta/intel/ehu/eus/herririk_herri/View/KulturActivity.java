package herririk_herri.tta.intel.ehu.eus.herririk_herri.View;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import herririk_herri.tta.intel.ehu.eus.herririk_herri.R;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.Kultura;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.ListKultura;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.coms.ProgressTask;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.coms.RestClient;

public class KulturActivity extends AppCompatActivity {
    private int indice=0;
    private int size=2;
    final String URL=null;
    private List<Kultura> lkultura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kultur);
        SharedPreferences myprefs=getSharedPreferences("herri",MODE_WORLD_READABLE);
        String herri=myprefs.getString("herri",null);
        String kultur=myprefs.getString("kultur",null);
        this.setTitle("Ezagutu "+herri+"ko "+kultur);
    }

    public void load(View view)
    {
        final String url=getString(R.string.url_server);
        SharedPreferences myprefs=getSharedPreferences("herri",MODE_WORLD_READABLE);
        final String herri=myprefs.getString("herri",null);
        final String kultur=myprefs.getString("kultur",null);
        //llamada a logica para solicitar los datos, petición al servidor
        View element=findViewById(R.id.kultur_button_init);
        element.setVisibility(View.GONE);
        final int indice=0;
        new ProgressTask<ListKultura>(this)
        {

            @Override
            protected ListKultura work() throws Exception {
                RestClient rest= new RestClient(url);
                ListKultura kulturas=new ListKultura();
                JSONObject jsonObject = rest.getJson(String.format("requestKultura?herri=%s&kulturamota=%s",herri,kultur ));
                JSONArray lista=jsonObject.getJSONArray("kultura");

                for(int i =0;i<lista.length();i++){
                    JSONObject temp=lista.getJSONObject(i);


                    Kultura objeto=new Kultura(temp.getString("img"),temp.getString("informacion"),temp.getString("kulturaType"));

                    kulturas.addChoice(objeto);




                }

                kulturas.setKulturaCode(jsonObject.getString("kulturaCode"));
                kulturas.setSize(jsonObject.getInt("total"));






                return kulturas;
            }

            @Override
            protected void onFinish(ListKultura result) {
                /*img.setImageBitmap(result);
                img.setVisibility(View.VISIBLE);
                Toast.makeText(context,"Aqui he llegado",Toast.LENGTH_SHORT).show();*/
                lkultura=result.lkultura;
                size=result.getSize();
                String imageUrl=lkultura.get(0).getImg();//"https://dl.dropboxusercontent.com/s/csw2mjy6kau1twu/12.%20Turismo%20bulegoa.jpg?dl=0";
                String text=lkultura.get(0).getInformacion();
                String title=result.getKulturaCode();


                DownloadImage(imageUrl);
                ChangeView(text,title);

                //img.setVisibility(View.VISIBLE);




            }
        }.execute();


    }
    protected void ChangeView(String text,String title)
    {
        this.setTitle(title);
        View element=null;
        TextView texto=(TextView)findViewById(R.id.kultur_text);
        texto.setText(text);
        texto.setVisibility(View.VISIBLE);
        element=findViewById(R.id.kultur_button_back);
        element.setVisibility(View.GONE);
        element=findViewById(R.id.kultur_button_next);
        element.setVisibility(View.VISIBLE);
    }
    protected void loadImage(Bitmap bm)
    {
        final ImageView img=(ImageView)findViewById(R.id.kultur_img);
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

    public void next(View view)
    {

        if(indice==size-1)
        {
            indice=0;
        }
        else
        {
            indice++;
        }
        //llamada a logica para solicitar los datos, petición al servidor
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

    }
    public void back(View view)
    {

        if(indice==0)
        {
            indice=size-1;
        }
        else
        {
            indice--;
        }
        //llamada a logica para solicitar los datos, petición al servidor
        String imageUrl=lkultura.get(indice).getImg();//"https://dl.dropboxusercontent.com/s/csw2mjy6kau1twu/12.%20Turismo%20bulegoa.jpg?dl=0";//cargar imagen de lista
        String text=lkultura.get(indice).getInformacion();//cargar texto de la lista
        DownloadImage(imageUrl);

        TextView texto=(TextView)findViewById(R.id.kultur_text);
        texto.setText(text);
        texto.setVisibility(View.VISIBLE);
        View element=null;
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

    }
}
