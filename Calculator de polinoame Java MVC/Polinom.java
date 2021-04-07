import java.util.ArrayList;

public class Polinom {

    private ArrayList<Monom> polinom;

    public Polinom() {
        this.polinom = new ArrayList<Monom>();

    }

    public ArrayList<Monom> getPolinom() {
        return polinom;
    }

    public void setPolinom(ArrayList<Monom> polinom) {
        this.polinom = polinom;
    }

    public void addPolinom(Monom e) {
        polinom.add(e);
    }

    public void slefuire(){

        for( Monom e : polinom)
            e.setCoef(Math.floor(e.getCoef() * 100) / 100);
    }


    public Polinom formatare(){


        Polinom result = new Polinom();

        int indexOfe1 = 0, indexOfe2 = 0;
        for(Monom e1 : polinom){

            double r;
            if(e1.isRemoval() == false)
                r = e1.getCoef();
            else {
                indexOfe1++;
                continue;
            }
            indexOfe2 = 0;
            for (Monom e2 : polinom){
                if(indexOfe1 > indexOfe2) {
                    indexOfe2++;
                    continue;
                }
                if(e1.getExp() == e2.getExp() &&  e2.isRemoval() == false){
                    r = r + e2.getCoef();

                    if(indexOfe1 == indexOfe2)
                        r = r / 2;
                    else
                        e2.setRemoval(true);
                }
                indexOfe2++;

            }

            result.addPolinom(new Monom(r, e1.getExp()));
            indexOfe1++;
        }


        double coefAux;
        int expAux;
        for( Monom e1 : result.getPolinom()){

            for(Monom e2 : result.getPolinom()){

                if(e1.getExp() > e2.getExp())
                {
                    coefAux = e1.getCoef();
                    expAux = e1.getExp();
                    e1.setCoef(e2.getCoef());
                    e1.setExp(e2.getExp());
                    e2.setCoef(coefAux);
                    e2.setExp(expAux);
                }

            }
        }
        return result;
    }


    protected Polinom plus (Polinom pol1, Polinom pol2){

        Polinom result = new Polinom();
        double r = 0;
        for (Monom e1 : pol1.getPolinom()) {
            r = e1.getCoef();
            for (Monom e2 : pol2.getPolinom()) {
                if (e1.getExp() == e2.getExp()) {
                    r = r + e2.getCoef();
                }
            }
            Monom mon = new Monom(r, e1.getExp());
            result.getPolinom().add(mon);
        }
        for (Monom e2 : pol2.getPolinom()) {
            int ok = 0;
            r = + e2.getCoef();
            for (Monom e1 : pol1.getPolinom()) {
                if (e1.getExp() == e2.getExp()) {
                    ok++;
                }
            }

            if(ok == 0) {
                System.out.println(r + "    " + e2.getExp());
                Monom mon = new Monom(r, e2.getExp());
                result.getPolinom().add(mon);
            }
        }

        result = result.formatare();
        return result;
    }
}
