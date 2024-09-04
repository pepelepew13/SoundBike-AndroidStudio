package com.example.soundbike;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;  // Importa el Handler
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        // Referencia al LottieAnimationView
        LottieAnimationView loaderCat = findViewById(R.id.loader_cat_view);

        // Iniciar la animación
        loaderCat.setAnimation(R.raw.loader_cat);
        loaderCat.playAnimation();

        // Configurar un retraso para pasar a la siguiente actividad después del tiempo definido
        // Iniciar la siguiente actividad
        new Handler().postDelayed(this::startNextActivity, 5000);  // 5000 ms = 5 segundos

        // Manejo de insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Método para iniciar la siguiente actividad
    private void startNextActivity() {
        Intent splash = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(splash);
        finish();  // Finaliza la SplashActivity
    }
}
