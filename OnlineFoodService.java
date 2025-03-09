import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OnlineFoodService extends JFrame {
    private JTextArea orderSummary;
    private StringBuilder orderedItems = new StringBuilder();
    private int totalPrice = 0;
    private Menu menu;

    public OnlineFoodService() {
        
        JOptionPane.showMessageDialog(this, "Welcome to the Yummy Deshi Food!");
        
        setTitle("Yummy Deshi Food Restaurant");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        menu = new Menu(); 

        
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createTitledBorder("Menu"));

        addMenuItems(menuPanel, "Biriyani", menu.getBiriyanis());
        addMenuItems(menuPanel, "Kebab", menu.getKebabs());
        addMenuItems(menuPanel, "Fish", menu.getFishes());
        addMenuItems(menuPanel, "Drinks", menu.getDrinks());

        add(new JScrollPane(menuPanel), BorderLayout.WEST);

       
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BorderLayout());
        orderPanel.setBorder(BorderFactory.createTitledBorder("Your Order"));

        orderSummary = new JTextArea();
        orderSummary.setEditable(false);
        orderPanel.add(new JScrollPane(orderSummary), BorderLayout.CENTER);
        add(orderPanel, BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel();
        JButton finishOrderButton = new JButton("Finish Order");
        JButton clearOrderButton = new JButton("Clear Order");

        finishOrderButton.addActionListener(e -> showCustomerDetails());
        clearOrderButton.addActionListener(e -> clearOrder());

        buttonPanel.add(finishOrderButton);
        buttonPanel.add(clearOrderButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addMenuItems(JPanel menuPanel, String category, java.util.List<FoodName> items) {
        menuPanel.add(new JLabel(category + ":"));
        for (FoodName item : items) {
            JButton addButton = new JButton("Add");
            addButton.addActionListener(e -> addItemToOrder(item));
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.add(new JLabel("• " + item), BorderLayout.CENTER);
            itemPanel.add(addButton, BorderLayout.EAST);
            menuPanel.add(itemPanel);
        }
    }

    private void addItemToOrder(FoodName item) {
        orderedItems.append(item.toString()).append("\n");
        totalPrice += item.getPrice();  
        updateOrderSummary();           
    }

    private void updateOrderSummary() {
        orderSummary.setText(orderedItems.toString()); 
        orderSummary.append("\nTotal Price: Tk" + totalPrice + "\n");  
    }

    private void clearOrder() {
        orderedItems.setLength(0);
        totalPrice = 0;  
        orderSummary.setText("");  
    }

    private void showCustomerDetails() {
        JFrame customerDetailsFrame = new JFrame("Customer Details");
        customerDetailsFrame.setSize(300, 200);
        customerDetailsFrame.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel nameLabel = new JLabel("Customer Name:");
        JTextField nameField = new JTextField();

        JLabel addressLabel = new JLabel("Customer Address:");
        JTextField addressField = new JTextField();

        JLabel mobileLabel = new JLabel("Mobile Number:");
        JTextField mobileField = new JTextField();

        JButton submitButton = new JButton("Finish Order");

        customerDetailsFrame.add(nameLabel);
        customerDetailsFrame.add(nameField);
        customerDetailsFrame.add(addressLabel);
        customerDetailsFrame.add(addressField);
        customerDetailsFrame.add(mobileLabel);
        customerDetailsFrame.add(mobileField);
        customerDetailsFrame.add(new JLabel());
        customerDetailsFrame.add(submitButton);

        customerDetailsFrame.setVisible(true);

        submitButton.addActionListener(e -> {
            String customerName = nameField.getText();
            String customerAddress = addressField.getText();
            String customermobileNum = mobileField.getText();

            if (customerName.isEmpty() || customerAddress.isEmpty() || customermobileNum.isEmpty()) {
                JOptionPane.showMessageDialog(customerDetailsFrame, "Please fill out all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Thanks, " + customerName + "! Your order will be delivered to " + customerAddress + ".\nTotal: Tk" + totalPrice);
                customerDetailsFrame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OnlineFoodService().setVisible(true));
    }
}
