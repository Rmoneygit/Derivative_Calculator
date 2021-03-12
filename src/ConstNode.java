//Node in the expression tree that just holds a number
public class ConstNode extends ExpNode {
	double value;
	
	public ConstNode(double v) {
		value = v;
	}

	@Override
	ExpNode derivative() {
		//The derivative of a constant is zero
		return new ConstNode(0);
	}

	@Override
	double calculate(double x) {
		return value;
	}

	@Override
	String stringInfix() {
		return Double.toString(value);
	}
	
	
}
