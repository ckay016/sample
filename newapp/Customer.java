import java.util.ArrayList;
import java.util.Scanner;

public class Customer 
{
	String customerName;
	int customerID;
	String customerPhone;
	String customerAddress;
	Cart cart;
	static int numCustomers=0;
	ArrayList<Order> orders;
	Scanner sc = new Scanner(System.in);
	public Customer(String name,String phone,String address,Cart cart)
	{
		numCustomers+=1;
		this.customerID = numCustomers;
		this.customerName = name;
		this.customerPhone = phone;
		this.customerAddress = address;
		this.cart = cart;
		this.cart.shippingAddress=address;
		this.orders = new ArrayList<Order>();
	}
	
	public void addToCart(Product p)
	{
		this.cart.addProduct(p);
	}
	
	public void removeFromCart(Product p)
	{
		this.cart.removeProduct(p);
	}
	
	public void rateProduct(Product p)
	{
		int flag=0;
		for(Order o:this.orders)
		{
			if(o.Products.contains(p))
			{
				System.out.println("Enter rating for the product:");
				float rating = sc.nextFloat();
				p.updateRating(rating);
				System.out.println("Ratings updated successfully!");
				flag=1;
			}
		}
		if(flag==0)
		{
			System.out.println("Sorry you cannot rate this product as you haven't purchased it!");
		}
		
	}
	
	public void displayCart()
	{
		this.cart.displayCart();
	}
	
	public void placeOrder()
	{
		if(!this.cart.Products.isEmpty())
		{
			System.out.println("here");
			Order order = this.cart.placeOrder();
			order.displayOrder();
			this.orders.add(order);
			this.cart.setTotalcost(0);
			System.out.println("Order placed succesfully!");
		}
		else
			System.out.println("Cart is empty! Cannot place order!");

	}
	public void displayOrders()
	{
		if(!this.orders.isEmpty())
		{
			for(Order o:this.orders)
			{
				o.displayOrder();
			}
		}
		else
			System.out.println("No orders to display!");
		
	}
}
