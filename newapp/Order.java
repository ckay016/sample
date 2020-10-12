import java.util.ArrayList;

public class Order extends Cart
{
	static int ordernum=0;
	int orderID;
	
	public Order() 
	{
		super();
		ordernum+=1;
		this.orderID=ordernum;
		
	}
	
	public ArrayList<Product> getProducts()
	{
		return this.Products;
	}
	public void displayOrder()
	{
		try
		{
			System.out.println("_____________________________");
			System.out.println("Order ID:"+this.orderID);
			System.out.println("Products:");
			for(Product p:this.Products)
			{
				p.displayProductDetails();
			}
			System.out.println("Total Cost:"+this.getTotalcost());
			System.out.println("Shipping Address:"+this.shippingAddress);
		}
		catch(Exception e)
		{
			System.out.println("Could not display this order. Sorry!");
		}
	}
	public static void main(String[] args) 
	{
		

	}

}
