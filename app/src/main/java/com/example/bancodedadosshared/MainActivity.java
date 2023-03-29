package com.example.bancodedadosshared;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnSalvar;
    private EditText editNome;
    private TextView txtResultado;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferenca";//usar dessa forma para defenir o nome do arquivo a se salvo no cell

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSalvar = findViewById(R.id.btnSalvar);
        editNome = findViewById(R.id.editNome);
        txtResultado = findViewById(R.id.txtResultNome);

        //Salvando dados, ele não e um banco de dados, e um arquivo usado para salvar dados pequenos, tipo cor escolhida, nome... 0 significa privado ninguem de fora pode alterar
        //criando a instancia
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);//aqui ele pedi pra da o nome do arquivo a ser criado e a forma q vai ser
        SharedPreferences.Editor editor = preferences.edit();//dessa forma da pra editar o arquivo agr

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editNome.getText().toString().isEmpty()){
                    //salvando os dados digitado pelo usuario
                    //para editar os dados e salvar os dados e usado o editor, para recuperar os dados e usado o shared
                    editor.putString("nome",editNome.getText().toString());//primeiro e o nome da chave, depois o dado a ser salvo
                    editor.commit();//aqui ele salva difinitivamente o dado no cell
                }else{
                    Toast.makeText(MainActivity.this, "Preencha o nome", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //recuperando os dados salvo no shared
        //validando se tem o dado (nome) salvo em preferencia
        //aqui ele verifica se tem essa chave criada no cell do usuario
        if (preferences.contains("nome")){
            //mostrando os dados recuperado
            String nomeRecuperado = preferences.getString("nome","Usuario não salvou nenhum dado");//primeiro passa a chave que quer ser recuperada, depois um valo caso não seja possivel recuperar os dados
            txtResultado.setText("Òla, "+nomeRecuperado);

        }else{
            txtResultado.setText("Usuario não salvou nenhum dado");
        }
    }
}