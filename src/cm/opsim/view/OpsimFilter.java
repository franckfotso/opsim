/**
 * 
 */
package cm.opsim.view;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * @author Romuald FOTSO
 *
 */
public class OpsimFilter extends FileFilter{
	
	private String extension = null;
    private String description = null;
	
	/**
	 * @param extension
	 * @param description
	 */
	public OpsimFilter(String extension, String description) {
		super();
		this.extension = extension;
		this.description = description;
	}

	public OpsimFilter() {
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File file){
		//System.out.println((file.getName().endsWith(this.extension))?"ext OK":"ext NOK");
		return (file.isDirectory() || file.getName().endsWith(this.extension));
	}

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	@Override
	public String getDescription(){
		return this.extension + " - " + this.description;
	}

	public String getExtension() {
		return extension;
	}
}
