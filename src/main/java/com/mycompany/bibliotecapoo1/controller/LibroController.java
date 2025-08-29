/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.bibliotecapoo1.controller;

import com.mycompany.bibliotecapoo1.model.CategoriaLibro;
import com.mycompany.bibliotecapoo1.model.CopiaLibro;
import com.mycompany.bibliotecapoo1.model.EstadoCopia;
import com.mycompany.bibliotecapoo1.model.Libro;
import com.mycompany.bibliotecapoo1.model.Rack;
import com.mycompany.bibliotecapoo1.model.TipoCopia;
import com.mycompany.bibliotecapoo1.repository.Repositorio;
import com.mycompany.bibliotecapoo1.service.CopiaLibroServicio;
import com.mycompany.bibliotecapoo1.service.Enrutador;
import com.mycompany.bibliotecapoo1.service.LibroServicio;
import com.mycompany.bibliotecapoo1.service.RackServicio;
import com.mycompany.bibliotecapoo1.util.Conexion;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import java.net.URL;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML; 
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;



public class LibroController extends Navegacion {
    
    // Tabla y columnas para la tab Libros
    
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
 
    
    @FXML private TableView<Libro> tablaLibros;
    @FXML private TableColumn<Libro, Integer> colId;
    @FXML private TableColumn<Libro, String> colTitulo;
    @FXML private TableColumn<Libro, String> colAutor;
    @FXML private TableColumn<Libro, String> colEditorial;
    @FXML private TableColumn<Libro, String> colISBN;
    @FXML private TableColumn<Libro, String> colIdioma;
    @FXML private TableColumn<Libro, CategoriaLibro> colCategoria;
    
    
    
    // Tabla y columnas para la tab Racks
    @FXML private TextField txtUbicacion;
    @FXML private Button btnAñadirRack, btnModificarRack, btnEliminarRack;
    @FXML private TableView<Rack> tablaRacks;
    @FXML private TableColumn<Rack, Integer> colRackId;
    @FXML private TableColumn<Rack, String> colRackUbicacion;

    @FXML private TableView<CopiaLibro> tablaCopias; // PERTENECE A LA PANTALLA TAB DE RACKS
    @FXML private TableColumn<CopiaLibro, Integer> colCopiaId;
    @FXML private TableColumn<CopiaLibro, String> colCopiaLibro;
    @FXML private TableColumn<CopiaLibro, String> colCopiaTipo;
    @FXML private TableColumn<CopiaLibro, String> colCopiaEstado;
    
    
     // Tabla de copias de un rack (pantalla de racks)
    @FXML private TableView<CopiaLibro> tablaCopiasRack;
    @FXML private TableColumn<CopiaLibro, Integer> columCopiaId;
    @FXML private TableColumn<CopiaLibro, String> columCopiaLibro;
    @FXML private TableColumn<CopiaLibro, String> columCopiaTipo;
    @FXML private TableColumn<CopiaLibro, String> columCopiaEstado;
    
    
    
    
    
    
    // Tabla y columnas del tab Copias 
    @FXML private ComboBox<Rack> cmbRack;
    @FXML private ComboBox<Libro> cmbLibro;
    @FXML private ComboBox<TipoCopia> cmbTipo;
    @FXML private RadioButton rbSi;
    @FXML private RadioButton rbNo;
    @FXML private Button btnAñadirCopia;
    
    @FXML private TableView<CopiaLibro> tablaCopiasLibros; // PERTENECE A LA PANTALLA TAB DE COPIAS
    @FXML private TableColumn<CopiaLibro, Integer> colIdCopia;
    @FXML private TableColumn<CopiaLibro, String> colLibro;
    @FXML private TableColumn<CopiaLibro, String> colTipo;
    @FXML private TableColumn<CopiaLibro, String> colRack;
    @FXML private TableColumn<CopiaLibro, String> colEstado;
    @FXML private TableColumn<CopiaLibro, Boolean> colReferencia;

    private ObservableList<CopiaLibro> copiasObservable;
    
    private ObservableList<Libro> listaLibrosObservable;
    private ObservableList<Rack> listaRacksObservable;
    
    
    

    // TABLA DE INFORMACIÓN DE UN LIBRO RESPECTO A LA PANTALLA COPIAS
    @FXML
    private TableView<Libro> tablaInfoLibro; 
    
    @FXML
    private TableColumn<Libro, Integer> colTCIdLibro;
    @FXML
    private TableColumn<Libro, String> colTCTitulo;
    @FXML
    private TableColumn<Libro, String> colTCAutor;
    @FXML
    private TableColumn<Libro, String> colTCEditorial;
    @FXML
    private TableColumn<Libro, String> colTCCategoria;
    @FXML
    private TableColumn<Libro, String> colTCISBN;
    @FXML
    private TableColumn<Libro, String> colTCIdioma;
    

    
    
    
    
    
    
    // Repositorio y servicios
    private Repositorio repositorio;
    private RackServicio rackServicio;
    private CopiaLibroServicio copiaServicio;
    private LibroServicio libroServicio;
  
    
    
    @FXML
    public void initialize() {
        // Cargar enum en ComboBox
        cmbCategoria.getItems().setAll(CategoriaLibro.values());

        // ✅ Crear EntityManagerFactory y pasarlo al Repositorio -> en realidad esto esta mal porque la conexión con la base de datos se debe hacer una vez(?) 
        // osea el siguiente codigo se hace solo una vez (por lo que entiendo)
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("IntegradorPoo");
        
        // Para las operaciones CRUD (supongo, no se si esta bien esto)
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        
        // Instanciar servicio con el repositorio
        // libroServicio = new LibroServicio(new Repositorio(emf)); <--- codigo viejo
        this.libroServicio = new LibroServicio(this.repositorio);
        
        this.listaLibrosObservable = FXCollections.observableArrayList();
        
        this.listaRacksObservable = FXCollections.observableArrayList();
        
        
        
        // VER SI DEJAR ESTO O NO
        this.copiaServicio = new CopiaLibroServicio(this.repositorio);
        
      
        // Configurar columnas de la tabla LIBROS
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
        
        
        tablaRacks.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                cargarCopiasDelRack(newSel);
            }
        });
        
        
        // Configurar columnas de la tabla de información
        configurarColumnasInfoLibro();
        
        // Listener para cuando se selecciona un libro en el ComboBox
        cmbLibro.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldLibro, newLibro) -> {
                if (newLibro != null) {
                    mostrarLibroEnTabla(newLibro);
                }
            }
        );

        
        configurarPantallaRack();
        
        //configurarTablaCopias();
        
        configurarComboLibros();
        
        configurarComboRacks();
        
        configurarPantallaCopias();
        
        validarRestricciones();
        
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

    private void cargarTextField(Rack rack) {
        txtUbicacion.setText(rack.getUbicacion());
}

@FXML
private void eliminarLibro() {
    Libro seleccionado = tablaLibros.getSelectionModel().getSelectedItem();
    if (seleccionado != null) {
        tablaLibros.getItems().remove(seleccionado);
        libroServicio.eliminarLibro(seleccionado);
        actualizarComboLibros(); // PARTE DE LA PANTALLA COPIAS
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
            
            // PARTE DE LA PANTALLA COPIAS | ComboBox Libro
            actualizarComboLibros(); 
            
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
            this.libroServicio.agregarLibro(libro); // parametros en el servicio

            mostrarAlerta("Éxito", "Libro añadido correctamente.");
            limpiarCampos();
            rellenarTabla();
          
            // ✅ ACTUALIZAR COMBOBOX de Libros en la pantalla Copias después de agregar libro
            actualizarComboLibros();

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
    
    //-------------------------------------------------------------------------------------------
    
    // TableView Racks: 
    
    //ABM (Alta, baja, y modificación) 
        
    @FXML
    private void agregarRack() {
        try {
            String ubicacion = txtUbicacion.getText().trim();

            if (ubicacion.isEmpty()) {
                mostrarAlerta("Error", "Complete la ubicación del rack.");
                return;
            }

            Rack rack = new Rack();
            rack.setUbicacion(ubicacion);

            rackServicio.agregarRack(rack);
            rellenarTablaRacks();
            txtUbicacion.clear();

            // ✅ ACTUALIZAR COMBOBOX de Racks en la pantalla Copias después de agregar rack
            actualizarComboRacks();
            
            mostrarAlerta("Éxito", "Rack agregado correctamente.");

        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo agregar el rack: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    @FXML
    private void eliminarRack() {
        Rack rackSel = tablaRacks.getSelectionModel().getSelectedItem();

        if (rackSel != null) {
            rackServicio.eliminarRack(rackSel);
            rellenarTablaRacks();
            tablaCopias.getItems().clear();
           
            // ✅ ACTUALIZAR COMBOBOX de Racks en la pantalla Copias después de agregar rack
            actualizarComboRacks();
            
            mostrarAlerta("Éxito", "Rack eliminado de la tabla.");
        } else {
            mostrarAlerta("Error", "Debe seleccionar un rack para eliminar.");
        }
        
    }
    
    
    
    @FXML
    private void modificarRack() {
        Rack rackSeleccionado = tablaRacks.getSelectionModel().getSelectedItem();
        if (rackSeleccionado != null) {
            // Actualizar datos en el objeto
            rackSeleccionado.setUbicacion(txtUbicacion.getText());

            // Refrescar la tabla
            tablaRacks.refresh();

            // Guardar cambios en la BD
            try {
                rackServicio.actualizarRack(rackSeleccionado);
                
                // ✅ ACTUALIZAR COMBOBOX de Racks en la pantalla Copias después de agregar rack
                actualizarComboRacks();
                
                mostrarAlerta("Éxito", "Rack modificado correctamente.");
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo modificar en la base de datos.");
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Error", "Debe seleccionar un rack para modificar.");
        }
    }
    
    
    
    
    
    
    // Para las tablas del TableView de Racks
    
    private void rellenarTablaRacks() {
        List<Rack> racks = rackServicio.obtenerTodos();
        ObservableList<Rack> listaRacks = FXCollections.observableArrayList(racks);
        // tablaRacks.setItems(FXCollections.observableArrayList(racks)); <--- CODIGO ANTIGUO
        tablaRacks.setItems(listaRacks); 
    }

    private void rellenarTablaCopias(Rack rack) {
        List<CopiaLibro> copias = copiaServicio.obtenerPorRack(rack);
        tablaCopias.setItems(FXCollections.observableArrayList(copias));
    }
    
    
    // Configuración de la pantalla de Racks
    
    private void configurarPantallaRack() {
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        this.rackServicio = new RackServicio(this.repositorio);
        this.copiaServicio = new CopiaLibroServicio(this.repositorio);

        // columnas Rack
        colRackId.setCellValueFactory(cd -> new SimpleIntegerProperty(cd.getValue().getId()).asObject());
        colRackUbicacion.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getUbicacion()));

        // columnas Copias
        colCopiaId.setCellValueFactory(cd -> new SimpleIntegerProperty(cd.getValue().getId()).asObject());
        colCopiaLibro.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getLibro().getTitulo()));
        colCopiaTipo.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getTipo().toString()));
        colCopiaEstado.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue().getEstado().toString()));

        // cargar racks iniciales
        rellenarTablaRacks();

        
    }
    
    private void cargarCopiasDelRack(Rack rack) {
        try {
            
            // Obtener todas las copias asociadas a este rack
            List<CopiaLibro> copias = copiaServicio.obtenerPorRack(rack);

            // Convertir a ObservableList
            ObservableList<CopiaLibro> copiasObservable = FXCollections.observableArrayList(copias);

            // Mostrar en la tabla
            tablaCopiasRack.setItems(copiasObservable);


        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar las copias: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    
    // cuando seleccionás un rack → cargar copias
    /*
    @FXML
    private void mostrarCopias (){
        tablaRacks.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                rellenarTablaCopias(newSel);
            }
        });
        
    }
    */
    
    
    /*
    @FXML
    private void verLibrosEnRack() {
        Rack rackSeleccionado = tablaRacks.getSelectionModel().getSelectedItem();

        if (rackSeleccionado == null) {
            mostrarAlerta("Error", "Seleccione un rack primero.");
            return;
        }

        cargarCopiasDelRack(rackSeleccionado);
    }

    private void cargarCopiasDelRack(Rack rack) {
        try {
            // Obtener todas las copias asociadas a este rack
            List<CopiaLibro> copias = copiaServicio.obtenerPorRack(rack);

            // Convertir a ObservableList
            ObservableList<CopiaLibro> copiasObservable = FXCollections.observableArrayList(copias);

            // Mostrar en la tabla
            tablaCopiasRack.setItems(copiasObservable);


        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar las copias: " + e.getMessage());
            e.printStackTrace();
        }
    }

   
    private void configurarTablaCopias() {
        columCopiaId.setCellValueFactory(cd -> 
            new SimpleIntegerProperty(cd.getValue().getId()).asObject());
        columCopiaLibro.setCellValueFactory(cd -> 
            new SimpleStringProperty(cd.getValue().getLibro().getTitulo()));
        columCopiaTipo.setCellValueFactory(cd -> 
            new SimpleStringProperty(cd.getValue().getTipo().toString()));
        columCopiaEstado.setCellValueFactory(cd -> 
            new SimpleStringProperty(cd.getValue().getEstado().toString()));

        // Hacer tabla de solo lectura
        tablaCopiasRack.setEditable(false);
    
    }
    */
    
    
    
    
    
// METODOS PARA LA PANTALLA TAB COPIAS    
        
        // ComboBox de la pantalla de copias | VER SI IMPLEMENTAR @FXML en los combobox    
        private void configurarComboLibros() {
                // Asignar la lista observable al ComboBox
                cmbLibro.setItems(listaLibrosObservable);


                // Configurar cómo se muestran los libros
                cmbLibro.setCellFactory(lv -> new ListCell<Libro>() {
                    @Override
                    protected void updateItem(Libro libro, boolean empty) {
                        super.updateItem(libro, empty);
                        // Muestra solo el título del libro
                        setText(empty ? "" : libro.getTitulo());
                    }
                });



                // Evaluar si esta parte sirve o no
                cmbLibro.setButtonCell(new ListCell<Libro>() {
                    @Override
                    protected void updateItem(Libro libro, boolean empty) {
                        super.updateItem(libro, empty);
                        setText(empty ? "Seleccione un libro" : libro.getTitulo() + " - " + libro.getAutor());
                    }
                });

                // Cargar datos iniciales
                actualizarComboLibros();
        }
    
    
        private void configurarComboRacks() {
                // Asignar la lista observable al ComboBox
                cmbRack.setItems(listaRacksObservable);

                // Configurar cómo se muestran los racks
                /*
                cmbRack.setCellFactory(lv -> new ListCell<Rack>() {
                    @Override
                    protected void updateItem(Rack rack, boolean empty) {
                        super.updateItem(rack, empty);
                        setText(empty ? "" : rack.getId() + " - " + rack.getUbicacion());
                    }
                });
                */

                 // Configurar cómo se muestran los racks
                cmbRack.setCellFactory(lv -> new ListCell<Rack>() {
                    @Override
                    protected void updateItem(Rack rack, boolean empty) {
                        super.updateItem(rack, empty);
                        // Muestra solo el ID del rack
                        setText(empty ? "" : String.valueOf(rack.getId()));
                    }
                });
        
                // Evaluar si esta parte sirve o no
                cmbRack.setButtonCell(new ListCell<Rack>() {
                    @Override
                    protected void updateItem(Rack rack, boolean empty) {
                        super.updateItem(rack, empty);
                        setText(empty ? "Seleccione un rack" : rack.getId() + " - " + rack.getUbicacion());
                    }
                });

                // Cargar datos iniciales
                actualizarComboRacks();
        }
    
    
    
    // Llama este método cada vez que agregues o elimines un libro
    private void actualizarComboLibros() {
        try {
            // Obtener libros actualizados de la base de datos
            List<Libro> librosActualizados = libroServicio.obtenerTodos();

            // Ordenar por título para mejor visualización
            librosActualizados.sort(Comparator.comparing(Libro::getTitulo));

            // Actualizar la ObservableList
            listaLibrosObservable.setAll(librosActualizados);

        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar los libros en el combobox: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    // Llama este método cada vez que agregues o elimines un rack
    
    private void actualizarComboRacks() {
        try {
            // Obtener libros actualizados de la base de datos
            List<Rack> racksActualizados = rackServicio.obtenerTodos();

            // Ordenar por título para mejor visualización
            racksActualizados.sort(Comparator.comparing(Rack::getId));

            // Actualizar la ObservableList
            listaRacksObservable.setAll(racksActualizados);

        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar los racks en el combobox: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    
    
    private void rellenarTabTablaCopias() {
        List<CopiaLibro> copias = copiaServicio.obtenerTodos();
        tablaCopiasLibros.setItems(FXCollections.observableArrayList(copias));
    }
    
    
    private void configurarPantallaCopias() {
        // ComboBox de tipos de copia
        cmbTipo.setItems(FXCollections.observableArrayList(TipoCopia.values()));

        // Tabla de copias
        colIdCopia.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLibro.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getLibro().getTitulo()));
        colTipo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTipo().toString()));
        colRack.setCellValueFactory(c -> {
            Rack r = c.getValue().getRack();
            return new SimpleStringProperty(r != null ? r.getUbicacion() : "Ninguno");
        });
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colReferencia.setCellValueFactory(new PropertyValueFactory<>("esDeReferencia"));

        copiasObservable = FXCollections.observableArrayList(copiaServicio.obtenerTodos());
        tablaCopias.setItems(copiasObservable);

        // Restricciones: rack vs tipo
        cmbTipo.setOnAction(e -> validarRestricciones());
        cmbRack.setOnAction(e -> validarRestricciones());

        // Restricción de los RadioButton
        rbSi.setOnAction(e -> {
            if (rbSi.isSelected()) rbNo.setSelected(false);
        });
        rbNo.setOnAction(e -> {
            if (rbNo.isSelected()) rbSi.setSelected(false);
        });

        // Estado inicial
        rbSi.setSelected(false);
        rbNo.setSelected(false);
    }

       
    private void validarRestricciones() {
        TipoCopia tipo = cmbTipo.getValue();
        Rack rack = cmbRack.getValue();

        if (rack == null || (rack != null && rack.getId() == 0)) {
            // Si no hay rack → solo Audiolibro o Electrónico
            if (tipo != null && (tipo != TipoCopia.AUDIOLIBRO && tipo != TipoCopia.ELECTRONICO)) {
                mostrarAlerta("Restricción", "Si no selecciona un rack, solo puede elegir Audiolibro o Electrónico.");
                cmbTipo.setValue(null);
            }
        } else {
            // Si hay rack → NO puede ser Audiolibro o Electrónico
            if (tipo == TipoCopia.AUDIOLIBRO || tipo == TipoCopia.ELECTRONICO) {
                mostrarAlerta("Restricción", "Si selecciona un rack, no puede elegir Audiolibro o Electrónico.");
                cmbTipo.setValue(null);
            }
        }
    }
    
    
    // ABM COPIAS: AÑADIR UNA COPIA LIBRO
        @FXML
        private void añadirCopia() {
            Libro libro = cmbLibro.getValue();
            Rack rack = cmbRack.getValue();
            TipoCopia tipo = cmbTipo.getValue();

            if (libro == null || tipo == null) {
                mostrarAlerta("Error", "Debe seleccionar un libro y un tipo de copia.");
                return;
            }

            if (!rbSi.isSelected() && !rbNo.isSelected()) {
                mostrarAlerta("Error", "Debe indicar si la copia es de referencia o no.");
                return;
            }

            boolean esReferencia = rbSi.isSelected();

            CopiaLibro copia = new CopiaLibro();
            copia.setLibro(libro);
            copia.setTipo(tipo);
            copia.setEsDeReferencia(esReferencia);

            // Si la copia no es de referencia, establece su estado como DISPONIBLE
            if (!esReferencia) {
                copia.setEstado(EstadoCopia.DISPONIBLE);
            } else {
                // Si es de referencia, establece el estado como EN_BIBLIOTECA
                copia.setEstado(EstadoCopia.NO_DISPONIBLE);
            }


            // Rack "Ninguno" → null
            if (rack != null && rack.getId() != 0) {
                copia.setRack(rack);
            } else {
                copia.setRack(null);
            }

            try {
                copiaServicio.agregarCopia(copia);
                copiasObservable.add(copia);
                rellenarTabTablaCopias();
                
                 // Limpiar la seleccionar de los combobox (libro, rack, tipo) y de los radioButtons
                    limpiarSeleccionCopias();
                
                mostrarAlerta("Éxito", "Copia añadida correctamente.");
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo guardar la copia en la base de datos.");
                e.printStackTrace();
            }
        }
    
        
        // ABM COPIAS:
        @FXML
        private void modificarCopia() {
            CopiaLibro copiaSeleccionada = tablaCopiasLibros.getSelectionModel().getSelectedItem();
            
             boolean esReferencia = rbSi.isSelected();
            
            if (copiaSeleccionada != null) {
                // Actualizar datos en el objeto
                copiaSeleccionada.setLibro(cmbLibro.getValue());
                
                copiaSeleccionada.setRack(cmbRack.getValue());
                
                copiaSeleccionada.setTipo(cmbTipo.getValue());

                if (!esReferencia) {
                    copiaSeleccionada.setEstado(EstadoCopia.DISPONIBLE);
                        } else {
                            // Si es de referencia, establece el estado como EN_BIBLIOTECA
                            copiaSeleccionada.setEstado(EstadoCopia.NO_DISPONIBLE);
                        }
                
                // Guardar cambios en la BD
                try {
                    copiaServicio.actualizarCopia(copiaSeleccionada);
                    
                    // rellenarTabTablaCopias();
                    
                    tablaCopiasLibros.refresh();
                    
                     // Limpiar la seleccionar de los combobox (libro, rack, tipo) y de los radioButtons
                    limpiarSeleccionCopias();
                    
                    mostrarAlerta("Éxito", "Copia modificada correctamente.");
                } catch (Exception e) {
                    mostrarAlerta("Error", "No se pudo modificar la copia en la base de datos.");
                    e.printStackTrace();
                }
            } else {
                mostrarAlerta("Error", "Debe seleccionar una copia para modificar.");
            }
        }
        
        /*
       @FXML
        private void eliminarCopia() {
             CopiaLibro copiaSeleccionada = tablaCopiasLibros.getSelectionModel().getSelectedItem();

            if (copiaSeleccionada != null) {
                copiaServicio.eliminarCopia(copiaSeleccionada);
                
                copiasObservable.remove(copiaSeleccionada);
                tablaCopiasLibros.refresh();

                mostrarAlerta("Éxito", "Copia eliminada de la tabla.");
            } else {
                mostrarAlerta("Error", "Debe seleccionar una copia para eliminar.");
            }
        } 
        */
        
        @FXML
        private void eliminarCopia() {
            CopiaLibro copiaSeleccionada = tablaCopiasLibros.getSelectionModel().getSelectedItem();

            if (copiaSeleccionada != null) {
                try {
                    // Eliminar en la BD
                    copiaServicio.eliminarCopia(copiaSeleccionada);

                    // Eliminar de la lista observable y refrescar la tabla
                    copiasObservable.remove(copiaSeleccionada);
                    tablaCopiasLibros.setItems(copiasObservable); // <- asegura que use la misma lista
                    tablaCopiasLibros.refresh();

                    // Limpiar la seleccionar de los combobox (libro, rack, tipo) y de los radioButtons
                    limpiarSeleccionCopias();
                    
                    mostrarAlerta("Éxito", "Copia eliminada correctamente.");
                } catch (Exception e) {
                    mostrarAlerta("Error", "No se pudo eliminar la copia de la base de datos.");
                    e.printStackTrace();
                }
            } else {
                mostrarAlerta("Error", "Debe seleccionar una copia para eliminar.");
            }
        }

        
        
        
        
        
     private void configurarColumnasInfoLibro() {
        colTCIdLibro.setCellValueFactory(cellData -> 
            new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colTCTitulo.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getTitulo()));
        colTCAutor.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getAutor()));
        colTCEditorial.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getEditorial()));
        colTCCategoria.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getCategoria().toString()));
        colTCISBN.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIsbn()));
        colTCIdioma.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getIdioma()));
    }
        
     /*
    @FXML
    private void verInfoLibro() {
        Libro libroSeleccionado = cmbLibro.getSelectionModel().getSelectedItem();

        if (libroSeleccionado == null) {
            mostrarAlerta("Error", "Seleccione un libro primero.");
            return;
        }

        mostrarLibroEnTabla(libroSeleccionado);
    }
    */

    private void mostrarLibroEnTabla(Libro libro) {
       if (libro == null) {
            tablaInfoLibro.getItems().clear();
            return;
        }

        ObservableList<Libro> libroList = FXCollections.observableArrayList();
        libroList.add(libro);
        tablaInfoLibro.setItems(libroList);

    }
    
    private void limpiarSeleccionCopias() {
        cmbLibro.getSelectionModel().clearSelection();
        cmbRack.getSelectionModel().clearSelection();
        cmbTipo.getSelectionModel().clearSelection();
        rbSi.setSelected(false);
        rbNo.setSelected(false);
    }

    
    
    @FXML
    private void limpiarTablaInfoLibro(){
        
        tablaInfoLibro.getItems().clear();
        
    }
    
    /*
    @FXML
    public void irPrestamos(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/mycompany/bibliotecapoo1/view/PrestamosView.fxml");
    }
    
    @FXML
    public void irUsuarios(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/mycompany/bibliotecapoo1/view/UsuariosView");
    }

    @FXML
    public void irBibliotecarios(ActionEvent event) {
        Enrutador.cambiarVentana(event, "/com/mycompany/bibliotecapoo1/view/BibliotecariosView");
    }
    */
    
    
}
