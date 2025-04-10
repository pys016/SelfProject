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
				Double parking_fee = rs.getDouble("parking_fee");
				ParkingVO parkingVO = new ParkingVO(parking_id,car_number,park_area,entry_log,exit_log,status, parking_fee);
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
				Double parking_fee = rs.getDouble("parking_fee");
				ParkingVO parkingVO = new ParkingVO(parking_id,car_number,park_area,entry_log,exit_log,status,parking_fee);
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
	//차량번호,주차장소 중복 확인
	public int checkDuplicateEntry(String car_number, String park_area) {
		boolean isCarNumberInUse = false;
	    boolean isParkAreaInUse = false;
		try {
			con = dataFactory.getConnection();
			//차량번호 중복 확인
			String cn_query = "SELECT COUNT(*) FROM parking_record WHERE car_number=? AND status='IN'";
			pstmt = con.prepareStatement(cn_query);
			pstmt.setString(1, car_number);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next() && rs.getInt(1)>0) {
				isCarNumberInUse = true;
			}
			rs.close();
			pstmt.close();
			
			//주차장소 중복 확인
			String pa_query = "SELECT COUNT(*) FROM parking_record WHERE park_area=? AND status='IN'";
			pstmt = con.prepareStatement(pa_query);
			pstmt.setString(1, park_area);
			rs = pstmt.executeQuery();
			if(rs.next() && rs.getInt(1)>0) {
				isParkAreaInUse = true;
			}
			rs.close();
			pstmt.close();
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(isCarNumberInUse && isParkAreaInUse) {
			return 3;
		}else if(isCarNumberInUse) {
			return 1;
		}else if(isParkAreaInUse) {
			return 2;
		}else {
			return 0;
		}
			
	}
	
	
	//입차등록
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
	
	//출차등록
	public ParkingVO findCarNumber(String car_number) {
		ParkingVO parkingVO = null;
		try {
			con = dataFactory.getConnection();
			String query = "SELECT * FROM parking_record WHERE car_number=? AND (status='IN' OR status='OUT')";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, car_number);
			System.out.println("find1:" + car_number);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("find2 : " + rs.getDate("entry_log"));
				int parking_id = rs.getInt("parking_id");
				String park_area = rs.getString("park_area");
				Date entry_log = rs.getDate("entry_log");
				Date exit_log = rs.getDate("exit_log");
				String status = rs.getString("status");
				Double parking_fee = rs.getDouble("parking_fee");
				parkingVO = new ParkingVO(parking_id, car_number, park_area, entry_log, exit_log, status, parking_fee);
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("find3 :" + parkingVO);
		return parkingVO;
	}
	
	public void updateExit(String car_number) {
		try {
			con = dataFactory.getConnection();
			String query = "UPDATE parking_record SET exit_log = SYSDATE, status = 'OUT' WHERE car_number = ? AND status = 'IN'";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, car_number);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//주차 요금 계산 기능
	public double parkingTimeFee(Date entry_log, Date exit_log) {
		long MilliSeconds = exit_log.getTime() - entry_log.getTime();
		//Date는 밀리초단위로 계산해야함 시간으로 변환하는 공식
		long Hours = MilliSeconds / (1000 * 60 * 60);
		
		
		if (MilliSeconds <= 0) {
		   System.out.println("Invalid time difference: " + MilliSeconds);
		   return 0; 
		    }
		
		System.out.println("시간으로계산 했을때 : " + Hours);
		
		double parkingFee = 0;
		
		if(Hours <= 1) {
			parkingFee = 1000;
		}else if(Hours>1 && Hours <=3) {
			parkingFee = 3000;
		}else if(Hours>3 && Hours <=5) {
			parkingFee = 5000;
		}else {
			parkingFee = 8000;
		}
		 return parkingFee;
	}
	
	public void updateParkingFee(String car_number, double parkingFee) {
		try {
			con = dataFactory.getConnection();
			String query = "UPDATE parking_record SET parking_fee=? WHERE car_number=? AND status='OUT'";
			pstmt = con.prepareStatement(query);
			pstmt.setDouble(1, parkingFee);
			pstmt.setString(2, car_number);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
