package entity;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name="job")

public class Job {
	
	@Id
	private Integer j_id;
	
	@Column(name="j_when")
	private Date j_when;
	
	@Column(name="weight")
	private Double weight;
	
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ob_id")
    private Objects object;
    
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Drivers driver;
    
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "truck_id")
    private Trucks truck;
    
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "mat_id")
    private Materials material;
    
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users user;

	public Integer getJ_id() {
		return j_id;
	}

	public void setJ_id(Integer j_id) {
		this.j_id = j_id;
	}

	public Date getJ_when() {
		return j_when;
	}

	public void setJ_when(Date j_when) {
		this.j_when = j_when;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Objects getObject() {
		return object;
	}

	public void setObject(Objects object) {
		this.object = object;
	}

	public Drivers getDriver() {
		return driver;
	}

	public void setDriver(Drivers driver) {
		this.driver = driver;
	}

	public Trucks getTruck() {
		return truck;
	}

	public void setTruck(Trucks truck) {
		this.truck = truck;
	}

	public Materials getMaterial() {
		return material;
	}

	public void setMaterial(Materials material) {
		this.material = material;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return j_id + " " + j_when + " " + weight + " " + object.getOb_name() + " " + driver.getDriver_fullname() + " " + truck.getTruck_name() + " " + material.getMat_name() + " " + user.getUser_fullname();
	}
}