package projetPOEIspring.poeidata.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projetPOEIspring.poeidata.api.dto.OrderDto;
import projetPOEIspring.poeidata.mappers.OrderMapper;
import projetPOEIspring.poeidata.services.OrdersService;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/orders")
public class OrderApi {

    private OrdersService service;
    private OrderMapper orderMapper;

    public OrderApi(OrdersService service, OrderMapper orderMapper) {
        this.service = service;
        this.orderMapper = orderMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return the lis of orders")
    public ResponseEntity<List<OrderDto>> getAll(){
        return ResponseEntity.ok(
                this.service.getAll().stream()
                .map(this.orderMapper::mapToDto)
                .toList()
        );
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Return an order by it's id")
    public ResponseEntity<OrderDto> getById(){



    }


}
