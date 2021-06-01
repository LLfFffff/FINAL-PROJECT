import java.sql.*;

public class StudentDao {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //数据库地址
    private static final String DB_URL = "jdbc:mysql://cn5.frp.cool:13306/student_management";
    private static final String USER = "root";
    private static final String PASS = "canwe233";
    private Connection conn = null;
    private Statement stat = null;

    /**
     * 根据id获取学生信息
     *
     * @param studentId 学生id
     * @return 学生信息，如果不存在则返回null
     */
    public  Student findByStudentId(int studentId) {
        try {
            //注册JDBC驱动
            Class.forName(JDBC_DRIVER);

            //打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //执行查询
            stat = conn.createStatement();
            String sql;
            sql = "SELECT * FROM stu_mess WHERE id=" + studentId;

            ResultSet rs = stat.executeQuery(sql);
            System.out.println("查询学生信息，id：" + studentId);

            if (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String age = rs.getString("age");
                String homePlace = rs.getString("home_place");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                return new Student(id, name, sex, age, phoneNumber, email, homePlace);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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

        return null;
    }

    /**
     * 添加学生信息
     *
     * @param stu 学生类（不可为null）
     * @return 是否添加成功
     */
    public boolean add(Student stu) {
        if (stu == null) {
            System.out.println("添加学生信息失败");
            return false;
        }

        try {
            //注册JDBC驱动
            Class.forName(JDBC_DRIVER);

            //打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //执行查询
            stat = conn.createStatement();
            String sql;
            sql = "INSERT INTO stu_mess (name,sex,age,home_place,phone_number,email) VALUES (?,?,?,?,?,?)";
            System.out.println("添加学生信息");
            System.out.println(stu.toString());

            //预编译sql语句
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, stu.getStu_Name());
            psmt.setInt(2, stu.getStuSex());
            psmt.setInt(3, stu.getStuAge());
            psmt.setString(4, stu.getHome_place());
            psmt.setString(5, stu.getPhone_Num());
            psmt.setString(6, stu.getEmail());
            psmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        return true;
    }

    /**
     * 修改学生信息
     *
     * @param stu 学生类（不可为null）
     * @return 是否添加成功
     */
    public void update(Student stu) {
        try {
            //注册JDBC驱动
            Class.forName(JDBC_DRIVER);

            //打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //执行查询
            stat = conn.createStatement();
            String sql = "update stu_mess set name=?,sex=?,age=?,home_place=?,phone_number=?,email=? where id=?";

            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, stu.getStu_Name());
            ptmt.setInt(2, stu.getStuSex());
            ptmt.setInt(3, stu.getStuAge());
            ptmt.setString(4, stu.getHome_place());
            ptmt.setString(5, stu.getPhone_Num());
            ptmt.setString(6, stu.getEmail());
            ptmt.setString(7, stu.getStu_ID());
            ptmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
    public void delete(int stuID) {

        try {
            //注册JDBC驱动
            Class.forName(JDBC_DRIVER);

            //打开链接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //执行查询
            stat = conn.createStatement();
            String sql;
            sql = "DELETE FROM stu_mess WHERE id=?";
            System.out.println("删除学生信息");
            System.out.println(stuID);

            //预编译sql语句
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, stuID);
            psmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
