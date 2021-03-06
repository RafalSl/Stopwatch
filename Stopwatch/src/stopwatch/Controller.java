package stopwatch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.Timer;

public class Controller implements ActionListener, Runnable {
		private View window;
		private Model licznik;
		private Executor exec;
		private Timer timer;
		
		public Controller() {
			licznik = new Model();
			window = new View();
			exec = Executors.newCachedThreadPool();
			window.getStart().addActionListener(this);
			window.getLap().addActionListener(this);
			timer = new Timer(30, new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                if (licznik.isGo()){
	                	window.getTimeCounter().setText(licznik.getElapsedTime());
	                	window.getTimeCounter().repaint();	                
	                } 
	            }
	        });
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(window.getStart())) {				
				if (licznik.isGo()){
					licznik.setGo(false);
					timer.stop();
					window.getStart().setText("Start");
					window.getLap().setText("Reset");
				}
				else {
					exec.execute(licznik);
					licznik.setGo(true);	
					window.getStart().setText("Stop");
					window.getLap().setText("Lap");
					timer.start();			
				}
			}
			
			if (e.getSource().equals(window.getLap())) {
				if(window.getLap().getText().equals("Lap")){
					String lap = licznik.lap();
					window.getLapsList().add(0, lap);
					window.getLaps().repaint();
				}else{
					licznik.setZero();
					window.getTimeCounter().setText("00:00,000");
					window.getLapsList().clear();
					window.getLap().setText("Lap");
					licznik.setI(0);
				}
			}
		}


		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}


}
