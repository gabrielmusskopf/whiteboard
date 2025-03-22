package br.com.gabrielmusskopf.wbserver;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageAction {
	SYNC(1);

	private final int code;

	public int codeLength() {
		return Integer.BYTES;
	}

}
