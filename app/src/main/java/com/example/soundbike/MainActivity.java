package com.example.soundbike;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    String TAG = "Test";
    Button btn_entrar;
    private int startCounter = 0;
    private int resumeCounter = 0;
    private int pauseCounter = 0;
    private int stopCounter = 0;
    private int restartCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ajuste de padding según insets de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Encuentra el botón por su ID
        btn_entrar = findViewById(R.id.Btn_Entrar);

        // Configura el click listener
        btn_entrar.setOnClickListener(view -> {
            // Crea un Intent para ir a LoginActivity
            Intent ToLogin = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(ToLogin);
        });
    }

    // Entendiendo el ciclo del aplicatico
    @Override
    protected void onStart() {
        super.onStart();
        startCounter++;
        Log.d(TAG, "Estoy en el onStart");
        Toast.makeText(this, "Estoy en el onStart " + startCounter, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeCounter++;
        Log.d(TAG, "Estoy en el onResume");
        Toast.makeText(this, "Estoy en el onResume " + resumeCounter, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseCounter++;
        Log.d(TAG, "Estoy en el onPause");
        Toast.makeText(this, "Estoy en el onPause " + pauseCounter, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopCounter++;
        Log.d(TAG, "Estoy en el onStop");
        Toast.makeText(this, "Estoy en el onStop " + stopCounter, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        restartCounter++;
        Log.d(TAG, "Estoy en el onRestart");
        Toast.makeText(this, "Estoy en el onRestart " + restartCounter, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Estoy en el onDestroy");
        Toast.makeText(this, "Estoy en el onDestroy", Toast.LENGTH_SHORT).show();
    }
}
