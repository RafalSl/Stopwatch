package stopwatch;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class View extends JFrame implements ActionListener, Runnable {
	private JFrame f;
	private Font fontHello, fontHelloMin, fontTime, fontTimeList, fontButton;
	private JButton start, lap;
	private JLabel timeCounter, timeOverall;
	private Model licznik;
	private Executor exec;
	private Timer timer;
	
	public View() {
		licznik= new Model();
		f = new JFrame();
		f.setTitle("Stoper");
		f.setSize(1200, 900);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		fontHello = new Font("Calbri", 1, 40);
		fontHelloMin = new Font("Calbri", 1, 12);
		fontTime = new Font("Calbri", 1, 50);
		fontButton = new Font("Calbri", 1, 25);
		JLabel hello = new JLabel();
		hello.setBounds(500, 70, 200, 50);
		hello.setText("Stoper");
		hello.setFont(fontHello);
		JLabel helloMin = new JLabel();
		helloMin.setBounds(580, 100, 200, 30);
		helloMin.setText("by Rafa� S�omka");
		helloMin.setFont(fontHelloMin);
		f.add(hello);
		f.add(helloMin);
		timeCounter = new JLabel("00:00,000");
		timeCounter.setBounds(450, 200, 300, 50);
		timeCounter.setFont(fontTime);
		timeCounter.setHorizontalAlignment(SwingConstants.RIGHT);
		f.add(timeCounter);
		start = new JButton("Start");
		start.setBounds(400, 350, 150, 50);
		start.setFont(fontButton);
		start.addActionListener(this);
		f.add(start);
		lap = new JButton("Lap");
		lap.setBounds(600, 350, 150, 50);
		lap.setFont(fontButton);
		f.add(lap);
		f.setVisible(true);
		exec = Executors.newCachedThreadPool();
		exec.execute(this);
		timer = new Timer(30, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (licznik.isGo()){
/*                	if(licznik.getHours() == 0) timeCounter.setText(licznik.getMinutes() + ":" + licznik.getSeconds() + "," + licznik.getMiliSeconds());
                	else timeCounter.setText(licznik.getHours()+ ":" + licznik.getMinutes() + ":" + licznik.getSeconds() + "," + licznik.getMiliSeconds());*/
                	timeCounter.setText(licznik.getElapsedTime());
                timeCounter.repaint();
                
                } 
            }
        });
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(start)) {				
			if (licznik.isGo()){
				licznik.setGo(false);
				start.setText("Start");
				timer.stop();
			}
			else {
				exec.execute(licznik);
				licznik.setZero();
				licznik.setGo(true);	
				start.setText("Stop");
				timer.start();			
			}
		}
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}