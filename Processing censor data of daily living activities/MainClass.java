import java.io.IOException;

public class MainClass {

    public static void main(String[] args){
        try {
            TaskManager manager = new TaskManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
