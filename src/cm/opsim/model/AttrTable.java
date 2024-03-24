package cm.opsim.model;
/***********************************************************************
 * Module:  AttrTable.java
 * Author:  Romuald FOTSO
 * Purpose: Defines the Class AttrTable
 ***********************************************************************/

import java.util.*;

/** @pdOid 2f12b4dd-3ea7-4a31-9d51-766b03acd109 */
public class AttrTable  extends AbstractModel{
   /** @pdOid ef66b655-7bc7-4e5e-815c-072a1c0db2c2 */
   private int id = 0;

/**
 * @param id
 */
public AttrTable(int id) {
	super();
	this.id = id;
}

/**
 * 
 */
public AttrTable() {
	super();
}

/**
 * @return the id
 */
public int getId() {
	return id;
}

/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}
   
}