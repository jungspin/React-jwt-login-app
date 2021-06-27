package com.cos.authjwt.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 모든 응답을 이 객체로 한다
public class CMRespDTO<T> {
	// 모든 응답을 이 클래스로 받는다 -> 파싱하기 ㅎ편ㄴ함
		private Integer code;
		private String msg;
		private T data;
}
