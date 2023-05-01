package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import itemMenu.ItemMenu;
import main.Menu;
import model.Chef;
import model.Customer;
import model.Pesanan;

public class ChefController {
	private Scanner input;
	private Menu showMenu;
	private ArrayList<Customer> listCustomer;
	private ArrayList<Chef> listChef;
	private ArrayList<ItemMenu> listMenu;
	private ArrayList<Pesanan> listPesanan;
	private PesananController showPesanan;
	private OwnerController data;
	
	public ChefController(OwnerController data) {
		input = new Scanner(System.in);
		showMenu = new Menu();
		listChef = data.getListChef();
		listCustomer = data.getListCustomer();
		listMenu = data.getListMenu();
		listPesanan = data.getListPesanan();
		showPesanan = new PesananController(data);
		this.data = data;
	}

	public void runProgram(Chef chef) {
		int option = 0;
		do {
			try {
				chef = listChef.get(getIndexByID(chef.getId()));
				System.out.println();
				System.out.println("Welcome "+ chef.getName());
				showMenu.MenuChef();
				System.out.print("Choice >> ");
				option = input.nextInt(); input.nextLine();
				if(option == 1) {
					updateProfile(chef.getId());
				}else if (option == 2) {
					Pesanan(chef);
				}else if (option == 3) {
					System.out.println();
					return;
				}else{
					showMenu.Error();
				}
			} catch (Exception e) {
				showMenu.Error();
				input.nextLine();
			}	
		} while(option != 3);
		System.out.println();
	}

	private void Pesanan(Chef chef) {
		// TODO Auto-generated method stub
		showPesanan.chefUpdatePesanan(chef);
	}

	private int getIndexByID(String id) {
		for(int i = 0; i < listChef.size(); i++) {
			if(listChef.get(i).getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}
	
	private boolean checkDuplicateUsername(String data) {
		for(int i = 0; i < listChef.size(); i++) {
			if(listChef.get(i).getUsername().equals(data)) {
				return true;
			}
		}
		return false;
	}

	private void updateProfile(String id) {
		// TODO Auto-generated method stub
		setProfile(id);
		
		System.out.println("Sucessfully update profile");
		input.nextLine();
	}
	
	private void setProfile(String id) {
		String username = "";
	    String password = "";
	    String name = "";
	    String email = "";
	    String address = "";
	    String gender = "";
		input.nextLine();
	    do {
			try {
				System.out.print("Input Username [5-15 Characters]: ");
				username = input.nextLine();
				if(checkDuplicateUsername(username)){
					System.out.println("Username already exits!!");
				}
			}catch (Exception e) {
				input.next();
			}
		} while(username.length() < 5 || username.length() > 15 || checkDuplicateUsername(username));
	    
	    do {
			try {
				System.out.print("Input Password [5-15 Characters]: ");
				password = input.nextLine();
			}catch (Exception e) {
				input.next();
			}
		} while(password.length() < 5 || password.length() > 15);
	    do {
			try {
				System.out.print("Input Name [5-15 Characters]: ");
				name = input.nextLine();
			}catch (Exception e) {
				input.next();
			}
		} while(name.length() < 5 || name.length() > 15);
	    
	    do {
			try {
				System.out.print("Input Email [must contain @]: ");
				email = input.nextLine();
			}catch (Exception e) {
				input.next();
			}
		} while(!email.matches("^(.+)@(.+)$"));
	    
	    do {
			try {
				System.out.print("Input Address [5-20 Characters]: ");
				address = input.nextLine();
			}catch (Exception e) {
				input.next();
			}
		} while(address.length() < 5 || address.length() > 20);
	    
	    do {
			try {
				System.out.print("Input Gender [Male or Female] (case sensitive): ");
				gender = input.next();
				if(gender.equals("Male") || gender.equals("Female")) {
					break;
				}
			}catch (Exception e) {
				input.next();
			}
		} while(true);
	    
	    Chef cheftemp = listChef.get(getIndexByID(id));
	    cheftemp.setUsername(username);
		cheftemp.setPassword(password);
		cheftemp.setEmail(email);
		cheftemp.setGender(gender);
		cheftemp.setAddress(address);
		cheftemp.setName(name);
	}

}
