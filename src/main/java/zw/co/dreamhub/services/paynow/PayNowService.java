package zw.co.dreamhub.services.paynow;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.dreamhub.config.env.PayNowEnv;
import zw.co.dreamhub.domain.dto.request.paynow.PayNowRequest;
import zw.co.dreamhub.domain.dto.response.paynow.PayNowResponse;
import zw.co.paynow.constants.MobileMoneyMethod;
import zw.co.paynow.core.Payment;
import zw.co.paynow.core.Paynow;
import zw.co.paynow.responses.MobileInitResponse;
import zw.co.paynow.responses.StatusResponse;
import zw.co.paynow.responses.WebInitResponse;


/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 1/10/2023
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class PayNowService {

    private final PayNowEnv env;

    private Paynow instance() {
        Paynow paynow = new Paynow(env.getIntegration().getId(), env.getIntegration().getKey());
        paynow.setResultUrl(env.getResultUrl());
        paynow.setReturnUrl(env.getReturnUrl());
        return paynow;
    }

    public PayNowResponse web(PayNowRequest request) {
        Paynow paynow = this.instance();
        Payment payment = paynow.createPayment(request.reference());
        payment.add(request.reference(), request.amount());
        WebInitResponse response = paynow.send(payment);
        if (response.success()) {
            return new PayNowResponse(PayNowResponse.TransactionStatus.SUCCESS, response.pollUrl(), response.redirectURL());
        } else {
            return new PayNowResponse(PayNowResponse.TransactionStatus.FAILED);
        }
    }

    public PayNowResponse mobile(PayNowRequest request) {
        Paynow paynow = this.instance();
        Payment payment = paynow.createPayment(request.reference(), request.email());
        payment.add(request.reference(), request.amount());

        // todo : refactor to handle innbucks

        MobileInitResponse response = paynow.sendMobile(payment,
                request.mobile().replace("263", "0").trim(),
                request.type() == PayNowRequest.MobileMoneyType.PAYNOW_ECOCASH ?
                        MobileMoneyMethod.ECOCASH : MobileMoneyMethod.ONEMONEY);

        if (response.success()) {
            return new PayNowResponse(PayNowResponse.TransactionStatus.SUCCESS, response.pollUrl());
        } else {
            return new PayNowResponse(PayNowResponse.TransactionStatus.FAILED);
        }
    }

    public PayNowResponse.TransactionStatus status(String url) {
        Paynow paynow = this.instance();
        StatusResponse status = paynow.pollTransaction(url);
        return status.isPaid() ? PayNowResponse.TransactionStatus.SUCCESS : PayNowResponse.TransactionStatus.FAILED;
    }

}
