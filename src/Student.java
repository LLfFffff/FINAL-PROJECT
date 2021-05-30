public class Student {
    private String Stu_ID;
    private String Stu_Name;
    private String sex;
    private String age;
    private String phone_Num;
    private String Email;
    private String home_place;

    public Student() {

    }

    public Student(String stu_ID, String stu_Name, String sex, String age, String phone_Num, String email, String home_place) {
        Stu_ID = stu_ID;
        Stu_Name = stu_Name;
        this.sex = sex;
        this.age = age;
        this.phone_Num = phone_Num;
        Email = email;
        this.home_place = home_place;
    }

    public String getStu_ID() {
        return Stu_ID;
    }

    public void setStu_ID(String stu_ID) {
        Stu_ID = stu_ID;
    }

    public String getStu_Name() {
        return Stu_Name;
    }

    public void setStu_Name(String stu_Name) {
        Stu_Name = stu_Name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone_Num() {
        return phone_Num;
    }

    public void setPhone_Num(String phone_Num) {
        this.phone_Num = phone_Num;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getHome_place() {
        return home_place;
    }

    public void setHome_place(String home_place) {
        this.home_place = home_place;
    }

    public int getStuAge(){
        return Integer.parseInt(age);
    }

    public int getStuSex(){
        if(sex == "male"){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return Stu_ID + " " + Stu_Name + " " + sex + " " + age + "  " + phone_Num + "  " + Email + "  " + home_place;
    }

}
