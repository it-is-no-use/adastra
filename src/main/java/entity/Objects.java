package entity;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="objects")

public class Objects {

	@Id
	private Integer ob_id;
	
	@Column(name="ob_name")
	private String ob_name;
	
    @OneToMany(mappedBy = "object", fetch = FetchType.EAGER)
    private Collection<Job> job;
	
	public Objects(){
		
	}

	public Integer getOb_id() {
		return ob_id;
	}

	public void setOb_id(Integer ob_id) {
		this.ob_id = ob_id;
	}

	public String getOb_name() {
		return ob_name;
	}

	public void setOb_name(String ob_name) {
		this.ob_name = ob_name;
	}

	@Override
	public String toString() {
		return ob_id + " " + ob_name;
	}
}