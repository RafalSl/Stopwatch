package stopwatch;

import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;


public class View extends JFrame implements Runnable {
	private JFrame f;
	private Font fontHello, fontHelloMin, fontTime, fontLapsList, fontButton;
	private JButton start, lap;
	private JLabel timeCounter;
	private JScrollPane listScrollPane;
	private JList<String> laps;
	private DefaultListModel<String> lapsList;
	
	public View() {
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
		fontLapsList = new Font("Calbri", 1, 25);
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
		timeCounter.setBounds(400, 200, 300, 50);
		timeCounter.setFont(fontTime);
		timeCounter.setHorizontalAlignment(SwingConstants.RIGHT);
		f.add(timeCounter);
		start = new JButton("Start");
		start.setBounds(400, 300, 150, 50);
		start.setFont(fontButton);
		f.add(start);
		lap = new JButton("Lap");
		lap.setBounds(600, 300, 150, 50);
		lap.setFont(fontButton);
		f.add(lap);
		lapsList = new DefaultListModel<>(); 
		laps = new JList<String>(lapsList);
		laps.setFont(fontLapsList);
		DefaultListCellRenderer renderer = (DefaultListCellRenderer)laps.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);
		f.add(laps);
		listScrollPane = new JScrollPane(laps);
		listScrollPane.setBounds(380, 400, 400, 400);
		f.add(listScrollPane);
		f.setVisible(true);
	}

	public JLabel getTimeCounter() {
		return timeCounter;
	}


	public void setTimeCounter(JLabel timeCounter) {
		this.timeCounter = timeCounter;
	}


	public JButton getStart() {
		return start;
	}


	public void setStart(JButton start) {
		this.start = start;
	}


	public JButton getLap() {
		return lap;
	}


	public void setLap(JButton lap) {
		this.lap = lap;
	}

	public JList<String> getLaps() {
		return laps;
	}

	public void setLaps(JList<String> laps) {
		this.laps = laps;
	}

	public DefaultListModel<String> getLapsList() {
		return lapsList;
	}

	public void setLapsList(DefaultListModel<String> lapsList) {
		this.lapsList = lapsList;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	

}
