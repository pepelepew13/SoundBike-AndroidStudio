package com.example.soundbike;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

public class HomeActivity extends AppCompatActivity {
    EditText respuestaEditText;
    Button btnRespuesta;
    FrameLayout animationOverlay;
    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Ajuste de insets en la vista
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicialización de los elementos del layout
        respuestaEditText = findViewById(R.id.Respuesta);
        btnRespuesta = findViewById(R.id.Btn_Respuesta);
        animationOverlay = findViewById(R.id.animation_overlay); // FrameLayout que contiene la animación
        animationView = findViewById(R.id.animationView); // LottieAnimationView

        // Listener para el botón
        btnRespuesta.setOnClickListener(view -> {
            String respuesta = respuestaEditText.getText().toString().trim();

            if (!respuesta.isEmpty()) {
                int respuestaInt = Integer.parseInt(respuesta);

                if (respuestaInt == 2024) {
                    Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_SHORT).show();

                    // Mostrar animación
                    animationOverlay.setVisibility(view.VISIBLE);
                    animationView.playAnimation();

                    // Ocultar animación después de 3 segundos
                    new Handler().postDelayed(() -> {
                        animationOverlay.setVisibility(view.GONE);
                    }, 3000);
                } else {
                    Toast.makeText(this, "Respuesta incorrecta, inténtalo de nuevo", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Por favor ingresa un año", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
