package view.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/view")
public class BookViewController {
	
	@RequestMapping(value = "/Book")
	public ModelAndView  listView(){
		ModelAndView m = new ModelAndView("book");
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String name = user.getUsername(); //get logged in username
	    m.addObject("username", name);
		return m; 
	}
	
	@RequestMapping(value = "/Book/Add")
	public ModelAndView  addView(){
		return new ModelAndView("bookAdd"); 
	}
	
	@RequestMapping(value = "/Book/Update")
	public ModelAndView  updateView(){
		return new ModelAndView("bookUpdate"); 
	}
	
	
	
}
