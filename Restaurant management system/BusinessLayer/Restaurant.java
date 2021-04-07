package BusinessLayer;
import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant implements IRestaurantProcessing {

    private  HashMap<Order, ArrayList<MenuItem>> commands;
    private ArrayList<Order> orders;
    int ordersIndex = 0;
    private  ArrayList<MenuItem> meniu;
    private int index = 0;

    public Restaurant() {

        commands = new HashMap<>();
        meniu = new ArrayList<MenuItem>();
        orders = new ArrayList<Order>();

        addBasicItemsToMeniu();
        //addAnOrder();
        //System.out.println(commands.get(orders.get(0)).get(0));

    }

    public ArrayList<String> showOnTable(int nr){

        ArrayList<String> list = new ArrayList<String>();

        list.add(Integer.toString(orders.get(nr).getOrderId()));
        list.add(Integer.toString(orders.get(nr).getTable()));

        String returned = "";

        for(int i=0;i < commands.get(orders.get(nr)).size();i++) {

            if(commands.get(orders.get(nr)).get(i).base)
                returned = returned + ((BaseProduct)commands.get(orders.get(nr)).get(i)).getName();
            else if(commands.get(orders.get(nr)).get(i).composite)
                returned = returned + ((CompositeProduct)commands.get(orders.get(nr)).get(i)).getName();

            if(i+1 != commands.get(orders.get(nr)).size())
                returned = returned + ", ";
        }
        list.add(returned);
        System.out.println(list);
        return list;

    }

    private void addAnOrder() {

        ArrayList<MenuItem> lista = new ArrayList<MenuItem>();

        lista.add(new BaseProduct(1,"apa", 13.0));
        lista.add(new BaseProduct(2,"cartofi pai", 14.0));
        lista.add(new BaseProduct(3,"mamaliga", 11.0));

        orders.add(new Order(1, 12));
        ordersIndex++;

        commands.put(orders.get(0), lista);
        System.out.println(commands.containsKey(orders.get(0)));
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void addMeniuItem(){


    }
    public void deleteMeniuItem(){


    }
    public void modifyMeniuItem(){


    }
    public void addOrder(){


    }

    public void makeBill(){


    }

    public HashMap<Order, ArrayList<MenuItem>> getCommands() {
        return commands;
    }

    public ArrayList<MenuItem> getMeniu() {
        return meniu;
    }

    public void setCommands(HashMap<Order, ArrayList<MenuItem>> commands) {
        this.commands = commands;
    }

    public void setMeniu(ArrayList<MenuItem> meniu) {
        this.meniu = meniu;
    }

    public static void main(String[] args){

        Restaurant res = new Restaurant();
    }

    public void addBasicItemsToMeniu() {

        BaseProduct[] base = new BaseProduct[10];
        CompositeProduct[] composite = new CompositeProduct[3];

        base[0] = new BaseProduct(1, "apa", 2.0);
        base[1] = new BaseProduct(2, "cartofi pai", 12.0);
        base[2] = new BaseProduct(3, "Coca-Cola", 5.0);
        base[3] = new BaseProduct(4, "Pepsi", 5.0);
        base[4] = new BaseProduct(5, "Cascaval", 11.0);
        base[5] = new BaseProduct(6, "Orez Risotto", 9.0);
        base[6] = new BaseProduct(7, "inghetata", 10.0);
        base[7] = new BaseProduct(8, "Piept de pui", 10.0);
        base[8] = new BaseProduct(9, "Sos rosii", 10.0);
        base[9] = new BaseProduct(10, "Sos usturoi", 10.0);


        meniu.add(base[0]);
        meniu.add(base[1]);
        meniu.add(base[2]);
        meniu.add(base[3]);
        meniu.add(base[4]);
        meniu.add(base[5]);
        meniu.add(base[6]);
        meniu.add(base[7]);
        meniu.add(base[8]);
        meniu.add(base[9]);

        ArrayList<MenuItem> temp = new ArrayList<MenuItem>();

        temp.add(base[7]);
        temp.add(base[8]);
        composite[0] = new CompositeProduct(11,"friptura pui", 28.0, temp);

        temp = new ArrayList<>();
        temp.add(base[0]);
        temp.add(base[8]);
        composite[1] = new CompositeProduct(12, "ciorba", 10.0, temp);

        meniu.add(composite[0]);
        meniu.add(composite[1]);

        temp = new ArrayList<>();
        temp.add(composite[0]);
        temp.add(composite[1]);
        meniu.add(new CompositeProduct(13, "specialitatea zilei", 35.0, temp));

        index = index + 10;


    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getOrdersIndex() {
        return ordersIndex;
    }

    public void setOrdersIndex(int ordersIndex) {
        this.ordersIndex = ordersIndex;
    }

    @Override
    public void addBaseItem(int id, String nume, double pret){
        assert(id > 0);
        meniu.add(new BaseProduct(id,nume,pret));
        assert(meniu.contains(new BaseProduct(id, nume, pret)) == true);

    }

    @Override
    public void addCompositeItem(int id, String nume, double pret, ArrayList<MenuItem> list){
        assert(id > 0);
        meniu.add(new CompositeProduct(id,nume,pret, list));
        assert(meniu.contains(new BaseProduct(id, nume, pret)) == true);
    }

    @Override
    public void deleteItem(MenuItem itm) {
        assert(itm != null);
        if(itm.base)
            meniu.remove((BaseProduct)itm);
        else if(itm.composite){
            meniu.remove((CompositeProduct)itm);
        }
        assert(meniu.contains(itm) == true);

    }

    @Override
    public void schimbaNume(MenuItem item, String nume) {
        assert (item != null);

        if(item.base){
            ((BaseProduct) item).setName(nume);
            assert(((BaseProduct) item).getName().equals(nume));
        }
        else if(item.composite){
            ((CompositeProduct) item).setName(nume);
            assert(((CompositeProduct) item).getName().equals(nume));
        }


    }

    @Override
    public void schimbaPret(MenuItem item, Double pret) {

        assert (item != null);
        assert (pret >= 0);

        if(item.base){
            ((BaseProduct) item).setPrice(pret);
            assert(((BaseProduct) item).getPrice() == pret);
        }
        else if(item.composite){
            ((CompositeProduct) item).setPrice(pret);
            assert(((CompositeProduct) item).getPrice() == pret);
        }

    }

    @Override
    public void stergeSubProduse(CompositeProduct item, MenuItem subP) {
        assert(item != null);
        item.getSubproducts().remove(subP);
        assert(!(item.getSubproducts().contains(subP)));
    }

    @Override
    public void adaugaSubProdus(CompositeProduct item, MenuItem temp) {

        assert(item != null);
        item.getSubproducts().add(temp);//adauga in compozitie
        assert(item.getSubproducts().contains(temp));

    }
}
