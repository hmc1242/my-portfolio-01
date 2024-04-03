package com.servicecenter.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servicecenter.dao.ProductDAO;
import com.servicecenter.dto.ProductDTO;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/Product.do")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ProductDAO pdao = ProductDAO.getInstance();
		List<ProductDTO> productlist = null;
		String producttype = null;
		RequestDispatcher dispatcher = null;
		
		String productmode = (String)request.getParameter("product_mode");
		dispatcher = request.getRequestDispatcher("/products/product");
		switch(productmode) {
			case "monitor":
				productlist = pdao.selectProducts("monitor");
				producttype = "모니터";
				break;
			case "keyboard":
				productlist = pdao.selectProducts("keyboard");
				producttype = "키보드";
				break;
			case "mouse":
				productlist = pdao.selectProducts("mouse");
				producttype = "마우스";
				break;
		}
		request.setAttribute("productlist", productlist);
		request.setAttribute("product_type", producttype);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
