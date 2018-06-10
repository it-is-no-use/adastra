package inquery;

public class Expense {

	private String expense_obj;
	private String expense_mat;
	private Double expense_weight;
	
	public Expense(){

	}

	public String getExpense_obj() {
		return expense_obj;
	}

	public void setExpense_obj(Object object) {
		this.expense_obj = object.toString();
	}

	public String getExpense_mat() {
		return expense_mat;
	}

	public void setExpense_mat(Object object) {
		this.expense_mat = object.toString();
	}

	public Double getExpense_weight() {
		return expense_weight;
	}

	public void setExpense_weight(Object object) {
		this.expense_weight = Double.valueOf(object.toString());
	}

	@Override
	public String toString() {
		return expense_obj + " " + expense_mat + " " + expense_weight;
	}
}