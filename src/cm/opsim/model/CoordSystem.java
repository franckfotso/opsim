package cm.opsim.model;
/***********************************************************************
 * Module:  CoordSystem.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class CoordSystem
 ***********************************************************************/

import java.util.*;

/** @pdOid 41865332-c47c-4136-a3fe-3c50f62209e4 */
public class CoordSystem  extends AbstractModel{
   /** @pdOid 3d73e946-4b3c-4132-b283-43953d458591 */
   private int id;
   /** @pdOid 9df89143-6d96-48de-9769-bfb028aa0317 */
   private String projCoordSyst;
   /** @pdOid d5fb6269-0c82-4502-888c-110b40083d71 */
   private int projection;
   /** @pdOid 6ddaca94-d962-474d-874e-7e19556f4328 */
   private int falseEasting;
   /** @pdOid bd0d43a7-faca-4994-ae82-776c3e81e55e */
   private int falseNorthing;
   /** @pdOid c5d62965-4744-4e1d-aef4-a69fecd44bb7 */
   private int centralMedian;
   /** @pdOid 7eb5bb2b-ab50-4749-a6d7-5effdf7b74d3 */
   private int scaleFactor;
   /** @pdOid 89c4d900-a5c1-488e-961a-902e1cf34421 */
   private int latitudeOfOrigin;
   /** @pdOid 14855263-1618-4678-a48a-455b3f7f1bed */
   private int linearUnit;
   /** @pdOid c30cef28-b779-43bb-a813-c8b69e2b4fde */
   private int geoCoordSyst;
   /** @pdOid 0649ea8f-67d9-44ae-a334-75f9cbd5951f */
   private int datum;
   /** @pdOid e8214120-3942-4fba-a567-bfe7d6bc2513 */
   private int primeMeridian;
   /** @pdOid d6e0af38-c383-426e-b2a5-ed54dc4403bd */
   private int angularUnit;
   /** @pdOid f9024439-b2f0-4bee-9359-51468d2960fb */
   private int createdDate;
   /** @pdOid 61b13a9f-e33d-4546-8cf1-7bf620b3bed7 */
   private int updatedDate;
   
   /** appartient */
   /** @pdRoleInfo migr=no name=Layer assc=appartient mult=1..1 */
   public Layer layer;
   
   /** @pdOid 37f40263-5306-4991-a49b-9806c4c935b7 */
   public int getId() {
      return id;
   }
   
   /** @param newId
    * @pdOid 45fce0b9-f3c9-481a-925c-646b7250c622 */
   public void setId(int newId) {
      id = newId;
   }
   
   /** @pdOid 2b1e3a7c-a645-401a-997f-5a1781521659 */
   public String getProjCoordSyst() {
      return projCoordSyst;
   }
   
   /** @param newProjCoordSyst
    * @pdOid 653bcc7c-df20-4e74-88cc-7fd00e4042dc */
   public void setProjCoordSyst(String newProjCoordSyst) {
      projCoordSyst = newProjCoordSyst;
   }
   
   /** @pdOid 4596b3a7-24f7-4d56-bb1f-cc5b67496bd5 */
   public int getProjection() {
      return projection;
   }
   
   /** @param newProjection
    * @pdOid f210e947-8f34-4d92-8128-d2d87b9b330e */
   public void setProjection(int newProjection) {
      projection = newProjection;
   }
   
   /** @pdOid bd7588f2-95cc-4291-a9bb-825a9d194eb5 */
   public int getFalseEasting() {
      return falseEasting;
   }
   
   /** @param newFalseEasting
    * @pdOid a80dfc7e-fc79-4be8-8e2e-d083a80d9c93 */
   public void setFalseEasting(int newFalseEasting) {
      falseEasting = newFalseEasting;
   }
   
   /** @pdOid c304f10d-83c7-4090-b6c1-1c154bceabc4 */
   public int getFalseNorthing() {
      return falseNorthing;
   }
   
   /** @param newFalseNorthing
    * @pdOid 657d45c4-1068-4d45-9c7f-4a4e8a26906c */
   public void setFalseNorthing(int newFalseNorthing) {
      falseNorthing = newFalseNorthing;
   }
   
   /** @pdOid e84908cf-7eec-440e-816c-309fd5a8ed5a */
   public int getCentralMedian() {
      return centralMedian;
   }
   
   /** @param newCentralMedian
    * @pdOid 93870b0b-382e-412f-b70d-50ad20126b14 */
   public void setCentralMedian(int newCentralMedian) {
      centralMedian = newCentralMedian;
   }
   
   /** @pdOid 877f6b3e-dae8-494e-96b4-1ec0a10aaa03 */
   public int getScaleFactor() {
      return scaleFactor;
   }
   
   /** @param newScaleFactor
    * @pdOid 0cfb9ad8-cadc-456e-a1c3-a3530af374fe */
   public void setScaleFactor(int newScaleFactor) {
      scaleFactor = newScaleFactor;
   }
   
   /** @pdOid b58d6f3d-9500-4e44-b9c9-b62524d45c33 */
   public int getLatitudeOfOrigin() {
      return latitudeOfOrigin;
   }
   
   /** @param newLatitudeOfOrigin
    * @pdOid aa2bbead-f1e0-4b82-a4e3-6ddbbc803f32 */
   public void setLatitudeOfOrigin(int newLatitudeOfOrigin) {
      latitudeOfOrigin = newLatitudeOfOrigin;
   }
   
   /** @pdOid 0fa09c82-3dcb-4109-a4b4-23e4df9347a1 */
   public int getLinearUnit() {
      return linearUnit;
   }
   
   /** @param newLinearUnit
    * @pdOid c00be7e4-145e-4f3a-a70c-7565f553dd55 */
   public void setLinearUnit(int newLinearUnit) {
      linearUnit = newLinearUnit;
   }
   
   /** @pdOid 58ca6043-ce44-4f25-ae9c-ca7f851b73d0 */
   public int getGeoCoordSyst() {
      return geoCoordSyst;
   }
   
   /** @param newGeoCoordSyst
    * @pdOid cebcb865-90d5-458b-8374-241983e37822 */
   public void setGeoCoordSyst(int newGeoCoordSyst) {
      geoCoordSyst = newGeoCoordSyst;
   }
   
   /** @pdOid 3267af9a-fb22-45c8-8982-11911f292472 */
   public int getDatum() {
      return datum;
   }
   
   /** @param newDatum
    * @pdOid feedcc35-96c3-4c0c-bdb6-ac9c65c2d771 */
   public void setDatum(int newDatum) {
      datum = newDatum;
   }
   
   /** @pdOid 0a550f2b-1fa3-4fc6-8760-cd5f954aefb0 */
   public int getPrimeMeridian() {
      return primeMeridian;
   }
   
   /** @param newPrimeMeridian
    * @pdOid f34147e1-c8b2-4016-af23-3b9ed7cacd87 */
   public void setPrimeMeridian(int newPrimeMeridian) {
      primeMeridian = newPrimeMeridian;
   }
   
   /** @pdOid 47914a07-7f58-493f-b712-95e7ace5fe71 */
   public int getAngularUnit() {
      return angularUnit;
   }
   
   /** @param newAngularUnit
    * @pdOid fbd9ed15-0b40-484d-b925-2171aee3f49e */
   public void setAngularUnit(int newAngularUnit) {
      angularUnit = newAngularUnit;
   }
   
   /** @pdOid e5c9c420-2569-4806-8f64-389989033f99 */
   public int getCreatedDate() {
      return createdDate;
   }
   
   /** @param newCreatedDate
    * @pdOid c1c8e5ad-4fe5-4133-848b-34cd68099889 */
   public void setCreatedDate(int newCreatedDate) {
      createdDate = newCreatedDate;
   }
   
   /** @pdOid 7fb2b048-ca48-44dc-88a3-f9fc02350dcc */
   public int getUpdatedDate() {
      return updatedDate;
   }
   
   /** @param newUpdatedDate
    * @pdOid 8157a12d-51b3-40ec-997e-fb07b9c340be */
   public void setUpdatedDate(int newUpdatedDate) {
      updatedDate = newUpdatedDate;
   }

}