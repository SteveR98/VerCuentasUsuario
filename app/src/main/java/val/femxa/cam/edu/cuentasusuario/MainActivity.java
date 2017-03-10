package val.femxa.cam.edu.cuentasusuario;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{
    private void obtenerCuentas()
    {

        AccountManager accountManager=(AccountManager) getSystemService(ACCOUNT_SERVICE);
        Account[] arrayCuentas=accountManager.getAccounts();

        for (Account cuenta: arrayCuentas){
            Log.d("MENSAJE","TIPO: "+cuenta.type);
           Log.d("MENSAJE","CUENTA: "+cuenta.name);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {

        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            Log.d("MENSJE","El usuario a dado permisos");
            obtenerCuentas ();
        }
        else {
            Log.d("MENSAJE","El usuario no ha dado permisos");
            finish();
        }

    }

    private void pedirPermisos()
    {

       /*int permiso= ContextCompat.checkSelfPermission(this,Manifest.permission.GET_ACCOUNTS);

        if (permiso== PackageManager.PERMISSION_GRANTED)
        {
            Log.d("MENSAJE","Permiso  Ya concedido");
        }*/

        ActivityCompat.requestPermissions(this,new String [] {Manifest.permission.GET_ACCOUNTS},69);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pedirPermisos();






    }
}
