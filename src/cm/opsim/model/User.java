package cm.opsim.model;
/***********************************************************************
 * Module:  User.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class User
 ***********************************************************************/

import java.util.*;

/** @pdOid 415e4a4d-2d4d-4f59-83e6-2f6f3f7fefef */
public class User extends AbstractModel{
   /** @pdOid 7f7cbfe6-d637-4365-b3cc-87c97ebb68e2 */
   private int id = 0;
   /** @pdOid 28317caf-2992-45a4-a50f-949a48030736 */
   private String name;
   /** @pdOid dc792234-9b64-47de-9bd3-1aa639999465 */
   private String username;
   /** @pdOid 99f3dcab-4351-448f-8e72-fa55e79a4e86 */
   private String password;
   /** @pdOid 543479bb-c9ea-4760-8d08-f8e7807f7123 */
   private String salt;
   /** @pdOid e81bd249-5329-4929-93d4-8f7703e00d0b */
   private boolean isEnabled;
   /** @pdOid ebaafa42-029d-46db-bb30-d5f9fcff2d9f */
   private Date createdDate;
   /** @pdOid 1dd5aeca-5f87-4316-a2ac-6141c9078d90 */
   private Date updateDate;
   
   /** @pdRoleInfo migr=no name=OptionSystem assc=parametre coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public java.util.Collection<OptionSystem> optionSystem;
   
   private Profil profil;
   

	
	/**
 * @param id
 * @param name
 * @param username
 * @param password
 * @param salt
 * @param isEnabled
 * @param createdDate
 * @param updateDate
 * @param optionSystem
 * @param profil
 */
public User(int id, String name, String username, String password, String salt,
		boolean isEnabled, Date createdDate, Date updateDate,
		Collection<OptionSystem> optionSystem, Profil profil) {
	super();
	this.id = id;
	this.name = name;
	this.username = username;
	this.password = password;
	this.salt = salt;
	this.isEnabled = isEnabled;
	this.createdDate = createdDate;
	this.updateDate = updateDate;
	this.optionSystem = optionSystem;
	this.profil = profil;
}

	public User(){
		super();
	}

		
	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

/** @pdOid 55b20858-8114-4b92-8e3f-45673737c5c9 */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid 62e352c8-04b1-4bd9-a48d-610b34b9ccd4 */
   public void setId(int newId) {
      id = newId;
   }
   
   /** @pdOid 36079404-9cb5-4f5c-93ce-2f0093576fa7 */
   public String getName() {
      return name;
   }
   
   /** @param newName
    * @pdOid 56176b93-28ab-446c-83ee-89440f1b042e */
   public void setName(String newName) {
      name = newName;
   }
   
   /** @pdOid e47793dc-e1db-4a00-a1fc-b6eb584fcc75 */
   public String getUsername() {
      return username;
   }
   
   /** @param newUsername
    * @pdOid c5c8de58-1769-41d8-aff9-cc5eb2fe314c */
   public void setUsername(String newUsername) {
      username = newUsername;
   }
   
   /** @pdOid dc217d50-5a1a-444b-9b9d-9618f198df23 */
   public String getPassword() {
      return password;
   }
   
   /** @param newPassword
    * @pdOid 5a43b3f5-bdf8-4c51-af39-06b68b74d69d */
   public void setPassword(String newPassword) {
      password = newPassword;
   }
   
   /** @pdOid daf6ce88-f728-469c-a498-ae60620881d6 */
   public String getSalt() {
      return salt;
   }
   
   /** @param newSalt
    * @pdOid a20cb60e-e128-406a-94d1-38be66006342 */
   public void setSalt(String newSalt) {
      salt = newSalt;
   }
   
   /** @pdOid a5bf8095-6ca0-47b2-b99b-74b939a10b92 */
   public boolean getIsEnabled() {
      return isEnabled;
   }
   
   /** @param newIsEnabled
    * @pdOid b4387e8c-3baf-4e22-8664-4b4f5565b842 */
   public void setIsEnabled(boolean newIsEnabled) {
      isEnabled = newIsEnabled;
   }
   
   /** @pdOid f2d9fd00-8c75-4cf0-b022-56c258492d10 */
   public Date getCreatedDate() {
      return createdDate;
   }
   
   /** @param newCreatedDate
    * @pdOid eb295f16-3cde-4e7a-b787-06c212eb3dea */
   public void setCreatedDate(Date newCreatedDate) {
      createdDate = newCreatedDate;
   }
   
   /** @pdOid 147b19dd-6f1b-4f20-b336-aafbf18b54f7 */
   public Date getUpdateDate() {
      return updateDate;
   }
   
   /** @param newUpdateDate
    * @pdOid 5c3bf5f7-df73-4b46-80aa-fcf3e4194221 */
   public void setUpdateDate(Date newUpdateDate) {
      updateDate = newUpdateDate;
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<OptionSystem> getOptionSystem() {
      if (optionSystem == null)
         optionSystem = new java.util.HashSet<OptionSystem>();
      return optionSystem;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorOptionSystem() {
      if (optionSystem == null)
         optionSystem = new java.util.HashSet<OptionSystem>();
      return optionSystem.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newOptionSystem */
   public void setOptionSystem(java.util.Collection<OptionSystem> newOptionSystem) {
      removeAllOptionSystem();
      for (java.util.Iterator iter = newOptionSystem.iterator(); iter.hasNext();)
         addOptionSystem((OptionSystem)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newOptionSystem */
   public void addOptionSystem(OptionSystem newOptionSystem) {
      if (newOptionSystem == null)
         return;
      if (this.optionSystem == null)
         this.optionSystem = new java.util.HashSet<OptionSystem>();
      if (!this.optionSystem.contains(newOptionSystem))
         this.optionSystem.add(newOptionSystem);
   }
   
   /** @pdGenerated default remove
     * @param oldOptionSystem */
   public void removeOptionSystem(OptionSystem oldOptionSystem) {
      if (oldOptionSystem == null)
         return;
      if (this.optionSystem != null)
         if (this.optionSystem.contains(oldOptionSystem))
            this.optionSystem.remove(oldOptionSystem);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllOptionSystem() {
      if (optionSystem != null)
         optionSystem.clear();
   }

}