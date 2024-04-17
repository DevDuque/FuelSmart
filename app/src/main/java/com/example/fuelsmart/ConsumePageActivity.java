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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnSubmit = (Button) findViewById(R.id.btn_next);
        btnSubmit.setOnClickListener(onClickSubmit());

        Button btnBack = (Button) findViewById(R.id.btn_back);
        btnSubmit.setOnClickListener(onClickBack());
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

                String alcoholValue = alcoholInput.getText().toString();
                String gasValue = gasInput.getText().toString();

                // Garantindo que os campos foram inseridos
                if(alcoholValue.isEmpty() || gasValue.isEmpty()) {
                    alert("Por favor insira um valor válido para os campos");
                } else {
                    // Navegando para próxima tela
                    Intent nextPage = new Intent(getBaseContext(), ResultPageActivity.class);

                    // Salvando os preços
                    Bundle values = new Bundle();
                    values.putString("alcoholValue", alcoholValue);
                    values.putString("gasValue", gasValue);
                    nextPage.putExtras(values);

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
                Intent previousPage = new Intent(getBaseContext(), ConsumePageActivity.class);

                startActivity(previousPage);
            }
        };
    }
}