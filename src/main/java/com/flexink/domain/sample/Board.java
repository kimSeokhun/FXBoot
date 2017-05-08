package com.flexink.domain.sample;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

import com.flexink.common.domain.BaseJpaModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Board extends BaseJpaModel<Long> {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BOARD_ID")
	private Long id;

	@Column(name="TYPE", length=10)
	private String type;
	
	@NotEmpty
	@Column(name="TITLE", length=100)
	private String title;
	
	@NotEmpty
	@Column(name="CONTENT", columnDefinition="TEXT")
	private String content;
	
	@Column(name="VIEW_COUNT")
	private int viewCount;
	
	@Column(length=1)
	@Enumerated(EnumType.STRING)
	private Secret secret;
	
	@OneToMany(mappedBy="board")
	private List<Comment> commentList = new ArrayList<Comment>();

	public enum Type {
		SAMPLE
	}
	
	public enum Secret {
		Y, N
	}
	
}
