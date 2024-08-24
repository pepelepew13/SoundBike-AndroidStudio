package com.example.soundbike;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {
    EditText respuestaEditText;
    Button btnRespuesta;

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
        respuestaEditText = findViewById(R.id.Respuesta); // EditText donde se ingresa la respuesta
        btnRespuesta = findViewById(R.id.Btn_Respuesta);  // Botón para enviar la respuesta

        // Listener para el botón
        btnRespuesta.setOnClickListener(view -> {
            String respuesta = respuestaEditText.getText().toString().trim(); // Obtener y limpiar el texto ingresado

            if (!respuesta.isEmpty()) { // Verificar si el campo no está vacío
                int respuestaInt = Integer.parseInt(respuesta); // Convertir a número

                // Comprobar si la respuesta es correcta
                if (respuestaInt == 2024) {
                    Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_SHORT).show(); // Mensaje para respuesta correcta
                } else {
                    Toast.makeText(this, "Respuesta incorrecta, inténtalo de nuevo", Toast.LENGTH_SHORT).show(); // Mensaje para respuesta incorrecta
                }
            } else {
                Toast.makeText(this, "Por favor ingresa un año", Toast.LENGTH_SHORT).show(); // Mensaje si no ingresó un valor
            }
        });
    }
}
