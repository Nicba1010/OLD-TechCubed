package com.nicba1010.techcubed.packet;

import java.util.Iterator;
import java.util.List;

import tv.twitch.chat.ChatMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

import com.nicba1010.techcubed.block.BlockCodeDoor;
import com.nicba1010.techcubed.tileentity.TileEntityCodeDoor;
import com.nicba1010.techcubed.util.LogHelper;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageDoorCodeEntered implements IMessage {
	int x, y, z;
	String code;
	String player;

	public MessageDoorCodeEntered() {

	}

	public MessageDoorCodeEntered(int x, int y, int z, String code,
			String player) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.code = code;
		this.player = player;
	}

	@Override
	public void fromBytes(ByteBuf buffer) {
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		code = ByteBufUtils.readUTF8String(buffer);
		player = ByteBufUtils.readUTF8String(buffer);
	}

	@Override
	public void toBytes(ByteBuf buffer) {
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		ByteBufUtils.writeUTF8String(buffer, code);
		ByteBufUtils.writeUTF8String(buffer, player);
	}

	public static class Handler implements
			IMessageHandler<MessageDoorCodeEntered, IMessage> {

		@Override
		public IMessage onMessage(MessageDoorCodeEntered message,
				MessageContext context) {
			System.out.println("RECIEVED");
			EntityPlayerMP playerEntity = context.getServerHandler().playerEntity;
			if (playerEntity != null) {
				World worldObj = playerEntity.worldObj;
				TileEntityCodeDoor te = ((TileEntityCodeDoor) context
						.getServerHandler().playerEntity.worldObj
						.getTileEntity(message.x, message.y, message.z));
				BlockCodeDoor block = (BlockCodeDoor) worldObj.getBlock(
						message.x, message.y, message.z);
				if (te != null) {
					if (te.getCode().length() > 0) {
						if (message.code.equals(te.getCode())) {
							block.toggleCodeDoor(worldObj, message.x,
									message.y, message.z, playerEntity);
						} else {
							playerEntity
									.addChatComponentMessage(new ChatComponentText(
											"Wrong code!"));
							playerEntity
									.addChatComponentMessage(new ChatComponentText(
											"Owner is " + te.owner + "!"));
						}
					} else {
						te.code = message.code;
						te.owner = message.player;
						playerEntity
								.addChatComponentMessage(new ChatComponentText(
										"Code successfuly set to " + te.code));
						playerEntity
								.addChatComponentMessage(new ChatComponentText(
										"Owner is " + te.owner));
					}
					worldObj.markBlockForUpdate(message.x, message.y, message.z);
				}
			}
			return null;
		}
	}
}
