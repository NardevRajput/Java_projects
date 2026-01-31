import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Contact {
    String name;
    String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}

public class ContactManagementApp extends JFrame {
    private ArrayList<Contact> contacts = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;

    public ContactManagementApp() {
        setTitle("Contact Management App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel for input fields and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 4, 10, 10));

        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();

        JButton addButton = new JButton("Add Contact");
        JButton deleteButton = new JButton("Delete Selected");

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Phone:"));
        inputPanel.add(phoneField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        // Table to display contacts
        String[] columnNames = {"Name", "Phone"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add action listeners
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both name and phone.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Contact contact = new Contact(name, phone);
            contacts.add(contact);
            tableModel.addRow(new Object[]{name, phone});
            nameField.setText("");
            phoneField.setText("");
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                contacts.remove(selectedRow);
                tableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a contact to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Layout setup
        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ContactManagementApp app = new ContactManagementApp();
            app.setVisible(true);
        });
    }
}