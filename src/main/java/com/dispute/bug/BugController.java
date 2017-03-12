package com.dispute.bug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BugController {
	@Autowired
	private BugRepository bugRepository;
	
	@RequestMapping(value = "/reportbug")
	public String reportbug() {
		return "reportbug";
	}
	
	@RequestMapping(value = "/reportbug", method = RequestMethod.POST)
	public String newReportbug(@RequestParam String description) {
		if (!description.trim().isEmpty()) {
			bugRepository.save(new Bug(description));
		}
		
		return "reportbug";
	}
}
