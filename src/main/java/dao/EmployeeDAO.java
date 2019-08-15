package dao;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import model.Employee;

public interface EmployeeDAO {
	
	@SqlQuery("SELECT * FROM employee")
	@RegisterBeanMapper(Employee.class)
	List<Employee> getAllEmployees();
	
	@SqlUpdate("INSERT into "
			+ "employee(first_name, middle_name, last_name, address_line, post_code, email, nin, bank_sort_code, bank_account_no, salary) "
			+ "VALUES (:firstName, :middleName, :lastName, :addressLine, :postCode, :email, :nin, :bankSortCode, :bankAccountNo, :salary")
	@GetGeneratedKeys
	int insertEmployee(@BindBean Employee employee);
	// the above used to return long and it worked, so change to that if there are errors
}
