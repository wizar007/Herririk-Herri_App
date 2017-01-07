package herririk_herri.tta.intel.ehu.eus.herririk_herri;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class KulturActivity extends AppCompatActivity {
    int indice=0;
    int size=2;
    final String URL=null;

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

        //llamada a logica para solicitar los datos, petición al servidor
        indice=0;
        final String imageUrl="https://dl.dropboxusercontent.com/s/csw2mjy6kau1twu/12.%20Turismo%20bulegoa.jpg?dl=0";
        String text="Oficina de turismo de algun Lugar";
        View element=findViewById(R.id.kultur_button_init);
        element.setVisibility(View.GONE);
        ImageView img=(ImageView)findViewById(R.id.kultur_img);
        /*Bitmap bitmap=null;
        bitmap=DownloadResources.downloadImg(imageUrl);
        img.setImageBitmap(bitmap);*/
        // Loader image - will be shown before loading image
        int loader = R.drawable.icon_prueba;
        // Imageview to show
        ImageView image = (ImageView) findViewById(R.id.image);
        ImageLoader imgLoader=new ImageLoader(getApplicationContext());
        imgLoader.DisplayImage(imageUrl, loader, image);
        img.setVisibility(View.VISIBLE);
        TextView texto=(TextView)findViewById(R.id.kultur_text);
        texto.setText(text);
        texto.setVisibility(View.VISIBLE);
        element=findViewById(R.id.kultur_button_back);
        element.setVisibility(View.GONE);
        element=findViewById(R.id.kultur_button_next);
        element.setVisibility(View.VISIBLE);

    }

    public void next(View view)
    {

        if(indice==size)
        {
            indice=0;
        }
        else
        {
            indice++;
        }
        //llamada a logica para solicitar los datos, petición al servidor
        String imageUrl="https://dl.dropboxusercontent.com/s/csw2mjy6kau1twu/12.%20Turismo%20bulegoa.jpg?dl=0";//cargar imagen de lista
        String text="Oficina de turismo de algun Lugar";//cargar texto de la lista
        View element=null;/*
        ImageView img=(ImageView)findViewById(R.id.kultur_img);
        try {
            ImageView i = (ImageView)findViewById(R.id.image);
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageUrl).getContent());//carga la imagen de internet
            i.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        img.setVisibility(View.VISIBLE);*/
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
        String imageUrl="https://dl.dropboxusercontent.com/s/csw2mjy6kau1twu/12.%20Turismo%20bulegoa.jpg?dl=0";//cargar imagen de lista
        String text="Oficina de turismo de algun Lugar";//cargar texto de la lista

        /*ImageView img=(ImageView)findViewById(R.id.kultur_img);
        try {
            ImageView i = (ImageView)findViewById(R.id.image);
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageUrl).getContent());//carga la imagen de internet
            i.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        img.setVisibility(View.VISIBLE);*/
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
