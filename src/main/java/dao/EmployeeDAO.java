package dao;

import java.util.List;
import model.Employee;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface EmployeeDAO {
	
	@SqlQuery("SELECT * FROM employee")
	@RegisterBeanMapper(Employee.class)
	List<Employee> getAllEmployees();
	
	@SqlUpdate("INSERT into "
			+ "employee(first_name, middle_name, last_name, address_line, post_code, email, nin, bank_sort_code, bank_account_no, salary) "
      + "VALUES (:e.first_name, :e.middle_name, :e.last_name, :e.address_line, :e.post_code, :e.email, :e.nin, :e.bank_sort_code, :e.bank_account_no, :e.salary)")
	@GetGeneratedKeys
  int insertEmployee(@BindBean("e") Employee employee);
	// the above used to return long and it worked, so change to that if there are errors
}
