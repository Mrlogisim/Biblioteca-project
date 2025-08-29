/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.controller;

import com.mycompany.bibliotecapoo1.model.Miembro;
import com.mycompany.bibliotecapoo1.repository.Repositorio;
import com.mycompany.bibliotecapoo1.service.MiembroServicio;
import com.mycompany.bibliotecapoo1.service.Enrutador;
import com.mycompany.bibliotecapoo1.util.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class LoginController extends Navegacion {

    @FXML 
    private PasswordField txtClave;
    
    @FXML 
    private Label lblMensaje;

    private MiembroServicio miembroServicio;
    private Repositorio repositorio;

    @FXML
    public void login(ActionEvent event) {
        // Inicializar el servicio y repositorio
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        this.miembroServicio = new MiembroServicio(this.repositorio);
        
        String clave = txtClave.getText().trim();
        
        if (clave.isEmpty()) {
            lblMensaje.setText("Por favor ingrese su clave");
            return;
        }
        
        Miembro miembro = miembroServicio.buscarPorClave(clave);
        
        if (miembro != null) {
            Enrutador.cambiarVentana(event, "/com/mycompany/bibliotecapoo1/view/LibroView.fxml");
        } else {
            lblMensaje.setText("Clave incorrecta. Intente nuevamente.");
            txtClave.clear();
        }
    }

    @FXML
    private void initialize() {
        // Inicializar el servicio y repositorio
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        this.miembroServicio = new MiembroServicio(this.repositorio);
        
        // Limpiar mensaje al escribir
        txtClave.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                lblMensaje.setText("");
            }
        });
    }
} // <-- ESTA ES LA LLAVE QUE FALTABA
        
     
