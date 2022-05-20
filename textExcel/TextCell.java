package textExcel;

public class TextCell implements Cell{
    
    private String cellIn;
    
    public TextCell(String cellIn){
        this.cellIn = cellIn;
    }
    public String abbreviatedCellText() { 
       String temp = cellIn + "                                                                                   ";
       return temp.substring(0,10);
    }
    public String fullCellText() {
        return "\"" + cellIn + "\"";
    }
}