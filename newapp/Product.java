import java.util.Scanner;

public class Product 
{
	String productID;
	String productName;
	String productBrand;
	String productCategory;
	private float productPrice;
	int productNumRating;
	float productRatingSum;
	private float productAvgRating;
	
	
	public Product(String productID, String productName, String productBrand, String productCategory,float productPrice) 
	{
		this.productID = productID;
		this.productName = productName;
		this.productBrand = productBrand;
		this.productCategory = productCategory;
		this.productPrice = productPrice;
		this.productNumRating=0;
		this.productRatingSum=0;
		this.productAvgRating=0;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public float getProductPrice()
	{
		return productPrice;
	}
	
	public void setProductPrice(float price)
	{
		this.productPrice = price;
	}
	
	public void modifyPrice(float price)
	{
		this.productPrice = price;
	}
	
	public void applyDiscount(float disc)
	{
		this.productPrice*=(1-(disc*0.01));
	}
	
	public void updateRating(float rating)
	{
		this.productNumRating+=1;
		this.productRatingSum+=rating;
		this.productAvgRating = (this.productRatingSum)/this.productNumRating;
		System.out.println(this.productAvgRating);
	}
	
	public void displayProductDetails()
	{
		System.out.println("____________________________");
		System.out.println("\nProduct ID:"+this.productID);
		System.out.println("Product Name:"+this.productName);
		System.out.println("Product Brand:"+this.productBrand);
		System.out.println("Product Category:"+this.productCategory);
		System.out.println("Product Price:"+this.productPrice);
		System.out.println("Product Rating:"+this.productAvgRating);
		System.out.println("____________________________");
	}
	


	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter ID,Name,Brand,Category,Price");
		String id = sc.nextLine();
		String name = sc.nextLine();
		String brand = sc.nextLine();
		String cat = sc.nextLine();
		float price = sc.nextFloat();
		
		Product p1 = new Product(id,name,brand,cat,price);
		p1.updateRating(5);
		p1.updateRating(4);
		p1.updateRating(3);
		p1.modifyPrice((float) 100.0);
		p1.displayProductDetails();
		p1.applyDiscount(20);
		p1.displayProductDetails();
	}

}
