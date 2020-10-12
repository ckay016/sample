import java.util.HashMap;
import java.util.Map;

public class Warehouse 
{
	int warehouseID;
	public int getWarehouseID() {
		return warehouseID;
	}

	public void setWarehouseID(int warehouseID) {
		this.warehouseID = warehouseID;
	}
	static int num=0;
	String warehouseAddress;
	Map<Product,Integer> stock;
	
	public Warehouse(String address)
	{
		num+=1;
		this.warehouseID=num;
		this.warehouseAddress=address;
		this.stock = new HashMap<Product,Integer>();
	}
	
	public void addStock(Product p,Integer n)
	{
		if(this.stock.containsKey(p))
		{
			this.stock.replace(p,this.stock.get(p)+n);
			System.out.println("Product exists already so stock has been updated!");
		}
		else
		{
			this.stock.put(p,n);
			System.out.println("New Product added!");
		}
	}
	
	public void removeStock(Product p,Integer n)
	{
		if(this.stock.containsKey(p))
		{
			if(this.stock.get(p)!=0)
			{
				if(this.stock.get(p)-n>=0)	
				{
					this.stock.replace(p,this.stock.get(p)-n);
					System.out.println("Product stock has been reduced!");
				}
				else
				{
					System.out.println("Cannout remove"+n+" stock as it will cause underflow!");
				}
			}
			else
				System.out.println("Product quantity is already zero!");
		}
		else
		{
			System.out.println("This product is not present in the warehouse!");
		}
	}
	
	public void viewStock()
	{
		try
		{
			System.out.println("\nWarehouse "+this.warehouseID+" Products are:");
			if(!this.stock.isEmpty())
			{
				for(Product p:this.stock.keySet())
				{
					System.out.println("Product ID:"+p.getProductID()+ "\nProduct Quantity:"+this.stock.get(p));
				}
			}
			else
			{
				System.out.println("Empty!");
			}
		}
		catch(Exception e)
		{
			System.out.println("Warehouse is empty!");
		}
	}
	public static void main(String[] args)
	{
	

	}

}
