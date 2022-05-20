package textExcel;

public class PercentCell extends RealCell implements Cell {
	private String cellIn;
	public String abbreviatedCellText() {
		return (cellIn.substring(0,cellIn.indexOf('.')) +"%" +  "        ").substring(0,10);
	}
	public String fullCellText() {
		return getDoubleValue() + "";
	}
	public PercentCell(String cellIn) {
		this.cellIn = cellIn;
	}
	@Override
	public double getDoubleValue() {
		// TODO Auto-generated method stub
		return Double.parseDouble(cellIn.substring(0,cellIn.indexOf('%'))) / 100.0;
	}
}
