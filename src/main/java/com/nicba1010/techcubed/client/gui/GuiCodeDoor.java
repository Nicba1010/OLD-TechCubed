package com.nicba1010.techcubed.client.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

import org.lwjgl.input.Keyboard;

import com.nicba1010.techcubed.packet.PacketDoorCodeEntered;
import com.nicba1010.techcubed.packet.PacketHandler;
import com.nicba1010.techcubed.tileentity.TileEntityCodeDoor;
import com.nicba1010.techcubed.util.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCodeDoor extends GuiScreen {
	/** Text field containing the command block's command. */
	private GuiTextField codeField;
	private GuiTextField returnedMessage;
	/** "Done" button for the GUI. */
	private GuiButton doneBtn;
	private GuiButton cancelBtn;
	private TileEntityCodeDoor tileEntity;

	public GuiCodeDoor(TileEntityCodeDoor tileEntity) {
		super(false);
		this.tileEntity = tileEntity;
	}

	/**
	 * Called from the main game loop to update the screen.
	 */
	@Override
	public void updateScreen() {
		this.codeField.updateCursorCounter();
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(this.doneBtn = new GuiButton(0,
				this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20,
				I18n.format("gui.done", new Object[0])));
		this.buttonList.add(this.cancelBtn = new GuiButton(1,
				this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, I18n
						.format("gui.cancel", new Object[0])));
		this.codeField = new GuiTextField(this.fontRendererObj,
				this.width / 2 - 150, 50, 300, 20);
		this.codeField.setMaxStringLength(32767);
		this.codeField.setFocused(true);
		this.codeField.setText("");
		this.returnedMessage = new GuiTextField(this.fontRendererObj,
				this.width / 2 - 150, 135, 300, 20);
		this.returnedMessage.setMaxStringLength(32767);
		this.returnedMessage.setEnabled(false);
		this.returnedMessage.setText("Enter code!");

		this.doneBtn.enabled = this.codeField.getText().trim().length() > 0;
	}

	/**
	 * Called when the screen is unloaded. Used to disable keyboard repeat
	 * events
	 */
	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			if (button.id == 1) {
				this.mc.displayGuiScreen((GuiScreen) null);
			} else if (button.id == 0) {
				LogHelper.debug("Queueing");
				PacketHandler.INSTANCE.sendToServer(new PacketDoorCodeEntered(
						tileEntity.xCoord, tileEntity.yCoord,
						tileEntity.zCoord, codeField.getText()));
			}
		}
	}

	/**
	 * Fired when a key is typed. This is the equivalent of
	 * KeyListener.keyTyped(KeyEvent e).
	 */
	@Override
	protected void keyTyped(char charTyped, int par2) {
		this.codeField.textboxKeyTyped(charTyped, par2);
		this.returnedMessage.textboxKeyTyped(charTyped, par2);
		this.doneBtn.enabled = this.codeField.getText().trim().length() > 0;

		if (par2 != 28 && par2 != 156) {
			if (par2 == 1) {
				this.actionPerformed(this.cancelBtn);
			}
		} else {
			this.actionPerformed(this.doneBtn);
		}
	}

	/**
	 * Called when the mouse is clicked.
	 */
	@Override
	protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
		super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
		this.codeField.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
		this.returnedMessage.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj,
				I18n.format("advMode.setCommand", new Object[0]),
				this.width / 2, 20, 16777215);
		this.drawString(this.fontRendererObj,
				I18n.format("advMode.command", new Object[0]),
				this.width / 2 - 150, 37, 10526880);
		this.codeField.drawTextBox();
		byte b0 = 75;
		byte b1 = 0;
		FontRenderer fontrenderer = this.fontRendererObj;
		String s = I18n.format("advMode.nearestPlayer", new Object[0]);
		int i1 = this.width / 2 - 150;
		int l = b1 + 1;
		this.drawString(fontrenderer, s, i1, b0 + b1
				* this.fontRendererObj.FONT_HEIGHT, 10526880);
		this.drawString(this.fontRendererObj,
				I18n.format("advMode.randomPlayer", new Object[0]),
				this.width / 2 - 150, b0 + l++
						* this.fontRendererObj.FONT_HEIGHT, 10526880);
		this.drawString(this.fontRendererObj,
				I18n.format("advMode.allPlayers", new Object[0]),
				this.width / 2 - 150, b0 + l++
						* this.fontRendererObj.FONT_HEIGHT, 10526880);

		if (this.returnedMessage.getText().length() > 0) {
			int k = b0 + l * this.fontRendererObj.FONT_HEIGHT + 20;
			this.drawString(this.fontRendererObj,
					I18n.format("advMode.previousOutput", new Object[0]),
					this.width / 2 - 150, k, 10526880);
			this.returnedMessage.drawTextBox();
		}

		super.drawScreen(par1, par2, par3);
	}
}