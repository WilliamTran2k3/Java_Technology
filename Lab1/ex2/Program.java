import vn.edu.tdtu.*;

public class Program {
	public static void main(String[] args) {
		int a[] = {12, 2, 34, 5, 6};
		int b[] = {11, 8, 7, 0, 98};
		ArrayOutput.print(a);
		ArrayOutput.print(b);
		int c[] = ArrayHandler.merge(a, b);
		ArrayOutput.print(c);
		ArrayHandler.sort(c);
		ArrayOutput.write(c, "output.txt");
	}
}