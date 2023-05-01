package itemMenu;

public abstract class ItemMenu {
	
	private String id;
	private String type;
	private String nama;
	private String origin;
	private int harga;
	
	public ItemMenu(String type, String nama, String origin, int harga) {
		super();
		this.type = type;
		this.nama = nama;
		this.origin = origin;
		this.harga = harga;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public int getHarga() {
		return harga;
	}
	public void setHarga(int harga) {
		this.harga = harga;
	}
	
	
	
	
	
}
