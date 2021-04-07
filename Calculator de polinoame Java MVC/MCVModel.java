
import java.util.ArrayList;
import java.util.regex.Pattern;

import static jdk.nashorn.internal.objects.NativeString.trim;

public class MCVModel {

    static final String INITIAL_VALUE = "0";

    private Polinom[] m_valueOfPolynom = new Polinom[2];
    private Polinom m_valueOfResult;
    private Polinom m_restImpartire;

    public MCVModel() {

        m_valueOfPolynom[0] = new Polinom();
        m_valueOfPolynom[1] = new Polinom();
        m_valueOfResult = new Polinom();
        m_restImpartire = new Polinom();
    }

    public void reset(){
        m_valueOfPolynom[0].getPolinom().clear();
        m_valueOfPolynom[1].getPolinom().clear();
        m_valueOfResult.getPolinom().clear();
        m_restImpartire.getPolinom().clear();
    }


    private String[] auxiliaryMethod(String textPolynom){
        String delimiterPlus = "[+]";
        Pattern plusPattern = Pattern.compile(delimiterPlus);
        String[] result = plusPattern.split(textPolynom);

        return result;
    }
    private void createPolynomsFromString(String textPolynom, int number) {//throws PolinomIntrodusGresit {
        //String delimiterPlus = "[+]";
        //Pattern plusPattern = Pattern.compile(delimiterPlus);
        //String[] result = plusPattern.split(textPolynom);
        String[] result = auxiliaryMethod(textPolynom);
        for (String tempMonom : result) {
            tempMonom = trim(tempMonom);
            String delimiterMinus = "-";
            Pattern minusPattern = Pattern.compile(delimiterMinus);
            String[] result2 = minusPattern.split(tempMonom);
            int index = 0;
            for (String tempMonom2 : result2 ) {
                tempMonom2 = trim(tempMonom2);
                if(tempMonom2.equals("")) {
                    index++; continue;
                }
                int nrNegativ;        //// este 1 daca e primul element din sir(de ex : x^5 - 6*x^3 - 11*x^2) si -1 daca nu
                if(index != 0) nrNegativ = -1;
                else nrNegativ = 1;

                if(tempMonom2.indexOf('x') == -1) //*************************************************************************** caz numar;
                    m_valueOfPolynom[number].getPolinom().add(new Monom(Double.valueOf(tempMonom2)*nrNegativ, 0));
                else {//*************************************************************************** caz not numar - (inmultire cu sau fara * ) : 1) a*x^b   2) x^b    3)a*x    4)x   ->care poate fii la inceput de linie
                    int c = tempMonom2.indexOf('x'), steluta;  ////// scadem cu 1 daca exista sau cu 0 daca nu exista din pozitia caracterulului 'x' pt a gasi  coef.
                        if (tempMonom2 == "x") {/////////////////////////////////////cazul 4.
                            m_valueOfPolynom[number].getPolinom().add(new Monom(1 * nrNegativ, 1));
                        } else {
                            if (tempMonom2.indexOf('*') != -1)
                                steluta = 1;
                            else steluta = 0;
                            if (tempMonom2.charAt(0) != 'x') {
                                if (tempMonom2.indexOf('^') != -1) m_valueOfPolynom[number].getPolinom().add(new Monom(Double.valueOf(tempMonom2.substring(0, c - steluta)) * nrNegativ, Integer.valueOf(tempMonom2.substring(c + 2)).intValue()));
                                 else m_valueOfPolynom[number].getPolinom().add(new Monom(Double.valueOf(tempMonom2.substring(0, c - steluta)) * nrNegativ, 1));

                            } else {
                                if (tempMonom2.indexOf('^') != -1) m_valueOfPolynom[number].getPolinom().add(new Monom(1 * nrNegativ, Integer.valueOf(tempMonom2.substring(c + 2)).intValue()));
                                 else m_valueOfPolynom[number].getPolinom().add(new Monom(1 * nrNegativ, 1));
                            }
                        }
                } index++;
            }
        }
    }


    protected void sumOfPolynoms(String pol1, String pol2){

        createPolynomsFromString(pol1, 0);
        createPolynomsFromString(pol2, 1);

        double r = 0;
        for (Monom e1 : m_valueOfPolynom[0].getPolinom()) {
            r = e1.getCoef();
            for (Monom e2 : m_valueOfPolynom[1].getPolinom()) {
                if (e1.getExp() == e2.getExp()) {
                    r = r + e2.getCoef();
                }
            }
            Monom mon = new Monom(r, e1.getExp());
            m_valueOfResult.getPolinom().add(mon);
        }
        for (Monom e2 : m_valueOfPolynom[1].getPolinom()) {
            int ok = 0;
            r = e2.getCoef();
            for (Monom e1 : m_valueOfPolynom[0].getPolinom()) {
                if (e1.getExp() == e2.getExp()) {
                    ok = 1;
                }
            }
            if(ok == 0) {
                Monom mon = new Monom(r, e2.getExp());
                m_valueOfResult.getPolinom().add(mon);
            }
        }

        m_valueOfResult = m_valueOfResult.formatare();

    }


    protected void downOfPolynoms (String pol1, String pol2){

        createPolynomsFromString(pol1, 0);
        createPolynomsFromString(pol2, 1);
        double r = 0;
        for (Monom e1 : m_valueOfPolynom[0].getPolinom()) {
            r = e1.getCoef();
            for (Monom e2 : m_valueOfPolynom[1].getPolinom()) {
                if (e1.getExp() == e2.getExp()) {
                    r = r - e2.getCoef();
                }
            }
            Monom mon = new Monom(r, e1.getExp());
            m_valueOfResult.getPolinom().add(mon);
        }
        for (Monom e2 : m_valueOfPolynom[1].getPolinom()) {
            int ok = 0;
            r = - e2.getCoef();
            for (Monom e1 : m_valueOfPolynom[0].getPolinom()) {
                if (e1.getExp() == e2.getExp()) {
                    ok++;
                }
            }
            System.out.println("ok pentru " + e2.getCoef() + " - " + e2.getExp() + "este: " + ok + " si r este :" + r);
            if(ok == 0) {
                System.out.println(r + "    " + e2.getExp());
                Monom mon = new Monom(r, e2.getExp());
                m_valueOfResult.getPolinom().add(mon);
            }
        }

        m_valueOfResult = m_valueOfResult.formatare();
    }

    protected void multiplicationOfPolynoms (String pol1, String pol2){

        createPolynomsFromString(pol1, 0);
        createPolynomsFromString(pol2, 1);

        Polinom polRezultat  = new Polinom();
        ///------------------------------------------------------construim pozRezultat
        for(Monom e1 : m_valueOfPolynom[0].getPolinom()){
            for (Monom e2 : m_valueOfPolynom[1].getPolinom()){
                double coef = e1.getCoef()*e2.getCoef();
                int exp = e1.getExp() + e2.getExp();

                polRezultat.addPolinom(new Monom(coef, exp));

            }
        }


        m_valueOfResult = polRezultat.formatare();

    }

    protected void divisionOfPolynoms (String pol1, String pol2){
        createPolynomsFromString(pol1, 0);
        createPolynomsFromString(pol2, 1);

        //verific mon1 din e2 de cate ori merge in mon1 e1
            //daca merge atunci continui daca nu rez = 0 rest e1
        //aflu val -> o pun la rezultat
        //val * e2(tot)
        // e1 - (-val * e2)
        //noul e1
        m_valueOfPolynom[0] = m_valueOfPolynom[0].formatare();
        m_valueOfPolynom[1] = m_valueOfPolynom[1].formatare();

        Polinom auxPol = new Polinom();
        Polinom rezultat = new Polinom();
        Polinom rest = new Polinom();

        for( Monom e : m_valueOfPolynom[0].getPolinom()){   ///restul e deocamdata primul polinom
            rest.getPolinom().add(e);
        }


        int stop = 0;        ///se face 1 cand gr(elem1 din e2) < gr(elem1 din e1)
        while(stop == 0){

            if(rest.getPolinom().get(0).getExp() >= m_valueOfPolynom[1].getPolinom().get(0).getExp()) {

                double nouCoef = rest.getPolinom().get(0).getCoef() / m_valueOfPolynom[1].getPolinom().get(0).getCoef();
                int nouExp = rest.getPolinom().get(0).getExp() - m_valueOfPolynom[1].getPolinom().get(0).getExp();
                rezultat.getPolinom().add(new Monom(nouCoef, nouExp));

                for (Monom i : m_valueOfPolynom[1].getPolinom()) {
                    Monom mon = new Monom((-1) * nouCoef * i.getCoef(), nouExp + i.getExp());
                    auxPol.addPolinom(mon);

                }

                rest = rest.plus(rest, auxPol);
                rest.getPolinom().remove(0);
                auxPol.getPolinom().clear();
            }
            else stop = 1;

            if(rest.getPolinom().size() == 0)
                stop = 1;
        }

        m_valueOfResult = rezultat;
        m_valueOfResult = m_valueOfResult.formatare();
        m_restImpartire = rest;
        m_restImpartire = m_restImpartire.formatare();
        m_valueOfResult.slefuire();
        m_restImpartire.slefuire();
    }

    protected void derivationOfPolynoms (String pol1, String pol2, int witchPol){
        createPolynomsFromString(pol1, 0);
        createPolynomsFromString(pol2, 1);


        for (Monom e1 : m_valueOfPolynom[witchPol].getPolinom()) {

            m_valueOfResult.addPolinom(new Monom(e1.getCoef()*e1.getExp(),e1.getExp()-1));

        }

        m_valueOfResult = m_valueOfResult.formatare();
        m_valueOfResult.slefuire();
    }

    protected void integrationOfPolynoms (String pol1, String pol2, int witchPol){
        createPolynomsFromString(pol1, 0);
        createPolynomsFromString(pol2, 1);

        for (Monom e1 : m_valueOfPolynom[witchPol].getPolinom()) {

            m_valueOfResult.addPolinom(new Monom(e1.getCoef()*1/(e1.getExp()+1),e1.getExp()+1));

        }
        m_valueOfResult = m_valueOfResult.formatare();
        m_valueOfResult.slefuire();
    }





    public Polinom getM_valueOfPolynom1() {
        return m_valueOfPolynom[0];
    }

    public Polinom getM_valueOfPolynom2() {
        return m_valueOfPolynom[1];
    }

    public void setM_valueOfPolynom1(Polinom m_valueOfPolynom1) {
        this.m_valueOfPolynom[0] = m_valueOfPolynom1;
    }

    public void setM_valueOfPolynom2(Polinom m_valueOfPolynom2) {
        this.m_valueOfPolynom[1] = m_valueOfPolynom2;
    }

    public Polinom getM_valueOfResult() {
        return m_valueOfResult;
    }

    public Polinom getM_restImpartire() {
        return m_restImpartire;
    }

    public void setM_valueOfResult(Polinom m_valueOfResult) {
        this.m_valueOfResult = m_valueOfResult;
    }


    //@Override
    public String toString(Polinom pol) {
        String res = "";
        int index = 0;
        String plus = " + ";

        boolean zero = true;
        for (Monom e : pol.getPolinom() ) {
            if(e.getCoef() != 0)
                zero = false;
            if(index == 0 && e.getCoef() >0) {
                plus = "";
            }
            else if(e.getCoef() < 0 && e.getCoef() != -1)
                plus = " ";
            else if(e.getCoef() > 0)
                plus = " +";
            else if(e.getCoef() < 0 && e.getCoef() == -1)
                plus = " -";
            else if(e.getCoef() != -1)
                plus = " ";

            if(e.getCoef() == 1 || e.getCoef() == -1) {//new Integer(1).doubleValue())
                if (e.getExp() == 0)
                    res = res + plus + "1";
                else if (e.getExp() == 1)
                    res = res + plus + "x";
                else if (e.getExp() > 1)
                    res = res + plus + "x^" + e.getExp();
            }
            else if ((e.getCoef() > 1) || (e.getCoef() < -1) || (e.getCoef() < 1 && e.getCoef() > -1) && (e.getCoef() != 0)) {
                    if(e.getExp() == 0)
                        res = res + plus + e.getCoef();
                    else if(e.getExp() == 1)
                        res = res + plus + e.getCoef() + "x";
                    else if(e.getExp() > 1)
                        res = res + plus + e.getCoef() +"x^" + e.getExp();
            }

            index++;
        }

        System.out.println(res);
        if(zero == false)
            return res;
        else{
            res = "0";
            return res;
        }

    }
}
