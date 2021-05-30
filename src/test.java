import java.sql.*;

public class test {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //数据库地址
    static final String DB_URL = "jdbc:mysql://localhost:3306/student_management";
    static final String USER = "root";
    static final String PASS = "canwe233";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stat = null;

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
            sql = "SELECT id, name, sex, age FROM stu_mess";
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                int sex = rs.getInt("sex");
                int age = rs.getInt("age");

                System.out.println("id:" + id);
                System.out.println("name:" + name);
                System.out.println("sex:" + sex);
                System.out.println("age:" + age);
            }
            rs.close();
            stat.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException se) {
                //什么都不做
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
