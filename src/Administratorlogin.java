import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Administratorlogin extends JFrame implements ActionListener {
    CheckUser checkUser = new CheckUser();
    private JPanel jp1;
    private JPanel jp2;
    private JPanel jp3;
    private JLabel username;
    private JLabel password;
    private JLabel jLabel;
    private JTextField Tusername;
    private JTextField Tpassword;
    private JButton Login;
    private JButton register;
    private JButton quit;

    public Administratorlogin() {
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
        jp2.setLayout(new GridLayout(1, 3));
        Login = new JButton("登录");
        register = new JButton("注册");
        quit = new JButton("退出");
        jp2.add(Login);
        jp2.add(register);
        jp2.add(quit);
        jLabel = new JLabel("登录");
        jp3 = new JPanel();
        jp3.add(jLabel);
        frame.setLayout(new BorderLayout());
        frame.add(jp3, BorderLayout.NORTH);
        frame.add(jp1, BorderLayout.CENTER);
        frame.add(jp2, BorderLayout.SOUTH);
        frame.setSize(500, 350);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);//Display in the middle of the screen (center display)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Exit and close JFrame
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(Administratorlogin.this, "确定退出?") == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new register();
            }
        });
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String UserN = Tusername.getText();
                String PassW = Tpassword.getText();
                AdminDao adminDao = new AdminDao();
                boolean loginRes = adminDao.verifyAccount(UserN, PassW);

                if (loginRes) {
//                    JOptionPane.showMessageDialog(null, "登陆成功");
                    Tusername.setText("");
                    Tpassword.setText("");
                    new ManagerLogin();
                    frame.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "登录失败\n\n请重新输入");
                    Tusername.setText("");
                    Tpassword.setText("");
                }
                return;

            }
        });


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
