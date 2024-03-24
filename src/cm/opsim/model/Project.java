package cm.opsim.model;

/***********************************************************************
 * Module:  Project.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class Project
 ***********************************************************************/

import java.util.*;

/** @pdOid 04d2c952-01ec-408b-b5d2-42955d8b48c2 */
public class Project extends AbstractModel{
   /** @pdOid b9a02f95-1601-4ece-a2e9-bbe2f704f847 */
   private int id;
   /** @pdOid a952ff25-5363-44cd-b538-2b247845ffe3 */
   private String name = null;
   /** @pdOid dd558265-0507-4480-ac58-5ebccdfc0a2e */
   private double progress = 0.0;
   /** @pdOid f6320023-e6e9-4bbf-9276-4562fb1bff65 */
   private boolean isOver = false;
   /** @pdOid 1dedde3f-7edb-4b82-848c-342c1b03125f */
   private Date createdDate;
   /** @pdOid 47e431e6-a127-48f2-836e-002768c12ef6 */
   private Date updatedDate;
   
   /** @pdRoleInfo migr=no name=User assc=estCreer mult=1..1 */
   public User user;
   /** @pdRoleInfo migr=no name=Template assc=correspondA mult=1..1 */
   public Template template;
   /** @pdRoleInfo migr=no name=GlobalDesignNet assc=aboutitA mult=1..1 */
   public GlobalDesignNet globalDesignNet;
   /** @pdRoleInfo migr=no name=FreqBand assc=utilise1 mult=1..1 */
   public FreqBand freqBand;
   /** @pdRoleInfo migr=no name=Map assc=generer coll=java.util.Collection impl=ArrayList mult=1..* */
   public java.util.Collection<Map> map;
   /** @pdRoleInfo migr=no name=SystemLog assc=sauvegarde coll=java.util.Collection impl=ArrayList mult=1..* */
   public java.util.Collection<SystemLog> systemLog;
   /** @pdRoleInfo migr=no name=Parameter assc=presente mult=1..1 */
   public Parameter parameter;
   /** @pdRoleInfo migr=no name=Configuration assc=dispose mult=1..1 */
   public Configuration configuration;
   /** @pdRoleInfo migr=no name=Planification assc=realise mult=1..1 */
   public Planification planification;
   /** @pdRoleInfo migr=no name=Simulation assc=engendre coll=java.util.Collection impl=ArrayList mult=1..* */
   public java.util.Collection<Simulation> simulation;
   
   private ProjInterne projInterne = null;
   private ProjExterne projExterne = null;
   
   private ArrayList<Site> list_site = new ArrayList<Site>();
   
   /**
	 * @param id
	 * @param name
	 * @param progress
	 * @param isOver
	 * @param createdDate
	 * @param updatedDate
	 * @param user
	 * @param template
	 * @param globalDesignNet
	 * @param freqBand
	 * @param map
	 * @param systemLog
	 * @param parameter
	 * @param configuration
	 * @param planification
	 * @param simulation
	 */
	public Project(int id, String name, double progress, boolean isOver,
			Date createdDate, Date updatedDate, User user, Template template,
			GlobalDesignNet globalDesignNet, FreqBand freqBand,
			Collection<Map> map, Collection<SystemLog> systemLog,
			Parameter parameter, Configuration configuration,
			Planification planification, Collection<Simulation> simulation) {
		super();
		this.id = id;
		this.name = name;
		this.progress = progress;
		this.isOver = isOver;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.user = user;
		this.template = template;
		this.globalDesignNet = globalDesignNet;
		this.freqBand = freqBand;
		this.map = map;
		this.systemLog = systemLog;
		this.parameter = parameter;
		this.configuration = configuration;
		this.planification = planification;
		this.simulation = simulation;
	}

	public Project(){
	  super(); 
	}
   
   public ProjInterne getProjInterne() {
		return projInterne;
	}

	public void setProjInterne(ProjInterne projInterne) {
		this.projInterne = projInterne;
	}

	public ProjExterne getProjExterne() {
		return projExterne;
	}

	public void setProjExterne(ProjExterne projExterne) {
		this.projExterne = projExterne;
	}

	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}

public void setUser(User user){
	   this.user = user;
   }
   
   public User getUser(){
	   return this.user;
   }
   
   public void setTemplate(Template tpl){
	   this.template = tpl;
   }
   
   public Template getTemplate(){
	   return this.template;
   }
   
   public void setGlobalDesignNet(GlobalDesignNet gdr){
	   this.globalDesignNet = gdr;
   }
   
   public GlobalDesignNet getGlobalDesignNet(){
	   return this.globalDesignNet;
   }
   
   public void setFreqBand(FreqBand fb){
	   this.freqBand = fb;
   }
   
   public FreqBand getFreqBand(){
	   return this.freqBand;
   }
   
   public void setParameter(Parameter param){
	   this.parameter = param;
   }
   
   public Parameter getParameter(){
	   return this.parameter;
   }
   
   public void setConfiguration(Configuration conf){
	   this.configuration = conf;
   }
   
   public Configuration getConfiguration(){
	   return this.configuration;
   }
   
   public void setPlanification(Planification plan){
	   this.planification = plan;
   }
   
   public Planification getPlanification(){
	   return this.planification;
   }
   
   
   /** @pdOid cf44f6e3-975e-4be9-a55b-abbe617e46fc */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid 97f6268b-5d93-4b9f-af8c-f50933e2f4ae */
   public void setId(int newId) {
      id = newId;
   }
   
   /** @pdOid 4ab35eb3-e63f-4768-9c54-3f8eb3e7d89f */
   public String getName() {
      return name;
   }
   
   /** @param newName
    * @pdOid 756114e3-6182-495d-9f88-bdcecaa2e552 */
   public void setName(String newName) {
      name = newName;
   }
   
   /** @pdOid 785c97aa-d602-4f7f-8c8a-8a64fb8c5b10 */
   public double getProgress() {
      return progress;
   }
   
   /** @param newProgress
    * @pdOid 14ad2165-e0af-42d4-b628-24c9bb91d821 */
   public void setProgress(double newProgress) {
      progress = newProgress;
   }
   
   /** @pdOid bee239e8-2410-410f-b4b0-b581df65ae02 */
   public boolean getIsOver() {
      return isOver;
   }
   
   /** @param newIsOver
    * @pdOid 796d8edd-ce26-40b1-b6b3-de0063c1b521 */
   public void setIsOver(boolean newIsOver) {
      isOver = newIsOver;
   }
   
   /** @pdOid f40a9522-d6e1-4d8c-b3d3-1c941ab4ab24 */
   public Date getCreatedDate() {
      return createdDate;
   }
   
   /** @param newCreatedDate
    * @pdOid 7d4d2bb5-a657-4c39-8578-277e08dbac1f */
   public void setCreatedDate(Date newCreatedDate) {
      createdDate = newCreatedDate;
   }
   
   /** @pdOid e9e4e263-0e20-4798-b7ba-9caf7a399b76 */
   public Date getUpdatedDate() {
      return updatedDate;
   }
   
   /** @param newUpdatedDate
    * @pdOid b7057ef3-ea9f-4885-aacd-b869f1a27f0f */
   public void setUpdatedDate(Date newUpdatedDate) {
      updatedDate = newUpdatedDate;
   }
   
   

   public java.util.Collection<Map> getMap() {
		return map;
	}
	
	public void setMap(java.util.Collection<Map> map) {
		this.map = map;
	}

/** @pdGenerated default getter */
   public java.util.Collection<SystemLog> getSystemLog() {
      if (systemLog == null)
         systemLog = new ArrayList<SystemLog>();
      return systemLog;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorSystemLog() {
      if (systemLog == null)
         systemLog = new ArrayList<SystemLog>();
      return systemLog.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newSystemLog */
   public void setSystemLog(java.util.Collection<SystemLog> newSystemLog) {
      removeAllSystemLog();
      for (java.util.Iterator iter = newSystemLog.iterator(); iter.hasNext();)
         addSystemLog((SystemLog)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newSystemLog */
   public void addSystemLog(SystemLog newSystemLog) {
      if (newSystemLog == null)
         return;
      if (this.systemLog == null)
         this.systemLog = new ArrayList<SystemLog>();
      if (!this.systemLog.contains(newSystemLog))
         this.systemLog.add(newSystemLog);
   }
   
   /** @pdGenerated default remove
     * @param oldSystemLog */
   public void removeSystemLog(SystemLog oldSystemLog) {
      if (oldSystemLog == null)
         return;
      if (this.systemLog != null)
         if (this.systemLog.contains(oldSystemLog))
            this.systemLog.remove(oldSystemLog);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllSystemLog() {
      if (systemLog != null)
         systemLog.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Simulation> getSimulation() {
      if (simulation == null)
         simulation = new ArrayList<Simulation>();
      return simulation;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorSimulation() {
      if (simulation == null)
         simulation = new ArrayList<Simulation>();
      return simulation.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newSimulation */
   public void setSimulation(java.util.Collection<Simulation> newSimulation) {
      removeAllSimulation();
      for (java.util.Iterator iter = newSimulation.iterator(); iter.hasNext();)
         addSimulation((Simulation)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newSimulation */
   public void addSimulation(Simulation newSimulation) {
      if (newSimulation == null)
         return;
      if (this.simulation == null)
         this.simulation = new ArrayList<Simulation>();
      if (!this.simulation.contains(newSimulation))
         this.simulation.add(newSimulation);
   }
   
   /** @pdGenerated default remove
     * @param oldSimulation */
   public void removeSimulation(Simulation oldSimulation) {
      if (oldSimulation == null)
         return;
      if (this.simulation != null)
         if (this.simulation.contains(oldSimulation))
            this.simulation.remove(oldSimulation);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllSimulation() {
      if (simulation != null)
         simulation.clear();
   }

	public ArrayList<Site> getList_site() {
		return list_site;
	}
	
	public void setList_site(ArrayList<Site> list_site) {
		this.list_site = list_site;
	}
}