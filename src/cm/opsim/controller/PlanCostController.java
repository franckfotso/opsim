/**
 * 
 */
package cm.opsim.controller;

import java.util.ArrayList;
import java.util.Hashtable;

import cm.opsim.model.AbstractModel;
import cm.opsim.model.CapexOpexPlanif;
import cm.opsim.model.Parameter;
import cm.opsim.model.Project;
import cm.opsim.model.RCapex;
import cm.opsim.model.ROpex;
import cm.opsim.model.Result;

/**
 * @author Romuald FOTSO
 *
 */
public class PlanCostController extends AbstractController{

	/**
	 * @param model
	 */
	public PlanCostController(AbstractModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cm.opsim.controller.AbstractController#control()
	 */
	@Override
	void control() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cm.opsim.controller.AbstractController#calResult(cm.opsim.model.AbstractModel)
	 */
	@Override
	public Result calResult(AbstractModel model) {
		Project proj = (Project) model;
		Parameter param = proj.getParameter();
		Result res = proj.getPlanification().getResult();	
		CapexOpexPlanif capexOpexPlanif = (CapexOpexPlanif) this.model;
		
		RCapex rcapex = res.getRcapex();
		Hashtable<String,Double> hashRCapex = capexOpexPlanif.calResultCapex(param, proj);
		rcapex.setConceptCost(hashRCapex.get("cout_conception"));
		rcapex.setInvMatLogi(hashRCapex.get("inv_mat_logi"));
		rcapex.setDeployCost(hashRCapex.get("cout_deploiement"));
		rcapex.setLogisCost(hashRCapex.get("cout_logistique"));
		rcapex.setCapexTotal(hashRCapex.get("total_capex"));
		res.setRcapex(rcapex);
		
		ArrayList<ROpex> listROpex = res.getListROpex();
		Hashtable<String,ArrayList<Double>> hashROpex = capexOpexPlanif.calResultOpex(param, proj);
		ArrayList<Double> listInCom = hashROpex.get("listInCom");
		ArrayList<Double> listOpex = hashROpex.get("listOpex");
		ArrayList<Double> listBenef = hashROpex.get("listBenef");
		
		ROpex ropex = null;
		for(int i=0;i<5; i++){
			System.out.println("listROpex size: "+listROpex.size());
			if(listROpex.size() >= 5)ropex = listROpex.get(i);
			else ropex = new ROpex();
			ropex.setYear(i+1);
			ropex.setInCom(listInCom.get(i));
			ropex.setOpex(listOpex.get(i));
			ropex.setBenef(listBenef.get(i));
			if(listROpex.size() >= 5)listROpex.set(i, ropex);
			else listROpex.add(ropex);
			if(listROpex.size() == 5)System.out.println("For: END");
		}		
		res.setListROpex(listROpex);
		
		return res;
	}

}
