package co.edu.lafloresta.view;

import co.edu.lafloresta.model.Students;
import co.edu.lafloresta.model.StudentsDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de la interfaz Grafica de usuario
 *
 * @author Jhon Erick Santos G46
 * @version 1.0
 */
public class ViewGUI extends JFrame {

    public String codText;
    private StudentsDTO institute;
    private JPanel mainPanel;
    private JTextArea textArea;
    private JTextField textName;
    private JTextField textLastName;
    private JTextField textIntsEmail;
    private JTextField textPerEmail;
    private JTextField textDateBirth;
    private JTextField textCell;
    private JTextField textPhone;
    private JTextField textProgram;
    private JList<String> textList;
    private List<Students> list;


    //Constructor
    public ViewGUI(String tittle) {
        super(tittle);
        this.setVisible(true);
        this.setBounds(250, 250, 850, 750);
        this.setResizable(false);
        list = new ArrayList<>();
        institute = new StudentsDTO();
        // LLAMAMOS METODOS DE LOS COMPONENTES
        panel();
        labels();
        txtFields();
        txtList();
        buttonInsert();
        buttonDelete();
        buttonEdit();
        buttonSearch();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * Método para instanciar componentes canvas
     *
     */
    private void panel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.gray);
        this.getContentPane().add(mainPanel);
    }

    //Boxes

    /**
     * Método para instanciar componentes tipo textList
     **/
    private void txtList(){
        textList = new JList<String>();
        textList.setBounds(500, 100, 300, 380);
        mainPanel.add(textList);
        refreshList();
    }

    /**
     * Método para instanciar componentes tipo Label
     *
     */
    private void labels() {
        /*etiqueta.setBounds(170, 520, 350, 30);
        mainPanel.add(etiqueta);*/

        JLabel labelTittle = new JLabel();
        labelTittle.setText("SISTEMA DE INFORMACIÓN ESTUDIANTES LA FLORESTA");
        labelTittle.setBounds(250, 20, 400, 30);
        mainPanel.add(labelTittle);

        JLabel labelName = new JLabel();
        labelName.setText("Nombres ");
        labelName.setBounds(70, 100, 250, 30);
        mainPanel.add(labelName);

        JLabel labelLastName = new JLabel();
        labelLastName.setText("Apellidos ");
        labelLastName.setBounds(70, 150, 250, 30);
        mainPanel.add(labelLastName);

        JLabel labelDateBirth = new JLabel();
        labelDateBirth.setText("Fecha (YYYY-MM-DD)");
        labelDateBirth.setBounds(70, 200, 250, 30);
        mainPanel.add(labelDateBirth);

        JLabel labelInstEmail = new JLabel();
        labelInstEmail.setText("Correo Institucional ");
        labelInstEmail.setBounds(70, 250, 250, 30);
        mainPanel.add(labelInstEmail);

        JLabel labelPerEmail = new JLabel();
        labelPerEmail.setText("Correo Personal");
        labelPerEmail.setBounds(70, 300, 250, 30);
        mainPanel.add(labelPerEmail);

        JLabel labelCell = new JLabel();
        labelCell.setText("Teléfono Celular ");
        labelCell.setBounds(70, 350, 250, 30);
        mainPanel.add(labelCell);

        JLabel labelPhone = new JLabel();
        labelPhone.setText("Teléfono fijo ");
        labelPhone.setBounds(70, 400, 250, 30);
        mainPanel.add(labelPhone);

        JLabel labelProgram = new JLabel();
        labelProgram.setText("Programa Académico");
        labelProgram.setBounds(70, 450, 250, 30);
        mainPanel.add(labelProgram);
    }

    /**
     * Método para instanciar componentes tipo textFiel
     *
     */
    private void txtFields() {
        textName = new JTextField();
        textName.setBounds(200, 100, 250, 30);
        mainPanel.add(textName);

        textLastName = new JTextField();
        textLastName.setBounds(200, 150, 250, 30);
        mainPanel.add(textLastName);

        textDateBirth = new JTextField();
        textDateBirth.setBounds(200, 200, 250, 30);
        mainPanel.add(textDateBirth);

        textIntsEmail = new JTextField();
        textIntsEmail.setBounds(200, 250, 250, 30);
        mainPanel.add(textIntsEmail);

        textPerEmail = new JTextField();
        textPerEmail.setBounds(200, 300, 250, 30);
        mainPanel.add(textPerEmail);

        textCell = new JTextField();
        textCell.setBounds(200, 350, 250, 30);
        mainPanel.add(textCell);

        textPhone = new JTextField();
        textPhone.setBounds(200, 400, 250, 30);
        mainPanel.add(textPhone);

        textProgram = new JTextField();
        textProgram.setBounds(200, 450, 250, 30);
        mainPanel.add(textProgram);


    }

    //Funciones

    /**
     * Método para limpiar datos textField
     */
    private void cleanBoxes(){
        textName.setText(null);
        textLastName.setText(null);
        textCell.setText(null);
        textDateBirth.setText(null);
        textIntsEmail.setText(null);
        textPerEmail.setText(null);
        textPhone.setText(null);
        textProgram.setText(null);
    }

    /**
     * Metodo para actualizar la lista de estudiantes en vista
     */
    public void refreshList(){
        list = institute.getStudentDao().listar();
        DefaultListModel data = new DefaultListModel();
        for (int i = 0; i < list.size(); i++) {
            Students n = list.get(i);
            data.addElement(n.getName() + " " +n.getLastName());
        }
        textList.setModel(data);
    }

    /**
     * Metodo para buscar la informacion de estudiante
     */
    public void search(){
        int index = textList.getSelectedIndex();
        Students response =list.get(index);
        textName.setText(response.getName());
        textLastName.setText(response.getLastName());
        textDateBirth.setText(response.getDateOfBirth());
        textIntsEmail.setText(response.getInstitutionalEmail());
        textPerEmail.setText(response.getPersonalEmail());
        textCell.setText(String.valueOf(response.getPhoneNumberCell()));
        textPhone.setText(String.valueOf(response.getPhoneNumber()));
        textProgram.setText(response.getAcademicProgram());
    }

    // Buttons CRUD

    /**
     * Función para crear un estudiante
     * LLama a la función crear estudiante de la clase DAO
     */
    private void buttonInsert() {
        JButton btInsert = new JButton("Crear");
        btInsert.setBounds(100, 600, 150, 40);
        mainPanel.add(btInsert);

        ActionListener Scan = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n, a, fn, ci, cp, pr;
                long cel, fi;
                n = a = fn = ci = cp = pr = "";
                cel = fi = 0;

                n = textName.getText();
                a = textLastName.getText();
                fn = textDateBirth.getText();
                ci = textIntsEmail.getText();
                cp = textPerEmail.getText();
                cel = Long.parseLong(textCell.getText());
                fi = Long.parseLong(textPhone.getText());
                pr = textProgram.getText();

                if (institute.getStudentDao().searchStudent(ci) == null) {
                    institute.getStudentDao().addStudents(n, a, fn, ci, cp, cel, fi, pr);
                    JOptionPane.showMessageDialog(rootPane, "Se agrego el estudiante correctamente");
                    cleanBoxes();
                    refreshList();
                } else {
                    JOptionPane.showMessageDialog(rootPane,"NO se guardo el registro.");
                }
            }
        };
        btInsert.addActionListener(Scan);
    }

    /**
     * Función para leer la información de un estudiante y modificarlo
     * Llama la función agregar y eliminar estudiante de la clase DAO
     */
    private void buttonEdit() {
        JButton btEdit = new JButton("Editar");
        btEdit.setBounds(400, 600, 150, 40);
        mainPanel.add(btEdit);

        ActionListener Scan = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n, a, fn, ci, cp, pr;
                long cel,fi;
                n = textName.getText();
                a = textLastName.getText();
                fn = textDateBirth.getText();
                ci = textIntsEmail.getText();
                cp = textPerEmail.getText();
                cel = Long.parseLong(textCell.getText());
                fi = Long.parseLong(textPhone.getText());
                pr = textProgram.getText();
                if (institute.getStudentDao().modifyStudent(ci,cp,cel,fi,pr)) {
                    JOptionPane.showMessageDialog(rootPane, "Se Modifico el estudiante " + textName.getText());
                    cleanBoxes();
                    refreshList();
                } else {
                    JOptionPane.showMessageDialog(rootPane,"El estudiante no se encuentra registrado en el instituto");
                }
            }
        };
        btEdit.addActionListener(Scan);
    }

    /**
     * Boton para leer la información de un estudiante y borrarlo
     * Llama la función eliminar estudiante de la clase DAO
     */
    private void buttonDelete() {
        JButton btDelete = new JButton("Borrar");
        btDelete.setBounds(550, 600, 150, 40);
        mainPanel.add(btDelete);

        ActionListener Scan = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = textList.getSelectedIndex();
                Students n =list.get(index);
                String ci = n.getInstitutionalEmail();
                if(institute.getStudentDao().deleteStudent(ci)){
                    JOptionPane.showMessageDialog(rootPane, "Se eliminó el estudiante");
                    cleanBoxes();
                    refreshList();
                }else{
                    JOptionPane.showMessageDialog(rootPane, "El estudiante no se encuentra registrado en el instituto");
                }
            }
        };
        btDelete.addActionListener(Scan);
    }

    /**
     * Boton para mostrar la información de un estudiante
     */
    private void buttonSearch(){
        JButton btPrint = new JButton("Buscar");
        btPrint.setBounds(250, 600, 150, 40);
        mainPanel.add(btPrint);

        ActionListener Scan = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        };
        btPrint.addActionListener(Scan);
    }


}
