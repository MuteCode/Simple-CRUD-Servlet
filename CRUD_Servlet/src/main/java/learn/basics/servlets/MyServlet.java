package learn.basics.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import learn.basics.beans.Staff;
import learn.basics.dao.StaffDAO;

@WebServlet("/staff")
public class MyServlet extends HttpServlet{
	/**
	 * For serialization
	 */
	private static final long serialVersionUID = -1364306109822893548L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Servlet doGet running ....");
		req.getRequestDispatcher("view.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Servlet doPost running ...");
		String actValue = req.getParameter("action");
		
		Staff staff = new Staff();
		StaffDAO dao = new StaffDAO();
		if(actValue != null) {
			staff.setId(Integer.parseInt(req.getParameter("id")));
			staff.setName(req.getParameter("name"));
			staff.setLanguage(req.getParameter("language"));
		}
		
		
		if(actValue.equals("Create")) {
			System.out.println("Creating new staff...");
			int rs = dao.creatStaff(staff);
			System.out.println(rs + " new staff was created.");
		} else if (actValue.equals("Save")) {
			System.out.println("Editing a staff...");
			dao.updateStaff(staff);
			System.out.println("Update staff success");
		} else if (actValue.equals("Delete")) {
			System.out.println("Deleting a staff...");
			dao.deleteStaff(Integer.parseInt(req.getParameter("id")));
			System.out.println("Delete staff success");
		}
//		System.out.println(req.getContextPath());
		resp.sendRedirect(req.getContextPath());
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("Initial for MyServlet...");
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("Something happening...at detroy servlet");
	}
}
