package Phase1;

public class Location {
	private String name;
	private int males, females;
	
	public Location(String name) {
		super();
		this.name = name;
		males=females=0;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMales() {
		return males;
	}
	public void setMales(int males) {
		this.males = males;
	}
	public int getFemales() {
		return females;
	}
	public void setFemales(int females) {
		this.females = females;
	}
	
	
}
