package co.edu.lafloresta.controller;


import co.edu.lafloresta.model.StudentsDTO;
import co.edu.lafloresta.view.ViewGUI;

/**
 * Clase encargada hacer las llamadas y controlar el flujo
 * @author Jhon Erick Santos
 * @version 1.0
 */
public class Controller {
    /**
     * Atributos
     */
    private StudentsDTO institute;
    private ViewGUI frame;


    /**
     * Constructor
     * Desplegar GUI y arranque del sistema
     */
    public Controller() {
        institute = new StudentsDTO();
        frame = new ViewGUI("Institución Educativa La Floresta");
    }
}

