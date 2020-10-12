import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.*;
public class Application
{	
	static ArrayList<Product> allProducts = new ArrayList<Product>();
	static ArrayList<Warehouse> allWarehouse = new ArrayList<Warehouse>();
	//allProducts.add(new Product("1","LG 8kg Washing Machine","LG","Washing Machine",45000));
	//Added a comment line
	// new pull request
	static Scanner sc = new Scanner(System.in);
	static Iterator<Product> itr = allProducts.iterator();
	public static void addProduct()
	{
		
		System.out.println("\nEnter ID,Name,Brand,Category,Price");
		sc.nextLine();
		String id = sc.nextLine();
		for(Product p:allProducts)
		{
			if(p.productID.equals(id))
			{
				System.out.println("Product ID already exists!");
				return;
			}
		}
		String name = sc.nextLine();
		String brand = sc.nextLine();
		String cat = sc.nextLine();
		float price = sc.nextFloat();
		Product p = new Product(id,name,brand,cat,price);
		allProducts.add(p);
		System.out.println("Product added successfully!");

	}
	
	public static void removeProduct()
	{
		System.out.println("Enter Product ID");
		sc.nextLine();
		String pid = sc.nextLine();
		int flag=0;
		itr = allProducts.iterator();
		while(itr.hasNext())
		{
			Product rem = itr.next();
			System.out.println(rem.productID);
			if(rem.productID.equals(pid))
			{
				itr.remove();
				flag=1;
				System.out.println("Product has been removed!");
			}
		}
		if(flag==0)
		{
			System.out.println("Product does not exist!");
		}
	}
	
	public static void displayAllProducts()
	{
		if(!allProducts.isEmpty())
		{
			for(Product p :allProducts)
			{
				p.displayProductDetails();
			}
		}
		else
		{
			System.out.println("No products to display!");
		}
	}
	
	public static void updatePrice()
	{
		System.out.println("Enter product ID");
		sc.nextLine();
		String id = sc.nextLine();
		itr = allProducts.iterator();
		while(itr.hasNext())
		{
			Product p = itr.next();
			if(p.productID.equals(id))
			{
				System.out.println("Enter new price:");
				float price = sc.nextFloat();
				p.setProductPrice(price);
				System.out.println("Product price updated successfully!");
			}
		}
	}
	
	public static void applyDiscount()
	{
		System.out.println("Enter product ID");
		sc.nextLine();
		String id = sc.nextLine();
		itr = allProducts.iterator();
		while(itr.hasNext())
		{
			Product p = itr.next();
			if(p.productID.equals(id))
			{
				System.out.println("Enter discount percentage %:");
				float price = sc.nextFloat();
				p.applyDiscount(price);
				System.out.println("Discount has been applied successfully!");
			}
		}
	}
	
	public static void addToCart(Customer c)
	{
		System.out.println("Enter product ID to add it to cart:");
		sc.nextLine();
		String pid = sc.nextLine();
		//int flag=0;
		if(allProducts.stream().filter(o -> o.getProductID().equals(pid)).findFirst().isPresent())
		{
			for(Product p:allProducts)
			{
				if(p.productID.equals(pid))
				{
					c.addToCart(p);
				}
			} 
		}
		else
			System.out.println("Product with the given ID is not present!");
		
	}
	
	
	public static void removeFromCart(Customer c)
	{
		System.out.println("Enter product ID to remove it from cart:");
		sc.nextLine();
		String pid = sc.nextLine();
		//int flag=0;
		if(allProducts.stream().filter(o -> o.getProductID().equals(pid)).findFirst().isPresent())
		{
			for(Product p:allProducts)
			{
				if(p.productID.equals(pid))
				{
					c.removeFromCart(p);
				}
			} 
		}
		else
			System.out.println("Product with the given ID is not present!");
	
	}
	
	public static void placeOrder(Customer c)
	{
		int stockAvailable=0;
		int flag=0;
		for(Warehouse w:allWarehouse)
		{
			for(Product p:c.cart.Products)
			{
				try
				{
					if(!(w.stock.get(p)>0))
					{
						System.out.println("Product ID:"+p.getProductID()+ " is not in stock! Cannot place order, sorry!");
						flag=1;
					}
					else
					{
						c.placeOrder();
						w.stock.replace(p,w.stock.get(p)-1);
						return;
					}
				}
				catch(NullPointerException e)
				{
					System.out.println("Cannot place order as products are not in stock. Sorry!");
				}
				catch(Exception e)
				{
					System.out.println("Sorry an exception occurred!");
				}
			}
		}
		if(flag==0)
		{
			System.out.println("here1");
			
			c.placeOrder();
		}
	}
	
	public static void rateProduct(Customer c)
	{
		System.out.println("Enter ID of product you wish to rate:");
		sc.nextLine();
		String pid = sc.nextLine();
		int flag=0;
			for(Product p:allProducts)
			{
				if(p.productID.equals(pid))
				{
					c.rateProduct(p);
					flag=1;
				}
			}
		
		if(flag==0)
			System.out.println("This product does not exist!");
		
	}
	
	public static void displayWarehouse()
	{
		try
		{
			if(!allWarehouse.isEmpty())
			{
				for(Warehouse w:allWarehouse)
				{
					w.viewStock();
				}
			}
			else
				System.out.println("Empty warehouse!");
		}
		catch(NullPointerException e)
		{
			System.out.println("Empty warehouse!");
		}
	}
	
	public static void addToWarehouse()
	{
		System.out.println("Enter warehouse ID:");
		int wid = sc.nextInt();
		int flag=0;
		for(Warehouse w:allWarehouse)
		{
			if(w.warehouseID == wid)
			{
				flag=1;
				System.out.println("\nEnter ID of product to add");
				sc.nextLine();
				String id = sc.nextLine();
				int flag1=0;
				for(Product p:allProducts)
				{
					if(p.productID.equals(id))
					{
						System.out.println("Enter quantity");
						int n = sc.nextInt();
						w.addStock(p,n);
						flag1=1;
					}
				}
				if(flag1==0)
				{
					System.out.println("Product does not exist!");
				}
			}
		}
		if(flag==0)
		{
			System.out.println("Warehouse does not exist!");
		}
		
	}
	
	public static void removeFromWarehouse()
	{
		System.out.println("Enter warehouse ID:");
		int wid = sc.nextInt();
		int flag=0;
		for(Warehouse w:allWarehouse)
		{
			if(w.warehouseID == wid)
			{
				System.out.println("\nEnter ID of product to remove");
				sc.nextLine();
				String id = sc.nextLine();
				int flag1=0;
				for(Product p:allProducts)
				{
					if(p.productID.equals(id))
					{
						System.out.println("Enter quantity");
						int n = sc.nextInt();
						w.removeStock(p,n);
						flag1=1;
					}
				}
				flag=1;
				if(flag1==0)
				{
					System.out.println("Product does not exist!");
				}
			}
		}
		if(flag==0)
		{
			System.out.println("Warehouse does not exist!");
		}
	}
	
	public static void customerFunctions()
	{
		System.out.println("Welcome! Please enter your details:");
		System.out.println("\nEnter Name,Phone,Address");
		String name = sc.nextLine();
		String phone = sc.nextLine();
		String address = sc.nextLine();
		Cart cart = new Cart();
		Customer c = new Customer(name,phone,address,cart);
		
		while(true)
		{
			System.out.println("\n1.View Products 2. Add to Cart 3. Remove from Cart 4.Display Cart 5.Rate a Product 6.Place Order 7.View Orders 8.Change Role");
			int usrchoice = sc.nextInt();
			switch(usrchoice)
			{
			case 1: displayAllProducts();
					break;
			case 2: addToCart(c);
			        break;
			case 3: removeFromCart(c);
					break;
			case 4: c.cart.displayCart();
					break;//display
			case 5: rateProduct(c);
					break;
			case 6: placeOrder(c);
					break;
			case 7:c.displayOrders();
					break;
			case 8: return;
			default:System.out.println("Invalid choice!");
			}
		}
	}
	
	public static void managerFunctions()
	{
		System.out.println("Enter password:");
		sc.nextLine();
		String pwd = sc.nextLine();
		if(pwd.equals("hello123"))
		{
			while(true)
			{
				System.out.println("\n\n1.Add Product 2.Remove Product 3.View Products 4.Update Product Price 5.Apply Discount 6.Add to Warehouse 7.Remove from warehouse 8.View Warehouse Stock 9.Change Role");
				int choice = sc.nextInt();
				switch(choice)
				{
				case 1:addProduct();
						break;
				case 2: removeProduct();
						break;
				case 3:displayAllProducts();
						break;
				case 4: updatePrice();
						break;
				case 5: applyDiscount();
						break;
				case 6:addToWarehouse();
						break;
				case 7:removeFromWarehouse();
						break;
				case 8:displayWarehouse();
						break;
				case 9:return;
				default:System.out.println("Invalid choice!");
				}

			}

		}	
		else
			System.out.println("Wrong password! Try again");
	}
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		allProducts.add(new Product("1","LG 8kg Washing Machine","LG","Washing Machine",36000));
		allProducts.add(new Product("2","HP Laptop 16GB RAM","HP","Laptop",84000));
		allProducts.add(new Product("3","OnePlus 8 Pro","OnePlus","Mobiles",58000));
		allProducts.add(new Product("4","Bean Bag XL","Godrej","Furniture",6000));
		allProducts.add(new Product("5","Aquaguard Puretech","Aquaguard","Water Purifier",26000));
		allWarehouse.add(new Warehouse("HSR Layout"));
		allWarehouse.add(new Warehouse("Banashankari"));
		allWarehouse.add(new Warehouse("Peenya"));
		for(Warehouse w:allWarehouse)
		{
			for(Product p:allProducts)
			{
				w.addStock(p,10);
			}
		}
		
		while(true)
		{
			System.out.println("Choose your role\n1. Customer 2. Manager 3.Exit");
			int usr = sc.nextInt();
			switch(usr)
			{
			case 1: customerFunctions();
					break;
			case 2:managerFunctions();
					break;
			case 3:System.exit(0);
			default:System.out.println("Wrong choice!");
			}
			
				
		}
		
//		Cart c1 = new Cart();
//		System.out.println("Enter Address:");
//		sc.nextLine();
//		String address = sc.nextLine();
//		c1.addAddress(address);
//		c1.addProduct(p1);
//		c1.displayCart();
	}

}
