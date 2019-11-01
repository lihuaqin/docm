package com.docm.service.impl;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.docm.dao.FileDao;
import com.docm.dao.FolderDao;
import com.docm.dao.PropertiesDao;
import com.docm.enumeration.AccountAuth;
import com.docm.vo.Folder;
import com.docm.vo.Node;
import com.docm.vo.Propertie;
import com.docm.service.FileChainService;
import com.docm.utils.AESCipher;
import com.docm.utils.ConfigureReader;
import com.docm.utils.ContentTypeMap;
import com.docm.utils.FileBlockUtil;
import com.docm.utils.FolderUtil;
import com.docm.utils.LogUtil;
import com.docm.utils.RangeFileStreamWriter;

@Service
public class FileChainServiceImpl extends RangeFileStreamWriter implements FileChainService {

	@Resource
	private FileDao nm;
	@Resource
	private FolderDao flm;
	@Resource
	private FileBlockUtil fbu;
	@Resource
	private ContentTypeMap ctm;
	@Resource
	private LogUtil lu;
	@Resource
	private AESCipher cipher;
	@Resource
	private PropertiesDao pm;
	@Resource
	private FolderUtil fu;

	@Override
	public void getResourceByChainKey(HttpServletRequest request, HttpServletResponse response) {
		int statusCode = 403;
		if (ConfigureReader.instance().isOpenFileChain()) {
			final String ckey = request.getParameter("ckey");
			// 权限凭证有效性并确认其对应的资源
			if (ckey != null) {
				Propertie keyProp = pm.selectByKey("chain_aes_key");
				if (keyProp != null) {
					try {
						String fid = cipher.decrypt(keyProp.getPropertieValue(), ckey);
						Node f = this.nm.queryById(fid);
						if (f != null) {
							File target = this.fbu.getFileFromBlocks(f);
							if (target != null && target.isFile()) {
								String fileName = f.getFileName();
								String suffix = "";
								if (fileName.indexOf(".") >= 0) {
									suffix = fileName.substring(fileName.indexOf("."));
								}
								writeRangeFileStream(request, response, target, f.getFileName(),
										ctm.getContentType(suffix));
								if (request.getHeader("Range") == null) {
									this.lu.writeChainEvent(request, f);
								}
								return;
							}
						}
						statusCode = 404;
					} catch (Exception e) {
						lu.writeException(e);
						statusCode = 500;
					}
				} else {
					statusCode = 404;
				}
			}
		}
		try {
			//  处理无法下载的资源
			response.sendError(statusCode);
		} catch (IOException e) {

		}
	}

	@Override
	public String getChainKeyByFid(HttpServletRequest request) {
		if (ConfigureReader.instance().isOpenFileChain()) {
			String fid = request.getParameter("fid");
			String account = (String) request.getSession().getAttribute("ACCOUNT");
			if (fid != null) {
				final Node f = this.nm.queryById(fid);
				if (f != null) {
					if (ConfigureReader.instance().authorized(account, AccountAuth.DOWNLOAD_FILES,
							fu.getAllFoldersId(f.getFileParentFolder()))) {
						Folder folder = flm.queryById(f.getFileParentFolder());
						if (ConfigureReader.instance().accessFolder(folder, account)) {
							// 将指定的fid加密为ckey并返回。
							try {
								Propertie keyProp = pm.selectByKey("chain_aes_key");
								if (keyProp == null) {// 如果没有生成过永久性AES密钥，则先生成再加密
									String aesKey = cipher.generateRandomKey();
									Propertie chainAESKey = new Propertie();
									chainAESKey.setPropertieKey("chain_aes_key");
									chainAESKey.setPropertieValue(aesKey);
									if (pm.insert(chainAESKey) > 0) {
										return cipher.encrypt(aesKey, fid);
									}
								} else {// 如果已经有了，则直接用其加密
									return cipher.encrypt(keyProp.getPropertieValue(), fid);
								}
							} catch (Exception e) {
								lu.writeException(e);
							}
						}
					}
				}
			}
		}
		return "ERROR";
	}

}
