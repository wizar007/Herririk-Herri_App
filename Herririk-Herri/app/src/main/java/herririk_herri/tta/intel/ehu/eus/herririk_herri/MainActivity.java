package herririk_herri.tta.intel.ehu.eus.herririk_herri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
        Intent intent=new Intent(this,MenuActivity.class);
        String login = ((EditText)findViewById(R.id.login_username)).getText().toString();
        String passwd = ((EditText)findViewById(R.id.login_password)).getText().toString();
        if(authenticate(login,passwd)) {
            intent.putExtra(MenuActivity.EXTRA_LOGIN, login);
            SharedPreferences myprefs= this.getSharedPreferences("user", MODE_WORLD_READABLE);
            myprefs.edit().putString("user",login).commit();
            startActivity(intent);

        }
    }
    protected boolean authenticate(String login, String passwd)
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
    }
    public void register(View view)
    {
        //Intent intent=new Intent(this,MenuActivity.class);
        String login = ((EditText)findViewById(R.id.register_username)).getText().toString();
        String passwd = ((EditText)findViewById(R.id.register_password)).getText().toString();
        String passwdconf = ((EditText)findViewById(R.id.register_password_conf)).getText().toString();
        String name = ((EditText)findViewById(R.id.register_name)).getText().toString();
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


        }
        else
        {
            Toast.makeText(getApplicationContext(),"Bete ezazu hutxune guztiak edo pasahitzak ezberdinak dira",Toast.LENGTH_SHORT).show();
            Log.d("CompruebaRegister","Datos formulario registro incorrectos");

        }
    }

}
