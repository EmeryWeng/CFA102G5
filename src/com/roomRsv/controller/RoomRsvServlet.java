package com.roomRsv.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.roomOrder.model.RoomOrderService;
import com.roomOrder.model.RoomOrderVO;
import com.roomType.model.RoomTypeService;
import com.roomType.model.RoomTypeVO;
import com.util.room.SendMail;

public class RoomRsvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 從首頁搜尋，房型列表顯示符合的結果
		if ("getEnoughType".equals(action)) {
			/*************************** 1.接收請求參數 ****************************************/
			String rangedate = req.getParameter("rangedate");
			Integer qty = new Integer(req.getParameter("qty"));
			Integer guest = new Integer(req.getParameter("guest"));

			// 將收到的住宿期間分割成 起始日 和 結束日
			List<String> dateList = new LinkedList<String>();
			dateList = Arrays.asList(rangedate.split(" to "));
			String start_date = dateList.get(0);
			String end_date = dateList.get(1);

			/*************************** 2.開始查詢資料 ****************************************/
			RoomTypeService roomTypeSvc = new RoomTypeService();
			// 符合條件的
			List<RoomTypeVO> ableList = roomTypeSvc.getEnoughType(java.sql.Date.valueOf(start_date),
					java.sql.Date.valueOf(end_date), qty, guest);
			// 不符合條件的
			List<RoomTypeVO> notList = roomTypeSvc.getNotEnoughType(java.sql.Date.valueOf(start_date),
					java.sql.Date.valueOf(end_date), qty, guest);

			/*************************** 3.查詢完成,準備轉交 ************/
			HttpSession session = req.getSession();
			session.setAttribute("rangedate", rangedate);
			session.setAttribute("start_date", start_date);
			session.setAttribute("end_date", end_date);
			session.setAttribute("qty", qty);
			session.setAttribute("guest", guest); // 人數只有搜尋時會用到，選完房型改記type_no
			session.setAttribute("ableList", ableList);
			session.setAttribute("notList", notList);
			String url = "/front_end/room/roomList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交前台的roomList.jsp
			successView.forward(req, res);
		}

		// 到結帳頁
		if ("payment".equals(action)) {

			try {
				String rangedate = req.getParameter("rangedate");
				Integer type_no = new Integer(req.getParameter("type_no"));
				Integer qty = new Integer(req.getParameter("qty"));
				System.out.print("////-----" + type_no);
				System.out.print("////-----" + req.getParameter("type_no"));

				// 將收到的住宿期間分割成 起始日 和 結束日
				List<String> dateList = new LinkedList<String>();
				String[] split = rangedate.split(" to ");
				for (int i = 0; i < split.length; i++) {
					dateList.add(split[i]);
				}
				String start_date = split[0];
				String end_date = split[1];

				// 取得天數
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate = df.parse(start_date);
				Date endDate = df.parse(end_date);
				long from = startDate.getTime();
				long to = endDate.getTime();
				int days = (int) ((to - from) / (1000 * 60 * 60 * 24));

				// 再確認一次是否有空房

				/*** 值存入session(跳出結帳畫面時還能抓的到值)，同時存入req(結帳頁用，存session會被汙染)，準備轉交 ***/
				HttpSession session = req.getSession();
				session.setAttribute("rangedate", rangedate);
				session.setAttribute("start_date", start_date);
				session.setAttribute("end_date", end_date);
				session.setAttribute("qty", qty);

				req.setAttribute("type_no", type_no);
				req.setAttribute("rangedate", rangedate);
				req.setAttribute("start_date", start_date);
				req.setAttribute("end_date", end_date);
				req.setAttribute("qty", qty);
				req.setAttribute("days", days); // 只有結帳頁會用到

				String url = "/front_end/room/payment.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交前台的結帳頁
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/room/roomList.jsp");
				failureView.forward(req, res);
			}
		}

		// 產生訂單
		if ("paying".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*** 1.接收請求參數 - 輸入格式的錯誤處理 ***/
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				Integer type_no = new Integer(req.getParameter("type_no"));
				String start_date = req.getParameter("start_date");
				String end_date = req.getParameter("end_date");
				Integer days = new Integer(req.getParameter("days"));
				Integer qty = new Integer(req.getParameter("qty"));

				String title = req.getParameter("title");

				String name = req.getParameter("name");
				if (name == null || name.trim().length() == 0 || name.trim().length() > 10) {
					errorMsgs.add("姓名欄位錯誤，請重新檢查");
				}

				String phone = req.getParameter("phone");
				if (phone == null || phone.trim().length() != 10) {
					errorMsgs.add("電話欄位錯誤，請重新檢查");
				}

				String email = req.getParameter("email");
				String mailformat = "/^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$/";
				if (email == null || email.trim().length() != 10) {
					errorMsgs.add("email欄位錯誤，請重新檢查");
				} else if (!email.trim().matches(mailformat)) {
					errorMsgs.add("email請符合格式");
				}
				// 備註串接
				String note1 = req.getParameter("note1");
				if (note1 != null) {
					note1 = note1 + ",";
				} else
					note1 = "";
				String note2 = req.getParameter("note2");
				if (note2 != null) {
					note2 = note2 + ",";
				} else
					note2 = "";
				String notearea = req.getParameter("notearea");
				String note = note1 + note2 + notearea;

				// 信用卡判斷+串接
				String creditcard = req.getParameter("creditcard");
				String card_no1 = req.getParameter("card_no1");
				String card_no2 = req.getParameter("card_no2");
				String card_no3 = req.getParameter("card_no3");
				String card_no4 = req.getParameter("card_no4");
				if (creditcard.equals("addCard")) {
					creditcard = card_no1 + card_no2 + card_no3 + card_no4;
				} else if (!creditcard.matches("[0-9]{16}")) {
					errorMsgs.add("信用卡格式錯誤，請重新檢查");
				}

				// 找房價，總金額
				RoomTypeService roomTypeSvc = new RoomTypeService();
				Integer price = roomTypeSvc.getOneRoomType(type_no).getType_price();
				Integer total_price = price * qty * days;

//				if (!errorMsgs.isEmpty()) {
//					req.getRequestDispatcher("/front_end/room/payment.jsp").forward(req, res);
//					return;
//				}

				/*** 2.開始新增訂單 ***/
				// 打包訂單明細VO 失敗的自增主鍵
//				List<RoomOrderDetailVO> list = new ArrayList<RoomOrderDetailVO>();
//				RoomOrderDetailService detailSvc = new RoomOrderDetailService();
//				RoomOrderDetailVO detailVO = new RoomOrderDetailVO();
//				for (int i = 0; i < qty; i++) {
//					list.add(detailVO);
//				}
//				RoomOrderService roomorderSvc = new RoomOrderService();
//				roomorderSvc.insertAuto(mem_no, type_no, java.sql.Date.valueOf(start_date),
//						java.sql.Date.valueOf(end_date), qty, price, total_price, note, title, name, phone, email,
//						creditcard, list);

				// 分開增加 明細的ord_no不會抓
				RoomOrderService roomOrderSvc = new RoomOrderService();
				RoomOrderVO roomOrderVO = new RoomOrderVO();
				roomOrderVO = roomOrderSvc.insert(mem_no, type_no, java.sql.Date.valueOf(start_date),
						java.sql.Date.valueOf(end_date), qty, price, total_price, note, title, name, phone, email,
						creditcard);

				// 更新預約表

				// 寄送mail
				SendMail mail = new SendMail();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:m:s");
				String messageText = "訂單編號：                                         " + "📆入住期間" + start_date + " ➜ "
						+ end_date;

				mail.sendMail(email, "🌴 Feliz Hotel 🔸🟢  您的住宿預訂已確認💳", messageText);

				// 新增完訂單後記得把session的值清空
				String url = "/front_end/room/confirmation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交前台的結帳完成頁
				successView.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/room/roomList.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
