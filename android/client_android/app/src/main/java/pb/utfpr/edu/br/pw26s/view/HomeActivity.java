package pb.utfpr.edu.br.pw26s.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pb.utfpr.edu.br.pw26s.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnGenero = (Button) findViewById(R.id.btnGenero);
        btnGenero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testarGenero();
            }
        });

    }

    public void testarGenero(){
        Intent intent = new Intent(this, ListaGeneroActivity.class);
        startActivity(intent);
    }

}
