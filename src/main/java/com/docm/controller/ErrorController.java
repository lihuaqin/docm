package com.docm.controller;

import javax.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.docm.printer.Printer;
import com.docm.utils.FileBlockUtil;
import com.docm.utils.LogUtil;

@ControllerAdvice
public class ErrorController {
	@Resource
	private FileBlockUtil fbu;
	@Resource
	private LogUtil lu;

	@ExceptionHandler({ Exception.class })
	public void handleException(final Exception e) {
		this.lu.writeException(e);
		e.printStackTrace();
		this.fbu.checkFileBlocks();
		Printer.instance
				.print("\u5904\u7406\u8bf7\u6c42\u65f6\u53d1\u751f\u9519\u8bef\uff1a\n\r------\u4fe1\u606f------\n\r"
						+ e.getMessage() + "\n\r------\u4fe1\u606f------");
	}
}
