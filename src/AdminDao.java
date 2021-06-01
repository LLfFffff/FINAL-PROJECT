import java.sql.*;

public class AdminDao {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //数据库地址
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student_management";
    private static final String USER = "root";
    private static final String PASS = "canwe233";
    private Connection conn = null;
    private Statement stat = null;

    /**
     * 添加管理员信息
     *
     * @param account 账号名
     * @param pass 密码
     * @return 是否添加成功
     */
    public boolean add(String account, String pass) {

        try {
            //注册JDBC驱动
            Class.forName(JDBC_DRIVER);

            //打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //执行查询
            stat = conn.createStatement();
            String sql;
            sql = "INSERT INTO admin_account (account,pass) VALUES (?,?)";
            System.out.println("添加管理员账号");

            //预编译sql语句
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, account);
            psmt.setString(2, pass);
            psmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
        return true;
    }
}
