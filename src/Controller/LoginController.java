package Controller;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Scanner;

import main.Menu;
import model.Chef;
import model.Customer;
import model.Owner;

public class LoginController {
	private Scanner input;
	private Owner ownerAuth;
	private OwnerController ownerHandler;
	private ChefController chefHandler;
	private CustomerController customerHanler;
	private ArrayList<Customer> customerData;
	private ArrayList<Chef> chefData;
	private Menu showMenu;
	
	public LoginController() {
		ownerAuth  = new Owner();
		ownerHandler = new OwnerController();
		chefData = ownerHandler.getListChef();
		customerData = ownerHandler.getListCustomer();
		customerHanler = new CustomerController(ownerHandler);
		chefHandler = new ChefController(ownerHandler);
		input = new Scanner(System.in);
		showMenu = new Menu();
	}
	
	//Validasi validasi login

	public void ownerLogin() {
		String username = "";
		String password = "";
		
		System.out.println("Username : ");
		username = input.next();
		System.out.println("Password : ");
		password = input.next();
		
		if(ownerAuth.getUsername().toLowerCase().equals(username.toLowerCase()) && ownerAuth.getPassword().equals(password)) {
			System.out.println();
			ownerHandler.runProgram();
		}
		else {
			showMenu.WrongUserAndPass();
		}
	}
	
	public void customerLogin() {
		customerData = ownerHandler.getListCustomer();
		String username = "";
		String password = "";
		boolean valid = false;
		
		if(customerData.isEmpty()) {
			System.out.println("\nNo customer availabe\n");
		}else {
			System.out.println("\nUsername : ");
		username = input.nextLine();
		System.out.println("\nPassword : ");
		password = input.nextLine();
		for (Customer i : customerData) {
			if(username.toLowerCase().equals(i.getUsername().toLowerCase()) && password.equals(i.getPassword())) {
				customerHanler.runProgram(i);
				valid = true;
				break;
			}
		}
		if(!valid) {
			showMenu.WrongUserAndPass();
		}
		}
	}
	
	public void chefLogin() {
		chefData = ownerHandler.getListChef();
		String username = "";
		String password = "";
		boolean valid = false;
		
		if(chefData.isEmpty()) {
			System.out.println("\nNo chef availabe\n");
		}else {
			System.out.println("\nUsername : ");
			username = input.nextLine();
			System.out.println("\nPassword : ");
			password = input.nextLine();
			for (Chef i : chefData) {
				if(username.toLowerCase().equals(i.getUsername().toLowerCase()) && password.equals(i.getPassword())) {
					chefHandler.runProgram(i);
					valid = true;
					break;
				}
			}
			if(!valid) {
				showMenu.WrongUserAndPass();
			}
		}
	}

	public void customerRegister() {
		String username = "";
	    String password = "";
	    String name = "";
	    String email = "";
	    String address = "";
	    String gender = "";
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
				gender = input.nextLine();
				if(gender.equals("Male") || gender.equals("Female")) {
					break;
				}
			}catch (Exception e) {
				input.next();
			}
		} while(true);
	    
	    Customer cusrtomertemp = new Customer(username, password, name, email, address, gender);
	    customerData.add(cusrtomertemp);
	}
	
	private boolean checkDuplicateUsername(String data) {
		for(int i = 0; i < customerData.size(); i++) {
			if(customerData.get(i).getUsername().equals(data)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
	
}
