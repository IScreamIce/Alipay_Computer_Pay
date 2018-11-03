package com.kaige123.daomu.pay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092000552402";

    // 商户私钥，您的PKCS8格式RSA2私钥，这是自己在支付宝生成工具生成的秘钥粘贴过来
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCJ/IwmXByOE8Btl+r6VKTn8F85MaK5BZ3fgdbag0qJbMnVgalKFiloaFlFp3IfaohS8WWaJuEkGzCMuW+7b9kqb4Y28sd/CI0LuWLMc6tBfv9wvHjEgjakNnuSBmSJ7w+2kxgA4ul9d8aV2K8Z1R3hidxSLPweSuw8W81j97LmjqJ1SXJthKKF3ANZxgTX4lpPF2vVw9d+tRdFfhi5nWVFXpQiCJdLrum9VK7+p+zH63kFg40t8F361Bmyrgl4MSGJNpaHUoNA3ziGWxzyYvFLQjkiR+vqaxDTFo1/yQHyWgr/Vyx4U5u6m+QoVTBrE/zIqrwyu+AKu3QjQSNaqCFZAgMBAAECggEAD2oH2e0mP3TTZSq4VBiKh2JhsMjrrZGEDki50JUkCW/+p/whBiatn30wo8hNxlanPeS7fdnkAp2K8d1xLtYSw1vL+W1rfbRd1TZzY/Vz5xAhxt02/HSzV+rCKRDPwFzVO4b5AnhWru5nX6vh+5fEC8LTutZNn7Cs7R0wN3+fG4Qe9KY2GxboYGZUa7M8CX9m3eb7uNbGD+GD/8nYM44sBBKc9TwubxczWYbALO2DDfXNx4TspqF1PUBj19WUrB5XYBe9Whu/87SpzfsaURkVuTN1loMVMubpNSuU+KnkhjNgnC3wzMmSecr8FvDf23Qc505GkvjNTNN5eOAeRm4jUQKBgQDdAQ9YXny3O8mGylAOref9s31xAwMnFexkRnBIAst8W5iUVILcVXEe01xBZuqcIas20s40jRo3YW7p0Fq3FMM+FZtu4s9xpXtPljUCPt74a7eSYTADiYssGwAwm+KapkPC247Hb5MxKUlLt1UBa06DQlJX+McslAN5npkBRtKLnQKBgQCf1iqMtTuezcWHS2PtZXpd0jEN4xC77QMrczkJNVId/GOrHan3+Kq8uf2vB2cnnZV8BhCSTk74LAdUc+HMXfBWIZE+TqCUXvqsdMAXr91kLgDQt30dbN1fNXRi90/cMQ9vSMtnEmPG8Kn7/2Qp88dH0h1MyguhZfoXq0+E6Z0V7QKBgHypB2XaTlDxCXjdj5DoKY7ieuSPRWz7oc7iKJ2F7g/kfslUbkgobdlscYJ2semF2FThTe04lkwcynpay4Cv1n2jpHxEtayuIkM/QAFXQ2AvKmoIJhQotncXd+DzXTvMgzZbsErwtb8qQazS6cEWLUA+3cG/k2Giz97/ReEBeOvFAoGAOx5Lvp+J3cLePJx16WiPSgRkgG5r0hZoBboobm96X/zqIHBoXX9sJ0HjwslKLizrDWRw3Kx6KPCgBWyXJ6/XJQRlZFZQrpAyx3anVhuGF3esdYZokGmjt4Nd+PzgXOEIhHC4SvdSUFLTjJF5ysqP+tCMZcqVYq0FQ5vpXY/KukECgYAI9uBw4RbnqvbjHhe3DjnB894qRzXiWmKLw3v5iQFLNxZxHbd67JfygIoKKAgGFdZ9qMfApDaWjeDriWZIhjt6DKftVhqvS4lZ7AJBjPDT8Zg9omdqyK3VDvNjtX61xKbD34rdXHehnMEDoSEc3YTyavpyDjbEdmTAUV443vO6/Q==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。这是自己生成共钥，上传到支付宝，并再粘贴到这里
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuP5cshqcfernbINpU/RB2En4k3YVe6GHCVnef5+K/LFUcXMa99QzBmT/saEg1vrD+obBIpqJyZQ3ILmgNKCE+LNskGpLk/9iaofSx3zUM3g6LSA9P7QcLm4DlLdimSy1CqGVBQCpTzL63p43D/AODUqVhNQknQRnRGnrw3CUF7b3u2QFccawbg8mo3vlUcXiT8AeL58d0e9XuUM4tFzDWJHFPoOXyTzY0+Mhl+I/6uckSodkjZIRbuCFIPNv23Xphe/MV1M4+DylCkWOkeHa+G5vMGkruWFXXrc9YcwH6pmxsCGu+jJw1y0/EVgzzOvoaWLGagia1yuiFPoX8NW+tQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://daomu.kaige123.com/zfbpay/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}