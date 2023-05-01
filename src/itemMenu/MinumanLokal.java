package itemMenu;

import singleton.IdGenerator;

public class MinumanLokal extends Minuman{


	private IdGenerator genId;
	
	public MinumanLokal(String nama, String origin, int harga) {
		super("Minuman Lokal",nama, origin, harga);
		// TODO Auto-generated constructor stub
		genId = IdGenerator.getInstance();
		this.setId("MIL-"+ genId.generateNum());
	}

	
	

	
	

	
	
	

}
