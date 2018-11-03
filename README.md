# Alipay_Computer_Pay
支付宝支付，电脑网页版，手机扫码电脑支付

在包中Package，案例包CasesPackage，包含了Contains，支付宝Alipay通过Java去完成业务，自己整合的：
1：付款
2：退款
3：商户订单号查询订单
4：交易号与商户订单号查询退款订单

在webapp下的JSP，是支付宝官网提供的操作页面，也可以完成一些操作

这个案例是，电脑网页支付，通过扫码或者账户密码支付。针对于电脑的支付整合测试。

支付宝沙箱测试环境URL：https://openhome.alipay.com/platform/appDaily.htm?tab=info，包含蛮多测试功能的

使用沙箱，需要下载沙箱版支付宝APP，完成操作。账户是沙箱分配的账户不是原有的支付宝账户

需要注意的地方，在cases包中，拥有案例支付，退款，订单查看

需要引入阿里支付宝的sdk，还有一个功能没有完成，就是支付宝返回sign签名验证，验证是否是支付宝的信息
