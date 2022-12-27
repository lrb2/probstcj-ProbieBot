/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package probiebot;

/**
 *
 * @author probs
 */
public class GasPrice {
    private String name, address;
    private double price;
    public GasPrice (String name, String address, double price) throws IllegalArgumentException{
        if(name!=null){
            this.name = name;
        }
        else{
            throw new IllegalArgumentException("Name cannot be null.");
        }
        if(address!=null){
            this.address=address;
        }
        else{
            throw new IllegalArgumentException("Address cannot be null.");
        }
        if(price != 0){
            this.price = price;
        }
        else{
            throw new IllegalArgumentException("Price cannot be 0.");
        }
    }
    public double getPrice(){
        return price;
    }
    public String getAddress(){
        return address;
    }
    public String getName(){
        return name;
    }
    @Override
    public String toString(){
        return name + ", " + address + ": " + price;
    }
}
