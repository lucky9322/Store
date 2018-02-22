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
- 案例3 用户登录
- 案例4 用户退出