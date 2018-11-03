package com.kaige123.daomu.pay.cases;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.kaige123.daomu.pay.config.AlipayConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//支付宝 -> 订单查询
@RestController
public class SelectOrder {

    String APP_ID = AlipayConfig.app_id;
    String gatewayUrl = AlipayConfig.gatewayUrl;    //支付宝网关
    String returnUrl = AlipayConfig.return_url;     //回跳地址
    String notifyUrl1 = AlipayConfig.notify_url;    //回调地址
    String APP_PRIVATE_KEY = AlipayConfig.merchant_private_key;     //私钥
    String FORMAT = "JSON";                         //格式
    String CHARSET = AlipayConfig.charset;
    String ALIPAY_PUBLIC_KEY = AlipayConfig.alipay_public_key;      //公钥
    String SIGN_TYPE = AlipayConfig.sign_type;

    @GetMapping("/order")
    public String testTradeQuery(@RequestParam String order) throws Exception {
        AlipayClient alipayClient =
                new DefaultAlipayClient(gatewayUrl, APP_ID, APP_PRIVATE_KEY, "json", "GBK", ALIPAY_PUBLIC_KEY, "RSA2");
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(order);   //订单号
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizModel(model);
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        System.out.println("response: "+response.getBody());
        return "response: " + response.getBody();
    }

    //查询订单号1541225609445,支付宝反馈的信息,订单查询返回:买家与买家的基本信息

    /*{
        "alipay_trade_query_response":
        {
                "code":"10000",
                "msg":"Success",
                "buyer_logon_id":"von***@sandbox.com",  //买家账号,账号比如邮箱等,但不是UID
                "buyer_pay_amount":"0.00",
                "buyer_user_id":"2088102176783025",     //买家用户的ID
                "buyer_user_type":"PRIVATE",
                "invoice_amount":"0.00",
                "out_trade_no":"1541225609445",         //查询的订单号
                "point_amount":"0.00",
                "receipt_amount":"0.00",
                "send_pay_date":"2018-11-03 14:09:33",
                "total_amount":"0.01",
                "trade_no":"2018110322001483020500327435",
                "trade_status":"TRADE_SUCCESS"
        },
                //签名
                "sign":"CJV+Kn0Fv6TyundoR5aX28PL6hW+aWszon834nT3W83Od0HeZO4xKo8PgTgR1c0D7X/71L98CHjMoALHq/lsnUpFU7SqWzelaOklM4FgL+fxszV70lXXp1nObCeWWjQiLAiVPnqFHntonqJZTEUXQUWOQ4ia/XbJ23E4DdRSySNTuInZ2ctBdDzl0Rff2BhD1UtxA9hy/aG/VjTGW99o2/z/7z5Zfy/8YYrQn3f8qYEG0TiStorLBI/EYxLCaYXKlHYp/xtMptiOt6F9r+Xdw4hrdGfYbaV4fXJe9/k+puNnBp1MsumMFVd72Lmz0PMrMp3jwvalUWp5JBH4DKKc6Q=="
    }*/

}
