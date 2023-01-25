package horizon.interview.horizonchallegeproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Selection;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class sqlClass extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "HorizonProjectDB";
    private static final Integer DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Prontuarios";

    private static final String ID_COL = "id";
    private static final String NOME_COL = "nome";
    private static final String IDADE_COL = "idade";
    private static final String TEMP_COL = "tempe";
    private static final String DIAS_FEBRE_COL = "diasfebre";
    private static final String DIAS_CABECA_COL = "diascabeca";
    private static final String DIAS_TOSSE_COL = "diastosse";
    private static final String VIAJOU_COL = "viajou";
    private static final String TEMPO_VIAGEM_COL = "tempoviagem";
    private static final String FORMA_TRATAMENTO_COL = "formatratamento";

    public sqlClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Cria Tabela
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "  + TABLE_NAME + " ("
                        + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + NOME_COL + " TEXT,"
                        + IDADE_COL + " INTEGER,"
                        + TEMP_COL + " REAL,"
                        + DIAS_FEBRE_COL + " INTEGER,"
                        + DIAS_CABECA_COL + " INTEGER,"
                        + DIAS_TOSSE_COL + " INTEGER,"
                        + VIAJOU_COL + " TEXT,"
                        + TEMPO_VIAGEM_COL + " INTEGER,"
                        + FORMA_TRATAMENTO_COL + " TEXT)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Adiciona os dados do paciente no banco de dados
    public void adicionarPaciente(String nome, Integer idade, Double temp,
                                  Integer diasFebre, Integer dorCabeca, Integer dTosse,
                                  String visitou, Integer semanasVisita, String formaTratamento){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NOME_COL, nome);
        values.put(IDADE_COL, idade);
        values.put(TEMP_COL, temp);
        values.put(DIAS_FEBRE_COL, diasFebre);
        values.put(DIAS_CABECA_COL, dorCabeca);
        values.put(DIAS_TOSSE_COL, dTosse);
        values.put(VIAJOU_COL, visitou);
        values.put(TEMPO_VIAGEM_COL, semanasVisita);
        values.put(FORMA_TRATAMENTO_COL, formaTratamento);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }
}
