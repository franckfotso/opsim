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
package cm.opsim.view;

import java.io.IOException;

import com.esri.arcgis.carto.IEnumSpatialBookmark;
import com.esri.arcgis.carto.IMapBookmarks;
import com.esri.arcgis.carto.ISpatialBookmark;
import com.esri.arcgis.controls.HookHelper;
import com.esri.arcgis.systemUI.IMultiItem;
import com.esri.arcgis.interop.AutomationException;

public class SpatialBookmarks implements IMultiItem
{

	private HookHelper hookHelper = null;

	private int activeBookmarkIndex = -1;

	public SpatialBookmarks()
	{
		
	}

	public int getItemBitmap(int arg0) throws IOException, AutomationException
	{
		return 0;
	}

	public String getItemCaption(int arg0) throws IOException, AutomationException
	{
		try
		{
			// Get the bookmarks of the focus map
			IMapBookmarks mapBookmarks = (IMapBookmarks) hookHelper.getFocusMap();

			// Get bookmarks enumerator
			IEnumSpatialBookmark enumSpatialBookmarks = mapBookmarks.getBookmarks();
			enumSpatialBookmarks.reset();

			// Loop through the bookmarks to get bookmark names
			ISpatialBookmark spatialBookmark = enumSpatialBookmarks.next();

			int bookmarkCount = 0;
			while (spatialBookmark != null)
			{
				// Get the correct bookmark
				if (bookmarkCount == arg0)
				{
					// Return the bookmark name
					return spatialBookmark.getName();
				}
				bookmarkCount = bookmarkCount + 1;
				spatialBookmark = enumSpatialBookmarks.next();
			}
		}
		catch (Exception e)
		{
		}
		return "";
	}

	public boolean isItemChecked(int index) throws IOException, AutomationException
	{
		// the current active boookmark should be checked
		return index == activeBookmarkIndex;
	}

	public boolean isItemEnabled(int arg0) throws IOException, AutomationException
	{
		return true;
	}

	public void onItemClick(int index) throws IOException, AutomationException
	{
		// set the current active bookmark
		this.activeBookmarkIndex = index;
		IMapBookmarks mapBookmarks = null;
		try {
			// Get the bookmarks of the focus map
			mapBookmarks = (IMapBookmarks) hookHelper.getFocusMap();

			// Get bookmarks enumerator
			if(mapBookmarks != null){
				IEnumSpatialBookmark enumSpatialBookmarks = mapBookmarks.getBookmarks();
				enumSpatialBookmarks.reset();
	
				// Loop through the bookmarks to get bookmark to zoom to
				ISpatialBookmark spatialBookmark = enumSpatialBookmarks.next();
	
				int bookmarkCount = 0;
				while (spatialBookmark != null)
				{
					// Get the correct bookmark
					if (bookmarkCount == index)
					{
						// Zoom to the bookmark
						spatialBookmark.zoomTo(hookHelper.getFocusMap());
						// Refresh the map
						hookHelper.getActiveView().refresh();
					}
					bookmarkCount = bookmarkCount + 1;
					spatialBookmark = enumSpatialBookmarks.next();
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int onPopup(Object hook) throws IOException, AutomationException
	{

		// Set the hookHelper
		try
		{
			hookHelper = new HookHelper();
			hookHelper.setHookByRef(hook);
		}
		catch (IOException ex)
		{
			System.out.println("Exception");
			throw new RuntimeException(ex);
		}

		int bookmarkCount = 0;
		IMapBookmarks mapBookmarks = null;
		try {
			// Get the bookmarks of the focus map
			mapBookmarks = (IMapBookmarks) hookHelper.getFocusMap();

			// Get bookmarks enumerator
			if(mapBookmarks != null){
				IEnumSpatialBookmark enumSpatialBookmarks = mapBookmarks.getBookmarks();
				enumSpatialBookmarks.reset();
	
				// Loop through the bookmarks to count them
				ISpatialBookmark spatialBookmark = enumSpatialBookmarks.next();
	
				while (spatialBookmark != null)
				{
					bookmarkCount = bookmarkCount + 1;
					spatialBookmark = enumSpatialBookmarks.next();
				}
			}			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Return the number of multiitems
		return bookmarkCount;
	}

	public String getName() throws IOException, AutomationException
	{
		return "Signets spéciaux";
	}

	public String getMessage() throws IOException, AutomationException
	{
		return "Signets référencant une position de la carte";
	}

	public String getHelpFile() throws IOException, AutomationException
	{
		return null;
	}

	public int getHelpContextID() throws IOException, AutomationException
	{
		return 0;
	}

	public String getCaption() throws IOException, AutomationException
	{
		return "Signets spéciaux";
	}

}
