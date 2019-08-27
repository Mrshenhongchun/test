package com.cy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SysRole implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -356538509994150709L;
	private Integer id;
	@NotBlank(message = "用户名不能为空")
	private String name;
	private String note;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;

}
