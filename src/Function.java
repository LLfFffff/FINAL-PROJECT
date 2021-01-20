import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ark-f
 */
class Function {

    //The number of students in this program is not large, with ArrayList ArrayList, when the student data is much, consider using a linked list
    ArrayList<Student> arry=new ArrayList<Student>();
    public Function()           //Read the file and store the student information in the file in the array list
    {
        this.readfile();
    }
    //Search student information according to student number, find returned student number, cannot find return -1
    public int find(String str)
    {
        for (int i = 0; i<arry.size(); i++)
            if (arry.get(i).getStu_ID().equals(str))
                return i;//Is equal to the position in the array list
        return -1;
    }

    //Modify student information
    public void update(Student stu) {
        int flag=find(stu.getStu_ID());    //Find out if it exists
        arry.set(flag, stu);		   //Replace the student information in the flag
    }
    //read file
    public boolean readfile() {
        String t=null;
        try{
            FileReader f1 = new FileReader("student.txt");
            BufferedReader br=new BufferedReader(f1);
            arry.clear();    //	Clear the data from the original array list
            while ((t= br.readLine())!= null)
            {
                String [] s=t.split("\\s+");
                Student st=new Student(s[0],s[1],s[2],s[3],s[4],s[5],s[6]);//Note that if the file does not have seven strings per line, an error occurs
                arry.add(st);
                System.out.println("readfile：");
                System.out.println(s[0]);
            }
            f1.close();
            br.close();
            return true;
        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }
    }
    //Add student information
    public boolean add(Student stu)
    {
        System.out.println();
        System.out.println("Student to add");
        System.out.println(stu.fileString());
        System.out.println();
        if (find(stu.getStu_ID())!=-1)
            return false;
        arry.add(stu);
        return true;
    }

    //Write note that each line must have seven elements, otherwise read the file and determine if there is an error when the error, no input with - instead
    public boolean writefile() {
        FileWriter fw=null;
        BufferedWriter out=null;
        try {
            fw = new FileWriter("student.txt");
            out = new BufferedWriter(fw);

            for(int i=0;i<arry.size();i++){
                String s=arry.get(i).fileString();
                System.out.println("Arraylist data：");
                System.out.println(arry.get(i).fileString());
                out.write(s);
                out.newLine();
            }
            out.close();
            fw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Delete the student information and modify the contents of the file
    public boolean delete(String s)	//Add student memorization information
    {
        int pos=find(s);
        if (pos==-1)
            return false;

        arry.remove(pos);
        return true;
    }
}
