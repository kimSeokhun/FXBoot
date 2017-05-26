package com.flexink.domain.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.flexink.common.domain.BaseJpaModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="BOARD_TYPE")
public class BoardType extends BaseJpaModel<String> {
	
	public BoardType(String type) {
		this.type = type;
	}

	@Id
	@Column(name="TYPE", length=10)
	private String type;
	
	@Column(length=100)
	private String name;
	
	@Column
	private String desc;

	@Override
	public String getId() {
		return this.type;
	}
	
}
