package com.example.soundbike;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    Button btn_sing_up;
    Button btn_login;
    EditText Usuario_EditText;
    EditText Contraseña_EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Encontrar el boton
        btn_sing_up = findViewById(R.id.Btn_Sing_Up);

        //Configurar el onclick
        btn_sing_up.setOnClickListener(view -> {
            Intent ToSingUp = new Intent(LoginActivity.this, SingUpActivity.class);
            startActivity(ToSingUp);
        });

        Usuario_EditText = findViewById(R.id.id_user);
        Contraseña_EditText = findViewById(R.id.id_password);
        btn_login = findViewById(R.id.Btn_Login);

        btn_login.setOnClickListener(view -> {
            String Usuario = Usuario_EditText.getText().toString().trim();
            String Contraseña = Contraseña_EditText.getText().toString().trim();

            if (Usuario.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa el usuario", Toast.LENGTH_SHORT).show();
            } else if (Contraseña.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa la contraseña", Toast.LENGTH_SHORT).show();
            } else if ((Usuario.equals("admin") && Contraseña.equals("admin")) || (Usuario.equals("") && Contraseña.equals(""))) {
                Intent ToHome = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(ToHome);
            } else {
                Toast.makeText(this, "Usuario o Contraseña incorrectos, inténtalo de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}