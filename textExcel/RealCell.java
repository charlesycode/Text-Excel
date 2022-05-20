package textExcel;

public abstract class RealCell implements Cell{
	
	public abstract double getDoubleValue();
	@Override
	public abstract String abbreviatedCellText();
	@Override
	public abstract String fullCellText();
}
