package com.activityImage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import com.activityImage.model.ActivityImageService;
import com.activityImage.model.ActivityImageVO;

@MultipartConfig(maxFileSize=5*1024*1024,maxRequestSize=5*5*1024*1024)
public class ActivityImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("img/jpeg");
		String act_img_no = request.getParameter("act_img_no");
		ActivityImageService actImageService = new ActivityImageService();
		byte[] imgArray = actImageService.getActImageByPk(new Integer(act_img_no)).getAct_img();
		ServletOutputStream out = response.getOutputStream();
		out.write(imgArray);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String actNo = request.getParameter("actNo");
		String action = request.getParameter("action");
		List<String> error = new ArrayList<>();
		ActivityImageService actImageService = new ActivityImageService();
		
		//上傳圖片
		if ("addImg".equals(action)) {
			Part part = request.getPart("actImg");
			try {
				if (part.getSize() == 0) {
					error.add("請選擇圖片");
					request.setAttribute("error", error);
					request.getRequestDispatcher("/front_end/activity/actImg/addActImg.jsp").forward(request, response);

					return;
				}

				byte[] imgArray = new byte[part.getInputStream().available()];
				BufferedInputStream buf = new BufferedInputStream(part.getInputStream());
				buf.read(imgArray);
				buf.close();

				ActivityImageVO actImgVO = actImageService.addActImage(new Integer(actNo), imgArray);
				request.setAttribute("actImgVO", actImgVO);
				request.getRequestDispatcher("/front_end/activity/actImg/showImages.jsp").forward(request, response);

				return;
				
			} catch (Exception ex) {
				error.add("新增失敗" + ex.getMessage());
				request.setAttribute("error", error);
				request.getRequestDispatcher("/front_end/activity/actImg/addActImg.jsp").forward(request, response);
			}
		}
		
		//刪除圖片
		if("deleteImg".equals(action)) {
			String actImgNo = request.getParameter("actImgNo");
			actImageService.deleteActImage(new Integer(actImgNo));
			List<ActivityImageVO> actImgList = actImageService.getAll();
			request.setAttribute("actImgList",actImgList);
			
			request.getRequestDispatcher("/front_end/activity/actImg/showImages.jsp")
			.forward(request, response);
			
			return;
		}
		
		//欲更新圖片的ID
		if("updateActImgNo".equals(action)) {
			Integer actImgNo = new Integer(request.getParameter("actImgNo"));
			
			request.setAttribute("actImgNo", actImgNo);
			
			request.getRequestDispatcher("/front_end/activity/actImg/updateActImg.jsp")
			.forward(request, response);
		}
		
		//將該ID做修改
		if("updateImg".equals(action)) {
			Part part = request.getPart("actImg");
			
			String actImgNo = request.getParameter("actImgNo");

			byte[] imgArray = null;
			// 修改時 檢查有無選擇圖片，若沒有保持原圖
			if(part.getInputStream().available() > 0) {			
				InputStream in = part.getInputStream();
				imgArray = new byte[in.available()];
				in.read(imgArray);
			}else {
				imgArray = actImageService.getActImageByPk(new Integer(actImgNo)).getAct_img();
			}
			
			
			actImageService.updateActImage(new Integer(actImgNo), new Integer(actNo), imgArray);
			List<ActivityImageVO> actImgList = actImageService.getAll();
			request.setAttribute("actImgList",actImgList);
			
			request.getRequestDispatcher("/front_end/activity/actImg/showImages.jsp")
			.forward(request, response);
			
			return;
		}
		
		//查看全部
		if("getAllImg".equals(action)) {
			List<ActivityImageVO> actImgList = actImageService.getAll();
			String[] base64Img = new String[actImgList.size()];
			for(int index=0;index<base64Img.length;index++) {
				base64Img[index] = Base64.getEncoder().encodeToString(actImgList.get(index).getAct_img());
			}
			
			request.setAttribute("actImgList",actImgList);
			
			request.getRequestDispatcher("/front_end/activity/actImg/showImages.jsp")
			.forward(request, response);
			
			return;
		}

	}

}
