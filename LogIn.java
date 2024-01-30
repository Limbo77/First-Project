import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class LogIn {

//Instance Variables


    private JFrame logInFrame;
    private JPanel logInPanel;
    private JPanel welcomePanel;
    private JButton closeButton;
    private JButton enterButton;
    private JLabel Welcome;
    private JTextField usernameField;
    private JTextField passwordField;
    private static String usernameStorage;
    private String passwordStorage;
    private static HashMap <String, String> Data = new HashMap<>();
    private static HashMap <String, Double> balanceData = new HashMap<>();
    public LogIn() {


//Frame Settings

        logInFrame = new JFrame();
        logInFrame.setLayout(new GridLayout(2  ,1));
        logInFrame.setResizable(false);
        logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logInFrame.setTitle("Our Bank");
        logInFrame.pack();
        logInFrame.setVisible(true);
        logInFrame.setSize(700, 700);
        logInFrame.setLocationRelativeTo(null);


// Panels, Buttons, etc.

        logInPanel = new JPanel();
        welcomePanel = new JPanel();
        Welcome = new JLabel("Please Log In With An Existing Account");
        welcomePanel.add(Welcome);
        logInFrame.add(welcomePanel, BorderLayout.NORTH);
        usernameField = new JTextField("Username", 20);
        passwordField = new JTextField("Password", 20);
        usernameField.setPreferredSize(new Dimension(20, 20));
        passwordField.setPreferredSize(new Dimension(20, 20));
        logInPanel.add(usernameField);
        logInPanel.add(passwordField);
        enterButton = new JButton("Press Enter");
        enterButton.setEnabled(false);
        closeButton = new JButton("Close");
        logInPanel.add(enterButton);
        logInPanel.add(closeButton);
        logInFrame.add(logInPanel);


// Action And Focus Listeners Below


        closeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new BGUI();
                logInFrame.dispose();
            }
        });


        usernameField.addFocusListener(new FocusListener() {

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
                getDataFromDataFile(Data);
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
                    passwordStorage = Register.Data.get(usernameField.getText());
                            for(String key : Register.Data.keySet()){
                                if(Register.Data.get(key).equals(passwordStorage)){
                                    usernameStorage = key;
                                    break;
                                }
                }
                passwordField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(Data.containsKey(usernameField.getText()) == true && Data.get(usernameField.getText()).equals(passwordField.getText())){
                            getDataFromBalanceFile(balanceData);
                            usernameStorage = usernameField.getText();
                            new Account();
                            logInFrame.dispose();
                        }
                        else{
                            System.out.println("Wrong Username Or Password");
                            
                        }
                        
                    }
                    
                });
            }
                @Override
                public void focusLost(FocusEvent e) {
                }
            });
            try (Scanner scanner = new Scanner("balanceName.txt")) {
                while (scanner.hasNextLine()) {
                    String txtLine = scanner.nextLine();
                    if(txtLine.equals(usernameField.getText())) {
                    }
                }
            }
           

    }
// Methods

    public static  Map<String, String> getDataFromDataFile(Map <String, String> Data){
        
        BufferedReader bf = null;
        try{
            File dataFile = new File("./Data");
            bf = new BufferedReader(new FileReader(dataFile));
            String line = null;
            while( (line = bf.readLine())!=null){
                String[] parts = line.split(":");
                String username = parts[0].trim();
                String password = parts[1].trim();
                if(!username.equals("") && !password.equals("")){
                    Data.put(username, password);
                }
            }
        }catch(IOException e1){
            
        }finally{
        if(bf != null){
            try{
                bf.close();
            }catch(IOException e1){
            }
        }
        }
        return Data;
    }



    public static void getDataFromBalanceFile(HashMap <String, Double> balanceData){
        Scanner stock = new Scanner("balanceData.txt");
        try {
            stock.useDelimiter("\\s+");
        
        while (stock.hasNext()) {
        String tempString = stock.nextLine();
        double tempdouble = stock.nextDouble();
        balanceData.put(tempString,tempdouble);
        stock.close();
            
        }
     } catch (Exception e) {
        }
}

    public static HashMap<String, Double> getBalanceData(){
        return balanceData;
    }
    public static String getUsername(){
        return usernameStorage;
    }
    


}