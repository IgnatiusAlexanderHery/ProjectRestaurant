package itemMenu;

import singleton.IdGenerator;

public class MinumanBarat extends Minuman{
	

	private IdGenerator genId;
	
	public MinumanBarat(String nama, String origin, int harga) {
		super("Minuman Barat", nama, origin, harga);
		genId = IdGenerator.getInstance();
		this.setId("MIB-"+ genId.generateNum());
	}

	
	

	

	

}
