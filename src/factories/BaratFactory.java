package factories;

import itemMenu.Makanan;
import itemMenu.MakananBarat;
import itemMenu.Minuman;
import itemMenu.MinumanBarat;

public class BaratFactory implements ItemMenuFactories{

	@Override
	public Makanan makeMakanan(String nama, String origin, int harga) {
		
		return new MakananBarat(nama, origin, harga);
	}

	@Override
	public Minuman makeMinuman(String nama, String origin, int harga) {
		
		return new MinumanBarat(nama, origin, harga);
	}

}
