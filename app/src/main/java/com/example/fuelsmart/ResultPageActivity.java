package com.example.fuelsmart;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.result_page), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Recuperando os dados da tela de Consumo
        Bundle values = getIntent().getExtras();
        if(values != null) {
            Double alcoholValue = values.getDouble("alcoholValue");
            Double gasValue = values.getDouble("gasValue");

            Double alcoholPrice = values.getDouble("alcoholPrice");
            Double gasPrice = values.getDouble("gasPrice");

            // Calculando o melhor caso
            double alcoholResult = alcoholPrice * alcoholValue;
            double gasResult = gasPrice * gasValue;

            if(alcoholResult > gasResult) {
                TextView resultTextView = findViewById(R.id.result_value);
                resultTextView.setText("");
            }

            // Concatenando tudo para ficar em um TextView
            String showText = "Preço do ÁLCOOL: R$ " + alcoholPrice + "\n" + "Consumo de ÁLCOOL: " + alcoholValue + " (KM/L)\n \n" +
                    "Preço da GASOLINA: R$ " + gasPrice + "\n" + "Consumo de GASOLINA: " + gasValue + " (KM/L)";

            // Atualizando o TextView
            TextView dataTextView = findViewById(R.id.data_value);
            dataTextView.setText(showText);
        }
    }
}