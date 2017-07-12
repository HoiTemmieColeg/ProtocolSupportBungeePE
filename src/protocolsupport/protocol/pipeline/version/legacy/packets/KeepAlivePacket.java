package protocolsupport.protocol.pipeline.version.legacy.packets;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.packet.KeepAlive;

public class KeepAlivePacket extends KeepAlive implements TransformedPacket {

	private int randomId;

	public KeepAlivePacket() {
	}

	public KeepAlivePacket(int randomId) {
		this.randomId = randomId;
	}

	@Override
	public void read(ByteBuf buf) {
		randomId = buf.readInt();
	}

	@Override
	public void write(ByteBuf buf) {
		buf.writeInt(randomId);
	}

	@Override
	public int getRandomId() {
		return this.randomId;
	}

	@Override
	public void setRandomId(int randomId) {
		this.randomId = randomId;
	}

	@Override
	public int getId() {
		return 0x00;
	}

}
