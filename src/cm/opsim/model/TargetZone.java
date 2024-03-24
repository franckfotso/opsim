package cm.opsim.model;
/***********************************************************************
 * Module:  TargetZone.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class TargetZone
 ***********************************************************************/

import java.util.*;

/** @pdOid ba9db691-e1ca-4658-884a-f823be49d8c7 */
public class TargetZone extends AbstractModel{
   /** @pdOid 704e122a-fb06-4d58-97e3-5a1510452acd */
   private int id;
   /** @pdOid 56f0576d-f12c-4dc6-8859-1846dd15689d */
   private String name;
   /** @pdOid c469c649-4f23-4b31-9914-56d18f992d74 */
   private String country;
   /** @pdOid 6d6410f3-713e-4410-a9cd-8a91e5aa7227 */
   private String province;
   /** @pdOid e273ef96-295c-4304-b207-e219cfb07fa8 */
   private String aim;
   /** @pdOid d9c4cb23-7262-4949-99bf-235a2472f566 */
   private String chiefProject;
   
   private double lon;
   private double lat;
   
   /**
 * 
 */
public TargetZone() {
	super();
	// TODO Auto-generated constructor stub
}

/**
 * @param id
 * @param name
 * @param country
 * @param province
 * @param aim
 * @param chiefProject
 * @param lon
 * @param lat
 */
public TargetZone(int id, String name, String country, String province,
		String aim, String chiefProject, double lon, double lat) {
	super();
	this.id = id;
	this.name = name;
	this.country = country;
	this.province = province;
	this.aim = aim;
	this.chiefProject = chiefProject;
	this.lon = lon;
	this.lat = lat;
}

public double getLon() {
		return lon;
	}
	
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	public double getLat() {
		return lat;
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}

/** @pdOid dbd9c375-0cc9-49b8-ae66-141512caab40 */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid b5d279ae-6a30-4dd2-9525-4b3467ac01c2 */
   public void setId(int newId) {
      id = newId;
   }
   
   /** @pdOid 98509556-03b7-4379-b597-340eb60a2cc7 */
   public String getName() {
      return name;
   }
   
   /** @param newName
    * @pdOid 8f67813f-0fea-4342-a843-9eadcc77787b */
   public void setName(String newName) {
      name = newName;
   }
   
   /** @pdOid b20c517e-0010-422c-8a77-d42af2a8e8ec */
   public String getCountry() {
      return country;
   }
   
   /** @param newCountry
    * @pdOid fe6174e4-9d47-4a2b-8448-c4da9e1ea668 */
   public void setCountry(String newCountry) {
      country = newCountry;
   }
   
   /** @pdOid be1b7fb1-d7ea-4e12-adec-3644dfb76063 */
   public String getProvince() {
      return province;
   }
   
   /** @param newProvince
    * @pdOid 5bd9bbe0-1c35-47c6-b7c6-9daec3040bf3 */
   public void setProvince(String newProvince) {
      province = newProvince;
   }
   
   /** @pdOid 901b6259-ccbb-49cb-9748-5cd0fec1ae6d */
   public String getAim() {
      return aim;
   }
   
   /** @param newAim
    * @pdOid 8fa590cc-c6e3-4458-bfdb-3f91f1f13b08 */
   public void setAim(String newAim) {
      aim = newAim;
   }
   
   /** @pdOid 02852913-1020-4c02-beb5-74ee66bbdf93 */
   public String getChiefProject() {
      return chiefProject;
   }
   
   /** @param newChiefProject
    * @pdOid 6a0fcfe4-ce76-40f1-8731-e80fb1549c28 */
   public void setChiefProject(String newChiefProject) {
      chiefProject = newChiefProject;
   }

}