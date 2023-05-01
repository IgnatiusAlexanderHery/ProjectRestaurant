package Controller;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;

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
import Controller.PesananController;

public class OwnerController {
    private Scanner input;
    private Menu showMenu;
    private ArrayList<Customer> listCustomer;
    private ArrayList<Chef> listChef;
    private ArrayList<ItemMenu> listMenu;
    private ArrayList<Pesanan> listPesanan;
    private ItemMenuFactories menuFactory;
    private PesananController showPesanan;

    public OwnerController() {
        input = new Scanner(System. in);
        listCustomer = new ArrayList<>();
        listChef = new ArrayList<>();
        listMenu = new ArrayList<>();
        listPesanan = new ArrayList<>();
        showMenu = new Menu();

        listChef.add(
            new Chef("budi", "budi", "Budi andri chef", "Budi@gmail.com", "JL BUDI", "Male")
        );
        listCustomer.add(
            new Customer("budi", "budi", "Budi andri customer", "Budi@gmail.com", "JL BUDI", "Male")
        );

        listMenu.add(new MakananBarat("Burger", "Jerman", 20_000));
        listMenu.add(new MinumanBarat("Lasagna", "Italia", 25_000));
        listMenu.add(new MakananBarat("Steak", "Meksiko", 23_000));
        listMenu.add(new MinumanLokal("Es Doger", "Jakarta", 10_000));
        listMenu.add(new MakananLokal("Nasi Goreng Jakarta", "Jakarta", 25_000));
        listMenu.add(new MakananBarat("spicy tuna sandwich", "Amerika", 22_000));
        listMenu.add(new MinumanBarat("Kopi Bali", "Bali", 5_000));
        listMenu.add(new MakananLokal("Nasi Jinggo", "Bali", 20_000));
        listMenu.add(new MakananLokal("Nasi Be Guling", "Bali", 35_000));
        listMenu.add(new MakananLokal("Nasi Padang", "Padang", 23_000));
        listMenu.add(new MinumanLokal("Jus Strawberry", "Bali", 10_000));
        listMenu.add(new MinumanBarat("Air Mineral", "Indonesia", 5_000));
    }

    // GET LIST DATA
    public ArrayList<Customer> getListCustomer() {
        return listCustomer;
    }
    public ArrayList<Chef> getListChef() {
        return listChef;
    }
    public ArrayList<ItemMenu> getListMenu() {
        return listMenu;
    }
    public ArrayList<Pesanan> getListPesanan() {
        return listPesanan;
    }

    // CRUD Menu
    public void createMenu(int index) {
        String nama,
        origin;
        int harga;
        int typeWilayah;
        int typeMenu = 0;
        String Menutype = "";
        if (index == -1) {

            System.out.println("Add Menu");
            while (true) {
                showMenu.choseWilayah();
                typeWilayah = input.nextInt();
                input.nextLine();
                if (typeWilayah == 1 || typeWilayah == 2) {
                    break;
                }
            }
            while (true) {
                showMenu.chooseTypeMenu();
                typeMenu = input.nextInt();
                input.nextLine();
                if (typeMenu == 1 || typeMenu == 2) {
                    if (typeMenu == 1) 
                        Menutype = "Makanan";
                    else 
                        Menutype = "Minuman";
                    break;
                }
            }
        } else {
            if (listMenu.get(index).getClass().getSimpleName().equals("MakananBarat")) {
                Menutype = "Makanan";
                typeWilayah = 2;
            } else if (listMenu.get(index).getClass().getSimpleName().equals("MinumanBarat")) {
                Menutype = "Minuman";
                typeWilayah = 2;
            } else if (listMenu.get(index).getClass().getSimpleName().equals("MakananLokal")) {
                Menutype = "Makanan";
                typeWilayah = 1;
            } else if (listMenu.get(index).getClass().getSimpleName().equals("MinumanLokal")) {
                Menutype = "Minuman";
                typeWilayah = 1;
            } else {
                showMenu.Error();
                return;
            }

        }
        do {
            System.out.println("Masukan nama " + Menutype + " : ");
            nama = input.nextLine();
        } while (nama.length() < 1 && nama.length() > 20);
        do {
            System.out.println("Masukan harga " + nama + ": ");
            harga = input.nextInt();
            input.nextLine();
        } while (harga < 1_000 && harga > 1_000_000);
        do {
            System.out.println("Masukan wilayah " + Menutype + ": ");
            origin = input.nextLine();
        } while (origin.length() < 1);

        if (index == -1) {
            if (typeMenu == 1) {
                menuFactory = new LokalFactory();
                if (typeMenu == 1) {
                    listMenu.add(menuFactory.makeMakanan(nama, origin, harga));
                } else {
                    listMenu.add(menuFactory.makeMinuman(nama, origin, harga));
                }
            } else if (typeMenu == 2) {
                menuFactory = new BaratFactory();
                if (typeMenu == 1) {
                    listMenu.add(menuFactory.makeMakanan(nama, origin, harga));
                } else {
                    listMenu.add(menuFactory.makeMinuman(nama, origin, harga));
                }
            }
        } else {
            listMenu.get(index).setNama(nama);
            listMenu.get(index).setOrigin(origin);
            listMenu.get(index).setHarga(harga);
        }
    }

    // CRUD Chef CRUD Customer CRUD Pesanan Start Program (Call this)
    public void runProgram() {
        int option = 0;
        do {
            try {
                System.out.println();
                showMenu.MenuOwner();
                System.out.println("Choice >> ");
                option = input.nextInt();
                if (option == 1) {
                    crdChef();
                } else if (option == 2) {
                    crudMenu();
                } else if (option == 3) {
                    printAllPesanan();
				}else if (option == 4) {
                    System.out.println();
                    return;
                } else {
                    showMenu.Error();
                }
            } catch (Exception e) {
                showMenu.Error();
                input.nextLine();
            }
        } while (option != 4);
        System.out.println();
    }

    private void printAllPesanan() {
		for (Pesanan pesanan : listPesanan) {
			pesanan.printDetail();
			if(pesanan.getisServe()){
				System.out.format("| %-40s   %-8s |\n","Served","");
			}else{
				System.out.format("| %-40s   %-8s |\n","Not Served","");
			}
			System.out.println("=======================================================");
		}
	}

	private void crdChef() {
        System.out.println("");
        int option = 0;
        do {
            try {
                System.out.println();
                showMenu.MenuCrdChef();
                System.out.println("Choice >> ");
                option = input.nextInt();
                if (option == 1) {
                    createChef();
                } else if (option == 2) {
                    deleteChef();
                } else if (option == 3) {
                    viewChef();
                } else if (option == 4) {
                    System.out.println();
                    return;
                } else {
                    showMenu.Error();
                }
            } catch (Exception e) {
                showMenu.Error();
                input.nextLine();
            }
        } while (option != 4);
        System.out.println();
    }

    private void viewChef() {
        if (listChef.isEmpty()) {
            System.out.println("There is no Chef Available, Please Hire first!");
			showMenu.printContinue();
        } else {
			System.out.println("=========================================================");
			System.out.format("| %-6s | %-20s | %-10s | %-7s |\n", "Chef Id", "Chef Name", "Has Served", "Salary"); 
			System.out.println("=========================================================");
            for (Chef chef : listChef) {
                chef.chefDetail();
            }
			System.out.println("=========================================================");
        }
    }

    private void deleteChef() {
        viewChef();

        String deleteId;
        while (true) {
            System.out.println("input id to delete(input 0 to cencel): ");
            deleteId = input.nextLine();
			if(deleteId.equalsIgnoreCase("0"))return;
            for (Chef chef : listChef) {
                if (chef.getId().equalsIgnoreCase(deleteId)) {
                    System.out.println("Chef " + chef.getName() + " has Fired");
                    listChef.remove(chef);
					showMenu.printContinue();
                    break;
                }
            }
			
        }
		
    }

    private int getIndexByID(String id) {
        for (int i = 0; i < listChef.size(); i++) {
            if (listChef.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkDuplicateUsername(String data) {
        for (int i = 0; i < listChef.size(); i++) {
            if (listChef.get(i).getUsername().equals(data)) {
                return true;
            }
        }
        return false;
    }

    private void createChef() {
        String username = "";
        String password = "";
        String name = "";
        String email = "";
        String address = "";
        String gender = "";
        do {
            try {
                System.out.print("Input Username [5-15 Characters]: ");
                username = input.next();
                if (checkDuplicateUsername(username)) {
                    System.out.println("Username already exits!!");
                }
            } catch (Exception e) {
                input.next();
            }
        } while (
            username.length() < 5 || username.length() > 15 || checkDuplicateUsername(username)
        );

        do {
            try {
                System.out.print("Input Password [5-15 Characters]: ");
                password = input.next();
            } catch (Exception e) {
                input.next();
            }
        } while (password.length() < 5 || password.length() > 15);
        input.nextLine();
        do {
            try {
                System.out.print("Input Name [5-15 Characters]: ");
                name = input.nextLine();
            } catch (Exception e) {
                input.next();
            }
        } while (name.length() < 5 || name.length() > 15);

        do {
            try {
                System.out.print("Input Email [must contain @]: ");
                email = input.nextLine();
            } catch (Exception e) {
                input.next();
            }
        } while (!email.matches("^(.+)@(.+)$"));

        do {
            try {
                System.out.print("Input Address [5-20 Characters]: ");
                address = input.nextLine();
            } catch (Exception e) {
                input.next();
            }
        } while (address.length() < 5 || address.length() > 20);

        do {
            try {
                System.out.print("Input Gender [Male or Female] (case sensitive): ");
                gender = input.next();
                if (gender.equals("Male") || gender.equals("Female")) {
                    break;
                }
            } catch (Exception e) {
                input.next();
            }
        } while (true);

        Chef cheftemp = new Chef(username, password, name, email, address, gender);
        listChef.add(cheftemp);
    }

    private void crudMenu() {
        int option = 0;
        do {
            try {
                System.out.println();
                showMenu.crudMenu();
                System.out.println("Choice >> ");
                option = input.nextInt();
                input.nextLine();
                if (option == 1) {
                    createMenu(-1);
                } else if (option == 2) {
                    listMenu("delete");
                } else if (option == 3) {
                    listMenu("update");
                } else if (option == 4) {
                    listMenu("view");
                } else if (option == 5) {
                    System.out.println();
                    return;
                } else {
                    showMenu.Error();
                }
            } catch (Exception e) {
                showMenu.Error();
                input.nextLine();
            }
        } while (option != 4);

    }

    private void listMenu(String type) {
        Boolean flag = false;
        if (listMenu.isEmpty()) {
            System.out.println("Menu is not Available");
        } else {
            int no = 1;
            for (ItemMenu menu : listMenu) {
                if (menu instanceof MinumanLokal) {
                    if (type.equals("delete") || type.equals("update")) 
                        System.out.print(menu.getId() + " ");
                    print(no, menu.getNama(), menu.getOrigin(), menu.getHarga());
                    no++;
                }

            }
            for (ItemMenu menu : listMenu) {
                if (menu instanceof MinumanBarat) {
                    if (type.equals("delete") || type.equals("update")) 
                        System.out.print(menu.getId() + " ");
                    print(no, menu.getNama(), menu.getOrigin(), menu.getHarga());
                    no++;
                }
            }
            for (ItemMenu menu : listMenu) {
                if (menu instanceof MakananLokal) {
                    if (type.equals("delete") || type.equals("update")) 
                        System.out.print(menu.getId() + " ");
                    print(no, menu.getNama(), menu.getOrigin(), menu.getHarga());
                    no++;
                }
            }
            for (ItemMenu menu : listMenu) {
                if (menu instanceof MakananBarat) {
                    if (type.equals("delete") || type.equals("update")) 
                        System.out.print(menu.getId() + " ");
                    print(no, menu.getNama(), menu.getOrigin(), menu.getHarga());
                    no++;
                }
            }
            if (type.equals("delete")) {
                String deleteId;
                while (true) {
                    System.out.println("input id to delete(input 0 to cencel): ");
                    deleteId = input.nextLine();
                    for (ItemMenu menu : listMenu) {
                        if (menu.getId().equalsIgnoreCase(deleteId)) {
                            listMenu.remove(menu);
                            flag = true;
                            break;
                        }
                    }
                    if (flag == true || deleteId.equals("0")) {
                        break;
                    }
                }
            }
            if (type.equals("update")) {
                String updateId;
                while (true) {
                    int index = 0;

                    System.out.println("input id to update(input 0 to cancel): ");
                    updateId = input.nextLine();
                    for (ItemMenu menu : listMenu) {
                        if (menu.getId().equalsIgnoreCase(updateId)) {
                            createMenu(index);
                        }
                        flag = true;
                        index++;
                    }
                    if (flag == true || updateId.equals("0")) {
                        break;
                    }
                }
            }
        }
    }

    private void print(int no, String nama, String origin, int harga) {
        System.out.println(no + " " + nama + " " + origin + " Rp." + harga);
    }
}