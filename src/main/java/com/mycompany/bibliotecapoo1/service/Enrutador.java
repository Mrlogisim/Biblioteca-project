/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.service;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.control.Alert;


/**
 *
 * @author mdcin
 */

public class Enrutador {
    
    public static void cambiarVentana(ActionEvent event, String rutaFXML) {
        try {
            // Asegurarse de que la ruta tenga la extensión .fxml
            if (!rutaFXML.endsWith(".fxml")) {
                rutaFXML += ".fxml";
            }
            
            FXMLLoader loader = new FXMLLoader(Enrutador.class.getResource(rutaFXML));
            
            // Verificar que el archivo existe
            if (loader.getLocation() == null) {
                throw new IOException("No se pudo encontrar el archivo: " + rutaFXML);
            }
            
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            
            // Preservar el tamaño y posición de la ventana
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException e) {
            System.err.println("Error al cargar la ventana: " + rutaFXML);
            e.printStackTrace();
            
            // Mostrar alerta al usuario
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo cargar la ventana");
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }
    
    // Método sobrecargado para mayor flexibilidad
    public static void cambiarVentana(Node node, String rutaFXML) {
        try {
            if (!rutaFXML.endsWith(".fxml")) {
                rutaFXML += ".fxml";
            }
            
            FXMLLoader loader = new FXMLLoader(Enrutador.class.getResource(rutaFXML));
            Parent root = loader.load();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
