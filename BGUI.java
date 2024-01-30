import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BGUI implements ActionListener{
//Instance Variables


    private JFrame frame;
    private JLabel Hello;
    private JButton logIn;
    private JButton register;
    private JButton exit;

    public BGUI(){

// Frame Settings


        frame = new JFrame();
        JPanel WelcomeToTheBank = new JPanel();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our Bank");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);

        //Panels, Buttons, etc.


        Hello = new JLabel("Welcome To Our Bank! Please Log In Or Create A New Account.");
        WelcomeToTheBank.add(Hello);
        frame.add(WelcomeToTheBank, BorderLayout.PAGE_START);
        JPanel Accounts = new JPanel();
        logIn = new JButton("Log In");
        register = new JButton("Register");
        exit = new JButton("Exit");
        Accounts.add(logIn);
        Accounts.add(register);
        Accounts.add(exit);
        Accounts.setBorder(BorderFactory.createEmptyBorder(300, 300, 100,300));
        Accounts.setLayout(new GridLayout(0, 1));
        frame.add(Accounts, BorderLayout.CENTER);


// Action And Focus Listeners Below


        logIn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new LogIn();
                frame.dispose();
            }
        });



        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Register();
                frame.dispose();
           }
        });



        exit.addActionListener(new CloseListener());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }


    class CloseListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        
        System.exit(0);
    }
        
}
}
