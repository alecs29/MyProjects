package BusinessLayer;
import PresentationLayer.AdministratorGUI;
import PresentationLayer.ChefGui;
import PresentationLayer.WaiterGUI;

public class MainClass {

    public static void main(String[] args){

        Restaurant res = new Restaurant();
        AdministratorGUI administrator = new AdministratorGUI(res);
        ChefGui chef = new ChefGui();
        WaiterGUI waiter = new WaiterGUI(res, chef);

        administrator.setVisible(true);
        waiter.setVisible(true);
        chef.setVisible(true);

        Controller controller = new Controller(res, administrator, waiter);

    }

}
