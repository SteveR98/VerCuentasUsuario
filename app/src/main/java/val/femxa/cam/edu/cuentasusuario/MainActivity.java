package val.femxa.cam.edu.cuentasusuario;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
{
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
       Log.d("MENSAJE","El usuaio le ha dado a salir");

        AlertDialog alertDialog= new AlertDialog.Builder(this).create();

        alertDialog.setTitle("SALIR");
        alertDialog.setMessage("¿De verdad quieres salir?");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("MENSAJE","El usuaio confirma la salida");
                finish();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("MENSAJE","El usuaio confirma no quiere salir");
                dialog.cancel();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "NO SE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("MENSAJE","El usuaio no sabe");
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        menu.add(Menu.NONE,1,1,"SALIR");
        menu.add(Menu.NONE,2,2,"FEMXA");



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.d("MENSAJE","Item ID: "+item.getItemId());

        if (item.getItemId()==1)
        {
            this.finish();
        }

        else

        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://femxa-ebtm.rhcloud.com/html/home.html"));
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}
