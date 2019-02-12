import java.util.Scanner;

public class DrawBrocolli {
	static int numberOfRows, numberOfColumns; 
	static String[][] board = new String[1][1];
	/* Use these static variables in main, send to CreateArray() to update 
	 * board size based on user input. I assign board an arbitrary size of 1 x 1, 
	 * then update in CreateArray() based on numberOfRows and numberOfColumns.
	 * Different way to do this? Proper use of static variables?
	*/
	static String [][] brocolliBoard;
	// TODO Unused?
	
//	static int numberOfRows = board.length;
//	// Number of rows in this array
//	static int numberOfColumns = board[0].length;
//	// Number of columns for row 0 in this array. I am assuming all row
//	// lengths / number of columns is the same for all rows
	// TODO Keep this, implement somehow? Old code
	
	static Scanner input = new Scanner(System.in);
	int row = -1;
	int column = -1;
	DrawBrocolli coords;
	// TODO coords is unused
	
	public static void main(String args[]){
		System.out.println("Welcome to the Draw Brocolli! \nWhat size board "
			+ "would you like? __ rows x __ columns\n"
			+ "(Hint. Enter a square, probably with"
			+ " sides greater than 10)");
		System.out.print("Enter number of rows: ");
		numberOfRows = UserInput();
		// Update static variable 
		System.out.print("Enter number of columns: ");
		numberOfColumns = UserInput();
		// Update static variable
		System.out.println("\nHere is your " + numberOfRows + " x " + numberOfColumns + 
		" board:\n");
		CreateArray(numberOfRows, numberOfColumns);
		UserPrompt();
	}
	private static void CreateArray(int row, int column) {
		board = new String[row][column];
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				board[i][j] = " - ";
			}
		}
		/* Update board with dimensions numberOfRows and numberOfColumns, sent
		from and updated in main method. "Create" board based on these dimensions */
	}
	private static void PrintArray() {		
		// Prints top header
		System.out.print(" ");
		// Adds one additional tab to correctly align the column headers with
		// their columns
		for (int j = 0; j < board[0].length; j++) {
		// I use board[0].length to find row length because I am
		// assuming that the rows will always be of constant length
			if (j < 10){
				System.out.print("  " + j);
			}
			else if (j >= 10 && j <= board[0].length){
				System.out.print(" " + j);
			}
			else {
				System.out.print("Error. Something went wrong.");
			}
		}
		// Prints full board
		System.out.println();
		for (int i = 0; i < board.length; i++){
			if (i <= 9) {
				System.out.print(i + " ");
			}
			else if (i > 9) {
				System.out.print(i);
			}
			for (int j = 0; j < board[i].length; j++){
				System.out.print(board[i][j]);
			}
			System.out.println();
		}	
		System.out.println();
	}
	private static void UserPrompt() {
		PrintArray();
		System.out.println("Do you want to draw on your own or "
			+ "\ndo you want to DRAW THE BROCOLLI?");
		System.out.print("Press 1 for free draw or 2 for THE BROCOLLI: ");
		int brocolliOrNah = UserInput();
		if (brocolliOrNah == 1) {
			UserChoiceDraw();
		}
		else if (brocolliOrNah == 2) {
			UserChoiceBrocolli();
		}
		else {
			System.out.println("Error. Wrong input. Program is quitting.");
			ProgramQuit();
			// TODO If non-int input, program crashes. Fix this > exception handling
		}
	}
	private static void UserChoiceDraw() {
		boolean cont = true;
		while (cont){
			System.out.println("Select row number and column number to draw.");
			System.out.print("Enter row: ");
			int row = UserInput();
			System.out.print("Enter column: ");
			int column = UserInput();
			System.out.println("You entered: " + row + " , " + column + "\n\nCURRENT BOARD:");
			DrawBrocolli object = new DrawBrocolli(row, column);
			object.Draw();
			System.out.print("Continue? Please select 'y' or 'n': "); 
			switch (input.nextLine()){
				case "n": cont = false; ProgramQuit();
				case "y": break;
				default: System.out.println("Error. Wrong input. Program is quitting.");
							ProgramQuit();
			}
		}
	}
	private static void UserChoiceBrocolli() {
//		DrawStem();
//		DrawTriangle();
//		DrawLowerSquares();
//		DrawUpperSquares();
		
		FunctionDrawTest();
		
/* I commented out the original brocolli drawing method and implemented my 
function drawing method for testing. It draws a triangle, not brocolli.
*/
		
		
		System.out.println("\nCURRENT BOARD: ");
		PrintArray();
	}
	private static void DrawStem() {
		
		int stemWidthRatio = numberOfColumns / 3;
		int stemHeight = 2 * (numberOfRows / 3);
		for (int i = (numberOfRows - 1); i >= stemHeight; i--) {
			board[i][stemWidthRatio] = " + ";
			board[i][(numberOfColumns - 1) - stemWidthRatio] = " + ";
		}
		// Above draws left stem and mirrors it from the other endpoint of the
		// array to draw the right stem, and therefore both stems symmetrically
	}
	private static void DrawTriangle() {
		int k = 1;
		for (int i = (2 * (numberOfRows / 3)); i > ((numberOfRows - 1) / 3); i--){ 
			board[i][(numberOfColumns - 1) - k] = " + ";
			board[i][k] = " + ";
			k++;
			for (int j = ((numberOfColumns - 1) - k); j >= k; j--) {
				board[i][j] = " + ";
			}
		}
		// Above draws "triangle" up from top of stem until there is only
		// a third of the array left ((numberOfColums - 1) / 3)
	}
	private static void DrawLowerSquares() {
		int lowerSquareLowerBound = (2 * (numberOfRows / 3)) + ((numberOfRows / 4) / 2); // 14 for board[18][18]
		int lowerSquareUpperBound = (2 * (numberOfRows / 3)) - ((numberOfRows / 4) / 2); // 10 for board[18][18]
		for (int i = (lowerSquareLowerBound - 1); i >= (lowerSquareUpperBound - 1); i--){ // change "+ 2" to be numberOfColumns/4 ?
			for (int j = 1; j <= (numberOfColumns / 4); j++){
				board[i][j] = " + ";
				board[i][(numberOfColumns - 1) - j] = " + ";
			}
		}
		// Above draws the lower left and right squares
		// TODO elaborate on the reasoning behind this
	}
	private static void DrawUpperSquares() {
		int upperSquareLowerBound = numberOfRows / 2;
		int upperSquareUpperBound = numberOfRows / 4;
		for (int i = (upperSquareUpperBound + 1); i < upperSquareLowerBound; i++){
			for (int j = upperSquareUpperBound; j < (upperSquareLowerBound - 1); j++){
				board[i][j] = " + ";
				board[i][(numberOfColumns - 1) - j] = " + ";
			}
		}
		// Above draws the upper left and right squares
		// TODO elaborate on the reasoning behind this
	}
	private static void FunctionDrawTest(){
		for (int i = 0; i <= (numberOfColumns - 1) / 2; i++){
			for (int j = 2 * i; j >= 0; j--){
				board [i][j] = " + ";
			}
		}
		/* Above draws a triangle from 0 to (numberOfColumns-1)/2 wide and from
		2*i on the right to 0 on the left as it works down.
		
		For board[20][20]; it works from row 0 to row 9, going from 2*i to 0 in
		each row
		 
		 */
	}
	private static int UserInput() {
		int selection = input.nextInt();
		input.nextLine();
		// TODO input validation
		return selection;
	}
	private DrawBrocolli(int row, int column){
		this.row = row;
		this.column = column;
	}	
	private int getRow() {
		return row;
	}
	private int getColumn() {
		return column;
	}
	private void Draw() {
		int currentRow = getRow();
		int currentColumn = getColumn();
		board[currentRow][currentColumn] = " + ";
		PrintArray();
	}
	private static void ProgramQuit(){
		System.out.print("Goodbye!");
		System.exit(0);
	}
}// end of class
