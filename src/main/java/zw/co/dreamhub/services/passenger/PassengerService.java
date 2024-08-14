package zw.co.dreamhub.services.passenger;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.dreamhub.domain.dto.request.passenger.PassengerRequest;
import zw.co.dreamhub.domain.dto.response.ApiResponse;
import zw.co.dreamhub.domain.mappers.passenger.PassengerMapper;
import zw.co.dreamhub.domain.models.passenger.Passenger;
import zw.co.dreamhub.domain.repositories.common.AddressRepository;
import zw.co.dreamhub.domain.repositories.passenger.PassengerRepository;
import zw.co.dreamhub.exception.NotFoundException;
import zw.co.dreamhub.utils.ResponseUtils;
import zw.co.dreamhub.utils.StringUtils;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2023/12/20
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final AddressRepository addressRepository;

    private final PassengerMapper mapper;
    private static final String NAME = "user";

    // todo : if you specifiy cascade types , you won't need to import individual repositories eg in passenger (you won't need to import address repository to persist before saving the passenger entity)
    // todo : concerning the 'application.yaml', you can have your own local version eg application-default.yml and load it with your own confis, just make sure you don't push it to git
    // todo : you can use mapstruct mapper when performing updates

    @Transactional
    public ApiResponse<String> update(PassengerRequest request) {

        Passenger passenger = passengerRepository.findPassengerByUser_Id(request.userId())
                .orElseThrow(() -> new NotFoundException(StringUtils.notFound(NAME)));

        Passenger updatedPassenger = mapper.partialUpdate(request, passenger);

        addressRepository.saveAll(updatedPassenger.getAddresses());
        passengerRepository.save(updatedPassenger);
        return ResponseUtils.success(StringUtils.SUCCESS);
    }


}
