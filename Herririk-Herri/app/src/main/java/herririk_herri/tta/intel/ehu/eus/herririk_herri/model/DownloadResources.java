package herririk_herri.tta.intel.ehu.eus.herririk_herri.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mikel on 7/01/17.
 */
public class  DownloadResources /*extends AsyncTask<Integer, Void, Bitmap>*/ {


    protected static Bitmap doInBackground(String... urls) {

        final Bitmap[] bitmap=null;
        final String URL=urls[0];

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    bitmap[0]= BitmapFactory.decodeStream((InputStream)new URL(URL).getContent());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        t.start();
        Bitmap bm=bitmap[0];

        return bm;//falla aqui devuelve un null
    }
/*
    public static Bitmap downloadImg(String imageUrl)
    {
        final Bitmap[] bitmap=null;
        final String URL=imageUrl;

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    bitmap[0]= BitmapFactory.decodeStream((InputStream)new URL(URL).getContent());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        t.start();
        Bitmap bm=bitmap[0];

        return bm;
    }*/
}
