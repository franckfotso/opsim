/**
 * 
 */
package cm.opsim.main;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JOptionPane;
/**
 * @author Romuald FOTSO
 *
 */
public class Bootstrapper {

	/**
	 * @param args
	 */
	public static void main(String[] args){
		bootstrapArcobjectsJar();
		try {
			OpsimMain.main(args);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Erreur fatale d�tect�e lors du d�marrage.\n" +
					"Bien vouloir re-installer OPSIM & les composants requis et ressayer.\n" +
					"NB: Certains composants requis n�cessitent la pr�sence d'une licence\n" +
					"ArcGIS Engine, Desktop ou Server (Basic, Standard ou Advanced).\n" +
					"Si l'erreur persite contacter les auteurs d'OPSIM pour signaler le bug.\n\n" +
					"Description: "+e,
					"OPSIM: V�rification des composants",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void bootstrapArcobjectsJar(){
    	//Get the ArcGIS Engine runtime, if it is available
    	String arcObjectsHome = System.getenv("AGSENGINEJAVA");
    	String opsim_home = System.getenv("OPSIM_HOME");
    	
    	//If the ArcGIS Engine runtime is not available, then we can try ArcGIS Desktop runtime
    	if(arcObjectsHome == null){
    		arcObjectsHome = System.getenv("AGSDESKTOPJAVA");
    	}
    	
    	//If no runtime is available, exit application gracefully
    	if(arcObjectsHome == null || opsim_home == null){
    		if(System.getProperty("os.name").toLowerCase().indexOf("win") > -1){
    			System.err.println("You must have ArcGIS Engine Runtime or ArcGIS Desktop " + 
    					"installed in order to execute this sample.");
    			System.err.println("Install one of the products above, then re-run this sample.");
    			System.err.println("Exiting execution of this sample...");
    			JOptionPane.showMessageDialog(null,"Le d�marrage OPSIM Pro. a echou� pour l'une des raisons suivantes:\n" +
    					"- Vous n'avez pas ArcGIS Engine Runtime ou ArcGIS Desktop install�;\n" +
    					"- Vous n'avez pas Correctement defini les variables d'environnement\n" +
    					"  syst�me: AGSENGINEJAVA, AGSDESKTOPJAVA, ou OPSIM_HOME.\n" +
    					"Pour y remedier � l'un de ces probl�mes, bien vouloir suivre\n" +
    					"correctement les instructions d'installation OPSIM Pro.",
						"OPSIM: V�rification des composants",JOptionPane.ERROR_MESSAGE);
    			System.exit(0);	
    		}else{
    			System.err.println("You must have ArcGIS Engine Runtime installed " + 
    					"in order to execute this sample.");
    			System.err.println("Install the product above, then re-run this sample.");
    			System.err.println("Exiting execution of this sample...");
    			JOptionPane.showMessageDialog(null,"Le d�marrage OPSIM Pro. a echou� pour l'une des raisons suivantes:\n" +
    					"- Vous n'avez pas ArcGIS Engine Runtime ou ArcGIS Desktop install�;\n" +
    					"- Vous n'avez pas Correctement defini les variables d'environnement\n" +
    					"  syst�me: AGSENGINEJAVA, AGSDESKTOPJAVA, ou OPSIM_HOME.\n" +
    					"Pour y remedier � l'un de ces probl�mes, bien vouloir suivre\n" +
    					"correctement les instructions d'installation OPSIM Pro.",
						"OPSIM: V�rification des composants",JOptionPane.ERROR_MESSAGE);
    			System.exit(0);	
    		}
    	}
        
    	//Obtain the relative path to the arcobjects.jar file
        String jarPath = arcObjectsHome + "java" + File.separator + "lib" +
            			 File.separator + "arcobjects.jar";

        //Create a new file
        File jarFile = new File(jarPath);
        
        //Test for file existence
        if(!jarFile.exists()){
        	System.err.println("The arcobjects.jar was not found in the following location: " +
                                                jarFile.getParent());
            System.err.println("Verify that arcobjects.jar can be located in the specified folder.");
            System.err.println("If not present, try uninstalling your ArcGIS software and reinstalling it.");
            System.err.println("Exiting execution of this sample...");
            JOptionPane.showMessageDialog(null,"Le d�marrage OPSIM Pro. a echou� pour la raison suivante:\n" +
					"OPSIM ne trouve pas l'API ArcObjects requis pour son fonctionnement\n" +
					"Pour y remedier � l'un de ce probl�me, bien vouloir suivre\n" +
					"correctement les instructions d'installation OPSIM Pro.",
					"OPSIM: V�rification des composants",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        //Helps load classes and resources from a search path of URLs
        URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class<URLClassLoader> sysclass = URLClassLoader.class;

        try{
        	Method method = sysclass.getDeclaredMethod("addURL", new Class[]{URL.class});
            method.setAccessible(true);
            method.invoke(sysloader, new Object[]{jarFile.toURI().toURL()});
        }catch (Throwable throwable){
            throwable.printStackTrace();
            System.err.println("Could not add arcobjects.jar to system classloader");
            System.err.println("Exiting execution of this sample...");
            System.exit(0);
        }
    }

}
