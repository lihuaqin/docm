<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path ;
	if("zdktmt.youwe-edu.com".equals(request.getServerName())){//添加域名
		basePath = "https://zdktmt.youwe-edu.com:1443" + path;
	}
	pageContext.setAttribute("basePath",basePath); 
	
%>
<!doctype html>

<html>
<head>
<base href="/">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>KIFT</title>
<!-- Bootstrap基本框架 -->
<link rel="stylesheet" href="${basePath}/css/bootstrap.min.css">
<!-- 全局样式 -->
<link rel="stylesheet" href="${basePath}/css/overrall.min.css">
<!-- 图片查看器插件 -->
<link rel="stylesheet" href="${basePath}/css/viewer.min.css">
<!-- 音乐播放器插件 -->
<link rel="stylesheet" href="${basePath}/css/APlayer.min.css">
<!-- 页面图标 -->
<link rel="icon" type="${basePath}/image/x-icon" href="${basePath}/css/icon.png" />
<!-- 对旧浏览器的支持部分... -->
<!--[if lt IE 9]>
      <script src="${basePath}/js/html5shiv.min.js"></script>
      <script src="${basePath}/js/respond.min.js"></script>
<![endif]-->
</head>

<body>
	<!-- 显示主体 -->
	<div class="container">
		<!-- 页面标题栏 -->
		<div class="row">
			<div class="col-md-12">
				<div class="titlebox">
					<span class="titletext"> <!-- 主标题，如需修改请替换后方标签内的文字 --> <em>
							青阳网络文件传输系统 <small> <!-- 副标题，如需修改请替换后方标签内的文字 --> <span
								class="graytext">KIFT</span>
						</small>
					</em>
					</span> <span id="tb" class="rightbtn"></span>
					<button class="btn btn-link rightbtn" onclick="refreshFolderView()">
						刷新 <span class="glyphicon glyphicon-repeat" aria-hidden="true"></span>
					</button>
				</div>
				<hr />
			</div>
		</div>
		<!-- 信息栏、操作栏与文件列表 -->
		<div class="row">
			<div class="col-md-12">
				<p id="vicetbbox" class="subtitle" style="display: none;">
					<span id="tb2"></span>
					<button class="btn btn-link" onclick="refreshFolderView()">
						刷新 <span class="glyphicon glyphicon-repeat" aria-hidden="true"></span>
					</button>
				</p>
				<p class="subtitle">
					文件同步时间：<span id="publishTime"></span>
				</p>
				<p class="subtitle">
					OS：<span id="serverOS"><span class="graytext">加载中...</span></span>
				</p>
				<div id="filetable" class="panel panel-default" unselectable="on"
					onselectstart="return false;" style="-moz-user-select: none;">
					<!-- 文件列表头部，也就是操作栏 -->
					<div class="panel-heading">
						<div class="heading">
							<div class="navbar-header">
								<a id="filetableheadera" href="javascript:void(0);"
									class="navbar-text" data-toggle="modal"
									data-target="#folderInfoModal"><span id="folderIconSpan"
									class="glyphicon glyphicon-folder-close"></span>&ensp;&ensp;<span
									id="currentFolderName"></span> <span id="mdropdownicon"></span></a>
							</div>
							<div class="collapse navbar-collapse" id="filetableoptmenu">
								<ul class="nav navbar-nav">
									<li class="dropdown"><a href="javascript:void(0);"
										class="dropdown-toggle" data-toggle="dropdown" role="button"
										aria-haspopup="true" aria-expanded="false">上一级 <span
											class="caret"></span></a>
										<ul class="dropdown-menu" id="parentFolderList"></ul></li>
								</ul>
								<form id="filetableoptmenusreach"
									class="navbar-form navbar-left">
									<div class="form-group">
										<input id="sreachKeyWordIn" type="text" class="form-control"
											placeholder="请输入文件名...">
									</div>
									<button type="button" class="btn btn-default"
										onclick="doSearchFile()">搜索</button>
								</form>
								<ul class="nav navbar-nav navbar-right">
									<li id="packageDownloadBox"></li>
									<li class="dropdown" id="operationMenuBox"
										data-toggle="popover"><a class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false"><span
											class="glyphicon glyphicon-cog"></span> 操作 <span
											class="caret"></span></a>
										<ul class="dropdown-menu" id="fileListDropDown">
											<li id="uploadFileButtonLi"><a>上传文件 <span
													class="pull-right"><span
														class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>+U</span></a></li>
											<li id="uploadFolderButtonLi"><a>上传文件夹 <span
													class="pull-right"><span
														class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>+F</span></a></li>
											<li role="separator" class="divider"></li>
											<li id="createFolderButtonLi"><a>新建文件夹 <span
													class="pull-right"><span
														class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>+N</span></a></li>
											<li role="separator" class="divider"></li>
											<li id="cutFileButtonLi"><a><span id='cutSignTx'>剪切
														<span class="pull-right"><span
															class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>+C</span>
												</span></a></li>
											<li id="deleteSeelectFileButtonLi"><a>删除 <span
													class="pull-right"><span
														class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>+D</span></a></li>
										</ul></li>
								</ul>
							</div>
						</div>
					</div>
					<table class="table table-hover">
						<thead>
							<tr>
								<th onclick="sortbyfn()">文件名<span id="sortByFN"
									aria-hidden="true" style="float: right"></span></th>
								<th class="hiddenColumn" onclick="sortbycd()">创建日期<span
									id="sortByCD" aria-hidden="true" style="float: right"></span></th>
								<th onclick="sortbyfs()">大小<span id="sortByFS"
									aria-hidden="true" style="float: right"></span></th>
								<th class="hiddenColumn" onclick="sortbycn()">创建者<span
									id="sortByCN" aria-hidden="true" style="float: right"></span></th>
								<th onclick="showOriginFolderView()">操作</th>
							</tr>
						</thead>
						<tbody id="foldertable"></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- end 显示主体 -->

	<!-- 登录模态框 -->
	<div class="modal fade bs-example-modal-sm" id="loginModal"
		tabindex="-1" role="dialog" aria-labelledby="loginModelTitle">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="loginModelTitle">
						<span class="glyphicon glyphicon-user"></span> 账户登录
					</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group" id="accountidbox">
							<label for="accountid" id="accountidtitle"
								class="col-sm-3 control-label">账户:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="accountid"
									placeholder="请输入账户……">
							</div>
						</div>
						<div class="form-group" id="accountpwdbox">
							<label for="accountpwd" id="accountpwdtitle"
								class="col-sm-3 control-label">密码:</label>
							<div class="col-sm-9">
								<input type="password" class="form-control" id="accountpwd"
									placeholder="请输入密码……">
							</div>
						</div>
						<div class="form-group hidden" id="vercodebox"></div>
						<div id="alertbox" role="alert"></div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" id="dologinButton" class="btn btn-primary"
						onclick="dologin()">登录</button>
				</div>
			</div>
		</div>
	</div>
	<!-- end 登录 -->
	<!-- 注销提示框 -->
	<div class="modal fade bs-example-modal-sm" id="logoutModal"
		tabindex="-1" role="dialog" aria-labelledby="logoutModelTitle">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="logoutModelTitle">
						<span class="glyphicon glyphicon-comment"></span> 注销
					</h4>
				</div>
				<div class="modal-body">
					<h5>提示：您确认要注销么？</h5>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-danger" onclick="dologout()">注销</button>
				</div>
			</div>
		</div>
	</div>
	<!-- end 注销 -->
	<!-- 新建文件夹框 -->
	<div class="modal fade" id="newFolderModal" tabindex="-1" role="dialog"
		aria-labelledby="newFolderlMolderTitle">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="newFolderlMolderTitle">
						<span class="glyphicon glyphicon-folder-open"></span> 新建文件夹
					</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group" id="foldernamebox">
							<label for="folderid" id="foldernametitle"
								class="col-sm-3 control-label">新建文件夹：</label>
							<div class="col-sm-9">
								<div class="input-group">
									<input type="text" class="form-control" id="foldername"
										placeholder="请输入新文件夹的名称……" folderConstraintLevel="0">
									<div class="input-group-btn">
										<button type="button" class="btn btn-default dropdown-toggle"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">
											&nbsp;<span id="newfoldertype">公开的</span>&nbsp;<span
												class="caret"></span>
										</button>
										<ul id="foldertypelist"
											class="dropdown-menu dropdown-menu-right">
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div id="folderalert" role="alert"></div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary"
						onclick="createfolder()">创建</button>
				</div>
			</div>
		</div>
	</div>
	<!-- end 创建文件夹 -->
	<!-- 删除文件夹提示框 -->
	<div class="modal fade bs-example-modal-sm" id="deleteFolderModal"
		tabindex="-1" role="dialog" aria-labelledby="deleteFolderModelTitle">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="deleteFolderModelTitle">
						<span class="glyphicon glyphicon-comment"></span> 删除文件夹
					</h4>
				</div>
				<div class="modal-body">
					<h5 id="deleteFolderMessage"></h5>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<span id="deleteFolderBox"></span>
				</div>
			</div>
		</div>
	</div>
	<!-- end 删除文件夹 -->
	<!-- 重命名文件夹框 -->
	<div class="modal fade" id="renameFolderModal" tabindex="-1"
		role="dialog" aria-labelledby="renameFolderMolderTitle">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="renameFolderMolderTitle">
						<span class="glyphicon glyphicon-wrench"></span> 编辑文件夹
					</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group" id="folderrenamebox">
							<label for="folderid" id="foldernametitle"
								class="col-sm-3 control-label">编辑文件夹：</label>
							<div class="col-sm-9">
								<div class="input-group">
									<input type="text" class="form-control" id="newfoldername"
										placeholder="请输入文件夹的名称……" folderConstraintLevel="0">
									<div class="input-group-btn">
										<button type="button" class="btn btn-default dropdown-toggle"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">
											&nbsp;<span id="editfoldertype">公开的</span>&nbsp;<span
												class="caret"></span>
										</button>
										<ul id="editfoldertypelist"
											class="dropdown-menu dropdown-menu-right">
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div id="editfolderalert" role="alert"></div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<span id="renameFolderBox"></span>
				</div>
			</div>
		</div>
	</div>
	<!-- end 重命名文件夹 -->
	<!-- 上传文件框 -->
	<div class="modal fade" id="uploadFileModal" tabindex="-1"
		role="dialog" aria-labelledby="uploadFileMolderTitle">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="uploadFileMolderTitle">
						<span class="glyphicon glyphicon-cloud-upload"></span> 上传文件
					</h4>
				</div>
				<div class="modal-body">
					<h5>
						选择文件：<span id="selectcount"></span>
					</h5>
					<input type="text" id="filepath" class="form-control"
						onclick="checkpath()" onfocus="this.blur()"
						placeholder="请点击选择要上传的文件……"> <input type="file"
						id="uploadfile" style="display: none;" onchange="getInputUpload()"
						multiple="multiple"> <br />
					<h5>
						上传进度：<span id="filecount"></span>
					</h5>
					<div class="progress">
						<div id="pros" class="progress-bar" role="progressbar"
							aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"
							style="width: 0%;">
							<span class="sr-only"></span>
						</div>
					</div>
					<h5>上传状态：</h5>
					<div class="panel panel-default">
						<div class="panel-body">
							<div id="uploadstatus" class="uploadstatusbox"></div>
						</div>
					</div>
					<div id="uploadFileAlert" role="alert" class="alert alert-danger"></div>
					<div id="selectFileUpLoadModelAlert" class="alert alert-danger"
						role="alert">
						<h4>提示：存在同名文件！</h4>
						<p>
							您要上传的文件“<span id="repeFileName"></span>”已存在于该路径下，您希望：
						</p>
						<p>
							<input id="selectFileUpLoadModelAsAll" type="checkbox">
							全部应用
						</p>
						<p>
							<button id="uploadcoverbtn" type="button"
								class="btn btn-danger btn-sm"
								onclick="selectFileUpLoadModelEnd('cover')">覆盖</button>
							<button type="button" class="btn btn-default btn-sm"
								onclick="selectFileUpLoadModelEnd('skip')">跳过</button>
							<button type="button" class="btn btn-default btn-sm"
								onclick="selectFileUpLoadModelEnd('both')">保留两者</button>
						</p>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						onclick='abortUpload()'>取消</button>
					<button id="umbutton" type='button' class='btn btn-primary'
						onclick='checkUploadFile()'>开始上传</button>
				</div>
			</div>
		</div>
	</div>
	<!-- end 上传文件 -->
	<!-- 上传文件夹框 -->
	<div class="modal fade" id="importFolderModal" tabindex="-1"
		role="dialog" aria-labelledby="importFolderMolderTitle">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="importFolderMolderTitle">
						<span class="glyphicon glyphicon-cloud-upload"></span> 上传文件夹
					</h4>
				</div>
				<div class="modal-body">
					<h5>选择文件夹：</h5>
					<div class="input-group">
						<input type="text" id="folderpath" class="form-control"
							onclick="checkimportpath()" onfocus="this.blur()"
							placeholder="请点击选择要上传的文件夹……" folderConstraintLevel="0">
						<div class="input-group-btn">
							<button id="importFolderLevelBtn" type="button"
								class="btn btn-default dropdown-toggle" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">
								&nbsp;<span id="importfoldertype">公开的</span>&nbsp;<span
									class="caret"></span>
							</button>
							<ul id="importfoldertypelist"
								class="dropdown-menu dropdown-menu-right">
							</ul>
						</div>
					</div>
					<input type="file" id="importfolder" style="display: none;"
						onchange="getInputImport()" multiple="multiple" webkitdirectory>
					<h5>
						上传进度：<span id="importcount"></span>
					</h5>
					<div class="progress">
						<div id="importpros" class="progress-bar" role="progressbar"
							aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"
							style="width: 0%;">
							<span class="sr-only"></span>
						</div>
					</div>
					<h5>上传状态：</h5>
					<div class="panel panel-default">
						<div class="panel-body">
							<div id="importstatus" class="uploadstatusbox"></div>
						</div>
					</div>
					<div id="importFolderAlert" class="alert alert-danger" role="alert"></div>
					<div id="selectFolderImportModelAlert" class="alert alert-danger"
						role="alert">
						<h4>提示：存在同名文件夹！</h4>
						<p>
							您要上传的文件夹“<span id="repeFolderName"></span>”已存在于该路径下，您希望：
						</p>
						<p>
							<button id="importcoverbtn" type="button"
								class="btn btn-danger btn-sm" onclick="importAndCover()">覆盖</button>
							<button type="button" class="btn btn-default btn-sm"
								onclick="abortImport()">取消上传</button>
							<button type="button" class="btn btn-default btn-sm"
								onclick="importAndBoth()">保留两者</button>
						</p>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						onclick='abortImport()'>取消</button>
					<button id="importbutton" type='button' class='btn btn-primary'
						onclick='checkImportFolder()'>开始上传</button>
				</div>
			</div>
		</div>
	</div>
	<!-- end 上传文件夹 -->
	<!-- 下载提示框 -->
	<div class="modal fade" id="downloadModal" tabindex="-1" role="dialog"
		aria-labelledby="downloadModelTitle">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="downloadModelTitle">
						<span class="glyphicon glyphicon-cloud-download"></span> 下载
					</h4>
				</div>
				<div class="modal-body">
					<h5 id="downloadFileName" class="wordbreak">提示：您确认要下载文件：[]么？</h5>
					<a href="javascript:void(0);"
						onclick="$('#downloadURLCollapse').collapse('toggle')">下载链接+</a>
					<div class="collapse" id="downloadURLCollapse">
						<div id="downloadHrefBox" class="well well-sm wordbreak"></div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<span id="downloadFileBox"></span>
				</div>
			</div>
		</div>
	</div>
	<!-- end 下载 -->
	<!-- 删除提示框 -->
	<div class="modal fade bs-example-modal-sm" id="deleteFileModal"
		tabindex="-1" role="dialog" aria-labelledby="deleteFileModelTitle">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="deleteFileModelTitle">
						<span class="glyphicon glyphicon-comment"></span> 删除文件
					</h4>
				</div>
				<div class="modal-body">
					<h5 id="deleteFileMessage" class="wordbreak"></h5>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<span id="deleteFileBox"></span>
				</div>
			</div>
		</div>
	</div>
	<!-- end 删除提示框 -->
	<!-- 重命名框 -->
	<div class="modal fade bs-example-modal-sm" id="renameFileModal"
		tabindex="-1" role="dialog" aria-labelledby="renameFileMolderTitle">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="renameFileMolderTitle">
						<span class="glyphicon glyphicon-wrench"></span> 重命名文件
					</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group" id="filerenamebox">
							<label for="folderid" id="filenametitle"
								class="col-sm-3 control-label">名称：</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="newfilename"
									placeholder="请输入文件的名称……">
							</div>
						</div>
						<div id="newFileNamealert" role="alert"></div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<span id="renameFileBox"></span>
				</div>
			</div>
		</div>
	</div>
	<!-- end 重命名 -->
	<!-- 打包下载提示框 -->
	<div class="modal fade bs-example-modal-sm"
		id="downloadAllCheckedModal" tabindex="-1" role="dialog"
		aria-labelledby="downloadAllCheckedModalTitle">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="downloadAllCheckedModalTitle">
						<span class="glyphicon glyphicon-cloud-download"></span> 打包下载
					</h4>
				</div>
				<div class="modal-body">
					<h5>
						<span id="downloadAllCheckedName"></span><span
							id="downloadAllCheckedLoad" style="text-align: center;"></span>
					</h5>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<span id="downloadAllCheckedBox"></span>
				</div>
			</div>
		</div>
	</div>
	<!-- end 打包下载 -->
	<!--音乐播放模态框-->
	<div class="modal fade" id="audioPlayerModal" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<div id="aplayer" class="aplayer"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						onclick="audio_vulome_down()">
						<span class="glyphicon glyphicon-volume-down" aria-hidden="true"></span>
					</button>
					<button type="button" class="btn btn-default"
						onclick="audio_vulome_up()">
						<span class="glyphicon glyphicon-volume-up" aria-hidden="true"></span>
					</button>
					<button type="button" class="btn btn-default" onclick="audio_bw()">
						<span class="glyphicon glyphicon-backward" aria-hidden="true"></span>
					</button>
					<button type="button" class="btn btn-primary" id="playOrPause"
						onclick="audio_playOrPause()">
						<span class='glyphicon glyphicon-play' aria-hidden='true'></span>
					</button>
					<button type="button" class="btn btn-default" onclick="audio_fw()">
						<span class="glyphicon glyphicon-forward" aria-hidden="true"></span>
					</button>
					<button type="button" class="btn btn-default"
						onclick="closeAudioPlayer()">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- end 音乐播放 -->
	<!-- 加载提示框 -->
	<div id="loadingModal" class="modal fade bs-example-modal-sm"
		tabindex="-1" role="dialog" aria-labelledby="page is loading">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content loading">加载中...</div>
		</div>
	</div>
	<!-- end 加载提示框 -->
	<!-- 移动文件提示框 -->
	<div class="modal fade bs-example-modal-sm" id="moveFilesModal"
		tabindex="-1" role="dialog" aria-labelledby="moveFolderModalTitle">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="moveFolderModalTitle">
						<span class="glyphicon glyphicon-import"></span> 移动文件
					</h4>
				</div>
				<div class="modal-body">
					<h5 id="moveFilesMessage"></h5>
					<div id="selectFileMoveModelAlert" class="alert alert-danger"
						role="alert">
						<h4>提示：存在同名文件！</h4>
						<p>
							您要移动的文件“<span id="mrepeFileName"></span>”已存在于该路径下，您希望：
						</p>
						<p>
							<input id="selectFileMoveModelAsAll" type="checkbox">
							全部应用
						</p>
						<p>
							<button id="movecoverbtn" type="button"
								class="btn btn-danger btn-sm"
								onclick="selectFileMoveModel('cover')">覆盖</button>
							<button type="button" class="btn btn-default btn-sm"
								onclick="selectFileMoveModel('skip')">跳过</button>
							<button type="button" class="btn btn-default btn-sm"
								onclick="selectFileMoveModel('both')">保留两者</button>
						</p>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<span id="moveFilesBox"></span>
				</div>
			</div>
		</div>
	</div>
	<!-- end 移动文件提示框 -->
	<!-- 文件夹详情模态框 -->
	<div class="modal fade" id="folderInfoModal" tabindex="-1"
		role="dialog" aria-labelledby="Folder Informaction Modal">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">详细信息...</h4>
				</div>
				<div class="modal-body">
					<dl>
						<dt>文件夹名称：</dt>
						<dd id="fim_name"></dd>
						<dt>创建者：</dt>
						<dd id="fim_creator"></dd>
						<dt>创建时间：</dt>
						<dd id="fim_folderCreationDate"></dd>
						<dt>文件统计：</dt>
						<dd id="fim_statistics"></dd>
						<dt>文件夹ID：</dt>
						<dd id="fim_folderId"></dd>
					</dl>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
				</div>
			</div>
		</div>
	</div>
	<!-- end 文件夹详情模态框 -->
	<!-- 修改密码模态框 -->
	<div class="modal fade bs-example-modal-sm" id="changePasswordModal"
		tabindex="-1" role="dialog" aria-labelledby="changePasswordModelTitle">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="changePasswordModelTitle">
						<span class="glyphicon glyphicon-edit"></span> 修改密码
					</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group" id="changepassword_oldepwdbox">
							<label for="changepassword_oldpwd"
								id="changepassword_oldpwdtitle" class="col-sm-3 control-label">旧密码:</label>
							<div class="col-sm-9">
								<input type="password" class="form-control"
									id="changepassword_oldpwd" placeholder="请输入旧密码确认身份……">
							</div>
						</div>
						<div class="form-group" id="changepassword_newpwdbox">
							<label for="changepassword_newpwd"
								id="changepassword_newpwdtitle" class="col-sm-3 control-label">新密码:</label>
							<div class="col-sm-9">
								<input type="password" class="form-control"
									id="changepassword_newpwd" placeholder="请输入新密码……">
							</div>
						</div>
						<div class="form-group" id="changepassword_reqnewpwdbox">
							<label for="changepassword_reqnewpwd"
								id="changepassword_reqnewpwdtitle"
								class="col-sm-3 control-label">确认新密码:</label>
							<div class="col-sm-9">
								<input type="password" class="form-control"
									id="changepassword_reqnewpwd" placeholder="请再次输入新密码……">
							</div>
						</div>
						<div id="changepassword_vccodebox" class="form-group"></div>
						<div id="changepasswordalertbox" class="alert alert-danger"
							role="alert"></div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" id="changePasswordButton"
						class="btn btn-danger" onclick="doChangePassword()">立即修改</button>
				</div>
			</div>
		</div>
	</div>
	<!-- end 改密 -->
	<!-- 永久资源链接显示模态框 -->
	<div class="modal fade" id="fileChainModal" tabindex="-1" role="dialog"
		aria-labelledby="chainModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="chainModalLabel">
						<span class="glyphicon glyphicon-link"></span> 资源链接
					</h4>
				</div>
				<div class="modal-body">
					<textarea id="fileChainTextarea" class="form-control" rows="3" readonly></textarea>
				</div>
				<div class="modal-footer">
					<button id="copyChainBtn" type="button" class="btn btn-info" onclick="copyFileChain()">复制链接</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<!-- end 永久资源链接 -->
	<!-- 返回顶部按钮（隐藏式） -->
	<div id="gobacktotopbox" class="gobacktopbox text-center hidden">
		<button type="button" onclick="goBackToTop()" class="gobacktopbutton">
			返回顶部 <span class="glyphicon glyphicon-eject" aria-hidden="true"></span>
		</button>
	</div>
	<!-- end 返回顶部按钮 -->
</body>
<!-- jquery基本框架 -->
<script type="text/javascript" src="${basePath}/js/jquery-1.12.4.min.js"></script>
<!-- bootstrap基本框架 -->
<script type="text/javascript" src="${basePath}/js/bootstrap.min.js"></script>
<!-- 加密插件 -->
<script type="text/javascript" src="${basePath}/js/jsencrypt.min.js"></script>
<!-- 图片查看器 -->
<script type="text/javascript" src="${basePath}/js/viewer.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-viewer.min.js"></script>
<!-- 音乐播放器 -->
<script type="text/javascript" src="${basePath}/js/APlayer.min.js"></script>
<!-- 页面操作定义 -->
<%-- <script type="text/javascript" src="${basePath}/js/home.min.jsp"></script> --%>
<%@include file="/js/home.jsp"%>
<%-- <%@include file="/js/home.min.jsp"%> --%>
</html>