import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

public class Cart
{
	static int count=0;
	ArrayList<Product> Products;
	String shippingAddress;
	private float totalcost;
	
	public float getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(float totalcost) {
		this.totalcost = totalcost;
	}

	public Cart()
	{
		this.Products = new ArrayList<Product>();
	}
	
	public void addAddress(String address)
	{
		this.shippingAddress = address;
	}
	
	public void addProduct(Product p)
	{
		if(!this.Products.contains(p))
		{
			this.Products.add(p);
			this.totalcost+=p.getProductPrice();
			System.out.println("Product added to cart!");
		}
		else
			System.out.println("This item is already in your cart!");
	}
	
	public void removeProduct(Product p)
	{
		int flag=0;
		if(!this.Products.isEmpty())
		{	
		
			if(this.Products.contains(p))
		{
			this.Products.remove(p);
			flag=1;
			System.out.println("Successfully removed from cart!");
		}
		if(flag==0)
		{
			System.out.println("Product does not exist in cart!");
		}
		
		}
		else
			System.out.println("Cart is empty!");
		
	}
	
	public void displayCart()
	{
		//System.out.println("Order ID:"+this.orderID);
		try
		{
			if(!Products.isEmpty())
		
		{
			System.out.println("Products:\n");
			for(Product p:this.Products)
			{
				p.displayProductDetails();
			}
			System.out.println("Total Cost:"+this.totalcost);
		}
		else
			System.out.println("Cart is empty!");
		}
		catch(NullPointerException e)
		{
			System.out.println("Cart is empty!");
		}
		
	}
	
	public Order placeOrder()
	{
		Order order = new Order();
		order.setTotalcost(this.totalcost);
		order.shippingAddress = this.shippingAddress;
		order.Products = this.Products;
		this.Products= new ArrayList<Product>();
		//order.displayOrder();
		return order;

		
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		Cart c1 = new Cart();
		System.out.println("Enter Address:");
		String address = sc.nextLine();
		c1.addAddress(address);
		
	}

}
