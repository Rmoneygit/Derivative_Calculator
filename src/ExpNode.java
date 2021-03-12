//Abstract class for a Node in the ExpressionTree. Three concrete implementations:
//ConstNode, VariableNode, BinOpNode
public abstract class ExpNode {
	// implements the appropriate derivative rule
	abstract ExpNode derivative();
	// returns the numeric value of this node for some given x value
	abstract double calculate(double x);
	//Recursively gets string of this node and its children
	abstract String stringInfix();

}
