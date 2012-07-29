package utilities;

public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("Start....");
		if (args.length == 3)
		{
			System.out.println("In the main....");
			int heght = Integer.parseInt(args[0]);
			int width = Integer.parseInt(args[1]);
			String filename = args[2];
			Mesh mesh = new Mesh(heght, width);
			mesh.writeToFile(filename);
		}
	}

}
