package TicketQueueApp;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI implements ActionListener {
	
	boolean status = true;
	String message;
	
	ArrayList<JLabel>current_nums = new ArrayList<JLabel>();
	ArrayList<JLabel>statuses = new ArrayList<JLabel>();
	
	ArrayList<JButton> statusBtns = new ArrayList<JButton>();
	ArrayList<JButton> completeBtns = new ArrayList<JButton>();
	ArrayList<JButton> proceedBtns = new ArrayList<JButton>();
	
	ArrayList<JPanel>counter_panels = new ArrayList<JPanel>();
	
	JFrame customer_window, counter_window;
	private JPanel queue_panel, status_panel, counter_panel, control_panel;
	private JButton take_numBtn, offlineBtn, completeBtn, callNextBtn;
	private JLabel now_servingLbl, last_numLbl, counter_numLbl, current_numLbl, statusLbl, control_numLbl;
	
	QueueFunc queue;
	
	public GUI() {
		
		//Customer Window
		customer_window = new JFrame();
		queue_panel 	= new JPanel();
		take_numBtn 	= new JButton("Take a Number");
		now_servingLbl 	= new JLabel("Now Serving: ");
		last_numLbl 	= new JLabel("Last Number: ");
		
		status_panel = new JPanel();
		status_panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		status_panel.setLayout(new GridLayout(1, 4));
		
		for(int i = 0; i < 4; i++) {
			
			counter_panel = new JPanel();
			counter_panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			counter_panel.setLayout(new GridLayout(3, 1));
			counter_panel.setBackground(Color.WHITE);
						
			statusLbl = new JLabel(" ");
			statusLbl.setBackground(Color.green);
			statusLbl.setOpaque(true);
			statuses.add(statusLbl);
						
			counter_numLbl = new JLabel("Counter " + (i+1));
			current_numLbl = new JLabel("");
			
			current_nums.add(current_numLbl);
			
			counter_panel.add(statusLbl);
			counter_panel.add(counter_numLbl);
			counter_panel.add(current_numLbl);
						
			counter_panels.add(counter_panel);
			status_panel.add(counter_panel);
		}
				
		take_numBtn.addActionListener(this);
		
		queue_panel.setLayout(new GridLayout(3,1));
	
		queue_panel.add(now_servingLbl);
		queue_panel.add(last_numLbl);
		queue_panel.add(take_numBtn);
		
		customer_window.setLayout(new GridLayout(2,1));
		customer_window.setSize(500, 300);
		customer_window.add(queue_panel);
		customer_window.add(status_panel);
		customer_window.setTitle("Customer Window");
		customer_window.setVisible(true);
		
		//Counter Management Window
		counter_window 	= new JFrame();
		counter_window.setLayout(new GridLayout(1, 4));
		counter_window.setSize(500, 300);
		
		for(int i = 0; i < 4; i++) {
			
			control_panel 	= new JPanel(new GridLayout(4, 1));
			control_panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
			
			control_numLbl 	= new JLabel("Counter " + (i+1));
			offlineBtn 		= new JButton("Go Offline");
			completeBtn		= new JButton("Complete Current");
			callNextBtn     = new JButton("Call Next");
			
			
			callNextBtn.addActionListener(callNextListener); //change counter status from green to red, should be disabled until doneListener invoked
			offlineBtn.addActionListener(statusListener); //change status button text, disable counter if offline (customer), disable buttons(counter)
			completeBtn.addActionListener(doneListener); //change counter status from red to green 
			
			statusBtns.add(offlineBtn);
			completeBtns.add(completeBtn);
			proceedBtns.add(callNextBtn);
						
			control_panel.add(control_numLbl);
			control_panel.add(offlineBtn);
			control_panel.add(completeBtn);
			control_panel.add(callNextBtn);
			
			counter_window.add(control_panel);				
		}
		
		counter_window.setTitle("Counter Management");
		counter_window.setVisible(true);
		
		
		
		queue = new QueueFunc(this);
	}
	
	public static void main (String[]args) {
		new GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		queue.enqueue();
		last_numLbl.setText("Last number: " + queue.queue_num);	
		updateNowServing();
		message = "Your ticket number is " + queue.queue_num;
		JOptionPane.showMessageDialog(customer_window, message, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	ActionListener callNextListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent ae) {
					
			for(int i = 0; i < proceedBtns.size(); i++) {
				if(ae.getSource() == proceedBtns.get(i)) {
					if(queue.size() != 0) {
						current_nums.get(i).setText(queue.queue[0]); //updates counter current num
						queue.dequeue();
						updateNowServing();
						proceedBtns.get(i).setEnabled(false);
						statuses.get(i).setBackground(Color.RED);
					}
					else {
						JOptionPane.showMessageDialog(counter_window, "No tickets in the waiting queue","Info", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
				
		}
	};
	
	ActionListener statusListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent ae) {
			
			if(status == true) {
				status = false; //offline
			}
			else {
				status = true; //online
			}
			
			for(int i = 0; i < statusBtns.size(); i++) {
				if(ae.getSource() == statusBtns.get(i)) {
					if(current_nums.get(i).getText() == "") {
						updateStatusText(status, statusBtns.get(i), i);	
					}
					else {
						JOptionPane.showMessageDialog(counter_window, "Counter serving ticket","Unable to go offline", JOptionPane.ERROR_MESSAGE);
						
					} } } }
	};
	
	ActionListener doneListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < completeBtns.size(); i++) {
				if(e.getSource() == completeBtns.get(i)) {
					statuses.get(i).setBackground(Color.green);
					proceedBtns.get(i).setEnabled(true);
					current_nums.get(i).setText("");
				}
			}
		}
		
	};
	
	public void updateNowServing() {
		if(queue.size()!= 0) {
			now_servingLbl.setText("Now serving: "+ queue.queue[0]);
		}
		else {
			now_servingLbl.setText("Now serving: No tickets in queue");
		}
	}
	
	public void updateStatusText(boolean stat, JButton btn, int index) {
		if(stat == true) {
			btn.setText("Go Offline");
			proceedBtns.get(index).setEnabled(true);
			counter_panels.get(index).setBackground(Color.WHITE);
			statuses.get(index).setBackground(Color.green);
		}
		else {
			btn.setText("Go Online");
			proceedBtns.get(index).setEnabled(false);
			completeBtns.get(index).setEnabled(false);
			counter_panels.get(index).setBackground(Color.LIGHT_GRAY);
			statuses.get(index).setBackground(Color.GRAY);
			current_nums.get(index).setText("Offline");
			
		}
	}
}
