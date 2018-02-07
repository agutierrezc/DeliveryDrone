package co.com.s4n.deliveryDrone.model;

public enum Orientation {
	
	NORTH("Norte"),
	SOUTH("Sur"),
	EAST("Oriente"),
	WEST("Occidente");
	
	private String name;
	
	Orientation(String name) {
		this.name = name;
	}
	
	public Orientation getLeftSide(){
		if(this.equals(NORTH)){
			return WEST;
		} else if(this.equals(WEST)){
			return SOUTH;
		} else if(this.equals(SOUTH)){
			return EAST;
		} else{
			return NORTH;
		}
	}
	
	public Orientation getRightSide(){
		if(this.equals(NORTH)){
			return EAST;
		} else if(this.equals(EAST)){
			return SOUTH;
		} else if(this.equals(SOUTH)){
			return WEST;
		} else{
			return NORTH;
		}
	}
	
	@Override
	public String toString() {
		return "dirección " + this.name;
	}
}
