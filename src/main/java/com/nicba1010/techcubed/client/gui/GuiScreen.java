package com.nicba1010.techcubed.client.gui;

public class GuiScreen extends net.minecraft.client.gui.GuiScreen {
	boolean doesGuiPauseGame = true;

	public GuiScreen(boolean doesGuiPauseGame) {
		this.doesGuiPauseGame = doesGuiPauseGame;
	}

	public GuiScreen() {
		super();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return doesGuiPauseGame;
	}
}
