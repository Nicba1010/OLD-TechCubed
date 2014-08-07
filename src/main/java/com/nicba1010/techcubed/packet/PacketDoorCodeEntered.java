package com.nicba1010.techcubed.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

import com.nicba1010.techcubed.util.LogHelper;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketDoorCodeEntered implements IMessage {
	int x, y, z;
	String code;

	public PacketDoorCodeEntered() {
		// TODO Auto-generated constructor stub
	}

	public PacketDoorCodeEntered(int x, int y, int z, String code) {
		LogHelper.debug("Constructiong");
		this.x = x;
		this.y = y;
		this.z = z;
		this.code = code;
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		code = ByteBufUtils.readUTF8String(buffer);
		LogHelper.debug("fromBytes");
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		ByteBufUtils.writeUTF8String(buffer, code);
		LogHelper.debug("toBytes");
	}

	public static class Handler implements
			IMessageHandler<PacketDoorCodeEntered, IMessage> {

		@Override
		public IMessage onMessage(PacketDoorCodeEntered message,
				MessageContext ctx) {
			LogHelper.debug("onMessage");
			LogHelper.debug(message.code);
			return null;
		}

	}
}
