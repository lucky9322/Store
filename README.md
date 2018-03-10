前面的忘记记录了，后期想起来在补。。。。

-----

2018-02-07

`user`模块

一共包括四个案例

- 案例1 用户注册

	需求：
	
		在(jsp/register.jsp)上填写用户的信息，点击保存 将用户的信息保存到数据库中
		
	步骤分析
		
		1. 设置默认首页(index.jsp)，在默认首页上让其自动的跳转到该页面(jsp/index.jsp)[可以使用重定向实现]
		2. 修改index.jsp上的注册的超链接 进行跳转到(jsp/register.jsp)页面上

			```
			<a href='／store/user?method=registUI'>注册</a>
			```
		3.在userservlet中编写registUI方法。在该方法中完成请求转发到(/jsp/register.jsp)即可。
		4.修改(register.jsp)上的表单信息
			action="/store/user?method=regist"
			method
			为每一个子标签添加name属性
		5.点击注册按钮，向userservlet发送请求
		6.userservlet中编写regist方法
			获取参数，封装成user对象
			调用service完成注册
			生成提示信息，转发 /jsp/msg.jsp
		7.userservice中的操作
			调用dao完成注册操作
			发送邮件
		8.userdao中的操作
			保存到数据库

- 案例2 用户激活

	需求：
		
		用户登录邮箱之后，点击邮箱中的链接，完成用户激活操作
	步骤分析：
	
		1.点击邮箱中的链接，向商城usereservlet发送一个请求
			user?=methodactive&code=****
		2.在userservlet中编写active方法
			接收code
			调用service完成激活
			生成提示信息（激活成功或者失败）
		3. 在service中编写激活方法
			通过code获取用户
				若没有找到：激活失败，提示重新激活或者重新注册
				若找到了：设置激活状态为1，清空code
		4. 在dao中需要编写两个方法
				getByCode
				update
- 案例3 用户登录
- 案例4 用户退出












