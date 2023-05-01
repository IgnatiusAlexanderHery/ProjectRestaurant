package main;

import java.util.Scanner;

import itemMenu.ItemMenu;

public class Menu {
	private Scanner input = new Scanner(System.in);

	public void WrongUserAndPass() {
		// TODO Auto-generated method stub
		System.out.println("\nEither Username or Password is wrong\n");
	}

	public void Error() {
		// TODO Auto-generated method stub
		System.out.println("\n==========================");
		System.out.println("WRONG INPUT");
		System.out.println("==========================\n");
	}

	public void MenuOwner() {
		// TODO Auto-generated method stub
		System.out.println("Welcome Owner");
		System.out.println("1. Chef");
		System.out.println("2. Menu");
		System.out.println("3. View Pesanan");
		System.out.println("4. Logout");
	}

	public void MenuCustomer() {
		// TODO Auto-generated method stub
		System.out.println("1. Update Profile");
		System.out.println("2. Create Pesanan");
		System.out.println("3. Logout");
	}

	public void MenuChef() {
		// TODO Auto-generated method stub
		System.out.println("1. Update Profile");
		System.out.println("2. View Pesanan");
		System.out.println("3. Logout");
	}

	public void menuMain() {
		// TODO Auto-generated method stub
		System.out.println("Welcome To Restauran");
		System.out.println("Select your role : ");
		System.out.println("1. Owner");
		System.out.println("2. Chef");
		System.out.println("3. Customer");
		System.out.println("4. Register Customer");
		System.out.println("5. Exit");
	}

	public void crudMenu() {
		System.out.println("CRUD Menu");
		System.out.println("1. Create");
		System.out.println("2. Delete");
		System.out.println("3. Update");
		System.out.println("4. View");
		System.out.println("5. Exit");
		
		
	}
	
	
	public void Pesanan() {
		System.out.println("Pesanan");
		System.out.println("1. Add Pesanan");
		System.out.println("2. History Pesanan");
		System.out.println("3. Exit");
	}
	
	
	public void printContinue(){
		System.out.println("press Enter to continue...");
		input.nextLine();
	}

	public void MenuCrdChef() {
		System.out.println("Chef");
		System.out.println("1. Add Chef");
		System.out.println("2. Delete Chef");
		System.out.println("3. View Chef");
		System.out.println("4. Exit");
	}

	public void choseWilayah() {
		System.out.println("Choose Type Wilayah");
        System.out.println("1. Lokal");
        System.out.println("2. Barat");
	}

	public void chooseTypeMenu() {
		System.out.println("Choose Type Menu");
        System.out.println("1. Makanan");
        System.out.println("2. Minuman");
	}
	
	
	

	// Buat function function untuk print mentu, pindahin semua di sini jadi 1 class khusus print
}
