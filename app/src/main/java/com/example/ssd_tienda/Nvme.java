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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import Base_de_datos.MainActivity;
import Vista.AdaptadorNvme;

public class Nvme extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout drawerLayout;
    private Spinner spinnerNvme, spinnernvmePzas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nvme);
        //botones
        Button btn_return_nvme = findViewById(R.id.btn_return_nvme);
        btn_return_nvme.setOnClickListener(this);
        Button btn_comprar_nvme = findViewById(R.id.comprarNvme);
        btn_comprar_nvme.setOnClickListener(this);

        drawerLayout = findViewById(R.id.drawer_layout);
        spinnerNvme= findViewById(R.id.spinnerNvme);

        ViewPager viewPager = findViewById(R.id.imagePagerNvme);
        AdaptadorNvme adaptadorNvme = new AdaptadorNvme(this);
        viewPager.setAdapter(adaptadorNvme);

        //spinner nvme
        String [] opciones ={"128GB $300.00","240GB $500.00"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_item_spinnernvme,opciones);
        spinnerNvme.setAdapter(adapter);
        //spiner piezas
        spinnernvmePzas = findViewById(R.id.spinnerPzasNvme);
        String [] piezasNvme = {"1","2","3","4","5"};
        ArrayAdapter<String> adapterPzasNvme = new ArrayAdapter<>(this,R.layout.spinner_item_spinnernvme, piezasNvme);
        spinnernvmePzas.setAdapter(adapterPzasNvme);



    }//fin del oncreate

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
                                Toast.makeText(Nvme.this,"Cerrando sesión",Toast.LENGTH_SHORT).show();
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
        switch (view.getId()){
            case R.id.btn_return_nvme:
                HomeActivity.redireccionActivity(this, SSD.class);
                break;

            case R.id.comprarNvme:
                Toast.makeText(this, "Vas a comprar esto", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}//fin de la clase