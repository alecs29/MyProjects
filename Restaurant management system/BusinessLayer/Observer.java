package BusinessLayer;

import PresentationLayer.Data;

import java.util.ArrayList;

public interface Observer {


    void update(ArrayList<Data> lista);
}
