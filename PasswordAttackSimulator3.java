import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PasswordAttackSimulator3 extends JFrame {
    private static final String CORRECT_PASSWORD = "aabbc"; // Hardcoded password
    private static final String DICTIONARY_FILE = "dictionary.txt"; // Dictionary file path

    public PasswordAttackSimulator3() {
        initComponents();
    }

    private void initComponents() {
        // Main frame setup
        setTitle("Password Attack Simulator");
        setSize(800, 500);
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel setup
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Right Panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(0, 123, 255));
        rightPanel.setPreferredSize(new Dimension(400, 500));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Logo Label with enhanced size
        ImageIcon logoIcon = new ImageIcon("logo.jpg"); // Update with your logo path
        Image logoImage = logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Resize logo
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel titleLabel = new JLabel("Password Attack Simulator");
        titleLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel footerLabel = new JLabel("© security tester");
        footerLabel.setForeground(Color.LIGHT_GRAY);
        footerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(logoLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(titleLabel);
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(footerLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new GridBagLayout());

        // Username Label and Text Field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 5, 10);
        leftPanel.add(userLabel, gbc);

        JTextField userText = new JTextField(20);
        userText.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        leftPanel.add(userText, gbc);

        // Result Area
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setBackground(new Color(240, 240, 240));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(550, 150));
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 2;
        leftPanel.add(scrollPane, gbc);

        // Attack Button
        JButton attackButton = new JButton("Start Attack");
        styleButton(attackButton);
        gbc.gridy = 2;
        leftPanel.add(attackButton, gbc);

        // Action Listener for the attack button
        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                resultArea.setText("Username: " + username + "\nStarting dictionary attack...\n");
                
                if (dictionaryAttack(resultArea)) {
                    resultArea.append("Success: Password found through dictionary attack.\n");
                } else {
                    resultArea.append("Dictionary attack failed. Starting brute force attack...\n");
                    if (bruteForceAttack(resultArea)) {
                        resultArea.append("Success: Password found through brute force.\n");
                    } else {
                        resultArea.append("Failure: Password not found.\n");
                    }
                }
            }
        });

        // Adding panels to main panel
        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        add(mainPanel);
        pack();
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(0, 123, 255));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 50)); // Set a uniform size for buttons
    }

    private boolean dictionaryAttack(JTextArea resultArea) {
        try (BufferedReader br = new BufferedReader(new FileReader(DICTIONARY_FILE))) {
            String password;
            while ((password = br.readLine()) != null) {
                if (password.equals(CORRECT_PASSWORD)) {
                    return true;
                }
            }
        } catch (IOException e) {
            resultArea.append("Error reading dictionary file.\n");
        }
        return false;
    }

    private boolean bruteForceAttack(JTextArea resultArea) {
        char[] charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789*/-+()@!#$%^&_-~`".toCharArray();
        return bruteForce("", charset, CORRECT_PASSWORD.length(), resultArea);
    }

    private boolean bruteForce(String prefix, char[] charset, int length, JTextArea resultArea) {
        if (length == 0) {
            return prefix.equals(CORRECT_PASSWORD);
        }
        for (char c : charset) {
            if (bruteForce(prefix + c, charset, length - 1, resultArea)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordAttackSimulator3().setVisible(true));
    }
}