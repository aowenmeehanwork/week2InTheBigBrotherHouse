package model;

public class Employee {

	private int employee_id;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String address_line;
	private String post_code;
	private String email;
	private String nin;
	private String bank_sort_code;
	private String bank_account_no;
	private double salary;

	public Employee() {
	}
	
	

	public int getEmployee_id() {
		return employee_id;
	}



	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}



	public String getFirst_name() {
		return first_name;
	}



	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}



	public String getMiddle_name() {
		return middle_name;
	}



	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}



	public String getLast_name() {
		return last_name;
	}



	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}



	public String getAddress_line() {
		return address_line;
	}



	public void setAddress_line(String address_line) {
		this.address_line = address_line;
	}



	public String getPost_code() {
		return post_code;
	}



	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getNin() {
		return nin;
	}



	public void setNin(String nin) {
		this.nin = nin;
	}



	public String getBank_sort_code() {
		return bank_sort_code;
	}



	public void setBank_sort_code(String bank_sort_code) {
		this.bank_sort_code = bank_sort_code;
	}



	public String getBank_account_no() {
		return bank_account_no;
	}



	public void setBank_account_no(String bank_account_no) {
		this.bank_account_no = bank_account_no;
	}



	public double getSalary() {
		return salary;
	}



	public void setSalary(double salary) {
		this.salary = salary;
	}



	@Override
	public String toString() {
		return "Employee{" + "employee_id=" + employee_id + '}';
	}
}
