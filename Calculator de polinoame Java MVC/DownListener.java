import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DownListener implements ActionListener {

    private MCVModel m_model;
    private MCVView m_view;

    public DownListener(MCVModel m_model, MCVView m_view) {
        this.m_model = m_model;
        this.m_view = m_view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputPol1;
        String inputPol2;
        String output;

        inputPol1 = m_view.getTxtPol1().getText();
        inputPol2 = m_view.getTxtPol2().getText();


        m_model.downOfPolynoms(inputPol1,inputPol2);

        m_view.setRestFieldTxt("");


        m_view.setResult(m_model.toString(m_model.getM_valueOfResult()));

        m_model.reset();

    }
}
