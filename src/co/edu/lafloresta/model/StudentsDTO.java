package co.edu.lafloresta.model;

import co.edu.lafloresta.model.persistence.Connect;
import co.edu.lafloresta.model.persistence.StudentsDAO;

//CONTIENE EL OBJETO CONNECT
//CONTIENE EL OBJETO DAO
/**
 * Clase encargada de transportar los datos DAO
 * Data Transfer Object
 * @author Jhon Erick Santos
 * @version 4.0
 */
public class StudentsDTO {


    private StudentsDAO studentDao;
    private Connect connect;


    /**constructor*/
    public StudentsDTO() {
        connect = new Connect();
        studentDao = new StudentsDAO(connect);
    }


    // Getter and Setters

    public StudentsDAO getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentsDAO studentDao) {
        this.studentDao = studentDao;
    }

    public  Connect getConnect(){
        return connect;
    }

    public void setConnect(Connect connect){
        this.connect = connect;
    }

}
