package com.dispute.bug;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/api")
public class BugRestController {
	interface BugListView extends Bug.BasicAtt {}
	
	@Autowired
	private BugRepository bugRepository;
	
	@JsonView(BugListView.class)
	@RequestMapping(value = "/reportbug", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Bug> getBugs() {
		return bugRepository.findAll();
	}
	
	@RequestMapping(value = "/reportbug", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public Bug newBug(@RequestBody Bug bug) {
		bugRepository.save(bug);
		return bug;
	}
}
