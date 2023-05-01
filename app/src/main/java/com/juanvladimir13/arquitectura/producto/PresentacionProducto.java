package com.juanvladimir13.arquitectura.producto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.juanvladimir13.arquitectura.R;
import com.juanvladimir13.arquitectura.utils.AdminSQLiteOpenHelper;

import java.util.List;

public class PresentacionProducto extends AppCompatActivity {
    private TextView txtId;
    private EditText txtNombre;
    private EditText txtMarca;
    private Button btnNuevo;
    private Button btnGuardar;
    private Button btnEliminar;
    private ListView productoListView;
    private ArrayAdapter<DTOProducto> productoListAdapter;

    private NegocioProducto negocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pproducto);

        txtId = (TextView) findViewById(R.id.producto_id);
        txtNombre = (EditText) findViewById(R.id.producto_nombre);
        txtMarca = (EditText) findViewById(R.id.producto_marca);

        btnNuevo = (Button) findViewById(R.id.btnNuevo);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        productoListView = (ListView) findViewById(R.id.producto_list_view);
        productoListAdapter = new ArrayAdapter<DTOProducto>(this, R.layout.list_item);
        productoListView.setAdapter(productoListAdapter);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                store();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                delete();
            }
        });

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cleanFormData();
            }
        });

        productoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = ((DTOProducto) productoListView.getItemAtPosition(i)).id;
                setFormData(negocio.findRecord(id));
            }
        });

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase db = admin.getWritableDatabase();

        DatoProducto datoProducto = new DatoProducto(db);
        negocio = new NegocioProducto(datoProducto);

        setRowsDataList();
    }

    private void setRowsDataList() {
        List<DTOProducto> list = negocio.rowsList();
        productoListAdapter.clear();

        for (int i = 0; i < list.size(); i++) {
            productoListAdapter.add(list.get(i));
        }
    }

    private void store() {
        negocio.setAttributesData(readFormData());
        DTOProducto dto = negocio.saveRecord();

        if (dto == null) return;

        setFormData(dto);
        setRowsDataList();
    }

    private void delete() {
        negocio.setAttributesData(readFormData());
        negocio.deleteRecord();

        cleanFormData();
        setRowsDataList();
    }

    private DTOProducto readFormData() {
        DTOProducto dto = new DTOProducto(
                txtId.getText().toString(),
                txtNombre.getText().toString(),
                txtMarca.getText().toString()
        );
        return dto;
    }

    private void setFormData(DTOProducto dto) {
        if (dto == null) return;
        txtId.setText(dto.id);
        txtNombre.setText(dto.nombre);
        txtMarca.setText(dto.marca);
    }

    private void cleanFormData() {
        txtId.setText("");
        txtNombre.setText("");
        txtMarca.setText("");
    }
}