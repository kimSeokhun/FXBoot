package com.flexink.domain.sample;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.flexink.common.domain.BaseJpaModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Comment extends BaseJpaModel<Long> {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="COMMENT_ID")
	private Long id;
	
	@Column(name="WRITER")
	private String writer;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="CONTENT")
	private String content;
	
	@Enumerated(EnumType.STRING)
	private Secret secret;
	
	@ManyToOne
	@JoinColumn(name="BOARD_ID")
	private Board board;
	
	public void setBoard(Board board) {
		board.addComment(this);
		this.board = board;
	}
	
	public enum Secret {
		Y, N
	}
}
