package userInterface;

import dao.EmployeeDAO;
import java.util.List;
import java.util.Scanner;
import main.Connection;
import model.Employee;

public class UserInterface {

  private static Connection connection;

  public static void start() {
    System.out.println("Hello whats your name:");

    Scanner scanner = new Scanner(System.in);

    String name = scanner.nextLine();
    System.out.println(" hey " + name);

    connection = Connection.getInstance();

    List<Employee> employees = connection.getJdbi().withHandle(handle -> {
      EmployeeDAO wrapper = handle.attach(EmployeeDAO.class);
      return wrapper.getAllEmployees();
    });

    employees.forEach(System.out::println);
  }
}
