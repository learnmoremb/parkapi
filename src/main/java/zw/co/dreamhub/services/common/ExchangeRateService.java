package zw.co.dreamhub.services.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.dreamhub.domain.dto.request.common.ExchangeRateRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.mappers.common.AmountMapper;
import zw.co.dreamhub.domain.mappers.common.ExchangeRateMapper;
import zw.co.dreamhub.domain.models.common.Amount;
import zw.co.dreamhub.domain.models.common.ExchangeRate;
import zw.co.dreamhub.domain.repositories.common.AmountRepository;
import zw.co.dreamhub.domain.repositories.common.ExchangeRateRepository;
import zw.co.dreamhub.exception.NotFoundException;
import zw.co.dreamhub.utils.ResponseUtils;
import zw.co.dreamhub.utils.StringUtils;

import java.util.UUID;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/18
 */

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final AmountRepository amountRepository;
    private final AmountMapper amountMapper;
    private final ExchangeRateMapper mapper;
    private static final String NAME = "exchange rate";

    // todo : view exchange rates with pagiantion

    public ApiResponse<String> create(ExchangeRateRequest request) {
        Amount amount = amountMapper.toEntity(request.amount());
        amountRepository.save(amount);
        ExchangeRate exchangeRate = new ExchangeRate(amount);
        exchangeRateRepository.save(exchangeRate);
        return ResponseUtils.created(StringUtils.SUCCESS);
    }

    public ApiResponse<String> update(UUID id, ExchangeRateRequest request) {
        ExchangeRate exchangeRate = exchangeRateRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new NotFoundException(StringUtils.notFound(NAME)));
        ExchangeRate savedExchangeRate = mapper.partialUpdate(request, exchangeRate);
        exchangeRateRepository.save(savedExchangeRate);
        return ResponseUtils.created(StringUtils.SUCCESS);
    }

    public ApiResponse<String> delete(UUID exchangeRateId) {
        ExchangeRate rate = exchangeRateRepository
                .findById(String.valueOf(exchangeRateId))
                .orElseThrow(() -> new NotFoundException(StringUtils.notFound(NAME)));
        exchangeRateRepository.delete(rate);
        return ResponseUtils.success(StringUtils.SUCCESS);
    }
}
