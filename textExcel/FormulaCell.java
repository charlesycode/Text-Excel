package textExcel;
import java.util.*;
public class FormulaCell extends RealCell implements Cell {
	private String cellIn;
	private Spreadsheet sprdsht;
	public FormulaCell(String cellIn, Spreadsheet sprdsht) {
		this.cellIn = cellIn;
		this.sprdsht = sprdsht;
	}
	@Override
	public double getDoubleValue() {
		// TODO Auto-generated method stub
		String[] temp = cellIn.split(" ");
		//odds are operators, evens are nums
		if (temp[1].equalsIgnoreCase("sum") || temp[1].equalsIgnoreCase("avg")) { 
            return sumOrAvg(temp[1], temp[2]);
        }
		ArrayList<Double> numList = new ArrayList<Double>();
		ArrayList<String> strList = new ArrayList<String>();
		for(int i = 1; i < temp.length- 1; i+=2) {
			numList.add(getValue((temp[i])));
		}
		for(int i = 2; i < temp.length- 2; i+=2) {
			strList.add(temp[i]);
		}
		double num = numList.get(0);
		for (int i = 0; i < strList.size(); i++) {
			if (strList.get(i).equals("+")) num += numList.get(i+1);
			if (strList.get(i).equals("-")) num -= numList.get(i+1);
			if (strList.get(i).equals("*")) num *= numList.get(i+1);
			if (strList.get(i).equals("/")) num /= numList.get(i+1);
		}
		return num;
	}
	private double sumOrAvg(String str1, String str2) { //sum or avg, cells
        String[] temp = str2.split("-");
        SpreadsheetLocation firstLocate = new SpreadsheetLocation(temp[0]);
        SpreadsheetLocation lastLocate = new SpreadsheetLocation(temp[1]);
        double count = 0;
        double sum = 0;
        int firstRow = firstLocate.getRow();
        int lastRow = lastLocate.getRow();
        int firstCol = firstLocate.getCol();
        int lastCol = lastLocate.getCol();
//        for (int i = firstLocate.getRow(); i <= lastLocate.getRow(); i++) { //this doesn't work for some reason, but using a variable equal to that does?
//            for (int j = firstLocate.getCol(); j <= firstLocate.getCol(); j++) {
        for (int i = firstRow; i <= lastRow; i++) {
        	for (int j = firstCol; j <= lastCol; j++) {
                RealCell currentCell = (RealCell) sprdsht.getCell(i,j);
                sum += currentCell.getDoubleValue();
                count++;
            }
        }
        if (str1.equalsIgnoreCase("avg")) return sum / count;
        return sum;
    }
	@Override
	public String abbreviatedCellText() {
		// TODO Auto-generated method stub
		return (getDoubleValue() + "                      ").substring(0,10);
	}
	@Override
	public String fullCellText() {
		// TODO Auto-generated method stub
		return cellIn;
	}
    public static boolean isNumeric(String str) {  //copied from ms dreyer
        try {  
          Double.parseDouble(str);  //checks if a string is a double or not
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
	}
    public double getValue(String val) { //copied from ms dreyer
        double value = 0;
        if (isNumeric(val)) {
            value = Double.parseDouble(val);
        }
        else { //so if you input a1, it refers to the value of a1, not the actual string
            SpreadsheetLocation locate = new SpreadsheetLocation(val);
            RealCell cell = (RealCell) sprdsht.getCell(locate);
            value = cell.getDoubleValue();
        }
        return value;
    }
}
