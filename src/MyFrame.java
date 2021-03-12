import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame implements ActionListener {
	
	private final int size = 500;
	private final int COLS = 100;
	private final int ROWS = 100;
	
	JMenuBar mainbar = new JMenuBar();
	JMenuItem input = new JMenuItem("Input");
	JMenuItem graph = new JMenuItem("Graph");
	
	CardLayout cardLayout = new CardLayout();
	JPanel inputPanel = new JPanel(new FlowLayout());
	JPanel graphPanel = new JPanel(new GridLayout(ROWS, COLS, 2, 2));
	private JPanel[][] panels = new JPanel[ROWS][COLS];
	
	JLabel l1 = new JLabel("f(x) = ");
	JLabel l2 = new JLabel("f'(x) = ");
	JLabel spacing = new JLabel("        ");
	JLabel spacing2 = new JLabel("                                                                                            ");
	JTextField inputField = new JTextField(30);
	JButton calcButton = new JButton("Calculate");
	
	public MyFrame() {
		super("Derivative Calculator");
		setSize(size, size);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(cardLayout);
		
		setJMenuBar(mainbar);
		mainbar.add(input);
		mainbar.add(graph);
		
		add("page1", inputPanel);
		add("page2", graphPanel);
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				panels[i][j] = new JPanel();
				if(i == 50 || j == 50) {
					panels[i][j].setBackground(Color.black);
				}
				else {
				panels[i][j].setBackground(Color.white);
				}
				graphPanel.add(panels[i][j]);
			}
		}
		
		inputPanel.add(l1);
		inputPanel.add(inputField);
		inputPanel.add(calcButton);
		inputPanel.add(spacing);
		inputPanel.add(l2);
		inputPanel.add(spacing2);
		
		calcButton.addActionListener(this);
		graph.addActionListener(this);
		input.addActionListener(this);
	}
	
	public void setDerivText(String d) {
		l2.setText("f'(x) = " + d);
	}
	
	public void drawFunction(ExpNode d) {
		int horiz = 0;
		int vert = 0;
		double i = -25.0;
		
		resetGraph();
		
		while(i <= 25.0) {
			horiz = (int)(50 + 2*i);
			vert = (int)(50 - 2*d.calculate(i));
			
			if(horiz >= 0 && horiz < 100 && vert >= 0 && vert < 100)
			{
				panels[vert][horiz].setBackground(Color.red);
			}
			i += 0.5;
		}
	}
	
	public void resetGraph() {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				if(i == 50 || j == 50) {
					panels[i][j].setBackground(Color.black);
				}
				else {
				panels[i][j].setBackground(Color.white);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == calcButton){
			Driver.processInput(inputField.getText());
		}
		
		if(source == graph) {
			cardLayout.show(getContentPane(), "page2");
		}
		
		if(source == input) {
			cardLayout.show(getContentPane(), "page1");
		}
		
	}

}
