package toy.dto;

public class Admin {
	
	private String id;
	private String name;
	private String shopName;
	private String email;
	private long phone;
	private String address;
	private String password;
	
	public Admin(String id, String name, String shopName, String email, long phone, String address, String password) {
		super();
		this.id = id;
		this.name = name;
		this.shopName = shopName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.password = password;
	}

	
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", shopName=" + shopName + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", password=" + password + "]";
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


	public String getShopName() {
		return shopName;
	}


	public void setShopName(String shopName) {
		this.shopName = shopName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public long getPhone() {
		return phone;
	}


	public void setPhone(long phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
}
