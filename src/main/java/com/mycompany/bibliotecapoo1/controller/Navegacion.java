/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.controller;

import com.mycompany.bibliotecapoo1.service.Enrutador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Navegacion {

    @FXML
    public void irLibros(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/mycompany/bibliotecapoo1/view/LibroView");
    }

    @FXML
    public void irUsuarios(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/mycompany/bibliotecapoo1/view/UsuariosView");
    }

    @FXML
    public void irBibliotecarios(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/mycompany/bibliotecapoo1/view/BibliotecariosView");
    }
    
    @FXML
    public void irPrestamos(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/mycompany/bibliotecapoo1/view/PrestamosView");
    }
    
}

