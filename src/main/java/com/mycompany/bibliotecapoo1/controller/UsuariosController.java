/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.controller;

import com.mycompany.bibliotecapoo1.model.Usuario;
import com.mycompany.bibliotecapoo1.repository.Repositorio;
import com.mycompany.bibliotecapoo1.service.MiembroServicio;
import com.mycompany.bibliotecapoo1.util.Conexion;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UsuariosController extends Navegacion{

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtClave;
    @FXML private ComboBox<String> cmbActivo;

    @FXML private TableView<Usuario> tablaUsuarios;
    @FXML private TableColumn<Usuario, Integer> colId;
    @FXML private TableColumn<Usuario, String> colClave;
    @FXML private TableColumn<Usuario, String> colNombre;
    @FXML private TableColumn<Usuario, String> colApellido;
    @FXML private TableColumn<Usuario, String> colActivo;

    private Repositorio repositorio;
    private MiembroServicio miembroServicio; 
    private ObservableList<Usuario> usuariosObservable;

    
    public UsuariosController() {
        // Constructor vacío para FXML
    }
   

    @FXML
    public void initialize() {
        
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        this.miembroServicio = new MiembroServicio(this.repositorio);
        
        
        // Inicializar combo de estado
        cmbActivo.setItems(FXCollections.observableArrayList("SI", "NO"));

        // Configuración columnas tabla
        colId.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getId()).asObject());
        colClave.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getClave()));
        colNombre.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colApellido.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getApellido()));
        colActivo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().isActivo() ? "SI" : "NO"));

        // Cargar datos iniciales
        usuariosObservable = FXCollections.observableArrayList(miembroServicio.obtenerUsuarios());
        tablaUsuarios.setItems(usuariosObservable);

        // Al seleccionar un usuario, mostrar en los campos
        tablaUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtNombre.setText(newSel.getNombre());
                txtApellido.setText(newSel.getApellido());
                txtClave.setText(newSel.getClave());
                cmbActivo.setValue(newSel.isActivo() ? "SI" : "NO");
            }
        });
    }

    @FXML
    private void añadirUsuario() {
        Usuario u = new Usuario();
        u.setNombre(txtNombre.getText());
        u.setApellido(txtApellido.getText());
        u.setClave(txtClave.getText());
        u.setActivo("SI".equals(cmbActivo.getValue()));

        miembroServicio.guardar(u);
        refrescarTabla();
        limpiarCampos();
    }

    @FXML
    private void modificarUsuario() {
        Usuario seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            seleccionado.setNombre(txtNombre.getText());
            seleccionado.setApellido(txtApellido.getText());
            seleccionado.setClave(txtClave.getText());
            seleccionado.setActivo("SI".equals(cmbActivo.getValue()));

            miembroServicio.actualizar(seleccionado);
            refrescarTabla();
            limpiarCampos();
        }
    }

    @FXML
    private void eliminarUsuario() {
        Usuario seleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            miembroServicio.eliminar(seleccionado);
            refrescarTabla();
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtClave.clear();
        cmbActivo.setValue(null);
        tablaUsuarios.getSelectionModel().clearSelection();
    }

    private void refrescarTabla() {
        usuariosObservable.setAll(miembroServicio.obtenerUsuarios());
    }
}

