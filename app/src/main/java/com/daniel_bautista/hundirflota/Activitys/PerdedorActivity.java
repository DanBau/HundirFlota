package com.daniel_bautista.hundirflota.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daniel_bautista.hundirflota.R;

public class PerdedorActivity extends AppCompatActivity {

    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perdedor);
        btnVolver = (Button) findViewById(R.id.perder_btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent(PerdedorActivity.this, MainActivity.class);
                startActivity(resultIntent);
                finish();
            }
        });
    }
}
