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

public class ConsumePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consume_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.consume_page), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnSubmit = findViewById(R.id.btn_next);
        btnSubmit.setOnClickListener(onClickSubmit());

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(onClickBack());
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private View.OnClickListener onClickSubmit() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText alcoholInput = (EditText) findViewById(R.id.input_AlcoholValue);
                EditText gasInput = (EditText) findViewById(R.id.input_GasValue);

                String alcoholValueStr = alcoholInput.getText().toString();
                String gasValueStr = gasInput.getText().toString();

                // Garantindo que os campos foram inseridos
                if(alcoholValueStr.isEmpty() || gasValueStr.isEmpty()) {
                    alert("Por favor insira um valor válido para os campos");
                } else {
                    // Pegando os dados da tela de preços
                    Bundle data = getIntent().getExtras();

                    double alcoholValue = Double.parseDouble(alcoholValueStr);
                    double gasValue = Double.parseDouble(gasValueStr);

                    assert data != null;
                    data.putDouble("alcoholValue", alcoholValue);
                    data.putDouble("gasValue", gasValue);

                    // Navegando para próxima tela
                    Intent nextPage = new Intent(getBaseContext(), ResultPageActivity.class);

                    nextPage.putExtras(data);

                    // Passando para próxima tela
                    startActivity(nextPage);
                }
            }
        };
    }

    private View.OnClickListener onClickBack() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent previousPage = new Intent(getBaseContext(), MainActivity.class);

                startActivity(previousPage);
            }
        };
    }
}