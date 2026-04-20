package com.lilos.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;

public class InputUtil {
    
    private static final Minecraft mc = Minecraft.getInstance();

    public static boolean isKeyDown(int key) {
        return GLFW.glfwGetKey(mc.getWindow().getWindow(), key) == GLFW.GLFW_PRESS;
    }

    public static boolean isMouseDown(int button) {
        return GLFW.glfwGetMouseButton(mc.getWindow().getWindow(), button) == GLFW.GLFW_PRESS;
    }

    public static void pressKey(int key) {
        long window = mc.getWindow().getWindow();
        GLFW.glfwSetKeyCallback(window, (w, k, s, a, m) -> {});
        GLFW.glfwSetInputMode(window, GLFW.GLFW_STICKY_KEYS, GLFW.GLFW_TRUE);
    }

    public static double getMouseX() {
        double[] x = new double[1];
        double[] y = new double[1];
        GLFW.glfwGetCursorPos(mc.getWindow().getWindow(), x, y);
        return x[0];
    }

    public static double getMouseY() {
        double[] x = new double[1];
        double[] y = new double[1];
        GLFW.glfwGetCursorPos(mc.getWindow().getWindow(), x, y);
        return y[0];
    }

    public static boolean isMouseOver(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public static void setCursorPosition(double x, double y) {
        GLFW.glfwSetCursorPos(mc.getWindow().getWindow(), x, y);
    }

    public static void simulateKeyPress(int keyCode) {
        ClientPlayerEntity player = mc.player;
        if (player != null) {
            if (keyCode == GLFW.GLFW_KEY_W) {
                player.setJumping(false);
                player.setSprinting(player.isSprinting());
            }
        }
    }

    public static String getKeyBindName(int key) {
        return GLFW.glfwGetKeyName(key, 0);
    }

    public static int getKeyFromName(String name) {
        if (name == null || name.isEmpty()) return -1;
        
        switch (name.toUpperCase()) {
            case "W": return GLFW.GLFW_KEY_W;
            case "A": return GLFW.GLFW_KEY_A;
            case "S": return GLFW.GLFW_KEY_S;
            case "D": return GLFW.GLFW_KEY_D;
            case "SPACE": return GLFW.GLFW_KEY_SPACE;
            case "SHIFT": return GLFW.GLFW_KEY_LEFT_SHIFT;
            case "CONTROL": return GLFW.GLFW_KEY_LEFT_CONTROL;
            case "ALT": return GLFW.GLFW_KEY_LEFT_ALT;
            case "INSERT": return GLFW.GLFW_KEY_INSERT;
            case "DELETE": return GLFW.GLFW_KEY_DELETE;
            case "HOME": return GLFW.GLFW_KEY_HOME;
            case "END": return GLFW.GLFW_KEY_END;
            case "PAGE_UP": return GLFW.GLFW_KEY_PAGE_UP;
            case "PAGE_DOWN": return GLFW.GLFW_KEY_PAGE_DOWN;
            case "UP": return GLFW.GLFW_KEY_UP;
            case "DOWN": return GLFW.GLFW_KEY_DOWN;
            case "LEFT": return GLFW.GLFW_KEY_LEFT;
            case "RIGHT": return GLFW.GLFW_KEY_RIGHT;
            case "ESCAPE": return GLFW.GLFW_KEY_ESCAPE;
            case "ENTER": return GLFW.GLFW_KEY_ENTER;
            case "TAB": return GLFW.GLFW_KEY_TAB;
            default: return -1;
        }
    }
}
