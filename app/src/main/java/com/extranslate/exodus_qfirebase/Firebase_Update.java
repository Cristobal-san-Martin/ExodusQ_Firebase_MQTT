package com.extranslate.exodus_qfirebase;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase_Update extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextAge;
    private Button buttonUpdate, buttonDelete;

    // Constante para el nodo de la base de datos
    private static final String DATABASE_NODE = "usuarios";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebase_update);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAge = findViewById(R.id.editTextAge);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        // Obtener el usuario de FirebaseListActivity
        Usuario usuario = getIntent().getParcelableExtra("usuario");

        // Mostrar los detalles del usuario en campos de edición
        if (usuario != null) {
            editTextName.setText(usuario.getNombre());
            editTextEmail.setText(usuario.getCorreo());
            editTextAge.setText(String.valueOf(usuario.getEdad()));
        }

        // Configurar el evento de clic para el botón de actualización
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarDatosUsuario(usuario);
            }
        });

        // Configurar el evento de clic para el botón de eliminación
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarUsuario(usuario);
            }
        });
    }

    private void actualizarDatosUsuario(Usuario usuario) {
        String newName = editTextName.getText().toString();
        String newEmail = editTextEmail.getText().toString();
        int newAge = Integer.parseInt(editTextAge.getText().toString());

        // Verificar que los campos no estén vacíos
        if (newName.isEmpty() || newEmail.isEmpty()) {
            Toast.makeText(FirebaseUpdateActivity.this, "Nombre y correo son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener una referencia a la base de datos de Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(DATABASE_NODE);

        // Crear un nuevo objeto Usuario con los nuevos valores
        Usuario usuarioActualizado = new Usuario(
                usuario.getKey(),
                newName,
                newEmail,
                newAge
        );

        // Actualizar los datos en la base de datos de Firebase
        myRef.child(usuario.getKey()).setValue(usuarioActualizado)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Operación completada con éxito
                        Toast.makeText(FirebaseUpdateActivity.this, "Usuario actualizado con éxito", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        // Error en la operación
                        Toast.makeText(FirebaseUpdateActivity.this, "Error al actualizar usuario", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void eliminarUsuario(Usuario usuario) {
        // Obtener una referencia a la base de datos de Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(DATABASE_NODE);

        // Eliminar el usuario de la base de datos
        myRef.child(usuario.getKey()).removeValue()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Operación completada con éxito
                        Toast.makeText(FirebaseUpdateActivity.this, "Usuario eliminado con éxito", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        // Error en la operación
                        Toast.makeText(FirebaseUpdateActivity.this, "Error al eliminar usuario", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
