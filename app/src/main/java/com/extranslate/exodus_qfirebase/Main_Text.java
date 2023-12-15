package com.extranslate.exodus_qfirebase;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Map;
import java.util.HashMap;
public class Main_Text extends AppCompatActivity {
    private EditText firstcodeEditText;
    private TextView resultTextView;
    private Spinner sp1;
    private Spinner sp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textmain);

        firstcodeEditText = findViewById(R.id.firstcodeEditText);
        resultTextView = findViewById(R.id.resultTextView);
        sp1 = findViewById(R.id.sp1);
        sp2 = findViewById(R.id.sp2);
        Button translateButton = findViewById(R.id.translateButton);
        Button GoActivityBtn = findViewById(R.id.GoMainActivityBtn);

        String[] textos = new String[]{"Texto", "Binario", "Morse"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, textos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        sp2.setAdapter(adapter);

        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = firstcodeEditText.getText().toString();

                String sistemaOrigen = sp1.getSelectedItem().toString();
                String sistemaDestino = sp2.getSelectedItem().toString();

                String resultado = convertirTexto(texto, sistemaOrigen, sistemaDestino);
                resultTextView.setText(resultado);
            }
        });

        GoActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_Text.this, Main.class);
                startActivity(intent);
            }
        });
    }

    private String convertirTexto(String texto, String sistemaOrigen, String sistemaDestino) {
        try {
            if (sistemaOrigen.equals("Texto")) {
                if (sistemaDestino.equals("Binario")) {
                    return textoToBinary(texto);
                } else if (sistemaDestino.equals("Morse")) {
                    return textoToMorse(texto);
                }
            } else if (sistemaOrigen.equals("Binario")) {
                if (sistemaDestino.equals("Texto")) {
                    return binaryToText(texto);
                } else if (sistemaDestino.equals("Morse")) {
                    return binaryToMorse(texto);
                }
            } else if (sistemaOrigen.equals("Morse")) {
                if (sistemaDestino.equals("Texto")) {
                    return morseToText(texto);
                } else if (sistemaDestino.equals("Binario")) {
                    return morseToBinary(texto);
                }
            }
        } catch (NumberFormatException e) {
            return "Error: Número no válido";
        }

        return "Error: Conversión no válida";
    }

    private String textoToBinary(String texto) {
        StringBuilder binaryResult = new StringBuilder();
        for (char c : texto.toCharArray()) {
            String binary = Integer.toBinaryString(c);
            binaryResult.append(binary).append("");
        }
        return binaryResult.toString().trim();
    }

    private String textoToMorse(String texto) {
        Map<Character, String> morseMap = new HashMap<>();
        morseMap.put('A', ".-");
        morseMap.put('B', "-...");
        morseMap.put('C', "-.-.");
        morseMap.put('D', "-..");
        morseMap.put('E', ".");
        morseMap.put('F', "..-.");
        morseMap.put('G', "--.");
        morseMap.put('H', "....");
        morseMap.put('I', "..");
        morseMap.put('J', ".---");
        morseMap.put('K', "-.-");
        morseMap.put('L', ".-..");
        morseMap.put('M', "--");
        morseMap.put('N', "-.");
        morseMap.put('Ñ', ".-.-.");
        morseMap.put('O', "---");
        morseMap.put('P', ".--.");
        morseMap.put('Q', "--.-");
        morseMap.put('R', ".-.");
        morseMap.put('S', "...");
        morseMap.put('T', "-");
        morseMap.put('U', "..-");
        morseMap.put('V', "...-");
        morseMap.put('W', ".--");
        morseMap.put('X', "-..-");
        morseMap.put('Y', "-.--");
        morseMap.put('Z', "--..");
        morseMap.put('a', ".- .-.-.");
        morseMap.put('b', "-... .-.-.");
        morseMap.put('c', "-.-. .-.-.");
        morseMap.put('d', "-.. .-.-.");
        morseMap.put('e', ". .-.-.");
        morseMap.put('f', "..-. .-.-.");
        morseMap.put('g', "--. .-.-.");
        morseMap.put('h', ".... .-.-.");
        morseMap.put('i', ".. .-.-.");
        morseMap.put('j', ".--- .-.-.");
        morseMap.put('k', "-.- .-.-.");
        morseMap.put('l', ".-.. .-.-.");
        morseMap.put('m', "-- .-.-.");
        morseMap.put('n', "-. .-.-.");
        morseMap.put('ñ', ".-.-. .-.-.");
        morseMap.put('o', "--- .-.-.");
        morseMap.put('p', ".--. .-.-.");
        morseMap.put('q', "--.- .-.-.");
        morseMap.put('r', ".-. .-.-.");
        morseMap.put('s', "... .-.-.");
        morseMap.put('t', "- .-.-.");
        morseMap.put('u', "..- .-.-.");
        morseMap.put('v', "...- .-.-.");
        morseMap.put('w', ".-- .-.-.");
        morseMap.put('x', "-..- .-.-.");
        morseMap.put('y', "-.-- .-.-.");
        morseMap.put('z', "--.. .-.-.");

        StringBuilder morseResult = new StringBuilder();

        for (char c : texto.toUpperCase().toCharArray()) {
            if (morseMap.containsKey(c)) {
                morseResult.append(morseMap.get(c)).append(" ");
            } else if (c == ' ') {
                morseResult.append("/ ");
            }
        }
        return morseResult.toString().trim();
    }

    private String binaryToText(String binario) {
        String[] binaryArray = binario.split("");
        StringBuilder textResult = new StringBuilder();
        for (String binary : binaryArray) {
            int decimal = Integer.parseInt(binary, 2);
            textResult.append((char) decimal);
        }
        return textResult.toString();
    }

    private String binaryToMorse(String binario) {
        String[] binaryArray = binario.split("");
        StringBuilder morseResult = new StringBuilder();
        for (String binary : binaryArray) {
            int decimal = Integer.parseInt(binary, 2);
            morseResult.append((char) decimal);
        }
        return morseResult.toString();
    }

    private String morseToText(String morse) {
        String[] morseArray = morse.split(" "); // Divide el código Morse en palabras
        StringBuilder textResult = new StringBuilder();

        // Define un mapa de código Morse a letras
        Map<String, String> morseToLetter = new HashMap<>();
        morseToLetter.put(".-", "A");

        morseToLetter.put(".- .-.-.", "a");//.-.-.
        morseToLetter.put("-... ", "B");
        morseToLetter.put("-... .-.-.", "b");
        morseToLetter.put("-.-.", "C");
        morseToLetter.put("-.-. .-.-.", "c");
        morseToLetter.put("-..", "D");
        morseToLetter.put("-.. .-.-.", "d");
        morseToLetter.put(".", "E");
        morseToLetter.put(". .-.-.", "e");//-.-. .-.-.
        morseToLetter.put("..-.", "F");
        morseToLetter.put("..-. .-.-.", "f");
        morseToLetter.put("--.", "G");
        morseToLetter.put("--. .-.-.", "g");
        morseToLetter.put("....", "H");
        morseToLetter.put(".... .-.-.", "h");
        morseToLetter.put("..", "I");
        morseToLetter.put(".. .-.-.", "i");
        morseToLetter.put(".---", "J");
        morseToLetter.put(".--- .-.-.", "j");
        morseToLetter.put("-.-", "K");
        morseToLetter.put("-.- .-.-.", "k");
        morseToLetter.put(".-..", "L");
        morseToLetter.put(".-.. .-.-.", "l");
        morseToLetter.put("--", "M");
        morseToLetter.put("-- .-.-.", "m");
        morseToLetter.put("-.", "N");
        morseToLetter.put("-. .-.-.", "n");
        morseToLetter.put(".-.-.", "Ñ");
        morseToLetter.put(".-.-. .-.-.", "ñ");
        morseToLetter.put("---", "O");
        morseToLetter.put("--- .-.-.", "o");
        morseToLetter.put(".--.", "P");
        morseToLetter.put(".--. .-.-.", "p");
        morseToLetter.put("--.-", "Q");
        morseToLetter.put("--.- .-.-.", "q");
        morseToLetter.put(".-.", "R");
        morseToLetter.put(".-. .-.-.", "r");
        morseToLetter.put("...", "S");
        morseToLetter.put("... .-.-.", "s");
        morseToLetter.put("-", "T");
        morseToLetter.put("- .-.-.", "t");
        morseToLetter.put("..-", "U");
        morseToLetter.put("..- .-.-.", "u");
        morseToLetter.put("...-", "V");
        morseToLetter.put("...- .-.-.", "v");
        morseToLetter.put(".--", "W");
        morseToLetter.put(".-- .-.-.", "w");
        morseToLetter.put("-..-", "X");
        morseToLetter.put("-..- .-.-.", "x");
        morseToLetter.put("-.--", "Y");
        morseToLetter.put("-.-- .-.-.", "y");
        morseToLetter.put("--..", "Z");
        morseToLetter.put("--.. .-.-.", "z");

        for (String morseWord : morseArray) {
            String[] morseLetters = morseWord.split(" "); // Divide el código Morse en letras
            for (String morseLetter : morseLetters) {
                if (morseToLetter.containsKey(morseLetter)) {
                    textResult.append(morseToLetter.get(morseLetter));
                } else {
                    // Agrega un espacio si el código Morse no se encuentra en el mapa
                    textResult.append(" ");
                }
            }
            // Agrega un espacio entre las palabras
            textResult.append(" ");
        }

        return textResult.toString().trim(); // Elimina espacios en blanco innecesarios al principio y al final
    }

    private String morseToBinary(String morse) {
        String[] morseArray = morse.split(" "); // Divide el código Morse en palabras
        StringBuilder binaryResult = new StringBuilder();

        Map<String, String> morseToBinary = new HashMap<>();
        morseToBinary.put(".-", "01");
        morseToBinary.put("-...", "1000");
        morseToBinary.put("-.-.", "1010");
        morseToBinary.put("-..", "100");
        morseToBinary.put(".", "0");
        morseToBinary.put("..-.", "0010");
        morseToBinary.put("--.", "1100");
        morseToBinary.put("....", "0000");
        morseToBinary.put("..", "001");
        morseToBinary.put(".---", "0111");
        morseToBinary.put("-.-", "101");
        morseToBinary.put(".-..", "0101");
        morseToBinary.put("--", "11");
        morseToBinary.put("-.", "10");
        morseToBinary.put("---", "111");
        morseToBinary.put(".--.", "0100");
        morseToBinary.put("--.-", "1101");
        morseToBinary.put(".-.", "010");
        morseToBinary.put("...", "000");
        morseToBinary.put("-", "1");
        morseToBinary.put("..-", "0011");
        morseToBinary.put("...-", "0001");
        morseToBinary.put(".--", "011");
        morseToBinary.put("-..-", "1001");
        morseToBinary.put("-.--", "1011");
        morseToBinary.put("--..", "1100");

        for (String morseWord : morseArray) {
            for (char morseLetter : morseWord.toCharArray()) {
                String morseChar = String.valueOf(morseLetter);
                if (morseToBinary.containsKey(morseChar)) {
                    binaryResult.append(morseToBinary.get(morseChar));
                    binaryResult.append(" "); // Agrega espacio entre caracteres binarios
                }
            }
            binaryResult.append(" "); // Agrega espacio entre palabras
        }
        return binaryResult.toString().trim();
    }
}
