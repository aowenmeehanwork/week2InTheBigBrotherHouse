package userInterface;

import dao.EmployeeDAO;
import java.util.List;
import java.util.Scanner;
import main.Connection;
import model.Employee;
import org.jdbi.v3.core.Jdbi;

public class UserInterface {

  private static Jdbi connection = Connection.getJdbi();
  private static Scanner scanner = new Scanner(System.in);

  public static void start() {
    Statement input = null;
    while (input != Statement.EXIT) {
      System.out.println("What operation would you like to perform:");
      for (Statement value : Statement.values()) {
        System.out.println("\t" + value.name());
      }

      String in = scanner.nextLine();
      try {
        input = Statement.valueOf(in.toUpperCase());
        switch (input) {
          case INSERT:
            insert();
            break;
          case SELECT:
            select();
            break;
          case EXIT:
            break;
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid input: " + in);
      }
    }
  }

  private static void select() {
    List<Employee> employees = connection.withHandle(handle -> {
      EmployeeDAO wrapper = handle.attach(EmployeeDAO.class);
      return wrapper.getAllEmployees();
    });

    employees.forEach(System.out::println);

  }

  private static void insert() {
    System.out.println("Do you want to insert:");
    System.out.println("\tEmployee");
    String in = scanner.nextLine();
    if (in.toUpperCase().equals("EMPLOYEE")) {
      insertGenericEmployee();
    } else {
      System.out.println("Please input a valid option");
    }
  }

  private static void insertGenericEmployee() {
    
	  String employeeName;
	  String employeeSurname;
	  String employeeOtherNames;
	  String employeeAddress;
	  String employeePostcode;
	  String employeeEmail;
	  String employeeNINumber;
	  String employeeBankAccountNo;
	  String employeeBankSortCode;
	  float employeeStartingSalary;
	  
	  System.out.println("Please enter employee first name: ");
	  do {
	    employeeName = scanner.nextLine();
	    if (!(employeeName.length() > 0 && employeeName.length() < 50)) {
	      System.out.println("Ensure that name is not null and less than 50 characters long");
	    }
	  } while (!(employeeName.length() > 0 && employeeName.length() < 50));
	  
	  System.out.println("Please enter employee surname: ");
	  employeeSurname = scanner.nextLine();
	  
	  System.out.println("Please enter employee other names: ");
	  employeeOtherNames = scanner.nextLine();
	  
	  System.out.println("Please enter employee address: ");
	  do {
	    employeeAddress = scanner.nextLine();
		if (!employeeAddress.isEmpty()) {
			System.out.println("Please ensure that the address is not empty");
		}
	  } while ((employeeAddress.isEmpty()));

	  System.out.println("Enter postcode");
	  employeePostcode = scanner.nextLine();
	  
	  System.out.println("Please enter employee email: ");
	  employeeEmail = scanner.nextLine();
	  
	  System.out.println("Please enter employee NI Number: ");
	  employeeNINumber = scanner.nextLine();
	  
	  System.out.println("Please enter employee bank account number: ");
	  employeeBankAccountNo = scanner.nextLine();
	  
	  System.out.println("Please enter employee bank account sort code (without '-' in number: ");
	  employeeBankSortCode = scanner.nextLine();
	  
	  System.out.println("Please enter employee starting salary: ");
	  employeeStartingSalary = scanner.nextInt();
	  
	  Employee newEmployee = new Employee();
	  
	  newEmployee.setFirstName(employeeName);
	  newEmployee.setLastName(employeeSurname);
	  newEmployee.setMiddleName(employeeOtherNames);
	  newEmployee.setAddressLine(employeeAddress);
	  newEmployee.setPostCode(employeePostcode);
	  newEmployee.setEmail(employeeEmail);
	  newEmployee.setNin(employeeNINumber);
	  newEmployee.setBankAccountNo(employeeBankAccountNo);
	  newEmployee.setBankSortCode(employeeBankSortCode);
	  newEmployee.setSalary(employeeStartingSalary);
	  
	  connection.withHandle(handle -> {
		  EmployeeDAO wrapper = handle.attach(EmployeeDAO.class);
		  return wrapper.insertEmployee(newEmployee);
	  });
	  
	  
  }

  private enum Statement {
    INSERT,
    SELECT,
    EXIT
  }
}
