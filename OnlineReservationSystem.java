package intership;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class OnlineReservationSystem {

   
    static HashMap<String, String> users = new HashMap<>();  
    static HashMap<String, Ticket> tickets = new HashMap<>();  

    public static void main(String[] args) {
        users.put("abhi", "lanke");
        new LoginForm();
    }

    static class LoginForm extends JFrame {
        JTextField usernameField;
        JPasswordField passwordField;

        LoginForm() {
            setTitle("Login Form");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(3, 2));

            JLabel usernameLabel = new JLabel("Username:");
            usernameField = new JTextField();

            JLabel passwordLabel = new JLabel("Password:");
            passwordField = new JPasswordField();

            JButton loginButton = new JButton("Login");
            loginButton.addActionListener(e -> validateLogin());

            add(usernameLabel);
            add(usernameField);
            add(passwordLabel);
            add(passwordField);
            add(new JLabel());
            add(loginButton);

            setVisible(true);
        }

        void validateLogin() {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (users.containsKey(username) && users.get(username).equals(password)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                dispose();
                new MainSystem();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials!");
            }
        }
    }

    static class MainSystem extends JFrame {
        MainSystem() {
            setTitle("Online Reservation System");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(3, 1));

            JButton reservationButton = new JButton("Reservation System");
            reservationButton.addActionListener(e -> new ReservationForm());

            JButton cancellationButton = new JButton("Cancellation System");
            cancellationButton.addActionListener(e -> new CancellationForm());

            add(reservationButton);
            add(cancellationButton);

            setVisible(true);
        }
    }

    static class ReservationForm extends JFrame {
        JTextField nameField, trainNumberField, classTypeField, fromField, toField, dateField;

        ReservationForm() {
            setTitle("Reservation Form");
            setSize(400, 400);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new GridLayout(7, 2));

            JLabel nameLabel = new JLabel("Passenger Name:");
            nameField = new JTextField();

            JLabel trainNumberLabel = new JLabel("Train Number:");
            trainNumberField = new JTextField();

            JLabel classTypeLabel = new JLabel("Class Type:");
            classTypeField = new JTextField();

            JLabel fromLabel = new JLabel("From:");
            fromField = new JTextField();

            JLabel toLabel = new JLabel("To:");
            toField = new JTextField();

            JLabel dateLabel = new JLabel("Date of Journey:");
            dateField = new JTextField();

            JButton insertButton = new JButton("Reserve");
            insertButton.addActionListener(e -> reserveTicket());

            add(nameLabel); add(nameField);
            add(trainNumberLabel); add(trainNumberField);
            add(classTypeLabel); add(classTypeField);
            add(fromLabel); add(fromField);
            add(toLabel); add(toField);
            add(dateLabel); add(dateField);
            add(new JLabel()); add(insertButton);

            setVisible(true);
        }

        void reserveTicket() {
            String name = nameField.getText();
            String trainNumber = trainNumberField.getText();
            String classType = classTypeField.getText();
            String from = fromField.getText();
            String to = toField.getText();
            String date = dateField.getText();

            if (name.isEmpty() || trainNumber.isEmpty() || classType.isEmpty() || from.isEmpty() || to.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            String pnr = UUID.randomUUID().toString().substring(0, 8);
            Ticket ticket = new Ticket(pnr, name, trainNumber, classType, from, to, date);
            tickets.put(pnr, ticket);

            JOptionPane.showMessageDialog(this, "Ticket Reserved!\nPNR: " + pnr);
            dispose();
        }
    }

    static class CancellationForm extends JFrame {
        JTextField pnrField;

        CancellationForm() {
            setTitle("Cancellation Form");
            setSize(300, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new GridLayout(3, 2));

            JLabel pnrLabel = new JLabel("Enter PNR:");
            pnrField = new JTextField();

            JButton cancelButton = new JButton("Cancel Ticket");
            cancelButton.addActionListener(e -> cancelTicket());

            add(pnrLabel);
            add(pnrField);
            add(new JLabel());
            add(cancelButton);

            setVisible(true);
        }

        void cancelTicket() {
            String pnr = pnrField.getText();

            if (tickets.containsKey(pnr)) {
                tickets.remove(pnr);
                JOptionPane.showMessageDialog(this, "Ticket Cancelled Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid PNR!");
            }
        }
    }

    static class Ticket {
        String pnr, name, trainNumber, classType, from, to, date;

        Ticket(String pnr, String name, String trainNumber, String classType, String from, String to, String date) {
            this.pnr = pnr;
            this.name = name;
            this.trainNumber = trainNumber;
            this.classType = classType;
            this.from = from;
            this.to = to;
            this.date = date;
        }
    }
}
