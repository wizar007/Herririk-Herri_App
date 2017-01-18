package herririk_herri.tta.intel.ehu.eus.herririk_herri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.User;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.coms.ProgressTask;
import herririk_herri.tta.intel.ehu.eus.herririk_herri.model.coms.RestClient;

public class MainActivity extends AppCompatActivity {
    private User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void changeview(View view)
    {
        findViewById(R.id.login_layout).setVisibility(View.GONE);
        findViewById(R.id.register_layout).setVisibility(View.VISIBLE);
        findViewById(R.id.button_login).setVisibility(View.GONE);
        findViewById(R.id.button_register).setVisibility(View.GONE);
        findViewById(R.id.button_register_send).setVisibility(View.VISIBLE);
    }
    public void login(View view)
    {
        final Intent intent=new Intent(this,MenuActivity.class);
        final String login = ((EditText)findViewById(R.id.login_username)).getText().toString();
        final String passwd = ((EditText)findViewById(R.id.login_password)).getText().toString();
        final String path=getString(R.string.url_server);
        new ProgressTask<User>(this)
        {

            @Override
            protected User work() throws Exception {
                User user=null;
                RestClient rest = new RestClient(path);//se genera
                //rest.setProperty();
                JSONObject jsonObject = rest.getJson(String.format("login?login=%s&password=%s", login,passwd));
                user = new User(jsonObject.getString("login"), jsonObject.getString("password"), jsonObject.getString("name"), jsonObject.getString("lastName"), jsonObject.getInt("ark2Unblocked"), jsonObject.getInt("ark3Unblocked"),jsonObject.getInt("testVis"));

                return user;
            }

            @Override
            protected void onFinish(User result) {
                usuario=result;
                //Toast.makeText(context,"Bienvenido "+result.getUser(),Toast.LENGTH_SHORT);

                if(usuario.getLogin().equals("Invalido"))
                {
                   Toast.makeText(context,"Erabiltzailea edo pasahitza ez dira zuzenak",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Erabiltzailea zuzena da",Toast.LENGTH_SHORT).show();
                    intent.putExtra(MenuActivity.EXTRA_LOGIN, login);
                    saveUserData();



                    startActivity(intent);
                }




            }
        }.execute();

    }
    protected void saveUserData()
    {
        SharedPreferences myprefs= this.getSharedPreferences("user", MODE_WORLD_READABLE);
        myprefs.edit().putString("user",usuario.getLogin()).commit();
        myprefs.edit().putInt("unlockTest",usuario.getTestVis()).commit();
        myprefs.edit().putInt("unlockariketa2",usuario.getArk2Unblocked()).commit();
        myprefs.edit().putInt("unlockariketa3",usuario.getArk3Unblocked()).commit();
        myprefs.edit().commit();
    }

    /*protected boolean authenticate(String login, String passwd)
    {
        boolean validez=false;
        String userTest="prueba";
        String passTest="1234";

        if(login.equals(userTest) && passwd.equals(passTest))
        {
            validez=true;
            Toast.makeText(getApplicationContext(),"Erabiltzailea zuzena da",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Erabiltzailea edo pasahitza ez dira zuzenak",Toast.LENGTH_SHORT).show();
        }

        return validez;
    }*/
    public void register(View view)
    {
        //Intent intent=new Intent(this,MenuActivity.class);
        String login = ((EditText)findViewById(R.id.register_username)).getText().toString();
        String passwd = ((EditText)findViewById(R.id.register_password)).getText().toString();
        String passwdconf = ((EditText)findViewById(R.id.register_password_conf)).getText().toString();
        final String name = ((EditText)findViewById(R.id.register_name)).getText().toString();
        String lastname = ((EditText)findViewById(R.id.register_name)).getText().toString();
        if((login!=null&&login.equals("")!=true)&&(passwd!=null&&passwd.equals("")!=true)&&(passwd.equals(passwdconf)))
        {
            Toast.makeText(getApplicationContext(),"Datos con formato correcto",Toast.LENGTH_SHORT).show();
            Log.d("CompruebaRegister","Datos formulario registro correctos");

            findViewById(R.id.login_layout).setVisibility(View.VISIBLE);
            findViewById(R.id.register_layout).setVisibility(View.GONE);
            findViewById(R.id.button_login).setVisibility(View.VISIBLE);
            findViewById(R.id.button_register).setVisibility(View.VISIBLE);
            findViewById(R.id.button_register_send).setVisibility(View.GONE);
            final User user=new User(login,passwd,name,lastname,0,0,0);
            final String path=getString(R.string.url_server);
            new ProgressTask<User>(this)
            {

                @Override
                protected User work() throws Exception {
                    User test=new User();
                    RestClient rest = new RestClient(path);//se genera
                    //rest.setProperty();
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("lastName",user.getLastName());
                    jsonObject.put("login",user.getLogin());
                    jsonObject.put("name",user.getName());
                    jsonObject.put("password",user.getPassword());
                    jsonObject.put("testVis",user.getTestVis());
                    jsonObject.put("ark3Unblocked",user.getArk3Unblocked());
                    jsonObject.put("ark2Unblocked",user.getArk2Unblocked());
                    test.setLogin(rest.addUser(jsonObject,"/addUser"));
                    return test;
                }

                @Override
                protected void onFinish(User result) {
                    usuario=result;
                    //Toast.makeText(context,"Bienvenido "+result.getUser(),Toast.LENGTH_SHORT);

                    Toast.makeText(context,result.getLogin(),Toast.LENGTH_SHORT).show();




                }
            }.execute();


        }
        else
        {
            Toast.makeText(getApplicationContext(),"Bete ezazu hutxune guztiak edo pasahitzak ezberdinak dira",Toast.LENGTH_SHORT).show();
            Log.d("CompruebaRegister","Datos formulario registro incorrectos");

        }
    }

}
