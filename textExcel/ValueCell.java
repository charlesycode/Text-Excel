package textExcel;

public class ValueCell extends RealCell implements Cell{
	
	private String value;
	@Override
	public String abbreviatedCellText() {
		// TODO Auto-generated method stub
//		if (value.indexOf('.')== -1) return (value + "              ").substring(0,10);
		return (getDoubleValue() + "            ").substring(0,10);
		
	}

	@Override
	public String fullCellText() {
		// TODO Auto-generated method stub
		if (value.indexOf('.')== -1) return value;
		return getDoubleValue() + "";
	}
	
	public ValueCell(String value) {
			this.value =  value;
	}

	@Override
	public double getDoubleValue() {
		// TODO Auto-generated method stub
		return Double.parseDouble(value);
	}

}
