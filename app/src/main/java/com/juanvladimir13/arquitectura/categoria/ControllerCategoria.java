package com.juanvladimir13.arquitectura.categoria;

import android.view.View;
import android.widget.AdapterView;

public class ControllerCategoria {
    private VistaCategoria vista;
    private ModeloCategoria modelo;

    public ControllerCategoria(VistaCategoria vista, ModeloCategoria modelo) {
        this.vista = vista;
        this.modelo = modelo;
        initListener();
        vista.setRowsDataList(modelo.rowsList());
    }

    private void store() {
        modelo.setAttributesData(vista.readFormData());
        DTOCategoria dto = modelo.saveInDatabase();

        if (dto == null) return;

        vista.setFormData(dto);
        vista.setRowsDataList(modelo.rowsList());
    }

    private void delete() {
        modelo.setAttributesData(vista.readFormData());
        modelo.deleteRecordInDatabase();
        vista.cleanFormData();
        vista.setRowsDataList(modelo.rowsList());
    }

    public void initListener() {
        vista.btnGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                store();
            }
        });

        vista.btnEliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                delete();
            }
        });

        vista.btnNuevo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vista.cleanFormData();
            }
        });

        vista.categoriaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String codigo = ((DTOCategoria) vista.categoriaListView.getItemAtPosition(i)).id;
                vista.setFormData(modelo.findRecordInDatabase("id", codigo));
            }
        });
    }
}
