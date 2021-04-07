package Utils;

import javafx.scene.control.Alert;

import javax.swing.*;

public class ControllerUtils {
    //----------------------------------------------------------------------Login

    public static String WrongPasswordContentError = "Va rugam introduceti alta parola.";
    public static String WrongUsernameContentError = "Va rugam introduceti alt email.";
    //----------------------------------------------------------------------Create Account

    public static String LengthAccountError = "Toate campurile trebuie sa aiba cel putin 2 caractere.";
    public static String userDuplicateError = "User already has an account.";
    public static String userWasSucsessfullyAdded = "User was successfully added.";

    //----------------------------------------------------------------------other
    public static String DocumentDuplicateError = "document already exists.";
    public static String DocumentWasSucsessfullyAdded = "document was successfully added.";
    public static String DocumentWasSucsessfullyDeleted = "document was successfully deleted.";

    public static String AddressDuplicateError = "address already exists.";
    public static String AddressWasSucsessfullyAdded = "address was successfully added.";
    public static String AddressWasSucsessfullyDeleted = "address was successfully deleted.";

    public static String ThreeRequestsFlag = "this address already has 3 requests this year.";
    public static String RequestSameAdressError = "o cerere nu se poate face de 2 ori pe aceeasi adresa.";



    public static void alert1(String contextText){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(contextText);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    public static void alert2(String contextText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succes");
        alert.setContentText(contextText);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
