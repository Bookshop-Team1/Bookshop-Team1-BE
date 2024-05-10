package com.tw.bootcamp.bookshop.book;

import com.tw.bootcamp.bookshop.apiUtil.APIResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

  private final BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/books")
  @Operation(
      summary = "List all books",
      description = "Lists all books in bookshop",
      tags = {"Books Service"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "List all books",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = BookResponse.class))
            })
      })
  ResponseEntity<APIResponse> list() {
    List<Book> books = bookService.fetchAll();
    APIResponse apiResponse =
        new APIResponse(
            HttpStatus.OK,
            books.stream().map(book -> book.toResponse()).collect(Collectors.toList()));
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @GetMapping("/books/{id}")
  @Operation(
      summary = "Fetch book details by id",
      description = "Fetch book details by id",
      tags = {"Books Service"})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Fetch book details by id",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = BookResponse.class))
            })
      })
  ResponseEntity<Object> fetchBookDetails(@PathVariable @Valid String id) {
    try {
      return ResponseEntity.ok(bookService.fetchBookDetails(id));
    } catch (ResourceNotFoundException ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
  }
}
