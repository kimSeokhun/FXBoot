package com.flexink.domain.board;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.flexink.common.domain.BaseJpaModel;
import com.flexink.domain.file.CommonFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@ToString(exclude={"comments"})
public class Board extends BaseJpaModel<Long> {

	public Board(Long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BOARD_ID")
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TYPE")
	private BoardType type;
	
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
	
	@Enumerated(EnumType.STRING)
	@Column(name="DEL_YN", length=1)
	private DelYn delYn = DelYn.N;
	
	@OneToMany(mappedBy="board", orphanRemoval=true, cascade=CascadeType.ALL)
	@JsonBackReference
	private List<Comment> comments = new ArrayList<Comment>();
	
	@Transient
	private List<CommonFile> files = new ArrayList<CommonFile>();
	
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	public enum Type {
		SAMPLE
	}
	
	public enum Secret {
		Y, N
	}
	
	public enum DelYn {
		Y, N
	}
	
}
