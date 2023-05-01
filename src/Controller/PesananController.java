package Controller;


import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

import factories.BaratFactory;
import factories.ItemMenuFactories;
import factories.LokalFactory;
import itemMenu.ItemMenu;
import itemMenu.Makanan;
import itemMenu.MakananBarat;
import itemMenu.MakananLokal;
import itemMenu.Minuman;
import itemMenu.MinumanBarat;
import itemMenu.MinumanLokal;
import main.Menu;
import model.Chef;
import model.Customer;
import model.Pesanan;


public class PesananController {
	private Scanner input;
	private Menu showMenu;
	private ArrayList<Customer> listCustomer;
	private ArrayList<Chef> listChef;
	private ArrayList<ItemMenu> listMenu;
	private ArrayList<Pesanan> listPesanan;
	private ItemMenuFactories menuFactory;
	
	
	public PesananController(OwnerController data) {
		input = new Scanner(System.in);
		showMenu = new Menu();
		listChef = data.getListChef();
		listCustomer = data.getListCustomer();
		listMenu = data.getListMenu();
		listPesanan = data.getListPesanan();
	}
	
	

	public void runProgram(Customer customer) {
		
		int option = 0;
		do {
			try {
				System.out.println();
				showMenu.Pesanan();
				System.out.println("Choice >> ");
				option = input.nextInt(); input.nextLine();
				if(option == 1) {
					//buat pesan
					createPesanan(customer);
				}else if(option == 2) {
					//historyPesanan
					historyPesanan(customer, null);
				}else if(option == 3){
					//exit
					System.out.println();
					return;
				}
				else {
					showMenu.Error();
				}
			} catch (Exception e) {
				showMenu.Error();
				input.nextLine();
			}
		}while(option !=3);
	}
	

	public void createPesanan(Customer customer){
		String noIdMenu;
		int jumlah;
		int total;
		boolean orderMore=false;
		
		String customerId = customer.getId();
		String chefId = "";
		ArrayList<ItemMenu> orderMenu = new ArrayList<>();
		ArrayList<Integer> qty = new ArrayList<>();
		int totalPrice = 0;
		
		//nampilin menu
		
			int no=1;
			for (ItemMenu menu : listMenu) {
				if(menu instanceof MinumanLokal) {
					System.out.print(menu.getId() + " ");
					print(no, menu.getNama(),menu.getOrigin(),menu.getHarga());
					no++;
				}
				
			}
			for (ItemMenu menu : listMenu) {
				if(menu instanceof MinumanBarat) {
					System.out.print(menu.getId() + " ");
					 print(no, menu.getNama(),menu.getOrigin(),menu.getHarga());
					 no++;
				}
			}
			for (ItemMenu menu : listMenu) {
				if(menu instanceof MakananLokal) {
					System.out.print(menu.getId() + " ");
					 print(no, menu.getNama(),menu.getOrigin(),menu.getHarga());
					 no++;
				}
			}
			for (ItemMenu menu : listMenu) {
				if(menu instanceof MakananBarat) {
					System.out.print(menu.getId() + " ");
					 print(no, menu.getNama(),menu.getOrigin(),menu.getHarga());
					 no++;
				}
			}
		do{
		boolean flag=true;
		ItemMenu chooseMenu = null;
			do{
				flag=true;
				System.out.print("Choose Menu to order: ");
				noIdMenu = input.nextLine();
				System.out.print("how much you want to order[max:99]: ");
				jumlah = input.nextInt(); input.nextLine();
					for (ItemMenu menu : listMenu) {
						if(menu.getId().equalsIgnoreCase(noIdMenu)) {
							chooseMenu = menu;
							flag = false;
							if(!(flag || !(jumlah>0 && jumlah<100))){
								totalPrice += (menu.getHarga() * jumlah );

							}
							break;
						}
					}
		
					System.out.println(totalPrice);
		}while(flag || !(jumlah>0 && jumlah<100));

		orderMenu.add(chooseMenu);
		qty.add(jumlah);
		String loop;
		do{
			System.out.println("Do you want to order more? [Y/N]");
			loop = input.nextLine();
		}while(!(loop.equals("Y") || loop.equals("N")));

		if(loop.equalsIgnoreCase("Y")){
				orderMore = true;
		}else{
			orderMore = false;
		}
		
		}while(orderMore);
		
		Pesanan pesanan = new Pesanan(customerId, null, orderMenu, qty, totalPrice);
		
		
		listPesanan.add(pesanan);
		System.out.println("Total Price : " + totalPrice);
		showMenu.printContinue();
	}

	private void print(int no, String nama, String origin, int harga) {
		System.out.println(no +" " + nama + " " + origin + " " + "Rp." + harga);
		
	}
	
	public void historyPesanan(Customer customer, Chef chef){
		
		if(chef == null){
			for (Pesanan pesanan : listPesanan) {
				if(pesanan.getcustomerId() == customer.getId()){
					pesanan.printDetail();
				}
			}
		}
		else if(chef != null){
			for (Pesanan pesanan : listPesanan) {
					if(pesanan.getisServe() == false){
						pesanan.printDetail();
						System.out.println("Belum di Serve");
						System.out.println("=======================================================");
					}
			}
		}
	}

	public void chefUpdatePesanan(Chef chef){
		if(checkPesanan()){
			System.out.println("Tidak Ada Pesanan...");
			showMenu.printContinue();
		}else{

		historyPesanan(null, chef);

		String idToServe = "";
		boolean flag = true;
		do{

			System.out.println("Select Pesanan to serve [Invoice Id] : ");
			idToServe = input.nextLine();
		
			for (Pesanan pesanan  : listPesanan) {
						if(pesanan.getId().equalsIgnoreCase(idToServe)) {
								pesanan.setisServe();
								flag = false;
								System.out.println("Pesanan " + pesanan.getId() + " Telah di serve");
								chef.setServe();
								showMenu.printContinue();
								break;
							}
			}
			if(flag){
				System.out.println("=======================================================");
				System.out.println("Invoice Id wrong or not found!");
				System.out.println("=======================================================");
			}
		}while(flag);
			
		}
		
	}

	public boolean checkPesanan(){
		
		for (Pesanan pesanan : listPesanan) {
			if(pesanan.getisServe()==false){
				return false;
			}
		}
			return true;
		
	}


}
