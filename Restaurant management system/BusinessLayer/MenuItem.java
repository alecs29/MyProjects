package BusinessLayer;

public abstract class MenuItem {

    public boolean base = false;
    public boolean composite = false;


    public MenuItem(boolean base, boolean composite) {
        this.base = base;
        this.composite = composite;

    }

    public MenuItem() {


    }


//    @Override
//    public boolean equals(Object obj) {
//        MenuItem itm = (MenuItem)obj;
//
//        if(itm.base){
//            if(((BaseProduct)this).getId() == ((BaseProduct)itm).getId())
//                if(((BaseProduct)this).getName() == ((BaseProduct)itm).getName())
//                    if(((BaseProduct)this).getPrice() == ((BaseProduct)itm).getPrice())
//                        return true;
//        }
//        else if(itm.composite){
//            if(((CompositeProduct)this).getId() == ((CompositeProduct)itm).getId())
//                if(((CompositeProduct)this).getName() == ((CompositeProduct)itm).getName())
//                    if(((CompositeProduct)this).getPrice() == ((CompositeProduct)itm).getPrice())
//                        return true;
//        }
//        return false;
//    }




}
