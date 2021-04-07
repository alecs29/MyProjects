package BusinessLayer;

import PresentationLayer.Data;

import java.util.ArrayList;

public interface Subject {

    void notifyObsevers(ArrayList<Data> list);
}
