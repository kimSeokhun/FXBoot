package com.flexink.project.web;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flexink.project.config.AmazonProperties;
import com.flexink.project.dao.CityMapper;
import com.flexink.project.domain.Book;
import com.flexink.project.domain.Reader;
import com.flexink.project.domain.ReadingListRepository;

@Controller
@RequestMapping("/reading")
public class ReadingListController {

	//private static final String reader = "craig";
	
	private ReadingListRepository readingListRepository;
	private AmazonProperties amazonProperties;
	
	private final CityMapper cityMapper;
	
	@Autowired
	public ReadingListController(ReadingListRepository readingListRepository, AmazonProperties amazonProperties, CityMapper cityMapper) {
		this.readingListRepository = readingListRepository;
		this.amazonProperties = amazonProperties;
		this.cityMapper = cityMapper;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String readersBooks(Reader reader, Model model) {
		List<Book> readingList = readingListRepository.findByReader(reader);
		if(readingList != null) {
			model.addAttribute("books", readingList);
			model.addAttribute("reader", reader);
			model.addAttribute("amazonID", amazonProperties.getAssociateId());
		}
		return "readingList";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String addToReadingList(Reader reader, Book book) {
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/";
	}
	
	@RequestMapping(value="/mybatis", method=RequestMethod.GET)
	@ResponseBody
	public String myBatis() {
		HashMap<String, Object> result = cityMapper.findByReader();
		return result.toString();
	}
	
}
