package com.juanvladimir13.arquitectura.categoria;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.juanvladimir13.arquitectura.R;
import com.juanvladimir13.arquitectura.utils.AdminSQLiteOpenHelper;

import java.util.List;

public class VistaCategoria extends AppCompatActivity {
    private TextView txtId;
    private EditText txtDescripcion;
    private EditText txtNombre;
    public Button btnNuevo;
    public Button btnGuardar;
    public Button btnEliminar;
    public ListView categoriaListView;
    private ArrayAdapter<DTOCategoria> categoriaListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vcategoria);

        txtId = (TextView) findViewById(R.id.categoria_id);
        txtDescripcion = (EditText) findViewById(R.id.categoria_descripcion);
        txtNombre = (EditText) findViewById(R.id.categoria_nombre);

        btnNuevo = (Button) findViewById(R.id.btnNuevo);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        categoriaListView = (ListView) findViewById(R.id.categoria_list_view);
        categoriaListAdapter = new ArrayAdapter<DTOCategoria>(this, R.layout.list_item);
        categoriaListView.setAdapter(categoriaListAdapter);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase db = admin.getWritableDatabase();

        ModeloCategoria modeloCategoria = new ModeloCategoria(db);
        ControllerCategoria controllerProducto = new ControllerCategoria(this, modeloCategoria);
    }

    public void setRowsDataList(List<DTOCategoria> list) {
        categoriaListAdapter.clear();
        for (int i = 0; i < list.size(); i++) {
            categoriaListAdapter.add(list.get(i));
        }
    }

    public DTOCategoria readFormData() {
        DTOCategoria dto = new DTOCategoria(
                txtId.getText().toString(),
                txtNombre.getText().toString(),
                txtDescripcion.getText().toString()
        );
        return dto;
    }

    public void setFormData(DTOCategoria dto) {
        if (dto == null) return;
        txtId.setText(dto.id);
        txtNombre.setText(dto.nombre);
        txtDescripcion.setText(dto.descripcion);
    }

    public void cleanFormData() {
        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
    }
}