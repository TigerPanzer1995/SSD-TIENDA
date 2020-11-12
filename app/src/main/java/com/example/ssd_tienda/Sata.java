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
import Vista.AdapterSata;

public class Sata extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinnerSata,spinnerPzasSata;
    DrawerLayout drawerLayout;
    //link 128gb
    private final static String Uno128gb= "https://mpago.la/2DKsckT";
    private final static String Dos128GB =  "https://mpago.la/2JdqYDL";
    private final static String Tres128gb = "https://mpago.la/2WX2xCi";
    private final static String Cuatro128gb = "https://mpago.la/17RMnJb";
    private final static  String Cinco128gb = "https://mpago.la/2vVBcYp";

    //link 250gb
    private final static String Uno240gb= "https://mpago.la/2hdJnWV";
    private final static String Dos240gb = "https://mpago.la/2EgN8Ch";
    private final static String Tres240gb = "https://mpago.la/2gZNjud";
    private final static String Cuatro240gb ="https://mpago.la/27nkCgq";
    private final static String Cinco240gb = "https://mpago.la/2ddTusN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sata);
        drawerLayout= findViewById(R.id.drawer_layout);
        //botones
        Button btn_return_sata = findViewById(R.id.btn_return_sata);
        btn_return_sata.setOnClickListener(this);
        Button btn_comprar_Sata = findViewById(R.id.comprarSata);
        btn_comprar_Sata.setOnClickListener(this);

        ViewPager viewPager = findViewById(R.id.imagePagerSata);
         spinnerSata= findViewById(R.id.spinnerSata);
         spinnerPzasSata = findViewById(R.id.spinnerPzasSata);

        AdapterSata adapterSata = new AdapterSata(this);
        viewPager.setAdapter(adapterSata);

        //spinner
        String [] opciones = {"128GB $565.00","240GB $948.00"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_item_spinnersata,opciones);
        spinnerSata.setAdapter(adapter);
        //spinner piezas
        String [] piezasSata = {"1","2","3","4","5"};
        ArrayAdapter<String> adapterPiezasSata = new ArrayAdapter<>(this,R.layout.spinner_item_spinnersata,piezasSata);
        spinnerPzasSata.setAdapter(adapterPiezasSata);


    }//fin del onCreate

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
                                Toast.makeText(Sata.this,"Cerrando sesión",Toast.LENGTH_SHORT).show();
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
            case R.id.btn_return_sata:
                HomeActivity.redireccionActivity(this, SSD.class);
                break;
            case R.id.comprarSata:
               //128 gb link
                String capacidad= spinnerSata.getSelectedItem().toString();
                String piezas = spinnerPzasSata.getSelectedItem().toString();
                if (capacidad.equals("128GB $565.00")&& piezas.equals("1")){
                    Toast.makeText(this, "Vas a comprar 01pza SSD SATA 128GB  ", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(Uno128gb));
                    startActivity(intent);

                }else if (capacidad.equals("128GB $565.00") &&piezas.equals("2")){
                    Toast.makeText(this, "Vas a comprar 02pzas SSD SATA 128GB", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(Dos128GB));
                    startActivity(intent);

                }else if (capacidad.equals("128GB $565.00")&& piezas.equals("3")){
                    Toast.makeText(this, "Vas a comprar 03pzas SSD SATA 128GB", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(Tres128gb));
                    startActivity(intent);

                }else if (capacidad.equals("128GB $565.00")&& piezas.equals("4")){
                    Toast.makeText(this, "Vas a comprar 04pzas SSD SATA 128GB", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(Cuatro128gb));
                    startActivity(intent);

                }else if (capacidad.equals("128GB $565.00")&& piezas.equals("5")){
                    Toast.makeText(this, "Vas a comprar 05pzas SSD SATA 128GB", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(Cinco128gb));
                    startActivity(intent);
                    //240gb
                }else if (capacidad.equals("240GB $948.00")&& piezas.equals("1")){
                    Toast.makeText(this, "Vas a comprar 01pza SSD SATA 250GB  ", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(Uno240gb));
                    startActivity(intent);

                }else if (capacidad.equals("240GB $948.00")&& piezas.equals("2")){
                    Toast.makeText(this, "Vas a comprar 02pza SSD SATA 250GB  ", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(Dos240gb));
                    startActivity(intent);

                }else if (capacidad.equals("240GB $948.00")&& piezas.equals("3")){
                    Toast.makeText(this, "Vas a comprar 03pza SSD SATA 250GB  ", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(Tres240gb));
                    startActivity(intent);

                }else if (capacidad.equals("240GB $948.00") && piezas.equals("4")){
                    Toast.makeText(this, "Vas a comprar 04pza SSD SATA 250GB  ", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(Cuatro240gb));
                    startActivity(intent);

                }else if (capacidad.equals("240GB $948.00")&& piezas.equals("5")){
                    Toast.makeText(this, "Vas a comprar 05pza SSD SATA 250GB  ", Toast.LENGTH_SHORT).show();
                    intent.setData(Uri.parse(Cinco240gb));
                    startActivity(intent);
                }
                break;
        }

    }
}//fin de la clase
