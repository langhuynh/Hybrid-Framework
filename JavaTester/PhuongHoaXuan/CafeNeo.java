package PhuongHoaXuan;

import java.util.PrimitiveIterator;

public class CafeNeo {
    public String Espresso = "Cafe Espresso";
    protected String OrangeJuice = "OrangeJuice";
    String LemonJuice = "LemonJuice";
    private String homemadeCoffee = "Homemade";
    public void ShipEspresso(){
        System.out.println("Ship coffee:" + Espresso);

    }
    protected void ShipOrangeJuice(){
        System.out.println("Ship orange: "+OrangeJuice);
    }
    void ShipLemon(){
        System.out.println(" Ship lemon:"+ LemonJuice);
    }
    private void ShipHandmade(){
        System.out.println("ship cafehomemade:" +homemadeCoffee);
    }
    public static void main(String[] args){
        CafeNeo neo = new CafeNeo();
        neo.ShipEspresso();
    }
}