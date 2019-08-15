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

  }

  private enum Statement {
    INSERT,
    SELECT,
    EXIT
  }
}
