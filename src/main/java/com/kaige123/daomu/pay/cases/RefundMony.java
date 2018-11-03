package com.kaige123.daomu.pay.cases;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.kaige123.daomu.pay.config.AlipayConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//支付宝 -> 退款
@RestController
public class RefundMony {

    String APP_ID = AlipayConfig.app_id;
    String gatewayUrl = AlipayConfig.gatewayUrl;    //支付宝网关
    String returnUrl = AlipayConfig.return_url;     //回跳地址
    String notifyUrl1 = AlipayConfig.notify_url;    //回调地址
    String APP_PRIVATE_KEY = AlipayConfig.merchant_private_key;     //私钥
    String FORMAT = "JSON";                         //格式
    String CHARSET = AlipayConfig.charset;
    String ALIPAY_PUBLIC_KEY = AlipayConfig.alipay_public_key;      //公钥
    String SIGN_TYPE = AlipayConfig.sign_type;

    @GetMapping("/refun")
    public void refund(@RequestParam String o, @RequestParam String r) throws AlipayApiException {
        AlipayClient alipayClient =
                new DefaultAlipayClient(gatewayUrl, APP_ID, APP_PRIVATE_KEY, "json", "GBK", ALIPAY_PUBLIC_KEY, "RSA2");

        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

        request.setBizContent("{" +
                "\"out_trade_no\":\"" + o + "\"," +                 //商户自己的订单号,自己生成
                "\"trade_no\":\"" + r + "\"," +                     //支付宝交易号
                "\"refund_amount\":\"100.00\"," +                   //退款金额

                "\"out_request_no\":\"HZ01RF001\"," +
                "\"refund_reason\":\"正常退款\"" +
                " }");

        AlipayTradeRefundResponse response = alipayClient.execute(request);

        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }

        System.out.println("result= " + response.getBody());
    }


}
