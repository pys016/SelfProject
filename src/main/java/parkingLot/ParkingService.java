package parkingLot;

import java.util.List;

public class ParkingService {
	ParkingDAO parkingDAO;
	
	public ParkingService() {
		parkingDAO = new ParkingDAO();
	}
	
	
	public List<ParkingVO> listParking(){
		List<ParkingVO> parkingList = parkingDAO.selectAllParking();
		return parkingList;
	}
	
	public List<ParkingVO> currentParking(){
		List<ParkingVO> currentParkingList = parkingDAO.currentParkingList();
		return currentParkingList;
	}
	
	public void addEntry(ParkingVO parkingVO) {
		parkingDAO.insertAddEntry(parkingVO);
	}
}
