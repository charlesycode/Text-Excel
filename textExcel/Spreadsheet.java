package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid
{
    private Cell[][] sheet;
    @Override
    public String processCommand(String command)
    {
        if (command.equals("")) return ""; //for checkpoint 1 to pass
    	String temp = ""; 
        String[] split = command.split(" ",3); 
        EmptyCell empty = new EmptyCell();
        if(split.length == 3) {
        	if (split[2].substring(0,1).equals("(")) { //checks if theres an opening paranthesis (formula cell)
        		SpreadsheetLocation locate = new SpreadsheetLocation(split[0]);
        		sheet[locate.getRow()][locate.getCol()] = new FormulaCell(split[2], this); //needed for getValue() in formula cell because it requires a spreadsheet object
                temp = getGridText();
        	}
        	else if(split[2].substring(split[2].length()-1).equals("%")) { //checks if its a percent cell
        		SpreadsheetLocation locate = new SpreadsheetLocation(split[0]);
        		sheet[locate.getRow()][locate.getCol()] = new PercentCell(split[2]);
                temp = getGridText();
         	}else if(split[2].substring(0,1).equals("\"")){ //checks if there are quotes (text cell)
        		SpreadsheetLocation locate = new SpreadsheetLocation(split[0]);
            	sheet[locate.getRow()][locate.getCol()] = new TextCell(removeQuotes(split[2]));
            	temp = getGridText();
        	} else {
        		SpreadsheetLocation locate = new SpreadsheetLocation(split[0]); //if there are no quotes, no percent, no parenthesis, then its just a value cell
        		sheet[locate.getRow()][locate.getCol()] = new ValueCell(split[2]);
                temp = getGridText();
        	}
        }else if(split.length == 2) { //works for things like "clear a1", which is split into 2 sections.
            SpreadsheetLocation locate = new SpreadsheetLocation(split[1]);
            sheet[locate.getRow()][locate.getCol()] = empty;
            temp = getGridText();
        }else {
            if(split[0].equalsIgnoreCase("CLEAR")){  //checks if its a single section and it contains the word "clear"
                for (int i = 0; i < 20; i++) { //nested for loop to traverse the grid and set every cell to an empty cell (created line 14)
                    for (int j = 0; j < 12; j++) {
                        sheet[i][j] = empty;
                    }
                }
                temp = getGridText();
            }else { //only thing left is just the cell name as an input like "a1"
                SpreadsheetLocation located = new SpreadsheetLocation(command);
                temp = getCell(located).fullCellText();
            }
       }
        return temp;
    }
    public String removeQuotes(String quoted){ //method to remove quotes
        return quoted.substring(1, quoted.length()-1); //the quotes for text cells are at the first and last index
    }
    @Override
    public int getRows()
    {
        // TODO Auto-generated method stub
        return 20;
    }

    @Override
    public int getCols()
    {
        // TODO Auto-generated method stub
        return 12;
    }

    @Override
    public Cell getCell(Location loc)
    {
        int row = loc.getRow();
        int col = loc.getCol();
        return sheet[row][col];
    }
    public Cell getCell(int row, int col) //makes life easier :)
    {
        return sheet[row][col];
    }

    @Override
    public String getGridText() //nested for loop
    {
        String temp = "   |";
        
        for (char c = 'A'; c < 'M'; c++) {
            temp += c + "         |";
        }
        for (int i = 0; i < 20; i++) {
            temp += "\n";
            if (i < 9) temp += (i+1) + "  |";
            if (i >= 9) temp += (i+1) + " |";
            for (int j = 0; j < 12; j++) {
                temp = temp + getCell(i,j).abbreviatedCellText() + "|";
            }
        }
            temp += "\n";
            return temp;    
    }
    public Spreadsheet(){
        EmptyCell empty = new EmptyCell();
        sheet = new Cell[20][12]; // creates an empty 2d array of Cells
        for (int i = 0; i <20; i++) {
            for (int j = 0; j <12; j++) {
                sheet[i][j] = empty;
            }
        }
    }
}