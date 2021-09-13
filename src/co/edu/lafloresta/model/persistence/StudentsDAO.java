package co.edu.lafloresta.model.persistence;

import co.edu.lafloresta.model.Students;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Acceso al CRUD
/**
 * Clase encargada de encapulsar los datos
 * Data Access Object
 * @author Jhon Erick Santos
 * @version 4.0
 */
public class StudentsDAO {
    /**
     * Atributos
     */
    private Connect connect;

    /**
     * Constructor
     *
     * @param connect de la Clase Connect
     **/
    public StudentsDAO(Connect connect) {
        this.connect = connect;
    }


    /**
     * Método para buscar un Estudiante en la BD
     *
     * @param institutionalEmail identificador para hacer la búsqueda dentro de la lista
     * @return Objeto si lo encuentra.
     */
    public Students searchStudent(String institutionalEmail) {
        Students found = null;
        PreparedStatement ps;
        ResultSet rs;
        String name, lName, dBirth, iEmail, pEmail, program;
        long pCell, pNumber;
        String sql = "SELECT * FROM estudiantes WHERE instEmail = ?";
        try {
            connect.openConnectDB();
            ps = connect.getConnection().prepareStatement(sql);
            ps.setString(1,institutionalEmail);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString(1);
                lName = rs.getString(2);
                dBirth = rs.getString(3);
                iEmail = rs.getString(4);
                pEmail = rs.getString(5);
                pCell = rs.getLong(6);
                pNumber = rs.getLong(7);
                program = rs.getString(8);
                found = new Students(name, lName, dBirth, iEmail, pEmail, pCell, pNumber, program);
            }
            connect.closeConnectDB();
        } catch (Exception e) {
            e.printStackTrace();
            found = null;
        }
        return found;
    }

    /**
     * Método para agregar un registro a la BD
     * parámetros todos los necesarios para crear un objeto Students
     */
    public void addStudents(String name, String lastName, String dataOfBirth, String institutionalEmail, String personalEmail,
                            long phoneNumberCell, long phoneNumber, String academicProgram) {

        PreparedStatement ps;
        String sql = "INSERT INTO estudiantes(name,lastName,dateBirth,instEmail,personalEmail,phoneCell,phoneNumber,program)" +
                "VALUES(?,?,?,?,?,?,?,?);";
        try {
            connect.openConnectDB();
            ps = connect.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setString(3, dataOfBirth);
            ps.setString(4, institutionalEmail);
            ps.setString(5, personalEmail);
            ps.setLong(6, phoneNumberCell);
            ps.setLong(7, phoneNumber);
            ps.setString(8, academicProgram);
            int flag = ps.executeUpdate();
            if (flag > 0) {
                connect.closeConnectDB();
            } else {
                connect.closeConnectDB();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para borrar un registro de la BD
     *
     * @param email Correo institucional como identificador
     * @return True si lo agrega o false en caso contrario.
     */
    public boolean deleteStudent(String email) {
        PreparedStatement ps;
        String sql = "DELETE FROM estudiantes WHERE instEmail = ?";
        try {
            connect.openConnectDB();
            ps = connect.getConnection().prepareStatement(sql);
            ps.setString(1, email);
            int flag = ps.executeUpdate();
            if (flag > 0) {
                connect.closeConnectDB();
                return true;
            } else {
                connect.closeConnectDB();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Método para borrar un registro de la BD
     * <p>
     * todos los atributos de la clase Student como parámetro
     */
    public boolean modifyStudent(String instEmail, String personalEmail, long cellPhone, long phone, String program ) {

        PreparedStatement ps;
        String sql = "UPDATE estudiantes SET personalEmail = ?,phoneCell = ?,phoneNumber = ?, program = ?"
                + "WHERE instEmail = ?";
        try {
            connect.openConnectDB();
            ps= connect.getConnection().prepareStatement(sql);
            ps.setString(1, personalEmail);
            ps.setLong(2, cellPhone);
            ps.setLong(3, phone);
            ps.setString(4, program);
            ps.setString(5, instEmail);
            int flag = ps.executeUpdate();
            if (flag > 0) {
                connect.closeConnectDB();
                return true;
            } else {
                connect.closeConnectDB();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Método para leer la base de datos, se almacena en un ArrayList
     *
     * @return ArrayList de estudiantes
     */
    public List<Students> listar() {
        List<Students> studentList = new ArrayList<Students>();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM estudiantes";
        try {
            connect.openConnectDB();
            ps = connect.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                String lName = rs.getString(2);
                String dBirth = rs.getString(3);
                String iEmail = rs.getString(4);
                String pEmail = rs.getString(5);
                long pCell = rs.getLong(6);
                long pNumber = rs.getLong(7);
                String program = rs.getString(8);
                Students newStudent = new Students(name, lName, dBirth, iEmail, pEmail, pCell, pNumber, program);
                studentList.add(newStudent);
            }
            connect.closeConnectDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }


}