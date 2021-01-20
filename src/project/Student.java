package project;

public class Student {
	private String Stu_ID;   
	private String Stu_Name;    
	private String sex;    
    private String age;       
    private String phone_number;  
    private String Email;  
    private String home_place;  
    
    
    public Student() {
    
    }
   
    public Student(String stu_ID,String stu_Name,String sex,String age,String phone_number,String email,String home_place) {
    	
    	this.Stu_ID=stu_ID;
    	this.Stu_Name=stu_Name;
    	this.sex=sex;
    	this.age=age;
    	this.phone_number=phone_number;
    	this.Email=email;
    	this.home_place=home_place;
    	
    }
    public String getStuID() {
    	return this.Stu_ID;
    }
    public void setStuID(String stu_ID) {
    	this.Stu_ID=stu_ID;
    }
    
    public String getName() {
    	return Stu_Name;
    }
    public void setName(String stu_Name) {
    	this.Stu_Name=stu_Name;
    }
    
    public String getSex() {
    	return this.sex;
    }
    public void setSex(String sex) {
    	this.sex=sex;
    }
    
    public String getAge() {
    	return this.age;
    }
    public void setAge(String age) {
    	this.age=age;
    }
    
    public String getphone_number() {
    	return this.phone_number;
    }
    public void setphone_nummber(String phone_number) {
    	this.phone_number=phone_number;
    }
    
    public String getHome_place() {
    	return this.home_place;
    }
    public void setHome_place(String home_place) {
    	this.home_place=home_place;
    }
    
    public String getEmail() {
    	return this.Email;
    }
    public void setEmail(String email) {
    	this.Email=email;
    }
    
    //Format of data input from the file "student number name gender age telephone number home address ID card number"  
    public String fileString()
	{
		return Stu_ID+" "+Stu_Name+" "+sex+" "+age+"  "+phone_number+"  "+Email+"  "+home_place;
	}

   
}
