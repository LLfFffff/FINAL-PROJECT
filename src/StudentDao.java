import java.sql.*;

public class StudentDao {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //数据库地址
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student_management";
    private static final String USER = "root";
    private static final String PASS = "canwe233";
    private Connection conn = null;
    private Statement stat = null;


    public boolean add(Student stu) {
        return false;
    }

    public Student findByStudentId(int studentId) {
        try {
            //注册JDBC驱动
            Class.forName(JDBC_DRIVER);

            //打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //执行查询
            System.out.println("实例化statement对象");
            stat = conn.createStatement();
            String sql;
            sql = "SELECT id,name,sex,age,home_place,phone_number,email FROM stu_mess WHERE id=" + studentId;
            ResultSet rs = stat.executeQuery(sql);
            if(rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String age = rs.getString("age");
                String homePlace = rs.getString("home_place");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                System.out.println(age);
                return new Student(id,name,sex,age,phoneNumber,email,homePlace);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
