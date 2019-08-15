package userInterface;

import dao.EmployeeDAO;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import main.Connection;
import model.Department;
import model.Employee;
import org.jdbi.v3.core.Jdbi;

public class UserInterface {

  private static Jdbi connection = Connection.getJdbi();
  private static Scanner scanner = new Scanner(System.in);

  public static void start() {
    Statement input = null;
    do {
      System.out.println("What operation would you like to perform:");
      for (Statement value : Statement.values()) {
        System.out.println("\t" + value.name());
      }

      String in;
      while (true) {
        in = scanner.nextLine();
        if (!in.isEmpty()) {
          break;
        }
      }

      try {
        input = Statement.valueOf(in.toUpperCase());
        switch (input) {
          case ADD:
            insert();
            break;
          case SHOW:
            select();
            break;
          case EXIT:
            break;
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid input: " + in);
        e.printStackTrace();
      }
    } while (input != Statement.EXIT);

  }

  private static void select() {
    List<String> departments = connection.withHandle(handle -> {
      EmployeeDAO wrapper = handle.attach(EmployeeDAO.class);
      List<Department> deps = wrapper.getAllDepartments();
      return deps.stream().map(Department::getName).collect(Collectors.toList());
    });

    System.out.println("Pick a department to view employees of: ");
    departments.forEach(s -> System.out.println("\t" + s));
    Scanner s = new Scanner(System.in);
    String input = s.nextLine();

    if (departments.contains(input)) {
      List<Employee> employees = connection.withHandle(handle -> {
        EmployeeDAO wrapper = handle.attach(EmployeeDAO.class);
        return wrapper.getAllEmployeesFromDepartment(input);
      });

      System.out.println("Employees in " + input + ":");
      employees.forEach(System.out::println);

    }
  }

  private static void insert() {
    System.out.println("What would you like to insert:");
    System.out.println("\tEmployee");
    System.out.println("\tDepartment");
    String in = scanner.nextLine();
    if (in.toUpperCase().equals("EMPLOYEE")) {
      insertGenericEmployee();
    } else if (in.toUpperCase().equals("DEPARTMENT")) {
      insertDepartment();
    } else {
      System.out.println("Please input a valid option");
    }
  }

  private static void insertDepartment() {
    String departmentName;

    System.out.println("Please insert a department name:");
    do {
      departmentName = scanner.nextLine();
    } while (departmentName.isEmpty());

    String finalDepartmentName = departmentName;
    connection.withHandle(handle -> {
      EmployeeDAO wrapper = handle.attach(EmployeeDAO.class);
      return wrapper.insertDepartment(finalDepartmentName);
    });
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
    int departmentId = 0;
	  
	  System.out.println("Please enter employee first name: ");
	  do {
	    employeeName = scanner.nextLine();
	    if (!(employeeName.length() > 0 && employeeName.length() < 50)) {
        System.out.println("Ensure that first name is not null and less than 50 characters long");
	    }
	  } while (!(employeeName.length() > 0 && employeeName.length() < 50));

    System.out.println("Please enter employee middle names: ");
    employeeOtherNames = scanner.nextLine();

    System.out.println("Please enter employee surname: ");
    do {
      employeeSurname = scanner.nextLine();
      if (!(employeeSurname.length() > 0 && employeeSurname.length() < 50)) {
        System.out.println("Ensure that surname is not null and less than 50 characters long");
      }
    } while (!(employeeSurname.length() > 0 && employeeSurname.length() < 50));

    System.out.println("Please enter employee address: ");
	  do {
	    employeeAddress = scanner.nextLine();
      if (employeeAddress.isEmpty()) {
			System.out.println("Please ensure that the address is not empty");
		}
	  } while ((employeeAddress.isEmpty()));

	  System.out.println("Enter postcode");
    do {
      employeePostcode = scanner.nextLine();
      if (!validPostcode(employeePostcode)) {
        System.out.println("Please enter a valid postcode.");
      }
    } while (!validPostcode(employeePostcode));

	  System.out.println("Please enter employee email: ");
    do {
      employeeEmail = scanner.nextLine();
      if (!validEmail(employeeEmail)) {
        System.out.println("Please enter valid email");
      }
    } while (!validEmail(employeeEmail));
	  
	  System.out.println("Please enter employee NI Number: ");
    do {
      employeeNINumber = scanner.nextLine();
      employeeNINumber = employeeNINumber.replaceAll(" ", "");
      if (!validNINumber(employeeNINumber)) {
        System.out.println("Please enter a valid NI number.");
      }
    } while (!validNINumber(employeeNINumber));

	  System.out.println("Please enter employee bank account number: ");
    do {
      employeeBankAccountNo = scanner.nextLine();
      employeeBankAccountNo = employeeBankAccountNo.replaceAll(" ", "");
      if (!validBankAccountNubmer(employeeBankAccountNo)) {
        System.out.println("Please enter a valid bank number");
      }
    } while (!validBankAccountNubmer(employeeBankAccountNo));

	  System.out.println("Please enter employee bank account sort code (without '-' in number: ");
    do {
      employeeBankSortCode = scanner.nextLine();
      if (!validBankSortCode(employeeBankSortCode)) {
        System.out.println("Please enter a valid bank sort code");
      }
    } while (!validBankSortCode(employeeBankSortCode));

	  System.out.println("Please enter employee starting salary: ");
    do {
      employeeStartingSalary = scanner.nextFloat();
      if (!validSalary(employeeStartingSalary)) {
        System.out.println("Please input a number greater than or equal to 0");
      }
    } while (!validSalary(employeeStartingSalary));

    List<Department> departments = connection.withHandle(handle -> {
      EmployeeDAO wrapper = handle.attach(EmployeeDAO.class);
      return wrapper.getAllDepartments();
    });

    System.out.println("Please enter a valid department:");
    departments.stream().map(Department::getName).forEach(s -> System.out.println("\t" + s));

    String in;
    do {
      in = scanner.nextLine();
      if (!validDepartment(in, departments)) {
        System.out.println("Please enter a valid department");
      }
    } while (!validDepartment(in, departments));

    for (Department department : departments) {
      if (department.getName().equals(in)) {
        departmentId = department.getDepartment_id();
      }
    }

	  Employee newEmployee = new Employee();

    newEmployee.setFirst_name(employeeName);
    newEmployee.setLast_name(employeeSurname);
    newEmployee.setMiddle_name(employeeOtherNames);
    newEmployee.setAddress_line(employeeAddress);
    newEmployee.setPost_code(employeePostcode);
	  newEmployee.setEmail(employeeEmail);
	  newEmployee.setNin(employeeNINumber);
    newEmployee.setBank_account_no(employeeBankAccountNo);
    newEmployee.setBank_sort_code(employeeBankSortCode);
	  newEmployee.setSalary(employeeStartingSalary);
    newEmployee.setDepartment_id(departmentId);
	  
	  connection.withHandle(handle -> {
		  EmployeeDAO wrapper = handle.attach(EmployeeDAO.class);
		  return wrapper.insertEmployee(newEmployee);
	  });
	  
	  
  }

  private static boolean validDepartment(String in, List<Department> departments) {
    for (Department department : departments) {
      if (department.getName().equals(in)) {
        return true;
      }
    }

    return false;
  }

  private static boolean validSalary(float employeeStartingSalary) {
    return employeeStartingSalary >= 0;
  }

  private static boolean validBankSortCode(String employeeBankSortCode) {
    return employeeBankSortCode.matches("^(\\d){6}$");
  }

  private static boolean validBankAccountNubmer(String employeeBankAccountNo) {
    return employeeBankAccountNo.matches("[0-9]+") && employeeBankAccountNo.length() == 8;
  }

  private static boolean validNINumber(String employeeNINumber) {
    return employeeNINumber.toUpperCase()
        .matches("^[A-CEGHJ-PR-TW-Z]{1}[A-CEGHJ-NPR-TW-Z]{1}[0-9]{6}[A-DFM]{0,1}$");
  }

  private static boolean validEmail(String employeeEmail) {
    return employeeEmail.toUpperCase().matches("[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,}");
  }

  private static boolean validPostcode(String employeePostcode) {
    return employeePostcode.toUpperCase()
        .matches("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$");
  }

  private enum Statement {
    ADD,
    SHOW,
    EXIT
  }
}
