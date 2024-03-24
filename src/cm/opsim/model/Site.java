/**
 * 
 */
package cm.opsim.model;

/**
 * @author Romuald FOTSO
 *
 */
public class Site extends AbstractModel{

	private int id;
	
	private double x;
	private double y;
	private double z;
	
	private long point_id;
	private double h_point;
	private long cluter_id;
	private long surface_id;
	private double surface_h;
	private double pxcenter;
	private double pycenter;
	private double h_dem;
	private double h_eff_r;
	private double surf_eff;
	private double tower_h;
	/**
	 * 
	 */
	public Site() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @param id
	 * @param x
	 * @param y
	 * @param z
	 * @param point_id
	 * @param h_point
	 * @param cluter_id
	 * @param surface_id
	 * @param surface_h
	 * @param pxcenter
	 * @param pycenter
	 * @param h_dem
	 * @param h_eff_r
	 * @param surf_eff
	 * @param tower_h
	 */
	public Site(int id, double x, double y, double z, long point_id,
			double h_point, long cluter_id, long surface_id, double surface_h,
			double pxcenter, double pycenter, double h_dem, double h_eff_r,
			double surf_eff, double tower_h) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
		this.point_id = point_id;
		this.h_point = h_point;
		this.cluter_id = cluter_id;
		this.surface_id = surface_id;
		this.surface_h = surface_h;
		this.pxcenter = pxcenter;
		this.pycenter = pycenter;
		this.h_dem = h_dem;
		this.h_eff_r = h_eff_r;
		this.surf_eff = surf_eff;
		this.tower_h = tower_h;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(double z) {
		this.z = z;
	}
	public long getPoint_id() {
		return point_id;
	}
	public void setPoint_id(long point_id) {
		this.point_id = point_id;
	}
	public double getH_point() {
		return h_point;
	}
	public void setH_point(double h_point) {
		this.h_point = h_point;
	}
	public long getCluter_id() {
		return cluter_id;
	}
	public void setCluter_id(long cluter_id) {
		this.cluter_id = cluter_id;
	}
	public long getSurface_id() {
		return surface_id;
	}
	public void setSurface_id(long surface_id) {
		this.surface_id = surface_id;
	}
	public double getSurface_h() {
		return surface_h;
	}
	public void setSurface_h(double surface_h) {
		this.surface_h = surface_h;
	}
	public double getPxcenter() {
		return pxcenter;
	}
	public void setPxcenter(double pxcenter) {
		this.pxcenter = pxcenter;
	}
	public double getPycenter() {
		return pycenter;
	}
	public void setPycenter(double pycenter) {
		this.pycenter = pycenter;
	}
	public double getH_dem() {
		return h_dem;
	}
	public void setH_dem(double h_dem) {
		this.h_dem = h_dem;
	}
	public double getH_eff_r() {
		return h_eff_r;
	}
	public void setH_eff_r(double h_eff_r) {
		this.h_eff_r = h_eff_r;
	}
	public double getSurf_eff() {
		return surf_eff;
	}
	public void setSurf_eff(double surf_eff) {
		this.surf_eff = surf_eff;
	}
	public double getTower_h() {
		return tower_h;
	}
	public void setTower_h(double tower_h) {
		this.tower_h = tower_h;
	}
}
