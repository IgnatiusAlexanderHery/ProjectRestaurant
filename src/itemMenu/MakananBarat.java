package itemMenu;

import singleton.IdGenerator;

public class MakananBarat extends Makanan{
	
	private IdGenerator genId;

	public MakananBarat(String nama, String origin, int harga) {
		super("Makanan Barat", nama, origin, harga);
		// TODO Auto-generated constructor stub
		genId = IdGenerator.getInstance();
		this.setId("MAB-"+ genId.generateNum());
	}

	
	

	
	
	
	
	
	
	
}
