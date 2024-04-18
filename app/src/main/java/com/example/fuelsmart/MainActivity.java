package com.example.fuelsmart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(onClickSubmit());
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private View.OnClickListener onClickSubmit() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText alcoholInput = (EditText) findViewById(R.id.input_AlcoholPrice);
                EditText gasInput = (EditText) findViewById(R.id.input_GasPrice);

                String alcoholPriceStr = alcoholInput.getText().toString();
                String gasPriceStr = gasInput.getText().toString();

                // Garantindo que os campos foram inseridos
                if(alcoholPriceStr.isEmpty() || gasPriceStr.isEmpty()) {
                    alert("Por favor insira um valor válido para os campos");
                } else {
                    // Navegando para próxima tela
                    Intent nextPage = new Intent(getBaseContext(), ConsumePageActivity.class);

                    double alcoholPrice = Double.parseDouble(alcoholPriceStr);
                    double gasPrice = Double.parseDouble(gasPriceStr);

                    // Salvando os preços
                    Bundle prizes = new Bundle();
                    prizes.putDouble("alcoholPrice", alcoholPrice);
                    prizes.putDouble("gasPrice", gasPrice);
                    nextPage.putExtras(prizes);

                    // Passando para próxima tela
                    startActivity(nextPage);
                }
            }
        };
    }
}