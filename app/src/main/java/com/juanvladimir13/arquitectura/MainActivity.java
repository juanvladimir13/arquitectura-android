package com.juanvladimir13.arquitectura;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.juanvladimir13.arquitectura.categoria.VistaCategoria;
import com.juanvladimir13.arquitectura.producto.PresentacionProducto;

public class MainActivity extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.categoria) {
            Intent intent = new Intent(this, VistaCategoria.class);
            startActivity(intent);
        } else if (id == R.id.producto) {
            Intent intent = new Intent(this, PresentacionProducto.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}