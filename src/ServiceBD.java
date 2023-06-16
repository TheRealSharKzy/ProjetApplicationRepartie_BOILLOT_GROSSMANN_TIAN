import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ServiceBD extends Remote {

    String getRestaurants() throws SQLException, IOException;
}
