package entity;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="materials")

public class Materials {

	@Id
	private Integer mat_id;
	
	@Column(name="mat_name")
	private String mat_name;

    @OneToMany(mappedBy = "material", fetch = FetchType.EAGER)
    private Collection<Job> job;
	
	public Integer getMat_id() {
		return mat_id;
	}

	public void setMat_id(Integer mat_id) {
		this.mat_id = mat_id;
	}

	public String getMat_name() {
		return mat_name;
	}

	public void setMat_name(String mat_name) {
		this.mat_name = mat_name;
	}

	@Override
	public String toString() {
		return mat_id + " " + mat_name;
	}
}