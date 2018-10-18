package simulador.com.simuladordeinvestimento;

import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText capInicial, aplicMensal, perAno, taxaJuros;
    private TextView tAcumulado;
    private Button bCalcular, bReset;

    private String sCap, sMensal, sPer, sJur;
    private double cap = 0;
    private double mensal = 0;
    private double per = 0;
    private double jur = 0;
    private double mont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        capInicial = (EditText) findViewById(R.id.capInicial);
        aplicMensal = (EditText) findViewById(R.id.aplicMensal);
        perAno = (EditText) findViewById(R.id.perAno);
        taxaJuros = (EditText) findViewById(R.id.taxaJuros);
        tAcumulado = (TextView) findViewById(R.id.tAcumulado);
        bCalcular = (Button) findViewById(R.id.bCalcular);
        bReset = (Button) findViewById(R.id.bReset);

        bCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sCap = capInicial.getText().toString();
                sMensal = aplicMensal.getText().toString();
                sPer = perAno.getText().toString();
                sJur = taxaJuros.getText().toString();

                if (!sCap.isEmpty()){
                    if (!sMensal.isEmpty()){
                        if (!sPer.isEmpty()){
                            if (!sJur.isEmpty()){

                                calcular();

                            }else{
                                Toast.makeText(MainActivity.this, "Qual a rentabilidade ao ano da aplicação", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(MainActivity.this, "Coloque o prazo da aplicação", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this, "Se não for fazer aplicação mensal, coloque 0", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Digite o investimento inicial", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sCap = capInicial.getText().toString();
                sMensal = aplicMensal.getText().toString();
                sPer = perAno.getText().toString();
                sJur = taxaJuros.getText().toString();

                if (!sCap.isEmpty()){
                    if (!sMensal.isEmpty()){
                        if (!sPer.isEmpty()){
                            if (!sJur.isEmpty()){

                                reset();

                            }
                        }
                    }
                }
            }
        });

    }

    private void calcular() {
        DecimalFormat df = new DecimalFormat(",##0.00");

        cap = Double.parseDouble(sCap.replace(',', '.'));
        mensal = Double.parseDouble(sMensal.replace(',','.'));
        per = Double.parseDouble(sPer.replace(',','.'));
        jur = Double.parseDouble(sJur.replace(',','.'))/100;

        /* Fórmula para converter o juros anual em juros mensal */
        double a = 1;
        double b = 12;
        double anoMes = Math.pow(1 + jur, a/b)-1;

        /* Fórmula do juros compostos */
        mont = cap * Math.pow(1 + anoMes, per);
        String fmt = df.format(mont);

        tAcumulado.setText("R$ " + fmt);
    }

    private void reset() {
        capInicial.setText("");
        aplicMensal.setText("");
        perAno.setText("");
        taxaJuros.setText("");
        tAcumulado.setText("");
    }

}
