/* Copyright 2013 ESRI
* 
* All rights reserved under the copyright laws of the United States
* and applicable international laws, treaties, and conventions.
* 
* You may freely redistribute and use this sample code, with or
* without modification, provided you include the original copyright
* notice and use restrictions.
* 
* See the use restrictions at <your ArcGIS install location>/DeveloperKit10.2/userestrictions.txt.
* 
*/
package cm.opsim.view.cmds;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.esri.arcgis.carto.AOIBookmark;
import com.esri.arcgis.carto.IActiveView;
import com.esri.arcgis.carto.IMapBookmarks;
import com.esri.arcgis.controls.BaseCommand;
import com.esri.arcgis.controls.HookHelper;

public class CreateBookmarkCommand extends BaseCommand
{

	private HookHelper hookHelper = null;

	public CreateBookmarkCommand()
	{
		caption = "Créer...";
		toolTip = "Créer signet";
		enabled = true;
	}

	public void onCreate(Object obj)
	{
		try
		{
			hookHelper = new HookHelper();
			hookHelper.setHookByRef(obj);
		}
		catch (IOException ex)
		{
			System.out.println("Exception");
			throw new RuntimeException(ex);
		}
	}

	public void onClick()
	{
		super.onClick();

		Runnable r = new Runnable()
		{
			public void run()
			{
				// Get Bookmark name
				String sName = JOptionPane.showInputDialog("Entrer le nom du signet:");
				if (sName != null)
				{
					try
					{
						// Get the focus map
						IActiveView activeView = (IActiveView) hookHelper.getFocusMap();

						// Create a new bookmark
						AOIBookmark aoibookmark = new AOIBookmark();

						// Set the location to the current extent of the focus map
						aoibookmark.setLocationByRef(activeView.getExtent());

						// Set the bookmark name
						aoibookmark.setName(sName);

						// Get the bookmark collection of the focus map
						IMapBookmarks mapBookmarks = (IMapBookmarks) hookHelper.getFocusMap();

						// Add the bookmark to the bookmarks collection
						mapBookmarks.addBookmark(aoibookmark);
					}
					catch (Exception e)
					{
						
					}
				}
			}
		};

		// SwingUtilities.invokeLater method be used to put the change of
		// state operation on the event-dispatching thread
		SwingUtilities.invokeLater(r);

	}

}