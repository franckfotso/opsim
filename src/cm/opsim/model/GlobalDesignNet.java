package cm.opsim.model;
/***********************************************************************
 * Module:  GlobalDesignNet.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class GlobalDesignNet
 ***********************************************************************/

import java.util.*;

/** @pdOid 776c24cb-076a-43c6-a98f-b04f2068b588 */
public class GlobalDesignNet extends AbstractModel{
   /** @pdOid 03999513-898e-4861-84a1-94ab6de09874 */
   private int id = 0;
   /** @pdOid f11b3fbe-97bf-43de-a678-1f0ec300cc2e */
   private String name;
   /** @pdOid 52f720b8-61bf-4422-9e0a-a2e824b917c9 */
   private int result;
   /** @pdOid ae1e4b23-cd31-422a-84f3-00e391ab1609 */
   private int simulation;
   /** @pdOid 3683db87-0410-4dc2-9133-0b5539d0c908 */
   private int map;
   
   
      
   /**
	 * @param id
	 * @param name
	 * @param result
	 * @param simulation
	 * @param map
	 */
	public GlobalDesignNet(int id, String name, int result, int simulation, int map) {
		super();
		this.id = id;
		this.name = name;
		this.result = result;
		this.simulation = simulation;
		this.map = map;
	}
	
	public GlobalDesignNet(){
		super();
		this.name = "";
		this.result = 0;
		this.simulation = 0;
		this.map = 0;
	}

/** @pdOid a175ce40-fa98-4e4f-a8de-76b8aa04f797 */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid d9c171a3-8445-476e-8013-de6f1f51d272 */
   public void setId(int newId) {
      id = newId;
   }
   
   /** @pdOid cefa230b-b9b6-4e7e-aeb7-e28855feedf8 */
   public String getName() {
      return name;
   }
   
   /** @param newName
    * @pdOid ae201b0a-1300-4253-adf5-dd0efaab7718 */
   public void setName(String newName) {
      name = newName;
   }
   
   /** @pdOid e76bdb90-f28b-458f-b7af-a57c6a8d1d61 */
   public int getResult() {
      return result;
   }
   
   /** @param newResult
    * @pdOid c6c6b30f-b990-4d25-a0af-f731e9df784d */
   public void setResult(int newResult) {
      result = newResult;
   }
   
   /** @pdOid a639a3a7-6a55-4fdd-92f7-ad22fa674d14 */
   public int getSimulation() {
      return simulation;
   }
   
   /** @param newSimulation
    * @pdOid 4e54bf9e-12a9-4dca-a4f3-3f47c4e40ee2 */
   public void setSimulation(int newSimulation) {
      simulation = newSimulation;
   }
   
   /** @pdOid 04459be9-9928-4b47-9601-59f67fe12552 */
   public int getMap() {
      return map;
   }
   
   /** @param newMap
    * @pdOid 77f754cb-97a9-4649-9759-09e592b54851 */
   public void setMap(int newMap) {
      map = newMap;
   }

}