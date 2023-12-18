package com.titan.table;

import com.titan.table.request.TableAddProductRequest;
import com.titan.table.request.TableRequest;
import com.titan.table.response.TableAddProductResponse;
import com.titan.table.response.TableCloseResponse;
import com.titan.table.response.TableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/table")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @GetMapping
    public ResponseEntity<List<TableEntity>> getTables() {
        return ResponseEntity.ok(tableService.getTableList());
    }

    @PostMapping("/add")
    public ResponseEntity<TableResponse> addTable(
            @RequestBody TableRequest request
    ) {
        return ResponseEntity.ok(tableService.createTable(request));
    }

    @PutMapping("/{tableId}")
    public ResponseEntity<?> occupyTable(
            @PathVariable(name = "tableId") Long id
    ) {
        tableService.occupyTableNow(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<TableAddProductResponse> addProductToTable(
            @RequestBody TableAddProductRequest request
    ) {
        return ResponseEntity.ok(tableService.addProductToTable(request));
    }

    @PutMapping("/edit/{tableId}")
    public ResponseEntity<TableResponse> editTableById(
            @RequestBody TableRequest request,
            @PathVariable(name = "tableId") Long id
    ) {
        return  ResponseEntity.ok(tableService.editTable(id, request));
    }

    @PutMapping("/close/{tableId}")
    public ResponseEntity<TableCloseResponse> closeTable(
            @PathVariable(name = "tableId") Long id
    ) {
        return ResponseEntity.ok(tableService.closeTable(id));
    }

    @DeleteMapping("/{tableId}")
    public ResponseEntity<?> deleteTableById(
            @PathVariable(name = "tableId") Long id
    ) {
        tableService.deleteTable(id);
        return ResponseEntity.ok().build();
    }
}
