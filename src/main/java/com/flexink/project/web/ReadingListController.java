package com.flexink.project.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flexink.project.config.AmazonProperties;
import com.flexink.project.domain.Book;
import com.flexink.project.domain.Reader;
import com.flexink.project.domain.ReadingListRepository;

@Controller
@RequestMapping("/")
public class ReadingListController {

	//private static final String reader = "craig";
	
	private ReadingListRepository readingListRepository;
	private AmazonProperties amazonProperties;
	
	@Autowired
	public ReadingListController(ReadingListRepository readingListRepository, AmazonProperties amazonProperties) {
		this.readingListRepository = readingListRepository;
		this.amazonProperties = amazonProperties;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String readersBooks(Reader reader, Model model) {
		List<Book> readingList = readingListRepository.findByReader(reader);
		System.out.println(readingList);
		if(readingList != null) {
			model.addAttribute("books", readingList);
			model.addAttribute("reader", reader);
			model.addAttribute("amazonID", amazonProperties.getAssociateId());
		}
		return "readingList";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String addToReadingList(Reader reader, Book book) {
		System.out.println(book);
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/";
	}
	
}
