package com.example.ssd_tienda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import Base_de_datos.MainActivity;
import Vista.AdapterMsata;

public class Msata extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout drawerLayout;
    private ViewPager viewPagerMsata;
    private Spinner spinnerMsata,spinnerPzas;
    private static final String msata1pza = "https://mpago.la/17WUjsX";
    private static final String msata2pza = "https://mpago.la/2gv95wj";
    private static final String msata3pza = "https://mpago.la/2ad2pNA";
    private static final String msata4pza = "https://mpago.la/2BrrAYv";
    private static final String msata5pza = "https://mpago.la/1e1grC4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msata);
        //botones
        Button btn_return_Msata = findViewById(R.id.btn_return_msata);
        btn_return_Msata.setOnClickListener(this);
        Button comprar_msata = findViewById(R.id.comprarMsata);
        comprar_msata.setOnClickListener(this);


        drawerLayout= findViewById(R.id.drawer_layout);
        viewPagerMsata = findViewById(R.id.imagePagerMsata);
        AdapterMsata adapterMsata = new AdapterMsata(this);
        viewPagerMsata.setAdapter(adapterMsata);

        //spinner msata
        spinnerMsata= findViewById(R.id.spinnerMSata);
        String [] opciones ={"256GB $706.00"};
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

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);


        switch (view.getId()){
            case R.id.btn_return_msata:
                HomeActivity.redireccionActivity(this, SSD.class);
                break;

            case R.id.comprarMsata:
                //240 gb msata
                String capacidad = spinnerMsata.getSelectedItem().toString();
                String piezas = spinnerPzas.getSelectedItem().toString();
                if (capacidad.equals("256GB $706.00")&& piezas.equals("1")){
                    Toast.makeText(this, "Vas a comprar 01 pza SSD MSATA 256GB", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(msata1pza));
                    startActivity(intent);
                }else if (capacidad.equals("256GB $706.00")&& piezas.equals("2")){
                    Toast.makeText(this, "Vas a comprar 02 pza SSD MSATA 256GB", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(msata2pza));
                    startActivity(intent);
                }else if (capacidad.equals("256GB $706.00")&& piezas.equals("3")){
                    Toast.makeText(this, "Vas a comprar 03 pza SSD MSATA 256GB", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(msata3pza));
                    startActivity(intent);
                }else if (capacidad.equals("256GB $706.00")&& piezas.equals("4")){
                    Toast.makeText(this, "Vas a comprar 04 pza SSD MSATA 256GB", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(msata4pza));
                    startActivity(intent);
                }else if (capacidad.equals("256GB $706.00")&& piezas.equals("5")){
                    Toast.makeText(this, "Vas a comprar 05 pza SSD MSATA 256GB", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(msata5pza));
                    startActivity(intent);
                }

                break;

        }

    }
}//fin de la clase