package entity;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="trucks")

public class Trucks {
	
	@Id
	private Integer truck_id;
	
	@Column(name="truck_name")
	private String truck_name;
	
	@Column(name="gov_number")
	private String gov_number;
	
    @OneToMany(mappedBy = "truck", fetch = FetchType.EAGER)
    private Collection<Job> job;
	
	public Trucks(){
		
	}

	public Integer getTruck_id() {
		return truck_id;
	}

	public void setTruck_id(Integer truck_id) {
		this.truck_id = truck_id;
	}

	public String getTruck_name() {
		return truck_name;
	}

	public void setTruck_name(String truck_name) {
		this.truck_name = truck_name;
	}

	public String getGov_number() {
		return gov_number;
	}

	public void setGov_number(String gov_number) {
		this.gov_number = gov_number;
	}
	
	@Override
	public String toString() {
		return truck_id + " " + truck_name + " " + gov_number;
	}
}