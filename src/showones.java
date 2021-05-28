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

    ArrayList<Student> arry=new ArrayList<Student>();
    Student stu=new Student();
    JTable table = null;
    DefaultTableModel model = null;
    //Display all student information
    public showones() {
        JFrame jf = new JFrame("Displaying eligible students' information");

        JPanel panel = new JPanel();

        Vector columnNames=createColumnNames();
        //The string array f is used to record the conditions that need to be queried
        String [] f = new String [7];
        try{
            FileReader f1 = new FileReader("temporary.txt");
            BufferedReader br=new BufferedReader(f1);

            String t=null;
            while ((t= br.readLine())!= null)
            {
                f=t.split("\\s+");
            }


            f1.close();
            br.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

        //Remember to clear, otherwise the next time the query has no filter, it will query the last condition
        File file =new File("temporary.txt");
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Determine which conditions need to be queried
        ArrayList List = new ArrayList();
        for(int i=0;i<7;i++) {
            if(!f[i].equals("--"))     //Conditions that need to be met
                List.add(i);
        }
        //Input the information of students that meet the query conditions into the system
        for(int i=0;i<List.size();i++)
            System.out.println(List.get(i));
        try{
            FileReader f1 = new FileReader("student.txt");
            BufferedReader br=new BufferedReader(f1);
            String t=null;
            int flag=0;
            int t1=0;

            arry.clear();//	Clear the data from the original array list
            while ((t= br.readLine())!= null)
            {
                String [] s=t.split("\\s+");
                for(int i=0;i<List.size();i++)
                {
                    t1=(int) List.get(i);
                    if(f[t1].equals(s[t1])) {
                        flag=flag+1;
                    }
                }
                if(flag==List.size()) {
                    Student st=new Student(s[0],s[1],s[2],s[3],s[4],s[5],s[6]);//Note that if the file does not have seven strings per line, an error occurs
                    arry.add(st);
                }
                flag=0;

            }
            f1.close();
            br.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

        Vector data=createTableModelData();

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
        int t=arry.size();
        //int t=table.getRowCount();
        if(t<=0){
            JOptionPane.showMessageDialog(null, "There are no eligible students in the system！！！");
            jf.setVisible(false);
        }
        else {
            jf.setVisible(true);
        }
    }
    private Vector createColumnNames() {
        Vector columnNames = new Vector();

        columnNames.add("stu_num");
        columnNames.add("name");
        columnNames.add("sex");
        columnNames.add("age");
        columnNames.add("phone_number");
        columnNames.add("home_place");
        columnNames.add("email");

        return columnNames;
    }

    private Vector createTableModelData() {
        Vector data = new Vector();
        for(int i=0;i<arry.size();i++) {
            Vector<String> rowData=new Vector<>();
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