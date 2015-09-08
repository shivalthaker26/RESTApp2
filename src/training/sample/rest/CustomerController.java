package training.sample.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import training.sample.dao.CustomerDAO;
import training.sample.exceptions.AppException;
import training.sample.model.Customer;

@Path("/customer")
public class CustomerController
{
   @GET
   @Path("/all")
   @Produces(MediaType.APPLICATION_JSON)
	public AppResponse getAll()
	{
		AppResponse resp = new AppResponse();
		
		try
		{
		CustomerDAO dao = new CustomerDAO();
		List<Customer> custList = dao.getAll();
		resp.setPayload(custList);
		}
		catch(AppException e)
		{
			e.printStackTrace();
			
			resp.setMessage(e.getMessage());
		}
		return resp;
		
	}
   
   
   @GET
	@Path("/get/{conf_num}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getPerson(@PathParam("conf_num") String conf_num)
	{
		AppResponse resp = new AppResponse();
		
		try
		{
		CustomerDAO dao = new CustomerDAO();
		Customer cust = dao.getPerson(conf_num);
		resp.setPayload(cust);
		}
		catch(AppException e)
		{
			e.printStackTrace();
			
			resp.setMessage(e.getMessage());
		}
		return resp;
		
		
	}
   @POST
   @Path("/add")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public AppResponse addCustomer(Customer cust)
   {
	   AppResponse resp = new AppResponse();
		
		try
		{
		CustomerDAO dao = new CustomerDAO();
		cust = dao.addCustomer(cust);
		resp.setMessage("Customer has been addded to the database");
		resp.setPayload(cust);
		}
		catch(AppException e)
		{
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);			
			resp.setMessage(e.getMessage());
		}
		return resp;
   }
   
   
   @POST
   @Path("/update")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public AppResponse updateCustomer(Customer cust)
   {
	   AppResponse resp = new AppResponse();
		
		try
		{
		CustomerDAO dao = new CustomerDAO();
		cust = dao.updateCustomer(cust);
		resp.setMessage("Customer has been updated into the database");
		resp.setPayload(cust);
		}
		catch(AppException e)
		{
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);			
			resp.setMessage(e.getMessage());
		}
		return resp;
   }
   
   @GET
   @Path("/delete/{confirmationNumber}")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public AppResponse deleteCustomer(@PathParam("confirmationNumber") String confirmationNumber)
   {
	   AppResponse resp = new AppResponse();
		
		try
		{
		CustomerDAO dao = new CustomerDAO();
		Customer cust = dao.deleteCustomer(confirmationNumber);
		resp.setMessage("Customer has been deleted from the database");
		resp.setPayload(cust);
		}
		catch(AppException e)
		{
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);			
			resp.setMessage(e.getMessage());
		}
		return resp;
   }
   
   
   
}