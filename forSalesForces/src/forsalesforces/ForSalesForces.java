package forsalesforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class ForSalesForces {
    
    static ArrayList<Product> products;
    static ArrayList<Order> orders;
    static ArrayList<OrderItem> orderItems;
    static User mainUser;
    static public int lastUsedOrder = -1;
    
    public static void main(String[] args) {
        loadProductsFromFile();
        startForm sForm = new startForm();
        sForm.setVisible(true);
    }
    
    public static void loadProductsFromFile() 
    {
        BufferedReader br = null;
        try{
            String filePath = "src\\forsalesforces\\products.txt";
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        }
        catch(Exception ex)
        {
            try {
                String filePath = "products.txt";
                br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            }
            catch(Exception ex1)
            {
                
            }
        }
        try 
        {
            String count = br.readLine();
            if (count != null)
            {
                for (int i = 0; i < Integer.valueOf(count); i++)
                {
                    String name = br.readLine();
                    String desc = br.readLine();
                    String type = br.readLine();
                    String family = br.readLine();
                    String price = br.readLine();
                    String imgPath = br.readLine();
                    if (imgPath == null)
                        imgPath = "";
                    if (products == null)
                    {
                        products = new ArrayList<>();
                    }
                    products.add(new Product(name, desc, type, family, imgPath, Integer.valueOf(price)));
                }
            }
        }
        catch(Exception ex2){}
    }
    static class User
    {
        boolean isManager;

        User()
        {
            isManager = false;
        }

        public void changeStat()
        {
            isManager = !isManager;
        }
        

    }

    static class Product
    {
        String name, desc, type, fam, imgPath;
        int price;

        Product(String newName, String newDesc, String newType, String newFam, String imgPath, int newPrice)
        {
            name = newName;
            price = newPrice;
            desc = newDesc;
            type = newType;
            fam = newFam;
            this.imgPath = imgPath;
        }

    }

    static class OrderItem
    {
        int orderId, productId, quantity, price;

        OrderItem(int newOrderId, int newProdId, int newQuantity, int newPrice)
        {
            orderId = newOrderId;
            productId = newProdId;
            quantity = newQuantity;
            price = newPrice;
        }
    }

     static class Order
    {
        String newName;
        int accId, totalProductCount, totalPrice;

        

        Order(String newName, int newAccId)
        {
            this.newName = newName;
            accId = newAccId;
            totalProductCount = 0;
            totalPrice = 0;
        }

        public void increaseTotalProdCount(int inc)
        {
            totalProductCount += inc;
        }

        public void increaseTotalPrice(int price)
        {
            totalPrice += price;
        }

    }
    
    
}
