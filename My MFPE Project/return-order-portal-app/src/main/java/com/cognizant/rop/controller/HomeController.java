package com.cognizant.rop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.rop.model.ComponentType;
import com.cognizant.rop.model.ProcessRequest;
import com.cognizant.rop.model.ProcessResponse;
import com.cognizant.rop.service.ReturnOrderService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController implements ErrorController {

	@Autowired
	private ReturnOrderService returnOrderService;

	private static final String LOGIN="login";
	private static final String ERROR="error";
	private static final String TIMEOUT="Session Timeout . Try to login again";
	private static final String JWT="jwtToken";
	private static final String LOGOUT="Logout Successfully";
	private static final String USERNAME="userName";
	
	
	@GetMapping("/login")
	public ModelAndView getLogin() {
		log.info("accessing /login  is started");
		ModelAndView map = new ModelAndView();
		map.addObject(ERROR, "");
		map.setViewName(LOGIN);
		log.info("accessing /login  is ended");
		return map;
	}

	@GetMapping("/userLogout")
	public ModelAndView logout(HttpSession session) {
		log.info("accessing /userLogout  is started");
		ModelAndView map = new ModelAndView();
		String token = (String) session.getAttribute(JWT);
		log.info("*******************" + token + "********************");
		session.setAttribute(JWT,"");
		log.info("-----------"+session);
		map.addObject(ERROR, "");
		map.setViewName(LOGIN);
		log.info("accessing /userLogout  is ended");
		map.addObject(ERROR,LOGOUT);
		return map;
	}

	@PostMapping("/processRequest")
	public ModelAndView getProcessRequest(@RequestParam("userName") String userName,
			@RequestParam("password") String password, HttpSession session) {
		log.info("accessing /processRequest is started");
		ModelAndView map = new ModelAndView();
		boolean isValid = returnOrderService.authenticateUser(userName, password, session);
		if (!isValid) {
			map.addObject(ERROR, "Your authentication information is incorrect. Please try again.");
			map.setViewName(LOGIN);
			log.info("accessing login.jsp  as Your authentication information is incorrect. Please try again.");
			return map;
		}

		map.addObject(USERNAME, userName);
		map.setViewName("processRequest");
		log.info("accessing /processRequest  is ended");
		return map;
	}

	@PostMapping("/addprocessRequest")
	public String addProcessRequest() {
		log.info("accessing /addProcessRequest is started");
		return "confirmation";
	}


	@PostMapping("/requestProcessing")
	public ModelAndView setProcessRequest(@RequestParam("userName") String username,
			@RequestParam("contactNumber") long contactNumber, @RequestParam("creditCardNumber") long creditCardNumber,
			@RequestParam("componentName") String componentName, @RequestParam("componentType") String componentType,
			@RequestParam("quantity") int quantity, @RequestParam("isPriorityRequest") boolean isPriorityRequest,
			HttpSession session) {

		log.info("accessing /requestProcessing  is started");
		ModelAndView map = new ModelAndView();
		if (!session.getAttribute(JWT).toString().isEmpty())
		{
			String token = (String) session.getAttribute(JWT);
			log.info("-------------------------------------------");
			log.info(username + "," + contactNumber + "," + creditCardNumber + "," + componentName + ","
					+ ComponentType.valueOf(componentType) + "," + quantity + "," + isPriorityRequest);

			ComponentType newComponentType = ComponentType.valueOf(componentType);
			ProcessRequest processRequest = new ProcessRequest(username, contactNumber, creditCardNumber, componentName,
					newComponentType, quantity, isPriorityRequest);
			log.info("Process Request :" + processRequest);
			ProcessResponse processResponse = returnOrderService.getResponse(processRequest, token);
			log.info("--------------------------------------Return Order Portal:- " + processResponse);
			if (processResponse == null) {
				map.addObject(ERROR,TIMEOUT);
				map.setViewName(LOGIN);
				return map;
			}

			log.info("Process Response" + processResponse);
			map.addObject("processResponse", processResponse);
			map.addObject(USERNAME,username);
			map.setViewName("processResponse");
		}
		else
		{
			 log.info("session is null");
			 map.addObject(ERROR,LOGOUT);
			 map.setViewName(LOGIN);
		}
		log.info("accessing /requestProcessing  is ended");
		return map;
	}

	
	@PostMapping("/payment")
	public ModelAndView doPayment(@RequestParam("packagingAndDeliveryCharge") String strPackagingAndDeliveryCharge,
			@RequestParam("processingCharge") String strProcessingCharge, @RequestParam("userId") String strUserId,
			@RequestParam("requestId") String strRequestId, 
			@RequestParam("userName") String userName ,
			HttpSession session) {
		log.info("accessing /payment  is started");
		ModelAndView model = new ModelAndView();
		if (!session.getAttribute(JWT).toString().isEmpty()) {
			int userId = Integer.parseInt(strUserId);
			int requestId = Integer.parseInt(strRequestId);
			double packagingAndDeliveryCharge = Double.parseDouble(strPackagingAndDeliveryCharge);
			double processingCharge = Double.parseDouble(strProcessingCharge);

			double creditLimit = returnOrderService.getCreditLimit();
			double totalCharge = packagingAndDeliveryCharge + processingCharge;

			String token = (String) session.getAttribute(JWT);
			ProcessRequest request = returnOrderService.getRequest(userId, token);
			if (request == null) {
				model.addObject(ERROR,TIMEOUT);
				model.setViewName(LOGIN);
				return model;
			}

			long creditCardNumber = request.getCreditCardNumber();
			double currentBalance = -1;

			try {
				currentBalance = returnOrderService.getTotalPayment(requestId, creditCardNumber,
						totalCharge, token);
			} catch (HttpClientErrorException e) {
				model.addObject("code", e.getRawStatusCode());
				model.addObject("message", e.getStatusCode());
				model.setViewName(ERROR);
				return model;
			}

			if (currentBalance == -1) {
				model.addObject(ERROR,TIMEOUT);
				model.setViewName(LOGIN);
				return model;
			}

			model.addObject("request", request);
			model.addObject("totalCharge", totalCharge);
			model.addObject("requestId", requestId);
			model.addObject("creditLimit", creditLimit);
			model.addObject("currentBalance", currentBalance);
			model.addObject(USERNAME,userName);
			model.setViewName("payment");

			log.info("Request :-" + request);
		} else {
			 log.info("session is null");
			 model.addObject(ERROR,LOGOUT);
			 model.setViewName(LOGIN);
		}
		log.info("accessing /payment  is ended");
		return model;
	}

	
	@PostMapping("/confirmation")
	public ModelAndView getSucces(@RequestParam("userName") String userName,
			@RequestParam("totalCharge") String totalCharge, @RequestParam("requestId") String requestId,
			HttpSession session) {
		log.info("accessing /confirmation  is started");
		ModelAndView model = new ModelAndView();
		if (!session.getAttribute(JWT).toString().isEmpty()) {
		  log.info("session is not null");
			model.addObject(USERNAME, userName);
			model.addObject("totalCharge", totalCharge);
			model.addObject("requestId", requestId);
			model.setViewName("confirmation");
		} else {
			 log.info("session is  null");
			 model.addObject(ERROR,LOGOUT);
			 model.setViewName(LOGIN);
		}
		log.info("accessing /confirmation  is started");
		return model;
	}

	@Override
	public String getErrorPath() {
		return ERROR;
	}

	@GetMapping("/error")
	public String getError() {
		return ERROR;
	}
}
