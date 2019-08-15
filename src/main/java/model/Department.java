package model;

public class Department {
	private int department_id;
	private String name;
	
	public int getDepartment_id() {
		return department_id;
	}
	
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Department{" +
		        "department_id=" + department_id +
		        ", first_name='" + name +
		        '}';
	}
	
}
