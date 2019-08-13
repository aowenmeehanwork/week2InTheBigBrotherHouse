package dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import model.Employee;

public interface EmployeeDAO {
	
	@SqlQuery("SELECT * FROM employee")
	@RegisterBeanMapper(Employee.class)
	List<Employee> getAllEmployees();
	
}
