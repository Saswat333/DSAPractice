package misc;

import java.util.ArrayList;
import java.util.HashMap;

public class ManagerEmployeeTree {
    public static void main(String[] args) {
        //List of employee
        ArrayList<Employee> empList = new ArrayList<>();
        Employee emp1 = new Employee(1,"Eric",0);
        Employee emp2 = new Employee(2,"Jack",1);
        Employee emp3 = new Employee(3,"Viral",2);
        Employee emp4 = new Employee(4,"Micheal",2);
        Employee emp5 = new Employee(5,"Nitesh",1);
        Employee emp6 = new Employee(6,"George",4);
        Employee emp7 = new Employee(7,"Ryan",2);
        Employee emp8 = new Employee(7,"Alex",3);

        HashMap<Integer, EmployeeNode> hm = new HashMap<>();
        //search and populate the
        for(Employee emp: empList){
            int empId = emp.empId;
            if(!hm.containsKey(empId)){
                hm.put(empId,null);
            }
            EmployeeNode node = hm.get(empId);

        }
    }
}
