package toy.dto;

public class Toy {
	private int id;
	private String tName;
	private String color;
	private double price;
	private int quantity;
	
	public Toy(String tName, String color, double price, int quantity) {
		super();
		this.tName = tName;
		this.color = color;
		this.price = price;
		this.quantity = quantity;
	}
	
	
	public Toy(int id, String tName, String color, double price, int quantity) {
		super();
		this.id = id;
		this.tName = tName;
		this.color = color;
		this.price = price;
		this.quantity = quantity;
	}

	
	public String toString() {
		return "Toy [id=" + id + ", tName=" + tName + ", color=" + color + ", price=" + price + ", quantity=" + quantity
				+ "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String gettName() {
		return tName;
	}


	public void settName(String tName) {
		this.tName = tName;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
