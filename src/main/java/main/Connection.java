package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;


public class Connection {

  private static Jdbi jdbi = null;

  private Connection() {
    setup();
  }

  public static Jdbi getJdbi() {
    if (jdbi == null) {
      new Connection();
    }
    return jdbi;
  }

  public static void setup() {
    InputStream inputStream = Connection.class.getClassLoader()
        .getResourceAsStream("db.properties");
    Properties props = new Properties();

    try {
      props.load(inputStream);
    } catch (IOException e) {
      System.err.println("Unable to load db.properties file");
    }

    String user = props.getProperty("user");
    String password = props.getProperty("password");
    String host = props.getProperty("host");
    String database = props.getProperty("database");

    jdbi = Jdbi.create("jdbc:mysql://" + host + "/" + database, user, password);
    jdbi.installPlugin(new SqlObjectPlugin());

  }
}
