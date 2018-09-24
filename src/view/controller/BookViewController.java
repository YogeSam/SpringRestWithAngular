package view.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/view")
public class BookViewController {
	
	@RequestMapping(value = "/Book")
	public ModelAndView  listView(){
		return new ModelAndView("book"); 
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
