package project.web.dto;

import java.io.Serializable;

import project.common.entity.Base;

public class ResponseDTO<T extends Base> implements Serializable {
	private boolean success;
	private String message;
	private T data;
}
