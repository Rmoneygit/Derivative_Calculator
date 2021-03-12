//Node in the expression tree that just holds 'x'
public class VariableNode extends ExpNode {

	public VariableNode() {
		//Empty
	}
	
	@Override
	ExpNode derivative() {
		//The derivative of x is one
		return new ConstNode(1);
	}
	@Override
	double calculate(double x) {
		return x;
	}

	@Override
	String stringInfix() {
		return "x";
	}

}
