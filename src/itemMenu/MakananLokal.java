package itemMenu;

import singleton.IdGenerator;

public class MakananLokal extends Makanan{

	private IdGenerator genId;
	
	public MakananLokal(String nama, String origin, int harga) {
		super("Minuman Lokal", nama, origin, harga);
		// TODO Auto-generated constructor stub
		genId = IdGenerator.getInstance();
		this.setId("MAL-"+ genId.generateNum());
	}

	
	

	
	
	

	

}
