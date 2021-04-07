import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DerivateListener implements ActionListener {

    private MCVModel m_model;
    private MCVView m_view;
    private int witchPol;

    public DerivateListener(MCVModel m_model, MCVView m_view, int witchPol) {
        this.m_model = m_model;
        this.m_view = m_view;
        this.witchPol = witchPol;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputPol1;
        String inputPol2;
        String output;

        inputPol1 = m_view.getTxtPol1().getText();
        inputPol2 = m_view.getTxtPol2().getText();


        m_model.derivationOfPolynoms(inputPol1,inputPol2,witchPol);

        m_view.setRestFieldTxt("");

        m_view.setResult(m_model.toString(m_model.getM_valueOfResult()));

        m_model.reset();

    }
}
