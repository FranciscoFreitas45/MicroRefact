package com.project.stockexchangeappbackend.rest;
 import com.project.stockexchangeappbackend.dto.ErrorResponse;
import com.project.stockexchangeappbackend.dto.TransactionDTO;
import com.project.stockexchangeappbackend.repository.specification.TransactionSpecification;
import com.project.stockexchangeappbackend.service.TransactionService;
import io.swagger.annotations;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation;
import springfox.documentation.annotations.ApiIgnore;
import com.project.stockexchangeappbackend.Interface.TransactionService;
@RestController
@RequestMapping("/api/transaction")
@CrossOrigin("*")
@AllArgsConstructor
@Api(value = "Transaction", description = "REST API for transactions' management", tags = "Transaction")
@ApiResponses({ @ApiResponse(code = 401, message = "Unauthorized.") })
public class TransactionController {

 private  TransactionService transactionService;

 private  ModelMapper mapper;


@GetMapping
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@ApiOperation(value = "Page and filter transactions", notes = "Required one role of: ADMIN, USER \n" + "Given date must be in one format of: \n - yyyy-MM-ddThh:mm:ss.SSSZ (Z means Greenwich zone), " + "\n - yyyy-MM-ddThh:mm:ss.SSS-hh:mm \n - yyyy-MM-ddThh:mm:ss.SSS%2Bhh:mm (%2B means +)")
@ApiResponses(@ApiResponse(code = 200, message = "Successfully paged and filtered transactions."))
@ApiImplicitParams({ @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N).", defaultValue = "0"), @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page.", defaultValue = "20"), @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. Multiple sort criteria are supported."), @ApiImplicitParam(name = "date>", dataType = "date", paramType = "query", value = "Filtering criteria for field `date`. (omitted if null)"), @ApiImplicitParam(name = "date<", dataType = "date", paramType = "query", value = "Filtering criteria for field `date`. (omitted if null)"), @ApiImplicitParam(name = "amount>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. (omitted if null)"), @ApiImplicitParam(name = "amount", dataType = "integer", paramType = "query", value = "Filtering criteria for field `amount`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "unitPrice>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `unitPrice`. (omitted if null)"), @ApiImplicitParam(name = "unitPrice<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `unitPrice`. (omitted if null)"), @ApiImplicitParam(name = "unitPrice", dataType = "integer", paramType = "query", value = "Filtering criteria for field `unitPrice`. Param is exact value. (omitted if null)"), @ApiImplicitParam(name = "name", dataType = "string", paramType = "query", value = "Filtering criteria for field `name` (omitted if null)"), @ApiImplicitParam(name = "abbreviation", dataType = "string", paramType = "query", value = "Filtering criteria for field `abbreviation`. (omitted if null)") })
public Page<TransactionDTO> getTransactions(Pageable pageable,TransactionSpecification specification){
    return transactionService.findAllTransactions(pageable, specification).map(transaction -> mapper.map(transaction, TransactionDTO.class));
}


@GetMapping("/{id}")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
@ApiOperation(value = "Retrieve transaction by id", response = TransactionDTO.class, notes = "Required one role of: ADMIN, USER")
@ApiResponses({ @ApiResponse(code = 200, message = "Transaction was successfully retrieved."), @ApiResponse(code = 404, message = "Given transaction not found.", response = ErrorResponse.class) })
public TransactionDTO getTransactionDetails(Long id){
    return mapper.map(transactionService.findTransactionById(id), TransactionDTO.class);
}


}