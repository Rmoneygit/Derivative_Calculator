
public class Driver {
	
	static MyFrame frame = new MyFrame();
	
	public static void main(String[] args) 
	{
		frame.setVisible(true);		
	}
	
	public static void processInput(String input)
	{
		ExpressionTree et = new ExpressionTree();
		
		//This processes the input and produces a new string with some added/removed symbols
		String altered = et.addMultiplicationSymbols(input);
		//This changes the expression from infix to postfix so the tree can understand it
		String postfix = et.infixToPostfix(altered);
		char[] charArray = postfix.toCharArray();
		//build an expression tree representing the function
		ExpNode root = et.constructTree(charArray);
		
		//return the root of a new expression tree representing the derivative
        ExpNode d = root.derivative();
        //Display it and draw the function in the JFrame
        System.out.println(d.stringInfix());
        frame.setDerivText(d.stringInfix());
        frame.drawFunction(d);

	}
	
}
