import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TempConverter extends JFrame {

    private JTextField textBox;
    private JComboBox<String> fromBox;
    private JComboBox<String> toBox;
    private JLabel answerLabel;

    public TempConverter() {
        setTitle("Temp Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        
        JLabel title = new JLabel("Temperature Converter", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(title);

        
        JPanel inputRow = new JPanel(new FlowLayout());
        inputRow.add(new JLabel("Temp:"));
        textBox = new JTextField(10);
        inputRow.add(textBox);
        panel.add(inputRow);

        
        JPanel fromRow = new JPanel(new FlowLayout());
        fromRow.add(new JLabel("From:"));
        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        fromBox = new JComboBox<>(units);
        fromRow.add(fromBox);
        panel.add(fromRow);

        
        JPanel toRow = new JPanel(new FlowLayout());
        toRow.add(new JLabel("To:"));
        toBox = new JComboBox<>(units);
        toBox.setSelectedIndex(1);
        toRow.add(toBox);
        panel.add(toRow);

        
        JPanel buttonRow = new JPanel(new FlowLayout());
        JButton convertBtn = new JButton("Convert");
        JButton clearBtn = new JButton("Clear");
        buttonRow.add(convertBtn);
        buttonRow.add(clearBtn);
        panel.add(buttonRow);

        
        answerLabel = new JLabel("Enter temp and click Convert", SwingConstants.CENTER);
        answerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(answerLabel);

        add(panel);

        
        convertBtn.addActionListener(e -> doConvert());
        clearBtn.addActionListener(e -> doClear());
        textBox.addActionListener(e -> doConvert());
    }

    private void doConvert() {
        String input = textBox.getText().trim();

        if (input.isEmpty()) {
            answerLabel.setText("Please enter a number");
            return;
        }

        double temp;
        try {
            temp = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            answerLabel.setText("Not a valid number");
            return;
        }

        int from = fromBox.getSelectedIndex();
        int to = toBox.getSelectedIndex();

        
        double celsius;
        if (from == 0) {
            celsius = temp; 
        } else if (from == 1) {
            celsius = (temp - 32) * 5.0 / 9.0; 
        } else {
            celsius = temp - 273.15; 
        }

        
        double result;
        String unit;
        if (to == 0) {
            result = celsius;
            unit = "C";
        } else if (to == 1) {
            result = celsius * 9.0 / 5.0 + 32;
            unit = "F";
        } else {
            result = celsius + 273.15;
            unit = "K";
        }

        answerLabel.setText(String.format("Result: %.2f %s", result, unit));
    }

    private void doClear() {
        textBox.setText("");
        answerLabel.setText("Enter temp and click Convert");
        fromBox.setSelectedIndex(0);
        toBox.setSelectedIndex(1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TempConverter().setVisible(true);
        });
    }
}