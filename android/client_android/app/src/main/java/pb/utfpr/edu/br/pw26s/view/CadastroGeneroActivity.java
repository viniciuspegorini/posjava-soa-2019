package pb.utfpr.edu.br.pw26s.view;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pb.utfpr.edu.br.pw26s.R;
import pb.utfpr.edu.br.pw26s.model.Genero;
import pb.utfpr.edu.br.pw26s.model.TokenResponse;
import pb.utfpr.edu.br.pw26s.security.ServiceGenerator;
import pb.utfpr.edu.br.pw26s.service.GeneroService;
import retrofit2.Call;

public class CadastroGeneroActivity extends AppCompatActivity {

    private TextView txtId;
    private TextView txtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_genero);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        txtId = (TextView) findViewById(R.id.edtId);
        txtNome = (TextView) findViewById(R.id.edtNome);

        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gravarDados();
            }
        });
        if (this.getIntent().getStringExtra("codigo") != null){
            editar();
            btnSalvar.setText("Alterar");
            Button btnExcluir = (Button) findViewById(R.id.btnExcluir);
            btnExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removerRegistro();
                }
            });
            btnExcluir.setVisibility(View.VISIBLE);
        }
    }

    private void removerRegistro() {
        try {
            Long codigo = Long.parseLong(
                    this.getIntent().getStringExtra("codigo"));
            GeneroService categoriaService = ServiceGenerator
                    .createService(GeneroService.class,
                            TokenResponse.getTokenSharedPreferences(this));
            Call<Void> call = categoriaService.delete(codigo);
            call.execute().body();
            Toast.makeText(this, "Registro removido com sucesso!", Toast.LENGTH_SHORT).show();
            abrirListaGenero();
        }catch (Exception ex){
            Toast.makeText(this, "Falha ao remover registro!" + ex.getMessage(), Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }
    }

    public void editar() {
        try {
            Long codigo = Long.parseLong(
                    this.getIntent().getStringExtra("codigo"));
            GeneroService categoriaService = ServiceGenerator
                    .createService(GeneroService.class,
                            TokenResponse.getTokenSharedPreferences(this));
            Call<Genero> call = categoriaService.getOne(codigo);
            Genero categoria = call.execute().body();
            txtId.setText(categoria.getId().toString());
            txtNome.setText(categoria.getNome());
        }catch (Exception ex){
            Toast.makeText(this, "Falha ao carregar os dados!" + ex.getMessage(), Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }
    }

    public void gravarDados(){
        try {
            Genero genero = new Genero();
            if (txtId.getText().toString() != "" &&
                    ! txtId.getText().toString().isEmpty() ){
                genero.setId( Integer.parseInt(txtId.getText().toString()));
            }
            genero.setNome(txtNome.getText().toString());

            GeneroService categoriaService = ServiceGenerator
                    .createService(GeneroService.class,
                            TokenResponse.getTokenSharedPreferences(this));
            Call<Void> call;
            call = categoriaService.save(genero);
            call.execute().body();

            Toast.makeText(this, "Registro salvo com sucesso!", Toast.LENGTH_SHORT).show();
            abrirListaGenero();
        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }
    }

    public void abrirListaGenero(){
        Intent intent = new Intent(this, ListaGeneroActivity.class);
        startActivity(intent);
        finish();
    }
}
