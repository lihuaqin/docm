package com.docm.listener;

import javax.servlet.annotation.*;

import com.docm.utils.FileNodeUtil;

import javax.servlet.*;

@WebListener
public class H2DBinitListener implements ServletContextListener {
	public void contextInitialized(final ServletContextEvent sce) {
		FileNodeUtil.initNodeTableToDataBase();
	}

	public void contextDestroyed(final ServletContextEvent sce) {
	}
}
