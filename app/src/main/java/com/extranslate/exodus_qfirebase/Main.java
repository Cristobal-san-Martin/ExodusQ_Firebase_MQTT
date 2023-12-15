package com.extranslate.exodus_qfirebase;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Main extends AppCompatActivity {
    private EditText firstcodeEditText;
    private TextView resultTextView;
    private Spinner sp1;
    private Spinner sp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        firstcodeEditText = findViewById(R.id.firstcodeEditText);
        resultTextView = findViewById(R.id.resultTextView);
        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);
        Button translateButton = findViewById(R.id.translateButton);
        Button irAotraActividadButton = findViewById(R.id.GoMainTextActivityBtn);
        Button irAMapaButton = findViewById(R.id.GoMapActivityBtn);
        Button iraSQLite = findViewById(R.id.GoSQLiteActivityBtn);
        Button irAFirebase = findViewById(R.id.GoFirebaseActivityBtn);

        String[] numeros = new String[]{"Decimal", "Binario", "Hexa-Decimal", "Octal"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, numeros);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        sp2.setAdapter(adapter);

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = firstcodeEditText.getText().toString();

                String sistemaOrigen = sp1.getSelectedItem().toString();
                String sistemaDestino = sp2.getSelectedItem().toString();

                String resultado = convertirNumero(numero, sistemaOrigen, sistemaDestino);
                resultTextView.setText(resultado);
            }
        });

        irAotraActividadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Main_Text.class);
                startActivity(intent);
            }
        });

        irAMapaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Map.class);
                startActivity(intent);
            }
        });


        iraSQLite.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(Main.this, Persona.class);
                startActivity(intent);
            }
        });

        irAFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Firebase.class);
                startActivity(intent);
            }
        });
    }

    private String convertirNumero(String numero, String sistemaOrigen, String sistemaDestino) {
        try {
            if (sistemaOrigen.equals("Decimal")) {
                if (sistemaDestino.equals("Binario")) {
                    int decimal = Integer.parseInt(numero);
                    return Integer.toBinaryString(decimal);
                } else if (sistemaDestino.equals("Hexa-Decimal")) {
                    int decimal = Integer.parseInt(numero);
                    return Integer.toHexString(decimal);
                } else if (sistemaDestino.equals("Octal")) {
                    int decimal = Integer.parseInt(numero);
                    return Integer.toOctalString(decimal);
                }
            } else if (sistemaOrigen.equals("Binario")) {
                if (sistemaDestino.equals("Decimal")) {
                    int decimal = Integer.parseInt(numero, 2);
                    return Integer.toString(decimal);
                } else if (sistemaDestino.equals("Hexa-Decimal")) {
                    int decimal = Integer.parseInt(numero, 2);
                    return Integer.toHexString(decimal);
                } else if (sistemaDestino.equals("Octal")) {
                    int decimal = Integer.parseInt(numero, 2);
                    return Integer.toOctalString(decimal);
                }
            } else if (sistemaOrigen.equals("Hexa-Decimal")) {
                if (sistemaDestino.equals("Decimal")) {
                    int decimal = Integer.parseInt(numero, 16);
                    return Integer.toString(decimal);
                } else if (sistemaDestino.equals("Binario")) {
                    int decimal = Integer.parseInt(numero, 16);
                    return Integer.toBinaryString(decimal);
                } else if (sistemaDestino.equals("Octal")) {
                    int decimal = Integer.parseInt(numero, 16);
                    return Integer.toOctalString(decimal);
                }
            } else if (sistemaOrigen.equals("Octal")) {
                if (sistemaDestino.equals("Decimal")) {
                    int decimal = Integer.parseInt(numero, 8);
                    return Integer.toString(decimal);
                } else if (sistemaDestino.equals("Binario")) {
                    int decimal = Integer.parseInt(numero, 8);
                    return Integer.toBinaryString(decimal);
                } else if (sistemaDestino.equals("Hexa-Decimal")) {
                    int decimal = Integer.parseInt(numero, 8);
                    return Integer.toHexString(decimal);
                }
            }
        } catch (NumberFormatException e) {
            return "Error: Número no válido";
        }
        return "Error: Conversión no válida";
    }
}