package com.extranslate.exodus_qfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class Firebase_List extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebase_list);

        // Inicializar Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("usuarios"); // Reemplaza con tu nodo real

        // Inicializar ListView y ArrayList
        listView = findViewById(R.id.lst1);
        dataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        // Leer datos de Firebase Realtime Database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataList.clear();
                // Limpiar la lista antes de agregar nuevos datos
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Usuario usuario = snapshot.getValue(Usuario.class);

                    // Formatear la información del usuario y agregarla a la lista
                    String userInfo = String.format("Nombre: %s\nEmail: %s\nEdad: %d",
                            usuario.getNombre(), usuario.getCorreo(), usuario.getEdad());
                    dataList.add(userInfo);
                }
                adapter.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Manejar errores aquí
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el usuario seleccionado
                Usuario usuarioSeleccionado = obtenerUsuarioSeleccionado(position);

                // Crear un Intent para abrir FirebaseUpdateActivity
                Intent intent = new Intent(Firebase_List.this, Firebase_Update.class);

                // Pasar información del usuario a FirebaseUpdateActivity
                intent.putExtra("usuario", usuarioSeleccionado);
                startActivity(intent);
            }
        });
    }

    private Usuario obtenerUsuarioSeleccionado(int position) {
        // Obtener el usuario seleccionado desde la lista
        String usuarioInfo = dataList.get(position);

        // Dividir la información del usuario
        String[] parts = usuarioInfo.split("\n");
        String nombre = parts[0].substring(parts[0].indexOf(":") + 2);
        String correo = parts[1].substring(parts[1].indexOf(":") + 2);
        int edad = Integer.parseInt(parts[2].substring(parts[2].indexOf(":") + 2));

        // Crear un objeto Usuario con la información
        Usuario usuarioSeleccionado = new Usuario("", nombre, correo, edad);

        return usuarioSeleccionado;
    }
}