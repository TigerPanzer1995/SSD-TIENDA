package com.example.ssd_tienda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import Base_de_datos.MainActivity;

public class Atencion_Cliente extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private EditText msj;
    private String num_tienda = "522941323090";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atencion__cliente);
        drawerLayout = findViewById(R.id.drawer_layout);
        msj = findViewById(R.id.tv_msj);

    }
    public void ClickMenu(View view){
        HomeActivity.abrirDrawer(drawerLayout);
    }
    public void ClickLogo(View view){
        HomeActivity.cerrarDrawer(drawerLayout);
    }
    public void ClickHome(View view){
        HomeActivity.redireccionActivity(this,HomeActivity.class);
    }
    public void ClickSSD(View view){
        HomeActivity.redireccionActivity(this,SSD.class);
    }
    public void ClickAtencion(View view){
        recreate();
    }
    public void ClickSalir(View view){
        logout(this);
    }
    public void logout(final Activity activity) {
        //inicializar alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Cerrar Sesión");
        builder.setMessage("¿Está seguro de querer salir?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AuthUI.getInstance().signOut(activity)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                //destruye la actividad
                                finish();
                                Toast.makeText(Atencion_Cliente.this,"Cerrando sesión",Toast.LENGTH_SHORT).show();
                                //se vaya a l login
                                vamosallogin();


                            }
                        });

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        builder.show();
    }
    private void vamosallogin() {
        Intent i= new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

    }

    public void ClickMsj (View view){
        if (msj.getText().toString().isEmpty()){
            Toast.makeText(this, "Debes agregar tu duda o consulta", Toast.LENGTH_SHORT).show();

        }else{
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_VIEW);
            String uri= "whatsapp://send?phone="+num_tienda+"&text="+msj.getText().toString();
            sendIntent.setData(Uri.parse(uri));
            startActivity(sendIntent);
        }


    }




}