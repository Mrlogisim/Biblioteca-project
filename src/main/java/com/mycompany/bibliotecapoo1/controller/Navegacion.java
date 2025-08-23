/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.controller;

import com.mycompany.bibliotecapoo1.service.Enrutador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
/**
 *
 * @author mdcin
 */
public class Navegacion {

    //Ubicar en ventana "Libros"
    @FXML
    public void tablaLibros(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/View/LibrosView.fxml");
    }
    
}
