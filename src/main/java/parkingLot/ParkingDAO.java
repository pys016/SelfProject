package parkingLot;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ParkingDAO {
	private Connection	con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public ParkingDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 총 차량 조회
	public List<ParkingVO> selectAllParking(){
		List<ParkingVO> parkingList = new ArrayList();
		try {
			con = dataFactory.getConnection();
			String query = "SELECT * FROM parking_record ORDER BY parking_id";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int parking_id = rs.getInt("parking_id");
				String car_number = rs.getString("car_number");
				String park_area = rs.getString("park_area");
				Date entry_log = rs.getDate("entry_log");
				Date exit_log = rs.getDate("exit_log");
				String status = rs.getString("status");
				ParkingVO parkingVO = new ParkingVO(parking_id,car_number,park_area,entry_log,exit_log,status);
				parkingList.add(parkingVO);
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return parkingList;
	}
	
	//주차중인 차량
	public List<ParkingVO> currentParkingList(){
		List<ParkingVO> currentParkingList = new ArrayList();
		try {
			con = dataFactory.getConnection();
			String query = "SELECT * FROM parking_record WHERE status = 'IN'";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int parking_id = rs.getInt("parking_id");
				String car_number = rs.getString("car_number");
				String park_area = rs.getString("park_area");
				Date entry_log = rs.getDate("entry_log");
				Date exit_log = rs.getDate("exit_log");
				String status = rs.getString("status");
				ParkingVO parkingVO = new ParkingVO(parking_id,car_number,park_area,entry_log,exit_log,status);
				currentParkingList.add(parkingVO);
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return currentParkingList;
	}
	
	public void insertAddEntry(ParkingVO parkingVO) {
		try {
			con = dataFactory.getConnection();
			String car_number = parkingVO.getCar_number();
			String park_area = parkingVO.getPark_area();
			String query = "INSERT INTO parking_record (car_number, park_area) VALUES (?, ?)";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, car_number);
			pstmt.setString(2, park_area);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
