package herririk_herri.tta.intel.ehu.eus.herririk_herri.model.coms;

import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mikel on 18/01/17.
 */
public class RestClient {
    private final static String AUTH="Authorization";
    private final String baseUrl;
    private final Map<String,String> properties =new HashMap<>();

    public RestClient(String baseUrl)
    {
        this.baseUrl=baseUrl;
    }
    public String getAuthorization()
    {
        return properties.get(AUTH);

    }
    public void setAuthorization(String auth)
    {
        this.properties.put(AUTH,auth);
    }
    public void setProperty(String name,String value)
    {
        properties.put(name,value);
    }
    private HttpURLConnection getConnection(String path) throws IOException
    {
        URL url=new URL(String.format("%s/%s",baseUrl,path));
        HttpURLConnection conn=(HttpURLConnection)url.openConnection();
        for(Map.Entry<String,String> property :properties.entrySet())
        {
            conn.setRequestProperty(property.getKey(),property.getValue());
        }
        conn.setUseCaches(false);//No mantener caches
        //conn.setRequestProperty("Connection","Keep-Alive");
        return conn;

    }

    public String getString(String path) throws IOException
    {
        HttpURLConnection conn=null;
        try {
            conn = getConnection(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            return br.readLine();

        } finally {
            if(conn!=null)
            {
                conn.disconnect();
            }
        }

    }

    public int postJson(final JSONObject json, String path) throws IOException{
        HttpURLConnection conn=null;
        try{
            conn=getConnection(path);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            PrintWriter pw = new PrintWriter(conn.getOutputStream());
            pw.print(json.toString());
            pw.close();
            return conn.getResponseCode();

        }finally {
            if(conn!=null)
            {
                conn.disconnect();
            }
        }
    }

    public String addUser(final JSONObject json, String path) throws IOException{
        HttpURLConnection conn=null;
        try{
            conn=getConnection(path);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            PrintWriter pw = new PrintWriter(conn.getOutputStream());
            pw.print(json.toString());
            pw.close();
            int rspcode=conn.getResponseCode();
            if(conn.getResponseCode()==200)
            {
                return conn.getResponseMessage();
            }
            else
            {
                return "Error al enviar el mensaje";
            }


        }finally {
            if(conn!=null)
            {
                conn.disconnect();
            }
        }
    }
    /*public JSONObject getJson(String path) throws IOException, Exception{
        JSONObject jsonob=new JSONObject();
        HttpURLConnection conn=null;
        String line;
        try{
            conn=getConnection(path);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            PrintWriter pw = new PrintWriter(conn.getOutputStream());
            if(200==conn.getResponseCode())
            {
                BufferedReader rd=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                line=rd.readLine();
                try
                {
                    jsonob=new JSONObject(line);
                }catch(Exception e){


            }


            }
            else
            {
                throw new Exception("Error /"+conn.getResponseCode());
            }
            return jsonob;

        }finally {
            if(conn!=null)
            {
                conn.disconnect();
            }
        }
    }*/
    public JSONObject getJson(String path) throws IOException,JSONException
    {
        return new JSONObject(getString(path));
    }

    public void setHttpBasicAuth(String user, String passwd)
    {
        String basicAuth = Base64.encodeToString(String.format("%s:%s",user,passwd).getBytes(),Base64.DEFAULT);
        properties.put(AUTH,String.format("Basic %s",basicAuth));

    }
}
