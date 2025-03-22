package br.com.gabrielmusskopf.wbserver;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lombok.RequiredArgsConstructor;

// Structure: [ message type (1 byte) | payload length (1 byte) | payload (N bytes) ]
// payload: whiteboard rows joined by '\n'
@RequiredArgsConstructor
public class SyncMessage {

	private static final MessageAction action = MessageAction.SYNC;
	private final byte[] content;

	public byte[] serialize() {
		int contentSize = action.codeLength() + content.length;

		try {
			final var byteStream = new ByteArrayOutputStream(contentSize);
			final var out = new DataOutputStream(byteStream);

			out.writeInt(action.getCode());
			out.writeInt(content.length);
			out.write(content);

			return byteStream.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
