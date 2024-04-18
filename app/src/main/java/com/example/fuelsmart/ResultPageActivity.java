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
            Double alcoholPrice = values.getDouble("alcoholPrice");

            Double gasValue = values.getDouble("gasValue");
            Double gasPrice = values.getDouble("gasPrice");

            String resultMessage = "";

            // Verificar se o preço ou o consumo do álcool é zero
            if (alcoholPrice == 0 || alcoholValue == 0) {
                resultMessage = "Álcool não é uma opção viável, gasolina é a melhor escolha";
            } else if (gasPrice == 0 || gasValue == 0) {
                resultMessage = "Gasolina não é uma opção viável, álcool é a melhor escolha";
            } else {
                // Calculando o melhor caso
                double alcoholResult = alcoholPrice / alcoholValue;
                double gasResult = gasPrice / gasValue;

                // Formatando os resultados com duas casas decimais
                String formattedAlcoholResult = String.format("%.2f", alcoholResult);
                String formattedGasResult = String.format("%.2f", gasResult);

                // Comparando e determinando o melhor caso
                if (alcoholResult < gasResult) {
                    resultMessage = "ÁLCOOL: R$ " + formattedAlcoholResult + " por KM";
                } else if (gasResult < alcoholResult) {
                    resultMessage = "GASOLINA: R$ " + gasResult + " por KM";
                } else {
                    resultMessage = "Ambos têm o custo de R$ " + formattedGasResult + " por KM";
                }
            }

            // Atualizando o TextView de Resultado
            TextView resultTextView = findViewById(R.id.result_value);
            resultTextView.setText(resultMessage);

            // Concatenando tudo para ficar em um TextView
            String showText = "Preço do ÁLCOOL: R$ " + alcoholPrice + "\n" + "Consumo de ÁLCOOL: " + alcoholValue + " (KM/L)\n \n" +
                    "Preço da GASOLINA: R$ " + gasPrice + "\n" + "Consumo de GASOLINA: " + gasValue + " (KM/L)";

            // Atualizando o TextView de dados
            TextView dataTextView = findViewById(R.id.data_value);
            dataTextView.setText(showText);


        }
    }
}