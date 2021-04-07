public class Monom {
//-------------------------------------- variables
    private double coef;
    private int exp;

    private boolean removal = false;
                                                /// => coef*x^exp
//---------------------------------------

    public Monom(double coef,int exp) {
        this.coef = coef;
        this.exp = exp;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public double getCoef() {
        return coef;
    }

    public int getExp() {
        return exp;
    }

    public boolean isRemoval() {
        return removal;
    }

    public void setRemoval(boolean removal) {
        this.removal = removal;
    }

    @Override
    public String toString() {
        return "Monom{" +
                "coef=" + coef +
                ", exp=" + exp +
                '}';
    }
}
