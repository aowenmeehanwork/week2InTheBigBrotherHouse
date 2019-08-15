package dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import model.City;
import model.Employee;

public interface EmployeeDAO {
	
	@SqlQuery("SELECT * FROM employee")
	@RegisterBeanMapper(Employee.class)
	List<Employee> getAllEmployees();
	
	@SqlUpdate("INSERT into "
			+ "employee(first_name, middle_name, last_name, address_line, post_code, email, nin, bank_account_no, bank_sort_code, salary) "
			+ "VALUES (:firstName, :middleName, :lastName, :addressLine, :post_Code, :email, :nin, :bankAccountNo, :bankSortCode, :salary")
	@GetGeneratedKeys
	long insertEmployee(@BindBean Employee employee);
}
