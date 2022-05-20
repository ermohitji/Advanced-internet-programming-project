import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.DoubleBinaryOperator;

public class ScientificCalculator extends JDialog {

    public static class CalculatorUI {
        private JTextField display;
        private JButton a7Button;
        private JButton a8Button;
        private JButton a9Button;
        private JButton clrButton;
        private JButton a4Button;
        private JButton a5Button;
        private JButton a6Button;
        private JButton xButton2;
        private JButton a1Button;
        private JButton a2Button;
        private JButton a3Button;
        private JButton divideButton;
        private JButton dotButton;
        private JButton a0Button;
        private JButton plusButton;
        private JButton minusButton;
        private JButton sinButton;
        private JButton cosButton;
        private JButton tanButton;
        private JButton x2Button;
        private JButton lnButton;
        private JButton logButton;
        private JButton inverseXButton;
        private JButton xPowerYButton;
        private JButton πButton;
        private JButton eButton;
        private JButton signChangeButton;
        private JButton equalToButton;
        private JButton sin1Button;
        private JButton cos1Button;
        private JButton tan1Button;
        private JButton rootxButton;
        private JPanel calculatorView;
        private JRadioButton ONRadioButton;
        private JRadioButton OFFRadioButton;
        private Double leftOperand;
        private Double rightOperand;
        private Operation calcOperation;

        private void createUIComponents() {
            // TODO: place custom component creation code here
            a7Button.addActionListener(new NumberBtnClicked(a7Button.getText()));
            a8Button.addActionListener(new NumberBtnClicked(a8Button.getText()));
            a9Button.addActionListener(new NumberBtnClicked(a9Button.getText()));
            a4Button.addActionListener(new NumberBtnClicked(a4Button.getText()));
            a5Button.addActionListener(new NumberBtnClicked(a5Button.getText()));
            a6Button.addActionListener(new NumberBtnClicked(a6Button.getText()));
            a1Button.addActionListener(new NumberBtnClicked(a1Button.getText()));
            a2Button.addActionListener(new NumberBtnClicked(a2Button.getText()));
            a3Button.addActionListener(new NumberBtnClicked(a3Button.getText()));
            a0Button.addActionListener(new NumberBtnClicked(a0Button.getText()));
        }

        private class NumberBtnClicked implements ActionListener {

            private String value;

            public NumberBtnClicked(String value) {
                this.value = value;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if(leftOperand == null || leftOperand == 0.0) {
                    value = display.getText() + value;
                }else{
                    rightOperand = Double.valueOf(value);
                }
                display.setText(value);

            }
        }

        private class OperationBtnClicked implements ActionListener {

            private Operation operation;

            public OperationBtnClicked(Operation operation) {
                this.operation = operation;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                calcOperation = operation;
                leftOperand = Double.valueOf(display.getText());
            }
        }

        private class ClearBtnClicked implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                display.setText("");
                leftOperand = 0.0;
                rightOperand = 0.0;
            }
        }

        private class DigitBtnClicked implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                display.setText(display.getText() + ".");

            }
        }

        private class EqualBtnClicked implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                Double output = calcOperation.getOperator().applyAsDouble(leftOperand, rightOperand);
                display.setText(output%1==0?String.valueOf(output.intValue()):String.valueOf(output));
                leftOperand = 0.0;
                rightOperand = 0.0;
            }
        }

        private class SignBtnClicked implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                display.setText("-"+ display.getText());
            }
        }



    }
    public enum Operation {
        ADDITION((x, y) -> x+y),
        SUBTRACTION((x, y) -> x-y),
        DIVISION((x, y) -> x/y),
        MULTIPLICATION((x, y) -> x*y);

        private DoubleBinaryOperator operator;

        Operation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        public DoubleBinaryOperator getOperator() {
            return operator;
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new CalculatorUI().calculatorView);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}