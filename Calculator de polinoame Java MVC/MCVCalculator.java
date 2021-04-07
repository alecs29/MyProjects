public class MCVCalculator {

    public static void main(String[] args){

        MCVModel model  = new MCVModel();
        MCVView view = new MCVView(model);
        view.setVisible(true);
        MCVController coltroller = new MCVController(model, view);
        
    }
}
