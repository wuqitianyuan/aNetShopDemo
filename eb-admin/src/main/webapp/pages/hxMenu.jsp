<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>前台横向菜单</title>
</head>
<body>
	<%--数据表格--%>
	<table id="dg"></table>
	<%--添加前台横向菜单窗口--%>
	<div id="box1">
		<center style="padding-top: 30px">
			<form id="ff" method="post" action="javascript:void(0)">
				<table cellpadding="5">
					<tr>
						<td>菜单名:</td>
						<td><input class="a1" type="text" name="title"></input></td>
					</tr>
					<tr>
						<td>跳转链接:</td>
						<td><input class="a2" type="text" name="url"></input></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<a href="#" class="c1">添加</a>
							<a href="#" class="c2">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</center>
	</div>

	<%--添加前台横向菜单编辑窗口--%>
	<div id="editbox">
		<center style="padding-top: 30px">
			<form id="editform" method="post" action="javascript:void(0)">
				<table cellpadding="5">
					<tr>
						<td>菜单名:</td>
						<td><input class="menutile" type="text" name="menutitle"></input></td>
					</tr>
					<tr>
						<td>跳转链接:</td>
						<td><input class="menuurl" type="text" name="menuurl"></input></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<a href="#" class="edit">编辑</a>
							<a href="#" class="reset">重置</a>
						</td>
					</tr>
				</table>
			</form>
		</center>
	</div>

	<script type="text/javascript">
		$(function(){//处理文档流
			/*表格请求后台java接口*/
			$("#dg").datagrid({
				url:"<%=basePath %>/getHxMenus.do",
                rownumbers:true,
                pagination:true,
                pageList:[5,10,20,30,50],
				columns:[
                    [
                        {checkbox:true},
                        {field:'id',title:'主键',width:150,align:'center',sortable:true},
                    	{field:'title',title:'菜单名',width:150},
                    	{field:'url',title:'跳转链接',width:150},
                        {field:'updateTime',title:'更新时间',width:200}
					]
				],
                sortName:'id',/*定义field=id的列可以排序*/
                remoteSort:false,/*关闭远程排序*/
				fit:true,
                striped:true,//斑马线效果
                /*rowStyler:function(index,row){//实现隔行变色 index：索引  row：当前行
					if(index%2==0){//奇数
						return "background-color:red";
					}else{//偶数
						return "background-color:green";
					}
				},*/
				toolbar:[
					{//添加按钮
					    iconCls:'icon-add',
						text:'添加',
						handler:function(){//事件处理器
							$(".c1").linkbutton({
								iconCls:'icon-ok'
							});
                            $(".c2").linkbutton({
                                iconCls:'icon-cancel'
                            });
                            $(".a1").validatebox({
                                required: true,
                                validType: 'length[1,10]'
                            });
                            $(".a2").validatebox({
                                required: true,
                                validType: 'url'
							});
                            $("#box1").window('open');
						}
					},'-',
					{//编辑按钮
					    iconCls:'icon-edit',
                        text:'编辑',
                        handler:function(){//事件处理器
					    	var arrLen = $("#dg").datagrid("getSelections").length;
					    	if(arrLen==0){
								$.messager.alert('警告','请选择一个待编辑的数据！');
							}else if(arrLen >1){
								$.messager.alert('警告','不可同时编辑多个数据，请重新选择！');
							}else{
								$(".edit").linkbutton({
									iconCls:'icon-ok'
								});
								$(".reset").linkbutton({
									iconCls:'icon-cancel'
								});
								$(".menutitile").validatebox({
									required: true,
									validType: 'length[1,10]'
								});
								$(".menuurl").validatebox({
									required: true,
									validType: 'url'
								});
								//进行数据回显
								var row = $("#dg").datagrid("getSelected");
								$("input[name=menutitle]").val(row.title);
								$("input[name=menuurl]").val(row.url);
								$("#editbox").window('open');
							}
                        }
					},'-',
					{
					    iconCls:'icon-remove',
                        text:'批量删除',
                        handler:function(){//事件处理器
							var arrLen = $("#dg").datagrid("getSelections").length;
							if(arrLen==0){
								$.messager.alert('警告','请至少选择一个待删除的数据！');
							}else{
								$.messager.confirm('确认对话框', '您确定删除所选记录吗？', function(r){
									if (r){
										var ids="";
										var selsArr= $("#dg").datagrid("getSelections");
										for(var i=0;i<selsArr.length;i++){
											if(ids==""){
												ids+=selsArr[i].id;
											}else{
												ids+="," + selsArr[i].id
											}
										}
										$.ajax({
											url:"<%=basePath %>/delWebMenu.do",
											type:"post",
											dataType:"json",
											data:{
												"ids":ids
											},
											success:function(rs){
												if(rs){//删除成功
													$("#dg").datagrid('reload')
													//3、提示添加成功
													$.messager.show({
														title:"提示",
														msg:"菜单删除成功"
													});
												}else{//添加失败
													$.messager.alert('提示','删除失败，请重试');
												}
											},
											error:function(err){

											}
										});
									}
								});
							}
                        }
					},//批量删除
				]
			});

			/*添加前台横向菜单窗口*/
            $("#box1").window({
                width:300,
                height:200,
                title:"添加横向菜单",
                iconCls:'icon-add',
                draggable:true,/*能拖动*/
                resizable:false, /*不能改变尺寸*/
                minimizable:false,
                collapsible:false,
                maximizable:false,
                modal:true,
                closed:true  /*窗口初始化时就默认关闭*/
            });

            /*添加触发ajax*/
            $(".c1").click(function(){
                $.ajax({
					url:"<%=basePath %>/addWebMenu.do",
					type:"post",
					dataType:"json",
					data:{
					    "title":$("input[name=title]").val(),
						"url":$("input[name=url]").val()
					},
					success:function(rs){
						if(rs){//添加成功
							//1、关闭添加前台菜单窗口
                            $("#box1").window('close');
							//2、刷新表格
                            $("#dg").datagrid('reload')
							//3、提示添加成功
                            $.messager.show({
								title:"提示",
								msg:"菜单添加成功"
							});
						}else{//添加失败
                            $.messager.alert('提示','添加失败，请重试');
						}
					},
					error:function(err){

					}
				});
			});
            /*重置触发事件*/
			$(".c2").click(function () {
				$("#ff input[type=text]").val("");
				$(".a1").focus();
			});

			/*添加前台横向菜单编辑窗口*/
			$("#editbox").window({
				width:300,
				height:200,
				title:"编辑横向菜单",
				iconCls:'icon-edit',
				draggable:true,/*能拖动*/
				resizable:false, /*不能改变尺寸*/
				minimizable:false,
				collapsible:false,
				maximizable:false,
				modal:true,
				closed:true  /*窗口初始化时就默认关闭*/
			});

			/*编辑触发ajax*/
			$(".edit").click(function(){
				console.log("111");
				var id=$("#dg").datagrid("getSelected").id;
				console.log("id=" + id)
				$.ajax({
					url:"<%=basePath %>/modWebMenu.do",
					type:"post",
					dataType:"json",
					data:{
						"title":$("input[name=menutitle]").val(),
						"url":$("input[name=menuurl]").val(),
						"id":id
					},
					success:function(rs){
						if(rs){//添加成功
							//1、关闭添加前台菜单窗口
							$("#editbox").window('close');
							//2、刷新表格
							$("#dg").datagrid('reload')
							//3、提示添加成功
							$.messager.show({
								title:"提示",
								msg:"菜单编辑成功"
							});
						}else{//添加失败
							$.messager.alert('提示','编辑失败，请重试');
						}
					},
					error:function(err){

					}
				});
			});
		});
	</script>
</body>
</html>
