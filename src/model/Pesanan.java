package model;

import java.util.ArrayList;

import itemMenu.ItemMenu;
import singleton.IdGenerator;

public class Pesanan {

	private IdGenerator genId;
	private String id;
	private String customerId;
	private String chefId;
	private ArrayList<ItemMenu> menuList;
	private ArrayList<Integer> jumlahMenu;
	private int totalPrice;
	private boolean isServe;
	


	public Pesanan(String customerId,String chefId,ArrayList<ItemMenu> menuList,ArrayList<Integer> jumlahMenu,int totalPrice) {
		super();
		this.customerId = customerId;
		this.chefId = chefId;
		this.menuList = menuList;
		this.jumlahMenu = jumlahMenu;
		this.totalPrice = totalPrice;
		this.isServe = false;
		genId = IdGenerator.getInstance();
		this.setId("P-"+ genId.generateNum());
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getcustomerId() {
		return customerId;
	}
	public void setcustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getchefId() {
		return chefId;
	}
	public void setchefId(String chefId) {
		this.chefId = chefId;
	}
	public ArrayList<ItemMenu> getmenuList() {
		return menuList;
	}
	public void setmenuList(ArrayList<ItemMenu> menuList) {
		this.menuList = menuList;
	}
	public ArrayList<Integer> getjumlahMenu() {
		return jumlahMenu;
	}
	public void setjumlahMenu(ArrayList<Integer> jumlahMenu) {
		this.jumlahMenu = jumlahMenu;
	}
	public int gettotalPrice() {
		return totalPrice;
	}
	public void settotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public boolean getisServe() {
		return isServe;
	}
	public void setisServe() {
		this.isServe = true;
	}

	public void printDetail(){
		System.out.println("Customer Id : "  + customerId);
		System.out.println("Invoice Id  : " +this.getId());
		int total;
		System.out.println("=======================================================");
		for (int i = 0 ; i < jumlahMenu.size() ;i++) {

			total = this.getmenuList().get(i).getHarga() * this.getjumlahMenu().get(i);
			System.out.format("| %-3s | %-20s | %-6s | %-2s | %-8s |\n", i + 1, this.getmenuList().get(i).getNama(), this.getmenuList().get(i).getHarga(), this.getjumlahMenu().get(i), total);
		}
		System.out.println("=======================================================");
		System.out.format("| %-40s | %-8s |\n","Total Pembayaran: ",totalPrice);
		System.out.println("=======================================================");
	}
}
