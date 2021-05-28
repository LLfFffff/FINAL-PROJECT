import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author ark-f
 */
public  class StudentLogin extends JFrame implements  ActionListener{

    Function fun=new Function();

    JLabel lb1=new JLabel("This is currently the student login screen" );
    JLabel lb2=new JLabel("Tip: Please input student number before input, modify, delete modify information according to student number");
    JTextField stu_num, name, age, phone_number, home_place, email;//Enter the text of the student's basic information
    JRadioButton male,female;//Declares a radio option object, gender selection
    ButtonGroup group=null;//Declare button group
    JButton add, search, delete, update, show, back;//A button that declares the corresponding operation
    JPanel p1,p2,p3,p4,p5,p6,p7,pv,ph,pb;//调节布局的通道

    Student stu=new Student();
    ArrayList<Student> arry=new ArrayList<>();

    public StudentLogin(){       //负责管理员登录的主窗口
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
        search=new JButton("搜索符合的学生");
        show=new JButton("显示所有学生的信息");
        back=new JButton("返回登录界面");

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
        p7.add(new JLabel("电子邮箱:",JLabel.CENTER));
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

        ph.add(search);


        ph.add(show);
        ph.add(back);

        Container con=getContentPane();
        con.setLayout(new BorderLayout());

        con.add(pb, BorderLayout.NORTH);
        con.add(pv, BorderLayout.CENTER);
        con.add(ph, BorderLayout.SOUTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100,100,900,450);
        setVisible(true);


        show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new show_stuall();

            }
        });


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


        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);      //The purpose is to prevent the control from being displayed
                new Administratorlogin();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }


}
