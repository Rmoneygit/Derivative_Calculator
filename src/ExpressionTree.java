import java.util.Stack;

//The purpose of this class is to perform input processing and to create a tree of ExpNodes
public class ExpressionTree {
	
	//return the precedence of an operator. High number = higher precedence
	int getPrecedence(char ch)
	{
		switch (ch)
		{
		case '+':
		case '-':
			return 1;
		
		case '*':
		case '/':
			return 2;
		case '^':
			return 3;
		}
		return -1;
		}
	
	//Checks if 'c' is an operator
	boolean isOperator(char c) {
		if(c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
			return true;
		}
		return false;
	}
	
	//Do this before processing the input
	String addMultiplicationSymbols(String exp) {
		
		String newString = new String();
		
		for(int i = 0; i < exp.length(); i++) {
			
			String fullNumber = ""; //Used for interpreting multi-character exponents
			
			// Insert the original string character 
            // into the new string 
			if(exp.charAt(i) != '^') {
            newString += exp.charAt(i); 
			}
  
            //If we're not at the end of the array
            //And we see two operands in succession
            //Add the multiplication symbol between them
            if(i < exp.length() - 1)
            {
            	boolean firstCondition = Character.isDigit(exp.charAt(i)) || exp.charAt(i) == 'x' || exp.charAt(i) == ')';
            	boolean secondCondition = exp.charAt(i + 1) == 'x' || exp.charAt(i + 1) == '(';
            	if(firstCondition && secondCondition)
            		
            	{	  
	                // Insert the string to be inserted 
	                // into the new string 
	                newString += "*"; 
            	}
            	
                //Replace exponent with multiplication
        		if(exp.charAt(i) == '^') {
		
        			if(Character.isDigit(exp.charAt(i+1))) {
	        			int exponent = Integer.parseInt(Character.toString(exp.charAt(i+1)));
	        			for(int j = 0; j < exponent - 1; j++) {
	        				newString += "*x"; 
	        			}
	        			//Do not add the exponent to altered expression
	        			i++;	
        			}
        			else if(exp.charAt(i+1) == '(' && i+2 < exp.length()) {
        			
        				int j = i+2;
        				
        				while(exp.charAt(j) != ')' && j < exp.length()) {
        					fullNumber += exp.charAt(j);
        					j++;
        				}
        				
        				int exponent = Integer.parseInt(fullNumber);
	        			for(int k = 0; k < exponent - 1; k++) {
	        				newString += "*x"; 
	        			}
	        			//Do not add the exponent to altered expression
	        			i = j;
        			}
        		}
               
            } 
		}		
		return newString;
	}
	
	//Converts an infix expression to a postfix expression
	String infixToPostfix(String exp) {
		//Initialize empty String for result
		String result = new String("");
		
		//Initialize empty stack
		Stack<Character> stack = new Stack<>();
		
		for(int i = 0; i < exp.length(); ++i) {
			char c = exp.charAt(i);
			
			//If the scanned character is an operand, add it to output
			if(Character.isDigit(c) || c == 'x' || c == '.')
				result += c;
			
			//If the scanned character is a '(', push it to the stack.
			else if(c == '(')
				stack.push(c);
			
			//If the scanned character is an ')', pop and output from
			//the stack until an '(' is encountered.
			else if(c == ')') {
				while(!stack.isEmpty() && stack.peek() != '(')
					result += stack.pop();
				
				if(!stack.isEmpty() && stack.peek() != '(')
					return "Invalid Expression"; //invalid expression
				else
					stack.pop();
			}
			else if(isOperator(c)) //an operator is encountered
			{
				while(!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek())) {
					if(stack.peek() == '(')
						return "Invalid Expression";
					result += stack.pop();
				}
				stack.push(c);
			}
		}
		
		//pop all the operators from the stack
		while(!stack.isEmpty()) {
			if(stack.peek() == '(')
				return "Invalid Expression";
			result += stack.pop();
		}
		return result;
	}
	
	//Returns root of the tree it constructs
	ExpNode constructTree(char[] postfix) {
		Stack<ExpNode> st = new Stack();
		ExpNode t, t1, t2;
		String fullNumber = "";
		
		//Traverse through every character of the expression
		for(int i = 0; i < postfix.length; i++) {
			
			//If operand, push into stack
			if(!isOperator(postfix[i])) {
				if(postfix[i] == 'x') {
					t = new VariableNode();
					st.push(t);
				}
				else if(Character.isDigit(postfix[i]) || postfix[i] == '.') {
					fullNumber += Character.toString(postfix[i]);
					if(i < postfix.length - 1)
					{
						if(!Character.isDigit(postfix[i+1]) && postfix[i+1] != '.')
						{
						t = new ConstNode(Double.parseDouble(fullNumber));
						st.push(t);
						fullNumber = "";
						}
					}
					else
					{
						t = new ConstNode(Double.parseDouble(fullNumber));
						st.push(t);	
						fullNumber = "";
					}
				}
			}else //operator
			{
				t = new BinOpNode(postfix[i]);
				
				//Pop two top nodes
				//Store top
				t1 = st.pop();	//Remove top
				t2 = st.pop();
				
				//make them children
				((BinOpNode) t).setRight(t1);
				((BinOpNode) t).setLeft(t2);
				
				//System.out.println(t1 + "" + t2);
				//Add this subexpression to the stack
				st.push(t);
			}
		}
		
		// only element will be root of expression tree
		t = st.peek();
		st.pop();
		
		return t;
	}

}
