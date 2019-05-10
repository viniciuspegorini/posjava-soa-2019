package pb.utfpr.edu.br.pw26s.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import pb.utfpr.edu.br.pw26s.R;
import pb.utfpr.edu.br.pw26s.model.Genero;

import pb.utfpr.edu.br.pw26s.model.TokenResponse;
import pb.utfpr.edu.br.pw26s.security.ServiceGenerator;
import pb.utfpr.edu.br.pw26s.service.GeneroService;
import retrofit2.Call;

public class ListaGeneroActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_genero);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lista = (ListView) findViewById(R.id.listaC);

        FloatingActionButton btnCadastrar = (FloatingActionButton) findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadastroCategoria();
            }
        });
        carregarDados();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Genero categoria = (Genero) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListaGeneroActivity.this, CadastroGeneroActivity.class);
                intent.putExtra("codigo", categoria.getId().toString());
                startActivity(intent);
            }
        });
    }

    public void abrirCadastroCategoria(){
        Intent intent = new Intent(this, CadastroGeneroActivity.class);
        startActivity(intent);
    }

    private void carregarDados(){
        try {
            GeneroService generoService = ServiceGenerator
                    .createService(GeneroService.class,
                            TokenResponse.getTokenSharedPreferences(this));
            Call<List<Genero>> call = generoService.getAll();
            List<Genero> generos = call.execute().body();

            ArrayAdapter<Genero> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, generos);
            lista.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(this, "Falha ao listar dados!" + e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
