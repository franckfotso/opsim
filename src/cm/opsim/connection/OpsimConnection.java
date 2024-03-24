package cm.opsim.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import com.mysql.jdbc.Driver;

import javax.swing.JOptionPane;

public class OpsimConnection {
	
	private static String url =	"jdbc:mysql://localhost/opsim";
	private static String user = "root";
	private static String passwd = "";
	private static Connection connect;
	
	public static Connection getInstance(){
		if(connect == null){
			try {
				String driverName = "com.mysql.jdbc.Driver";
				Class.forName(driverName);
				
				connect = DriverManager.getConnection(url+"?"+"user="+user+"&password="+passwd);
			}
			catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,"Erreur fatale détectée lors du démarrage.\n" +
						"Bien vouloir vérifier que MySQL est correctement installé et démarré.\n" +
						"NB: Rassurer vous que les paramètres de connexion à MySQL soient tels\n" +
						"qu'indiquer dans le manuel d'installation et de configuration d'OPSIM.\n" +
						"Si l'erreur persite contacter les auteurs d'OPSIM pour signaler le bug.\n\n" +
						"Description: "+e,
						"OPSIM: Vérification des composants",JOptionPane.ERROR_MESSAGE);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,"Erreur fatale détectée lors du démarrage.\n" +
						"Description: "+e,
						"OPSIM: Vérification des composants",JOptionPane.ERROR_MESSAGE);
			}
			
		} 
		return connect;
	} 
}
