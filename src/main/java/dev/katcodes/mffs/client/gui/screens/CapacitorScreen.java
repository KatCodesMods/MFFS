/*
 * This file is part of MFFS.
 * Copyright (c) 2014-2023 KatCodes.
 * <p>
 *  MFFS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *  <p>
 *  MFFS is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  <p>
 * You should have received a copy of the GNU General Public License
 * along with MFFS.  If not, see <http://www.gnu.org/licenses/>.
 * </p>
 * </p>
 * </p>
 */

package dev.katcodes.mffs.client.gui.screens;

import dev.katcodes.mffs.MFFSMod;
import dev.katcodes.mffs.client.gui.widgets.ModeButton;
import dev.katcodes.mffs.client.gui.widgets.VerticalGuage;
import dev.katcodes.mffs.common.inventory.CapacitorMenu;
import dev.katcodes.mffs.common.inventory.GeneratorMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.GlobalPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CapacitorScreen extends AbstractContainerScreen<CapacitorMenu> {
    private final ResourceLocation texture = new ResourceLocation(MFFSMod.MODID, "textures/gui/capacitor.png");
    private final ResourceLocation modeButtonTexture = new ResourceLocation(MFFSMod.MODID, "textures/gui/widgets/mode_button.png");
    private final ResourceLocation powerButtonTexture = new ResourceLocation(MFFSMod.MODID, "textures/gui/widgets/capacitor_buttons.png");
    private ModeButton modeButton;

    private ModeButton powerButton;

    public CapacitorScreen(CapacitorMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }


    @Override
    protected void init() {
        super.init();
        boolean widthTooNarrow = this.width < 379;
        this.imageHeight=208;
        this.imageWidth=176;
        modeButton=new ModeButton(this,modeButtonTexture,this.getGuiLeft()+154,this.getGuiTop()+5,Component.empty(),0,96,96);
        modeButton.setMode(this.menu.getMode());
        powerButton=new ModeButton(this,powerButtonTexture,this.getGuiLeft()+110,this.getGuiTop()+75,Component.empty(),1,80,48);
        powerButton.setMode(this.menu.getPowerMode());

        this.addRenderableWidget(modeButton);
        this.addRenderableWidget(powerButton);
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        modeButton.setMode(this.menu.getMode());
        powerButton.setMode(this.menu.getPowerMode());
    }

    @Override
    public void render(GuiGraphics p_97795_, int p_97796_, int p_97797_, float p_97798_) {
        this.renderBackground(p_97795_);
        super.render(p_97795_, p_97796_, p_97797_, p_97798_);
        this.renderTooltip(p_97795_, p_97796_, p_97797_);
    }

    @Override
    protected void renderLabels(GuiGraphics p_281635_, int p_282681_, int p_283686_) {
        //super.renderLabels(p_281635_, p_282681_, p_283686_);
        p_281635_.drawString(this.font,"Force Energy Capacitor", 8, 25, 4210752, false);
        p_281635_.drawString(this.font, "FE: "+ String.valueOf(this.menu.getEnergy()),8,100,4210752,false);
        //fontRenderer.drawString(Core.getDeviceName(), 8, 8, 0x404040);
//        p_281635_.drawString(this.font,
//                "FE: " + String.valueOf(Core.getStorageAvailablePower()), 8,
//                100, 0x404040);
        p_281635_.drawString(this.font,"Power Uplink: ", 8, 80, 4210752, false);

        p_281635_.drawString(this.font,"transmit range:", 8, 60, 4210752, false);
        p_281635_.drawString(this.font,(new StringBuilder()).append(" ").append(this.menu.getTransmitRange()).toString(),90,60,4210752,false);
//        fontRenderer.drawString(
//                (new StringBuilder()).append(" ")
//                        .append(Core.getTransmitRange()).toString(), 90, 60,
//                0x404040);
        p_281635_.drawString(this.font,"linked device:", 8, 43, 4210752, false);
//        fontRenderer.drawString(
//                (new StringBuilder()).append(" ")
//                        .append(Core.getLinketProjektor()).toString(), 90, 45,
//                0x404040);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int i = this.leftPos;
        int j = this.topPos;
        int i1 = (79 * this.menu.getPercentCapacity() / 100);
        MFFSMod.LOGGER.info("There is "+ i1 + " from percentcap - " + this.menu.getPercentCapacity() + " thus: "+ this.menu.getCapacity() + " / "+ this.menu.getEnergy());
        pGuiGraphics.blit(this.texture, i, j, 0, 0, this.imageWidth, this.imageHeight);
        pGuiGraphics.blit(this.texture,i+8,j+112,176,0,i1+1,79);
    }
}
