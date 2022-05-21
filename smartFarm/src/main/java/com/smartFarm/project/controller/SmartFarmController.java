package com.smartFarm.project.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.smartFarm.project.model.smartFarm.*;
import com.smartFarm.project.security.CustomUserDetails;
import com.smartFarm.project.service.SmartFarmService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class SmartFarmController {

	@Autowired
	SmartFarmRepository smartFarmRepository;

	@Autowired
	SmartFarmService smartFarmService;

	@Autowired
	MonitoringRepository monitoringRepository;

	@Autowired
	UserRepository userRepository;

	ModelAndView mav;

	@RequestMapping(value = "/home")
	public ModelAndView home(HttpServletRequest request) {
		mav = smartFarmService.movePage("home");
		return mav;
	}
	@GetMapping(value = "/home")
	public ModelAndView homeLogin(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception) {
		mav = smartFarmService.movePage("home");
		mav.addObject("error", error);
		mav.addObject("exception", exception);
		return mav;
	}


//	@RequestMapping(value = "/loginPage")
//	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
//			@RequestParam(value = "exception", required = false) String exception) {
//		mav = smartFarmService.movePage("home");
//		mav.addObject("error", error);
//		mav.addObject("exception", exception);
//		log.info("loginForm view resolve");
//		return mav;
//	}

	@RequestMapping(value = "/monitoring")
	public ModelAndView monitoring(HttpServletRequest request) {
		List<MonitoringVo> monitoring = monitoringRepository.findByDeviceCode("sm01");
		mav = smartFarmService.movePage("monitoring");
		mav.addObject("monitoringData", monitoring);
		return mav;
	}

	@RequestMapping(value = "/settingChange")
	public ModelAndView settingChange(HttpServletRequest request) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CustomUserDetails userDetails = (CustomUserDetails) principal;
		String username = userDetails.getUsername();
		String password = userDetails.getPassword();
		log.info(username);
		log.info(password);

		HttpSession session = request.getSession();
		log.info(session.getId());

		mav = smartFarmService.movePage("arduino");
		return mav;
	}

	@RequestMapping(value = "/explanation")
	public ModelAndView explanation(HttpServletRequest request) {
		mav = smartFarmService.movePage("explanation");
		return mav;
	}

	@RequestMapping(value = "/photo")
	public ModelAndView photo(HttpServletRequest request) {
		mav = smartFarmService.movePage("photo");
		return mav;
	}

	@RequestMapping(value = "/teamRole")
	public ModelAndView teamRole(HttpServletRequest request) {
		mav = smartFarmService.movePage("teamRole");
		return mav;
	}

	@RequestMapping(value = "/shamePoint")
	public ModelAndView shamePoint(HttpServletRequest request) {
		List<MonitoringVo> monitoring = monitoringRepository.findByDevice_CodeDescLimit("sm01",24);
		mav = smartFarmService.movePage("shamePoint");
		mav.addObject("monitoringData", monitoring);
		return mav;
	}

	// 로그인 가능여부 확인
	@ResponseBody
	@RequestMapping(value = "/loginCheck", method = { RequestMethod.POST })
	public String actionLoginCheck(@RequestParam Map<String, Object> paramMap) throws Exception {
		log.info("컨트");
		JSONObject json = smartFarmService.actionLoginCheck(paramMap.get("user_id").toString(),
				paramMap.get("user_password").toString());
		return json.toString();
	}

	// 로그인한 user의 device_code 값과
	@RequestMapping("/comprison")
	public ModelAndView comprison(@RequestParam String sessionUserCode, String deviceCode, Principal principal,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		/*
		 * if (mav.getModel().get("monitoring") instanceof MonitoringVo) { monitoring =
		 * (MonitoringVo) mav.getModel().get("monitoring"); }
		 */
		/*
		 * MonitoringVo monitoring = new MonitoringVo(); String device_Code = (String)
		 * monitoring.getDevice_code(); String sessionDevice_Code=principal.getName();
		 * System.out.printf(device_Code,sessionDevice_Code); if(sessionDevice_Code !=
		 * null) { mav =
		 * smartFarmService.userComparison(device_Code,sessionDevice_Code); }
		 */
		return mav;
	}

}
