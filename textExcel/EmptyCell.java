package textExcel;

public class EmptyCell implements Cell {
	private String text;
	@Override
	public String abbreviatedCellText() {
		// TODO Auto-generated method stub
		return text;
	}

	@Override
	public String fullCellText() {
		// TODO Auto-generated method stub
		return "";
	}
	public EmptyCell() {
		text = "          ";
	}
}
