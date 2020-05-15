import java.io.FileNotFoundException;
import java.io.FileReader;

import ast.Program;
import parser.ParserLogic;
import visitor.PrettyPrintVisitor;

public class Parser {
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length == 0)
			System.err.println("No file arguments givens");
		else {
			// parse each file argument given
			for (int i = 0; i < args.length; i++) {
				FileReader file;
				
				// attempt to open file
				try {
					file = new FileReader("programs/" + args[i]);
				} catch (FileNotFoundException e) {
					System.err.println(args[i] + " was not found in MiniJava/programs");
					continue; // try next file
				}
				
				// create parser
				ParserLogic parserLogic = new ParserLogic(file);
				System.out.println("Parsing " + args[i] + "...");
				
				// initiate parse and clock time
				long startTime = System.currentTimeMillis();
				Program prog = parserLogic.parseProgram();
				long endTime = System.currentTimeMillis();
				
				// print out statistics
				System.out.println("File has finished parsing!");
//				System.out.println("Execution time: " + (endTime - startTime) + "ms");
				System.out.println(parserLogic.getErrorCount() + " errors reported");
				System.out.println("---");
				
				// print out ASTs
				PrettyPrintVisitor printer = new PrettyPrintVisitor();
				printer.visit(prog);
				System.out.println();
			}
		}
	}
}
