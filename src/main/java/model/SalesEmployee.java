package model;

public class SalesEmployee extends Employee {
	private int employee_id;
	private double commission_rate;
	private double total_sales;
	
	public int getEmployee_id() {
		return employee_id;
	}
	
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	
	public double getCommission_rate() {
		return commission_rate;
	}
	
	public void setCommission_rate(double commission_rate) {
		this.commission_rate = commission_rate;
	}
	
	public double getTotal_sales() {
		return total_sales;
	}
	
	public void setTotal_sales(double total_sales) {
		this.total_sales = total_sales;
	}
	
}
