package br.com.gabrielmusskopf.wbserver;

import lombok.RequiredArgsConstructor;

/*
Ação: CONN, DISC, UPTD
IP: 255.255.255.255

<ACAO>
<IP>
 */
@RequiredArgsConstructor
public class BoardMessage {

	private static final MessageAction action = MessageAction.BOARD;
	private final byte[] content;

	public byte[] serialize() {
		final byte[] b = new byte[action.name().length() + content.length + 3];

		System.arraycopy(action.name().getBytes(), 0, b, 0, action.name().length());
		b[5] = '\n';
		System.arraycopy(content, 0, b, 6, content.length);
		b[b.length - 2] = '\n';
		b[b.length - 1] = '\n';

		return b;
	}

}
