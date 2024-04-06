package edu.ucalgary.oop;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReliefServiceGUI extends JFrame {

    private JTextArea logTextArea;
    private JTextField searchField;
    private JButton searchButton;
    JComboBox<String> columnSelector;
    private JTextField searchLogField;
    private JButton searchLogButton;

    private Connection dbConnection;
    private ResultSet resultSet;
    private boolean isAuthenticated = false;
    private boolean isCentralReliefWorker = false;


    public ReliefServiceGUI() {
        setTitle("GUI");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel();
        searchField = new JTextField(10);
        searchButton = new JButton("Search Inquirer");
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isAuthenticated) {
                    String searchQuery = searchField.getText();
                    if (searchQuery != null && !searchQuery.isEmpty()) {
                        searchInquirer(searchQuery);
                    }
                }
            }
        });

        searchLogField = new JTextField(10);
        searchPanel.add(searchLogField);

        searchLogButton = new JButton("Search Log");
        searchLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isAuthenticated) {
                    String logQuery = searchLogField.getText();
                    if (logQuery != null && !logQuery.isEmpty()) {
                        searchLog(logQuery);
                    }
                }
            }
        });
        searchPanel.add(searchLogButton);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JPanel northPanel = new JPanel();
        northPanel.add(searchButton);
        northPanel.add(searchField);
        northPanel.add(searchLogButton);
        northPanel.add(searchLogField);
        add(northPanel, BorderLayout.NORTH);

        JLabel searchLabel = new JLabel("Old Value:");
        JTextField searchField = new JTextField(10);
        JLabel newValueLabel = new JLabel("New Value:");
        JTextField newValueField = new JTextField(10);
        String[] options = {"Inquirer phonenumber", "Inquirer firstname", "Inquirer lastname", 
        "InquiryLog calldate", "InquiryLog details", "InquiryLog inquirer"};
        JComboBox<String> optionSelector = new JComboBox<>(options);
        JButton updateButton = new JButton("Update");
        
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchQuery = searchField.getText();
                String newValue = newValueField.getText();
                String selectedOption = (String)optionSelector.getSelectedItem();
                String[] parts = selectedOption.split(" ");
                String tableName = parts[0];
                String columnName = parts[1];
                if (tableName.equals("Inquirer")) {
                    updateInquirer(searchQuery, newValue, columnName);
                } else if (tableName.equals("InquiryLog")) {
                    updateLog(searchQuery, newValue, columnName);
                }
            }
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(newValueLabel);
        searchPanel.add(newValueField);
        searchPanel.add(optionSelector);
        searchPanel.add(updateButton);
        add(searchPanel, BorderLayout.SOUTH);

        searchPanel.revalidate();
        searchPanel.repaint();

        // Create a panel for the left side
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JButton databaseButton = new JButton("Database");
        databaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPanel.setVisible(true);
                searchField.setVisible(true);
                searchButton.setVisible(true);
                searchLogField.setVisible(true);
                searchLogButton.setVisible(true);
                // Reset the GUI to its initial state
                logTextArea.setText("");
                searchField.setText("");
                searchLogField.setText("");
                optionSelector.setSelectedIndex(0);
                
                // Fetch all inquirers and all inquiry logs
                List<String> allInquirers = ReliefServiceGUI.this.fetchAllInquirers();
                List<String> allInquiryLogs = ReliefServiceGUI.this.fetchAllInquiryLogs();
                
                // Display all inquirers and all inquiry logs in the logTextArea
                logTextArea.append("Inquirers:\n\n");
                for (String inquirer : allInquirers) {
                    logTextArea.append(inquirer + "\n");
                }
                logTextArea.append("\nInquiry Logs:\n\n");
                for (String log : allInquiryLogs) {
                    logTextArea.append(log + "\n");
                }
            }
        });

        leftPanel.add(databaseButton);
        // Add the left panel to the west region of the main frame
        add(leftPanel, BorderLayout.WEST);

        searchPanel.setVisible(false);
        searchField.setVisible(false);
        searchButton.setVisible(false);
        searchLogField.setVisible(false);
        searchLogButton.setVisible(false);

    }

    public void createConnection() {
        try {
            this.dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost/project", "oop", "ucalgary");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void authenticate(String username, String password) {
        // Set isAuthenticated and isCentralReliefWorker accordingly
        isAuthenticated = true;
        isCentralReliefWorker = true;
    }

    public void logInquiry(String inquirer, String contactDetails, String queryDetails) {
        if (isAuthenticated) {
            // Perform logging logic here
            // Include inquirer's name, contact details, query details, and timestamps

        }
    }


    public List<String> fetchAllInquirers() {
        List<String> inquirers = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = dbConnection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Inquirer");
            while (rs.next()) {
                String inquirer = "ID: " + rs.getString("id") + "\n" +
                    "First Name: " + rs.getString("firstname") + "\n" +
                    "Last Name: " + rs.getString("lastname") + "\n" +
                    "Phone Number: " + rs.getString("phonenumber") + "\n";
                inquirers.add(inquirer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return inquirers;
    }

    public List<String> fetchAllInquiryLogs() {
        List<String> logs = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = dbConnection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Inquiry_Log");
            while (rs.next()) {
                String log = "Log ID: " + rs.getString("id") + "\n" +
                    "Inquirer: " + rs.getString("inquirer") + "\n" +
                    "Inquiry Date: " + rs.getString("calldate") + "\n" +
                    "Inquiry Details: " + rs.getString("details") + "\n";
                logs.add(log);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return logs;
    }


    public void searchInquirer(String query) {
        if (isAuthenticated) {
            try {
                String SQLquery = "";
                Statement statement = dbConnection.createStatement();

                SQLquery = "SELECT * FROM Inquirer WHERE LOWER(firstname) LIKE '%" + query.toLowerCase() + "%' OR LOWER(lastname) LIKE '%" + query.toLowerCase() + "%'";
                resultSet = statement.executeQuery(SQLquery);
    
                StringBuilder resultBuilder = new StringBuilder();
                while (resultSet.next()) {
                    String inquirer = "ID: " + resultSet.getString("id") + "\n" +
                    "First Name: " + resultSet.getString("firstname") + "\n" +
                    "Last Name: " + resultSet.getString("lastname") + "\n" +
                    "Phone Number: " + resultSet.getString("phonenumber");
                    resultBuilder.append(inquirer).append("\n");
                }
    
                logTextArea.setText(resultBuilder.toString());
    
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void searchLog(String query){
        if (isAuthenticated) {
            try {
                String SQLquery = "";
                Statement statement = dbConnection.createStatement();

                SQLquery = "SELECT * FROM Inquiry_Log WHERE LOWER(details) LIKE '%" + query.toLowerCase() + "%'";
                resultSet = statement.executeQuery(SQLquery);
    
                StringBuilder resultBuilder = new StringBuilder();
                while (resultSet.next()) {
                    String log = "Inquirer: " + resultSet.getString("inquirer") + "\n" +
                    "Log ID: " + resultSet.getString("id") + "\n" +
                    "Query Details: " + resultSet.getString("details") + "\n" +
                    "Inquiry Date: " + resultSet.getString("calldate");
                    resultBuilder.append(log).append("\n");
                }
    
                logTextArea.setText(resultBuilder.toString());
    
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    public void updateInquirer(String searchQuery, String newValue, String selectedOption) {
        if (isAuthenticated) {
            String sqlQuery;
            switch (selectedOption) {
                case "phonenumber":
                    sqlQuery = "UPDATE Inquirer SET phonenumber = ? WHERE LOWER(phonenumber) = LOWER(?)";
                    break;
                case "firstname":
                    sqlQuery = "UPDATE Inquirer SET firstname = ? WHERE LOWER(firstname) = LOWER(?)";
                    break;
                case "lastname":
                    sqlQuery = "UPDATE Inquirer SET lastname = ? WHERE LOWER(lastname) = LOWER(?)";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid update option");
            }
            try {
                System.out.println("Executing SQL query: " + sqlQuery);
                PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlQuery);
                preparedStatement.setString(1, newValue);
                preparedStatement.setString(2, searchQuery.toLowerCase());
            
                // Print out the searchQuery and newValue
                System.out.println("searchQuery: " + searchQuery);
                System.out.println("newValue: " + newValue);
            
                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Update successful");
                } else {
                    System.out.println("No rows were updated. Check your searchQuery and newValue.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void updateLog(String searchQuery, String newValue, String selectedOption) {
        if (isAuthenticated) {
            String sqlQuery;
            switch (selectedOption) {
                case "details":
                    sqlQuery = "UPDATE Inquiry_Log SET details = ? WHERE LOWER(details) = LOWER(?)";
                    break;
                case "inquirer":
                    sqlQuery = "UPDATE Inquiry_Log SET inquirer = ? WHERE inquirer = ?";
                    break;
                case "calldate":
                    sqlQuery = "UPDATE Inquiry_Log SET calldate = TO_DATE(?, 'YYYY-MM-DD') WHERE calldate = TO_DATE(?, 'YYYY-MM-DD')";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid update option");
            }
            try {
                System.out.println("Executing SQL query: " + sqlQuery);
                PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlQuery);
                if (selectedOption.equals("inquirer")){
                    preparedStatement.setInt(1, Integer.parseInt(newValue));
                    preparedStatement.setInt(2, Integer.parseInt(searchQuery));
                }
                else if (selectedOption.equals("calldate")){
                    preparedStatement.setString(1, newValue);
                    preparedStatement.setString(2, searchQuery);
                }else {
                    preparedStatement.setString(1, newValue);
                    preparedStatement.setString(2, searchQuery.toLowerCase());
                }
            
                // Print out the searchQuery and newValue
                System.out.println("searchQuery: " + searchQuery);
                System.out.println("newValue: " + newValue);
            
                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Update successful");
                } else {
                    System.out.println("No rows were updated. Check your searchQuery and newValue.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void close() {
        try {
            resultSet.close();
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        ReliefServiceGUI myGUI = new ReliefServiceGUI();
    
        myGUI.setVisible(true);
    
        myGUI.createConnection();
    
        // Perform authentication here
        myGUI.authenticate("username", "password");
    
        if (myGUI.isAuthenticated) {
            // user input for inquirer, contact details, and query details
            myGUI.logInquiry("inquirer", "contactDetails", "details");
    
            String searchQuery = myGUI.searchField.getText();
            myGUI.searchInquirer(searchQuery);

            String logQuery = myGUI.searchLogField.getText();
            myGUI.searchLog(logQuery);

        }

    }
}
