package projetPOEIspring.poeidata.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import projetPOEIspring.poeidata.api.dto.OrderDto;
import projetPOEIspring.poeidata.exceptions.UnknownResourceException;
import projetPOEIspring.poeidata.mappers.OrderMapper;
import projetPOEIspring.poeidata.services.OrdersService;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/orders")
public class OrderApi {

    private OrdersService ordersService;
    private OrderMapper orderMapper;

    public OrderApi(OrdersService ordersService, OrderMapper orderMapper) {
        this.ordersService = ordersService;
        this.orderMapper = orderMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return the lis of orders")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return all the orders")
    })
    public ResponseEntity<List<OrderDto>> getAll(){
        return ResponseEntity.ok(
                this.ordersService.getAll().stream()
                .map(this.orderMapper::mapToDto)
                .toList()
        );
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Return an order by it's id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return an order find by is id"),
            @ApiResponse(responseCode = "404", description = "order not found")
    })
    public ResponseEntity<OrderDto> getById(@PathVariable final Integer id){
        try{
            return ResponseEntity.ok(this.orderMapper.mapToDto(this.ordersService.getById(id)));
        }catch (UnknownResourceException ure){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }


}
