package com.example.ssd_tienda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Base_de_datos.MainActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    //inicializar variables
    DrawerLayout drawerLayout;
    private Button btn_catalogo;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        drawerLayout = findViewById(R.id.drawer_layout);
        btn_catalogo = findViewById(R.id.btnCata);
        btn_catalogo.setOnClickListener(this);



    }//fin onCreate



    private void vamosallogin() {
        Intent i= new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

    }

    public void ClickMenu(View view){
        //abrir drawer
        abrirDrawer(drawerLayout);
    }

    public static void abrirDrawer(DrawerLayout drawerLayout) {
        //abrir drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){
        //cerrar logo
        cerrarDrawer(drawerLayout);
    }

    public static void cerrarDrawer(DrawerLayout drawerLayout) {
        //cerrar drawer layout
        //verifica la condición del mismo
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            //cuando drawer está abierto
            //cierra el drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickHome(View view){
        recreate();
    }
    public void ClickSSD(View view){
        //redireccionamos la actividad
        redireccionActivity(this,SSD.class);

    }
    public void ClickAtencion(View view){
        redireccionActivity(this,Atencion_Cliente.class);
    }
    
    public  void ClickSalir(View view){
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
                        Toast.makeText(HomeActivity.this,"Cerrando sesión",Toast.LENGTH_SHORT).show();
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

    public static void redireccionActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        cerrarDrawer(drawerLayout);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCata:
                redireccionActivity(this,SSD.class);
                break;
        }

    }
}