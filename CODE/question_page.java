package horizon.interview.horizonchallegeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class question_page extends AppCompatActivity {

    private sqlClass db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);

        configureBackButton();
        configureNextButton();

    }


    private void configureBackButton(){
        Button backButton = (Button) findViewById(R.id.buttonVoltar);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void configureNextButton(){

        Button nextButton = (Button) findViewById(R.id.buttonProximo);
        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){

                db = new sqlClass(question_page.this);

                /*Nome do paciente*/
                EditText nomeS = findViewById(R.id.nomePaciente);
                String nome = nomeS.getText().toString();

                /*Idade do paciente*/
                EditText idadeTxt = findViewById(R.id.idadePaciente);
                String idadeS = idadeTxt.getText().toString();
                Integer idade = Integer.parseInt(idadeS);

                /*Temperatura do paciente*/
                EditText tempTxt = findViewById(R.id.temperaturaPaciente);
                String tempS = tempTxt.getText().toString();
                Double temp = Double.parseDouble(tempS);

                /*paciente teve febre, se teve pergunta por quantos dias ele teve febre*/
                EditText dFebreTxt = findViewById(R.id.diasFebrePaciente);
                String dFebreS = dFebreTxt.getText().toString();
                Integer diasFebre = Integer.parseInt(dFebreS);

                /*Tempo que o paciente esta tossindo*/
                EditText dTosseTxt = findViewById(R.id.diasTossePaciente);
                String dTosseS = dTosseTxt.getText().toString();
                Integer dTosse = Integer.parseInt(dTosseS);

                /*Tempo que o paciente sente dor de cabeça*/
                EditText dCabecaTxt = findViewById(R.id.diasCabecaPaciente);
                String dCabecaS = dCabecaTxt.getText().toString();
                Integer dorCabeca =  Integer.parseInt(dCabecaS);

                /*Checa se o paciente visitou algum dos paises sinalizados*/
                EditText visitouS = findViewById(R.id.confirmaçãoViagem);
                String visitou = visitouS.getText().toString();

                EditText sVisitasTxt = findViewById(R.id.dataViagem);
                String sVisitasS = sVisitasTxt.getText().toString();
                Integer semanasVisita = Integer.parseInt(sVisitasS);

                String formaTratamento = "";

                if(visitou.equals("Sim") && semanasVisita <= 6 && temp > 37){
                    /* Decide se o paciente deve ser internado */
                    if(dorCabeca > 5 && dTosse > 5){
                        formaTratamento = "Internado";
                    }
                }else if(visitou.equals("Sim") && semanasVisita <= 6 && idade > 10 && idade < 60){
                    /* Diz que o paciente vai para a quarentena por ter visitado os países mesmo sem sintomas */
                    if(dTosse >= 0 && dorCabeca >=0 && temp <= 37){
                        formaTratamento = "Quarentena";
                    }
                }else if(idade > 60 || idade < 10 && temp > 37){
                    /* Diz que o paciente vai para a quarentena se tiver mais que 60 ou menos que 10 caso apresente sintomas */
                    if(diasFebre > 3 || dorCabeca > 3 || dTosse > 5){
                        formaTratamento = "Quarentena";
                    }
                }else if(idade > 10 && idade < 60 && temp > 37){
                    /* Diz que o paciente vai para a quarentena caso tenha entre 10 e 60 anos e apresente sintomas */
                    if(dorCabeca > 5 && dTosse > 5){
                        formaTratamento = "Quarentena";
                    }
                }else if(visitou.equals("Não") && idade > 10 && idade < 60){
                    /* Caso o paciente não se encaixe em nenhum dos casos acima ele será liberado */
                    if(dorCabeca <= 5 && dTosse <= 5){
                        formaTratamento = "Liberado";
                    }
                } else if (visitou.equals("Não") && idade < 10 || idade > 60 && temp <= 37) {
                    if(dorCabeca <= 3 && dTosse <= 5 & diasFebre <= 3){
                        formaTratamento = "Liberado";
                    }
                }

                db.adicionarPaciente(nome, idade, temp, diasFebre, dTosse, dorCabeca, visitou,
                                     semanasVisita, formaTratamento);

                System.out.println(formaTratamento);
                Intent i = new Intent(question_page.this, FinalPage.class);
                i.putExtra("nome", nome);
                i.putExtra("idade", idade.toString());
                i.putExtra("temp", temp.toString());
                i.putExtra("diasFebre", diasFebre.toString());
                i.putExtra("dTosse", dTosse.toString());
                i.putExtra("dorCabeca", dorCabeca.toString());
                i.putExtra("visitou", visitou);
                i.putExtra("formaTratamento", formaTratamento);
                startActivity(i);
                //startActivity(new Intent(question_page.this, PacienteCadastradoPreviamente.class));
            }
        });

    }

}