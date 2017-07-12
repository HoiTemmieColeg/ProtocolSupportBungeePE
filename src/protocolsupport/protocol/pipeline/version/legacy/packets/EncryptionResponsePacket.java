package protocolsupport.protocol.pipeline.version.legacy.packets;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.connection.InitialHandler;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.packet.EncryptionResponse;
import protocolsupport.protocol.serializer.LegacySerializer;

public class EncryptionResponsePacket extends EncryptionResponse implements TransformedPacket {

	private byte[] sharedSecret = new byte[0];
	private byte[] verifyToken = new byte[0];

	@Override
	public void read(ByteBuf buf) {
		sharedSecret = LegacySerializer.readArray(buf);
		verifyToken = LegacySerializer.readArray(buf);
	}

	@Override
	public void write(ByteBuf buf) {
		LegacySerializer.writeArray(sharedSecret, buf);
		LegacySerializer.writeArray(verifyToken, buf);
	}

	@Override
	public byte[] getSharedSecret() {
		return this.sharedSecret;
	}

	@Override
	public byte[] getVerifyToken() {
		return this.verifyToken;
	}

	@Override
	public void setSharedSecret(final byte[] sharedSecret) {
		this.sharedSecret = sharedSecret;
	}

	@Override
	public void setVerifyToken(final byte[] verifyToken) {
		this.verifyToken = verifyToken;
	}

	@Override
	public int getId() {
		return 0xFC;
	}

	@Override
	public void handle(AbstractPacketHandler handler) throws Exception {
		((InitialHandler) handler).unsafe().sendPacket(new EncryptionResponsePacket());
		handler.handle(this);
	}

}
