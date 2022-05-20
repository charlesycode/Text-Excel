package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location
{
    private int rows;
    private int cols;
    
    public int getRow()
    {
        // TODO Auto-generated method stub
        return rows;
    }

    @Override
    public int getCol()
    {
        // TODO Auto-generated method stub
        return cols;
    }
    
    public SpreadsheetLocation(String cellName)
    {
        cols = cellName.toUpperCase().charAt(0)-65;
        rows = Integer.parseInt(cellName.substring(1))-1;
        // TODO: Fill this out with your own code
    }

}