package misc;

public class EmployeeNode {
    int empId;
    String empName;
    int managerId;

    EmployeeNode nextNode;

    public EmployeeNode(int empId, String empName, int managerId, EmployeeNode node) {
        this.empId = empId;
        this.empName = empName;
        this.managerId = managerId;
        this.nextNode = node;
    }
}
