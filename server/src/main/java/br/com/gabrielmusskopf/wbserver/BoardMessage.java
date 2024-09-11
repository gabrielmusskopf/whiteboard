package br.com.gabrielmusskopf.wbserver;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.RequiredArgsConstructor;

/*
Ação: CONN, DISC, UPTD
IP: 255.255.255.255

<ACAO>
<IP>
 */
@RequiredArgsConstructor
public class BoardMessage {

	private static final MessageAction action = MessageAction.SYNC;
	private final byte[] content;

	public byte[] serialize() {
		int contentSize = action.name().length() + content.length + 3;

		try {
			final var byteStream = new ByteArrayOutputStream(contentSize);
			final var out = new DataOutputStream(byteStream);

			out.write(action.getCode());
			out.writeInt(content.length);
			out.write(content);

			return byteStream.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
