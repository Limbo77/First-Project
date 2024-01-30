import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Account {


//Instance Variables


    private JFrame accountFrame;
    private JPanel accountPanel;
    private JPanel welcomePanel;
    private JPanel wrapperPanel;
    private JPanel depositPanel;
    private JPanel withdrawPanel;
    private JLabel Welcome;
    private JButton balanceButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton closeButton;
    private JButton enterWithdraw;
    private JButton enterDeposit;
    private JTextField depositField;
    private JTextField withdrawField;
    private String usernameStorage;
    private static double accountBalance;
    private static Map <String, Double> balanceData= new HashMap <>();

    Account(){


//Filling balanceData From File

fillingBalanceData(balanceData);
//Frame Settings


            accountFrame = new JFrame();
            accountFrame.setLayout(new GridLayout(2  ,1));
            accountFrame.setResizable(false);
            accountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            accountFrame.setTitle("Our Bank");
            accountFrame.pack();
            accountFrame.setVisible(true);
            accountFrame.setSize(700, 700);
            accountFrame.setLocationRelativeTo(null);

            //Panels, Buttons, etc.


                welcomePanel = new JPanel();
                accountPanel = new JPanel();
                welcomePanel = new JPanel();
                wrapperPanel = new JPanel();
                Welcome = new JLabel("Welcome To Your Account! How May We Help You Today?");
                welcomePanel.add(Welcome);
                accountFrame.add(welcomePanel, BorderLayout.PAGE_START);
                balanceButton = new JButton("My Balance");
                depositButton = new JButton("Deposit");
                withdrawButton = new JButton("Withdraw");
                closeButton = new JButton("Exit Account");
                accountPanel.add(balanceButton);
                accountPanel.add(Box.createVerticalStrut(20));
                accountPanel.add(depositButton);
                accountPanel.add(Box.createVerticalStrut(20));
                accountPanel.add(withdrawButton);
                accountPanel.add(Box.createVerticalStrut(20));
                accountPanel.add(closeButton);
                accountPanel.setLayout(new BoxLayout(accountPanel, BoxLayout.Y_AXIS));
                wrapperPanel.setLayout(new GridBagLayout());
                wrapperPanel.add(accountPanel);
                accountFrame.add(wrapperPanel, BorderLayout.CENTER);
                balanceData.putAll(LogIn.getBalanceData());
                usernameStorage = LogIn.getUsername();
                accountBalance = balanceData.get(usernameStorage);


// Action And Focus Listeners Below


                withdrawButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        withdrawField = new JTextField("Enter Amount Of Money", 25);
                        enterWithdraw = new JButton("Enter");
                        accountPanel.setVisible(false);
                        welcomePanel.setVisible(false);
                        withdrawPanel = new JPanel();
                        withdrawPanel.add(withdrawField);
                        withdrawPanel.add(enterWithdraw);
                        accountFrame.add(withdrawPanel, BorderLayout.CENTER);


                        withdrawField.addFocusListener(new FocusListener() {


                            @Override
                            public void focusGained(FocusEvent e) {
                                if(withdrawField.getText().equals("Enter Amount Of Money")){
                                    withdrawField.setText("");
                                }
                            }

                            @Override
                            public void focusLost(FocusEvent e) {
                                if(withdrawField.getText().equals("")){
                                    withdrawField.setText("Enter Amount Of Money");
                                }
                            }

                        });


                        enterWithdraw.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                for(char i : withdrawField.getText().toCharArray()){
                                    if(i<'0' || i>'9'){
                                        System.out.println("Wrong Input. Please Enter Numbers.");
                                        break;
                                    }
                                    else{
                                        accountBalance -= Double.parseDouble(withdrawField.getText());
                                        balanceData.put(usernameStorage, accountBalance);

                                        System.out.println("You Have Successfully Withdrawed Money");
                                        withdrawField.setText("Enter Amount Of Money");
                                        welcomePanel.setVisible(true);
                                        accountPanel.setVisible(true);
                                        accountFrame.remove(withdrawPanel);
                                        break;
                                    }
                                }
                            }

                        });
                    }

                });


                depositButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        depositField = new JTextField("Enter Amount Of Money", 25);
                        enterDeposit = new JButton("Enter");
                        accountPanel.setVisible(false);
                        welcomePanel.setVisible(false);
                        depositPanel = new JPanel();
                        depositPanel.add(depositField);
                        depositPanel.add(enterDeposit);
                        accountFrame.add(depositPanel, BorderLayout.CENTER);



                        depositField.addFocusListener(new FocusListener() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                if(depositField.getText().equals("Enter Amount Of Money")){
                                    depositField.setText("");
                                }
                            }

                            @Override
                            public void focusLost(FocusEvent e) {
                                if(depositField.getText().equals("")){
                                    depositField.setText("Enter Amount Of Money");
                                }
                            }

                        });


                        enterDeposit.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                for(char i : depositField.getText().toCharArray()){
                                    if(i<'0' || i>'9'){
                                        System.out.println("Wrong Input. Please Enter Numbers.");
                                        break;
                                    }
                                    else{
                                        accountBalance+=Double.parseDouble(depositField.getText());
                                        balanceData.put(usernameStorage, accountBalance);
                                        System.out.println("You Have Successfully Deposited Money");
                                        depositField.setText("Enter Amount Of Money");
                                        welcomePanel.setVisible(true);
                                        accountPanel.setVisible(true);
                                        accountFrame.remove(depositPanel);
                                        break;
                                    }
                                }
                            }

                        });
                    }

                });


                balanceButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Your Current Balance Is: " + accountBalance);
                    }

                });


                closeButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        writeToBalanceData(balanceData);
                        new BGUI();
                        accountFrame.dispose();
                    }
                });
                }

//  Methods


        public static void setBalance(double setBalance) {
            accountBalance+=setBalance;
        }


        private void writeToBalanceData(Map<String, Double> balanceData2){
            File file = new File("./balanceData");
            BufferedWriter bw = null;
            try{
                bw = new BufferedWriter(new FileWriter(file));
                for(Map.Entry<String, Double> entry : balanceData2.entrySet()){
                    bw.write(entry.getKey() + ":" + entry.getValue());
                    bw.newLine();
                }
                bw.flush();
            }catch(IOException e){
                e.printStackTrace();
            }finally{
                try {
                    bw.close();
                } catch (Exception e) {
                }
            }
        }


        private void fillingBalanceData (Map <String, Double> balanceData){
            String line;
            BufferedReader reader = null;
            try {
            reader = new BufferedReader(new FileReader("./balanceData"));
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(":");
                    String key = parts[0];
                    String value = parts[1];
                    balanceData.put(key, Double.parseDouble(value));
            }

                System.out.println("Welcome!");
            reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}