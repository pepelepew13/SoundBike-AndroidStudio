package com.example.soundbike;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;

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

    //Inicializo variables camara
    private final int REQUEST_IMAGE_CAPTURE = 1;
    Button btnCamera;

    private static final int REQUEST_CAMERA_PERMISSION = 200;

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

        // Agrega el botón para abrir la cámara
        btnCamera = findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(v -> {
            Toast.makeText(HomeActivity.this, "Botón de cámara presionado", Toast.LENGTH_SHORT).show();
            checkCameraPermission();
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

    private void checkCameraPermission() {
        // Verificar si el permiso ya está concedido
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Solicitar el permiso si no está concedido
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            // Si el permiso ya está concedido, abrir la cámara
            dispatchTakePictureIntent();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Si el usuario concede el permiso, abrir la cámara
                dispatchTakePictureIntent();
            } else {
                // Si el usuario deniega el permiso, mostrar un mensaje
                Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Método para abrir la cámara
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            Log.d("Camera", "Intent de cámara iniciado");
        } else {
            Log.e("Camera", "No se encontró ninguna aplicación de cámara");
            Toast.makeText(this, "No se encontró ninguna aplicación de cámara", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            // Aquí puedes mostrar la imagen en un ImageView o hacer cualquier otra operación
            Toast.makeText(this, "Imagen capturada", Toast.LENGTH_SHORT).show();
        }
    }
}