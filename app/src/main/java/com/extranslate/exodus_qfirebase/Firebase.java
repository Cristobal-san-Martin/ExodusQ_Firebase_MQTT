package com.extranslate.exodus_qfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase extends AppCompatActivity{

    private EditText editTextName, editTextEmail, editTextAge;
    private Button buttonAdd, buttonViewUsers; // Agregué el botón View Users

    // Referencia a la base de datos
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebase);

        // Inicializar la referencia a la base de datos
        databaseReference = FirebaseDatabase.getInstance().getReference();

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAge = findViewById(R.id.editTextAge);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonViewUsers = findViewById(R.id.buttonViewUsers); // Referencia al nuevo botón

        // Agregar evento de clic al botón de agregar
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener valores de los campos de entrada
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                int age = Integer.parseInt(editTextAge.getText().toString().trim());

                // Lógica para agregar datos a Firebase
                agregarDatosFirebase(name, email, age);
            }
        });

        buttonViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para ir a la actividad de lista de usuarios
                abrirFirebaseListActivity();
            }
        });
    }
    private void agregarDatosFirebase(String name, String email, int age) {
        // Crear un nuevo nodo en la base de datos con una clave única
        String key = databaseReference.child("usuarios").push().getKey();

        // Crear un objeto Usuario con los datos
        Usuario usuario = new Usuario(key, name, email, age);

        // Agregar el usuario a la base de datos en la ubicación específica
        databaseReference.child("usuarios").child(key).setValue(usuario);

        // Mostrar un mensaje de éxito
        Toast.makeText(getApplicationContext(), "Usuario agregado con éxito", Toast.LENGTH_SHORT).show();
    }

    private void abrirFirebaseListActivity() {
        // Intent para abrir la actividad de lista de usuarios
        Intent intent = new Intent(Firebase.this, Firebase_List.class);
        startActivity(intent);
    }
}
