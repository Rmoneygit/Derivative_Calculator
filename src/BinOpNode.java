
public class BinOpNode extends ExpNode {
	char op;		//The operator, could be +, -, *, or /
	ExpNode left;
	ExpNode right;
	
	BinOpNode(char op) {
		this.op = op;
		left = right = null;
	}
	
	BinOpNode(char op, ExpNode left, ExpNode right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}
	
	void setLeft(ExpNode left) {
		this.left = left;
	}
	
	void setRight(ExpNode right) {
		this.right = right;
	}
	
	@Override
	ExpNode derivative() {		
		switch(op) {
		case '+':
			return new BinOpNode('+', left.derivative(), right.derivative());
		case '-':
			return new BinOpNode('-', left.derivative(), right.derivative());
		case '*':
			return new BinOpNode('+',
								new BinOpNode('*', left, right.derivative()),
								new BinOpNode('*', right, left.derivative()));
		case '/':
			return new BinOpNode('/', 
								new BinOpNode('-', 
										new BinOpNode('*', right, left.derivative()),
										new BinOpNode('*', left, right.derivative())),
								new BinOpNode('*', right, right));
		default:
			return null;		
		}
	}
	
	@Override
	String stringInfix() {
		String result = "";
		
		if(op == '+' || op == '-') {
			result += "(";
			}
			
			if(op == '/') {
				result += "(";
			}
			result += left.stringInfix();	
			if(op == '/') {
				result += ")";
			}
			result += op;
			if(op == '/') {
				result += "(";
			}
			result += right.stringInfix();
			if(op == '/') {
				result += ")";
			}
			if(op == '+' || op == '-') {
			result += ")";
			}
		return result;
	}
	
	@Override
	double calculate(double x) {
		switch(op) {
		case '+':
			return left.calculate(x) + right.calculate(x);
		case '-':
			return left.calculate(x) - right.calculate(x);
		case '*':
			return left.calculate(x) * right.calculate(x);
		case '/':
			return left.calculate(x) * right.calculate(x);
		default:
			return 0.0;		
		}
		
	}

}
