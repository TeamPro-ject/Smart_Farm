package com.smartFarm.project.controller;

import java.security.MessageDigest;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.boot.internal.SessionFactoryBuilderImpl;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.Session;
import com.smartFarm.project.model.smartFarm.*;
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
	public ModelAndView goMain(HttpServletRequest request) {
		mav = smartFarmService.movePage("home");
		return mav;
	}

	@RequestMapping(value = "/monitoring")
	public ModelAndView monitoring(HttpServletRequest request) {
		List<MonitoringVo> monitoring=monitoringRepository.findAll();
		
		mav = smartFarmService.movePage("monitoring");

		return mav;
	}

	@RequestMapping(value = "/settingChange")
	public ModelAndView settingChange(HttpServletRequest request) {
		mav = smartFarmService.movePage("settingChange");
		return mav;
	}

	@RequestMapping(value = "/explanation")
	public ModelAndView explanation(HttpServletRequest request) {
		List<MonitoringVo> monitoring=monitoringRepository.findAll();
		
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
		mav = smartFarmService.movePage("shamePoint");
		return mav;
	}

	// -------------------------------유저로그인---------------------------------------
	@RequestMapping(value = "/actionLogin")
	public ModelAndView actionLogin(@RequestParam String login_id, @RequestParam String login_password,
			@RequestParam String rememberID, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		mav = smartFarmService.actionUserLogin(login_id, login_password);
		UserVo user = new UserVo();
		if (mav.getModel().get("user") instanceof UserVo) {
			user = (UserVo) mav.getModel().get("user");
		}

		if (user != null) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60 * 60);
			session.setAttribute("loginedUser", user);

			// -------아이디 기억---------
			if (rememberID.equals("off")) {
				Cookie[] cookies = request.getCookies();
				for (int i = 0; i < cookies.length; i++) {
					String str = cookies[i].getValue();
					if (str.equals(login_id)) {
						cookies[i].setMaxAge(0);
						response.addCookie(cookies[i]);
					}
				}
			} else {
				Cookie cookie = new Cookie("rememberId", login_id);
				cookie.setMaxAge(60 * 60 * 24 * 31);
				response.addCookie(cookie);
			}
		}
		log.info(login_id + "님이 로그인 하셨습니다.");

		return mav;
	}

	// -------------------------------로그아웃---------------------------------------
	@RequestMapping(value = "/actionLogout")
	public ModelAndView actionLogout(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginedUser") != null) {
			if (session.getAttribute("loginedUser") instanceof UserVo) {
				UserVo user = (UserVo) session.getAttribute("loginedUser");
				log.info(user.getUser_id() + "님이 로그아웃 하셨습니다.");
			}
			session.removeAttribute("loginedUser");
		}

		mav = smartFarmService.movePage("mainContent");
		return mav;

	}

	// 로그인 가능여부 확인
	@ResponseBody
	@RequestMapping("/actionLoginCheck")
	public String actionLoginCheck(@RequestParam String login_id, @RequestParam String login_password)
			throws Exception {
		JSONObject json = smartFarmService.actionLoginCheck(login_id, login_password);
		return json.toString();
	}

	// 로그인한 user의 device_code 값과
	@RequestMapping("/comprison")
	public ModelAndView comprison(@RequestParam String sessionUserCode, String deviceCode,
			Principal principal, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
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
