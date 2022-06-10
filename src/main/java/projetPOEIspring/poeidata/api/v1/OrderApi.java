package projetPOEIspring.poeidata.api.v1;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import projetPOEIspring.poeidata.api.dto.OrderDto;
import projetPOEIspring.poeidata.exceptions.OrderException;
import projetPOEIspring.poeidata.exceptions.UnknownResourceException;
import projetPOEIspring.poeidata.mappers.OrderMapper;
import projetPOEIspring.poeidata.services.OrdersService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/orders")
public class OrderApi {

    private OrdersService orderService;
    private OrderMapper orderMapper;

    public OrderApi(OrdersService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Return the lis of orders")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return all the orders")
    })
    public ResponseEntity<List<OrderDto>> getAll(){
        return ResponseEntity.ok(
                this.orderService.getAll().stream()
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
            return ResponseEntity.ok(this.orderMapper.mapToDto(this.orderService.getById(id)));
        }catch (UnknownResourceException ure){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    @Operation(summary = "Create an order")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "404", description = "can't create an order")
    })
    public ResponseEntity<OrderDto> create(@RequestBody final OrderDto orderDto){
        try{
            OrderDto orderDtoResponse =
                    this.orderMapper.mapToDto(
                            this.orderService.create(this.orderMapper.mapToModel(orderDto))
                    );
            return ResponseEntity.created(URI.create("/v1/orders/" + orderDtoResponse.getId()))
                    .body(orderDtoResponse);
        } catch (OrderException orderException){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, orderException.getMessage());
        }

    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete an order by the given Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "No order found by the given Id"),
            @ApiResponse(responseCode = "403", description = "can't delete an order by the given Id")
    })
    public ResponseEntity<Void> delete(@PathVariable final Integer id){
        try{
            this.orderService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    @Operation(summary = "Updated order")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content")
    })
    public ResponseEntity<Void> update(
            @PathVariable final Integer id,
            @RequestBody OrderDto orderDto)
    {
        try{
            orderDto.setId(id);
            this.orderService.update(this.orderMapper.mapToModel(orderDto));
            return ResponseEntity.noContent().build();
        } catch (UnknownResourceException ure){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }


}
