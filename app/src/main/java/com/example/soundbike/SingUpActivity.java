package com.example.soundbike;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SingUpActivity extends AppCompatActivity {
    Button btn_new_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Inicialización de variables

        UserProfileActivity perfil_usuario;


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sing_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_new_user = findViewById(R.id.Btn_New_User);

        btn_new_user.setOnClickListener(view -> {
            Intent ToHome = new Intent(SingUpActivity.this, HomeActivity.class);
            startActivity(ToHome);
        });


    }
}