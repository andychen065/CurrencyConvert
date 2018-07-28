1.开发要点及思路
a.无参数或者第一个参数是--help，则会提示用法。
b.提供4个可用参数--field, --multiplier， -i， -o。
c.--field 参数是必填参数，其余3个未输入则取默认值1, data.csv 和 data_fr.csv
d.输出文件中格式化汇率，输出格式为本比货币格式，法国默认格式欧元符号在金额之后，可能与spec要求不同。
e.程序不使用CSV第三方包，只使用JAVA 自带IO类解析CSV文件。
f.输出汇率格式中带逗号，会破坏CSV的字段顺序，应该在输出时用双引号包裹有逗号的值。
g.参数必填项和可选项，以及显式参数名(--field和--multiplier)和隐式参数名(-i和-o)


2.功能介绍
本程序是一个美元USD到欧元EUR的转换工具。实现了在CSV文件中指定汇率列号，批量转换汇率并输入输出文件。
基于jdk7开发并向下兼容，如果运行环境高于jdk7，可能会提示版本错误，请根据源码重新编译运行。
目录里提供currency_convert.bat和currency_convert.sh，运行之前请修改路径。


3.运行方法
a.请解压到任意路径。
b.如果运行环境是Windows，请在开始菜单\运行里执行CMD，打开DOS窗口，CD进入解压目录，运行程序。具体命令如下:

	rem 进入解压目录
	cd /d 目标目录 

	rem 运行批处理程序
	currency_convert.bat --field 2 --multiplier 0.6 data.csv data_fr.csv
	currency_convert.bat --field 2 --multiplier 0.6 stdin stdout

	或者
	rem 直接使用java命令运行,注意请确认是否已提前安装jdk或者jre
	java -Xmx256M -Xms256M -classpath ./classes com.andy.practice.CurrencyConvert --field 2 --multiplier 0.6 data.csv data_fr.csv

c.如果运行环境是linux, 请进入终端，CD进入解压目录，运行程序。具体命令如下:

	# 进入解压目录
	cd 目标目录

	# 运行Shell程序
	currency_convert.sh --field 2 --multiplier 0.6 data.csv data_fr.csv
	currency_convert.sh --field 2 --multiplier 0.6 stdin stdout
	
	或者
	# 直接使用java命令运行,注意请确认是否已提前安装jdk或者jre
	java -Xmx256M -Xms256M -classpath ./classes com.andy.practice.CurrencyConvert --field 2 --multiplier 0.6 data.csv data_fr.csv

4.用法说明
a.运行参数选择1: currency_convert.bat --field 2, 则表示其余3个未输入则取默认值1, data.csv 和 data_fr.csv
b.运行参数选择2: currency_convert.bat --field 2 --multiplier 0.6, 则表示其余2个未输入则取默认值1, data.csv 和 data_fr.csv
c.运行参数选择3: currency_convert.bat --field 2 --multiplier 0.6 stdin data_fr.csv,则表示接受标准输入数据。
d.运行参数选择3: currency_convert.bat --field 2 --multiplier 0.6 data.csv stdout,则表示转换结果从标准输出。

5.注意事项
a.如果参数-i 的值为stdin,则接收从窗口输入的数据，以:q加回车来结束输入。
b.如果参数-o 的值为stdout,则会输出结果在屏幕，DOS下有可能出现欧元符号乱码，这是因为DOS的字符集默认是GBK，而代码输出是UTF-8，解决方法如下
	DOS窗口执行chcp 65001，代码页就被变成UTF-8了。同时，在命令行标题栏上点击右键，选择"属性"->"字体"，将字体修改为True Type字体"Lucida Console"。
c.由于currency_convert.sh在windows下编写，在linux下运行前请将脚本做dos2unix操作。