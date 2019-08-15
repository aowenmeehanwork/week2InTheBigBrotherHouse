package wrapper;

import java.util.List;
import model.Department;
import model.Employee;
import model.SalesEmployee;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface EmployeeWrapper {

	@SqlQuery("SELECT * FROM employee")
	@RegisterBeanMapper(Employee.class)
	List<Employee> getAllEmployees();

	@SqlUpdate("INSERT into "
			+ "employee(first_name, middle_name, last_name, address_line, post_code, email, nin, bank_sort_code, bank_account_no, salary, department_id) "
			+ "VALUES (:e.first_name, :e.middle_name, :e.last_name, :e.address_line, :e.post_code, :e.email, :e.nin, :e.bank_sort_code, :e.bank_account_no, :e.salary, :e.department_id)")
	@GetGeneratedKeys
	int insertEmployee(@BindBean("e") Employee employee);
	// the above used to return long and it worked, so change to that if there are
	// errors

	@SqlUpdate("INSERT into sales_employee(employee_id, commission_rate, total_sales) "
			+ "VALUES (:s.employee_id, :s.commission_rate, :s.total_sales)")
	void insertSalesEmployee(@BindBean("s") SalesEmployee salesEmployee);

	@SqlUpdate("INSERT into department(name) VALUES (?)")
	@GetGeneratedKeys
	int insertDepartment(String department_name);

	@SqlQuery("SELECT * FROM department")
	@RegisterBeanMapper(Department.class)
	List<Department> getAllDepartments();
	
	@SqlQuery("SELECT first_name, middle_name, last_name, address_line, post_code, email, nin, bank_sort_code, bank_account_no, salary, employee.department_id, department.name "
			+ "FROM week2_company.employee inner join department on employee.department_id=department.department_id "
			+ "where department.name = ?")
	@RegisterBeanMapper(Employee.class)
	@RegisterBeanMapper(Department.class)
	List<Employee> getAllEmployeesFromDepartment(String name);

	@SqlQuery("SELECT department_id FROM department WHERE name = ?")
	@RegisterBeanMapper(Department.class)
	int getIdForDepartment(String department);
}
