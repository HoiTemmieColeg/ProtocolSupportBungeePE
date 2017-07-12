package protocolsupport.protocol.pipeline.version.legacy.packets;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.packet.ScoreboardScore;
import protocolsupport.protocol.serializer.LegacySerializer;

public class ScoreboardScorePacket extends ScoreboardScore implements TransformedPacket {

	private String itemName;
	private byte action;
	private String scoreName;
	private int value;

	public ScoreboardScorePacket() {
	}

	public ScoreboardScorePacket(String itemName, byte action, String scoreName, int value) {
		this.itemName = itemName;
		this.action = action;
		this.scoreName = scoreName;
		this.value = value;
	}

	@Override
	public void read(ByteBuf buf) {
		itemName = LegacySerializer.readString(buf);
		action = buf.readByte();
		if (action != 1) {
			scoreName = LegacySerializer.readString(buf);
			value = buf.readInt();
		}
	}

	@Override
	public void write(ByteBuf buf) {
		LegacySerializer.writeString(itemName, buf);
		buf.writeByte(action);
		if (action != 1) {
			LegacySerializer.writeString(scoreName, buf);
			buf.writeInt(value);
		}
	}

	@Override
	public String getItemName() {
		return this.itemName;
	}

	@Override
	public byte getAction() {
		return this.action;
	}

	@Override
	public String getScoreName() {
		return this.scoreName;
	}

	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public void setItemName(final String itemName) {
		this.itemName = itemName;
	}

	@Override
	public void setAction(final byte action) {
		this.action = action;
	}

	@Override
	public void setScoreName(final String scoreName) {
		this.scoreName = scoreName;
	}

	@Override
	public void setValue(final int value) {
		this.value = value;
	}

	@Override
	public int getId() {
		return 0xCF;
	}

}
