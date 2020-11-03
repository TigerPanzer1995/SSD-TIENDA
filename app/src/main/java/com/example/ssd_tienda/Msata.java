package com.example.ssd_tienda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import Base_de_datos.MainActivity;
import Vista.AdapterMsata;

public class Msata extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private ViewPager viewPagerMsata;
    private Spinner spinnerMsata,spinnerPzas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msata);
        drawerLayout= findViewById(R.id.drawer_layout);
        viewPagerMsata = findViewById(R.id.imagePagerMsata);
        AdapterMsata adapterMsata = new AdapterMsata(this);
        viewPagerMsata.setAdapter(adapterMsata);

        //spinner msata
        spinnerMsata= findViewById(R.id.spinnerMSata);
        String [] opciones ={"128GB $300.00","240GB $500.00"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_item_spinnermsata,opciones);
        spinnerMsata.setAdapter(adapter);

        //spinner piezas
        spinnerPzas = findViewById(R.id.spinnerPzas);
        String [] piezas = {"1","2","3","4","5"};
        ArrayAdapter <String> adapterPzas = new ArrayAdapter<>(this,R.layout.spinner_item_spinnermsata, piezas);
        spinnerPzas.setAdapter(adapterPzas);



    }//fin del metodo onCreate
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
        HomeActivity.redireccionActivity(this,Atencion_Cliente.class);
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
                                Toast.makeText(Msata.this,"Cerrando sesión",Toast.LENGTH_SHORT).show();
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

    public void Comprar(View view){
        String selecion = spinnerMsata.getSelectedItem().toString();
        if(selecion.equals("128GB $300.00")){
            Toast.makeText(this, "Vas a comprar un ssd de 128gb", Toast.LENGTH_SHORT).show();
        }else if (selecion.equals("240GB $500.00")){
            Toast.makeText(this, "Vas a comprar un ssd de 240gb", Toast.LENGTH_SHORT).show();

        }

    }
    public void Carrito (View view){
        String selecion = spinnerMsata.getSelectedItem().toString();
        if(selecion.equals("128GB $300.00")){
            Toast.makeText(this, "Agregaste un ssd de 128gb", Toast.LENGTH_SHORT).show();
        }else if (selecion.equals("240GB $500.00")){
            Toast.makeText(this, "Agregaste un ssd de 240gb", Toast.LENGTH_SHORT).show();

        }

    }
}//fin de la clase