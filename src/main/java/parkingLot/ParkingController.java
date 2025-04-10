package parkingLot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class ParkingController
 */
@WebServlet("/parkingLot/*")
public class ParkingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ParkingService parkingService;
	ParkingVO parkingVO;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		parkingService = new ParkingService();
		parkingVO = new ParkingVO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action : " + action);
		try {
				// 주차리스트
			List<ParkingVO> parkingList = new ArrayList<ParkingVO>();
			if(action == null) {
				parkingList = parkingService.listParking();
				request.setAttribute("parkingList", parkingList);
				nextPage = "/parking/listEntry.jsp";
			}else if(action.equals("/listParking.do")) {
				parkingList = parkingService.listParking();
				request.setAttribute("parkingList", parkingList);
				nextPage = "/parking/listEntry.jsp";
				// 입차등록
			}else if(action.equals("/entryForm.do")) {
				nextPage = "/parking/entryForm.jsp";
			}else if(action.equals("/addEntry.do")) {
				String car_number = request.getParameter("car_number");
				String park_area = request.getParameter("park_area");
				
				int duplicateStatus = parkingService.checkDuplicate(car_number, park_area);
				if(duplicateStatus == 1) {
					request.setAttribute("msg", "이미 주차된 차량번호입니다");
					nextPage = "/parking/entryForm.jsp";
				}else if(duplicateStatus == 2) {
					request.setAttribute("msg", "이미 사용 중인 주차장소입니다");
					nextPage = "/parking/entryForm.jsp";
				}else if (duplicateStatus == 3) {
			        // 둘 다 중복
			        request.setAttribute("msg", "차량번호와 주차장소가 모두 중복됩니다.");
			        nextPage = "/parking/entryForm.jsp";
				}else {
					
				ParkingVO parkingVO = new ParkingVO();
				parkingVO.setCar_number(car_number);
				parkingVO.setPark_area(park_area);
				parkingService.addEntry(parkingVO);
				//request.setAttribute("msg", "addEntry");
				//nextPage = "/parkingLot/listParking.do";
				response.sendRedirect(request.getContextPath() + "/parkingLot/listParking.do?msg=addEntry");
				return;
				}
				//출차등록
			}else if(action.equals("/findExitForm.do")) {
				nextPage = "/parking/findExitForm.jsp";
			}else if(action.equals("/findCar.do")) {
				String car_number = request.getParameter("car_number");
				ParkingVO parkingVO = parkingService.findCar(car_number);
				request.setAttribute("findCar", parkingVO);
				nextPage = "/parking/exitConfirm.jsp";
			}else if(action.equals("/addExit.do")) {
				String car_number = request.getParameter("car_number");
				
				parkingService.addExit(car_number);
				response.sendRedirect(request.getContextPath() + "/parkingLot/listParking.do?msg=addExit");
				System.out.println("33333333333");
				return;
				
			}
			/*
			 * else if(action.equals("/feeParkingTime.do")){
				String car_number = request.getParameter("car_number");
				ParkingVO parkingVO = parkingService.findCar(car_number);
				
				if(parkingVO != null) {
					double parkingFee = parkingService.parkingFee(car_number);
                    request.setAttribute("parkingFee", parkingFee);
                    nextPage = "/parking/displayParkingFee.jsp";
				}
			}
			 */
			
			
			
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
