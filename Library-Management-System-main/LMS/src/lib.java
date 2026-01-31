import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class lib extends JFrame implements ActionListener {

    private JTextField tfId, tfTitle, tfAuthor, tfPublisher, tfYear, tfISBN, tfCopies;
    private JButton btnAdd, btnView, btnEdit, btnDelete, btnClear, btnExit;
    private ArrayList<String[]> books = new ArrayList<>();

    public lib() {
        setTitle("Library Management System");
        setSize(1100, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Font font = new Font("Segoe UI", Font.BOLD, 18);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2),
                "Library Book Details",
                0, 0,
                new Font("Segoe UI", Font.BOLD, 22)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel[] labels = {
                new JLabel("Book ID"),
                new JLabel("Book Title"),
                new JLabel("Author"),
                new JLabel("Publisher"),
                new JLabel("Year of Publication"),
                new JLabel("ISBN"),
                new JLabel("Number of Copies")
        };

        for (JLabel label : labels) {
            label.setFont(font);
        }

        tfId = new JTextField(20);
        tfTitle = new JTextField(20);
        tfAuthor = new JTextField(20);
        tfPublisher = new JTextField(20);
        tfYear = new JTextField(20);
        tfISBN = new JTextField(20);
        tfCopies = new JTextField(20);

        JTextField[] fields = { tfId, tfTitle, tfAuthor, tfPublisher, tfYear, tfISBN, tfCopies };
        for (JTextField tf : fields) {
            tf.setFont(font);
            tf.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
        }

        int row = 0;
        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = row;
            mainPanel.add(labels[i], gbc);

            gbc.gridx = 1;
            mainPanel.add(fields[i], gbc);
            row++;
        }

        btnAdd = new JButton("Add");
        btnView = new JButton("View");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        btnExit = new JButton("Exit");

        JButton[] buttons = { btnAdd, btnView, btnEdit, btnDelete, btnClear, btnExit };
        for (JButton btn : buttons) {
            btn.setFont(font);
            btn.addActionListener(this);
        }

        btnAdd.setBackground(new Color(76, 175, 80));
        btnView.setBackground(new Color(33, 150, 243));
        btnEdit.setBackground(new Color(255, 193, 7));
        btnDelete.setBackground(new Color(244, 67, 54));
        btnClear.setBackground(Color.LIGHT_GRAY);
        btnExit.setBackground(Color.DARK_GRAY);

        btnAdd.setForeground(Color.WHITE);
        btnView.setForeground(Color.WHITE);
        btnDelete.setForeground(Color.WHITE);
        btnExit.setForeground(Color.WHITE);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setBackground(new Color(245, 247, 250));

        for (JButton btn : buttons) {
            btnPanel.add(btn);
        }

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        mainPanel.add(btnPanel, gbc);

        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAdd) {
            String[] book = {
                    tfId.getText(),
                    tfTitle.getText(),
                    tfAuthor.getText(),
                    tfPublisher.getText(),
                    tfYear.getText(),
                    tfISBN.getText(),
                    tfCopies.getText()
            };
            books.add(book);
            JOptionPane.showMessageDialog(this, "Book Added Successfully");
            clearFields();

        } else if (e.getSource() == btnView) {
            showTable();

        } else if (e.getSource() == btnEdit) {
            String id = JOptionPane.showInputDialog(this, "Enter Book ID to Edit:");
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i)[0].equals(id)) {
                    books.set(i, new String[]{
                            id, tfTitle.getText(), tfAuthor.getText(),
                            tfPublisher.getText(), tfYear.getText(),
                            tfISBN.getText(), tfCopies.getText()
                    });
                    JOptionPane.showMessageDialog(this, "Book Updated");
                    clearFields();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Book Not Found");

        } else if (e.getSource() == btnDelete) {
            String id = JOptionPane.showInputDialog(this, "Enter Book ID to Delete:");
            books.removeIf(book -> book[0].equals(id));
            JOptionPane.showMessageDialog(this, "Book Deleted");

        } else if (e.getSource() == btnClear) {
            clearFields();

        } else if (e.getSource() == btnExit) {
            System.exit(0);
        }
    }

    private void showTable() {
        String[] cols = { "Book ID", "Title", "Author", "Publisher", "Year", "ISBN", "Copies" };
        String[][] data = books.toArray(new String[0][0]);

        JTable table = new JTable(data, cols);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.setAutoCreateRowSorter(true);
        table.setSelectionBackground(new Color(33, 150, 243));
        table.setSelectionForeground(Color.WHITE);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JFrame frame = new JFrame("View Books");
        frame.add(new JScrollPane(table));
        frame.setSize(1000, 500);
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }

    private void clearFields() {
        tfId.setText("");
        tfTitle.setText("");
        tfAuthor.setText("");
        tfPublisher.setText("");
        tfYear.setText("");
        tfISBN.setText("");
        tfCopies.setText("");
    }

    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(lib::new);
    }
}

