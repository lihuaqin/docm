package com.docm.utils;

import org.springframework.stereotype.*;

import com.docm.dao.FileDao;
import com.docm.dao.FolderDao;
import com.docm.enumeration.AccountAuth;
import com.docm.vo.Folder;
import com.docm.vo.Node;

import javax.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class FolderUtil {
	@Resource
	private FolderDao fm;
	@Resource
	private FileDao fim;
	@Resource
	private FileBlockUtil fbu;

	public List<Folder> getParentList(final String fid) {
		Folder f = this.fm.queryById(fid);
		final List<Folder> folderList = new ArrayList<Folder>();
		if (f != null) {
			while (!f.getFolderParent().equals("null")) {
				f = this.fm.queryById(f.getFolderParent());
				folderList.add(f);
			}
		}
		Collections.reverse(folderList);
		return folderList;
	}

	public List<String> getAllFoldersId(final String fid) {
		List<String> idList = new ArrayList<>();
		idList.addAll(getParentList(fid).parallelStream().map((e) -> e.getFolderId()).collect(Collectors.toList()));
		idList.add(fid);
		return idList;
	}

	public int deleteAllChildFolder(final String folderId) {
		final Thread deleteChildFolderThread = new Thread(() -> this.iterationDeleteFolder(folderId));
		deleteChildFolderThread.start();
		return this.fm.deleteById(folderId);
	}

	private void iterationDeleteFolder(final String folderId) {
		final List<Folder> cf = (List<Folder>) this.fm.queryByParentId(folderId);
		if (cf.size() > 0) {
			for (final Folder f : cf) {
				this.iterationDeleteFolder(f.getFolderId());
			}
		}
		final List<Node> files = (List<Node>) this.fim.queryByParentFolderId(folderId);
		if (files.size() > 0) {
			this.fim.deleteByParentFolderId(folderId);
			for (final Node f2 : files) {
				this.fbu.deleteFromFileBlocks(f2);
			}
		}
		this.fm.deleteById(folderId);
	}

	public Folder createNewFolder(final String parentId, String account, String folderName, String folderConstraint) {
		if (!ConfigureReader.instance().authorized(account, AccountAuth.CREATE_NEW_FOLDER, getAllFoldersId(parentId))) {
			return null;
		}
		if (parentId == null || folderName == null || parentId.length() <= 0 || folderName.length() <= 0) {
			return null;
		}
		if (folderName.indexOf(".") == 0) {
			return null;
		}
		final Folder parentFolder = this.fm.queryById(parentId);
		if (parentFolder == null) {
			return null;
		}
		if (!ConfigureReader.instance().accessFolder(parentFolder, account)) {
			return null;
		}
		if (fm.queryByParentId(parentId).parallelStream().anyMatch((e) -> e.getFolderName().equals(folderName))) {
			return null;
		}
		Folder f = new Folder();
		// 设置子文件夹约束等级，不允许子文件夹的约束等级比父文件夹低
		int pc = parentFolder.getFolderConstraint();
		if (folderConstraint != null) {
			try {
				int ifc = Integer.parseInt(folderConstraint);
				if (ifc > 0 && account == null) {
					return null;
				}
				if (ifc < pc) {
					return null;
				} else {
					f.setFolderConstraint(ifc);
				}
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
		f.setFolderId(UUID.randomUUID().toString());
		f.setFolderName(folderName);
		f.setFolderCreationDate(ServerTimeUtil.accurateToDay());
		if (account != null) {
			f.setFolderCreator(account);
		} else {
			f.setFolderCreator("匿名用户");
		}
		f.setFolderParent(parentId);
		int i = 0;
		while (true) {
			try {
				final int r = this.fm.insertNewFolder(f);
				if (r > 0) {
					return f;
				}
				break;
			} catch (Exception e) {
				f.setFolderId(UUID.randomUUID().toString());
				i++;
			}
			if (i >= 10) {
				break;
			}
		}
		return null;
	}

	// 检查新建的文件夹是否存在同名问题。若有，删除同名文件夹并返回是否进行了该操作（旨在确保上传文件夹操作不被重复上传干扰）
	public boolean hasRepeatFolder(Folder f) {
		Folder[] repeats = fm.queryByParentId(f.getFolderParent()).parallelStream()
				.filter((e) -> e.getFolderName().equals(f.getFolderName())).toArray(Folder[]::new);
		if (repeats.length > 1) {
			deleteAllChildFolder(f.getFolderId());
			return true;
		} else {
			return false;
		}
	}
}
