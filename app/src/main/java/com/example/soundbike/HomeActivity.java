package com.example.soundbike;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
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

    UserProfileActivity perfil_usuario; //inicializo la variable del metodo
    TextView userInfoTextView;

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

        perfil_usuario = new UserProfileActivity(1000000,(byte) 17,"Sebastian","Alzate");

        // Listener para el botón
        btnRespuesta.setOnClickListener(view -> {
            String respuesta = respuestaEditText.getText().toString().trim();

            if (!respuesta.isEmpty()) {
                int respuestaInt = Integer.parseInt(respuesta);

                if (respuestaInt == 2024) {
                    Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_SHORT).show();

                    // Mostrar animación con superposición
                    animationOverlay.setVisibility(View.VISIBLE);
                    animationView.playAnimation();

                    // Ocultar animación después de 3 segundos
                    new Handler().postDelayed(() -> {
                        animationOverlay.setVisibility(View.GONE);
                    }, 3000);

                    // Ocultar el teclado
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(respuestaEditText.getWindowToken(), 0);

                } else {
                    Toast.makeText(this, "Respuesta incorrecta, inténtalo de nuevo", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Por favor ingresa un año", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void DatosUsuario(View v) {
        // Accediendo a los datos del usuario
        String userInfo = "ID: " + perfil_usuario.getId() + "\n" +
                "Nombre: " + perfil_usuario.getNombre() + " " + perfil_usuario.getApellido() + "\n" +
                "Edad: " + perfil_usuario.getEdad();

        // Actualizando el TextView con la información del usuario
            userInfoTextView = findViewById(R.id.user_info);
        userInfoTextView.setText(userInfo);
    }

}
