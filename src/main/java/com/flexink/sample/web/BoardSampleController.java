package com.flexink.sample.web;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.flexink.common.file.service.CommonFileService;
import com.flexink.domain.sample.Board;
import com.flexink.domain.sample.Comment;
import com.flexink.sample.service.BoardSampleService;
import com.flexink.vo.ParamsVo;
import com.flexink.vo.file.UploadParameters;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/sample")
public class BoardSampleController {

	@Autowired
	BoardSampleService boardSampleService;
	
	@Autowired
    private CommonFileService commonFileService;
	
	
	@GetMapping("/board")
	public String view(ParamsVo params, ModelMap model) {
		model.put("list", boardSampleService.getList(params));
		model.put("type", params.getString("type", ""));
		return "/sample/boardList";
	}
	
	@GetMapping("/viewArticle")
	public String viewArticle(Board board, ParamsVo params, ModelMap model) {
		Board article = boardSampleService.getArticle(board);
		model.put("article", article);
		model.put("comments", article.getComments());
		Collections.sort(article.getComments(), new Comparator<Comment>() {
			@Override
			public int compare(Comment o1, Comment o2) {
				return o2.getId().compareTo(o1.getId());
			}
		});
		
		return "/sample/viewArticle";
	}
	
	@GetMapping("/article")
	public String writeAriticle(Board board, ParamsVo params, ModelMap model) {
		if(board.getId() != null) {
			model.put("article", boardSampleService.getArticle(board));
		}
		return "/sample/boardDetail";
	}
	
	@Transactional
	@PostMapping("/article")
	public String saveAriticle(MultipartHttpServletRequest request, @RequestParam(value = "file") MultipartFile multipartFile, ParamsVo params, @Valid Board board, BindingResult bindingResult) throws IOException {
		if(params.getString("secret", "").equalsIgnoreCase("true")) {
			board.setSecret(Board.Secret.Y);
		} else {
			board.setSecret(Board.Secret.N);
		}
		
		// 유효성 검증
        if (bindingResult.hasErrors()) {
        	log.debug("Validator Errors : {} ", bindingResult.getAllErrors());
            return "/sample/boardDetail";
        }
        
        boardSampleService.saveArticle(board);
        
        UploadParameters uploadParameters = new UploadParameters();
        uploadParameters.setMultipartFiles(request.getFiles("file"));
        uploadParameters.setTargetType(board.getType());
        uploadParameters.setTargetId(String.valueOf(board.getId()));
        
        commonFileService.uploads(uploadParameters);
        
		
		return "redirect:/sample/board?type="+board.getType();
	}
	
	@Transactional
	@PostMapping("/article/update")
	public String updateAriticle(ParamsVo params, @Valid Board board, BindingResult bindingResult) {
		if(params.getString("secret", "").equalsIgnoreCase("true")) {
			board.setSecret(Board.Secret.Y);
		} else {
			board.setSecret(Board.Secret.N);
		}
		
		// 유효성 검증
        if (bindingResult.hasErrors()) {
        	log.debug("Validator Errors : {} ", bindingResult.getAllErrors());
            return "/sample/boardDetail";
        }
        
        boardSampleService.saveArticle(board);
		
		//return "redirect:/sample/board?type="+board.getType();
        return "redirect:/sample/viewArticle?id="+board.getId();
	}
	
	@Transactional
	@PostMapping("/article/delete")
	public String deleteAriticle(Board board) {
		System.out.println(board.getId());
        boardSampleService.deleteArticle(board);
		
		return "redirect:/sample/board?type="+board.getType();
	}
	
	
	@Transactional
	@PostMapping("/article/comment")
	@ResponseBody
	public Comment saveComment(ParamsVo params, Comment comment) {
		boardSampleService.saveComment(params, comment);
		return comment;
	}
	
	@Transactional
	@PostMapping("/article/comment/delete")
	@ResponseBody
	public void deleteComment(Comment comment) {
		boardSampleService.deleteComment(comment);
	}
}
