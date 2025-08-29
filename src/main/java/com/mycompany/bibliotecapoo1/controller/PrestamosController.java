/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bibliotecapoo1.controller;

import com.mycompany.bibliotecapoo1.model.CategoriaLibro;
import com.mycompany.bibliotecapoo1.model.CopiaLibro;
import com.mycompany.bibliotecapoo1.model.Miembro;
import com.mycompany.bibliotecapoo1.model.Prestamo;
import com.mycompany.bibliotecapoo1.model.Rack;
import com.mycompany.bibliotecapoo1.repository.Repositorio;
import com.mycompany.bibliotecapoo1.service.CopiaLibroServicio;
import com.mycompany.bibliotecapoo1.service.MiembroServicio;
import com.mycompany.bibliotecapoo1.service.PrestamoServicio;
import com.mycompany.bibliotecapoo1.util.Conexion;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrestamosController extends Navegacion{

    @FXML private TextField txtBusqueda;
    @FXML private ComboBox<CategoriaLibro> cmbCategoria;
    @FXML private TableView<CopiaLibro> tablaCopias;
    @FXML private TableColumn<CopiaLibro, Integer> colIdCopia;
    @FXML private TableColumn<CopiaLibro, String> colTitulo;
    @FXML private TableColumn<CopiaLibro, String> colAutor;
    @FXML private TableColumn<CopiaLibro, String> colCategoria;
    @FXML private TableColumn<CopiaLibro, String> colTipo;
    @FXML private TableColumn<CopiaLibro, String> colRack;
    @FXML private TableColumn<CopiaLibro, String> colEstado;
    
    @FXML private TextField txtClaveMiembro;
    @FXML private DatePicker dpFechaVencimiento;
    @FXML private Button btnGenerarPrestamo;

    private Repositorio repositorio;
    private CopiaLibroServicio copiaLibroServicio;
    private MiembroServicio miembroServicio;
    private PrestamoServicio prestamoServicio;
    
    private ObservableList<CopiaLibro> copiasObservable;
    

    

    @FXML
    public void initialize() {
        
        this.repositorio = new Repositorio(Conexion.getEntityManagerFactory());
        this.copiaLibroServicio = new CopiaLibroServicio(this.repositorio);
        this.prestamoServicio = new PrestamoServicio(this.repositorio);
        this.miembroServicio = new MiembroServicio(this.repositorio);
        
        // Configurar ComboBox de categorías
        cmbCategoria.setItems(FXCollections.observableArrayList(CategoriaLibro.values()));

        // Configurar columnas de la tabla
        colIdCopia.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getId()).asObject());
        colTitulo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getLibro().getTitulo()));
        colAutor.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getLibro().getAutor()));
        colCategoria.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getLibro().getCategoria().toString()));
        colTipo.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getTipo().toString()));
        colRack.setCellValueFactory(c -> {
            Rack r = c.getValue().getRack();
            return new SimpleStringProperty(r != null ? r.getUbicacion() : "Ninguno");
        });
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        

        // Configurar selección múltiple en la tabla
        tablaCopias.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        
        
        // Cargar todos inicialmente
        List<CopiaLibro> todasLasCopias = copiaLibroServicio.obtenerTodos();
        copiasObservable = FXCollections.observableArrayList(todasLasCopias);
        tablaCopias.setItems(copiasObservable);
    }

    @FXML
    private void buscar() {
        String textoBusqueda = txtBusqueda.getText() != null ? txtBusqueda.getText().trim().toLowerCase() : "";
        CategoriaLibro categoriaSeleccionada = cmbCategoria.getValue();

        // Obtener todas las copias desde servicio
        List<CopiaLibro> todasLasCopias = copiaLibroServicio.obtenerTodos();

        // Filtrar
        List<CopiaLibro> filtradas = todasLasCopias.stream()
                .filter(copia -> {
                    boolean coincideTexto = textoBusqueda.isEmpty() ||
                            copia.getLibro().getTitulo().toLowerCase().contains(textoBusqueda) ||
                            copia.getLibro().getAutor().toLowerCase().contains(textoBusqueda) ||
                            copia.getLibro().getIsbn().toLowerCase().contains(textoBusqueda);

                    boolean coincideCategoria = (categoriaSeleccionada == null) ||
                            copia.getLibro().getCategoria() == categoriaSeleccionada;

                    return coincideTexto && coincideCategoria;
                })
                .collect(Collectors.toList());

        // Actualizar tabla
        copiasObservable.setAll(filtradas);

        // Resetear ComboBox de categoría (para que el usuario no quede obligado)
        cmbCategoria.setValue(null);
    }
    
    
    // ------------------------------------------------------------------------------------------------------------------
    
    
    //                      GENERAR EL PRESTAMO: METODOS Y VALIDACIONES 
    
    
    //-------------------------------------------------------------------------------------------------------------------
    
    
    /*
    @FXML
    private void generarPrestamo() {
        String clave = txtClaveMiembro.getText().trim();
        CopiaLibro copiaSeleccionada = tablaCopias.getSelectionModel().getSelectedItem();
        LocalDate fechaHoy = LocalDate.now();
        LocalDate fechaVencimiento = dpFechaVencimiento.getValue();

        // Validaciones
        if (clave.isEmpty()) {
            mostrarError("Debe ingresar su clave de miembro.");
            return;
        }

        Miembro miembro = miembroServicio.buscarPorClave(clave);
        if (miembro == null) {
            mostrarError("No se encontró un miembro con esa clave.");
            return;
        }

        if (copiaSeleccionada == null) {
            mostrarError("Debe seleccionar una copia de libro de la tabla.");
            return;
        }

        if (fechaVencimiento == null) {
            mostrarError("Debe seleccionar una fecha de vencimiento.");
            return;
        }

        long diasPrestamo = ChronoUnit.DAYS.between(fechaHoy, fechaVencimiento);
        if (diasPrestamo <= 0 || diasPrestamo > 10) {
            mostrarError("La fecha de vencimiento debe ser dentro de los próximos 10 días.");
            return;
        }

        // Crear el préstamo
        Prestamo prestamo = new Prestamo();
        prestamo.setMiembro(miembro);
        prestamo.setCopia(copiaSeleccionada);
        prestamo.setFechaInicio(fechaHoy);
        prestamo.setFechaVencimiento(fechaVencimiento);

        // Guardar en BD
        prestamoServicio.agregarPrestamo(prestamo);

        mostrarInfo("Préstamo generado con éxito para el miembro: " + miembro.getNombre());
        limpiarCampos();
    }
    */
    
    
    
    
    @FXML
    private void generarPrestamo() {
        String clave = txtClaveMiembro.getText().trim();
        List<CopiaLibro> copiasSeleccionadas = tablaCopias.getSelectionModel().getSelectedItems();
        LocalDate fechaHoy = LocalDate.now();
        LocalDate fechaVencimiento = dpFechaVencimiento.getValue();

        // Validaciones
        if (clave.isEmpty()) {
            mostrarError("Debe ingresar su clave de miembro.");
            return;
        }

        Miembro miembro = miembroServicio.buscarPorClave(clave);
        if (miembro == null) {
            mostrarError("No se encontró un miembro con esa clave.");
            return;
        }

        if (copiasSeleccionadas == null || copiasSeleccionadas.isEmpty()) {
            mostrarError("Debe seleccionar al menos una copia de libro de la tabla.");
            return;
        }

        if (fechaVencimiento == null) {
            mostrarError("Debe seleccionar una fecha de vencimiento.");
            return;
        }

        long diasPrestamo = ChronoUnit.DAYS.between(fechaHoy, fechaVencimiento);
        if (diasPrestamo <= 0 || diasPrestamo > 10) {
            mostrarError("La fecha de vencimiento debe ser dentro de los próximos 10 días.");
            return;
        }

        // Crear un préstamo por cada copia seleccionada
        for (CopiaLibro copia : copiasSeleccionadas) {
            Prestamo prestamo = new Prestamo();
            prestamo.setMiembro(miembro);
            prestamo.setCopia(copia);
            prestamo.setFechaInicio(fechaHoy);
            prestamo.setFechaVencimiento(fechaVencimiento);

            prestamoServicio.agregarPrestamo(prestamo);
        }

        mostrarInfo("Préstamos generados con éxito para el miembro: " 
                    + miembro.getNombre() + " (" + copiasSeleccionadas.size() + " copias)");
        limpiarCampos();
    }

    
    
    
    
    
    
    
    

    private void limpiarCampos() {
        txtClaveMiembro.clear();
        dpFechaVencimiento.setValue(null);
        tablaCopias.getSelectionModel().clearSelection();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
    
    
    
    


