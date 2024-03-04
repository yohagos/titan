package com.titan.table;

import com.titan.product.ProductEntity;
import com.titan.table.request.TableRequest;
import com.titan.table.response.TableAddProductResponse;
import com.titan.table.response.TableCloseResponse;
import com.titan.table.response.TableResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/table")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    private static final Logger log = LoggerFactory.getLogger(TableController.class);

    @GetMapping
    public ResponseEntity<List<TableEntity>> getTables() {
        log.info(tableService.getTableList().toString());
        return ResponseEntity.ok(tableService.getTableList());
    }

    @GetMapping("/{tableId}")
    public ResponseEntity<List<ProductEntity>> getProductsForTable(
            @PathVariable(name = "tableId") Long id
    ) {
        return ResponseEntity.ok(tableService.getProductsByTableId(id));
    }

    @PutMapping
    public ResponseEntity updateTables(
            @RequestBody List<TableEntity> tables
    ) {
        tableService.updateTablesPositions(tables);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity<TableResponse> addTable(
            @RequestBody TableRequest request
    ) {
        return ResponseEntity.ok(tableService.createTable(request));
    }

    @PutMapping("/store/{tableId}")
    public ResponseEntity<TableAddProductResponse> addProductToTable(
            @PathVariable(name = "tableId") Long id,
            @RequestBody List<ProductEntity> request
    ) {
        return ResponseEntity.ok(tableService.addProductToTable(id, request));
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

    @PutMapping("/{tableId}")
    public ResponseEntity<?> occupyTable(
            @PathVariable(name = "tableId") Long id
    ) {
        tableService.occupyTableNow(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{tableId}")
    public ResponseEntity<?> deleteTableById(
            @PathVariable(name = "tableId") Long id
    ) {
        tableService.deleteTable(id);
        return ResponseEntity.ok().build();
    }
}
