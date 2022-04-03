package com.project.stockexchangeappbackend.rest;
 import com.project.stockexchangeappbackend.dto;
import com.project.stockexchangeappbackend.repository.specification.AllOrdersSpecification;
import com.project.stockexchangeappbackend.repository.specification.ResourceSpecification;
import com.project.stockexchangeappbackend.repository.specification.TransactionSpecification;
import com.project.stockexchangeappbackend.repository.specification.UserSpecification;
import com.project.stockexchangeappbackend.service.OrderService;
import com.project.stockexchangeappbackend.service.ResourceService;
import com.project.stockexchangeappbackend.service.TransactionService;
import com.project.stockexchangeappbackend.service.UserService;
import io.swagger.annotations;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import springfox.documentation.annotations.ApiIgnore;
import javax.validation.Valid;
import java.security.Principal;
import com.project.stockexchangeappbackend.Interface.ResourceService;
import com.project.stockexchangeappbackend.Interface.OrderService;
import com.project.stockexchangeappbackend.Interface.TransactionService;
@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
@AllArgsConstructor
@Api(value = "Users", description = "REST API for users' management", tags = "Users")
@ApiResponses({ @ApiResponse(code = 401, message = "Unauthorized.") })
public class UsersController {

 private  UserService userService;

 private  ResourceService resourceService;

 private  OrderService orderService;

 private  TransactionService transactionService;

 private  ModelMapper mapper;


@GetMapping("/order/owned")
@PreAuthorize("hasRole('USER')")
@ApiOperation(value = "Page and filter logged in user's orders", notes = "Required role of: USER \n" + "Given date must be in one format of: \n - yyyy-MM-ddThh:mm:ss.SSSZ (Z means Greenwich zone), " + "\n - yyyy-MM-ddThh:mm:ss.SSS-hh:mm \n - yyyy-MM-ddThh:mm:ss.SSS%2Bhh:mm (%2B means +)")
@ApiResponses({ @ApiResponse(code = 200, message = "Successfully paged and filtered logged user's orders."), @ApiResponse(code = 403, message = "Access Denied.") })
@ApiImplicitParams({ @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N).", defaultValue = "0"), @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "20"), @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. Multiple sort criteria are supported."), @ApiImplicitParam(name = "name", dataType = "string", paramType = "query", value = "Filtering criteria for field `name` (omitted if null)"), @ApiImplicitParam(name = "abbreviation", dataType = "string", paramType = "query", value = "Filtering criteria for field `abbreviation`. (omitted if null)"), @ApiImplicitParam(name = "price>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `price`. (omitted if null)"), @ApiImplicitParam(name = "price<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `price`. (omitted if null)"), @ApiImplicitParam(name = "price", dataType = "integer", paramType = "query", value = "Filtering criteria for field `price`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "amount>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "priceType", dataType = "string", paramType = "query", value = "Filtering criteria for field `priceType`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "orderType", dataType = "string", paramType = "query", value = "Filtering criteria for field `orderType`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "creationDate>", dataType = "date", paramType = "query", value = "Filtering criteria for field `creationDate`. (omitted if null)"), @ApiImplicitParam(name = "creationDate<", dataType = "date", paramType = "query", value = "Filtering criteria for field `creationDate`. (omitted if null)"), @ApiImplicitParam(name = "dateExpiration>", dataType = "date", paramType = "query", value = "Filtering criteria for field `dateExpiration`. (omitted if null)"), @ApiImplicitParam(name = "dateExpiration<", dataType = "date", paramType = "query", value = "Filtering criteria for field `dateExpiration`. (omitted if null)"), @ApiImplicitParam(name = "dateClosing>", dataType = "date", paramType = "query", value = "Filtering criteria for field `creationClosing`. (omitted if null)"), @ApiImplicitParam(name = "dateClosing<", dataType = "date", paramType = "query", value = "Filtering criteria for field `creationClosing`. (omitted if null)"), @ApiImplicitParam(name = "active", dataType = "boolean", paramType = "query", value = "Filtering criteria for state of order. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "tag", dataType = "string", paramType = "query", value = "Filtering criteria for field `tag`. Param is exact value.  (omitted if null)") })
public Page<OrderDTO> getOwnedOrders(Pageable pageable,AllOrdersSpecification specification){
    return orderService.getOwnedOrders(pageable, specification).map(order -> mapper.map(order, OrderDTO.class));
}


@GetMapping("/transaction/owned")
@PreAuthorize("hasRole('USER')")
@ApiOperation(value = "Page and filter logged in user's owned transactions", notes = "Required role of: USER \n" + "Given date must be in one format of: \n - yyyy-MM-ddThh:mm:ss.SSSZ (Z means Greenwich zone), " + "\n - yyyy-MM-ddThh:mm:ss.SSS-hh:mm \n - yyyy-MM-ddThh:mm:ss.SSS%2Bhh:mm (%2B means +)")
@ApiResponses({ @ApiResponse(code = 200, message = "Successfully paged and filtered user's owned transactions."), @ApiResponse(code = 403, message = "Access Denied.") })
@ApiImplicitParams({ @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N).", defaultValue = "0"), @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "20"), @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. Multiple sort criteria are supported."), @ApiImplicitParam(name = "date>", dataType = "date", paramType = "query", value = "Filtering criteria for field `date`. (omitted if null)"), @ApiImplicitParam(name = "date<", dataType = "date", paramType = "query", value = "Filtering criteria for field `date`. (omitted if null)"), @ApiImplicitParam(name = "amount>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "unitPrice>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `unitPrice`. (omitted if null)"), @ApiImplicitParam(name = "unitPrice<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `unitPrice`. (omitted if null)"), @ApiImplicitParam(name = "unitPrice", dataType = "integer", paramType = "query", value = "Filtering criteria for field `unitPrice`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "name", dataType = "string", paramType = "query", value = "Filtering criteria for field `name` (omitted if null)"), @ApiImplicitParam(name = "abbreviation", dataType = "string", paramType = "query", value = "Filtering criteria for field `abbreviation`. (omitted if null)"), @ApiImplicitParam(name = "isSeller", dataType = "boolean", paramType = "query", value = "Filtering criteria for field sellingOrder. " + "Include transactions where user is selling (not required, default true"), @ApiImplicitParam(name = "isBuyer", dataType = "boolean", paramType = "query", value = "Filtering criteria for field buyingOrder. " + "Include transactions where user is buying. (not required, default true)") })
public Page<TransactionDTO> getOwnedTransactions(Pageable pageable,TransactionSpecification specification,boolean isSeller,boolean isBuyer){
    return transactionService.getOwnedTransactions(pageable, specification, isSeller, isBuyer).map(transaction -> mapper.map(transaction, TransactionDTO.class));
}


@GetMapping("/{id}/transaction")
@PreAuthorize("hasRole('ADMIN')")
@ApiOperation(value = "Page and filter user's transactions", notes = "Required role of: ADMIN \n" + "Given date must be in one format of: \n - yyyy-MM-ddThh:mm:ss.SSSZ (Z means Greenwich zone), " + "\n - yyyy-MM-ddThh:mm:ss.SSS-hh:mm \n - yyyy-MM-ddThh:mm:ss.SSS%2Bhh:mm (%2B means +)")
@ApiResponses({ @ApiResponse(code = 200, message = "Successfully paged and filtered user's transactions."), @ApiResponse(code = 403, message = "Access Denied."), @ApiResponse(code = 400, message = "The request could not be understood or was missing required parameters.", response = ErrorResponse.class), @ApiResponse(code = 404, message = "Given user not found", response = ErrorResponse.class) })
@ApiImplicitParams({ @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N).", defaultValue = "0"), @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "20"), @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. Multiple sort criteria are supported."), @ApiImplicitParam(name = "date>", dataType = "date", paramType = "query", value = "Filtering criteria for field `date`. (omitted if null)"), @ApiImplicitParam(name = "date<", dataType = "date", paramType = "query", value = "Filtering criteria for field `date`. (omitted if null)"), @ApiImplicitParam(name = "amount>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "unitPrice>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `unitPrice`. (omitted if null)"), @ApiImplicitParam(name = "unitPrice<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `unitPrice`. (omitted if null)"), @ApiImplicitParam(name = "unitPrice", dataType = "integer", paramType = "query", value = "Filtering criteria for field `unitPrice`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "name", dataType = "string", paramType = "query", value = "Filtering criteria for field `name` (omitted if null)"), @ApiImplicitParam(name = "abbreviation", dataType = "string", paramType = "query", value = "Filtering criteria for field `abbreviation`. (omitted if null)"), @ApiImplicitParam(name = "isSeller", dataType = "boolean", paramType = "query", value = "Filtering criteria for field sellingOrder. " + "Include transactions where specified user is selling (not required, default true"), @ApiImplicitParam(name = "isBuyer", dataType = "boolean", paramType = "query", value = "Filtering criteria for field buyingOrder. " + "Include transactions where specified user is buying. (not required, default true)") })
public Page<TransactionDTO> getUsersTransactions(Pageable pageable,TransactionSpecification specification,boolean isSeller,boolean isBuyer,Long id){
    return transactionService.getUserTransactions(pageable, specification, id, isSeller, isBuyer).map(transaction -> mapper.map(transaction, TransactionDTO.class));
}


@GetMapping("/stock/owned")
@PreAuthorize("hasRole('USER')")
@ApiOperation(value = "Page and filter logged in user's stocks", notes = "Required role: USER")
@ApiResponses({ @ApiResponse(code = 200, message = "Successfully paged and filtered logged user's stocks."), @ApiResponse(code = 403, message = "Access Denied.") })
@ApiImplicitParams({ @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N).", defaultValue = "0"), @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "20"), @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. Multiple sort criteria are supported."), @ApiImplicitParam(name = "name", dataType = "string", paramType = "query", value = "Filtering criteria for field `name` (omitted if null)"), @ApiImplicitParam(name = "abbreviation", dataType = "string", paramType = "query", value = "Filtering criteria for field `abbreviation`. (omitted if null)"), @ApiImplicitParam(name = "amount>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "currentPrice>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `currentPrice`. (omitted if null)"), @ApiImplicitParam(name = "currentPrice<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `currentPrice`. (omitted if null)"), @ApiImplicitParam(name = "currentPrice", dataType = "integer", paramType = "query", value = "Filtering criteria for field `currentPrice`. Param is exact value. (omitted if null)") })
public Page<ResourceDTO> getOwnedStocks(Pageable pageable,ResourceSpecification specification){
    return resourceService.getOwnedResources(pageable, specification);
}


@GetMapping
@PreAuthorize("hasRole('ADMIN')")
@ApiOperation(value = "Page and filter users", notes = "Required role: ADMIN")
@ApiResponses({ @ApiResponse(code = 200, message = "Successfully paged and filtered users."), @ApiResponse(code = 403, message = "Access Denied.") })
@ApiImplicitParams({ @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N).", defaultValue = "0"), @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "20"), @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. Multiple sort criteria are supported."), @ApiImplicitParam(name = "email", dataType = "string", paramType = "query", value = "Filtering criteria for field `email` (omitted if null)"), @ApiImplicitParam(name = "firstName", dataType = "string", paramType = "query", value = "Filtering criteria for field `firstName`. (omitted if null)"), @ApiImplicitParam(name = "lastName", dataType = "string", paramType = "query", value = "Filtering criteria for field `lastName`. (omitted if null)"), @ApiImplicitParam(name = "role", dataType = "string", paramType = "query", value = "Filtering criteria for field `role`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "money>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `money`. (omitted if null)"), @ApiImplicitParam(name = "money<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `money`. (omitted if null)"), @ApiImplicitParam(name = "money", dataType = "integer", paramType = "query", value = "Filtering criteria for field `money`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "tag", dataType = "string", paramType = "query", value = "Filtering criteria for field `tag`. Param is exact value.  (omitted if null)"), @ApiImplicitParam(name = "active", dataType = "boolean", paramType = "query", value = "Filtering criteria for field `active`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "search", dataType = "string", paramType = "query", value = "Searching criteria for fields: `firstName`, `lastName`, `email`. (omitted if null)") })
public Page<UserDTO> getUsers(Pageable pageable,UserSpecification specification){
    return userService.getUsers(pageable, specification).map(user -> mapper.map(user, UserDTO.class));
}


@GetMapping("/config/user-data")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@ApiOperation(value = "Retrieve logged in user", response = UserDTO.class, notes = "Required one role of: USER, ADMIN")
@ApiResponses({ @ApiResponse(code = 200, message = "User was successfully retrieved."), @ApiResponse(code = 404, message = "Given user not found.", response = ErrorResponse.class) })
public UserDTO getUser(Principal principal){
    return mapper.map(userService.findUserByEmail(principal.getName()), UserDTO.class);
}


@GetMapping("/{id}/order")
@PreAuthorize("hasRole('ADMIN')")
@ApiOperation(value = "List given user's orders", notes = "Required role of: ADMIN \n" + "Given date must be in one format of: \n - yyyy-MM-ddThh:mm:ss.SSSZ (Z means Greenwich zone), " + "\n - yyyy-MM-ddThh:mm:ss.SSS-hh:mm \n - yyyy-MM-ddThh:mm:ss.SSS%2Bhh:mm (%2B means +)")
@ApiResponses({ @ApiResponse(code = 200, message = "Successfully paged and filtered user's orders"), @ApiResponse(code = 400, message = "The request could not be understood or was missing required parameters.", response = ErrorResponse.class), @ApiResponse(code = 403, message = "Access Denied."), @ApiResponse(code = 404, message = "User not found.", response = ErrorResponse.class) })
@ApiImplicitParams({ @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N).", defaultValue = "0"), @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "20"), @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. Multiple sort criteria are supported."), @ApiImplicitParam(name = "name", dataType = "string", paramType = "query", value = "Filtering criteria for field `name` (omitted if null)"), @ApiImplicitParam(name = "abbreviation", dataType = "string", paramType = "query", value = "Filtering criteria for field `abbreviation`. (omitted if null)"), @ApiImplicitParam(name = "price>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `price`. (omitted if null)"), @ApiImplicitParam(name = "price<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `price`. (omitted if null)"), @ApiImplicitParam(name = "price", dataType = "integer", paramType = "query", value = "Filtering criteria for field `price`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "amount>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "priceType", dataType = "string", paramType = "query", value = "Filtering criteria for field `priceType`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "orderType", dataType = "string", paramType = "query", value = "Filtering criteria for field `orderType`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "creationDate>", dataType = "date", paramType = "query", value = "Filtering criteria for field `creationDate`. (omitted if null)"), @ApiImplicitParam(name = "creationDate<", dataType = "date", paramType = "query", value = "Filtering criteria for field `creationDate`. (omitted if null)"), @ApiImplicitParam(name = "dateExpiration>", dataType = "date", paramType = "query", value = "Filtering criteria for field `dateExpiration`. (omitted if null)"), @ApiImplicitParam(name = "dateExpiration<", dataType = "date", paramType = "query", value = "Filtering criteria for field `dateExpiration`. (omitted if null)"), @ApiImplicitParam(name = "dateClosing>", dataType = "date", paramType = "query", value = "Filtering criteria for field `creationClosing`. (omitted if null)"), @ApiImplicitParam(name = "dateClosing<", dataType = "date", paramType = "query", value = "Filtering criteria for field `creationClosing`. (omitted if null)"), @ApiImplicitParam(name = "active", dataType = "boolean", paramType = "query", value = "Filtering criteria for state of order. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "tag", dataType = "string", paramType = "query", value = "Filtering criteria for field `tag`. Param is exact value.  (omitted if null)") })
public Page<OrderDTO> getUserOwnedOrders(Pageable pageable,AllOrdersSpecification specification,Long id){
    return orderService.getOrdersByUser(pageable, specification, id).map(order -> mapper.map(order, OrderDTO.class));
}


@PutMapping("/{id}")
@PreAuthorize("hasRole('ADMIN')")
@ApiOperation(value = "Update existing user as administrator", notes = "Required role of: ADMIN")
@ApiResponses({ @ApiResponse(code = 200, message = "User's details were successfully updated."), @ApiResponse(code = 400, message = "The request could not be understood or was missing required parameters.", response = ErrorResponse.class), @ApiResponse(code = 403, message = "Access Denied."), @ApiResponse(code = 404, message = "User not found.", response = ErrorResponse.class) })
public void updateUser(EditUserDetailsDTO editUserDetailsDTO,Long id){
    userService.updateUser(id, editUserDetailsDTO);
}


@GetMapping("/{id}/stock")
@PreAuthorize("hasRole('ADMIN')")
@ApiOperation(value = "Page and filter given user's stocks", notes = "Required role: ADMIN")
@ApiResponses({ @ApiResponse(code = 200, message = "Successfully paged and filtered user's stocks."), @ApiResponse(code = 400, message = "The request could not be understood or was missing required parameters.", response = ErrorResponse.class), @ApiResponse(code = 403, message = "Access Denied."), @ApiResponse(code = 404, message = "Given user not found.", response = ErrorResponse.class) })
@ApiImplicitParams({ @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N).", defaultValue = "0"), @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "20"), @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. Multiple sort criteria are supported."), @ApiImplicitParam(name = "name", dataType = "string", paramType = "query", value = "Filtering criteria for field `name` (omitted if null)"), @ApiImplicitParam(name = "abbreviation", dataType = "string", paramType = "query", value = "Filtering criteria for field `abbreviation`. (omitted if null)"), @ApiImplicitParam(name = "amount>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "currentPrice>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `currentPrice`. (omitted if null)"), @ApiImplicitParam(name = "currentPrice<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `currentPrice`. (omitted if null)"), @ApiImplicitParam(name = "currentPrice", dataType = "integer", paramType = "query", value = "Filtering criteria for field `currentPrice`. Param is exact value. (omitted if null)") })
public Page<ResourceDTO> getUsersStocks(Pageable pageable,ResourceSpecification specification,Long id){
    return resourceService.getUsersResources(pageable, specification, id);
}


@PostMapping("/config/change-password")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@ApiOperation(value = "Change user's password", notes = "Required one role of: USER, ADMIN")
@ApiResponses({ @ApiResponse(code = 200, message = "User password was successfully changed."), @ApiResponse(code = 400, message = "The request could not be understood or was missing required parameters.", response = ErrorResponse.class) })
public void changePassword(ChangePasswordDTO changePasswordDTO,Principal principal){
    userService.changeUserPassword(changePasswordDTO, principal);
}


@PutMapping("/config/user-data")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
@ApiOperation(value = "Change logged in user first and last name", notes = "Required one role of: USER, ADMIN")
@ApiResponses({ @ApiResponse(code = 200, message = "User first and last name was successfully changed."), @ApiResponse(code = 400, message = "The request could not be understood or was missing required parameters.", response = ErrorResponse.class) })
public void changeDetails(EditUserNameDTO userName,Principal principal){
    userService.changeUserDetails(userName, principal);
}


}