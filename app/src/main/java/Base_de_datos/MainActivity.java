package Base_de_datos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ssd_tienda.HomeActivity;
import com.example.ssd_tienda.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mfirebaseAuth;
    private FirebaseAuth.AuthStateListener mautListener;
    public static final int SIGN_IN =1;

    List <AuthUI.IdpConfig> providers= Arrays.asList(
            new AuthUI.IdpConfig.GoogleBuilder().build()
    );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mfirebaseAuth = FirebaseAuth.getInstance();
        mautListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //accdemos a la informacion del usuario
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    vamosalHome();
                }else {
                    startActivityForResult(
                            AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setIsSmartLockEnabled(false)
                            .build(),SIGN_IN
                    );
                }

            }
        };
    }// fin del onCreate

    @Override
    protected void onResume() {
        super.onResume();
        mfirebaseAuth.addAuthStateListener(mautListener);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //cuando se abra la app en auto haga login
        mfirebaseAuth.removeAuthStateListener(mautListener);

    }

    public void vamosalHome() {
        Intent i= new Intent(this, HomeActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

    }
}