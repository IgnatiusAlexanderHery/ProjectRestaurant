package factories;

import itemMenu.Makanan;
import itemMenu.MakananLokal;
import itemMenu.Minuman;
import itemMenu.MinumanLokal;

public class LokalFactory implements ItemMenuFactories{

	@Override
	public Makanan makeMakanan(String nama, String origin, int harga) {
		
		return new MakananLokal(nama, origin, harga);
	}

	@Override
	public Minuman makeMinuman(String nama, String origin, int harga) {
		
		return new MinumanLokal(nama, origin, harga);
	}

}
