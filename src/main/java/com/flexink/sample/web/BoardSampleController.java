package com.flexink.sample.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.flexink.common.file.service.CommonFileService;
import com.flexink.common.utils.EditorUtils;
import com.flexink.domain.file.CommonFile;
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
	
	private String editorImgPathKey = "upload.editor";
	private String fileSavePathKey = "upload.board";
	
	/********************************************************************
	 * @메소드명	: view
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 게시판 리스트
	 ********************************************************************/
	@GetMapping("/board")
	public String view(ParamsVo params, ModelMap model) {
		model.put("list", boardSampleService.getList(params));
		model.put("type", params.getString("type", ""));
		return "/sample/boardList";
	}
	
	/********************************************************************
	 * @메소드명	: viewArticle
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 게시글 상세 (읽기 전용)
	 ********************************************************************/
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
	
	/********************************************************************
	 * @메소드명	: writeAriticle
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 글 쓰기 및 수정 페이지
	 ********************************************************************/
	@GetMapping(value="/article")
	public String writeAriticle(Board board, ParamsVo params, ModelMap model) {
		if(board.getId() != null) {
			model.put("article", boardSampleService.getArticle(board));
		}
		return "/sample/boardDetail";
	}
	
	/********************************************************************
	 * @메소드명	: saveAriticle
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 글 등록 및 수정 (AxFile 용)
	 ********************************************************************/
	@PostMapping(value="/article", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Board> saveAriticle(ParamsVo params, Board board) throws IOException {
		
		if(params.getString("secret", "").equalsIgnoreCase("true")) {
			board.setSecret(Board.Secret.Y);
		} else {
			board.setSecret(Board.Secret.N);
		}
        
        boardSampleService.saveArticle(board);
        
        // editor에 업로드된 이미지 tempDir -> 디렉토리 수정
        commonFileService.filePathUpdate(EditorUtils.getImgIds(board.getContent()), editorImgPathKey);
        
        // Axfile tempDir -> 디렉토리 수정
        if(params.getArray("fileIds") != null && params.getArray("fileIds").size() > 0) {
        	commonFileService.filePathUpdate(params.getArray("fileIds"), fileSavePathKey, board.getType(), board.getId());
        }
        
		
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}
	
	/********************************************************************
	 * @메소드명	: deleteAriticle2
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 글 삭제
	 ********************************************************************/
	@DeleteMapping(value="/article")
	public String deleteAriticle2(Board board) {
		boardSampleService.deleteArticle(board);
        return "redirect:/sample/board?type="+board.getType();
	}
	
	
	/********************************************************************
	 * @메소드명	: saveAriticle
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 글 등록 (form 전용, Input type=file 형식)
	 ********************************************************************/
	@PostMapping("/formArticle")
	public String saveAriticle(MultipartHttpServletRequest request, ParamsVo params, @Valid Board board, BindingResult bindingResult) throws IOException {
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
        
        // 파일업로드
        UploadParameters uploadParameters = new UploadParameters();
        uploadParameters.setSavePath("upload.testBoard");
        uploadParameters.setMultipartFiles(request.getFiles("file"));
        uploadParameters.setTargetType(board.getType());
        uploadParameters.setTargetId(String.valueOf(board.getId()));
        
        commonFileService.uploads(uploadParameters);
        
		
		return "redirect:/sample/board?type="+board.getType();
	}
	
	/********************************************************************
	 * @메소드명	: updateAriticle
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 글 수정  (form 전용, Input type=file 형식)
	 ********************************************************************/
	@PostMapping("/formArticle/update")
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
		
        return "redirect:/sample/viewArticle?id="+board.getId();
	}
	
	

	
	/********************************************************************
	 * @메소드명	: saveComment
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 댓글 등록
	 ********************************************************************/
	@PostMapping(value="/article/comment", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public Comment saveComment(ParamsVo params, Comment comment) {
		boardSampleService.saveComment(params, comment);
		return comment;
	}
	
	/********************************************************************
	 * @메소드명	: deleteComment
	 * @작성자	: KIMSEOKHOON
	 * @메소드 내용	: 댓글 삭제
	 ********************************************************************/
	@DeleteMapping(value="/article/comment", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public void deleteComment(@RequestBody Comment comment) {
		boardSampleService.deleteComment(comment);
	}
}
