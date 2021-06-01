import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class register extends JFrame implements ActionListener{
    CheckUser checkUser = new CheckUser();

    private JPanel jp1;
    private JPanel jp2;
    private JPanel jp3;
    private JLabel username;
    private JLabel password;
    private JLabel jLabel;
    private JTextField Tusername;
    private JTextField Tpassword;
    private JButton register;
    private JButton quit;
    public register(){
        JFrame frame = new JFrame();
        jp1 = new JPanel();
        jp1.setLayout(null);
        username = new JLabel("用户名");
        password = new JLabel("密码");
        Tusername = new JTextField(20);
        Tpassword = new JTextField(20);
        username.setBounds(20, 20, 100, 25);
        password.setBounds(20, 50, 100, 25);
        Tusername.setBounds(150, 20, 200, 25);
        Tpassword.setBounds(150, 50, 200, 25);
        jp1.add((username));
        jp1.add(Tusername);
        jp1.add(password);
        jp1.add(Tpassword);
        jp2 = new JPanel();
        jp2.setLayout(new GridLayout(1,3));
        register = new JButton("注册");
        quit = new JButton("退出");
        jp2.add(register);
        jp2.add(quit);
        jp3 = new JPanel();
        jLabel = new JLabel("注册一个账号");
        jp3.add(jLabel);
        frame.setLayout(new BorderLayout());
        frame.add(jp3,BorderLayout.NORTH);
        frame.add(jp1,BorderLayout.CENTER);
        frame.add(jp2,BorderLayout.SOUTH);
        frame.setSize(500,350);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);//Display in the middle of the screen (center display)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Exit and close JFrame
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(register.this,"确定退出?")==JOptionPane.OK_OPTION){
                    System.exit(0);
                }
            }
        });
        Administrator administrator = new Administrator();
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userN = Tusername.getText();
                String passW = Tpassword.getText();
                if(checkUser.find(userN)!=-1){
                    JOptionPane.showMessageDialog(null, "用户名已存在\n\n请重新输入");
                    Tusername.setText("");
                    Tpassword.setText("");
                    return;
                }

                if(userN.equals("")){
                    JOptionPane.showMessageDialog(null, "用户名为空\n\n请重新输入");
                    return;
                }
                if(passW.equals("")){
                    JOptionPane.showMessageDialog(null, "密码为空\n\n请重新输入");
                    return;
                }
                administrator.setUsername(userN);
                administrator.setPassword(passW);

                //写入数据库
                AdminDao adminDao = new AdminDao();
                adminDao.add(userN,passW);

                System.out.println("information"+administrator.fileString());
                checkUser.insert(administrator);
                JOptionPane.showMessageDialog(null, "注册成功");
                frame.setVisible(false);
                new Administratorlogin();
            }
        });



    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
