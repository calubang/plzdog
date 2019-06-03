package com.plzdaeng.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plzdaeng.dto.UserDto;
import com.plzdaeng.user.dao.PetDao;
import com.plzdaeng.user.service.PetService;

@WebServlet("/petnamecheck")
public class PetNameCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PetService service;
	
    public PetNameCheckServlet() {
        service = new PetService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String petName = request.getParameter("petname");
		UserDto user = (UserDto)request.getSession().getAttribute("userInfo");
		System.out.println("petnamecheck : " + petName);
		
		//DB 통신
		int result = service.petNameCheck(petName, user.getUser_id());
		request.setAttribute("result", result);
		System.out.println(result);
		String path = "/user/result/petnamecheckresult.jsp";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
