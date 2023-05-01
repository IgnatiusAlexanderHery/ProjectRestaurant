package factories;

import itemMenu.Makanan;
import itemMenu.Minuman;

public interface ItemMenuFactories {
	public Makanan makeMakanan(String nama, String origin, int harga);
	public Minuman makeMinuman(String nama, String origin, int harga);
}	
