package com.kaige123.daomu.pay.cases;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.kaige123.daomu.pay.config.AlipayConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//支付宝 -> 退款查询
@RestController
public class SelectRefunMony {

    String APP_ID = AlipayConfig.app_id;
    String gatewayUrl = AlipayConfig.gatewayUrl;    //支付宝网关
    String returnUrl = AlipayConfig.return_url;     //回跳地址
    String notifyUrl1 = AlipayConfig.notify_url;    //回调地址
    String APP_PRIVATE_KEY = AlipayConfig.merchant_private_key;     //私钥
    String FORMAT = "JSON";                         //格式
    String CHARSET = AlipayConfig.charset;
    String ALIPAY_PUBLIC_KEY = AlipayConfig.alipay_public_key;      //公钥
    String SIGN_TYPE = AlipayConfig.sign_type;

    @GetMapping("/srefun")
    public void selectRefun(@RequestParam String o, @RequestParam String r) throws AlipayApiException {
        AlipayClient alipayClient =
                new DefaultAlipayClient(gatewayUrl, APP_ID, APP_PRIVATE_KEY, "json", "GBK", ALIPAY_PUBLIC_KEY, "RSA2");

        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();

        request.setBizContent("{" +
                "\"out_trade_no\":\"" + o + "\"," +                 //商户自己的订单号
                "\"trade_no\":\"" + r + "\"," +                     //支付宝交易号
                "\"out_request_no\":\"" + r + "\"," +               //退款的请求号,需填,请求号就是商户自己的订单号,如果不正确,查询信息不完整
                "\"org_pid\":\"2088101117952222\"" +
                "  }");

        AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        System.out.println("result= " + response.getBody());
    }


//    {
//        "alipay_trade_refund_response":
//        {
//            "code":"10000",
//                "msg":"Success",
//                "buyer_logon_id":"von***@sandbox.com",
//                "buyer_user_id":"2088102176783025",
//                "fund_change":"N",
//                "gmt_refund_pay":"2018-11-03 17:29:11",
//                "out_trade_no":"1541237542981",
//                "refund_fee":"100.00",
//                "send_back_fee":"0.00",
//                "trade_no":"2018110322001483020500327726"
//        },
//        "sign":
//        "O1qDmoMTKizBymVJqUXH5iprjDH2BypgZXwSL5TuO4Vwn0NPtU/otM6thcjPgWxPzdt75bDq6te1UXRjZEsXXi+Qr72IDv7UA164XzRPy7891AYw0hZz/2dg78/W7Y4VQhpcGE6XNIfTFyBM6CE9adiZT7qCupEZgMJi9c7SW/LCQX0cPMGfPXsGyLsohhvw9SAl1AHZmKlX1YXdp3VBaa4kH1NNAyoa6IKXpn5Gl6xMfHK/Z4OELHWLP87195qXkIjGrhLL4V41EpGkvnD/RJAz69XKHkDXrAkttZBHeLXtjo60RaH/MTCXcaqMcsIxoK88zw2fvuH8x91ajW1i1g=="
//    }

}
