package com.vidroid.com.br.apps.bancodedadossqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //Criar banco de dados
            SQLiteDatabase bancoDeDados = openOrCreateDatabase("nome_banco_dados",
                    MODE_PRIVATE,null);

            //Criar tabela
            bancoDeDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3)) ");

            //Inserir dados
            //bancoDeDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Jamilton', 30)");
            //bancoDeDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Otavio', 20)");
            //bancoDeDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Gustavo', 18)");


            //Update do registro
            //bancoDeDados.execSQL("UPDATE pessoas SET nome = 'Otavio Miguel' WHERE nome = 'Otavio'");

            //Apagar registro
            bancoDeDados.execSQL("DELETE FROM pessoas WHERE nome = 'Jamilton'");


            //Recuperar dados
            String consulta = "SELECT nome, idade, id FROM pessoas ORDER BY id ASC";
            /* Se usar o "*" ao invés de escolher as colunas, ele pega todas as colunas */

            Cursor cursor = bancoDeDados.rawQuery(consulta, null);

            //Recuperar indice da coluna
            int indiceColunaID = cursor.getColumnIndex("id");
            int indiceColunaNome = cursor.getColumnIndex("nome");
            int indiceColunaIdade = cursor.getColumnIndex("idade");

            for(cursor.moveToFirst(); cursor != null; cursor.moveToNext()){
                Log.i("RESULTADO - nome: ", cursor.getString(indiceColunaNome));
                Log.i("RESULTADO - idade: ", cursor.getString(indiceColunaIdade));
                Log.i("RESULTADO - id: ", cursor.getString(indiceColunaID));
            }

            //Apagar tabela por completo
            //bancoDeDados.execSQL("DROP TABLE pessoas");


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
