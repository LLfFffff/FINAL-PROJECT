import java.util.ArrayList;

public class CheckUser {
    ArrayList<Administrator> list = new ArrayList<Administrator>();
    Administrator administrator;
    public Administrator getAdministrator(int i ){
        return list.get(i);
    }
    public int find(String str){
        for(int i = 0;i<list.size();i++){
            if(list.get(i).password == str)
                return  i;

        } return -1;
    }
    public boolean insert(Administrator administrator){
        System.out.println();
        System.out.println("要添加的学生");
        System.out.println(administrator.fileString());
        System.out.println();
        if(find(administrator.username)!=-1){
            System.out.println("the user name already exists");
            return false;
        }
        list.add(administrator);
        return true;

    }

}
