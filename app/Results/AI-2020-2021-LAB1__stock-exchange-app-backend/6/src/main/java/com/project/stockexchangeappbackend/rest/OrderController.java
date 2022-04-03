package com.project.stockexchangeappbackend.rest;
 import com.project.stockexchangeappbackend.dto.CreateOrderDTO;
import com.project.stockexchangeappbackend.dto.ErrorResponse;
import com.project.stockexchangeappbackend.dto.OrderDTO;
import com.project.stockexchangeappbackend.dto.TransactionDTO;
import com.project.stockexchangeappbackend.repository.specification.AllOrdersSpecification;
import com.project.stockexchangeappbackend.repository.specification.TransactionSpecification;
import com.project.stockexchangeappbackend.service.OrderService;
import com.project.stockexchangeappbackend.service.TransactionService;
import io.swagger.annotations;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import springfox.documentation.annotations.ApiIgnore;
import javax.validation.Valid;
import com.project.stockexchangeappbackend.Interface.OrderService;
import com.project.stockexchangeappbackend.Interface.TransactionService;
@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
@AllArgsConstructor
@Api(value = "Orders", description = "REST API for orders' management", tags = "Orders")
@ApiResponses({ @ApiResponse(code = 401, message = "Unauthorized.") })
public class OrderController {

 private  OrderService orderService;

 private  TransactionService transactionService;

 private  ModelMapper mapper;


@PostMapping
@PreAuthorize("hasRole('USER')")
@ApiOperation(value = "Create new order")
@ApiResponses({ @ApiResponse(code = 200, message = "Order was successfully created."), @ApiResponse(code = 400, message = "The request could not be understood or was missing required parameters.", response = ErrorResponse.class), @ApiResponse(code = 403, message = "Access Denied.") })
public void createOrder(CreateOrderDTO orderDTO){
    orderService.createOrder(orderDTO);
}


@GetMapping("/{id}")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@ApiOperation(value = "Retrieve order by id", response = OrderDTO.class)
@ApiResponses({ @ApiResponse(code = 200, message = "Order was successfully retrieved."), @ApiResponse(code = 404, message = "Given order not found.", response = ErrorResponse.class) })
public OrderDTO getOrderDetails(Long id){
    return mapper.map(orderService.findOrderById(id), OrderDTO.class);
}


@PostMapping("/{id}/deactivation")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
@ApiOperation(value = "Deactivate order")
@ApiResponses({ @ApiResponse(code = 200, message = "Order was successfully deactivated."), @ApiResponse(code = 400, message = "The request could not be understood or was missing required parameters.", response = ErrorResponse.class), @ApiResponse(code = 403, message = "Access Denied."), @ApiResponse(code = 404, message = "Order not found.", response = ErrorResponse.class) })
public void deactivateOrder(Long id){
    orderService.deactivateOrder(id);
}


@GetMapping
@PreAuthorize("hasAnyRole('ADMIN','USER')")
@ApiOperation(value = "Page and filter orders", notes = "Required one role of: ADMIN, USER \n" + "Given date must be in one format of: \n - yyyy-MM-ddThh:mm:ss.SSSZ (Z means Greenwich zone), " + "\n - yyyy-MM-ddThh:mm:ss.SSS-hh:mm \n - yyyy-MM-ddThh:mm:ss.SSS%2Bhh:mm (%2B means +)")
@ApiResponses(@ApiResponse(code = 200, message = "Successfully paged and filtered orders."))
@ApiImplicitParams({ @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N).", defaultValue = "0"), @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "20"), @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. Multiple sort criteria are supported."), @ApiImplicitParam(name = "name", dataType = "string", paramType = "query", value = "Filtering criteria for field `name` (omitted if null)"), @ApiImplicitParam(name = "abbreviation", dataType = "string", paramType = "query", value = "Filtering criteria for field `abbreviation`. (omitted if null)"), @ApiImplicitParam(name = "price>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `price`. (omitted if null)"), @ApiImplicitParam(name = "price<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `price`. (omitted if null)"), @ApiImplicitParam(name = "price", dataType = "integer", paramType = "query", value = "Filtering criteria for field `price`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "amount>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "priceType", dataType = "string", paramType = "query", value = "Filtering criteria for field `priceType`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "orderType", dataType = "string", paramType = "query", value = "Filtering criteria for field `orderType`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "creationDate>", dataType = "date", paramType = "query", value = "Filtering criteria for field `creationDate`. (omitted if null)"), @ApiImplicitParam(name = "creationDate<", dataType = "date", paramType = "query", value = "Filtering criteria for field `creationDate`. (omitted if null)"), @ApiImplicitParam(name = "dateExpiration>", dataType = "date", paramType = "query", value = "Filtering criteria for field `dateExpiration`. (omitted if null)"), @ApiImplicitParam(name = "dateExpiration<", dataType = "date", paramType = "query", value = "Filtering criteria for field `dateExpiration`. (omitted if null)"), @ApiImplicitParam(name = "dateClosing>", dataType = "date", paramType = "query", value = "Filtering criteria for field `creationClosing`. (omitted if null)"), @ApiImplicitParam(name = "dateClosing<", dataType = "date", paramType = "query", value = "Filtering criteria for field `creationClosing`. (omitted if null)"), @ApiImplicitParam(name = "active", dataType = "boolean", paramType = "query", value = "Filtering criteria for state of order. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "tag", dataType = "string", paramType = "query", value = "Filtering criteria for field `tag`. Param is exact value.  (omitted if null)") })
public Page<OrderDTO> getOrders(Pageable pageable,AllOrdersSpecification allOrdersSpecification){
    return orderService.findAllOrders(pageable, allOrdersSpecification).map(order -> mapper.map(order, OrderDTO.class));
}


@GetMapping("/{id}/transactions")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
@ApiOperation(value = "Page and filter given order's transactions", notes = "Required one role of: ADMIN, USER \n" + "Given date must be in one format of: \n - yyyy-MM-ddThh:mm:ss.SSSZ (Z means Greenwich zone), " + "\n - yyyy-MM-ddThh:mm:ss.SSS-hh:mm \n - yyyy-MM-ddThh:mm:ss.SSS%2Bhh:mm (%2B means +)")
@ApiResponses({ @ApiResponse(code = 200, message = "Successfully paged and filtered order's transactions."), @ApiResponse(code = 400, message = "The request could not be understood or was missing required parameters.", response = ErrorResponse.class), @ApiResponse(code = 404, message = "Order not found.", response = ErrorResponse.class) })
@ApiImplicitParams({ @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N).", defaultValue = "0"), @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "20"), @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. Multiple sort criteria are supported."), @ApiImplicitParam(name = "date>", dataType = "date", paramType = "query", value = "Filtering criteria for field `date`. (omitted if null)"), @ApiImplicitParam(name = "date<", dataType = "date", paramType = "query", value = "Filtering criteria for field `date`. (omitted if null)"), @ApiImplicitParam(name = "amount>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "unitPrice>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `unitPrice`. (omitted if null)"), @ApiImplicitParam(name = "unitPrice<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `unitPrice`. (omitted if null)"), @ApiImplicitParam(name = "unitPrice", dataType = "integer", paramType = "query", value = "Filtering criteria for field `unitPrice`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "name", dataType = "string", paramType = "query", value = "Filtering criteria for field `name` (omitted if null)"), @ApiImplicitParam(name = "abbreviation", dataType = "string", paramType = "query", value = "Filtering criteria for field `abbreviation`. (omitted if null)") })
public Page<TransactionDTO> getTransactionsByOrder(Long orderId,Pageable pageable,TransactionSpecification transactionSpecification){
    return transactionService.getTransactionsByOrder(pageable, transactionSpecification, orderId).map(transaction -> mapper.map(transaction, TransactionDTO.class));
}


}