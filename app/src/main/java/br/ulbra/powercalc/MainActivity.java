package br.ulbra.powercalc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTxtNome, editTxtPotencia, editTxtHoras, editTxtPreco;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTxtNome = findViewById(R.id.editTxtNome);
        editTxtPotencia = findViewById(R.id.editTxtPotencia);
        editTxtHoras = findViewById(R.id.editTxtHoras);
        editTxtPreco = findViewById(R.id.editTxtPreco);
        txtResultado = findViewById(R.id.txtResultado);
        Button btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(v -> calcular());
    }

    private void calcular() {

        String nome = editTxtNome.getText().toString().trim();
        String potenciaStr = editTxtPotencia.getText().toString().trim();
        String horasStr = editTxtHoras.getText().toString().trim();
        String precoStr = editTxtPreco.getText().toString().trim();

        if (nome.isEmpty() || potenciaStr.isEmpty() || horasStr.isEmpty() || precoStr.isEmpty()) {
            txtResultado.setText("Por favor, preencha todos os campos!");
            return;
        }

        try {

            double p = Double.parseDouble(potenciaStr);
            double h = Double.parseDouble(horasStr);
            double precoKwh = Double.parseDouble(precoStr);

            // CE = (Potência * Horas) / 1000
            double consumoDiario = (p * h) / 1000;

            // Custo = Consumo * Preço do kWh
            double custoDiario = consumoDiario * precoKwh;

            String resultadoFinal = String.format(
                    "Aparelho: %s\n" +
                            "Consumo Diário: %.2f kWh\n" +
                            "Custo Estimado: R$ %.2f",
                    nome, consumoDiario, custoDiario
            );

            txtResultado.setText(resultadoFinal);

        } catch (NumberFormatException e) {
            txtResultado.setText("Erro: Insira valores numéricos válidos!");
        }
    }
}