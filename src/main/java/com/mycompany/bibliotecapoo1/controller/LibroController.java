/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bibliotecapoo1.controller;

import com.mycompany.bibliotecapoo1.model.CategoriaLibro;
import com.mycompany.bibliotecapoo1.model.Libro;
import com.mycompany.bibliotecapoo1.repository.Repositorio;
import com.mycompany.bibliotecapoo1.service.LibroServicio;
import com.mycompany.bibliotecapoo1.util.Conexion;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML; 
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;



public class LibroController implements Initializable {
    
    @FXML
    private Button btnAñadir;
    
    @FXML
    private ComboBox<CategoriaLibro> cmbCategoria;

    @FXML
    private TextField txtAutor;

    @FXML
    private TextField txtEditorial;

    @FXML
    private TextField txtISBN;

    @FXML
    private TextField txtIdioma;

    @FXML
    private TextField txtTitulo;
    
    private LibroServicio libroServicio;
    
    
    // Tabla y columnas
    @FXML private TableView<Libro> tablaLibros;
    @FXML private TableColumn<Libro, Integer> colId;
    @FXML private TableColumn<Libro, String> colTitulo;
    @FXML private TableColumn<Libro, String> colAutor;
    @FXML private TableColumn<Libro, String> colEditorial;
    @FXML private TableColumn<Libro, String> colISBN;
    @FXML private TableColumn<Libro, String> colIdioma;
    @FXML private TableColumn<Libro, CategoriaLibro> colCategoria;
    
    private Repositorio repositorio;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Cargar enum en ComboBox
        cmbCategoria.getItems().setAll(CategoriaLibro.values());

        // ✅ Crear EntityManagerFactory y pasarlo al Repositorio -> en realidad esto esta mal porque la conexión con la base de datos se debe hacer una vez(?) 
        // osea el siguiente codigo se hace solo una vez (por lo que entiendo)
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("IntegradorPoo");
        
        // Para las operaciones CRUD (supongo, no se si esta bien esto)
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        
        // Instanciar servicio con el repositorio
        // libroServicio = new LibroServicio(new Repositorio(emf)); <--- codigo viejo
        libroServicio = new LibroServicio(this.repositorio);
      
        // Configurar columnas
        colId.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colTitulo.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitulo()));
        colAutor.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAutor()));
        colEditorial.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEditorial()));
        colISBN.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getIsbn()));
        colIdioma.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getIdioma()));
        colCategoria.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getCategoria()));

        // Cargar la tabla
        rellenarTabla();

        // listener para detectar el libro de la tabla(?) | NOTA: VER SI PUEDO HACER UN METODO
        // QUE CONTEMPLE ESTE LISTENER Y USAR LA ANOTACIÓN @FXML Y ASOCIAR EL METODO
        tablaLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cargarDatosEnFormulario(newSelection);
            }
        });

        
        
        
        // Acción botón que agrega el libro en la base de datos
        // en realidad no hace falta pues el metodo o función "agregarLibro" 
        // ya esta mapeado con la anotación de @FXML, lo dejo no mas para tenerlo en cuenta
        // btnAñadir.setOnAction(e -> agregarLibro());
    }

    
    private void cargarDatosEnFormulario(Libro libro) {
        txtTitulo.setText(libro.getTitulo());
        txtAutor.setText(libro.getAutor());
        txtEditorial.setText(libro.getEditorial());
        txtISBN.setText(libro.getIsbn());
        txtIdioma.setText(libro.getIdioma());
        cmbCategoria.setValue(libro.getCategoria());
}


@FXML
private void eliminarLibro() {
    Libro seleccionado = tablaLibros.getSelectionModel().getSelectedItem();
    if (seleccionado != null) {
        tablaLibros.getItems().remove(seleccionado);
        libroServicio.eliminarLibro(seleccionado);
        mostrarAlerta("Éxito", "Libro eliminado de la tabla.");
    } else {
        mostrarAlerta("Error", "Debe seleccionar un libro para eliminar.");
    }
}
    

@FXML
private void modificarLibro() {
    Libro seleccionado = tablaLibros.getSelectionModel().getSelectedItem();
    if (seleccionado != null) {
        // Actualizar datos en el objeto
        seleccionado.setTitulo(txtTitulo.getText());
        seleccionado.setAutor(txtAutor.getText());
        seleccionado.setEditorial(txtEditorial.getText());
        seleccionado.setIsbn(txtISBN.getText());
        seleccionado.setIdioma(txtIdioma.getText());
        seleccionado.setCategoria(cmbCategoria.getValue());

        // Refrescar la tabla
        tablaLibros.refresh();

        // Guardar cambios en la BD
        try {
            libroServicio.actualizarLibro(seleccionado);
            mostrarAlerta("Éxito", "Libro modificado correctamente.");
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo modificar en la base de datos.");
            e.printStackTrace();
        }
    } else {
        mostrarAlerta("Error", "Debe seleccionar un libro para modificar.");
    }
}



    
    
    
    private void rellenarTabla() {
        // Obtener todos los expedientes de la base de datos a través del servicio
        List<Libro> libros = libroServicio.obtenerTodos();

        // Convertir la lista de expedientes en una ObservableList
        ObservableList<Libro> listaLibros = FXCollections.observableArrayList(libros);

        // Asignar la lista de expediente al TableView
        tablaLibros.setItems(listaLibros);
    }
    
    
    
    @FXML
    private void agregarLibro() {
        try {
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            String editorial = txtEditorial.getText();
            String isbn = txtISBN.getText();
            String idioma = txtIdioma.getText();
            CategoriaLibro categoria = cmbCategoria.getValue();

            // Validación básica
            if (titulo.isEmpty() || autor.isEmpty() || categoria == null) {
                mostrarAlerta("Error", "Complete los campos obligatorios.");
                return;
            }

            // Crear libro
            Libro libro = new Libro(titulo, autor, editorial, isbn, idioma, categoria);

            // Guardar en DB | VER SI HACE FALTA EL THIS O NO (NO SE PARA QUE SE USA EL THIS)
            this.libroServicio.agregarLibro(libro);

            mostrarAlerta("Éxito", "Libro añadido correctamente.");
            limpiarCampos();
            rellenarTabla();

        } catch (Exception ex) {
            ex.printStackTrace();
            mostrarAlerta("Error", "No se pudo guardar el libro.");
        }
    }

    
    private void limpiarCampos() {
        txtTitulo.clear();
        txtAutor.clear();
        txtEditorial.clear();
        txtISBN.clear();
        txtIdioma.clear();
        cmbCategoria.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
