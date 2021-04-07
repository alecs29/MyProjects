import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MCVView extends JFrame {

    private JPanel contentPane;

    private JTextField txtPol1;
    private JTextField txtPol2;
    private JTextField resultFieldTxt;
    private JTextField restFieldTxt;

    private JButton btnAx;
    private JButton btnIntegralOfAx;
    private JButton btnBx;
    private JButton btnIntegralOfBx;

    private JButton buttonPlus;
    private JButton buttonMinus;
    private JButton buttonMultiplication;
    private  JButton buttonDivision;

    private MCVModel m_model;

    public static void main(String[] args) {
        MCVModel model = new MCVModel();
        MCVView frame = new MCVView(model);
                    frame.setVisible(true);
    }

    public MCVView(MCVModel model ) {

        m_model = model;
        //model.setM_valueState(MCVModel.INITIAL_VALUE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 579, 250);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JPanel panelA = new JPanel();
        FlowLayout fl_panelA = (FlowLayout) panelA.getLayout();
        fl_panelA.setAlignment(FlowLayout.LEFT);
        contentPane.add(panelA);

        JLabel lblAx = new JLabel("A(x):");
        lblAx.setFont(new Font("Arial", Font.BOLD, 13));
        lblAx.setHorizontalAlignment(SwingConstants.LEFT);
        panelA.add(lblAx);

        txtPol1 = new JTextField();
        txtPol1.setText("");
        panelA.add(txtPol1);
        txtPol1.setColumns(30);

        btnAx = new JButton("( A(x) )'");
        btnAx.setFont(new Font("Arial", Font.BOLD, 12));
        panelA.add(btnAx);

        btnIntegralOfAx = new JButton("Integral of A(x)");
        btnIntegralOfAx.setFont(new Font("Arial", Font.BOLD, 12));
        panelA.add(btnIntegralOfAx);

        JPanel panelBAndSymbols = new JPanel();
        contentPane.add(panelBAndSymbols);
        panelBAndSymbols.setLayout(new BoxLayout(panelBAndSymbols, BoxLayout.Y_AXIS));

        JPanel panel2_1 = new JPanel();
        FlowLayout fl_panel2_1 = (FlowLayout) panel2_1.getLayout();
        fl_panel2_1.setAlignment(FlowLayout.LEFT);
        panelBAndSymbols.add(panel2_1);

        JLabel lblBx = new JLabel("B(x):");
        panel2_1.add(lblBx);
        lblBx.setHorizontalAlignment(SwingConstants.LEFT);
        lblBx.setFont(new Font("Arial", Font.BOLD, 13));

        txtPol2 = new JTextField();
        panel2_1.add(txtPol2);
        txtPol2.setText("");
        txtPol2.setColumns(30);

        btnBx = new JButton("( B(x) )'");
        btnBx.setFont(new Font("Arial", Font.BOLD, 12));
        panel2_1.add(btnBx);

        btnIntegralOfBx = new JButton("Integral of B(x)");
        btnIntegralOfBx.setFont(new Font("Arial", Font.BOLD, 12));
        panel2_1.add(btnIntegralOfBx);

        JPanel panel2_2 = new JPanel();
        FlowLayout fl_panel2_2 = (FlowLayout) panel2_2.getLayout();
        fl_panel2_2.setAlignment(FlowLayout.LEFT);
        panelBAndSymbols.add(panel2_2);

        buttonPlus = new JButton("+");
        buttonPlus.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel2_2.add(buttonPlus);

        buttonMinus = new JButton("-");
        buttonMinus.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel2_2.add(buttonMinus);

        buttonMultiplication = new JButton("*");
        buttonMultiplication.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel2_2.add(buttonMultiplication);

        buttonDivision = new JButton("/");
        buttonDivision.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel2_2.add(buttonDivision);

        JPanel panelResult = new JPanel();
        FlowLayout fl_panelResult = (FlowLayout) panelResult.getLayout();
        fl_panelResult.setAlignment(FlowLayout.LEFT);
        contentPane.add(panelResult);

        JLabel lblResult = new JLabel("R:");
        lblResult.setFont(new Font("Arial", Font.BOLD, 13));
        panelResult.add(lblResult);

        resultFieldTxt = new JTextField();
        panelResult.add(resultFieldTxt);
        resultFieldTxt.setColumns(45);

        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        contentPane.add(panel);

        JLabel lblRest = new JLabel("rest:  ");
        lblRest.setFont(new Font("Arial", Font.BOLD, 13));
        panel.add(lblRest);

        restFieldTxt = new JTextField();
        restFieldTxt.setColumns(43);
        panel.add(restFieldTxt);
    }

    public MCVModel getM_model() {
        return m_model;
    }

    public void setM_model(MCVModel m_model) {
        this.m_model = m_model;
    }



    public JTextField getTxtPol1() {

        return txtPol1;
    }

    public JTextField getTxtPol2() {
        return txtPol2;
    }

    public JTextField getResultFieldTxt() {
        return resultFieldTxt;
    }

    public JTextField getRestFieldTxt() {
        return restFieldTxt;
    }

    public JButton getBtnAx() {
        return btnAx;
    }

    public JButton getBtnIntegralOfAx() {
        return btnIntegralOfAx;
    }

    public JButton getBtnBx() {
        return btnBx;
    }

    public JButton getBtnIntegralOfBx() {
        return btnIntegralOfBx;
    }

    public void setTxtPol1(JTextField txtPol1) {
        this.txtPol1 = txtPol1;
    }

    public void setTxtPol2(JTextField txtPol2) {
        this.txtPol2 = txtPol2;
    }

    public void setResult(String resultTxt) {
        resultFieldTxt.setText(resultTxt);
        resultFieldTxt.repaint();
        resultFieldTxt.revalidate();
        getContentPane().repaint();
        getContentPane().revalidate();
        getContentPane().setVisible(false);
        getContentPane().setVisible(true);
    }

    public void setRestFieldTxt(String restTxt) {
        restFieldTxt.setText(restTxt);
    }

    public void setBtnAx(JButton btnAx) {
        this.btnAx = btnAx;
    }

    public void setBtnIntegralOfAx(JButton btnIntegralOfAx) {
        this.btnIntegralOfAx = btnIntegralOfAx;
    }

    public void setBtnBx(JButton btnBx) {
        this.btnBx = btnBx;
    }

    public void setBtnIntegralOfBx(JButton btnIntegralOfBx) {
        this.btnIntegralOfBx = btnIntegralOfBx;
    }

    void addSumListener(ActionListener plus){
        buttonPlus.addActionListener(plus);

    }

    void addDownListener(ActionListener minus){
        buttonMinus.addActionListener(minus);
    }

    public void addMultiplyListener(MultiplyListener star) {
        buttonMultiplication.addActionListener(star);
    }

    public void addIntegrateListenerA(ActionListener integrate) {
        btnIntegralOfAx.addActionListener(integrate);
    }

    public void addDerivateListenerA(ActionListener derivate) {

        btnAx.addActionListener(derivate);
    }

    public void addIntegrateListenerB(ActionListener integrate) {
        btnIntegralOfBx.addActionListener(integrate);
    }

    public void addDerivateListenerB(ActionListener derivate) {
        btnBx.addActionListener(derivate);
    }

    public void addDivideListener(DivideListener bar) {
        buttonDivision.addActionListener(bar);
    }
}
