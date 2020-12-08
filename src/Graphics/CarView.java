package Graphics;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarView extends JFrame{
    private static final int X = 800;
    private static final int Y = 800;

    String title;

    CarModel carM;
    DrawPanel drawPanel;

    JPanel controlPanel = new JPanel();
    JPanel bigButtonPanel = new JPanel();
    Speedometer speedometer;

    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Lift Bed");
    JButton lowerBedButton = new JButton("Lower Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");

    JButton addCarButton = new JButton("Add car");
    JButton removeCarButton = new JButton("Remove car");

    // Constructor
    public CarView(String framename, CarModel cc){
        this.carM = cc;
        drawPanel = new DrawPanel(X, Y-240,cc.getCars());
        title = framename;
        initComponents();
    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
    public void initComponents() {
        initFrame();

        initDrawPanel();

        initGasPanel();

        initControlPanel();

        initBigButtonPanel();

        initSpeedometer();

        this.setPreferredSize(new Dimension(X,Y+speedometer.getPreferredSize().height));
        this.pack();

        this.setVisible(true);

    }

    private void initDrawPanel(){
        this.add(drawPanel);
    }

    public void initFrame(){
        this.setTitle(title);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    private void initGasPanel(){
        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1); //step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);
    }

    private void initControlPanel(){
        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);

        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        controlPanel.setBackground(Color.CYAN);
        this.add(controlPanel);
    }

    private void initBigButtonPanel(){
        bigButtonPanel.setLayout(new GridLayout(2,2));

        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/5-15,100));
        bigButtonPanel.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/5-15,100));
        bigButtonPanel.add(stopButton);

        addCarButton.setBackground(Color.green);
        addCarButton.setForeground(Color.black);
        addCarButton.setPreferredSize(new Dimension(X/5-15,100));
        bigButtonPanel.add(addCarButton);

        removeCarButton.setBackground(Color.orange);
        removeCarButton.setForeground(Color.black);
        removeCarButton.setPreferredSize(new Dimension(X/5-15,100));
        bigButtonPanel.add(removeCarButton);

        this.add(bigButtonPanel);
    }

    public void initSpeedometer(){
        speedometer = new Speedometer(carM);
        this.add(speedometer);
    }

    public int getGasAmount(){return gasAmount;}

}