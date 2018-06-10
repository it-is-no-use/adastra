package inquery;

public class Storage {

	private String storage_mat;
	private Double storage_weight;
	
	public Storage(){

	}
	
	public String getStorage_mat() {
		return storage_mat;
	}
	public void setStorage_mat(Object object) {
		this.storage_mat = object.toString();
	}
	public Double getStorage_weight() {
		return storage_weight;
	}
	public void setStorage_weight(Object object) {
		this.storage_weight = Double.valueOf(object.toString());
	}
	
	@Override
	public String toString() {
		return storage_mat + " " + storage_weight;
	}
}