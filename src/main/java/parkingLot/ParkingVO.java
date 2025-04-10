package parkingLot;

import java.sql.Date;

public class ParkingVO {
	private int parking_id;
	private String car_number;
	private String park_area;
	private Date entry_log;
	private Date exit_log;
	private String status;
	
	public ParkingVO() {
		super();
	}

	public ParkingVO(int parking_id, String car_number, String park_area, Date entry_log, Date exit_log,
			String status) {
		super();
		this.parking_id = parking_id;
		this.car_number = car_number;
		this.park_area = park_area;
		this.entry_log = entry_log;
		this.exit_log = exit_log;
		this.status = status;
	}

	public int getParking_id() {
		return parking_id;
	}

	public void setParking_id(int parking_id) {
		this.parking_id = parking_id;
	}

	public String getCar_number() {
		return car_number;
	}

	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}

	public String getPark_area() {
		return park_area;
	}

	public void setPark_area(String park_area) {
		this.park_area = park_area;
	}

	public Date getEntry_log() {
		return entry_log;
	}

	public void setEntry_log(Date entry_log) {
		this.entry_log = entry_log;
	}

	public Date getExit_log() {
		return exit_log;
	}

	public void setExit_log(Date exit_log) {
		this.exit_log = exit_log;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
