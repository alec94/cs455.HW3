import java.io.IOException;

/**
 * Created by Alec Kent on 4/8/2017.
 * default main for running the different map reduce algorithms
 */
public class Main {
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Usage: <input file> <output file> <profile>");
			System.exit(-1);
		}

		try {
			String profile = args[2].toLowerCase();

			switch (profile) {
				case "q1":
					System.out.println("Running Q1");
					cs455.Q1.Main.main(args);
					break;
				case "q2":
					System.out.println("Running Q2");
					cs455.Q2.Main.main(args);
					break;
				case "q3":
					System.out.println("Running Q3");
					cs455.Q3.Main.main(args);
					break;
				case "q4":
					System.out.println("Running Q4");
					cs455.Q4.Main.main(args);
					break;
				case "q5":
					System.out.println("Running Q5");
					cs455.Q5.Main.main(args);
					break;
				case "q6":
					System.out.println("Running Q6");
					cs455.Q6.Main.main(args);
					break;
				case "q7":
					System.out.println("Running Q7");
					cs455.Q7.Main.main(args);
					break;
				case "q8":
					System.out.println("Running Q8");
					cs455.Q8.Main.main(args);
					break;
				case "q9":
					System.out.println("Running Q9");
					cs455.Q9.Main.main(args);
					break;
				default:
					System.out.println("Unknown profile: " + profile);
			}
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
