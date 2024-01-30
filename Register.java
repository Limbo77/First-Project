import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


public class Register {


//Instance Variables


    private JFrame registerFrame;
    private JPanel registerPanel;
    private JPanel welcomePanel;
    private JLabel Welcome;
    private JButton closeButton;
    private JButton enterButton;
    private JTextField usernameField;
    private JTextField passwordField;
    private String usernameStorage;
    private String passwordStorage;
    public static Map <String, String> Data = new HashMap<>();
    private static final double STARTING_BALANCE = 0;

    public Register(){

//Frame Settings


        registerFrame = new JFrame();
        registerFrame.setLayout(new GridLayout(2  ,1));
        registerFrame.setResizable(false);
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setTitle("Our Bank");
        registerFrame.pack();
        registerFrame.setVisible(true);
        registerFrame.setSize(700, 700);
        registerFrame.setLocationRelativeTo(null);


// Panel, Buttons, etc.


        welcomePanel = new JPanel();
        registerPanel = new JPanel();
        welcomePanel = new JPanel();
        Welcome = new JLabel("Please Register A New Account.");
        welcomePanel.add(Welcome);
        registerFrame.add(welcomePanel, BorderLayout.NORTH);
        usernameField = new JTextField("Username", 20);
        passwordField = new JTextField("Password", 20);
        usernameField.setPreferredSize(new Dimension(20, 20));
        passwordField.setPreferredSize(new Dimension(20, 20));
        registerPanel.add(usernameField);
        registerPanel.add(passwordField);
        enterButton = new JButton("Press Enter");
        enterButton.setEnabled(false);
        closeButton = new JButton("Close");
        registerPanel.add(enterButton);
        registerPanel.add(closeButton);
        registerFrame.add(registerPanel);
        LogIn.getDataFromDataFile(Data);
            


// Action And Focus Listeners Below


        closeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new BGUI();
                registerFrame.dispose();
            }
        });

        
        usernameField.addFocusListener((FocusListener) new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if(usernameField.getText().equals("Username")){
             usernameField.setText("");
            }
        }

            @Override
            public void focusLost(FocusEvent e) {
                if(usernameField.getText().equals("")){
                    usernameField.setText("Username");
                }
            }
            
        });


        passwordField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if(passwordField.getText().equals("Password")){
             passwordField.setText("");
            }
        }

            @Override
            public void focusLost(FocusEvent e) {
                if(passwordField.getText().equals("")){
                    passwordField.setText("Password");
                }
            }
            
        });


        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }
            @Override
            public void focusLost(FocusEvent e) {
                usernameStorage = usernameField.getText();
                passwordField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(usernameField.getText().length() < 6){
                            System.out.println("Username Should Be At Least 6 Letters Long.");
                        }
                        else{
                            if(passwordField.getText().length() < 8){
                                System.out.println("Password Should Be At Least 8 Letters Long.");
                            }else{
                                passwordStorage = passwordField.getText();
                            if(Data.containsKey(usernameStorage)){
                            System.out.println("This Account Already Exists. Please Log In");
                            registerFrame.dispose();
                            new LogIn();

                        }else{

                            //Filling "Data.txt" and "balanceData.txt" File

                        Data.put(usernameStorage, passwordStorage);
                        File balanceDataFile = new File("./balanceData");
                        File dataFile = new File("./Data");
                        try (Scanner scanner = new Scanner("Data.txt")) {
                            while (scanner.hasNextLine()) {
                                String txtLine = scanner.nextLine();
                                if(txtLine.equals(usernameStorage + ":" + passwordStorage)){

                                }
                                else{
                                    BufferedWriter bf = null;
                                    BufferedWriter bw = null;
                            try{
                                bf = new BufferedWriter(new FileWriter(dataFile, true));
                                bw = new BufferedWriter(new FileWriter(balanceDataFile, true));
                                bw.write(usernameStorage + ":" + STARTING_BALANCE);
                                bf.write(usernameStorage + ":" + passwordStorage);
                                bw.newLine();
                                bw.flush();
                                bf.newLine();
                                bf.flush();
                            }catch(IOException e1){
                                e1.printStackTrace();
                            }try{
                                bw.close();
                                bf.close();
                            }catch(IOException e1){
                            }
                                }
                            }
                        }
                    registerFrame.dispose();
                    new LogIn();
                    }
                }};
            }});
     } });
                        }
                        

/* 
    public static void txtFileFillerMap(HashMap <String, String> Data) {
        File dataFile = new File("./Data");
        BufferedWriter bf = null;
        try{
            bf = new BufferedWriter(new FileWriter(dataFile, true));
            for(Entry<String, String> entry : Data.entrySet()){
                bf.write(entry.getKey() + ":" + entry.getValue());
                bf.newLine();
            }
            bf.flush();
        }catch(IOException e1){
            e1.printStackTrace();
        }try{
            bf.close();
        }catch(IOException e1){
        }
    }
    */

}