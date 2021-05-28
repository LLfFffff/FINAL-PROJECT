import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;/*ArrayList;
import java.util.Hashtable;
*/
public class ManagerLogin extends JFrame implements  ActionListener{

    Function fun=new Function();

    //Define the components
    JLabel lb1=new JLabel("The current information manager login interface" );//JLabel can display text, images
    JLabel lb2=new JLabel("Tip: Please input student number before input, modify, delete modify information according to student number");

    JTextField stu_num, name, age, phone_number, home_place, email;//Enter the text of the student's basic information
    JRadioButton male,female;//Declares a radio option object, gender selection
    ButtonGroup group=null;//Declare button group
    JButton add, search, delete, update, show, back;//A button that declares the corresponding operation
    JPanel p1,p2,p3,p4,p5,p6,p7,pv,ph,pb;//Adjust the channels of the layout

    public ManagerLogin(){       //The main window responsible for administrator login
        super("学生信息管理系统");
        stu_num=new JTextField(10);//Objects that create text messages
        name=new JTextField(10);
        phone_number=new JTextField(15);
        age=new JTextField(5);
        home_place=new JTextField(15);
        email=new JTextField(18);

        group=new ButtonGroup();
        male=new JRadioButton("男");//Initializes the checkbox,
        female=new JRadioButton("女");
        group.add(male);//Add a button to the button group
        group.add(female);
        add=new JButton("添加");//Create a button object
        search=new JButton("查找");
        delete=new JButton("删除");
        update=new JButton("更新");
        show=new JButton("显示全部");
        back=new JButton("返回");

        pb=new JPanel();
        pb.add(lb1,JLabel.CENTER);

        p1=new JPanel();//Create a panel

        p1.add(lb2,JLabel.CENTER);
        p1.add(new JLabel("学生id:",JLabel.CENTER));
        p1.add(stu_num);

        p2=new JPanel();
        p2.add(new JLabel("姓名:",JLabel.CENTER));
        p2.add(name);
        p3=new JPanel();
        p3.add(new JLabel("性别:",JLabel.CENTER));
        p3.add(male);
        p3.add(female);
        p4=new JPanel();
        p4.add(new JLabel("年龄:",JLabel.CENTER));
        p4.add(age);
        p5=new JPanel();
        p5.add(new JLabel("电话号码:",JLabel.CENTER));
        p5.add(phone_number);
        p6=new JPanel();
        p6.add(new JLabel("家庭住址:",JLabel.CENTER));
        p6.add(home_place);
        p7=new JPanel();
        p7.add(new JLabel("邮箱:",JLabel.CENTER));
        p7.add(email);

        pv=new JPanel();
        pv.setLayout(new GridLayout(7,1));

        pv.add(p1);//Place the panel in the container, and add() represents the container
        pv.add(p2);
        pv.add(p3);
        pv.add(p4);
        pv.add(p5);
        pv.add(p6);
        pv.add(p7);

        ph=new JPanel();
        ph.add(add);
        ph.add(search);
        ph.add(update);
        ph.add(delete);
        ph.add(show);
        ph.add(back);

        Container con=getContentPane();//Create the container object Con and get the container panel
        con.setLayout(new BorderLayout());//Set the layout as the border layout, border layout points in the southeast and northwest five azimuth to add controls.
        con.add(pb, BorderLayout.NORTH);
        con.add(pv, BorderLayout.CENTER);
        con.add(ph, BorderLayout.SOUTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);//Set a default close action, which is the close button on your JFrame window, and click it to exit the program
        setBounds(100,100,900,450);
        setVisible(true);//he purpose is to enable the control to be displayed if the control has already been displayed

        Student stu=new Student();
        ArrayList<Student> arry=new ArrayList<Student>();
        //Add to monitor

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sID = stu_num.getText();    //Gets the contents of the text box
                if(fun.find(sID)!=-1)
                {
                    JOptionPane.showMessageDialog(null, "This student number corresponds to the student already exists!!\\n\\n Please re-enter or modify the students already entered");

                    stu_num.setText("");
                    name.setText("");
                    age.setText("");
                    phone_number.setText("");
                    home_place.setText("");
                    email.setText("");
                    return;
                }
                String sname = name.getText();
                //First check whether the stu_num and name are empty. If so, exit directly
                if(sID.equals("")||sname.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Input student number or name is empty!! \\n\\n Please retype");
                    return;
                }
                String sage = age.getText();
                if(sage.equals(""))//Age is blank, no input
                    sage="--";
                String scall = phone_number.getText();
                if(scall.equals(""))
                    scall="--";
                String shome = home_place.getText();
                if(shome.equals(""))
                    shome="--";
                String siden = email.getText();
                if(siden.equals(""))
                    siden="--";
                String ssex=null;
                if(male.isSelected()){//Choose the men and women
                    ssex=male.getText();
                }
                else{
                    ssex=female.getText();
                }

                stu.setStu_ID(sID);
                stu.setAge(sage);
                stu.setStu_Name(sname);
                stu.setSex(ssex);
                stu.setPhone_Num(scall);
                stu.setHome_place(shome);
                stu.setEmail(siden);


                System.out.println("Administrator");
                System.out.println(stu.fileString());

                fun.add(stu);
                fun.writefile();

                JOptionPane.showMessageDialog(null, "Add successfully！！！");

                setVisible(false);
                new ManagerLogin();


            }
        });
        //Display all student information
        show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new show_stuall();
            }
        });

        //Find information about a student
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sID = stu_num.getText();
                if(sID.equals(""))
                    sID="--";
                String sname = name.getText();
                if(sname.equals(""))
                    sname="--";
                String sage = age.getText();
                if(sage.equals(""))
                    sage="--";
                String scall = phone_number.getText();
                if(scall.equals(""))
                    scall="--";
                String shome = home_place.getText();
                if(shome.equals(""))
                    shome="--";
                String siden = email.getText();
                if(siden.equals(""))
                    siden="--";
                String ssex=null;
                if(male.isSelected()){
                    ssex=male.getText();
                }
                else {
                    if(female.isSelected())
                        ssex=female.getText();
                    else {
                        ssex="--";
                    }
                }

                FileWriter fw=null;
                BufferedWriter out=null;
                try {
                    fw = new FileWriter("temporary.txt");
                    out = new BufferedWriter(fw);

                    out.write(sID+"  ");
                    out.write(sname+"  ");
                    out.write(ssex+"  ");
                    out.write(sage+"  ");
                    out.write(scall+"  ");
                    out.write(shome+"  ");
                    out.write(siden+"  "); //Every time you write an overwrite, you don't need to save it all

                    out.close();
                    fw.close();

                } catch (IOException e1) {
                    e1.printStackTrace();

                }
                //Began to query
                new showones();

                //Clear the textbox
                stu_num.setText("");
                name.setText("");
                age.setText("");
                phone_number.setText("");
                home_place.setText("");
                email.setText("");
                group.clearSelection();

            }
        });



        //Delete student information by stu_num
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sID = stu_num.getText();
                int flag=fun.find(sID);
                if(flag==-1)
                {
                    JOptionPane.showMessageDialog(null, "Did not find the student number of the student!! \\n\\n Please retype");
                }
                else {
                    fun.delete(sID);
                    fun.writefile();
                    JOptionPane.showMessageDialog(null, "Delete successfully！！！\n");
                }


                stu_num.setText("");
                name.setText("");
                age.setText("");
                phone_number.setText("");
                home_place.setText("");
                email.setText("");
                group.clearSelection();

            }
        });

        //Update student information
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sID = stu_num.getText();
                int flag=fun.find(sID);    //Find out if it exists
                if (flag==-1)
                {
                    JOptionPane.showMessageDialog(null, "Did not find the student number of the student!! \\n\\n Please retype");
                    return;
                }
                else
                    JOptionPane.showMessageDialog(null, "The student data \\n\\n exists in the system. Please input the modified data after confirming the return");

                String sage = age.getText();
                if(sage.equals(""))
                    sage="--";
                String scall = phone_number.getText();
                if(scall.equals(""))
                    scall="--";
                String shome = home_place.getText();
                if(shome.equals(""))
                    shome="--";
                String siden = email.getText();
                if(siden.equals(""))
                    siden="--";
                String ssex=null;
                if(male.isSelected()){
                    ssex=male.getText();
                }
                else{
                    ssex=female.getText();
                }
                String sname = name.getText();

                if(sname.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Input student name is empty!! \\n\\n Please retype");
                    return;
                }

                stu.setStu_ID(sID);
                stu.setAge(sage);
                stu.setStu_Name(sname);
                stu.setSex(ssex);
                stu.setPhone_Num(scall);
                stu.setHome_place(shome);
                stu.setEmail(siden);

                fun.update(stu);
                fun.writefile();
                JOptionPane.showMessageDialog(null, "Update successfully！！！");

                stu_num.setText("");
                name.setText("");
                age.setText("");
                phone_number.setText("");
                home_place.setText("");
                email.setText("");
                group.clearSelection();
            }
        });

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);      //The purpose is to prevent the control from being displayed
                new Administratorlogin();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }

}


