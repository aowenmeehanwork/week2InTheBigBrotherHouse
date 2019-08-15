package model;

public class Employee {

  private int employee_id;

  public Employee() {
  }

  public Employee(int employee_id) {
    this.employee_id = employee_id;
  }

  public int getEmployee_id() {
    return employee_id;
  }

  public void setEmployee_id(int employee_id) {
    this.employee_id = employee_id;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "employee_id=" + employee_id +
        '}';
  }
}
