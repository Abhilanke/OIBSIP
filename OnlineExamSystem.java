package intership;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

public class OnlineExamSystem {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Session and user data
    private String currentUser = null;
    private String currentPassword = "12345"; // Default password for demo
    private HashMap<Integer, String> userAnswers = new HashMap<>(); // Store answers
    private int timer = 60; // Timer in seconds
    private Timer countdownTimer;

    public OnlineExamSystem() {
        frame = new JFrame("Online Examination System");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Adding pages
        mainPanel.add(loginPage(), "Login");
        mainPanel.add(profilePage(), "Profile");
        mainPanel.add(examPage(), "Exam");

        frame.add(mainPanel);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        cardLayout.show(mainPanel, "Login");
    }

    // Login Page
    private JPanel loginPage() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            if (username.equals("admin") && password.equals(currentPassword)) {
                currentUser = username;
                JOptionPane.showMessageDialog(frame, "Login Successful!");
                cardLayout.show(mainPanel, "Profile");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Credentials!");
            }
        });

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(loginButton);
        return panel;
    }

    // Profile Page
    private JPanel profilePage() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel profileLabel = new JLabel("Welcome, Admin");
        JButton changePasswordButton = new JButton("Change Password");
        JButton startExamButton = new JButton("Start Exam");
        JButton logoutButton = new JButton("Logout");

        profileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(profileLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.add(changePasswordButton);
        buttonPanel.add(startExamButton);
        buttonPanel.add(logoutButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        changePasswordButton.addActionListener(e -> {
            String newPassword = JOptionPane.showInputDialog("Enter New Password:");
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                currentPassword = newPassword;
                JOptionPane.showMessageDialog(frame, "Password Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Password cannot be empty!");
            }
        });

        startExamButton.addActionListener(e -> cardLayout.show(mainPanel, "Exam"));

        logoutButton.addActionListener(e -> {
            currentUser = null;
            userAnswers.clear();
            cardLayout.show(mainPanel, "Login");
        });

        return panel;
    }

    // Exam Page
    private JPanel examPage() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel questionLabel = new JLabel("Q1: What is Java?");
        ButtonGroup optionsGroup = new ButtonGroup();
        JRadioButton option1 = new JRadioButton("A Programming Language");
        JRadioButton option2 = new JRadioButton("A Coffee Brand");
        JRadioButton option3 = new JRadioButton("An Operating System");
        JRadioButton option4 = new JRadioButton("None of the Above");

        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        JButton submitButton = new JButton("Submit");
        JLabel timerLabel = new JLabel("Time Remaining: " + timer + "s");

        panel.add(questionLabel, BorderLayout.NORTH);
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);

        panel.add(optionsPanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.add(timerLabel);
        bottomPanel.add(submitButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

       

        // Submit button functionality
        submitButton.addActionListener(e -> {
            String selectedOption = null;
            if (option1.isSelected()) selectedOption = option1.getText();
            if (option2.isSelected()) selectedOption = option2.getText();
            if (option3.isSelected()) selectedOption = option3.getText();
            if (option4.isSelected()) selectedOption = option4.getText();

            if (selectedOption != null) {
                userAnswers.put(1, selectedOption);
                //countdownTimer.stop();
                JOptionPane.showMessageDialog(frame, "Exam Submitted Successfully!");
                cardLayout.show(mainPanel, "Profile");
            } else {
                JOptionPane.showMessageDialog(frame, "Please select an answer before submitting!");
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OnlineExamSystem::new);
    }
}
