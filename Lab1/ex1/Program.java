public class Program {
	public static void main(String[] args) {
		if(args.length != 3) {
			System.out.println("Invalid expression");
		} else {
			double num1 = Double.parseDouble(args[0]);
			double num2 = Double.parseDouble(args[2]);
			String operator = args[1];
			switch (operator) {
				case "+":
					System.out.println(num1 + num2);
					break;
				case "-":
					System.out.println(num1 - num2);
					break;
				case "*":
					System.out.println(num1 * num2);
					break;
				case "/":
					if(num2 == 0) System.out.println("Cannot divide by zero");
					else System.out.println(num1 / num2);
					break;
				case "^":
					System.out.println(Math.pow(num1, num2));
					break;
				default:
					System.out.println("Unsupported operator");
					break;
			}
		}
	}
}