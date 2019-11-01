package com.docm.printer;

import com.docm.ui.module.ServerUIModule;
import com.docm.utils.ServerTimeUtil;

public class Printer
{
    public static Printer instance;
    private static boolean isUIModel;
    private static ServerUIModule sum;
    
     static {
    	Printer.instance = new Printer();
    }
    
    public static void init(final boolean isUIModel) {
    	System.out.println(">>>>>>>>>>>>>>>>>>init<<<<<<<<<<<<<<<<");
        Printer.instance = new Printer();
        if (isUIModel) {
            Printer.sum = ServerUIModule.getInsatnce();
        }
        Printer.isUIModel = isUIModel;
    }
    
    public void print(final String context) {
    	System.out.println(">>>>>>>>>>>>>>>>>>print<<<<<<<<<<<<<<<<");
        if (Printer.instance != null) {
            if (Printer.isUIModel) {
                Printer.sum.printMessage(context);
            }
            else {
                System.out.println("[" + new String(ServerTimeUtil.accurateToSecond().getBytes()) + "]" + new String(context.getBytes()) + "\r\n");
            }
        }
    }
}
