package parkingLot;

import java.util.Date;
import java.util.List;

public class ParkingService {
	ParkingDAO parkingDAO;
	
	public ParkingService() {
		parkingDAO = new ParkingDAO();
	}
	
	//주차리스트기능
	public List<ParkingVO> listParking(){
		List<ParkingVO> parkingList = parkingDAO.selectAllParking();
		return parkingList;
	}
	
	public List<ParkingVO> currentParking(){
		List<ParkingVO> currentParkingList = parkingDAO.currentParkingList();
		return currentParkingList;
	}
	//차량번호,주차장소 중복 확인
	public int checkDuplicate(String car_number, String car_area) {
		return parkingDAO.checkDuplicateEntry(car_number, car_area);
	}
	
	//입차기능
	public void addEntry(ParkingVO parkingVO) {
		parkingDAO.insertAddEntry(parkingVO);
	}
	
	//출차기능
	public ParkingVO findCar(String car_number) {
		return parkingDAO.findCarNumber(car_number);
	}
	
	public void addExit(String car_number) {
		// 출차 등록
		parkingDAO.updateExit(car_number);
		//출차 후 요금 계산
		System.out.println("22222222222222");
		parkingFee(car_number);
	}
	//주차요금
	public double parkingFee(String car_number) {
		ParkingVO parkingVO = parkingDAO.findCarNumber(car_number);
		System.out.println("pakingFee : " +parkingVO.getStatus());
		System.out.println("pakingFee : " +parkingVO.getEntry_log());
		if (parkingVO != null) {
			java.util.Date entry_log_util = parkingVO.getEntry_log();
			java.util.Date exit_log_util = new java.util.Date();
			
			java.sql.Date entry_log = new java.sql.Date(entry_log_util.getTime());
			java.sql.Date exit_log = new java.sql.Date(exit_log_util.getTime());
			System.out.println("entry_log : " + entry_log);
			System.out.println("exit_log : " + exit_log);
			
			double parkingFee = parkingDAO.parkingTimeFee(entry_log, exit_log);
			
			parkingDAO.updateParkingFee(car_number, parkingFee);
			
			return parkingFee;
		}
		return 0;
	}
}
