package model;

import singleton.IdGenerator;

public class Chef extends User {
	
	private String id;
    private String name;
    private String email;
    private String address;
    private String gender;
	private IdGenerator genId;
	private int serve;
	
	public Chef(String username, String password,  String name, String email, String address,
			String gender) {
		super(username, password);
		
		this.name = name;
		this.email = email;
		this.address = address;
		this.gender = gender;
		if(this.id==null){
			genId = IdGenerator.getInstance();
			this.setId("CH-"+ genId.generateNum());
		}
		this.serve=0;
	}
	public int getServe() {
		return this.serve;
	}
	public void setServe() {
		this.serve += 1 ;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
    
	public void chefDetail(){
		System.out.format("| %-6s | %-20s | %-10s | %-7s |\n", this.getId(), this.getName(), this.getServe(), this.getServe()*500_000);  
		
	}
    
}
