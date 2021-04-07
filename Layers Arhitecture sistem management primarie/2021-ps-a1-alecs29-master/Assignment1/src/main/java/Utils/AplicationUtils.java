package Utils;


import entity.Address;
import entity.User;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import repository.UserRepo;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AplicationUtils {

    public static boolean DocumentListFlahRefresh = true;

    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }

    public static ImageView byteArrayToImage(byte[] byteArray){
        ImageView pictureItemImage = new ImageView();
        pictureItemImage.setImage(new Image(new ByteArrayInputStream(byteArray)));

        return pictureItemImage;
    }

    public static java.sql.Date convert(java.util.Date date)
    {
        return new java.sql.Date(date.getTime());
    }


    public static int convertYearTimeStampToInt(java.sql.Date timestamp){

            return Integer.valueOf(timestamp.toString().substring(0,4)).intValue();

    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
