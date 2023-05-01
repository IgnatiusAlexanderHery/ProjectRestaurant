package main;

import java.util.Scanner;

import Controller.LoginController;
import factories.BaratFactory;
import factories.ItemMenuFactories;
import factories.LokalFactory;
import itemMenu.MakananBarat;
import itemMenu.MakananLokal;
import itemMenu.MinumanBarat;
import itemMenu.MinumanLokal;

public class Main {
	private Scanner input;
	private Menu showMenu;
	private LoginController loginAs;
	
	public Main() {
		input = new Scanner(System.in);
		loginAs = new LoginController();
		showMenu = new Menu();
		runProgram();
	}
	
	private void runProgram() {
		// TODO Auto-generated method stub
		int option = 0;
		do {
			try{
				showMenu.menuMain();
				System.out.print("Choice >> ");
				option = input.nextInt();
				input.nextLine();
				
				if(option == 1) {
					loginAs.ownerLogin();
				}else if(option == 2){
					loginAs.chefLogin();
				}else if(option == 3) {
					loginAs.customerLogin();
				}else if(option == 4){
					loginAs.customerRegister();
				}else if(option == 5){
					System.exit(0);
				}
				
				else{
					showMenu.Error();
				}
			}catch(Exception e){
				showMenu.Error();
				input.nextLine();
			}
		} while(option != 5);
	}

	public static void main(String[] args) {
		new Main();

	}

}
