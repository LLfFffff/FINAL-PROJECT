import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

public class showones {
    //Read the student information and display it in the window

    ArrayList<Student> arry = new ArrayList<Student>();
    Student stu = new Student();
    JTable table = null;
    DefaultTableModel model = null;
    StudentDao studentDao = new StudentDao();

    //Display all student information
    public showones(String id) {
        JFrame jf = new JFrame("Displaying eligible students' information");

        JPanel panel = new JPanel();

        Vector columnNames = createColumnNames();
        //The string array f is used to record the conditions that need to be queried
        String[] f = new String[7];
        try {
            FileReader f1 = new FileReader("temporary.txt");
            BufferedReader br = new BufferedReader(f1);

            String t = null;
            while ((t = br.readLine()) != null) {
                f = t.split("\\s+");
            }


            f1.close();
            br.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

        //Remember to clear, otherwise the next time the query has no filter, it will query the last condition
        File file = new File("temporary.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //查找学生信息
        Student stu = studentDao.findByStudentId(Integer.parseInt(id));
        arry.add(stu);

        Vector data = createTableModelData();

        //Create a default table model
        model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(400, 80));
        JScrollPane tablePanel = new JScrollPane(table);


        table.setForeground(Color.BLACK);
        table.setFont(new Font(null, Font.PLAIN, 14));
        table.setSelectionForeground(Color.DARK_GRAY);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setGridColor(Color.GRAY);


        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
        table.getTableHeader().setForeground(Color.RED);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);


        table.setRowHeight(40);


        table.getColumnModel().getColumn(0).setPreferredWidth(40);


        table.setPreferredScrollableViewportSize(new Dimension(900, 320));

        RowSorter sorter = new TableRowSorter(model);
        table.setRowSorter(sorter);
        JScrollPane pane = new JScrollPane(table);


        JScrollPane scrollPane = new JScrollPane(table);


        panel.add(scrollPane);

        jf.setContentPane(panel);
        jf.pack();
        jf.setSize(900, 600);
        jf.add(scrollPane, BorderLayout.CENTER);

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);

        jf.setLocationRelativeTo(null);
        int t = arry.size();
        //int t=table.getRowCount();
        if (t <= 0) {
            JOptionPane.showMessageDialog(null, "There are no eligible students in the system！！！");
            jf.setVisible(false);
        } else {
            jf.setVisible(true);
        }
    }

    private Vector createColumnNames() {
        Vector columnNames = new Vector();

        columnNames.add("学生id");
        columnNames.add("姓名");
        columnNames.add("性别");
        columnNames.add("年龄");
        columnNames.add("电话号码");
        columnNames.add("家庭地址");
        columnNames.add("电子邮箱");

        return columnNames;
    }

    private Vector createTableModelData() {
        Vector data = new Vector();
        for (int i = 0; i < arry.size(); i++) {
            Vector<String> rowData = new Vector<>();
            rowData.add(arry.get(i).getStu_ID());
            rowData.add(arry.get(i).getStu_Name());
            rowData.add(arry.get(i).getSex());
            rowData.add(arry.get(i).getAge());
            rowData.add(arry.get(i).getPhone_Num());
            rowData.add(arry.get(i).getHome_place());
            rowData.add(arry.get(i).getEmail());
            data.add(rowData);

        }
        return data;
    }

}