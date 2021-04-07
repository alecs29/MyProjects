import java.awt.event.ActionListener;

public class MCVController {

    private MCVModel m_model;
    private MCVView m_view;

    public MCVController(MCVModel m_model, MCVView m_view) {
        this.m_model = m_model;
        this.m_view = m_view;

        m_view.addSumListener(new AddListener(m_model,m_view));
        m_view.addDownListener(new DownListener(m_model,m_view));
        m_view.addMultiplyListener(new MultiplyListener(m_model,m_view));
        m_view.addDivideListener(new DivideListener(m_model,m_view));

        m_view.addDerivateListenerA(new DerivateListener(m_model,m_view, 0));
        m_view.addIntegrateListenerA(new IntegrateListener(m_model,m_view, 0));

        m_view.addDerivateListenerB(new DerivateListener(m_model,m_view, 1));
        m_view.addIntegrateListenerB(new IntegrateListener(m_model,m_view, 1));
    }

}
