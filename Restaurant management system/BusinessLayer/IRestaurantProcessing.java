package BusinessLayer;

import java.awt.*;
import java.util.ArrayList;

public interface IRestaurantProcessing {

/**
* @precondition id > 0
* @postcondition getMenu().contains(item) == true
*/
public void addBaseItem(int id, String nume, double pret);


/**
* @precondition list != null
 * precondition id > 0
* @postcondition getMenu().contains(item) == true
*/
public void addCompositeItem(int id, String nume, double pret, ArrayList<MenuItem> list);

/**
* @precondition item != null
* @postcondition getMenu().contains(item) == true
*/
public void deleteItem(MenuItem item);

/**
* @precondition item != null
* @postcondition getMenu().item.getName.equals(name) == true
*/
public void schimbaNume(MenuItem item, String name);

/**
 * @precondition item != null
 * @precondition pret >= 0
 * @postcondition getMenu().item.getPrice == pret
 */
public void schimbaPret(MenuItem item, Double pret);


/**
* @precondition item != null
* @postcondition getMenu().getSubProducts().contains(item) == true
*/
public void adaugaSubProdus(CompositeProduct item, MenuItem temp);


/**
 * @precondition item != null
 * @postcondition getMenu().getSubProducts().contains(item) == false
 */
public void stergeSubProduse(CompositeProduct item, MenuItem temp);


}

