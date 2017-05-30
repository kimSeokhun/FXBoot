package com.flexink.domain.board;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
	
	@Column(name="WRITER", length=50)
	private String writer;
	
	@Column(name="PASSWORD", length=100)
	private String password;
	
	@Column(name="CONTENT", columnDefinition="TEXT", nullable=false)
	private String content;
	
	@Enumerated(EnumType.STRING)
	@Column(name="SECRET", length=1)
	private Secret secret;
	
	@Enumerated(EnumType.STRING)
	@Column(name="DEL_YN", length=1)
	private DelYn delYn = DelYn.N;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="BOARD_ID")
	private Board board;
	
	public void setBoard(Board board) {
		board.addComment(this);
		this.board = board;
	}
	
	public enum Secret {
		Y, N
	}
	
	public enum DelYn {
		Y, N
	}
}
