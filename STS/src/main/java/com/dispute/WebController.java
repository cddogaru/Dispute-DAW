package com.dispute;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.dispute.team.TeamRepository;
import com.dispute.tournament.Tournament;
import com.dispute.tournament.TournamentRepository;
import com.dispute.user.*;

@Controller
public class WebController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private TournamentRepository tournamentRepository;
	
	@Autowired 
	private UserComponent userComponent;
	
	@RequestMapping(value = {"/", "index"} )
	public String tournaments(Model model){
		List<Tournament> toptr = new ArrayList<Tournament>();
		for(int i = 0; i < 3; i++){
			toptr.add(0, tournamentRepository.findAll().get(i));
		}
		model.addAttribute("tournaments", toptr);
		return "index";
	}
	
	@RequestMapping("/login")
    public String login(Model model, @RequestParam(required = false) boolean loginError) {	
		model.addAttribute("loginError" , loginError);
    	return "login";
    }
	
	@RequestMapping("/image/{fileName}")
	public void handleFileDownload(Model model, @PathVariable String fileName,
			HttpServletResponse res) throws FileNotFoundException, IOException {

		File file = new File("files", fileName+".jpg");

		if (file.exists()) {
			res.setContentType("image/jpeg");
			res.setContentLength(new Long(file.length()).intValue());
			FileCopyUtils
					.copy(new FileInputStream(file), res.getOutputStream());
		} else {
			res.sendError(404, "File" + fileName + "(" + file.getAbsolutePath()
					+ ") does not exist");
		}
	}
	
	@RequestMapping(value = "/contactus")
	public String contactus() {
		return "contactus";
	}
	
}
