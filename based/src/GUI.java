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
        JPanel mainContainer = new JPanel(new BorderLayout(10, 10)); 
        mainContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainContainer.setOpaque(true);
        mainContainer.setBackground(Color.WHITE);

        // Header container
        JPanel headerContainer = buildHeaderContainer();

        // Body container
        JPanel bodyContainer = new JPanel(new GridBagLayout());
        bodyContainer.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Components
        JLabel numLabel = new JLabel("Input: ");
        JLabel basLabel = new JLabel("Base: ");
        JTextField num = new JTextField(); 
        JButton compute = new JButton("COMPUTE");

        // Drop down options
        Integer[] bases = {2, 4, 6, 8, 10, 12, 14, 16, 32, 64};
        JComboBox<Integer> baseBox = new JComboBox<>(bases);

        // Result box
        JTextArea result = new JTextArea(5, 20);
        result.setLineWrap(true);
        result.setWrapStyleWord(true);
        result.setEditable(false);
        result.setText("Let's base some numbers...");
        result.setForeground(Color.darkGray);
        
        // Result container with scroll
        JScrollPane resContainer = new JScrollPane(result);
        resContainer.setPreferredSize(new Dimension(300, 150)); 

        // Layout adjustments
        gbc.weightx = 0.3;
        gbc.gridx = 0;
        gbc.gridy = 0;
        bodyContainer.add(numLabel, gbc);

        gbc.weightx = 0.7;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        bodyContainer.add(num, gbc);

        gbc.weightx = 0.3;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        bodyContainer.add(basLabel, gbc);

        gbc.weightx = 0.7;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        bodyContainer.add(baseBox, gbc);

        // Reset width and add result box
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        bodyContainer.add(resContainer, gbc);

        // Font Styling
        Font defaultFont = new Font("Arial", Font.PLAIN, 18);
        numLabel.setFont(defaultFont);
        num.setFont(defaultFont);
        basLabel.setFont(defaultFont);
        baseBox.setFont(defaultFont);
        result.setFont(defaultFont);
        compute.setFont(defaultFont);

        // Button styling
        compute.setOpaque(true);
        compute.setContentAreaFilled(true);
        compute.setFocusPainted(false);
        compute.setBackground(Color.BLACK); 
        compute.setForeground(Color.WHITE); 
        compute.setFont(new Font("Arial", Font.BOLD, 16));
        compute.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); 
        compute.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        compute.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                compute.setBackground(new Color(53, 53, 53)); 
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                compute.setBackground(Color.BLACK); 
            }
        });


        // Action listener
        compute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int number = Integer.parseInt(num.getText().trim());
                    int base = (Integer) baseBox.getSelectedItem();

                    // Perform base conversion
                    Convert convert = new Convert();
                    String[] ans = convert.convert(number, base);

                    // Append results properly
                    result.setForeground(Color.BLACK);
                    result.setText(ans[0] + "\n\nSteps:\n");
                    
                    int index = 1; 
                    for (int i = 1; i < ans.length; i++) {
                        result.append(index + ": " + ans[i] + "\n");
                        index++;
                    }
                } catch (NumberFormatException ex) {
                    result.setForeground(Color.RED);
                    result.setText("Please enter a valid integer");
                }
            }
        });

        // Add to main container
        mainContainer.add(headerContainer, BorderLayout.NORTH);
        mainContainer.add(bodyContainer, BorderLayout.CENTER);
        mainContainer.add(compute, BorderLayout.SOUTH);

        window.add(mainContainer);
        window.pack();
        return window;
    }

    public JFrame buildWindow() {
        JFrame window = new JFrame("Base Converter");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        return window;
    }

    public JPanel buildHeaderContainer() {
        JPanel headerContainer = new JPanel(new GridLayout());
        headerContainer.setOpaque(false);
        headerContainer.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));

        JLabel titleLabel = new JLabel("BASED");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel descriptionLabel = new JLabel("<HTML>Base your numbers!</HTML>");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        
        headerContainer.add(titleLabel);
        headerContainer.add(descriptionLabel);

        return headerContainer;
    }
}
