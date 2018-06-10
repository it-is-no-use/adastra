package entity;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="drivers")

public class Drivers {
	
	@Id
	private Integer driver_id;
	
	@Column(name="driver_fullname")
	private String driver_fullname;
	
    @OneToMany(mappedBy = "driver", fetch = FetchType.EAGER)
    private Collection<Job> job;
	
	public Drivers(){
	
	}

	public Integer getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(Integer driver_id) {
		this.driver_id = driver_id;
	}

	public String getDriver_fullname() {
		return driver_fullname;
	}

	public void setDriver_fullname(String driver_fullname) {
		this.driver_fullname = driver_fullname;
	}
	
	@Override
	public String toString() {
		return driver_id + " " + driver_fullname;
	}
}