package horizon.interview.horizonchallegeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FinalPage extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_page);

        configureFinishButton();
        alterText();
    }

    public void alterText(){

        String nomelocal = getIntent().getStringExtra("nome");
        String idadelocal = getIntent().getStringExtra("idade");
        String tempLocal = getIntent().getStringExtra("temp");
        String dFebreLocal = getIntent().getStringExtra("diasFebre");
        String dTosseLocal = getIntent().getStringExtra("dTosse");
        String dorCabecaLocal = getIntent().getStringExtra("dorCabeca");
        String visitouLocal = getIntent().getStringExtra("visitou");
        String formaDeTratamentoLocal = getIntent().getStringExtra("formaTratamento");

        TextView textNome = findViewById(R.id.textNome);
        TextView textIdade = findViewById(R.id.textIdade);
        TextView textTemp = findViewById(R.id.textTemperatura);
        TextView textProntuario = findViewById(R.id.textProntuário);
        TextView textFebre = findViewById(R.id.textDiasFebre);
        TextView textdorCabeca = findViewById(R.id.textDiasCabeca);
        TextView textTosse = findViewById(R.id.textDiasTosse);
        TextView textViajou = findViewById(R.id.textViajouPaisSinalizado);

        textNome.setText(nomelocal);
        textIdade.setText(idadelocal);
        textTemp.setText(tempLocal);
        textProntuario.setText(formaDeTratamentoLocal);
        textFebre.setText(dFebreLocal);
        textdorCabeca.setText(dorCabecaLocal);
        textTosse.setText(dTosseLocal);
        textViajou.setText(visitouLocal);
    }


    public void configureFinishButton(){
        Button finishButton = (Button) findViewById(R.id.buttonFinish);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalPage.super.finish();
            }
        });

    }
}