import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    public JFrame build() {
        
        // Build window
        JFrame window = buildWindow();

        // Main layout
        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.setBorder(new EmptyBorder(20,20,20,20));

        // Header container
        JPanel headerContainer = buildHeaderContainer("Based", "Convert any number format to a base");

        // Body container
        JPanel bodyContainer = new JPanel();
        bodyContainer.setLayout(new GridLayout(4, 2));
        
        // Components
        JLabel numLabel = new JLabel("Input: ");
        JLabel basLabel = new JLabel("Base: ");
        final JTextField num = new JTextField(); 
        JButton compute = new JButton("COMPUTE");

        // Drop down options
        Integer[] bases = {2, 8, 10, 16};
        JComboBox<Integer> baseBox = new JComboBox<>(bases);

        // Result box
        JTextArea result = new JTextArea(5, 10);

        // Top container
        JPanel gridContainer = new JPanel(new GridBagLayout());
        GridBagConstraints mainGrid = new GridBagConstraints();
        mainGrid.fill = GridBagConstraints.HORIZONTAL;
        mainGrid.weighty = 1;

        // Result container
        JPanel resContainer = new JPanel(new GridLayout());
        resContainer.add(result);

        // Add padding
        mainGrid.insets = new Insets(0, 5, 0, 5);

        // First column label
        mainGrid.gridx = 0;
        mainGrid.gridy = 0;
        mainGrid.weightx = 0.2; // Smaller for label
        gridContainer.add(numLabel, mainGrid);

        // Second column text
        mainGrid.gridx = 1;
        mainGrid.weightx = 0.8;
        gridContainer.add(num, mainGrid);

        // Third column label
        mainGrid.gridx = 2;
        mainGrid.weightx = 0.2; // Smaller for label
        gridContainer.add(basLabel, mainGrid);

        // Fourth column base
        mainGrid.gridx = 3;
        mainGrid.weightx = 0.8;
        gridContainer.add(baseBox, mainGrid);
    
        // Fonts
        numLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        num.setFont(new Font("Arial", Font.PLAIN, 14));
        basLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        baseBox.setFont(new Font("Arial", Font.PLAIN, 14));
        result.setFont(new Font("Arial", Font.PLAIN, 20));
        compute.setFont(new Font("Arial", Font.BOLD, 14));

        compute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int number = Integer.parseInt(num.getText().trim()); 
                    int base = (Integer) baseBox.getSelectedItem();

                    // Perform base conversion
                    Convert convert = new Convert();
                    String text = convert.convert(number, base);
                    result.setText(text);
                } catch (NumberFormatException ex) {
                    String error = "Invalid number input! Please enter a valid integer.";
                    result.setText(error);
                }
            }
        });

        bodyContainer.add(gridContainer);
        bodyContainer.add(resContainer);

        mainContainer.add(headerContainer, BorderLayout.NORTH);
        mainContainer.add(bodyContainer, BorderLayout.CENTER);
        mainContainer.add(compute, BorderLayout.SOUTH);
        
        window.add(mainContainer);
        window.pack();
        return window;
    }
    
    public JFrame buildWindow() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        return window;
    }

    public JPanel buildMainContainer() {
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BorderLayout());
        
        // Styling
        mainContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
        return mainContainer;
    }

    public JPanel buildHeaderContainer(String title, String description) {
        JPanel headerContainer = new JPanel();
        headerContainer.setLayout(new GridLayout());
        
        JLabel titleLabel = new JLabel(title);
        JLabel descriptionLabel = new JLabel(description);

        // Styling
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        headerContainer.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));

        headerContainer.add(titleLabel);
        headerContainer.add(descriptionLabel);

        return headerContainer;
    }

    
    
}
