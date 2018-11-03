package com.kaige123.daomu.pay.cases;

import com.alipay.api.*;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.kaige123.daomu.pay.config.AlipayConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

//支付宝 -> 支付
@Controller
public class PayMony {

    private static String APP_ID = AlipayConfig.app_id;
    private static String gatewayUrl = AlipayConfig.gatewayUrl;    //支付宝网关
    private static String returnUrl = AlipayConfig.return_url;     //回跳地址
    private static String notifyUrl1 = AlipayConfig.notify_url;    //回调地址
    private static String APP_PRIVATE_KEY = AlipayConfig.merchant_private_key;     //私钥
    private static String FORMAT = "JSON";                         //格式
    private static String CHARSET = AlipayConfig.charset;
    private static String ALIPAY_PUBLIC_KEY = AlipayConfig.alipay_public_key;      //公钥
    private static String SIGN_TYPE = AlipayConfig.sign_type;

    @GetMapping("/pay")
    public void doPost(HttpServletRequest httpRequest,
                       HttpServletResponse httpResponse) throws ServletException, IOException {

        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                APP_ID, APP_PRIVATE_KEY, FORMAT,
                CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE); //获得初始化的AlipayClient

        String order = System.currentTimeMillis() + "";
        System.out.println("订单号order= " + order);

        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();      //创建API对应的request
        alipayRequest.setReturnUrl(returnUrl);             //回跳地址
        alipayRequest.setNotifyUrl(notifyUrl1);            //回调地址

        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"" + order + "\"," +        //商户ID
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":100," +                                          //金额
                "    \"subject\":\"Iphone6 16G\"," +                                    //商品主体
                "    \"body\":\"Iphone6 16G\"," +                                       //商品描述信息
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +   //可不传,公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。参数经UrlEncode之后才可以发送给支付宝
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088022578780823\"" +                //可不传,PID
                "    }" +
                "  }");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }


    //    从异步通知notifyUrl1指定的服务器中notify_url.jsp页面,请求头收集的支付宝通知信息
    //    通知信息描述:商品订单信息。{商品信息,买家与卖家的基本信息}

    /*{
        "gmt_create":"2018-11-03 14:09:22",         //创建时间,格林尼治时间
            "charset":"utf-8",
            "subject":"Iphone6 16G",                //商品
            //签名
            "sign":"tbwMdaRl2+1cNnmNvPhAcOnS3/NdwTiE12voOP7lUjorm/C8VLEQN0dQGxokToV6RuMGYETKTNog1S4jZEG/nsTwlpG32dDzS7etA/WUL/NizLT+E0aw3QEksoaSlFlF7N+FU3arKfsdUqlk4hVJ4Lbs6Gkrni4Mp/FMFU+K5hhD/zi+Nlhd4WPJEuuSoQQ5NdI3EmsihwkL43+qCz3M/SsijUoJw1pxCd4OO4y3Y0J6LkO4TwxYPzDt6Tx/DiKP2Np/+adoh0SwafH3wjXCN4uqP7optKPVaHlUcdEVl8Sq0+Q2Z2kRBrbKJoLpZv6LRHEHa/sb1nhcY9og+Im92A==",
            "buyer_id":"2088102176783025",          //买家的ID
            "body":"Iphone6 16G",                   //内容
            "invoice_amount":"0.01",
            "notify_id":"8ccd6da85d6a83f799fd18712dbbdd3g5p",
            "fund_bill_list":"[{\"amount\":\"0.01\",\"fundChannel\":\"ALIPAYACCOUNT\"}]",
            "notify_type":"trade_status_sync",      //通知类型
            "trade_status":"TRADE_SUCCESS",         //贸易状态
            "receipt_amount":"0.01",                //收到金额,1毛
            "app_id":"2016092000552402",
            "buyer_pay_amount":"0.01",              //买家支付金额,1毛
            "sign_type":"RSA2",
            "seller_id":"2088102176424074",         //商家的UID
            "gmt_payment":"2018-11-03 14:09:33",    //通知时间
            "notify_time":"2018-11-03 14:09:34",    //支付时间
            "passback_params":"merchantBizType%3d3C%26merchantBizNo%3d2016010101111",
            "version":"1.0",
            "out_trade_no":"1541225609445",         //订单号
            "total_amount":"0.01",
            "trade_no":"2018110322001483020500327435",      //支付宝交易号
            "auth_app_id":"2016092000552402",       //APPID
            "point_amount":"0.00"
    }*/


    public static void main(String[] args) throws AlipayApiException {
        Map map = new HashMap();
//        "{\"gmt_create\":\"2018-11-03 14:09:22\",\"charset\":\"utf-8\",\"subject\":\"Iphone6 16G\",\"sign\":\"tbwMdaRl2+1cNnmNvPhAcOnS3/NdwTiE12voOP7lUjorm/C8VLEQN0dQGxokToV6RuMGYETKTNog1S4jZEG/nsTwlpG32dDzS7etA/WUL/NizLT+E0aw3QEksoaSlFlF7N+FU3arKfsdUqlk4hVJ4Lbs6Gkrni4Mp/FMFU+K5hhD/zi+Nlhd4WPJEuuSoQQ5NdI3EmsihwkL43+qCz3M/SsijUoJw1pxCd4OO4y3Y0J6LkO4TwxYPzDt6Tx/DiKP2Np/+adoh0SwafH3wjXCN4uqP7optKPVaHlUcdEVl8Sq0+Q2Z2kRBrbKJoLpZv6LRHEHa/sb1nhcY9og+Im92A==\",\"buyer_id\":\"2088102176783025\",\"body\":\"Iphone6 16G\",\"invoice_amount\":\"0.01\",\"notify_id\":\"8ccd6da85d6a83f799fd18712dbbdd3g5p\",\"fund_bill_list\":\"[{\\\"amount\\\":\\\"0.01\\\",\\\"fundChannel\\\":\\\"ALIPAYACCOUNT\\\"}]\",\"notify_type\":\"trade_status_sync\",\"trade_status\":\"TRADE_SUCCESS\",\"receipt_amount\":\"0.01\",\"app_id\":\"2016092000552402\",\"buyer_pay_amount\":\"0.01\",\"sign_type\":\"RSA2\",\"seller_id\":\"2088102176424074\",\"gmt_payment\":\"2018-11-03 14:09:33\",\"notify_time\":\"2018-11-03 14:09:34\",\"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\",\"version\":\"1.0\",\"out_trade_no\":\"1541225609445\",\"total_amount\":\"0.01\",\"trade_no\":\"2018110322001483020500327435\",\"auth_app_id\":\"2016092000552402\",\"point_amount\":\"0.00\"}"
        String resultInfo = "gmt_create:2018-11-03 14:09:22,charset:utf-8,subject:Iphone6 16G,buyer_id:2088102176783025,body:Iphone6 16G,invoice_amount:0.01,notify_id:8ccd6da85d6a83f799fd18712dbbdd3g5p,fund_bill_list:[{\\amount\\:\\0.01\\,\\fundChannel\\:\\ALIPAYACCOUNT\\}],notify_type:trade_status_sync,trade_status:TRADE_SUCCESS,receipt_amount:0.01,app_id:2016092000552402,buyer_pay_amount:0.01,seller_id:2088102176424074,gmt_payment:2018-11-03 14:09:33,notify_time:2018-11-03 14:09:34,passback_params:merchantBizType%3d3C%26merchantBizNo%3d2016010101111,version:1.0,out_trade_no:1541225609445,total_amount:0.01,trade_no:2018110322001483020500327435,auth_app_id:2016092000552402,point_amount:0.00";
        System.out.println(resultInfo);

        StringTokenizer items;
        for (StringTokenizer entrys = new StringTokenizer(resultInfo, "&"); entrys.hasMoreTokens(); map.put(items.nextToken(), items.hasMoreTokens() ? ((items.nextToken())) : null)) {
            items = new StringTokenizer(entrys.nextToken(), "=");
        }
        map.put("sign", "tbwMdaRl2+1cNnmNvPhAcOnS3/NdwTiE12voOP7lUjorm/C8VLEQN0dQGxokToV6RuMGYETKTNog1S4jZEG/nsTwlpG32dDzS7etA/WUL/NizLT+E0aw3QEksoaSlFlF7N+FU3arKfsdUqlk4hVJ4Lbs6Gkrni4Mp/FMFU+K5hhD/zi+Nlhd4WPJEuuSoQQ5NdI3EmsihwkL43+qCz3M/SsijUoJw1pxCd4OO4y3Y0J6LkO4TwxYPzDt6Tx/DiKP2Np/+adoh0SwafH3wjXCN4uqP7optKPVaHlUcdEVl8Sq0+Q2Z2kRBrbKJoLpZv6LRHEHa/sb1nhcY9og+Im92A==");

        System.out.println("result= " + AlipaySignature.rsaCheckV1(map, ALIPAY_PUBLIC_KEY, CHARSET, "RSA2"));
    }
}
