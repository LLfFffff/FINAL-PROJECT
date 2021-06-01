import java.util.ArrayList;
import java.util.LinkedList;

public class CheckUser {
    static LinkedList<Administrator> list = new LinkedList<Administrator>();
    Administrator administrator;
    public Administrator getAdministrator(int i){
        return list.get(i);
    }
    public int find(String str){
        for(int i = 0;i<list.size();i++)
            if(list.get(i).getUsername().equals(str))
                return  i;
         return -1;
    }
    public boolean insert(Administrator administrator){
        System.out.println();
        System.out.println(administrator.fileString());
        System.out.println();

        if(find(administrator.username)!=-1){
            System.out.println("用户名已存在");
            return false;
        }
        list.add(administrator);
        System.out.println("在checkbox中有了："+list.get(0).fileString());
        return true;

    }

}
